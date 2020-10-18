package com.kobe.nucleus.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kobe.nucleus.web.rest.TestUtil;

public class DetailsInventaireDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DetailsInventaireDTO.class);
        DetailsInventaireDTO detailsInventaireDTO1 = new DetailsInventaireDTO();
        detailsInventaireDTO1.setId(1L);
        DetailsInventaireDTO detailsInventaireDTO2 = new DetailsInventaireDTO();
        assertThat(detailsInventaireDTO1).isNotEqualTo(detailsInventaireDTO2);
        detailsInventaireDTO2.setId(detailsInventaireDTO1.getId());
        assertThat(detailsInventaireDTO1).isEqualTo(detailsInventaireDTO2);
        detailsInventaireDTO2.setId(2L);
        assertThat(detailsInventaireDTO1).isNotEqualTo(detailsInventaireDTO2);
        detailsInventaireDTO1.setId(null);
        assertThat(detailsInventaireDTO1).isNotEqualTo(detailsInventaireDTO2);
    }
}
