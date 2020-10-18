package com.kobe.nucleus.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kobe.nucleus.web.rest.TestUtil;

public class StockReportTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(StockReport.class);
        StockReport stockReport1 = new StockReport();
        stockReport1.setId(1L);
        StockReport stockReport2 = new StockReport();
        stockReport2.setId(stockReport1.getId());
        assertThat(stockReport1).isEqualTo(stockReport2);
        stockReport2.setId(2L);
        assertThat(stockReport1).isNotEqualTo(stockReport2);
        stockReport1.setId(null);
        assertThat(stockReport1).isNotEqualTo(stockReport2);
    }
}
