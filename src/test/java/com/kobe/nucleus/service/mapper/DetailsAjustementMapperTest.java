package com.kobe.nucleus.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DetailsAjustementMapperTest {

    private DetailsAjustementMapper detailsAjustementMapper;

    @BeforeEach
    public void setUp() {
        detailsAjustementMapper = new DetailsAjustementMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(detailsAjustementMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(detailsAjustementMapper.fromId(null)).isNull();
    }
}
