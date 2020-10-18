package com.kobe.nucleus.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class FormProduitMapperTest {

    private FormProduitMapper formProduitMapper;

    @BeforeEach
    public void setUp() {
        formProduitMapper = new FormProduitMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(formProduitMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(formProduitMapper.fromId(null)).isNull();
    }
}
