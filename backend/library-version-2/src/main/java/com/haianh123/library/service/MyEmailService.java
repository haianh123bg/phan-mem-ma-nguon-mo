package com.haianh123.library.service;

public interface MyEmailService {
    void sendSimpleEmail(String to, String subject, String content);
    void sendHtmlEmail(String to, String subject, String content);
    String generateVerificationEmailContent(String verificationCode);
}
