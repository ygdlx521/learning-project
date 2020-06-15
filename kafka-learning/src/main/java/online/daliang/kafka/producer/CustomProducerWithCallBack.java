package online.daliang.kafka.producer;

import org.apache.kafka.clients.producer.*;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class CustomProducerWithCallBack {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Properties props = new Properties();

        props.put("bootstrap.servers", "master:9092");//kafka集群，broker-list

        props.put("acks", "all");

        props.put("retries", 1);//重试次数

        props.put("batch.size", 16384);//批次大小

        props.put("linger.ms", 1);//等待时间

        props.put("buffer.memory", 33554432);//RecordAccumulator缓冲区大小

        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<String, String>(props);

        for (int i = 0; i < 100; i++) {
            producer.send(new ProducerRecord<String, String>(
                    "first",
                    Integer.toString(i),
                    Integer.toString(i)),
                    new Callback() {

                        //回调函数，该方法会在Producer收到ack时调用，为异步调用
                        public void onCompletion(RecordMetadata metadata, Exception exception) {
                            if (exception == null) {
                                System.out.println("success->" + metadata.offset());
                            } else {
                                exception.printStackTrace();
                            }
                        }
                    });
        }
        producer.close();
    }

}
