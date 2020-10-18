package com.kobe.nucleus.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kobe.nucleus.web.rest.TestUtil;

public class FabriquantTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Fabriquant.class);
        Fabriquant fabriquant1 = new Fabriquant();
        fabriquant1.setId(1L);
        Fabriquant fabriquant2 = new Fabriquant();
        fabriquant2.setId(fabriquant1.getId());
        assertThat(fabriquant1).isEqualTo(fabriquant2);
        fabriquant2.setId(2L);
        assertThat(fabriquant1).isNotEqualTo(fabriquant2);
        fabriquant1.setId(null);
        assertThat(fabriquant1).isNotEqualTo(fabriquant2);
    }
}
