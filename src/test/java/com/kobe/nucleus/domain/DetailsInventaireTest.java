package com.kobe.nucleus.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kobe.nucleus.web.rest.TestUtil;

public class DetailsInventaireTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DetailsInventaire.class);
        DetailsInventaire detailsInventaire1 = new DetailsInventaire();
        detailsInventaire1.setId(1L);
        DetailsInventaire detailsInventaire2 = new DetailsInventaire();
        detailsInventaire2.setId(detailsInventaire1.getId());
        assertThat(detailsInventaire1).isEqualTo(detailsInventaire2);
        detailsInventaire2.setId(2L);
        assertThat(detailsInventaire1).isNotEqualTo(detailsInventaire2);
        detailsInventaire1.setId(null);
        assertThat(detailsInventaire1).isNotEqualTo(detailsInventaire2);
    }
}
