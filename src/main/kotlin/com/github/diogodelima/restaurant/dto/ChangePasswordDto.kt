package com.github.diogodelima.restaurant.dto

import jakarta.validation.constraints.NotBlank

data class ChangePasswordDto(

    @field:NotBlank
    val password: String

)