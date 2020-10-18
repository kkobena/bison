package com.kobe.nucleus.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kobe.nucleus.web.rest.TestUtil;

public class MvtsProduitDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MvtsProduitDTO.class);
        MvtsProduitDTO mvtsProduitDTO1 = new MvtsProduitDTO();
        mvtsProduitDTO1.setId(1L);
        MvtsProduitDTO mvtsProduitDTO2 = new MvtsProduitDTO();
        assertThat(mvtsProduitDTO1).isNotEqualTo(mvtsProduitDTO2);
        mvtsProduitDTO2.setId(mvtsProduitDTO1.getId());
        assertThat(mvtsProduitDTO1).isEqualTo(mvtsProduitDTO2);
        mvtsProduitDTO2.setId(2L);
        assertThat(mvtsProduitDTO1).isNotEqualTo(mvtsProduitDTO2);
        mvtsProduitDTO1.setId(null);
        assertThat(mvtsProduitDTO1).isNotEqualTo(mvtsProduitDTO2);
    }
}
