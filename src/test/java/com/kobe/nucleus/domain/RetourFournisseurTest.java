package com.kobe.nucleus.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kobe.nucleus.web.rest.TestUtil;

public class RetourFournisseurTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RetourFournisseur.class);
        RetourFournisseur retourFournisseur1 = new RetourFournisseur();
        retourFournisseur1.setId(1L);
        RetourFournisseur retourFournisseur2 = new RetourFournisseur();
        retourFournisseur2.setId(retourFournisseur1.getId());
        assertThat(retourFournisseur1).isEqualTo(retourFournisseur2);
        retourFournisseur2.setId(2L);
        assertThat(retourFournisseur1).isNotEqualTo(retourFournisseur2);
        retourFournisseur1.setId(null);
        assertThat(retourFournisseur1).isNotEqualTo(retourFournisseur2);
    }
}
