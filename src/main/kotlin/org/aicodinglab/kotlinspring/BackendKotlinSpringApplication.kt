package org.aicodinglab.kotlinspring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
//@ComponentScan(basePackages = ["org.springframework"])
class BackendKotlinSpringApplication

fun main(args: Array<String>) {
    runApplication<BackendKotlinSpringApplication>(*args)
}
