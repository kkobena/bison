package com.kobe.nucleus.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kobe.nucleus.web.rest.TestUtil;

public class MvtProduitDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MvtProduitDTO.class);
        MvtProduitDTO mvtProduitDTO1 = new MvtProduitDTO();
        mvtProduitDTO1.setId(1L);
        MvtProduitDTO mvtProduitDTO2 = new MvtProduitDTO();
        assertThat(mvtProduitDTO1).isNotEqualTo(mvtProduitDTO2);
        mvtProduitDTO2.setId(mvtProduitDTO1.getId());
        assertThat(mvtProduitDTO1).isEqualTo(mvtProduitDTO2);
        mvtProduitDTO2.setId(2L);
        assertThat(mvtProduitDTO1).isNotEqualTo(mvtProduitDTO2);
        mvtProduitDTO1.setId(null);
        assertThat(mvtProduitDTO1).isNotEqualTo(mvtProduitDTO2);
    }
}
