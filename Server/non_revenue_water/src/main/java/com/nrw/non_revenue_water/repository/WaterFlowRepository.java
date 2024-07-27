package com.nrw.non_revenue_water.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nrw.non_revenue_water.model.WaterFlow;

public interface WaterFlowRepository extends JpaRepository<WaterFlow, String> {
    List<WaterFlow> findByFlowTypeAndAccountAccountNumber(String flowType, long accountNumber);
}
