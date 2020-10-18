package com.kobe.nucleus.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kobe.nucleus.web.rest.TestUtil;

public class TypeMvtCaisseDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TypeMvtCaisseDTO.class);
        TypeMvtCaisseDTO typeMvtCaisseDTO1 = new TypeMvtCaisseDTO();
        typeMvtCaisseDTO1.setId(1L);
        TypeMvtCaisseDTO typeMvtCaisseDTO2 = new TypeMvtCaisseDTO();
        assertThat(typeMvtCaisseDTO1).isNotEqualTo(typeMvtCaisseDTO2);
        typeMvtCaisseDTO2.setId(typeMvtCaisseDTO1.getId());
        assertThat(typeMvtCaisseDTO1).isEqualTo(typeMvtCaisseDTO2);
        typeMvtCaisseDTO2.setId(2L);
        assertThat(typeMvtCaisseDTO1).isNotEqualTo(typeMvtCaisseDTO2);
        typeMvtCaisseDTO1.setId(null);
        assertThat(typeMvtCaisseDTO1).isNotEqualTo(typeMvtCaisseDTO2);
    }
}
