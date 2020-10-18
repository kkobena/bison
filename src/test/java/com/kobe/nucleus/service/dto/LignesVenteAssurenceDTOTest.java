package com.kobe.nucleus.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kobe.nucleus.web.rest.TestUtil;

public class LignesVenteAssurenceDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LignesVenteAssurenceDTO.class);
        LignesVenteAssurenceDTO lignesVenteAssurenceDTO1 = new LignesVenteAssurenceDTO();
        lignesVenteAssurenceDTO1.setId(1L);
        LignesVenteAssurenceDTO lignesVenteAssurenceDTO2 = new LignesVenteAssurenceDTO();
        assertThat(lignesVenteAssurenceDTO1).isNotEqualTo(lignesVenteAssurenceDTO2);
        lignesVenteAssurenceDTO2.setId(lignesVenteAssurenceDTO1.getId());
        assertThat(lignesVenteAssurenceDTO1).isEqualTo(lignesVenteAssurenceDTO2);
        lignesVenteAssurenceDTO2.setId(2L);
        assertThat(lignesVenteAssurenceDTO1).isNotEqualTo(lignesVenteAssurenceDTO2);
        lignesVenteAssurenceDTO1.setId(null);
        assertThat(lignesVenteAssurenceDTO1).isNotEqualTo(lignesVenteAssurenceDTO2);
    }
}
