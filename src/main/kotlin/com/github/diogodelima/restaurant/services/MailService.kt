package com.github.diogodelima.restaurant.services

import jakarta.mail.internet.InternetAddress
import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service

@Service
class MailService(

    @Value("\${mail.username}")
    private val username: String,

    private val mailSender: JavaMailSender

) {

    fun sendEmail(email: String, subject: String, content: String) {

        val message = mailSender.createMimeMessage()
        val helper = MimeMessageHelper(message, true)

        helper.setFrom(InternetAddress(username, "restaurant"))
        helper.setTo(email)
        helper.setSubject(subject)
        helper.setText(content, true)

        mailSender.send(message)
    }

}