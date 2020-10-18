package com.kobe.nucleus.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kobe.nucleus.web.rest.TestUtil;

public class DetailsAjustementTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DetailsAjustement.class);
        DetailsAjustement detailsAjustement1 = new DetailsAjustement();
        detailsAjustement1.setId(1L);
        DetailsAjustement detailsAjustement2 = new DetailsAjustement();
        detailsAjustement2.setId(detailsAjustement1.getId());
        assertThat(detailsAjustement1).isEqualTo(detailsAjustement2);
        detailsAjustement2.setId(2L);
        assertThat(detailsAjustement1).isNotEqualTo(detailsAjustement2);
        detailsAjustement1.setId(null);
        assertThat(detailsAjustement1).isNotEqualTo(detailsAjustement2);
    }
}
