package com.kobe.nucleus.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kobe.nucleus.web.rest.TestUtil;

public class FormProduitTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FormProduit.class);
        FormProduit formProduit1 = new FormProduit();
        formProduit1.setId(1L);
        FormProduit formProduit2 = new FormProduit();
        formProduit2.setId(formProduit1.getId());
        assertThat(formProduit1).isEqualTo(formProduit2);
        formProduit2.setId(2L);
        assertThat(formProduit1).isNotEqualTo(formProduit2);
        formProduit1.setId(null);
        assertThat(formProduit1).isNotEqualTo(formProduit2);
    }
}
