package com.kobe.nucleus.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MvtProduitMapperTest {

    private MvtProduitMapper mvtProduitMapper;

    @BeforeEach
    public void setUp() {
        mvtProduitMapper = new MvtProduitMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(mvtProduitMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(mvtProduitMapper.fromId(null)).isNull();
    }
}
