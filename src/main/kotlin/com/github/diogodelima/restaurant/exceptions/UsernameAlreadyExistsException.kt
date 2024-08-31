package com.github.diogodelima.restaurant.exceptions

class UsernameAlreadyExistsException(

    override val message: String? = "Username already exists"

): RuntimeException(message)