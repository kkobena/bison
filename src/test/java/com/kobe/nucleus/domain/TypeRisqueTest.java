package com.kobe.nucleus.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kobe.nucleus.web.rest.TestUtil;

public class TypeRisqueTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TypeRisque.class);
        TypeRisque typeRisque1 = new TypeRisque();
        typeRisque1.setId(1L);
        TypeRisque typeRisque2 = new TypeRisque();
        typeRisque2.setId(typeRisque1.getId());
        assertThat(typeRisque1).isEqualTo(typeRisque2);
        typeRisque2.setId(2L);
        assertThat(typeRisque1).isNotEqualTo(typeRisque2);
        typeRisque1.setId(null);
        assertThat(typeRisque1).isNotEqualTo(typeRisque2);
    }
}
