package org.aicodinglab.kotlinspring.common.specification

import org.aicodinglab.kotlinspring.entity.User
import org.springframework.data.jpa.domain.Specification

class UserSpecification {
    companion object {
        fun userNameEquals(userName: String): Specification<User> {
            return Specification { root, query, cb ->
                cb.equal(root.get<String>("userName"), userName)
            }
        }

        fun userIdGreaterThanOrEqual(userId: Long): Specification<User> {
            return Specification { root, query, cb ->
                cb.ge(root.get<Int>("userId"), userId)
            }
        }

        fun nameAndUserId(userName: String, userId: Long): Specification<User> {
            return userNameEquals(userName).and(userIdGreaterThanOrEqual(userId))
        }
    }
}