package com.kobe.nucleus.service.mapper;


import com.kobe.nucleus.domain.*;
import com.kobe.nucleus.service.dto.RemiseDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Remise} and its DTO {@link RemiseDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RemiseMapper extends EntityMapper<RemiseDTO, Remise> {


    @Mapping(target = "ventes", ignore = true)
    @Mapping(target = "removeVente", ignore = true)
    Remise toEntity(RemiseDTO remiseDTO);

    default Remise fromId(Long id) {
        if (id == null) {
            return null;
        }
        Remise remise = new Remise();
        remise.setId(id);
        return remise;
    }
}
