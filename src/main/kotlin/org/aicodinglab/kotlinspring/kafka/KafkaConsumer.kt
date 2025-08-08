package org.aicodinglab.kotlinspring.kafka

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class KafkaConsumer {

    @KafkaListener(topics = ["test-topic"], groupId = "test-group", containerFactory = "kafkaListenerContainerFactory")
    fun consume(message: ConsumerRecord<String, KafkaMessage>) {
        println("Consumed message: ${message.value()}")
    }
}