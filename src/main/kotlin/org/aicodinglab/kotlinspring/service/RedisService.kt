package org.aicodinglab.kotlinspring.service

import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Service

@Service
class RedisService(val stringRedisTemplate: StringRedisTemplate) {

    fun setValue(key: String, value: String) {
        stringRedisTemplate.opsForValue().set(key, value)
    }

    fun getValue(key: String): String? {
        return stringRedisTemplate.opsForValue().get(key)
    }
}