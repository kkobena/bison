package com.kobe.nucleus.service.mapper;


import com.kobe.nucleus.domain.*;
import com.kobe.nucleus.service.dto.StockProduitDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link StockProduit} and its DTO {@link StockProduitDTO}.
 */
@Mapper(componentModel = "spring", uses = {RayonMapper.class, ProduitMapper.class})
public interface StockProduitMapper extends EntityMapper<StockProduitDTO, StockProduit> {

    @Mapping(source = "rayon.id", target = "rayonId")
    @Mapping(source = "rayon.libelle", target = "rayonLibelle")
    @Mapping(source = "produit.id", target = "produitId")
    @Mapping(source = "produit.libelle", target = "produitLibelle")
    StockProduitDTO toDto(StockProduit stockProduit);

    @Mapping(target = "stockReports", ignore = true)
    @Mapping(target = "removeStockReport", ignore = true)
    @Mapping(target = "mvtProduits", ignore = true)
    @Mapping(target = "removeMvtProduit", ignore = true)
    @Mapping(source = "rayonId", target = "rayon")
    @Mapping(source = "produitId", target = "produit")
    StockProduit toEntity(StockProduitDTO stockProduitDTO);

    default StockProduit fromId(Long id) {
        if (id == null) {
            return null;
        }
        StockProduit stockProduit = new StockProduit();
        stockProduit.setId(id);
        return stockProduit;
    }
}
