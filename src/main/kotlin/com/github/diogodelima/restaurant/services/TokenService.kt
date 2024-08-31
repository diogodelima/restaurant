package com.github.diogodelima.restaurant.services

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.github.diogodelima.restaurant.domain.User
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class TokenService(

    @Value("\${security.token.secret}")
    private val secret: String

) {

    fun generateToken(user: User): String {

        val algorithm = Algorithm.HMAC256(secret)

        return JWT.create()
            .withIssuer("restaurant")
            .withSubject(user.username)
            .withExpiresAt(expiresAt())
            .sign(algorithm)
    }

    fun validateToken(token: String): String {

        val algorithm = Algorithm.HMAC256(secret)

        return JWT.require(algorithm)
            .withIssuer("restaurant")
            .build()
            .verify(token)
            .subject
    }

    fun expiresAt(): Instant {
        return Instant.now().plusSeconds(24 * 3600L)
    }

}