package com.nrw.non_revenue_water.controller;

import java.util.concurrent.ExecutionException;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nrw.non_revenue_water.dto.AdminDashboardDTO;
import com.nrw.non_revenue_water.service.IAccountService;
import com.nrw.non_revenue_water.service.ITransactionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/dashboards")
@RequiredArgsConstructor
@CrossOrigin
public class DashboardController {
    private final ITransactionService transactionService;
    private final IAccountService accountService;

    // @GetMapping("/admin")
    // public AdminDashboardDTO adminDashboardDetails(@RequestAttribute long accountNumber)
    //         throws ExecutionException, InterruptedException {
    //     // var totalRevenue=
    //     return new AdminDashboardDTO();

    // }

}
