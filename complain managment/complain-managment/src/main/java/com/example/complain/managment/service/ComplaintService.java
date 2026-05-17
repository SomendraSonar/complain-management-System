package com.example.complain.managment.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.complain.managment.entity.Complaint;
import com.example.complain.managment.repository.ComplaintRepository;

@Service
public class ComplaintService {

    private final ComplaintRepository repo;
    private final EmailService emailService;

    public ComplaintService(
            ComplaintRepository repo,
            EmailService emailService){

        this.repo=repo;
        this.emailService=emailService;

    }

    public Complaint saveComplaint(
            Complaint complaint){

        return repo.save(
                complaint);

    }

    public List<Complaint>
    getAllComplaints(){

        return repo.findAll();

    }

    public List<Complaint>
    getUserComplaints(
            Long userId){

        return repo
        .findByUserId(
                userId);

    }

    public Complaint updateStatus(
            Long id,
            String status){

        Complaint c=

        repo.findById(id)
        .orElseThrow();

        c.setStatus(
                status);

        Complaint saved=
        repo.save(c);

        if(saved.getUser()!=null){

            emailService.sendEmail(

            saved.getUser()
            .getEmail(),

            "Complaint Updated",

            "Complaint: "

            + saved.getTitle()

            +

            "\nStatus: "

            + status

            );

        }

        return saved;

    }

    // admin response

    public Complaint addResponse(

            Long id,

            String response){

        Complaint c=

        repo.findById(id)
        .orElseThrow();

        c.setAdminResponse(
                response);

        return repo.save(c);

    }

}