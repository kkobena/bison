package com.kobe.nucleus.service.mapper;


import com.kobe.nucleus.domain.*;
import com.kobe.nucleus.service.dto.FournisseurProduitDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link FournisseurProduit} and its DTO {@link FournisseurProduitDTO}.
 */
@Mapper(componentModel = "spring", uses = {ProduitMapper.class, FournisseurMapper.class})
public interface FournisseurProduitMapper extends EntityMapper<FournisseurProduitDTO, FournisseurProduit> {

    @Mapping(source = "produit.id", target = "produitId")
    @Mapping(source = "produit.libelle", target = "produitLibelle")
    @Mapping(source = "fournisseur.id", target = "fournisseurId")
    @Mapping(source = "fournisseur.libelle", target = "fournisseurLibelle")
    FournisseurProduitDTO toDto(FournisseurProduit fournisseurProduit);

    @Mapping(source = "produitId", target = "produit")
    @Mapping(source = "fournisseurId", target = "fournisseur")
    FournisseurProduit toEntity(FournisseurProduitDTO fournisseurProduitDTO);

    default FournisseurProduit fromId(Long id) {
        if (id == null) {
            return null;
        }
        FournisseurProduit fournisseurProduit = new FournisseurProduit();
        fournisseurProduit.setId(id);
        return fournisseurProduit;
    }
}
