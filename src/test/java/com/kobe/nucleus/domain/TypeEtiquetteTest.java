package com.kobe.nucleus.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kobe.nucleus.web.rest.TestUtil;

public class TypeEtiquetteTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TypeEtiquette.class);
        TypeEtiquette typeEtiquette1 = new TypeEtiquette();
        typeEtiquette1.setId(1L);
        TypeEtiquette typeEtiquette2 = new TypeEtiquette();
        typeEtiquette2.setId(typeEtiquette1.getId());
        assertThat(typeEtiquette1).isEqualTo(typeEtiquette2);
        typeEtiquette2.setId(2L);
        assertThat(typeEtiquette1).isNotEqualTo(typeEtiquette2);
        typeEtiquette1.setId(null);
        assertThat(typeEtiquette1).isNotEqualTo(typeEtiquette2);
    }
}
