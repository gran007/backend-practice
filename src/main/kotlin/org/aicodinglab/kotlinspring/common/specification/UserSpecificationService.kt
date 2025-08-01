package org.aicodinglab.kotlinspring.common.specification

import org.aicodinglab.kotlinspring.entity.User
import org.aicodinglab.kotlinspring.repository.UserSpecificationRepository
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service

@Service
class UserSpecificationService(val userRepository: UserSpecificationRepository) {

    fun findUsers(name: String?, userId: Long?): List<User> {
        var specification: Specification<User>? = null

        if (name != null) {
            specification = UserSpecification.userNameEquals(name)
        }
        if (userId != null) {
            specification = specification?.and(UserSpecification.userIdGreaterThanOrEqual(userId)) ?: UserSpecification.userIdGreaterThanOrEqual(userId)
        }
        return userRepository.findAll(specification)
    }
}