package com.example.complain.managment.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.complain.managment.entity.Complaint;
import com.example.complain.managment.entity.ComplaintHistory;
import com.example.complain.managment.repository.ComplaintHistoryRepository;
import com.example.complain.managment.repository.ComplaintRepository;

@Service
public class ComplaintService {

    private final ComplaintRepository repo;
    private final EmailService emailService;
    private final ComplaintHistoryRepository historyRepo;
    private final NotificationService notificationService;

    public ComplaintService(

            ComplaintRepository repo,

            EmailService emailService,

            ComplaintHistoryRepository historyRepo,

            NotificationService notificationService){

        this.repo = repo;
        this.emailService = emailService;
        this.historyRepo = historyRepo;
        this.notificationService =
                notificationService;
    }


    public Complaint saveComplaint(
            Complaint complaint){

        Complaint saved =
                repo.save(
                        complaint);

        ComplaintHistory h=
                new ComplaintHistory();

        h.setAction(
                "Complaint Created");

        h.setTime(
                LocalDateTime.now());

        h.setComplaint(
                saved);

        historyRepo.save(h);

        notificationService.save(

                "New complaint: "

                +

                saved.getTitle()

        );

        return saved;

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


    public Complaint
    getComplaintById(
            Long id){

        return repo
                .findById(id)
                .orElseThrow();

    }


    public Complaint
    updateStatus(

            Long id,

            String status){

        Complaint c=

                repo.findById(id)
                .orElseThrow();

        c.setStatus(
                status);

        Complaint saved=
                repo.save(c);


        ComplaintHistory h=
                new ComplaintHistory();

        h.setAction(
                "Status changed to "
                +status);

        h.setTime(
                LocalDateTime.now());

        h.setComplaint(
                saved);

        historyRepo.save(h);


        notificationService.save(

                "Status updated: "

                +

                saved.getTitle()

        );


        if(
                saved.getUser()!=null
        ){

            emailService.sendEmail(

                    saved
                    .getUser()
                    .getEmail(),

                    "Complaint Status Updated",

                    "Complaint: "

                    +

                    saved.getTitle()

                    +

                    "\nStatus changed to: "

                    +

                    status

            );

        }

        return saved;

    }


    public Complaint
    addResponse(

            Long id,

            String response){

        Complaint c=

                repo.findById(id)
                .orElseThrow();

        c.setAdminResponse(
                response);

        Complaint saved=
                repo.save(c);


        ComplaintHistory h=
                new ComplaintHistory();

        h.setAction(
                "Admin responded");

        h.setTime(
                LocalDateTime.now());

        h.setComplaint(
                saved);

        historyRepo.save(h);


        notificationService.save(

                "Admin responded: "

                +

                saved.getTitle()

        );

        return saved;

    }

}