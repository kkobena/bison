package com.kobe.nucleus.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kobe.nucleus.web.rest.TestUtil;

public class AjustementDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AjustementDTO.class);
        AjustementDTO ajustementDTO1 = new AjustementDTO();
        ajustementDTO1.setId(1L);
        AjustementDTO ajustementDTO2 = new AjustementDTO();
        assertThat(ajustementDTO1).isNotEqualTo(ajustementDTO2);
        ajustementDTO2.setId(ajustementDTO1.getId());
        assertThat(ajustementDTO1).isEqualTo(ajustementDTO2);
        ajustementDTO2.setId(2L);
        assertThat(ajustementDTO1).isNotEqualTo(ajustementDTO2);
        ajustementDTO1.setId(null);
        assertThat(ajustementDTO1).isNotEqualTo(ajustementDTO2);
    }
}
