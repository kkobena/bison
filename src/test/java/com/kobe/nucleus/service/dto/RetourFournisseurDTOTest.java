package com.kobe.nucleus.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kobe.nucleus.web.rest.TestUtil;

public class RetourFournisseurDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RetourFournisseurDTO.class);
        RetourFournisseurDTO retourFournisseurDTO1 = new RetourFournisseurDTO();
        retourFournisseurDTO1.setId(1L);
        RetourFournisseurDTO retourFournisseurDTO2 = new RetourFournisseurDTO();
        assertThat(retourFournisseurDTO1).isNotEqualTo(retourFournisseurDTO2);
        retourFournisseurDTO2.setId(retourFournisseurDTO1.getId());
        assertThat(retourFournisseurDTO1).isEqualTo(retourFournisseurDTO2);
        retourFournisseurDTO2.setId(2L);
        assertThat(retourFournisseurDTO1).isNotEqualTo(retourFournisseurDTO2);
        retourFournisseurDTO1.setId(null);
        assertThat(retourFournisseurDTO1).isNotEqualTo(retourFournisseurDTO2);
    }
}
