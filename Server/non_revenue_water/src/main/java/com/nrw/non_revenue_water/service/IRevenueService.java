package com.nrw.non_revenue_water.service;

import java.util.List;

import com.nrw.non_revenue_water.model.Revenue;
import com.nrw.non_revenue_water.model.RevenueLocation;

public interface IRevenueService {
    // Revenue By Month
    List<Revenue> getRevenueByAccount(long accountNumber);

    // Revenue By Location
    List<RevenueLocation> getRevenueByLocations(long accountNumber);
}
