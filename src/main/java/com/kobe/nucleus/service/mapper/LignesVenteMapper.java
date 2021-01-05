package com.kobe.nucleus.service.mapper;


import com.kobe.nucleus.domain.*;
import com.kobe.nucleus.service.dto.LignesVenteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link LignesVente} and its DTO {@link LignesVenteDTO}.
 */
@Mapper(componentModel = "spring", uses = {VenteMapper.class, StockProduitMapper.class})
public interface LignesVenteMapper extends EntityMapper<LignesVenteDTO, LignesVente> {

    @Mapping(source = "vente.id", target = "venteId")
    @Mapping(source = "produitStock.id", target = "produitStockId")
    LignesVenteDTO toDto(LignesVente lignesVente);

    @Mapping(source = "venteId", target = "vente")
    @Mapping(source = "produitStockId", target = "produitStock")
    LignesVente toEntity(LignesVenteDTO lignesVenteDTO);

    default LignesVente fromId(Long id) {
        if (id == null) {
            return null;
        }
        LignesVente lignesVente = new LignesVente();
        lignesVente.setId(id);
        return lignesVente;
    }
}
