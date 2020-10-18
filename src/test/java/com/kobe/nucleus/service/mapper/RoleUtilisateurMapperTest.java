package com.kobe.nucleus.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class RoleUtilisateurMapperTest {

    private RoleUtilisateurMapper roleUtilisateurMapper;

    @BeforeEach
    public void setUp() {
        roleUtilisateurMapper = new RoleUtilisateurMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(roleUtilisateurMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(roleUtilisateurMapper.fromId(null)).isNull();
    }
}
