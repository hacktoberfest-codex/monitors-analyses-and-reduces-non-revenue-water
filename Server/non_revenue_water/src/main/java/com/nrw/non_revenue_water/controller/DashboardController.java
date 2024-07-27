package com.nrw.non_revenue_water.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nrw.non_revenue_water.dto.AdminDashboardDTO;
import com.nrw.non_revenue_water.dto.TransactionDTO;
import com.nrw.non_revenue_water.mapper.TransactionMapper;
import com.nrw.non_revenue_water.service.IAccountService;
// import com.nrw.non_revenue_water.service.ITransactionService;
import com.nrw.non_revenue_water.service.IRevenueService;
import com.nrw.non_revenue_water.service.IWaterFlowService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/dashboards")
@RequiredArgsConstructor
@CrossOrigin
public class DashboardController {
    // private final ITransactionService transactionService;
    private final IAccountService accountService;
    private final IRevenueService revenueService;
    private final IWaterFlowService waterFlowService;

    // Admin Dashboard API
    @GetMapping("/admin")
    public AdminDashboardDTO adminDashboardDetails(@RequestAttribute long accountNumber) {
        var result = accountService.getAccountBalanaceAndNumberOfComplaints(accountNumber);
        var accountBalance = result.get().getAccountBalance();
        var numberOfComplaints = result.get().getNumberOfComplaints();
        var totalUsers = accountService.countRecord() - 1;
        var todaysRevenue = 0;
        var name = result.get().getAccountHolderName();

        // Revenue By Month
        var results = revenueService.getRevenueByAccount(accountNumber);
        var transaction = results.stream().map(TransactionMapper::dtoMapperSeries).toList();
        var transactions = TransactionMapper.dtoMapper(name, transaction);
        List<TransactionDTO> revenueMonth = new ArrayList<>();
        revenueMonth.add(transactions);

        // Revenue By Location
        var revenue = revenueService.getRevenueByLocations(accountNumber);
        var revenueLocation = revenue.stream().map(TransactionMapper::dtoMapperLocation).toList();

        // Water Flow
        // Flow Type is either Inflow or Outflow
        var inflow = waterFlowService.getWaterFlowByAccount("INFLOW", accountNumber);
        var inflowMapper = inflow.stream().map(TransactionMapper::dtoMapperWaterFlow).toList();
        var inflows = TransactionMapper.dtoMapper("INFLOW", inflowMapper);

        var outflow = waterFlowService.getWaterFlowByAccount("OUTFLOW", accountNumber);
        var outflowMapper = outflow.stream().map(TransactionMapper::dtoMapperWaterFlow).toList();
        var outflows = TransactionMapper.dtoMapper("OUTFLOW", outflowMapper);

        List<TransactionDTO> waterFlow = new ArrayList<>();
        waterFlow.add(inflows);
        waterFlow.add(outflows);

        return new AdminDashboardDTO(name, accountBalance, numberOfComplaints,
                todaysRevenue, totalUsers, revenueMonth, revenueLocation, waterFlow);

    }

}
