package com.kobe.nucleus.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kobe.nucleus.web.rest.TestUtil;

public class CompteClientTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CompteClient.class);
        CompteClient compteClient1 = new CompteClient();
        compteClient1.setId(1L);
        CompteClient compteClient2 = new CompteClient();
        compteClient2.setId(compteClient1.getId());
        assertThat(compteClient1).isEqualTo(compteClient2);
        compteClient2.setId(2L);
        assertThat(compteClient1).isNotEqualTo(compteClient2);
        compteClient1.setId(null);
        assertThat(compteClient1).isNotEqualTo(compteClient2);
    }
}
