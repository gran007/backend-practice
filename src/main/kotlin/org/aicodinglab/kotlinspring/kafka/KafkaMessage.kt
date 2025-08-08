package org.aicodinglab.kotlinspring.kafka

data class KafkaMessage(
    val topic: String,
    val offset: Long,
    val timestamp: Long,
)