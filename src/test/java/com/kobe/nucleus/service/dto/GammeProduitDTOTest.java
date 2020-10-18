package com.kobe.nucleus.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kobe.nucleus.web.rest.TestUtil;

public class GammeProduitDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(GammeProduitDTO.class);
        GammeProduitDTO gammeProduitDTO1 = new GammeProduitDTO();
        gammeProduitDTO1.setId(1L);
        GammeProduitDTO gammeProduitDTO2 = new GammeProduitDTO();
        assertThat(gammeProduitDTO1).isNotEqualTo(gammeProduitDTO2);
        gammeProduitDTO2.setId(gammeProduitDTO1.getId());
        assertThat(gammeProduitDTO1).isEqualTo(gammeProduitDTO2);
        gammeProduitDTO2.setId(2L);
        assertThat(gammeProduitDTO1).isNotEqualTo(gammeProduitDTO2);
        gammeProduitDTO1.setId(null);
        assertThat(gammeProduitDTO1).isNotEqualTo(gammeProduitDTO2);
    }
}
