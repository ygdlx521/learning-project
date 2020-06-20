package online.daliang.kafka.producer;

import com.alibaba.fastjson.JSON;
import online.daliang.kafka.producer.model.*;
import online.daliang.kafka.producer.utils.*;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;


import java.util.Properties;

public class KafkaLogProducer {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "node00:9092,node01:9092,node02:9092");
        props.put("acks", "-1");
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer kafkaProducer = new KafkaProducer<String, String>(props);
        for (int i = 0; i < 7000000; i++) {
            String jsonString;

            if(i < 10) {
                Advertisement advertisement = AdvertisementLog.generateLog(String.valueOf(i));
                jsonString = JSON.toJSONString(advertisement);
                kafkaProducer.send(
                        new ProducerRecord<String, String>("ods_advertisement", jsonString)
                );
            }

            if(i < 5000000) {
                Member member = MemberLog.generateLog(String.valueOf(i));
                jsonString = JSON.toJSONString(member);
                kafkaProducer.send(
                        new ProducerRecord<String, String>("ods_member", jsonString)
                );
            }

            if(i < 5000000) {
                MemberRegisterType memberRegisterType = MemberRegisterTypeLog.generateLog(String.valueOf(i), "webA");
                jsonString = JSON.toJSONString(memberRegisterType);
                kafkaProducer.send(
                        new ProducerRecord<String, String>("ods_member_register_type", jsonString)
                );
            }

            if(i < 7000000) {
                MemberTransaction memberTransaction = MemberTransactionLog.generateLog();
                jsonString = JSON.toJSONString(memberTransaction);
                kafkaProducer.send(
                        new ProducerRecord<String, String>("ods_member_transaction", jsonString)
                );
            }

            if(i < 5) {
                MemberVipLevel memberVipLevel = MemberVipLevelLog.generateLog(String.valueOf(i));
                jsonString = JSON.toJSONString(memberVipLevel);
                kafkaProducer.send(
                        new ProducerRecord<String, String>("ods_member_vip_level", jsonString)
                );
            }

            if(i < 5) {
                WebSite webSiteA = WebSiteLog.generateLog(String.valueOf(i), "webA");
                jsonString = JSON.toJSONString(webSiteA);
                kafkaProducer.send(
                        new ProducerRecord<String, String>("ods_website", jsonString)
                );
                WebSite webSiteB = WebSiteLog.generateLog(String.valueOf(i), "webB");
                jsonString = JSON.toJSONString(webSiteB);
                kafkaProducer.send(
                        new ProducerRecord<String, String>("ods_website", jsonString)
                );
                WebSite webSiteC = WebSiteLog.generateLog(String.valueOf(i), "webC");
                jsonString = JSON.toJSONString(webSiteC);
                kafkaProducer.send(
                        new ProducerRecord<String, String>("ods_website", jsonString)
                );
            }

//            kafkaProducer.send(new ProducerRecord<String, String >("member_log", jsonString));
        }
        kafkaProducer.flush();
        kafkaProducer.close();


    }
}
