package com.kobe.nucleus.service.mapper;


import com.kobe.nucleus.domain.*;
import com.kobe.nucleus.service.dto.RayonDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Rayon} and its DTO {@link RayonDTO}.
 */
@Mapper(componentModel = "spring", uses = {MagasinMapper.class})
public interface RayonMapper extends EntityMapper<RayonDTO, Rayon> {

    @Mapping(source = "magasin.id", target = "magasinId")
    @Mapping(source = "magasin.nomCourt", target = "magasinNomCourt")
    RayonDTO toDto(Rayon rayon);

    @Mapping(target = "stockProduits", ignore = true)
    @Mapping(target = "removeStockProduit", ignore = true)
    @Mapping(source = "magasinId", target = "magasin")
    Rayon toEntity(RayonDTO rayonDTO);

    default Rayon fromId(Long id) {
        if (id == null) {
            return null;
        }
        Rayon rayon = new Rayon();
        rayon.setId(id);
        return rayon;
    }
}
