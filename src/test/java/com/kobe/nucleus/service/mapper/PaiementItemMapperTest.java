package com.kobe.nucleus.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PaiementItemMapperTest {

    private PaiementItemMapper paiementItemMapper;

    @BeforeEach
    public void setUp() {
        paiementItemMapper = new PaiementItemMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(paiementItemMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(paiementItemMapper.fromId(null)).isNull();
    }
}
