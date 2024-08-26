package com.nrw.non_revenue_water.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nrw.non_revenue_water.model.Account;
import com.nrw.non_revenue_water.model.Complaint;

public interface ComplaintRepository extends JpaRepository<Complaint, Integer> {
    List<Complaint> findAll();

    Optional<Complaint> findByAccountId(int accountId);

    List<Complaint> findByAccount(Account account);
    
}
