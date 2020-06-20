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
        props.put("bootstrap.servers", "master:9092,slave0:9092,slave1:9092");
        props.put("acks", "-1");
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer kafkaProducer = new KafkaProducer<String, String>(props);
        for (int i = 0; i < 100; i++) {
            String jsonString;
            Advertisement advertisement = AdvertisementLog.generateLog(String.valueOf(i));
            jsonString = JSON.toJSONString(advertisement);
            System.out.println(jsonString);

            Member member = MemberLog.generateLog(String.valueOf(i));
            jsonString = JSON.toJSONString(member);
            System.out.println(jsonString);

            MemberRegisterTypeLog.generateLog(String.valueOf(i), "webA");
            jsonString = JSON.toJSONString(member);
            System.out.println(jsonString);

            MemberTransaction memberTransaction = MemberTransactionLog.generateLog();
            jsonString = JSON.toJSONString(memberTransaction);
            System.out.println(jsonString);

            MemberVipLevel memberVipLevel = MemberVipLevelLog.generateLog(String.valueOf(i));
            jsonString = JSON.toJSONString(memberVipLevel);
            System.out.println(jsonString);

            WebSite webSite = WebSiteLog.generateLog(String.valueOf(i), "webA");
            jsonString = JSON.toJSONString(webSite);
            System.out.println(jsonString);

//            kafkaProducer.send(new ProducerRecord<String, String >("member_log", jsonString));
        }
        kafkaProducer.flush();
        kafkaProducer.close();


    }
}
