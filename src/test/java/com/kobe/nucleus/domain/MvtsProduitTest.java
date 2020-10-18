package com.kobe.nucleus.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kobe.nucleus.web.rest.TestUtil;

public class MvtsProduitTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MvtsProduit.class);
        MvtsProduit mvtsProduit1 = new MvtsProduit();
        mvtsProduit1.setId(1L);
        MvtsProduit mvtsProduit2 = new MvtsProduit();
        mvtsProduit2.setId(mvtsProduit1.getId());
        assertThat(mvtsProduit1).isEqualTo(mvtsProduit2);
        mvtsProduit2.setId(2L);
        assertThat(mvtsProduit1).isNotEqualTo(mvtsProduit2);
        mvtsProduit1.setId(null);
        assertThat(mvtsProduit1).isNotEqualTo(mvtsProduit2);
    }
}
