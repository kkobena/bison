package com.kobe.nucleus.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kobe.nucleus.web.rest.TestUtil;

public class TierspayantDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TierspayantDTO.class);
        TierspayantDTO tierspayantDTO1 = new TierspayantDTO();
        tierspayantDTO1.setId(1L);
        TierspayantDTO tierspayantDTO2 = new TierspayantDTO();
        assertThat(tierspayantDTO1).isNotEqualTo(tierspayantDTO2);
        tierspayantDTO2.setId(tierspayantDTO1.getId());
        assertThat(tierspayantDTO1).isEqualTo(tierspayantDTO2);
        tierspayantDTO2.setId(2L);
        assertThat(tierspayantDTO1).isNotEqualTo(tierspayantDTO2);
        tierspayantDTO1.setId(null);
        assertThat(tierspayantDTO1).isNotEqualTo(tierspayantDTO2);
    }
}
