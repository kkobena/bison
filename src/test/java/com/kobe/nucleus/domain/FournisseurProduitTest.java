package com.kobe.nucleus.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kobe.nucleus.web.rest.TestUtil;

public class FournisseurProduitTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FournisseurProduit.class);
        FournisseurProduit fournisseurProduit1 = new FournisseurProduit();
        fournisseurProduit1.setId(1L);
        FournisseurProduit fournisseurProduit2 = new FournisseurProduit();
        fournisseurProduit2.setId(fournisseurProduit1.getId());
        assertThat(fournisseurProduit1).isEqualTo(fournisseurProduit2);
        fournisseurProduit2.setId(2L);
        assertThat(fournisseurProduit1).isNotEqualTo(fournisseurProduit2);
        fournisseurProduit1.setId(null);
        assertThat(fournisseurProduit1).isNotEqualTo(fournisseurProduit2);
    }
}
