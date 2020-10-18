package com.kobe.nucleus.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kobe.nucleus.web.rest.TestUtil;

public class FactureItemDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FactureItemDTO.class);
        FactureItemDTO factureItemDTO1 = new FactureItemDTO();
        factureItemDTO1.setId(1L);
        FactureItemDTO factureItemDTO2 = new FactureItemDTO();
        assertThat(factureItemDTO1).isNotEqualTo(factureItemDTO2);
        factureItemDTO2.setId(factureItemDTO1.getId());
        assertThat(factureItemDTO1).isEqualTo(factureItemDTO2);
        factureItemDTO2.setId(2L);
        assertThat(factureItemDTO1).isNotEqualTo(factureItemDTO2);
        factureItemDTO1.setId(null);
        assertThat(factureItemDTO1).isNotEqualTo(factureItemDTO2);
    }
}
