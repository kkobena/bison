package com.kobe.nucleus.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CodeGestionMapperTest {

    private CodeGestionMapper codeGestionMapper;

    @BeforeEach
    public void setUp() {
        codeGestionMapper = new CodeGestionMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(codeGestionMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(codeGestionMapper.fromId(null)).isNull();
    }
}
