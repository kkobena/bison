package com.kobe.nucleus.service.mapper;


import com.kobe.nucleus.domain.*;
import com.kobe.nucleus.service.dto.GammeProduitDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link GammeProduit} and its DTO {@link GammeProduitDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface GammeProduitMapper extends EntityMapper<GammeProduitDTO, GammeProduit> {


    @Mapping(target = "produits", ignore = true)
    @Mapping(target = "removeProduit", ignore = true)
    GammeProduit toEntity(GammeProduitDTO gammeProduitDTO);

    default GammeProduit fromId(Long id) {
        if (id == null) {
            return null;
        }
        GammeProduit gammeProduit = new GammeProduit();
        gammeProduit.setId(id);
        return gammeProduit;
    }
}
