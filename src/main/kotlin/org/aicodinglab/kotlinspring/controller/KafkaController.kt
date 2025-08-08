package org.aicodinglab.kotlinspring.controller

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
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

        runBlocking {

            val jobs = List(dto.count) { index ->
                launch {
                    kafkaTemplate.send("test-topic",
                        KafkaMessage(
                            dto.message,
                            "coroutine",
                            index.toLong(),
                            System.currentTimeMillis()))
                }
            }

            jobs.joinAll()
        }

        return ResponseEntity.ok("Message sent to Kafka")
    }
}