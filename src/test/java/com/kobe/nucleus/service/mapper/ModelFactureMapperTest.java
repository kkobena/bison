package com.kobe.nucleus.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ModelFactureMapperTest {

    private ModelFactureMapper modelFactureMapper;

    @BeforeEach
    public void setUp() {
        modelFactureMapper = new ModelFactureMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(modelFactureMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(modelFactureMapper.fromId(null)).isNull();
    }
}
