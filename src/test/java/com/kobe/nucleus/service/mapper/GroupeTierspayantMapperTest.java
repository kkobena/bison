package com.kobe.nucleus.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class GroupeTierspayantMapperTest {

    private GroupeTierspayantMapper groupeTierspayantMapper;

    @BeforeEach
    public void setUp() {
        groupeTierspayantMapper = new GroupeTierspayantMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(groupeTierspayantMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(groupeTierspayantMapper.fromId(null)).isNull();
    }
}
