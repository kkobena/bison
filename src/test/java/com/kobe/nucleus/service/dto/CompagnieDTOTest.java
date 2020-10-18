package com.kobe.nucleus.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kobe.nucleus.web.rest.TestUtil;

public class CompagnieDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CompagnieDTO.class);
        CompagnieDTO compagnieDTO1 = new CompagnieDTO();
        compagnieDTO1.setId(1L);
        CompagnieDTO compagnieDTO2 = new CompagnieDTO();
        assertThat(compagnieDTO1).isNotEqualTo(compagnieDTO2);
        compagnieDTO2.setId(compagnieDTO1.getId());
        assertThat(compagnieDTO1).isEqualTo(compagnieDTO2);
        compagnieDTO2.setId(2L);
        assertThat(compagnieDTO1).isNotEqualTo(compagnieDTO2);
        compagnieDTO1.setId(null);
        assertThat(compagnieDTO1).isNotEqualTo(compagnieDTO2);
    }
}
