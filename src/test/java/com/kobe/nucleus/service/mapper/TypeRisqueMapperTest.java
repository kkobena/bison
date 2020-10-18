package com.kobe.nucleus.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TypeRisqueMapperTest {

    private TypeRisqueMapper typeRisqueMapper;

    @BeforeEach
    public void setUp() {
        typeRisqueMapper = new TypeRisqueMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(typeRisqueMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(typeRisqueMapper.fromId(null)).isNull();
    }
}
