package com.kobe.nucleus.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kobe.nucleus.web.rest.TestUtil;

public class GammeProduitTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GammeProduit.class);
        GammeProduit gammeProduit1 = new GammeProduit();
        gammeProduit1.setId(1L);
        GammeProduit gammeProduit2 = new GammeProduit();
        gammeProduit2.setId(gammeProduit1.getId());
        assertThat(gammeProduit1).isEqualTo(gammeProduit2);
        gammeProduit2.setId(2L);
        assertThat(gammeProduit1).isNotEqualTo(gammeProduit2);
        gammeProduit1.setId(null);
        assertThat(gammeProduit1).isNotEqualTo(gammeProduit2);
    }
}
