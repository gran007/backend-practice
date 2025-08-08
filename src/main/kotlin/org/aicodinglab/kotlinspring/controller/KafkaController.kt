package org.aicodinglab.kotlinspring.controller

import org.aicodinglab.kotlinspring.dto.KafkaDto
import org.aicodinglab.kotlinspring.kafka.KafkaMessage
import org.springframework.http.ResponseEntity
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/kafka")
class KafkaController(
    private val kafkaTemplate: KafkaTemplate<String, KafkaMessage>
) {
    @PostMapping("messages")
    fun sendMessage(@RequestBody dto: KafkaDto): ResponseEntity<String> {
        for (i in 1..dto.count) {
            kafkaTemplate.send("test-topic",
                KafkaMessage(dto.message, i.toLong(), System.currentTimeMillis()))
        }
        return ResponseEntity.ok("Message sent to Kafka")
    }
}