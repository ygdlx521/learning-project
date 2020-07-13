package online.daliang.flink.streaming.serde;

import com.google.gson.Gson
import online.daliang.flink.streaming.model.DwdMember
import org.apache.flink.api.common.typeinfo.{TypeHint, TypeInformation}
import org.apache.flink.streaming.connectors.kafka.KafkaDeserializationSchema
import org.apache.kafka.clients.consumer.ConsumerRecord


class DwdMemberDeserializationSchema extends KafkaDeserializationSchema[DwdMember] {

    override def isEndOfStream(t: DwdMember): Boolean = false

    override def deserialize(consumerRecord: ConsumerRecord[Array[Byte], Array[Byte]]): DwdMember = {
        val gson = new Gson()
        gson.fromJson(new String(consumerRecord.value(), "utf-8"),  classOf[DwdMember])

    }

    override def getProducedType: TypeInformation[DwdMember] = {
        TypeInformation.of(new TypeHint[DwdMember] {})
    }

}
