package com.example.complain.managment.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.scheduling.annotation.Async;

import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender sender;

    public EmailService(
            JavaMailSender sender){

        this.sender = sender;

    }


    @Async
    public void sendEmail(

            String to,

            String subject,

            String body){

        try{

            SimpleMailMessage message=

                    new SimpleMailMessage();

            message.setTo(
                    to);

            message.setSubject(
                    subject);

            message.setText(
                    body);

            sender.send(
                    message);


            System.out.println(

                    "Email sent to: "

                    +

                    to

            );

        }

        catch(Exception e){

            System.out.println(

                    "Email Error: "

                    +

                    e.getMessage()

            );

        }

    }

}   