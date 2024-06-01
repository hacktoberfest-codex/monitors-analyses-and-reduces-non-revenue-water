package com.nrw.non_revenue_water.dto;

import com.nrw.non_revenue_water.constant.AccountType;

public record AccountResponseDTO(
        long accountNumber,
        String accountHolderName,
        String aboutCustomer,
        String contactNumber,
        String accountEmail,
        double accountBalance,
        int numberOfComplaints,
        AccountType accountType,
        String city,
        String state,
        String area,
        String country,
        String zipcode) {

}
