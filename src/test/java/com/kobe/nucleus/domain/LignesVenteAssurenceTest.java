package com.kobe.nucleus.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kobe.nucleus.web.rest.TestUtil;

public class LignesVenteAssurenceTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LignesVenteAssurence.class);
        LignesVenteAssurence lignesVenteAssurence1 = new LignesVenteAssurence();
        lignesVenteAssurence1.setId(1L);
        LignesVenteAssurence lignesVenteAssurence2 = new LignesVenteAssurence();
        lignesVenteAssurence2.setId(lignesVenteAssurence1.getId());
        assertThat(lignesVenteAssurence1).isEqualTo(lignesVenteAssurence2);
        lignesVenteAssurence2.setId(2L);
        assertThat(lignesVenteAssurence1).isNotEqualTo(lignesVenteAssurence2);
        lignesVenteAssurence1.setId(null);
        assertThat(lignesVenteAssurence1).isNotEqualTo(lignesVenteAssurence2);
    }
}
