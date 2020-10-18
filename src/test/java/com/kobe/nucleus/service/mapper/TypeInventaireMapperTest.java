package com.kobe.nucleus.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TypeInventaireMapperTest {

    private TypeInventaireMapper typeInventaireMapper;

    @BeforeEach
    public void setUp() {
        typeInventaireMapper = new TypeInventaireMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(typeInventaireMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(typeInventaireMapper.fromId(null)).isNull();
    }
}
