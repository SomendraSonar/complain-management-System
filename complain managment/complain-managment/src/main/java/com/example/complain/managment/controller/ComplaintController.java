package com.example.complain.managment.controller;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.*;
// import org.springframework.web.multipart.MultipartFile;
import com.example.complain.managment.entity.User;

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

   @PostMapping("/upload")
public Complaint create(

@RequestParam("title")
String title,

@RequestParam("description")
String description,

@RequestParam("file")
MultipartFile file,

@RequestParam("userId")
Long userId

)throws Exception{

String fileName=

System.currentTimeMillis()

+"_"

+

file.getOriginalFilename();

Path path=

Paths.get(
"uploads",
fileName
);

Files.write(
path,
file.getBytes()
);

Complaint complaint=
new Complaint();

complaint.setTitle(
title);

complaint.setDescription(
description);

complaint.setCategory(
"General");

complaint.setStatus(
"Pending");

complaint.setImageName(
fileName);

complaint.setCreatedAt(
LocalDateTime.now()
);

User user=
new User();

user.setId(
userId);

complaint.setUser(
user);

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