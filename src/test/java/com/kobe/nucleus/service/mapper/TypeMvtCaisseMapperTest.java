package com.kobe.nucleus.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TypeMvtCaisseMapperTest {

    private TypeMvtCaisseMapper typeMvtCaisseMapper;

    @BeforeEach
    public void setUp() {
        typeMvtCaisseMapper = new TypeMvtCaisseMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(typeMvtCaisseMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(typeMvtCaisseMapper.fromId(null)).isNull();
    }
}
