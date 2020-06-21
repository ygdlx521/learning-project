package online.daliang.flink.test

import java.util.Properties

import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.api.java.utils.ParameterTool
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.streaming.connectors.kafka.{FlinkKafkaConsumer010, FlinkKafkaProducer010}

object FlinkWithKafka {

  def main(args: Array[String]): Unit = {
    import org.apache.flink.api.scala._
    val params: ParameterTool =  ParameterTool.fromArgs(args)

    // 创建流处理环境
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment

    val properties = new Properties()
//    properties.setProperty("bootstrap.servers", "master:9092,slave0:9092,slave1:9092")
    properties.setProperty("bootstrap.servers", "node00:9092,node01:9092,node02:9092")
    properties.setProperty("group.id", "consumer-group")
    properties.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    properties.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    properties.setProperty("auto.offset.reset", "latest")

    val stream: DataStream[String] = env.addSource(
      new FlinkKafkaConsumer010[String]("first_test", new SimpleStringSchema(), properties)
    )

    val producerProperties = new Properties()
    producerProperties.setProperty("bootstrap.servers", "node00:9092,node01:9092,node02:9092")


    stream.addSink(
      new FlinkKafkaProducer010[String](
        "second_test", new SimpleStringSchema(), producerProperties)
    )

    // 启动executor，执行任务
    env.execute("Flink Read Write Kafka")
  }



}
