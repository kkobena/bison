package com.kobe.nucleus.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kobe.nucleus.web.rest.TestUtil;

public class TvaDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TvaDTO.class);
        TvaDTO tvaDTO1 = new TvaDTO();
        tvaDTO1.setId(1L);
        TvaDTO tvaDTO2 = new TvaDTO();
        assertThat(tvaDTO1).isNotEqualTo(tvaDTO2);
        tvaDTO2.setId(tvaDTO1.getId());
        assertThat(tvaDTO1).isEqualTo(tvaDTO2);
        tvaDTO2.setId(2L);
        assertThat(tvaDTO1).isNotEqualTo(tvaDTO2);
        tvaDTO1.setId(null);
        assertThat(tvaDTO1).isNotEqualTo(tvaDTO2);
    }
}
