package com.github.diogodelima.restaurant.domain

import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "recover_password")
data class RecoverPassword(

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, unique = true)
    val user: User,

    @Column(nullable = false)
    val generatedAt: Long = System.currentTimeMillis()

)