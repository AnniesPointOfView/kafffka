package com.example.consumer.service;

import lombok.extern.slf4j.*;
import org.springframework.kafka.annotation.*;
import org.springframework.stereotype.*;

import static com.example.consumer.config.KafkaConsumerConfig.TOPIC_NAME;

@Slf4j
@Service
public class KafkaConsumer {



    @KafkaListener(topicPartitions = @TopicPartition(
            topic = TOPIC_NAME,
            partitionOffsets = @PartitionOffset(
                    partition = "0", initialOffset = "0"
            )))
    public void consumePart0(String message) {
        log.info("Receive new message! {}", message);
    }

    @KafkaListener(topicPartitions = @TopicPartition(
            topic = TOPIC_NAME,
            partitionOffsets = @PartitionOffset(
                    partition = "1", initialOffset = "0"
            )))
    public void consumePart1(String message) {
        log.info("Receive new message! {}", message);
    }
}
