package com.kobe.nucleus.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AjustementMapperTest {

    private AjustementMapper ajustementMapper;

    @BeforeEach
    public void setUp() {
        ajustementMapper = new AjustementMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(ajustementMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(ajustementMapper.fromId(null)).isNull();
    }
}
