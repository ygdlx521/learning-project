package online.daliang.flink.streaming.dwd

import java.util.Properties
// 加下边此行可以减少报错，要查一下
import org.apache.flink.streaming.api.scala._

import online.daliang.flink.streaming.model.{DwdMember, DwdMemberRegisterType, DwdMemberTransaction}
import online.daliang.flink.streaming.serde.{DwdMemberDeserializationSchema, DwdMemberRegisterTypeDeserializationSchema, DwdMemberTransactionDeserializationSchema}
import org.apache.flink.api.common.restartstrategy.RestartStrategies
import org.apache.flink.api.java.utils.ParameterTool
import org.apache.flink.contrib.streaming.state.RocksDBStateBackend
import org.apache.flink.runtime.state.StateBackend
import org.apache.flink.streaming.api.environment.CheckpointConfig
import org.apache.flink.streaming.api.functions.timestamps.BoundedOutOfOrdernessTimestampExtractor
import org.apache.flink.streaming.api.{CheckpointingMode, TimeCharacteristic}
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.streaming.api.windowing.triggers.CountTrigger
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer010

object DwdJoinData {

  val BOOTSTRAP_SERVERS = "bootstrap.servers"
  val GROUP_ID = "group.id"
  val RETRIES = "retries"
  val TOPIC = "topic"

  def main(args: Array[String]): Unit = {
    val params = ParameterTool.fromArgs(args)
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    //    val env = StreamExecutionEnvironment.createLocalEnvironmentWithWebUI()

    env.getConfig.setGlobalJobParameters(params)
    env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime) //设置时间模式为事件时间

    //checkpoint设置
    env.enableCheckpointing(60000l) //1分钟做一次checkpoint
    val checkpointConfig = env.getCheckpointConfig
    checkpointConfig.setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE) //仅仅一次
    checkpointConfig.setMinPauseBetweenCheckpoints(30000l) //设置checkpoint间隔时间30秒
    checkpointConfig.setCheckpointTimeout(10000l) //设置checkpoint超时时间
    checkpointConfig.enableExternalizedCheckpoints(CheckpointConfig.ExternalizedCheckpointCleanup.RETAIN_ON_CANCELLATION) //cancel时保留checkpoint
    //设置statebackend 为rockdb
    val stateBackend: StateBackend = new RocksDBStateBackend("hdfs://master/user/flink/rocks_db_checkpoint")
    env.setStateBackend(stateBackend)

    //设置重启策略   重启3次 间隔10秒
    env.setRestartStrategy(RestartStrategies.fixedDelayRestart(3, org.apache.flink.api.common.time.Time.seconds(10)))

    val consumerProps = new Properties()
    consumerProps.setProperty(BOOTSTRAP_SERVERS, params.get(BOOTSTRAP_SERVERS))
    consumerProps.setProperty(GROUP_ID, params.get(GROUP_ID))
    val dwdMemberSource = new FlinkKafkaConsumer010[DwdMember]("dwd_member", new DwdMemberDeserializationSchema, consumerProps)
    val dwdMemberTransactionSource = new FlinkKafkaConsumer010[DwdMemberTransaction]("dwd_member_transaction", new DwdMemberTransactionDeserializationSchema, consumerProps)
    val dwdMemberRegisterTypeSource = new FlinkKafkaConsumer010[DwdMemberRegisterType]("dwd_member_register_type", new DwdMemberRegisterTypeDeserializationSchema, consumerProps)
    dwdMemberSource.setStartFromEarliest()
    dwdMemberTransactionSource.setStartFromEarliest()
    dwdMemberRegisterTypeSource.setStartFromEarliest()


    // 注册时间，也就是这条日志的创建时间，作为 事件时间 水位线设置为10分钟
    val dwdMemberStream = env.addSource(dwdMemberSource).assignTimestampsAndWatermarks(new BoundedOutOfOrdernessTimestampExtractor[DwdMember](Time.seconds(10)) {
      override def extractTimestamp(element: DwdMember): Long = {
        element.register.toLong
      }
    })

    // 创建时间作为 事件时间  水位线设置10分钟
    val dwdMemberTransactionStream = env.addSource(dwdMemberTransactionSource).assignTimestampsAndWatermarks(
      new BoundedOutOfOrdernessTimestampExtractor[DwdMemberTransaction](Time.seconds(10)) {
      override def extractTimestamp(element: DwdMemberTransaction): Long = {
        element.createtime.toLong
      }
    })
    // 创建时间作为 事件时间  水位线设置10分钟
    val dwdMemberRegisterTypeStream = env.addSource(dwdMemberRegisterTypeSource).assignTimestampsAndWatermarks(
      new BoundedOutOfOrdernessTimestampExtractor[DwdMemberRegisterType](Time.seconds(10)) {
      override def extractTimestamp(element: DwdMemberRegisterType): Long = {
        element.createtime.toLong
      }
    })

    //    用户表先关联注册表 以用户表为主表 用cogroup 实现left join
    val dwdMemberLeftJoinRegisterTypeStream = dwdMemberStream.coGroup(dwdMemberRegisterTypeStream)
      .where(item => item.uid + "_" + item.dn).equalTo(item => item.uid + "_" + item.dn)
      //      .window(SlidingEventTimeWindows.of(Time.seconds(10), Time.seconds(5)))
      //      .window(ProcessingTimeSessionWindows.withGap(Time.seconds(5)))
      .window(TumblingEventTimeWindows.of(Time.minutes(10)))
      .trigger(CountTrigger.of(1))
      .apply(new MemberLeftJoinRegtype)
      .assignTimestampsAndWatermarks(new BoundedOutOfOrdernessTimestampExtractor[String](Time.seconds(10)) {
        override def extractTimestamp(element: String): Long = {
          val register = ParseJsonData.getJsonData(element).getString("register")
          register.toLong
        }
      })
    //再根据用户信息跟消费金额进行关联 用户表为主表进行 left join 根据uid和dn进行join
    val resultStream = dwdmemberLeftJoinRegtyeStream.coGroup(dwdMemberTransactionStream)
      .where(item => {
        val jsonObject = ParseJsonData.getJsonData(item)
        val uid = jsonObject.getString("uid")
        val dn = jsonObject.getString("dn")
        uid + "_" + dn
      }).equalTo(item => item.uid + "_" + item.dn)
      .window(TumblingEventTimeWindows.of(Time.minutes(10)))
      .trigger(CountTrigger.of(1))
      .apply(new resultStreamCoGroupFunction)
    //    resultStream.print()
    val result = AsyncDataStream.orderedWait(resultStream, new DimHbaseAsyncFunction, 10l, java.util.concurrent.TimeUnit.SECONDS, 10)
    result.print()
    env.execute()
  }
}
