package com.kobe.nucleus.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class GammeProduitMapperTest {

    private GammeProduitMapper gammeProduitMapper;

    @BeforeEach
    public void setUp() {
        gammeProduitMapper = new GammeProduitMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(gammeProduitMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(gammeProduitMapper.fromId(null)).isNull();
    }
}
