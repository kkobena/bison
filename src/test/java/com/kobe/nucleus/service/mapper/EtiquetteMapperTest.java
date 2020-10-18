package com.kobe.nucleus.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class EtiquetteMapperTest {

    private EtiquetteMapper etiquetteMapper;

    @BeforeEach
    public void setUp() {
        etiquetteMapper = new EtiquetteMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(etiquetteMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(etiquetteMapper.fromId(null)).isNull();
    }
}
