package com.kobe.nucleus.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kobe.nucleus.web.rest.TestUtil;

public class StockProduitDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(StockProduitDTO.class);
        StockProduitDTO stockProduitDTO1 = new StockProduitDTO();
        stockProduitDTO1.setId(1L);
        StockProduitDTO stockProduitDTO2 = new StockProduitDTO();
        assertThat(stockProduitDTO1).isNotEqualTo(stockProduitDTO2);
        stockProduitDTO2.setId(stockProduitDTO1.getId());
        assertThat(stockProduitDTO1).isEqualTo(stockProduitDTO2);
        stockProduitDTO2.setId(2L);
        assertThat(stockProduitDTO1).isNotEqualTo(stockProduitDTO2);
        stockProduitDTO1.setId(null);
        assertThat(stockProduitDTO1).isNotEqualTo(stockProduitDTO2);
    }
}
