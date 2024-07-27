package com.nrw.non_revenue_water.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nrw.non_revenue_water.model.WaterFlow;
import com.nrw.non_revenue_water.repository.WaterFlowRepository;
import com.nrw.non_revenue_water.service.IWaterFlowService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WaterFlowServiceImpl implements IWaterFlowService {
    private final WaterFlowRepository waterFlowRepository;

    @Override
    public List<WaterFlow> getWaterFlowByAccount(String flowType, long accountNumber) {
        return waterFlowRepository.findByFlowTypeAndAccountAccountNumber(flowType, accountNumber);
    }

}
