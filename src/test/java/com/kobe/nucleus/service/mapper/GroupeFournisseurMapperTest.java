package com.kobe.nucleus.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class GroupeFournisseurMapperTest {

    private GroupeFournisseurMapper groupeFournisseurMapper;

    @BeforeEach
    public void setUp() {
        groupeFournisseurMapper = new GroupeFournisseurMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(groupeFournisseurMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(groupeFournisseurMapper.fromId(null)).isNull();
    }
}
