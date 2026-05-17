package com.example.complain.managment.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.complain.managment.entity.Notification;
import com.example.complain.managment.repository.NotificationRepository;

@Service
public class NotificationService {

    private final NotificationRepository repo;

    public NotificationService(
            NotificationRepository repo){

        this.repo=repo;
    }

    public void save(
            String message){

        Notification n=
        new Notification();

        n.setMessage(
                message);

        n.setTime(
                LocalDateTime.now());

        repo.save(n);

    }

    public List<Notification>
    getAll(){

        return repo.findAll();

    }

}