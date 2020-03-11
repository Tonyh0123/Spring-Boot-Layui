package com.tangtang.manager.service.impl;

import com.tangtang.manager.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String myMailSender;

    @Override
    public void sendSimpleMail(String receiver, String subject, String content) throws MailException {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(myMailSender);
        mailMessage.setTo(receiver);
        mailMessage.setSubject(subject);
        mailMessage.setText(content);

        mailSender.send(mailMessage);
    }

    @Override
    public void sendVerifyCodeMail(String receiver, String subject, String content) throws MailException {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(myMailSender);
        mailMessage.setTo(receiver);
        mailMessage.setSubject(subject);
        mailMessage.setText(content);

        mailSender.send(mailMessage);
    }
}
