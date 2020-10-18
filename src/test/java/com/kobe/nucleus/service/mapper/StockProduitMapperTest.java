package com.kobe.nucleus.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class StockProduitMapperTest {

    private StockProduitMapper stockProduitMapper;

    @BeforeEach
    public void setUp() {
        stockProduitMapper = new StockProduitMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(stockProduitMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(stockProduitMapper.fromId(null)).isNull();
    }
}
