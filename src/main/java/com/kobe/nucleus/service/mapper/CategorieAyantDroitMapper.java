package com.kobe.nucleus.service.mapper;


import com.kobe.nucleus.domain.*;
import com.kobe.nucleus.service.dto.CategorieAyantDroitDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CategorieAyantDroit} and its DTO {@link CategorieAyantDroitDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CategorieAyantDroitMapper extends EntityMapper<CategorieAyantDroitDTO, CategorieAyantDroit> {


    @Mapping(target = "ayantDroits", ignore = true)
    @Mapping(target = "removeAyantDroit", ignore = true)
    CategorieAyantDroit toEntity(CategorieAyantDroitDTO categorieAyantDroitDTO);

    default CategorieAyantDroit fromId(Long id) {
        if (id == null) {
            return null;
        }
        CategorieAyantDroit categorieAyantDroit = new CategorieAyantDroit();
        categorieAyantDroit.setId(id);
        return categorieAyantDroit;
    }
}
