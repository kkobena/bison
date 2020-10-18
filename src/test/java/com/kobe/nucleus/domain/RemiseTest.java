package com.kobe.nucleus.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kobe.nucleus.web.rest.TestUtil;

public class RemiseTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Remise.class);
        Remise remise1 = new Remise();
        remise1.setId(1L);
        Remise remise2 = new Remise();
        remise2.setId(remise1.getId());
        assertThat(remise1).isEqualTo(remise2);
        remise2.setId(2L);
        assertThat(remise1).isNotEqualTo(remise2);
        remise1.setId(null);
        assertThat(remise1).isNotEqualTo(remise2);
    }
}
