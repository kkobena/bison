package com.kobe.nucleus.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kobe.nucleus.web.rest.TestUtil;

public class GroupeTierspayantTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GroupeTierspayant.class);
        GroupeTierspayant groupeTierspayant1 = new GroupeTierspayant();
        groupeTierspayant1.setId(1L);
        GroupeTierspayant groupeTierspayant2 = new GroupeTierspayant();
        groupeTierspayant2.setId(groupeTierspayant1.getId());
        assertThat(groupeTierspayant1).isEqualTo(groupeTierspayant2);
        groupeTierspayant2.setId(2L);
        assertThat(groupeTierspayant1).isNotEqualTo(groupeTierspayant2);
        groupeTierspayant1.setId(null);
        assertThat(groupeTierspayant1).isNotEqualTo(groupeTierspayant2);
    }
}
