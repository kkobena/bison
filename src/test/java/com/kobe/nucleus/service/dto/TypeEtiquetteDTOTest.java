package com.kobe.nucleus.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kobe.nucleus.web.rest.TestUtil;

public class TypeEtiquetteDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TypeEtiquetteDTO.class);
        TypeEtiquetteDTO typeEtiquetteDTO1 = new TypeEtiquetteDTO();
        typeEtiquetteDTO1.setId(1L);
        TypeEtiquetteDTO typeEtiquetteDTO2 = new TypeEtiquetteDTO();
        assertThat(typeEtiquetteDTO1).isNotEqualTo(typeEtiquetteDTO2);
        typeEtiquetteDTO2.setId(typeEtiquetteDTO1.getId());
        assertThat(typeEtiquetteDTO1).isEqualTo(typeEtiquetteDTO2);
        typeEtiquetteDTO2.setId(2L);
        assertThat(typeEtiquetteDTO1).isNotEqualTo(typeEtiquetteDTO2);
        typeEtiquetteDTO1.setId(null);
        assertThat(typeEtiquetteDTO1).isNotEqualTo(typeEtiquetteDTO2);
    }
}
