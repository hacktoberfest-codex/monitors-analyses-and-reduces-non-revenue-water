package com.nrw.non_revenue_water.dto;

import com.nrw.non_revenue_water.constant.ComplaintStatus;

public record ComplaintResponseDTO(
                int accountId,
                String accountHolderName,
                String accountEmail,
                String complaintDescription,
                ComplaintStatus complaintStatus) {
}
