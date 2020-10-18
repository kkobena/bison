package com.kobe.nucleus.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kobe.nucleus.web.rest.TestUtil;

public class EtiquetteTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Etiquette.class);
        Etiquette etiquette1 = new Etiquette();
        etiquette1.setId(1L);
        Etiquette etiquette2 = new Etiquette();
        etiquette2.setId(etiquette1.getId());
        assertThat(etiquette1).isEqualTo(etiquette2);
        etiquette2.setId(2L);
        assertThat(etiquette1).isNotEqualTo(etiquette2);
        etiquette1.setId(null);
        assertThat(etiquette1).isNotEqualTo(etiquette2);
    }
}
