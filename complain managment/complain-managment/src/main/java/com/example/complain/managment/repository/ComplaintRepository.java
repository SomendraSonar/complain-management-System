package com.example.complain.managment.repository;

import com.example.complain.managment.entity.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplaintRepository
        extends JpaRepository<Complaint, Long> {

}