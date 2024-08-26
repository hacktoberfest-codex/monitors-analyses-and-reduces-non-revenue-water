package com.nrw.non_revenue_water.dto;

import java.util.List;

public record TransactionDTO(
                String name,
                List<TransactionSeriesDTO> series) {

}
