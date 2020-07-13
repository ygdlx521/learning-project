package online.daliang.flink.streaming.serde

import com.google.gson.Gson
import online.daliang.flink.streaming.model.DwdMemberTransaction
import org.apache.flink.api.common.typeinfo.{TypeHint, TypeInformation}
import org.apache.flink.streaming.connectors.kafka.KafkaDeserializationSchema
import org.apache.kafka.clients.consumer.ConsumerRecord

class DwdMemberTransactionDeserializationSchema extends KafkaDeserializationSchema[DwdMemberTransaction] {
  override def isEndOfStream(t: DwdMemberTransaction): Boolean = false

  override def deserialize(consumerRecord: ConsumerRecord[Array[Byte], Array[Byte]]): DwdMemberTransaction = {
    val gson = new Gson()
    gson.fromJson(new String(consumerRecord.value(), "utf-8"), classOf[DwdMemberTransaction])
  }

  override def getProducedType: TypeInformation[DwdMemberTransaction] = {
    TypeInformation.of(new TypeHint[DwdMemberTransaction] {})
  }
}
