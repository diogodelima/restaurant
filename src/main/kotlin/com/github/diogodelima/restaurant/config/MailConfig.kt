package com.github.diogodelima.restaurant.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.JavaMailSenderImpl


@Configuration
class MailConfig(

    @Value("\${mail.host}")
    private val host: String,

    @Value("\${mail.port}")
    private val port: Int,

    @Value("\${mail.username}")
    private val username: String,

    @Value("\${mail.password}")
    private val password: String,

    @Value("\${mail.protocol}")
    private val protocol: String,

    @Value("\${mail.auth}")
    private val auth: Boolean,

    @Value("\${mail.starttls}")
    private val starttls: Boolean,

    @Value("\${mail.debug}")
    private val debug: Boolean

) {

    @Bean
    fun javaMailSender(): JavaMailSender {

        val javaMailSender = JavaMailSenderImpl()
        javaMailSender.host = host
        javaMailSender.port = port
        javaMailSender.username = username
        javaMailSender.password = password

        val props = javaMailSender.javaMailProperties
        props["mail.transport.protocol"] = protocol
        props["mail.smtp.auth"] = auth
        props["mail.smtp.starttls.enable"] = starttls
        props["mail.debug"] = debug

        return javaMailSender
    }

}