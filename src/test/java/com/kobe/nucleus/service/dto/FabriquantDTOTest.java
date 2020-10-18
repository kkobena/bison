package com.kobe.nucleus.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kobe.nucleus.web.rest.TestUtil;

public class FabriquantDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FabriquantDTO.class);
        FabriquantDTO fabriquantDTO1 = new FabriquantDTO();
        fabriquantDTO1.setId(1L);
        FabriquantDTO fabriquantDTO2 = new FabriquantDTO();
        assertThat(fabriquantDTO1).isNotEqualTo(fabriquantDTO2);
        fabriquantDTO2.setId(fabriquantDTO1.getId());
        assertThat(fabriquantDTO1).isEqualTo(fabriquantDTO2);
        fabriquantDTO2.setId(2L);
        assertThat(fabriquantDTO1).isNotEqualTo(fabriquantDTO2);
        fabriquantDTO1.setId(null);
        assertThat(fabriquantDTO1).isNotEqualTo(fabriquantDTO2);
    }
}
