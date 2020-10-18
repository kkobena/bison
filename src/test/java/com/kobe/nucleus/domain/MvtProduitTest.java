package com.kobe.nucleus.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kobe.nucleus.web.rest.TestUtil;

public class MvtProduitTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MvtProduit.class);
        MvtProduit mvtProduit1 = new MvtProduit();
        mvtProduit1.setId(1L);
        MvtProduit mvtProduit2 = new MvtProduit();
        mvtProduit2.setId(mvtProduit1.getId());
        assertThat(mvtProduit1).isEqualTo(mvtProduit2);
        mvtProduit2.setId(2L);
        assertThat(mvtProduit1).isNotEqualTo(mvtProduit2);
        mvtProduit1.setId(null);
        assertThat(mvtProduit1).isNotEqualTo(mvtProduit2);
    }
}
