package com.kobe.nucleus.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CompteClientMapperTest {

    private CompteClientMapper compteClientMapper;

    @BeforeEach
    public void setUp() {
        compteClientMapper = new CompteClientMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(compteClientMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(compteClientMapper.fromId(null)).isNull();
    }
}
