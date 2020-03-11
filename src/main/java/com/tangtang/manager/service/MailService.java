package com.tangtang.manager.service;

import org.springframework.mail.MailException;

public interface MailService {
    void sendSimpleMail(String receiver, String subject, String content) throws MailException;

    void sendVerifyCodeMail(String receiver, String subject, String content) throws MailException;
}
