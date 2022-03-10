package org.kedar.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class SimpleProducer {

    SimpleProducer(){
        Properties properties = new Properties();

        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");


        ProducerRecord record = new ProducerRecord("messenger", "name", "hello, yoo!");

        KafkaProducer producer = new KafkaProducer(properties);
        producer.send(record);
        producer.close();
    }

    public static void main(String[] args) {
        SimpleProducer producer = new SimpleProducer();
    }
}
