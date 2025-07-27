package org.aicodinglab.backendhanbi.service

import jakarta.transaction.Transactional
import org.aicodinglab.backendhanbi.entity.User
import org.aicodinglab.backendhanbi.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class UserService(val userRepository: UserRepository) {

    fun findByEmail(email: String): User? {
        return userRepository.findByEmail(email)
    }

    fun findAll(): List<User> {
        return userRepository.findAll()
    }

    @Transactional
    fun save(user: User): User {
        return userRepository.save(user)
    }


    @Transactional
    fun deleteByUserId(userId: Long) {
        return userRepository.deleteById(userId)
    }

    @Transactional
    fun update(user: User): User? {
        userRepository.findByIdOrNull(user.userId)?.let {
            return userRepository.save(user)
        }
        throw RuntimeException("User with id ${user.userId} not found")
    }
}
