package com.kobe.nucleus.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CompagnieMapperTest {

    private CompagnieMapper compagnieMapper;

    @BeforeEach
    public void setUp() {
        compagnieMapper = new CompagnieMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(compagnieMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(compagnieMapper.fromId(null)).isNull();
    }
}
