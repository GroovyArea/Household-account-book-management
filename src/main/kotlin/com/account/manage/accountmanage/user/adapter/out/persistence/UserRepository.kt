package com.account.manage.accountmanage.user.adapter.out.persistence

import com.account.manage.accountmanage.user.domain.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {

    fun findUserById(id: Long): User?

    fun findUserByEmail(email: String): User?

    fun existsByEmail(email: String) : Boolean
}