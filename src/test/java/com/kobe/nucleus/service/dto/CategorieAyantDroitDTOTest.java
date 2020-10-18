package com.kobe.nucleus.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kobe.nucleus.web.rest.TestUtil;

public class CategorieAyantDroitDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CategorieAyantDroitDTO.class);
        CategorieAyantDroitDTO categorieAyantDroitDTO1 = new CategorieAyantDroitDTO();
        categorieAyantDroitDTO1.setId(1L);
        CategorieAyantDroitDTO categorieAyantDroitDTO2 = new CategorieAyantDroitDTO();
        assertThat(categorieAyantDroitDTO1).isNotEqualTo(categorieAyantDroitDTO2);
        categorieAyantDroitDTO2.setId(categorieAyantDroitDTO1.getId());
        assertThat(categorieAyantDroitDTO1).isEqualTo(categorieAyantDroitDTO2);
        categorieAyantDroitDTO2.setId(2L);
        assertThat(categorieAyantDroitDTO1).isNotEqualTo(categorieAyantDroitDTO2);
        categorieAyantDroitDTO1.setId(null);
        assertThat(categorieAyantDroitDTO1).isNotEqualTo(categorieAyantDroitDTO2);
    }
}
