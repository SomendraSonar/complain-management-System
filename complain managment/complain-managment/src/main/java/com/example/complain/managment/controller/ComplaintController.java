package com.example.complain.managment.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.complain.managment.entity.Complaint;
import com.example.complain.managment.service.ComplaintService;

@RestController
@RequestMapping("/api/complaints")

public class ComplaintController {

    private final ComplaintService service;

    public ComplaintController(
            ComplaintService service){

        this.service = service;
    }

    // Create complaint
    @PostMapping
    public Complaint create(
            @RequestBody Complaint complaint){

        complaint.setCreatedAt(
                LocalDateTime.now());

        return service
                .saveComplaint(
                        complaint);

    }

    // Get all complaints
    @GetMapping
    public List<Complaint> getAll(){

        return service
                .getAllComplaints();

    }

    // Update status
    @PutMapping("/{id}")
    public Complaint updateStatus(

            @PathVariable
            Long id,

            @RequestParam
            String status){

        return service
                .updateStatus(
                        id,
                        status);

    }

    // Get complaints of specific user
    @GetMapping("/user/{id}")
    public List<Complaint>
    getUserComplaints(

            @PathVariable
            Long id){

        return service
                .getUserComplaints(
                        id);

    }

}