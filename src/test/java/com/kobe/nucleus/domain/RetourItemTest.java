package com.kobe.nucleus.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kobe.nucleus.web.rest.TestUtil;

public class RetourItemTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RetourItem.class);
        RetourItem retourItem1 = new RetourItem();
        retourItem1.setId(1L);
        RetourItem retourItem2 = new RetourItem();
        retourItem2.setId(retourItem1.getId());
        assertThat(retourItem1).isEqualTo(retourItem2);
        retourItem2.setId(2L);
        assertThat(retourItem1).isNotEqualTo(retourItem2);
        retourItem1.setId(null);
        assertThat(retourItem1).isNotEqualTo(retourItem2);
    }
}
