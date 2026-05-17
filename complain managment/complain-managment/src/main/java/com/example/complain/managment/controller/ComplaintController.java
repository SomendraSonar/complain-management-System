package com.example.complain.managment.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.complain.managment.entity.Complaint;
import com.example.complain.managment.entity.ComplaintHistory;
import com.example.complain.managment.entity.User;
import com.example.complain.managment.repository.ComplaintHistoryRepository;
import com.example.complain.managment.service.ComplaintService;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/complaints")
@CrossOrigin("*")

public class ComplaintController {

    private final ComplaintService service;

    private final ComplaintHistoryRepository historyRepo;


    public ComplaintController(

            ComplaintService service,

            ComplaintHistoryRepository historyRepo){

        this.service = service;

        this.historyRepo = historyRepo;

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


    // Upload complaint with image

    @PostMapping("/upload")

    public Complaint createWithFile(

            @RequestParam("title")
            String title,

            @RequestParam("description")
            String description,

            @RequestParam(
                    value="file",
                    required=false)

            MultipartFile file,

            @RequestParam("userId")
            Long userId)

            throws Exception{


        Complaint complaint =
                new Complaint();

        complaint.setTitle(
                title);

        complaint.setDescription(
                description);

        complaint.setCategory(
                "General");

        complaint.setStatus(
                "Pending");

        complaint.setCreatedAt(
                LocalDateTime.now());


        if(file!=null
        &&
        !file.isEmpty()){


            String fileName=

            System.currentTimeMillis()

            +"_"

            +

            file
            .getOriginalFilename();


            Path path=

            Paths.get(
                    "uploads",
                    fileName);


            Files.write(

                    path,

                    file.getBytes()

            );


            complaint
            .setImageName(
                    fileName);

        }


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


    // Get all

    @GetMapping
    public List<Complaint>
    getAll(){

        return service
                .getAllComplaints();

    }


    // User complaints

    @GetMapping("/user/{id}")

    public List<Complaint>
    getUserComplaints(

            @PathVariable
            Long id){

        return service
                .getUserComplaints(
                        id);

    }


    // Update status

    @PutMapping("/{id}")

    public Complaint
    updateStatus(

            @PathVariable
            Long id,

            @RequestParam
            String status){

        return service
                .updateStatus(
                        id,
                        status);

    }


    // Admin response

    @PutMapping("/{id}/response")

    public Complaint
    addResponse(

            @PathVariable
            Long id,

            @RequestParam
            String response){

        return service
                .addResponse(
                        id,
                        response);

    }


    // Timeline history

    @GetMapping("/{id}/history")

    public List<ComplaintHistory>
    history(

            @PathVariable
            Long id){

        return historyRepo
                .findByComplaintId(
                        id);

    }


    // Single PDF

    @GetMapping("/{id}/pdf")

    public void exportSinglePdf(

            @PathVariable
            Long id,

            HttpServletResponse response)

            throws Exception{


        response.setContentType(
                "application/pdf");

        response.setHeader(

                "Content-Disposition",

                "attachment; filename=complaint_"
                +id+
                ".pdf"

        );


        Complaint c=
                service
                .getComplaintById(
                        id);


        Document document=
                new Document();


        PdfWriter.getInstance(

                document,

                response
                .getOutputStream()

        );


        document.open();


        document.add(
                new Paragraph(
                        "Complaint Details"));

        document.add(
                new Paragraph(" "));


        document.add(

                new Paragraph(

                        "Title: "

                        +

                        c.getTitle()

                )

        );


        document.add(

                new Paragraph(

                        "Description: "

                        +

                        c.getDescription()

                )

        );


        document.add(

                new Paragraph(

                        "Status: "

                        +

                        c.getStatus()

                )

        );


        document.add(

                new Paragraph(

                        "Admin Response: "

                        +

                        (

                        c.getAdminResponse()
                        !=null

                        ?

                        c.getAdminResponse()

                        :

                        "No response"

                        )

                )

        );


        document.add(

                new Paragraph(

                        "User: "

                        +

                        (

                        c.getUser()!=null

                        ?

                        c.getUser()
                        .getName()

                        :

                        "Anonymous"

                        )

                )

        );


        if(
                c.getImageName()
                !=null
        ){

            String imagePath=

                    "uploads/"

                    +

                    c.getImageName();


            Image image=

                    Image.getInstance(
                            imagePath);


            image.scaleToFit(
                    300,
                    300);


            document.add(
                    new Paragraph(" "));

            document.add(
                    new Paragraph(
                            "Image:"
                    ));


            document.add(
                    image);

        }


        document.close();

    }

}