package org.aicodinglab.backendhanbi.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.sql.Timestamp
import java.time.LocalDateTime

@Entity
class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var userId: Long,
    var email: String,
    @Column(name="user_name") var userName: String,
    var password: String,
    @Column(name="create_at") var createAt: LocalDateTime = LocalDateTime.now(),
    @Column(name="update_at") var updateAt: LocalDateTime = LocalDateTime.now(),
    )