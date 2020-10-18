package com.kobe.nucleus.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class FabriquantMapperTest {

    private FabriquantMapper fabriquantMapper;

    @BeforeEach
    public void setUp() {
        fabriquantMapper = new FabriquantMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(fabriquantMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(fabriquantMapper.fromId(null)).isNull();
    }
}
