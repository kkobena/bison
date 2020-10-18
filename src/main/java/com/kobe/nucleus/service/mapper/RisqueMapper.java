package com.kobe.nucleus.service.mapper;


import com.kobe.nucleus.domain.*;
import com.kobe.nucleus.service.dto.RisqueDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Risque} and its DTO {@link RisqueDTO}.
 */
@Mapper(componentModel = "spring", uses = {TypeRisqueMapper.class})
public interface RisqueMapper extends EntityMapper<RisqueDTO, Risque> {

    @Mapping(source = "typerisque.id", target = "typerisqueId")
    @Mapping(source = "typerisque.libelle", target = "typerisqueLibelle")
    RisqueDTO toDto(Risque risque);

    @Mapping(target = "tierspayants", ignore = true)
    @Mapping(target = "removeTierspayant", ignore = true)
    @Mapping(source = "typerisqueId", target = "typerisque")
    Risque toEntity(RisqueDTO risqueDTO);

    default Risque fromId(Long id) {
        if (id == null) {
            return null;
        }
        Risque risque = new Risque();
        risque.setId(id);
        return risque;
    }
}
