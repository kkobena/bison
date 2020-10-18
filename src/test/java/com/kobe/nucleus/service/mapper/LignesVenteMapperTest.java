package com.kobe.nucleus.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class LignesVenteMapperTest {

    private LignesVenteMapper lignesVenteMapper;

    @BeforeEach
    public void setUp() {
        lignesVenteMapper = new LignesVenteMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(lignesVenteMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(lignesVenteMapper.fromId(null)).isNull();
    }
}
