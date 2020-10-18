package com.kobe.nucleus.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class FactureItemMapperTest {

    private FactureItemMapper factureItemMapper;

    @BeforeEach
    public void setUp() {
        factureItemMapper = new FactureItemMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(factureItemMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(factureItemMapper.fromId(null)).isNull();
    }
}
