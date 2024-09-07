package com.github.diogodelima.restaurant.exceptions

class TokenExpiredException(

    override val message: String? = "Token expired"

): RuntimeException(message)