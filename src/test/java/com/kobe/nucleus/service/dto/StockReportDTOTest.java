package com.kobe.nucleus.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kobe.nucleus.web.rest.TestUtil;

public class StockReportDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(StockReportDTO.class);
        StockReportDTO stockReportDTO1 = new StockReportDTO();
        stockReportDTO1.setId(1L);
        StockReportDTO stockReportDTO2 = new StockReportDTO();
        assertThat(stockReportDTO1).isNotEqualTo(stockReportDTO2);
        stockReportDTO2.setId(stockReportDTO1.getId());
        assertThat(stockReportDTO1).isEqualTo(stockReportDTO2);
        stockReportDTO2.setId(2L);
        assertThat(stockReportDTO1).isNotEqualTo(stockReportDTO2);
        stockReportDTO1.setId(null);
        assertThat(stockReportDTO1).isNotEqualTo(stockReportDTO2);
    }
}
