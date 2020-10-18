package com.kobe.nucleus.service.mapper;


import com.kobe.nucleus.domain.*;
import com.kobe.nucleus.service.dto.TvaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Tva} and its DTO {@link TvaDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TvaMapper extends EntityMapper<TvaDTO, Tva> {


    @Mapping(target = "produits", ignore = true)
    @Mapping(target = "removeProduit", ignore = true)
    Tva toEntity(TvaDTO tvaDTO);

    default Tva fromId(Long id) {
        if (id == null) {
            return null;
        }
        Tva tva = new Tva();
        tva.setId(id);
        return tva;
    }
}
