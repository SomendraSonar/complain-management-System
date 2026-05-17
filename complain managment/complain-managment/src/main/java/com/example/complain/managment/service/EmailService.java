package com.example.complain.managment.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

private final JavaMailSender sender;

public EmailService(
JavaMailSender sender){

this.sender=sender;

}

public void sendEmail(
String to,
String subject,
String body){

SimpleMailMessage msg=
new SimpleMailMessage();

msg.setTo(to);

msg.setSubject(
subject);

msg.setText(
body);

sender.send(msg);

}

}