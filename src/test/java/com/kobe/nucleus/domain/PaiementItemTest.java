package com.kobe.nucleus.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kobe.nucleus.web.rest.TestUtil;

public class PaiementItemTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PaiementItem.class);
        PaiementItem paiementItem1 = new PaiementItem();
        paiementItem1.setId(1L);
        PaiementItem paiementItem2 = new PaiementItem();
        paiementItem2.setId(paiementItem1.getId());
        assertThat(paiementItem1).isEqualTo(paiementItem2);
        paiementItem2.setId(2L);
        assertThat(paiementItem1).isNotEqualTo(paiementItem2);
        paiementItem1.setId(null);
        assertThat(paiementItem1).isNotEqualTo(paiementItem2);
    }
}
