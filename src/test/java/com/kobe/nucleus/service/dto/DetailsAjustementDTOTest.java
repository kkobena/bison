package com.kobe.nucleus.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kobe.nucleus.web.rest.TestUtil;

public class DetailsAjustementDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DetailsAjustementDTO.class);
        DetailsAjustementDTO detailsAjustementDTO1 = new DetailsAjustementDTO();
        detailsAjustementDTO1.setId(1L);
        DetailsAjustementDTO detailsAjustementDTO2 = new DetailsAjustementDTO();
        assertThat(detailsAjustementDTO1).isNotEqualTo(detailsAjustementDTO2);
        detailsAjustementDTO2.setId(detailsAjustementDTO1.getId());
        assertThat(detailsAjustementDTO1).isEqualTo(detailsAjustementDTO2);
        detailsAjustementDTO2.setId(2L);
        assertThat(detailsAjustementDTO1).isNotEqualTo(detailsAjustementDTO2);
        detailsAjustementDTO1.setId(null);
        assertThat(detailsAjustementDTO1).isNotEqualTo(detailsAjustementDTO2);
    }
}
