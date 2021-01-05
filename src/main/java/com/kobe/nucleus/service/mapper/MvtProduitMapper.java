package com.kobe.nucleus.service.mapper;


import com.kobe.nucleus.domain.*;
import com.kobe.nucleus.service.dto.MvtProduitDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MvtProduit} and its DTO {@link MvtProduitDTO}.
 */
@Mapper(componentModel = "spring", uses = {MagasinMapper.class, UtilisateurMapper.class, StockProduitMapper.class})
public interface MvtProduitMapper extends EntityMapper<MvtProduitDTO, MvtProduit> {

    @Mapping(source = "magasin.id", target = "magasinId")
    @Mapping(source = "magasin.nomCourt", target = "magasinNomCourt")
    @Mapping(source = "createdBy.id", target = "createdById")
    @Mapping(source = "createdBy.firstName", target = "createdByFirstName")

    MvtProduitDTO toDto(MvtProduit mvtProduit);

    @Mapping(source = "magasinId", target = "magasin")
    @Mapping(source = "createdById", target = "createdBy")
   
    MvtProduit toEntity(MvtProduitDTO mvtProduitDTO);

    default MvtProduit fromId(Long id) {
        if (id == null) {
            return null;
        }
        MvtProduit mvtProduit = new MvtProduit();
        mvtProduit.setId(id);
        return mvtProduit;
    }
}
