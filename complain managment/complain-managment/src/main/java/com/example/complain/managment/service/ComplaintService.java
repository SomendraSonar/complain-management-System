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

        this.repo = repo;
    }

    // Save complaint
    public Complaint saveComplaint(
            Complaint complaint){

        return repo.save(
                complaint);

    }

    // Get all complaints
    public List<Complaint> getAllComplaints(){

        return repo.findAll();

    }

    // Update complaint status
    public Complaint updateStatus(
            Long id,
            String status){

        Complaint c =
        repo.findById(id)
        .orElseThrow();

        c.setStatus(status);

        return repo.save(c);

    }

    // Get complaints of specific user
    public List<Complaint>
    getUserComplaints(
            Long userId){

        return repo
        .findByUserId(
                userId);

    }

}