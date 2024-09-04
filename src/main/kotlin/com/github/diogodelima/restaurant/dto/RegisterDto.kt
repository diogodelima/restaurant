package com.github.diogodelima.restaurant.dto

import jakarta.validation.constraints.NotBlank

data class RegisterDto(

    @field:NotBlank
    val username: String,

    @field:NotBlank
    val email: String,

    @field:NotBlank
    val password: String

)