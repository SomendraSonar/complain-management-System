package com.example.complain.managment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.complain.managment.entity.ComplaintHistory;

public interface ComplaintHistoryRepository
extends JpaRepository<ComplaintHistory,Long>{

    List<ComplaintHistory>
    findByComplaintId(
            Long id
    );

}