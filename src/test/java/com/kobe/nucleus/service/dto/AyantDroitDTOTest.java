package com.kobe.nucleus.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kobe.nucleus.web.rest.TestUtil;

public class AyantDroitDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AyantDroitDTO.class);
        AyantDroitDTO ayantDroitDTO1 = new AyantDroitDTO();
        ayantDroitDTO1.setId(1L);
        AyantDroitDTO ayantDroitDTO2 = new AyantDroitDTO();
        assertThat(ayantDroitDTO1).isNotEqualTo(ayantDroitDTO2);
        ayantDroitDTO2.setId(ayantDroitDTO1.getId());
        assertThat(ayantDroitDTO1).isEqualTo(ayantDroitDTO2);
        ayantDroitDTO2.setId(2L);
        assertThat(ayantDroitDTO1).isNotEqualTo(ayantDroitDTO2);
        ayantDroitDTO1.setId(null);
        assertThat(ayantDroitDTO1).isNotEqualTo(ayantDroitDTO2);
    }
}
