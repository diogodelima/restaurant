package com.github.diogodelima.restaurant.filter

import com.github.diogodelima.restaurant.exceptions.UserNotFoundException
import com.github.diogodelima.restaurant.services.TokenService
import com.github.diogodelima.restaurant.services.UserService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class SecurityFilter(

    private val tokenService: TokenService,
    private val userService: UserService

): OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {

        val token = request.getHeader("Authorization")?.replace("Bearer ", "")

        if (token != null) {

            val subject = tokenService.validateToken(token)
            val user = userService.loadUserByUsername(subject) ?: throw UserNotFoundException()
            val authentication = UsernamePasswordAuthenticationToken(user, null, user.authorities)

            SecurityContextHolder.getContext().authentication = authentication
        }

        filterChain.doFilter(request, response)
    }

}