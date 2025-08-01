package org.aicodinglab.kotlinspring.dto

import org.aicodinglab.kotlinspring.entity.User
import java.time.LocalDateTime

data class UserDto(
    val userId: Long,
    val email: String,
    val userName: String,
    val password: String,
    val createAt: LocalDateTime,
    val updateAt: LocalDateTime,
) {
    constructor(user: User) : this(
        userId = user.userId,
        email = user.email,
        userName = user.userName,
        password = user.password,
        createAt = user.createAt,
        updateAt = user.updateAt,
    )
}