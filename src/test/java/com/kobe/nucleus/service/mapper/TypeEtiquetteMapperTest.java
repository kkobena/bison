package com.kobe.nucleus.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TypeEtiquetteMapperTest {

    private TypeEtiquetteMapper typeEtiquetteMapper;

    @BeforeEach
    public void setUp() {
        typeEtiquetteMapper = new TypeEtiquetteMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(typeEtiquetteMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(typeEtiquetteMapper.fromId(null)).isNull();
    }
}
