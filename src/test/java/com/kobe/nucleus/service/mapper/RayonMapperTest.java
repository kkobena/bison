package com.kobe.nucleus.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class RayonMapperTest {

    private RayonMapper rayonMapper;

    @BeforeEach
    public void setUp() {
        rayonMapper = new RayonMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(rayonMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(rayonMapper.fromId(null)).isNull();
    }
}
