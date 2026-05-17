package com.example.complain.managment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.complain.managment.entity.Notification;

public interface NotificationRepository
extends JpaRepository<Notification,Long>{

}