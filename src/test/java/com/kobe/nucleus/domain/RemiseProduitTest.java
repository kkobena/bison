package com.kobe.nucleus.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kobe.nucleus.web.rest.TestUtil;

public class RemiseProduitTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RemiseProduit.class);
        RemiseProduit remiseProduit1 = new RemiseProduit();
        remiseProduit1.setId(1L);
        RemiseProduit remiseProduit2 = new RemiseProduit();
        remiseProduit2.setId(remiseProduit1.getId());
        assertThat(remiseProduit1).isEqualTo(remiseProduit2);
        remiseProduit2.setId(2L);
        assertThat(remiseProduit1).isNotEqualTo(remiseProduit2);
        remiseProduit1.setId(null);
        assertThat(remiseProduit1).isNotEqualTo(remiseProduit2);
    }
}
