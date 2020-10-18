package com.kobe.nucleus.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AyantDroitMapperTest {

    private AyantDroitMapper ayantDroitMapper;

    @BeforeEach
    public void setUp() {
        ayantDroitMapper = new AyantDroitMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(ayantDroitMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(ayantDroitMapper.fromId(null)).isNull();
    }
}
