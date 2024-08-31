package com.github.diogodelima.restaurant.controller

import com.github.diogodelima.restaurant.domain.Role
import com.github.diogodelima.restaurant.domain.User
import com.github.diogodelima.restaurant.dto.LoginDto
import com.github.diogodelima.restaurant.dto.RegisterDto
import com.github.diogodelima.restaurant.exceptions.EmailAlreadyExistsException
import com.github.diogodelima.restaurant.exceptions.RoleException
import com.github.diogodelima.restaurant.exceptions.UserNotFoundException
import com.github.diogodelima.restaurant.exceptions.UsernameAlreadyExistsException
import com.github.diogodelima.restaurant.services.TokenService
import com.github.diogodelima.restaurant.services.UserService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(

    private val authenticationManager: AuthenticationManager,
    private val tokenService: TokenService,
    private val userService: UserService,
    private val passwordEncoder: PasswordEncoder

) {

    @PostMapping("/register")
    fun register(@RequestBody @Valid dto: RegisterDto): ResponseEntity<String> {

        if (userService.getByEmail(dto.email) != null)
            throw EmailAlreadyExistsException()

        if (userService.loadUserByUsername(dto.username) != null)
            throw UsernameAlreadyExistsException()

        if (dto.role == Role.ADMIN)
            throw RoleException(dto.role)

        userService.save(
            User(
                email = dto.email,
                username = dto.username,
                password = passwordEncoder.encode(dto.password),
                role = dto.role
            )
        )

        return ResponseEntity.ok("User created successfully")
    }

    @PostMapping("/login")
    fun login(@RequestBody @Valid dto: LoginDto): ResponseEntity<String> {

        if (userService.loadUserByUsername(dto.username) == null)
            throw UserNotFoundException()

        val authentication = UsernamePasswordAuthenticationToken(dto.username, dto.password)
        val auth = authenticationManager.authenticate(authentication)
        val user = auth.principal as User
        val token = tokenService.generateToken(user)

        return ResponseEntity.ok(token)
    }

}