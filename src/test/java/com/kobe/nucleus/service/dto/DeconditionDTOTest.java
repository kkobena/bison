package com.kobe.nucleus.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kobe.nucleus.web.rest.TestUtil;

public class DeconditionDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DeconditionDTO.class);
        DeconditionDTO deconditionDTO1 = new DeconditionDTO();
        deconditionDTO1.setId(1L);
        DeconditionDTO deconditionDTO2 = new DeconditionDTO();
        assertThat(deconditionDTO1).isNotEqualTo(deconditionDTO2);
        deconditionDTO2.setId(deconditionDTO1.getId());
        assertThat(deconditionDTO1).isEqualTo(deconditionDTO2);
        deconditionDTO2.setId(2L);
        assertThat(deconditionDTO1).isNotEqualTo(deconditionDTO2);
        deconditionDTO1.setId(null);
        assertThat(deconditionDTO1).isNotEqualTo(deconditionDTO2);
    }
}
