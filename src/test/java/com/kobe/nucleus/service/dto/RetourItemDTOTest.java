package com.kobe.nucleus.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kobe.nucleus.web.rest.TestUtil;

public class RetourItemDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RetourItemDTO.class);
        RetourItemDTO retourItemDTO1 = new RetourItemDTO();
        retourItemDTO1.setId(1L);
        RetourItemDTO retourItemDTO2 = new RetourItemDTO();
        assertThat(retourItemDTO1).isNotEqualTo(retourItemDTO2);
        retourItemDTO2.setId(retourItemDTO1.getId());
        assertThat(retourItemDTO1).isEqualTo(retourItemDTO2);
        retourItemDTO2.setId(2L);
        assertThat(retourItemDTO1).isNotEqualTo(retourItemDTO2);
        retourItemDTO1.setId(null);
        assertThat(retourItemDTO1).isNotEqualTo(retourItemDTO2);
    }
}
