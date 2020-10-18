package com.kobe.nucleus.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kobe.nucleus.web.rest.TestUtil;

public class ModelFactureTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ModelFacture.class);
        ModelFacture modelFacture1 = new ModelFacture();
        modelFacture1.setId(1L);
        ModelFacture modelFacture2 = new ModelFacture();
        modelFacture2.setId(modelFacture1.getId());
        assertThat(modelFacture1).isEqualTo(modelFacture2);
        modelFacture2.setId(2L);
        assertThat(modelFacture1).isNotEqualTo(modelFacture2);
        modelFacture1.setId(null);
        assertThat(modelFacture1).isNotEqualTo(modelFacture2);
    }
}
