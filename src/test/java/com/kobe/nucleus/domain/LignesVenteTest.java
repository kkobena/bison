package com.kobe.nucleus.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kobe.nucleus.web.rest.TestUtil;

public class LignesVenteTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LignesVente.class);
        LignesVente lignesVente1 = new LignesVente();
        lignesVente1.setId(1L);
        LignesVente lignesVente2 = new LignesVente();
        lignesVente2.setId(lignesVente1.getId());
        assertThat(lignesVente1).isEqualTo(lignesVente2);
        lignesVente2.setId(2L);
        assertThat(lignesVente1).isNotEqualTo(lignesVente2);
        lignesVente1.setId(null);
        assertThat(lignesVente1).isNotEqualTo(lignesVente2);
    }
}
