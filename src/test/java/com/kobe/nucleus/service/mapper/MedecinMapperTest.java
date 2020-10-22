package com.kobe.nucleus.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MedecinMapperTest {

    private MedecinMapper medecinMapper;

    @BeforeEach
    public void setUp() {
        medecinMapper = new MedecinMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(medecinMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(medecinMapper.fromId(null)).isNull();
    }
}
