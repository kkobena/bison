package com.kobe.nucleus.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kobe.nucleus.web.rest.TestUtil;

public class CodeGestionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CodeGestion.class);
        CodeGestion codeGestion1 = new CodeGestion();
        codeGestion1.setId(1L);
        CodeGestion codeGestion2 = new CodeGestion();
        codeGestion2.setId(codeGestion1.getId());
        assertThat(codeGestion1).isEqualTo(codeGestion2);
        codeGestion2.setId(2L);
        assertThat(codeGestion1).isNotEqualTo(codeGestion2);
        codeGestion1.setId(null);
        assertThat(codeGestion1).isNotEqualTo(codeGestion2);
    }
}
