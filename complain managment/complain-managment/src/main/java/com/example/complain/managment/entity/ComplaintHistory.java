package com.example.complain.managment.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ComplaintHistory {

    @Id
    @GeneratedValue(
            strategy=
            GenerationType.IDENTITY
    )

    private Long id;


    private String action;


    private LocalDateTime time;


    @ManyToOne

    @JoinColumn(
            name=
            "complaint_id"
    )

    private Complaint complaint;

}