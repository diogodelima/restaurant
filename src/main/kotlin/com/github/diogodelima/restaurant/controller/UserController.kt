package com.github.diogodelima.restaurant.controller

import com.github.diogodelima.restaurant.domain.User
import com.github.diogodelima.restaurant.dto.ChangePasswordDto
import com.github.diogodelima.restaurant.dto.ForgotPasswordDto
import com.github.diogodelima.restaurant.dto.LoginDto
import com.github.diogodelima.restaurant.dto.RegisterDto
import com.github.diogodelima.restaurant.exceptions.EmailAlreadyExistsException
import com.github.diogodelima.restaurant.exceptions.UserNotFoundException
import com.github.diogodelima.restaurant.exceptions.UsernameAlreadyExistsException
import com.github.diogodelima.restaurant.services.MailService
import com.github.diogodelima.restaurant.services.TokenService
import com.github.diogodelima.restaurant.services.UserService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(

    private val authenticationManager: AuthenticationManager,
    private val tokenService: TokenService,
    private val userService: UserService,
    private val mailService: MailService,
    private val passwordEncoder: PasswordEncoder

) {

    @PostMapping("/register")
    fun register(@RequestBody @Valid dto: RegisterDto): ResponseEntity<String> {

        if (userService.getByEmail(dto.email) != null)
            throw EmailAlreadyExistsException()

        if (userService.loadUserByUsername(dto.username) != null)
            throw UsernameAlreadyExistsException()

        userService.save(
            User(
                email = dto.email,
                username = dto.username,
                password = passwordEncoder.encode(dto.password)
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

    @PutMapping("/changepassword")
    fun changePassword(@RequestBody @Valid dto: ChangePasswordDto): ResponseEntity<String> {

        val user = SecurityContextHolder.getContext().authentication.principal as User
        user.password = passwordEncoder.encode(dto.password)
        userService.save(user)

        return ResponseEntity.ok("Password changed successfully")
    }

    @PostMapping("/forgotpassword")
    fun forgotPassword(@RequestBody dto: ForgotPasswordDto): ResponseEntity<String> {

        val user = userService.loadUserByUsername(dto.username) ?: userService.getByEmail(dto.email) ?: throw UserNotFoundException()

        //send email to user
        mailService.sendEmail(user.email, "Recover password", "Test email")
        return ResponseEntity.ok("Check your email box")
    }

}