package com.kobe.nucleus.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MotifMapperTest {

    private MotifMapper motifMapper;

    @BeforeEach
    public void setUp() {
        motifMapper = new MotifMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(motifMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(motifMapper.fromId(null)).isNull();
    }
}
