package com.kobe.nucleus.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kobe.nucleus.web.rest.TestUtil;

public class MotifDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MotifDTO.class);
        MotifDTO motifDTO1 = new MotifDTO();
        motifDTO1.setId(1L);
        MotifDTO motifDTO2 = new MotifDTO();
        assertThat(motifDTO1).isNotEqualTo(motifDTO2);
        motifDTO2.setId(motifDTO1.getId());
        assertThat(motifDTO1).isEqualTo(motifDTO2);
        motifDTO2.setId(2L);
        assertThat(motifDTO1).isNotEqualTo(motifDTO2);
        motifDTO1.setId(null);
        assertThat(motifDTO1).isNotEqualTo(motifDTO2);
    }
}
