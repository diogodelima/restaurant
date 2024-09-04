package com.github.diogodelima.restaurant.repository

import com.github.diogodelima.restaurant.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<User, Long> {

    fun findByUsername(username: String?): User?

    fun findByEmail(email: String?): User?

}