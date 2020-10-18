package com.kobe.nucleus.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kobe.nucleus.web.rest.TestUtil;

public class RisqueTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Risque.class);
        Risque risque1 = new Risque();
        risque1.setId(1L);
        Risque risque2 = new Risque();
        risque2.setId(risque1.getId());
        assertThat(risque1).isEqualTo(risque2);
        risque2.setId(2L);
        assertThat(risque1).isNotEqualTo(risque2);
        risque1.setId(null);
        assertThat(risque1).isNotEqualTo(risque2);
    }
}
