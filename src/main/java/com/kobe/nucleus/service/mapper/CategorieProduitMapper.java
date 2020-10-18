package com.kobe.nucleus.service.mapper;


import com.kobe.nucleus.domain.*;
import com.kobe.nucleus.service.dto.CategorieProduitDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CategorieProduit} and its DTO {@link CategorieProduitDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CategorieProduitMapper extends EntityMapper<CategorieProduitDTO, CategorieProduit> {


    @Mapping(target = "familleProduits", ignore = true)
    @Mapping(target = "removeFamilleProduit", ignore = true)
    CategorieProduit toEntity(CategorieProduitDTO categorieProduitDTO);

    default CategorieProduit fromId(Long id) {
        if (id == null) {
            return null;
        }
        CategorieProduit categorieProduit = new CategorieProduit();
        categorieProduit.setId(id);
        return categorieProduit;
    }
}
