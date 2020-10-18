package com.kobe.nucleus.service.mapper;


import com.kobe.nucleus.domain.*;
import com.kobe.nucleus.service.dto.FamilleProduitDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link FamilleProduit} and its DTO {@link FamilleProduitDTO}.
 */
@Mapper(componentModel = "spring", uses = {CategorieProduitMapper.class})
public interface FamilleProduitMapper extends EntityMapper<FamilleProduitDTO, FamilleProduit> {

    @Mapping(source = "categorie.id", target = "categorieId")
    @Mapping(source = "categorie.libelle", target = "categorieLibelle")
    FamilleProduitDTO toDto(FamilleProduit familleProduit);

    @Mapping(target = "produits", ignore = true)
    @Mapping(target = "removeProduit", ignore = true)
    @Mapping(source = "categorieId", target = "categorie")
    FamilleProduit toEntity(FamilleProduitDTO familleProduitDTO);

    default FamilleProduit fromId(Long id) {
        if (id == null) {
            return null;
        }
        FamilleProduit familleProduit = new FamilleProduit();
        familleProduit.setId(id);
        return familleProduit;
    }
}
