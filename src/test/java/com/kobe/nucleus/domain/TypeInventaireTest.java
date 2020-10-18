package com.kobe.nucleus.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kobe.nucleus.web.rest.TestUtil;

public class TypeInventaireTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TypeInventaire.class);
        TypeInventaire typeInventaire1 = new TypeInventaire();
        typeInventaire1.setId(1L);
        TypeInventaire typeInventaire2 = new TypeInventaire();
        typeInventaire2.setId(typeInventaire1.getId());
        assertThat(typeInventaire1).isEqualTo(typeInventaire2);
        typeInventaire2.setId(2L);
        assertThat(typeInventaire1).isNotEqualTo(typeInventaire2);
        typeInventaire1.setId(null);
        assertThat(typeInventaire1).isNotEqualTo(typeInventaire2);
    }
}
