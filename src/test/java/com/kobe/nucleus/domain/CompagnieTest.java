package com.kobe.nucleus.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kobe.nucleus.web.rest.TestUtil;

public class CompagnieTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Compagnie.class);
        Compagnie compagnie1 = new Compagnie();
        compagnie1.setId(1L);
        Compagnie compagnie2 = new Compagnie();
        compagnie2.setId(compagnie1.getId());
        assertThat(compagnie1).isEqualTo(compagnie2);
        compagnie2.setId(2L);
        assertThat(compagnie1).isNotEqualTo(compagnie2);
        compagnie1.setId(null);
        assertThat(compagnie1).isNotEqualTo(compagnie2);
    }
}
