package online.daliang.flink.streaming.serde

import java.nio.charset.Charset

import online.daliang.flink.streaming.model.TopicAndValue
import org.apache.flink.streaming.util.serialization.KeyedSerializationSchema


class DwdKafkaProducerSerializationSchema extends KeyedSerializationSchema[TopicAndValue] {
  val serialVersionUID = 1351665280744549933L;

  override def serializeKey(element: TopicAndValue): Array[Byte] = null

  override def serializeValue(element: TopicAndValue): Array[Byte] = {
    element.value.getBytes(Charset.forName("utf-8"))
  }

  override def getTargetTopic(element: TopicAndValue): String = {
    "dwd_" + element.topic.substring(4)
  }
}
