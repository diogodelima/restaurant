package com.github.diogodelima.restaurant.dto

import jakarta.validation.constraints.NotEmpty

data class LoginDto(

    @field:NotEmpty
    val username: String,

    @field:NotEmpty
    val password: String

)