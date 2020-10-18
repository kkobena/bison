package com.kobe.nucleus.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kobe.nucleus.web.rest.TestUtil;

public class LignesVenteDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LignesVenteDTO.class);
        LignesVenteDTO lignesVenteDTO1 = new LignesVenteDTO();
        lignesVenteDTO1.setId(1L);
        LignesVenteDTO lignesVenteDTO2 = new LignesVenteDTO();
        assertThat(lignesVenteDTO1).isNotEqualTo(lignesVenteDTO2);
        lignesVenteDTO2.setId(lignesVenteDTO1.getId());
        assertThat(lignesVenteDTO1).isEqualTo(lignesVenteDTO2);
        lignesVenteDTO2.setId(2L);
        assertThat(lignesVenteDTO1).isNotEqualTo(lignesVenteDTO2);
        lignesVenteDTO1.setId(null);
        assertThat(lignesVenteDTO1).isNotEqualTo(lignesVenteDTO2);
    }
}
