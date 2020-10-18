package com.kobe.nucleus.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class InventaireMapperTest {

    private InventaireMapper inventaireMapper;

    @BeforeEach
    public void setUp() {
        inventaireMapper = new InventaireMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(inventaireMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(inventaireMapper.fromId(null)).isNull();
    }
}
