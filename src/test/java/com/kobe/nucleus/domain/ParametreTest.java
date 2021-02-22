package com.kobe.nucleus.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kobe.nucleus.web.rest.TestUtil;

public class ParametreTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Parametre.class);
        Parametre parametre1 = new Parametre();
        parametre1.setId(1L);
        Parametre parametre2 = new Parametre();
        parametre2.setId(parametre1.getId());
        assertThat(parametre1).isEqualTo(parametre2);
        parametre2.setId(2L);
        assertThat(parametre1).isNotEqualTo(parametre2);
        parametre1.setId(null);
        assertThat(parametre1).isNotEqualTo(parametre2);
    }
}
