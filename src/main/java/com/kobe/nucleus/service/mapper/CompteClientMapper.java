package com.kobe.nucleus.service.mapper;


import com.kobe.nucleus.domain.*;
import com.kobe.nucleus.service.dto.CompteClientDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CompteClient} and its DTO {@link CompteClientDTO}.
 */
@Mapper(componentModel = "spring", uses = {ClientMapper.class, TierspayantMapper.class})
public interface CompteClientMapper extends EntityMapper<CompteClientDTO, CompteClient> {

    @Mapping(source = "client.id", target = "clientId")
    @Mapping(source = "tierspayant.id", target = "tierspayantId")
    CompteClientDTO toDto(CompteClient compteClient);

    @Mapping(target = "lignesVenteAssurences", ignore = true)
    @Mapping(target = "removeLignesVenteAssurence", ignore = true)
    @Mapping(source = "clientId", target = "client")
    @Mapping(source = "tierspayantId", target = "tierspayant")
    CompteClient toEntity(CompteClientDTO compteClientDTO);

    default CompteClient fromId(Long id) {
        if (id == null) {
            return null;
        }
        CompteClient compteClient = new CompteClient();
        compteClient.setId(id);
        return compteClient;
    }
}
