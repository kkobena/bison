package com.kobe.nucleus.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kobe.nucleus.web.rest.TestUtil;

public class CompteClientDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CompteClientDTO.class);
        CompteClientDTO compteClientDTO1 = new CompteClientDTO();
        compteClientDTO1.setId(1L);
        CompteClientDTO compteClientDTO2 = new CompteClientDTO();
        assertThat(compteClientDTO1).isNotEqualTo(compteClientDTO2);
        compteClientDTO2.setId(compteClientDTO1.getId());
        assertThat(compteClientDTO1).isEqualTo(compteClientDTO2);
        compteClientDTO2.setId(2L);
        assertThat(compteClientDTO1).isNotEqualTo(compteClientDTO2);
        compteClientDTO1.setId(null);
        assertThat(compteClientDTO1).isNotEqualTo(compteClientDTO2);
    }
}
