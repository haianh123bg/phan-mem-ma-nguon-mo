package com.haianh123.library.service.impl;

import com.haianh123.library.service.MyEmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MyEmailServiceImpl implements MyEmailService {
    private final JavaMailSender javaMailSender;

    @Override
    public void sendSimpleEmail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        javaMailSender.send(message);
    }

    @Override
    public void sendHtmlEmail(String to, String subject, String content) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message, true);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        try {
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        javaMailSender.send(message);
    }

    @Override
    public String generateVerificationEmailContent(String verificationCode) {
        StringBuilder builder = new StringBuilder();
        builder.append("<!DOCTYPE html>")
                .append("<html>")
                .append("<head>")
                .append("<style>")
                .append("body {font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #f4f4f4;}")
                .append(".container {max-width: 600px; margin: 20px auto; background-color: #ffffff; padding: 20px; border-radius: 8px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);}")
                .append(".header {background-color: #007bff; color: white; padding: 10px 20px; text-align: center; border-top-left-radius: 8px; border-top-right-radius: 8px;}")
                .append(".content {margin: 20px; text-align: center;}")
                .append(".content p {font-size: 16px; line-height: 1.5;}")
                .append(".verification-code {font-size: 24px; font-weight: bold; color: #007bff; margin: 20px 0;}")
                .append(".footer {background-color: #f1f1f1; text-align: center; padding: 10px; border-bottom-left-radius: 8px; border-bottom-right-radius: 8px;}")
                .append("</style>")
                .append("</head>")
                .append("<body>")
                .append("<div class='container'>")
                .append("<div class='header'><h1>Email Verification</h1></div>")
                .append("<div class='content'>")
                .append("<p>Dear User,</p>")
                .append("<p>Thank you for registering with our service. Please use the following verification code to complete your registration:</p>")
                .append("<div class='verification-code'>").append(verificationCode).append("</div>")
                .append("<p>If you did not request this verification, please ignore this email.</p>")
                .append("</div>")
                .append("<div class='footer'><p>Contact us: support@example.com</p></div>")
                .append("</div>")
                .append("</body>")
                .append("</html>");
        return builder.toString();
    }

}
