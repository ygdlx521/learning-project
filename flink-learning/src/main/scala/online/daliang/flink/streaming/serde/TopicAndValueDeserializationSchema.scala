package online.daliang.flink.streaming.serde

import online.daliang.flink.streaming.model.TopicAndValue
import org.apache.flink.api.common.typeinfo.{TypeHint, TypeInformation}
import org.apache.flink.streaming.connectors.kafka.KafkaDeserializationSchema
import org.apache.kafka.clients.consumer.ConsumerRecord

class TopicAndValueDeserializationSchema extends KafkaDeserializationSchema[TopicAndValue] {

  override def isEndOfStream(t: TopicAndValue): Boolean = false

  override def deserialize(consumerRecord: ConsumerRecord[Array[Byte], Array[Byte]]): TopicAndValue = {
    TopicAndValue(consumerRecord.topic(), new String(consumerRecord.value(), "utf-8"))
  }

  override def getProducedType: TypeInformation[TopicAndValue] = {
    TypeInformation.of(new TypeHint[TopicAndValue] {})
  }

}
