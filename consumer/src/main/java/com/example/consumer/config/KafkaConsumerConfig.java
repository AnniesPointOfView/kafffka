package com.example.consumer.config;

import lombok.*;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.serialization.*;
import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.kafka.annotation.*;
import org.springframework.kafka.config.*;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.*;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    public static final String TOPIC_NAME = "kafka.example.topic";

    @Value("${spring.kafka.bootstrap-servers}")
    private String serverAddress;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    @Bean
    public ConsumerFactory<String, Object> consumerFactory() {
        val config = new HashMap<String, Object>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, serverAddress);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        return new DefaultKafkaConsumerFactory<>(config);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Message> kafkaListenerContainerFactory(
            ConsumerFactory<String, Object> consumerFactory
    ) {
        val factory = new ConcurrentKafkaListenerContainerFactory<String, Message>();
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }

}
