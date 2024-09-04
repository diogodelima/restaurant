package com.github.diogodelima.restaurant.exceptions

class EmailAlreadyExistsException(

    override val message: String? = "Email already exists"

): RuntimeException(message)