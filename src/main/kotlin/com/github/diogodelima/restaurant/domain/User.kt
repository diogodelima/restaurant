package com.github.diogodelima.restaurant.domain

import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity
@Table(name = "\"user\"")
data class User(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(unique = true, nullable = false)
    val email: String,

    @Column(unique = true, nullable = false)
    @get:JvmName("username")
    val username: String,

    @Column(nullable = false)
    @get:JvmName("password")
    val password: String,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    val role: Role = Role.CUSTOMER

): UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf(SimpleGrantedAuthority("ROLE_" + role.name))
    }

    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return username
    }

}