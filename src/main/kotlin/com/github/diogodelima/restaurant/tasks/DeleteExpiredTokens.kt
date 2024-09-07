package com.github.diogodelima.restaurant.tasks

import com.github.diogodelima.restaurant.constants.TOKEN_EXPIRATION
import com.github.diogodelima.restaurant.services.RecoverPasswordService
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class DeleteExpiredTokens(

    private val recoverPasswordService: RecoverPasswordService

) {

    @Scheduled(fixedRate = 60 * 60 * 1000L)
    fun deleteExpiredTokens() {
        recoverPasswordService.deleteAllExpired(TOKEN_EXPIRATION)
    }

}