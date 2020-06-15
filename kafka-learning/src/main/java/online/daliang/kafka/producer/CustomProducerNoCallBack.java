package online.daliang.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class CustomProducerNoCallBack {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Properties props = new Properties();

        //kafka集群，broker-list
        props.put("bootstrap.servers", "master:9092,slave0:9092,slave1:9092");

        props.put("acks", "all");

        //重试次数
        props.put("retries", 1);

        //批次大小
        props.put("batch.size", 16384);

        //等待时间
        props.put("linger.ms", 1);

        //RecordAccumulator缓冲区大小
        props.put("buffer.memory", 33554432);

        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<String, String>(props);

        for (int i = 0; i < 1000000; i++) {
            producer.send(new ProducerRecord<String, String>("first", Integer.toString(i), Integer.toString(i)));
        }

        producer.close();
    }

}
