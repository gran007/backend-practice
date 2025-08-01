package org.aicodinglab.kotlinspring.repository

import org.aicodinglab.kotlinspring.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface UserSpecificationRepository : JpaRepository<User, Long>, JpaSpecificationExecutor<User>