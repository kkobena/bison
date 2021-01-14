package com.kobe.nucleus.service.mapper;


import com.kobe.nucleus.domain.*;
import com.kobe.nucleus.service.dto.ProduitDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Produit} and its DTO {@link ProduitDTO}.
 */
@Mapper(componentModel = "spring", uses = {LaboratoireMapper.class, FormProduitMapper.class, TypeEtiquetteMapper.class, FamilleProduitMapper.class, GammeProduitMapper.class, TvaMapper.class})
public interface ProduitMapper extends EntityMapper<ProduitDTO, Produit> {

    @Mapping(source = "parent.id", target = "parentId")
    @Mapping(source = "parent.libelle", target = "parentLibelle")
    @Mapping(source = "laboratoire.id", target = "laboratoireId")
    @Mapping(source = "laboratoire.libelle", target = "laboratoireLibelle")
    @Mapping(source = "forme.id", target = "formeId")
    @Mapping(source = "forme.libelle", target = "formeLibelle")
    @Mapping(source = "typeEtyquette.id", target = "typeEtyquetteId")
    @Mapping(source = "typeEtyquette.libelle", target = "typeEtyquetteLibelle")
    @Mapping(source = "famille.id", target = "familleId")
    @Mapping(source = "famille.libelle", target = "familleLibelle")
    @Mapping(source = "gamme.id", target = "gammeId")
    @Mapping(source = "gamme.libelle", target = "gammeLibelle")
    @Mapping(source = "tva.id", target = "tvaId")
    @Mapping(source = "tva.taux", target = "tvaTaux")
    ProduitDTO toDto(Produit produit);
    @Mapping(target = "produits", ignore = true)
    @Mapping(target = "removeProduit", ignore = true)
    @Mapping(target = "commandeItems", ignore = true)
    @Mapping(target = "removeCommandeItem", ignore = true)
    @Mapping(target = "etiquettes", ignore = true)
    @Mapping(target = "removeEtiquette", ignore = true)
    @Mapping(target = "detailsAjustements", ignore = true)
    @Mapping(target = "removeDetailsAjustement", ignore = true)
    @Mapping(target = "stockProduits", ignore = true)
    @Mapping(target = "removeStockProduit", ignore = true)
    @Mapping(source = "parentId", target = "parent")
    @Mapping(source = "laboratoireId", target = "laboratoire")
    @Mapping(source = "formeId", target = "forme")
    @Mapping(source = "typeEtyquetteId", target = "typeEtyquette")
    @Mapping(source = "familleId", target = "famille")
    @Mapping(source = "gammeId", target = "gamme")
    @Mapping(source = "tvaId", target = "tva")
    Produit toEntity(ProduitDTO produitDTO);

    default Produit fromId(Long id) {
        if (id == null) {
            return null;
        }
        Produit produit = new Produit();
        produit.setId(id);
        return produit;
    }
}
