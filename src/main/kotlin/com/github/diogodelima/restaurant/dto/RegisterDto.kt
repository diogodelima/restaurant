package com.github.diogodelima.restaurant.dto

import com.github.diogodelima.restaurant.domain.Role
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

data class RegisterDto(

    @field:NotEmpty
    val username: String,

    @field:NotEmpty
    val email: String,

    @field:NotEmpty
    val password: String,

    @field:NotNull
    val role: Role

)