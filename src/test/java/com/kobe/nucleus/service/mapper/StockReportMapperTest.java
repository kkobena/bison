package com.kobe.nucleus.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class StockReportMapperTest {

    private StockReportMapper stockReportMapper;

    @BeforeEach
    public void setUp() {
        stockReportMapper = new StockReportMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(stockReportMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(stockReportMapper.fromId(null)).isNull();
    }
}
