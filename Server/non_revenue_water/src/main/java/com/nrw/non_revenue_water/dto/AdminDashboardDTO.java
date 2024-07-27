package com.nrw.non_revenue_water.dto;

import java.util.List;

public record AdminDashboardDTO(
                String userName,
                double accountBalance,
                int numberOfComplaints,
                double todaysRevenue,
                long totalUsers,
                List<TransactionDTO> transactions,
                List<RevenueByLocationDTO> revenueByLocations,
                List<TransactionDTO> waterFlows) {

}
