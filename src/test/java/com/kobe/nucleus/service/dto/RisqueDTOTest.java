package com.kobe.nucleus.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kobe.nucleus.web.rest.TestUtil;

public class RisqueDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RisqueDTO.class);
        RisqueDTO risqueDTO1 = new RisqueDTO();
        risqueDTO1.setId(1L);
        RisqueDTO risqueDTO2 = new RisqueDTO();
        assertThat(risqueDTO1).isNotEqualTo(risqueDTO2);
        risqueDTO2.setId(risqueDTO1.getId());
        assertThat(risqueDTO1).isEqualTo(risqueDTO2);
        risqueDTO2.setId(2L);
        assertThat(risqueDTO1).isNotEqualTo(risqueDTO2);
        risqueDTO1.setId(null);
        assertThat(risqueDTO1).isNotEqualTo(risqueDTO2);
    }
}
