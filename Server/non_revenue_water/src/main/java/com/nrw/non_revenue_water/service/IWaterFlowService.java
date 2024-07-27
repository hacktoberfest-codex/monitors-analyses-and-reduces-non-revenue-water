package com.nrw.non_revenue_water.service;

import java.util.List;

import com.nrw.non_revenue_water.model.WaterFlow;

public interface IWaterFlowService {

    // Water Inflow and Outflow
    List<WaterFlow> getWaterFlowByAccount(String flowType, long accountNumber);
    
}
