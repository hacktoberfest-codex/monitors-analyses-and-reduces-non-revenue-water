package com.nrw.non_revenue_water.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nrw.non_revenue_water.model.RevenueLocation;

public interface RevenueLocationRepository extends JpaRepository<RevenueLocation, String> {
    List<RevenueLocation> findByAccountAccountNumber(long accountNumber);
}
