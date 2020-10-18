package com.kobe.nucleus.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kobe.nucleus.web.rest.TestUtil;

public class PaiementItemDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PaiementItemDTO.class);
        PaiementItemDTO paiementItemDTO1 = new PaiementItemDTO();
        paiementItemDTO1.setId(1L);
        PaiementItemDTO paiementItemDTO2 = new PaiementItemDTO();
        assertThat(paiementItemDTO1).isNotEqualTo(paiementItemDTO2);
        paiementItemDTO2.setId(paiementItemDTO1.getId());
        assertThat(paiementItemDTO1).isEqualTo(paiementItemDTO2);
        paiementItemDTO2.setId(2L);
        assertThat(paiementItemDTO1).isNotEqualTo(paiementItemDTO2);
        paiementItemDTO1.setId(null);
        assertThat(paiementItemDTO1).isNotEqualTo(paiementItemDTO2);
    }
}
