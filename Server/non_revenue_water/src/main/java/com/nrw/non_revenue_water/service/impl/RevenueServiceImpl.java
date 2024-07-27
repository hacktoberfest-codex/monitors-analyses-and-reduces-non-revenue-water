package com.nrw.non_revenue_water.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nrw.non_revenue_water.model.Revenue;
import com.nrw.non_revenue_water.model.RevenueLocation;
import com.nrw.non_revenue_water.repository.RevenueLocationRepository;
import com.nrw.non_revenue_water.repository.RevenueRepository;
import com.nrw.non_revenue_water.service.IRevenueService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RevenueServiceImpl implements IRevenueService {
    private final RevenueRepository revenueRepository;
    private final RevenueLocationRepository revenueLocationRepository;

    @Override
    public List<Revenue> getRevenueByAccount(long accountNumber) {
        return revenueRepository.findByAccountAccountNumber(accountNumber);
    }

    @Override
    public List<RevenueLocation> getRevenueByLocations(long accountNumber) {
        return revenueLocationRepository.findByAccountAccountNumber(accountNumber);
    }

}
