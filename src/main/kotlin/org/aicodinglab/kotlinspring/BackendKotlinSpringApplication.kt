package org.aicodinglab.kotlinspring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@EnableCaching
class BackendKotlinSpringApplication

fun main(args: Array<String>) {
    runApplication<BackendKotlinSpringApplication>(*args)
}
