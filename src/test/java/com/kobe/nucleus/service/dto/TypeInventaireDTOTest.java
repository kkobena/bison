package com.kobe.nucleus.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kobe.nucleus.web.rest.TestUtil;

public class TypeInventaireDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TypeInventaireDTO.class);
        TypeInventaireDTO typeInventaireDTO1 = new TypeInventaireDTO();
        typeInventaireDTO1.setId(1L);
        TypeInventaireDTO typeInventaireDTO2 = new TypeInventaireDTO();
        assertThat(typeInventaireDTO1).isNotEqualTo(typeInventaireDTO2);
        typeInventaireDTO2.setId(typeInventaireDTO1.getId());
        assertThat(typeInventaireDTO1).isEqualTo(typeInventaireDTO2);
        typeInventaireDTO2.setId(2L);
        assertThat(typeInventaireDTO1).isNotEqualTo(typeInventaireDTO2);
        typeInventaireDTO1.setId(null);
        assertThat(typeInventaireDTO1).isNotEqualTo(typeInventaireDTO2);
    }
}
