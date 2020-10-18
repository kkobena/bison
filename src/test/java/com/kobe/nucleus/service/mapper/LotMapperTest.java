package com.kobe.nucleus.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class LotMapperTest {

    private LotMapper lotMapper;

    @BeforeEach
    public void setUp() {
        lotMapper = new LotMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(lotMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(lotMapper.fromId(null)).isNull();
    }
}
