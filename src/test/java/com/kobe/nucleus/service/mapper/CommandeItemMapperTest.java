package com.kobe.nucleus.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CommandeItemMapperTest {

    private CommandeItemMapper commandeItemMapper;

    @BeforeEach
    public void setUp() {
        commandeItemMapper = new CommandeItemMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(commandeItemMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(commandeItemMapper.fromId(null)).isNull();
    }
}
