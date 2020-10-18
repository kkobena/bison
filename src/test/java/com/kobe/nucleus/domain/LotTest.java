package com.kobe.nucleus.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kobe.nucleus.web.rest.TestUtil;

public class LotTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Lot.class);
        Lot lot1 = new Lot();
        lot1.setId(1L);
        Lot lot2 = new Lot();
        lot2.setId(lot1.getId());
        assertThat(lot1).isEqualTo(lot2);
        lot2.setId(2L);
        assertThat(lot1).isNotEqualTo(lot2);
        lot1.setId(null);
        assertThat(lot1).isNotEqualTo(lot2);
    }
}
