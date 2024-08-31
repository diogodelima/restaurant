package com.github.diogodelima.restaurant.exceptions

import com.github.diogodelima.restaurant.domain.Role

class RoleException(

    role: Role,
    override val message: String? = "You can't create a user with ${role.name} role"

): RuntimeException(message)