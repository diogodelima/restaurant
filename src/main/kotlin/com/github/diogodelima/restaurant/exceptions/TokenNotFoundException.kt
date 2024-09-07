package com.github.diogodelima.restaurant.exceptions

class TokenNotFoundException(

    override val message: String? = "Token not found"

): RuntimeException(message)