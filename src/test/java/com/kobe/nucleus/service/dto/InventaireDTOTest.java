package com.kobe.nucleus.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kobe.nucleus.web.rest.TestUtil;

public class InventaireDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InventaireDTO.class);
        InventaireDTO inventaireDTO1 = new InventaireDTO();
        inventaireDTO1.setId(1L);
        InventaireDTO inventaireDTO2 = new InventaireDTO();
        assertThat(inventaireDTO1).isNotEqualTo(inventaireDTO2);
        inventaireDTO2.setId(inventaireDTO1.getId());
        assertThat(inventaireDTO1).isEqualTo(inventaireDTO2);
        inventaireDTO2.setId(2L);
        assertThat(inventaireDTO1).isNotEqualTo(inventaireDTO2);
        inventaireDTO1.setId(null);
        assertThat(inventaireDTO1).isNotEqualTo(inventaireDTO2);
    }
}
