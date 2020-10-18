package com.kobe.nucleus.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kobe.nucleus.web.rest.TestUtil;

public class RoleUtilisateurTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RoleUtilisateur.class);
        RoleUtilisateur roleUtilisateur1 = new RoleUtilisateur();
        roleUtilisateur1.setId(1L);
        RoleUtilisateur roleUtilisateur2 = new RoleUtilisateur();
        roleUtilisateur2.setId(roleUtilisateur1.getId());
        assertThat(roleUtilisateur1).isEqualTo(roleUtilisateur2);
        roleUtilisateur2.setId(2L);
        assertThat(roleUtilisateur1).isNotEqualTo(roleUtilisateur2);
        roleUtilisateur1.setId(null);
        assertThat(roleUtilisateur1).isNotEqualTo(roleUtilisateur2);
    }
}
