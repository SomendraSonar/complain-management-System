package com.example.complain.managment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync

public class ComplainManagmentApplication {

    public static void main(
            String[] args){

        SpringApplication.run(

                ComplainManagmentApplication.class,

                args

        );

    }

}