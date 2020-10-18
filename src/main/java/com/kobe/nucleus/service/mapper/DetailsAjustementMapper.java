package com.kobe.nucleus.service.mapper;


import com.kobe.nucleus.domain.*;
import com.kobe.nucleus.service.dto.DetailsAjustementDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DetailsAjustement} and its DTO {@link DetailsAjustementDTO}.
 */
@Mapper(componentModel = "spring", uses = {ProduitMapper.class})
public interface DetailsAjustementMapper extends EntityMapper<DetailsAjustementDTO, DetailsAjustement> {

    @Mapping(source = "produit.id", target = "produitId")
    @Mapping(source = "produit.libelle", target = "produitLibelle")
    DetailsAjustementDTO toDto(DetailsAjustement detailsAjustement);

    @Mapping(source = "produitId", target = "produit")
    DetailsAjustement toEntity(DetailsAjustementDTO detailsAjustementDTO);

    default DetailsAjustement fromId(Long id) {
        if (id == null) {
            return null;
        }
        DetailsAjustement detailsAjustement = new DetailsAjustement();
        detailsAjustement.setId(id);
        return detailsAjustement;
    }
}
