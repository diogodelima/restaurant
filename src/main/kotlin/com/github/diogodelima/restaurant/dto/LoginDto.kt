package com.github.diogodelima.restaurant.dto

import jakarta.validation.constraints.NotBlank

data class LoginDto(

    @field:NotBlank
    val username: String,

    @field:NotBlank
    val password: String

)