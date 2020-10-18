package com.kobe.nucleus.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kobe.nucleus.web.rest.TestUtil;

public class TypeRisqueDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TypeRisqueDTO.class);
        TypeRisqueDTO typeRisqueDTO1 = new TypeRisqueDTO();
        typeRisqueDTO1.setId(1L);
        TypeRisqueDTO typeRisqueDTO2 = new TypeRisqueDTO();
        assertThat(typeRisqueDTO1).isNotEqualTo(typeRisqueDTO2);
        typeRisqueDTO2.setId(typeRisqueDTO1.getId());
        assertThat(typeRisqueDTO1).isEqualTo(typeRisqueDTO2);
        typeRisqueDTO2.setId(2L);
        assertThat(typeRisqueDTO1).isNotEqualTo(typeRisqueDTO2);
        typeRisqueDTO1.setId(null);
        assertThat(typeRisqueDTO1).isNotEqualTo(typeRisqueDTO2);
    }
}
