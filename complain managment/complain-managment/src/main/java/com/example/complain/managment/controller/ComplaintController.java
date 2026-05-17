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

        this.service=service;

    }

    @PostMapping
    public Complaint create(
            @RequestBody Complaint complaint){

        complaint.setCreatedAt(
                LocalDateTime.now());

        return service
                .saveComplaint(
                        complaint);

    }

    @GetMapping
    public List<Complaint>
    getAll(){

        return service
                .getAllComplaints();

    }

    @GetMapping("/user/{id}")
    public List<Complaint>
    getUserComplaints(

            @PathVariable
            Long id){

        return service
                .getUserComplaints(
                        id);

    }

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

    // admin response

    @PutMapping("/{id}/response")

    public Complaint addResponse(

            @PathVariable
            Long id,

            @RequestParam
            String response){

        return service
                .addResponse(
                        id,
                        response);

    }

}