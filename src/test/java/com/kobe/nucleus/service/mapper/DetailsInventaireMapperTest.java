package com.kobe.nucleus.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DetailsInventaireMapperTest {

    private DetailsInventaireMapper detailsInventaireMapper;

    @BeforeEach
    public void setUp() {
        detailsInventaireMapper = new DetailsInventaireMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(detailsInventaireMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(detailsInventaireMapper.fromId(null)).isNull();
    }
}
