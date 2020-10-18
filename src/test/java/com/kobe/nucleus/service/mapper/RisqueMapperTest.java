package com.kobe.nucleus.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class RisqueMapperTest {

    private RisqueMapper risqueMapper;

    @BeforeEach
    public void setUp() {
        risqueMapper = new RisqueMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(risqueMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(risqueMapper.fromId(null)).isNull();
    }
}
