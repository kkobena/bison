package com.kobe.nucleus.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kobe.nucleus.web.rest.TestUtil;

public class CategorieProduitDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CategorieProduitDTO.class);
        CategorieProduitDTO categorieProduitDTO1 = new CategorieProduitDTO();
        categorieProduitDTO1.setId(1L);
        CategorieProduitDTO categorieProduitDTO2 = new CategorieProduitDTO();
        assertThat(categorieProduitDTO1).isNotEqualTo(categorieProduitDTO2);
        categorieProduitDTO2.setId(categorieProduitDTO1.getId());
        assertThat(categorieProduitDTO1).isEqualTo(categorieProduitDTO2);
        categorieProduitDTO2.setId(2L);
        assertThat(categorieProduitDTO1).isNotEqualTo(categorieProduitDTO2);
        categorieProduitDTO1.setId(null);
        assertThat(categorieProduitDTO1).isNotEqualTo(categorieProduitDTO2);
    }
}
