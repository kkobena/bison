package com.kobe.nucleus.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MagasinMapperTest {

    private MagasinMapper magasinMapper;

    @BeforeEach
    public void setUp() {
        magasinMapper = new MagasinMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(magasinMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(magasinMapper.fromId(null)).isNull();
    }
}
