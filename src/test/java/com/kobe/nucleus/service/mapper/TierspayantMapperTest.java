package com.kobe.nucleus.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TierspayantMapperTest {

    private TierspayantMapper tierspayantMapper;

    @BeforeEach
    public void setUp() {
        tierspayantMapper = new TierspayantMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(tierspayantMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(tierspayantMapper.fromId(null)).isNull();
    }
}
