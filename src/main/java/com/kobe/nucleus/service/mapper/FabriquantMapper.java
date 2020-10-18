package com.kobe.nucleus.service.mapper;


import com.kobe.nucleus.domain.*;
import com.kobe.nucleus.service.dto.FabriquantDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Fabriquant} and its DTO {@link FabriquantDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FabriquantMapper extends EntityMapper<FabriquantDTO, Fabriquant> {


    @Mapping(target = "produits", ignore = true)
    @Mapping(target = "removeProduit", ignore = true)
    Fabriquant toEntity(FabriquantDTO fabriquantDTO);

    default Fabriquant fromId(Long id) {
        if (id == null) {
            return null;
        }
        Fabriquant fabriquant = new Fabriquant();
        fabriquant.setId(id);
        return fabriquant;
    }
}
