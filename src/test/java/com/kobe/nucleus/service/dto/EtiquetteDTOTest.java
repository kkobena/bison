package com.kobe.nucleus.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kobe.nucleus.web.rest.TestUtil;

public class EtiquetteDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EtiquetteDTO.class);
        EtiquetteDTO etiquetteDTO1 = new EtiquetteDTO();
        etiquetteDTO1.setId(1L);
        EtiquetteDTO etiquetteDTO2 = new EtiquetteDTO();
        assertThat(etiquetteDTO1).isNotEqualTo(etiquetteDTO2);
        etiquetteDTO2.setId(etiquetteDTO1.getId());
        assertThat(etiquetteDTO1).isEqualTo(etiquetteDTO2);
        etiquetteDTO2.setId(2L);
        assertThat(etiquetteDTO1).isNotEqualTo(etiquetteDTO2);
        etiquetteDTO1.setId(null);
        assertThat(etiquetteDTO1).isNotEqualTo(etiquetteDTO2);
    }
}
