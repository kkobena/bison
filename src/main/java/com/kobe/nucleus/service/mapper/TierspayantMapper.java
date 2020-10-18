package com.kobe.nucleus.service.mapper;


import com.kobe.nucleus.domain.*;
import com.kobe.nucleus.service.dto.TierspayantDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Tierspayant} and its DTO {@link TierspayantDTO}.
 */
@Mapper(componentModel = "spring", uses = {GroupeTierspayantMapper.class, RisqueMapper.class, ModelFactureMapper.class})
public interface TierspayantMapper extends EntityMapper<TierspayantDTO, Tierspayant> {

    @Mapping(source = "groupetp.id", target = "groupetpId")
    @Mapping(source = "groupetp.libelle", target = "groupetpLibelle")
    @Mapping(source = "risque.id", target = "risqueId")
    @Mapping(source = "risque.libelle", target = "risqueLibelle")
    @Mapping(source = "modelFacture.id", target = "modelFactureId")
    @Mapping(source = "modelFacture.libelle", target = "modelFactureLibelle")
    TierspayantDTO toDto(Tierspayant tierspayant);

    @Mapping(target = "factures", ignore = true)
    @Mapping(target = "removeFacture", ignore = true)
    @Mapping(target = "compteClients", ignore = true)
    @Mapping(target = "removeCompteClient", ignore = true)
    @Mapping(source = "groupetpId", target = "groupetp")
    @Mapping(source = "risqueId", target = "risque")
    @Mapping(source = "modelFactureId", target = "modelFacture")
    Tierspayant toEntity(TierspayantDTO tierspayantDTO);

    default Tierspayant fromId(Long id) {
        if (id == null) {
            return null;
        }
        Tierspayant tierspayant = new Tierspayant();
        tierspayant.setId(id);
        return tierspayant;
    }
}
