package com.kobe.nucleus.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kobe.nucleus.web.rest.TestUtil;

public class GroupeTierspayantDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(GroupeTierspayantDTO.class);
        GroupeTierspayantDTO groupeTierspayantDTO1 = new GroupeTierspayantDTO();
        groupeTierspayantDTO1.setId(1L);
        GroupeTierspayantDTO groupeTierspayantDTO2 = new GroupeTierspayantDTO();
        assertThat(groupeTierspayantDTO1).isNotEqualTo(groupeTierspayantDTO2);
        groupeTierspayantDTO2.setId(groupeTierspayantDTO1.getId());
        assertThat(groupeTierspayantDTO1).isEqualTo(groupeTierspayantDTO2);
        groupeTierspayantDTO2.setId(2L);
        assertThat(groupeTierspayantDTO1).isNotEqualTo(groupeTierspayantDTO2);
        groupeTierspayantDTO1.setId(null);
        assertThat(groupeTierspayantDTO1).isNotEqualTo(groupeTierspayantDTO2);
    }
}
