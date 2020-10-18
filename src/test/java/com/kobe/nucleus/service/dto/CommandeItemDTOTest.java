package com.kobe.nucleus.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kobe.nucleus.web.rest.TestUtil;

public class CommandeItemDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CommandeItemDTO.class);
        CommandeItemDTO commandeItemDTO1 = new CommandeItemDTO();
        commandeItemDTO1.setId(1L);
        CommandeItemDTO commandeItemDTO2 = new CommandeItemDTO();
        assertThat(commandeItemDTO1).isNotEqualTo(commandeItemDTO2);
        commandeItemDTO2.setId(commandeItemDTO1.getId());
        assertThat(commandeItemDTO1).isEqualTo(commandeItemDTO2);
        commandeItemDTO2.setId(2L);
        assertThat(commandeItemDTO1).isNotEqualTo(commandeItemDTO2);
        commandeItemDTO1.setId(null);
        assertThat(commandeItemDTO1).isNotEqualTo(commandeItemDTO2);
    }
}
