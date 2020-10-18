package com.kobe.nucleus.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kobe.nucleus.web.rest.TestUtil;

public class LotDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LotDTO.class);
        LotDTO lotDTO1 = new LotDTO();
        lotDTO1.setId(1L);
        LotDTO lotDTO2 = new LotDTO();
        assertThat(lotDTO1).isNotEqualTo(lotDTO2);
        lotDTO2.setId(lotDTO1.getId());
        assertThat(lotDTO1).isEqualTo(lotDTO2);
        lotDTO2.setId(2L);
        assertThat(lotDTO1).isNotEqualTo(lotDTO2);
        lotDTO1.setId(null);
        assertThat(lotDTO1).isNotEqualTo(lotDTO2);
    }
}
