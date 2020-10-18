package com.kobe.nucleus.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kobe.nucleus.web.rest.TestUtil;

public class GroupeFournisseurTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GroupeFournisseur.class);
        GroupeFournisseur groupeFournisseur1 = new GroupeFournisseur();
        groupeFournisseur1.setId(1L);
        GroupeFournisseur groupeFournisseur2 = new GroupeFournisseur();
        groupeFournisseur2.setId(groupeFournisseur1.getId());
        assertThat(groupeFournisseur1).isEqualTo(groupeFournisseur2);
        groupeFournisseur2.setId(2L);
        assertThat(groupeFournisseur1).isNotEqualTo(groupeFournisseur2);
        groupeFournisseur1.setId(null);
        assertThat(groupeFournisseur1).isNotEqualTo(groupeFournisseur2);
    }
}
