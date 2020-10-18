package com.kobe.nucleus.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kobe.nucleus.web.rest.TestUtil;

public class RayonDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RayonDTO.class);
        RayonDTO rayonDTO1 = new RayonDTO();
        rayonDTO1.setId(1L);
        RayonDTO rayonDTO2 = new RayonDTO();
        assertThat(rayonDTO1).isNotEqualTo(rayonDTO2);
        rayonDTO2.setId(rayonDTO1.getId());
        assertThat(rayonDTO1).isEqualTo(rayonDTO2);
        rayonDTO2.setId(2L);
        assertThat(rayonDTO1).isNotEqualTo(rayonDTO2);
        rayonDTO1.setId(null);
        assertThat(rayonDTO1).isNotEqualTo(rayonDTO2);
    }
}
