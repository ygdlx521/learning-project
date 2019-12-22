package online.daliang.flink.streaming

import org.apache.flink.api.java.utils.ParameterTool
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}


object StreamingWordCount {

  def main(args: Array[String]): Unit = {
    val params: ParameterTool =  ParameterTool.fromArgs(args)
    val host: String = params.get("host","localhost")
    val port: Int = params.getInt("port", 9090)

    // 创建流处理环境
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    // 接收socket文本流
    val textDstream: DataStream[String] = env.socketTextStream(host, port)

    // flatMap和Map需要引用的隐式转换
    import org.apache.flink.api.scala._
    val dataStream: DataStream[(String, Int)] = textDstream.flatMap(_.split("\\s")).filter(_.nonEmpty).map((_, 1)).keyBy(0).sum(1)

    dataStream.print().setParallelism(3)

    // 启动executor，执行任务
    env.execute("Socket stream word count")
  }

}
