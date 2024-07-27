package com.nrw.non_revenue_water.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nrw.non_revenue_water.model.Revenue;

public interface RevenueRepository extends JpaRepository<Revenue, String> {
    List<Revenue> findByAccountAccountNumber(long accountNumber);
}
