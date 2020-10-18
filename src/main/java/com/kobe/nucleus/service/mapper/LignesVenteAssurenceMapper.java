package com.kobe.nucleus.service.mapper;


import com.kobe.nucleus.domain.*;
import com.kobe.nucleus.service.dto.LignesVenteAssurenceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link LignesVenteAssurence} and its DTO {@link LignesVenteAssurenceDTO}.
 */
@Mapper(componentModel = "spring", uses = {VenteMapper.class, CompteClientMapper.class})
public interface LignesVenteAssurenceMapper extends EntityMapper<LignesVenteAssurenceDTO, LignesVenteAssurence> {

    @Mapping(source = "vente.id", target = "venteId")
    @Mapping(source = "compteClient.id", target = "compteClientId")
    LignesVenteAssurenceDTO toDto(LignesVenteAssurence lignesVenteAssurence);

    @Mapping(source = "venteId", target = "vente")
    @Mapping(source = "compteClientId", target = "compteClient")
    LignesVenteAssurence toEntity(LignesVenteAssurenceDTO lignesVenteAssurenceDTO);

    default LignesVenteAssurence fromId(Long id) {
        if (id == null) {
            return null;
        }
        LignesVenteAssurence lignesVenteAssurence = new LignesVenteAssurence();
        lignesVenteAssurence.setId(id);
        return lignesVenteAssurence;
    }
}
