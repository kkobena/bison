package com.kobe.nucleus.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CategorieAyantDroitMapperTest {

    private CategorieAyantDroitMapper categorieAyantDroitMapper;

    @BeforeEach
    public void setUp() {
        categorieAyantDroitMapper = new CategorieAyantDroitMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(categorieAyantDroitMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(categorieAyantDroitMapper.fromId(null)).isNull();
    }
}
