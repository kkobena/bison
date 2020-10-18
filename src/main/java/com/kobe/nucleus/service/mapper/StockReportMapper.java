package com.kobe.nucleus.service.mapper;


import com.kobe.nucleus.domain.*;
import com.kobe.nucleus.service.dto.StockReportDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link StockReport} and its DTO {@link StockReportDTO}.
 */
@Mapper(componentModel = "spring", uses = {StockProduitMapper.class})
public interface StockReportMapper extends EntityMapper<StockReportDTO, StockReport> {

    @Mapping(source = "stockProduit.id", target = "stockProduitId")
    StockReportDTO toDto(StockReport stockReport);

    @Mapping(source = "stockProduitId", target = "stockProduit")
    StockReport toEntity(StockReportDTO stockReportDTO);

    default StockReport fromId(Long id) {
        if (id == null) {
            return null;
        }
        StockReport stockReport = new StockReport();
        stockReport.setId(id);
        return stockReport;
    }
}
