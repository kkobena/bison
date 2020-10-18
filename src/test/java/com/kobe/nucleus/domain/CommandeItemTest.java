package com.kobe.nucleus.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kobe.nucleus.web.rest.TestUtil;

public class CommandeItemTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CommandeItem.class);
        CommandeItem commandeItem1 = new CommandeItem();
        commandeItem1.setId(1L);
        CommandeItem commandeItem2 = new CommandeItem();
        commandeItem2.setId(commandeItem1.getId());
        assertThat(commandeItem1).isEqualTo(commandeItem2);
        commandeItem2.setId(2L);
        assertThat(commandeItem1).isNotEqualTo(commandeItem2);
        commandeItem1.setId(null);
        assertThat(commandeItem1).isNotEqualTo(commandeItem2);
    }
}
