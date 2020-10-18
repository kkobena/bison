package com.kobe.nucleus.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class RetourItemMapperTest {

    private RetourItemMapper retourItemMapper;

    @BeforeEach
    public void setUp() {
        retourItemMapper = new RetourItemMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(retourItemMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(retourItemMapper.fromId(null)).isNull();
    }
}
