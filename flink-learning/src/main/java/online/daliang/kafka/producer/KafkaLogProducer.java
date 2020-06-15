package online.daliang.kafka.producer;

import com.alibaba.fastjson.JSON;
import online.daliang.kafka.model.Member;
import online.daliang.kafka.utils.MemberLog;
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
        for (int i = 0; i < 10000; i++) {
            Member member = MemberLog.generateLog(String.valueOf(i));
            String jsonString = JSON.toJSONString(member);
            kafkaProducer.send(new ProducerRecord<String, String >("member_log", jsonString));
        }
        kafkaProducer.flush();
        kafkaProducer.close();


    }
}
