package com.github.diogodelima.restaurant.services

import com.github.diogodelima.restaurant.domain.User
import com.github.diogodelima.restaurant.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserService(

    private val userRepository: UserRepository

): UserDetailsService {

    override fun loadUserByUsername(username: String?): User? {
        return userRepository.findByUsername(username)
    }

    fun getByEmail(email: String?) = userRepository.findByEmail(email)

    fun save(user: User) = userRepository.save(user)

}