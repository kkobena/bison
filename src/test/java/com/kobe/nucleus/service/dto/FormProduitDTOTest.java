package com.kobe.nucleus.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kobe.nucleus.web.rest.TestUtil;

public class FormProduitDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FormProduitDTO.class);
        FormProduitDTO formProduitDTO1 = new FormProduitDTO();
        formProduitDTO1.setId(1L);
        FormProduitDTO formProduitDTO2 = new FormProduitDTO();
        assertThat(formProduitDTO1).isNotEqualTo(formProduitDTO2);
        formProduitDTO2.setId(formProduitDTO1.getId());
        assertThat(formProduitDTO1).isEqualTo(formProduitDTO2);
        formProduitDTO2.setId(2L);
        assertThat(formProduitDTO1).isNotEqualTo(formProduitDTO2);
        formProduitDTO1.setId(null);
        assertThat(formProduitDTO1).isNotEqualTo(formProduitDTO2);
    }
}
