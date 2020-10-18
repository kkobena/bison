package com.kobe.nucleus.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kobe.nucleus.web.rest.TestUtil;

public class TierspayantTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Tierspayant.class);
        Tierspayant tierspayant1 = new Tierspayant();
        tierspayant1.setId(1L);
        Tierspayant tierspayant2 = new Tierspayant();
        tierspayant2.setId(tierspayant1.getId());
        assertThat(tierspayant1).isEqualTo(tierspayant2);
        tierspayant2.setId(2L);
        assertThat(tierspayant1).isNotEqualTo(tierspayant2);
        tierspayant1.setId(null);
        assertThat(tierspayant1).isNotEqualTo(tierspayant2);
    }
}
