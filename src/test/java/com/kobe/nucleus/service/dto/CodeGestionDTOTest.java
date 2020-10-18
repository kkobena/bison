package com.kobe.nucleus.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kobe.nucleus.web.rest.TestUtil;

public class CodeGestionDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CodeGestionDTO.class);
        CodeGestionDTO codeGestionDTO1 = new CodeGestionDTO();
        codeGestionDTO1.setId(1L);
        CodeGestionDTO codeGestionDTO2 = new CodeGestionDTO();
        assertThat(codeGestionDTO1).isNotEqualTo(codeGestionDTO2);
        codeGestionDTO2.setId(codeGestionDTO1.getId());
        assertThat(codeGestionDTO1).isEqualTo(codeGestionDTO2);
        codeGestionDTO2.setId(2L);
        assertThat(codeGestionDTO1).isNotEqualTo(codeGestionDTO2);
        codeGestionDTO1.setId(null);
        assertThat(codeGestionDTO1).isNotEqualTo(codeGestionDTO2);
    }
}
