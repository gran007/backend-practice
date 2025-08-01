package org.aicodinglab.kotlinspring.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name="USERS")
class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var userId: Long,
    var email: String,
    var userName: String,
    var password: String,
    var createAt: LocalDateTime = LocalDateTime.now(),
    var updateAt: LocalDateTime = LocalDateTime.now(),
    ) {
    constructor(email: String, userName: String, password: String) : this(0, email, userName, password, LocalDateTime.now(), LocalDateTime.now())
}