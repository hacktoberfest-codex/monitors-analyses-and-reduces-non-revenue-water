package com.nrw.non_revenue_water.dto;

import com.nrw.non_revenue_water.constant.ComplaintStatus;

import jakarta.validation.constraints.NotNull;

public record ComplaintUpdateDTO(
        @NotNull(message = "Account ID should not be null") int accountId,
        @NotNull(message = "Account holder name should not be Null") String accountHolderName,
        @NotNull(message = "Account email should not be Null") String accountEmail,
        @NotNull(message = "Complaint Description should not be null") String complaintDescription,
        @NotNull(message = "Complaint status should not be null") ComplaintStatus complaintStatus) {

}
