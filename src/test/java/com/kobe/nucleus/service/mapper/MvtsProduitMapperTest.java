package com.kobe.nucleus.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MvtsProduitMapperTest {

    private MvtsProduitMapper mvtsProduitMapper;

    @BeforeEach
    public void setUp() {
        mvtsProduitMapper = new MvtsProduitMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(mvtsProduitMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(mvtsProduitMapper.fromId(null)).isNull();
    }
}
