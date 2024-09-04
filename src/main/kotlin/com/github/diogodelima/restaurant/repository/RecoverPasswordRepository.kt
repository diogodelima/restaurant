package com.github.diogodelima.restaurant.repository

import com.github.diogodelima.restaurant.domain.RecoverPassword
import com.github.diogodelima.restaurant.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RecoverPasswordRepository: JpaRepository<RecoverPassword, String> {

    fun findByUser(user: User): RecoverPassword?

}