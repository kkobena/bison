package com.kobe.nucleus.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class RetourFournisseurMapperTest {

    private RetourFournisseurMapper retourFournisseurMapper;

    @BeforeEach
    public void setUp() {
        retourFournisseurMapper = new RetourFournisseurMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(retourFournisseurMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(retourFournisseurMapper.fromId(null)).isNull();
    }
}
