package org.aicodinglab.kotlinspring.kafka

data class KafkaMessage(
    val topic: String,
    val type: String,
    val offset: Long,
    val timestamp: Long,
)