package online.daliang.flink.streaming.serde

import com.google.gson.Gson
import online.daliang.flink.streaming.model.DwdMemberRegisterType
import org.apache.flink.api.common.typeinfo.{TypeHint, TypeInformation}
import org.apache.flink.streaming.connectors.kafka.KafkaDeserializationSchema
import org.apache.kafka.clients.consumer.ConsumerRecord

class DwdMemberRegisterTypeDeserializationSchema extends KafkaDeserializationSchema[DwdMemberRegisterType]{
  override def isEndOfStream(t: DwdMemberRegisterType): Boolean = false

  override def deserialize(consumerRecord: ConsumerRecord[Array[Byte], Array[Byte]]): DwdMemberRegisterType = {
    val gson = new Gson()
    gson.fromJson(new String(consumerRecord.value(), "utf-8"), classOf[DwdMemberRegisterType])
  }

  override def getProducedType: TypeInformation[DwdMemberRegisterType] = {
    TypeInformation.of(new TypeHint[DwdMemberRegisterType] {})
  }
}
