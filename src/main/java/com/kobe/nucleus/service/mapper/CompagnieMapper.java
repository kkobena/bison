package com.kobe.nucleus.service.mapper;


import com.kobe.nucleus.domain.*;
import com.kobe.nucleus.service.dto.CompagnieDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Compagnie} and its DTO {@link CompagnieDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CompagnieMapper extends EntityMapper<CompagnieDTO, Compagnie> {


    @Mapping(target = "clients", ignore = true)
    @Mapping(target = "removeClient", ignore = true)
    Compagnie toEntity(CompagnieDTO compagnieDTO);

    default Compagnie fromId(Long id) {
        if (id == null) {
            return null;
        }
        Compagnie compagnie = new Compagnie();
        compagnie.setId(id);
        return compagnie;
    }
}
