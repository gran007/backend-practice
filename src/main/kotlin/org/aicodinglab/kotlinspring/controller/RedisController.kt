package org.aicodinglab.kotlinspring.controller

import com.google.gson.Gson
import org.aicodinglab.kotlinspring.dto.RedisDto
import org.aicodinglab.kotlinspring.service.RedisService
import org.springframework.cache.annotation.Cacheable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/redis")
class RedisController(val redisService: RedisService) {

    val gson = Gson()

    @Cacheable("redisCacheDto")
    @GetMapping("cache")
    fun cacheTest(): String {
        Thread.sleep(1000)
        return gson.toJson(RedisDto("Jack", 20))
    }

    @GetMapping("data")
    fun getData(): String {
        return redisService.getValue("data").toString()
    }

    @PostMapping("data")
    fun setData(@RequestBody dto: RedisDto): ResponseEntity<String> {
        redisService.setValue("data", gson.toJson(dto))
        return ResponseEntity.ok("success")
    }
}
