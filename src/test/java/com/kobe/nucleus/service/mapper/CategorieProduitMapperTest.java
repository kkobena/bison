package com.kobe.nucleus.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CategorieProduitMapperTest {

    private CategorieProduitMapper categorieProduitMapper;

    @BeforeEach
    public void setUp() {
        categorieProduitMapper = new CategorieProduitMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(categorieProduitMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(categorieProduitMapper.fromId(null)).isNull();
    }
}
