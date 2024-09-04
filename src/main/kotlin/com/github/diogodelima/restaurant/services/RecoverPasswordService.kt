package com.github.diogodelima.restaurant.services

import com.github.diogodelima.restaurant.domain.RecoverPassword
import com.github.diogodelima.restaurant.domain.User
import com.github.diogodelima.restaurant.repository.RecoverPasswordRepository
import org.springframework.stereotype.Service

@Service
class RecoverPasswordService(

    private val recoverPasswordRepository: RecoverPasswordRepository

) {

    fun getByUser(user: User): RecoverPassword? = recoverPasswordRepository.findByUser(user)

    fun save(recoverPassword: RecoverPassword): RecoverPassword = recoverPasswordRepository.save(recoverPassword)

}