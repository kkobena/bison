package com.kobe.nucleus.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class LignesVenteAssurenceMapperTest {

    private LignesVenteAssurenceMapper lignesVenteAssurenceMapper;

    @BeforeEach
    public void setUp() {
        lignesVenteAssurenceMapper = new LignesVenteAssurenceMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(lignesVenteAssurenceMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(lignesVenteAssurenceMapper.fromId(null)).isNull();
    }
}
