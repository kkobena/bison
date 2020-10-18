package com.kobe.nucleus.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kobe.nucleus.web.rest.TestUtil;

public class GroupeFournisseurDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(GroupeFournisseurDTO.class);
        GroupeFournisseurDTO groupeFournisseurDTO1 = new GroupeFournisseurDTO();
        groupeFournisseurDTO1.setId(1L);
        GroupeFournisseurDTO groupeFournisseurDTO2 = new GroupeFournisseurDTO();
        assertThat(groupeFournisseurDTO1).isNotEqualTo(groupeFournisseurDTO2);
        groupeFournisseurDTO2.setId(groupeFournisseurDTO1.getId());
        assertThat(groupeFournisseurDTO1).isEqualTo(groupeFournisseurDTO2);
        groupeFournisseurDTO2.setId(2L);
        assertThat(groupeFournisseurDTO1).isNotEqualTo(groupeFournisseurDTO2);
        groupeFournisseurDTO1.setId(null);
        assertThat(groupeFournisseurDTO1).isNotEqualTo(groupeFournisseurDTO2);
    }
}
