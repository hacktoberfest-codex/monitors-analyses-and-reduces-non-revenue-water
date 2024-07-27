package com.nrw.non_revenue_water.mapper;

import java.util.List;

import com.nrw.non_revenue_water.dto.RevenueByLocationDTO;
import com.nrw.non_revenue_water.dto.TransactionDTO;
import com.nrw.non_revenue_water.dto.TransactionSeriesDTO;
import com.nrw.non_revenue_water.model.Revenue;
import com.nrw.non_revenue_water.model.RevenueLocation;
import com.nrw.non_revenue_water.model.WaterFlow;

public class TransactionMapper {
    private TransactionMapper() {

    }

    public static TransactionDTO dtoMapper(String name, List<TransactionSeriesDTO> transactionSeriesDTO) {
        return new TransactionDTO(name, transactionSeriesDTO);
    }

    public static TransactionSeriesDTO dtoMapperSeries(Revenue revenue) {
        return new TransactionSeriesDTO(revenue.getMonth(), revenue.getRevenue());
    }

    public static RevenueByLocationDTO dtoMapperLocation(RevenueLocation revenueLocation) {
        return new RevenueByLocationDTO(revenueLocation.getName(), revenueLocation.getValue());
    }

    public static TransactionSeriesDTO dtoMapperWaterFlow(WaterFlow waterFlow) {
        return new TransactionSeriesDTO(waterFlow.getMonth(), waterFlow.getFlow());
    }
}
