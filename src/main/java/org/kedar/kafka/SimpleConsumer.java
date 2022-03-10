package org.kedar.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class SimpleConsumer {

    SimpleConsumer(){

        // Boilerplate Properties
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "consumer-group");

        // Create Consumer client
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);

        // Subscribe to topic(s)
        consumer.subscribe(Arrays.asList("messenger"));

        // Poll and consume records
        while(true){
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));

            for(ConsumerRecord record: records){
                System.out.println("Topic: "+record.topic()+" Key: "+record.key()+" Value: "+record.value());
            }
        }
    }

    public static void main(String[] args) {
        SimpleConsumer consumer = new SimpleConsumer();
    }
}
