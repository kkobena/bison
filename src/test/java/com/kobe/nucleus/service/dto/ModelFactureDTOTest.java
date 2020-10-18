package com.kobe.nucleus.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kobe.nucleus.web.rest.TestUtil;

public class ModelFactureDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ModelFactureDTO.class);
        ModelFactureDTO modelFactureDTO1 = new ModelFactureDTO();
        modelFactureDTO1.setId(1L);
        ModelFactureDTO modelFactureDTO2 = new ModelFactureDTO();
        assertThat(modelFactureDTO1).isNotEqualTo(modelFactureDTO2);
        modelFactureDTO2.setId(modelFactureDTO1.getId());
        assertThat(modelFactureDTO1).isEqualTo(modelFactureDTO2);
        modelFactureDTO2.setId(2L);
        assertThat(modelFactureDTO1).isNotEqualTo(modelFactureDTO2);
        modelFactureDTO1.setId(null);
        assertThat(modelFactureDTO1).isNotEqualTo(modelFactureDTO2);
    }
}
