package com.kobe.nucleus.service.mapper;


import com.kobe.nucleus.domain.*;
import com.kobe.nucleus.service.dto.LaboratoireDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Laboratoire} and its DTO {@link LaboratoireDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface LaboratoireMapper extends EntityMapper<LaboratoireDTO, Laboratoire> {


    @Mapping(target = "produits", ignore = true)
    @Mapping(target = "removeProduit", ignore = true)
    Laboratoire toEntity(LaboratoireDTO laboratoireDTO);

    default Laboratoire fromId(Long id) {
        if (id == null) {
            return null;
        }
        Laboratoire laboratoire = new Laboratoire();
        laboratoire.setId(id);
        return laboratoire;
    }
}
