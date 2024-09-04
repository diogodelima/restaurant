package com.github.diogodelima.restaurant.exceptions

class UserNotFoundException(

    override val message: String? = "User not found"

): RuntimeException(message)