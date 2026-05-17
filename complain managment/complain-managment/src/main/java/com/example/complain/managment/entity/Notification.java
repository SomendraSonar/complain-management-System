package com.example.complain.managment.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Notification {

    @Id
    @GeneratedValue(
    strategy=
    GenerationType.IDENTITY)

    private Long id;

    private String message;

    private LocalDateTime time;

}