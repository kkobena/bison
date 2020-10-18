package com.kobe.nucleus.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kobe.nucleus.web.rest.TestUtil;

public class RoleUtilisateurDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RoleUtilisateurDTO.class);
        RoleUtilisateurDTO roleUtilisateurDTO1 = new RoleUtilisateurDTO();
        roleUtilisateurDTO1.setId(1L);
        RoleUtilisateurDTO roleUtilisateurDTO2 = new RoleUtilisateurDTO();
        assertThat(roleUtilisateurDTO1).isNotEqualTo(roleUtilisateurDTO2);
        roleUtilisateurDTO2.setId(roleUtilisateurDTO1.getId());
        assertThat(roleUtilisateurDTO1).isEqualTo(roleUtilisateurDTO2);
        roleUtilisateurDTO2.setId(2L);
        assertThat(roleUtilisateurDTO1).isNotEqualTo(roleUtilisateurDTO2);
        roleUtilisateurDTO1.setId(null);
        assertThat(roleUtilisateurDTO1).isNotEqualTo(roleUtilisateurDTO2);
    }
}
