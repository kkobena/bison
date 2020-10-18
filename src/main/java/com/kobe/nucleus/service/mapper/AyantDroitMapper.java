package com.kobe.nucleus.service.mapper;


import com.kobe.nucleus.domain.*;
import com.kobe.nucleus.service.dto.AyantDroitDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AyantDroit} and its DTO {@link AyantDroitDTO}.
 */
@Mapper(componentModel = "spring", uses = {ClientMapper.class, CategorieAyantDroitMapper.class})
public interface AyantDroitMapper extends EntityMapper<AyantDroitDTO, AyantDroit> {

    @Mapping(source = "assure.id", target = "assureId")
    @Mapping(source = "assure.firstName", target = "assureFirstName")
    @Mapping(source = "categorie.id", target = "categorieId")
    @Mapping(source = "categorie.libelle", target = "categorieLibelle")
    AyantDroitDTO toDto(AyantDroit ayantDroit);

    @Mapping(target = "factureItems", ignore = true)
    @Mapping(target = "removeFactureItem", ignore = true)
    @Mapping(target = "ventes", ignore = true)
    @Mapping(target = "removeVente", ignore = true)
    @Mapping(source = "assureId", target = "assure")
    @Mapping(source = "categorieId", target = "categorie")
    AyantDroit toEntity(AyantDroitDTO ayantDroitDTO);

    default AyantDroit fromId(Long id) {
        if (id == null) {
            return null;
        }
        AyantDroit ayantDroit = new AyantDroit();
        ayantDroit.setId(id);
        return ayantDroit;
    }
}
