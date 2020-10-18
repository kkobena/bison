package com.kobe.nucleus.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kobe.nucleus.web.rest.TestUtil;

public class StockProduitTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(StockProduit.class);
        StockProduit stockProduit1 = new StockProduit();
        stockProduit1.setId(1L);
        StockProduit stockProduit2 = new StockProduit();
        stockProduit2.setId(stockProduit1.getId());
        assertThat(stockProduit1).isEqualTo(stockProduit2);
        stockProduit2.setId(2L);
        assertThat(stockProduit1).isNotEqualTo(stockProduit2);
        stockProduit1.setId(null);
        assertThat(stockProduit1).isNotEqualTo(stockProduit2);
    }
}
