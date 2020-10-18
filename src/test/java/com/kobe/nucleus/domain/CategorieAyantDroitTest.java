package com.kobe.nucleus.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kobe.nucleus.web.rest.TestUtil;

public class CategorieAyantDroitTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CategorieAyantDroit.class);
        CategorieAyantDroit categorieAyantDroit1 = new CategorieAyantDroit();
        categorieAyantDroit1.setId(1L);
        CategorieAyantDroit categorieAyantDroit2 = new CategorieAyantDroit();
        categorieAyantDroit2.setId(categorieAyantDroit1.getId());
        assertThat(categorieAyantDroit1).isEqualTo(categorieAyantDroit2);
        categorieAyantDroit2.setId(2L);
        assertThat(categorieAyantDroit1).isNotEqualTo(categorieAyantDroit2);
        categorieAyantDroit1.setId(null);
        assertThat(categorieAyantDroit1).isNotEqualTo(categorieAyantDroit2);
    }
}
