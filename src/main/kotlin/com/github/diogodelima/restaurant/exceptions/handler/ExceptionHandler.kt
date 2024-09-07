package com.github.diogodelima.restaurant.exceptions.handler

import com.github.diogodelima.restaurant.exceptions.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(UserNotFoundException::class, TokenNotFoundException::class)
    fun handleNotFound(exception: Exception): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.message)
    }

    @ExceptionHandler(
        UsernameAlreadyExistsException::class, EmailAlreadyExistsException::class, RoleException::class,
        TokenExpiredException::class
    )
    fun handleConflict(exception: Exception): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.message)
    }

}