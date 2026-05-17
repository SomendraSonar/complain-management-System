package com.example.complain.managment.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.complain.managment.entity.Notification;
import com.example.complain.managment.service.NotificationService;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin("*")

public class NotificationController {

    private final NotificationService service;

    public NotificationController(
            NotificationService service){

        this.service=service;
    }

    @GetMapping
    public List<Notification>
    getAll(){

        return service.getAll();

    }

}