package com.kobe.nucleus.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kobe.nucleus.web.rest.TestUtil;

public class FournisseurProduitDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FournisseurProduitDTO.class);
        FournisseurProduitDTO fournisseurProduitDTO1 = new FournisseurProduitDTO();
        fournisseurProduitDTO1.setId(1L);
        FournisseurProduitDTO fournisseurProduitDTO2 = new FournisseurProduitDTO();
        assertThat(fournisseurProduitDTO1).isNotEqualTo(fournisseurProduitDTO2);
        fournisseurProduitDTO2.setId(fournisseurProduitDTO1.getId());
        assertThat(fournisseurProduitDTO1).isEqualTo(fournisseurProduitDTO2);
        fournisseurProduitDTO2.setId(2L);
        assertThat(fournisseurProduitDTO1).isNotEqualTo(fournisseurProduitDTO2);
        fournisseurProduitDTO1.setId(null);
        assertThat(fournisseurProduitDTO1).isNotEqualTo(fournisseurProduitDTO2);
    }
}
