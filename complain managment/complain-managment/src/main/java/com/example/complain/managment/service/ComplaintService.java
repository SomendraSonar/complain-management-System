package com.example.complain.managment.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.complain.managment.entity.Complaint;
import com.example.complain.managment.repository.ComplaintRepository;

@Service
public class ComplaintService {

    private final ComplaintRepository repo;

    public ComplaintService(
            ComplaintRepository repo){

        this.repo=repo;
    }

    public Complaint saveComplaint(
            Complaint complaint){

        return repo.save(
                complaint);

    }

    public List<Complaint> getAllComplaints(){

        return repo.findAll();

    }

    public Complaint updateStatus(
            Long id,
            String status){

        Complaint c=
        repo.findById(id)
        .orElseThrow();

        c.setStatus(status);

        return repo.save(c);

    }

}