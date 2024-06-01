package com.nrw.non_revenue_water.dto;

import com.nrw.non_revenue_water.constant.AccountType;

public record AdminAccountResponseDTO(
        long accountNumber,
        String accountHolderName,
        String aboutCustomer,
        String contactNumber,
        String accountEmail,
        double accountBalance,
        int numberOfComplaints,
        double todaysRevenue,
        int totalUsers,
        AccountType accountType) {

}
