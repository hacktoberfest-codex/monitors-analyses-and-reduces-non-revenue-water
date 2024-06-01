package com.nrw.non_revenue_water.dto;

public record AdminDashboardDTO(
        double accountBalance,
        int numberOfComplaints,
        double todaysRevenue,
        int totalUsers) {

}
