package com.github.diogodelima.restaurant.repository

import com.github.diogodelima.restaurant.domain.RecoverPassword
import com.github.diogodelima.restaurant.domain.User
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface RecoverPasswordRepository: JpaRepository<RecoverPassword, UUID> {

    fun findByUser(user: User): RecoverPassword?

    @Modifying
    @Transactional
    @Query("DELETE FROM RecoverPassword rp WHERE rp.generatedAt < :expirationDate")
    fun deleteAllExpired(@Param("expirationDate") expirationDate: Long)

}