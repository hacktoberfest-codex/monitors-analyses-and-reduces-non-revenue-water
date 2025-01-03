package com.nrw.non_revenue_water.dto;

public record UserDashboardDTO(
        String userName,
        double accountBalance,
        int numberOfComplaints,
        long totalUsers) {

}
