package com.nrw.non_revenue_water.dto;

import jakarta.validation.constraints.NotNull;

public record ComplaintRequestDTO(
        @NotNull(message = "Account holder name should not be Null") String accountHolderName,
        @NotNull(message = "Account email should not be Null") String accountEmail,
        @NotNull(message = "Description should not be null") String complaintDescription) {

}
