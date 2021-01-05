package com.kobe.nucleus.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kobe.nucleus.web.rest.TestUtil;

public class StockoutTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Stockout.class);
        Stockout stockout1 = new Stockout();
        stockout1.setId(1L);
        Stockout stockout2 = new Stockout();
        stockout2.setId(stockout1.getId());
        assertThat(stockout1).isEqualTo(stockout2);
        stockout2.setId(2L);
        assertThat(stockout1).isNotEqualTo(stockout2);
        stockout1.setId(null);
        assertThat(stockout1).isNotEqualTo(stockout2);
    }
}
