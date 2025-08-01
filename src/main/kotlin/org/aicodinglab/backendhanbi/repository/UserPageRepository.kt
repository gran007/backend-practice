package org.aicodinglab.backendhanbi.repository

import org.aicodinglab.backendhanbi.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface UserPageRepository : PagingAndSortingRepository<User, Long>, JpaSpecificationExecutor<User> {

    fun findByEmail(email: String): User?
}