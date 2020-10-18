package com.kobe.nucleus.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kobe.nucleus.web.rest.TestUtil;

public class TypeMvtCaisseTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TypeMvtCaisse.class);
        TypeMvtCaisse typeMvtCaisse1 = new TypeMvtCaisse();
        typeMvtCaisse1.setId(1L);
        TypeMvtCaisse typeMvtCaisse2 = new TypeMvtCaisse();
        typeMvtCaisse2.setId(typeMvtCaisse1.getId());
        assertThat(typeMvtCaisse1).isEqualTo(typeMvtCaisse2);
        typeMvtCaisse2.setId(2L);
        assertThat(typeMvtCaisse1).isNotEqualTo(typeMvtCaisse2);
        typeMvtCaisse1.setId(null);
        assertThat(typeMvtCaisse1).isNotEqualTo(typeMvtCaisse2);
    }
}
