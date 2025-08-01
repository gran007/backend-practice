package org.aicodinglab.kotlinspring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BackendHanbiApplication

fun main(args: Array<String>) {
    runApplication<BackendHanbiApplication>(*args)
}
