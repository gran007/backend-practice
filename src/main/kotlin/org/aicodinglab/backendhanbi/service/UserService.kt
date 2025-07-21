package org.aicodinglab.backendhanbi.service

import jakarta.transaction.Transactional
import org.aicodinglab.backendhanbi.entity.User
import org.aicodinglab.backendhanbi.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService(val userRepository: UserRepository) {

    fun findByEmail(email: String): User? {
        return userRepository.findByEmail(email)
    }

    @Transactional
    fun save(user: User): User {
        return userRepository.save(user)
    }

    fun findAll(): List<User> {
        return userRepository.findAll()
    }
}