package com.github.diogodelima.restaurant.services

import com.github.diogodelima.restaurant.domain.RecoverPassword
import com.github.diogodelima.restaurant.domain.User
import com.github.diogodelima.restaurant.repository.RecoverPasswordRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class RecoverPasswordService(

    private val recoverPasswordRepository: RecoverPasswordRepository

) {

    fun getByUser(user: User) = recoverPasswordRepository.findByUser(user)

    fun getById(token: String): RecoverPassword? = recoverPasswordRepository.findById(UUID.fromString(token)).orElse(null)

    fun deleteAllExpired(timeToExpire: Long) {
        val expirationDate = System.currentTimeMillis() - timeToExpire
        recoverPasswordRepository.deleteAllExpired(expirationDate)
    }

    fun save(recoverPassword: RecoverPassword) = recoverPasswordRepository.save(recoverPassword)

    fun delete(token: String) = recoverPasswordRepository.deleteById(UUID.fromString(token))

}