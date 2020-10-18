package com.kobe.nucleus.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DeconditionMapperTest {

    private DeconditionMapper deconditionMapper;

    @BeforeEach
    public void setUp() {
        deconditionMapper = new DeconditionMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(deconditionMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(deconditionMapper.fromId(null)).isNull();
    }
}
