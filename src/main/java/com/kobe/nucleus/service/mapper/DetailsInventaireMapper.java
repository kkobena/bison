package com.kobe.nucleus.service.mapper;


import com.kobe.nucleus.domain.*;
import com.kobe.nucleus.service.dto.DetailsInventaireDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DetailsInventaire} and its DTO {@link DetailsInventaireDTO}.
 */
@Mapper(componentModel = "spring", uses = {InventaireMapper.class})
public interface DetailsInventaireMapper extends EntityMapper<DetailsInventaireDTO, DetailsInventaire> {

    @Mapping(source = "inventaire.id", target = "inventaireId")
    DetailsInventaireDTO toDto(DetailsInventaire detailsInventaire);

    @Mapping(source = "inventaireId", target = "inventaire")
    DetailsInventaire toEntity(DetailsInventaireDTO detailsInventaireDTO);

    default DetailsInventaire fromId(Long id) {
        if (id == null) {
            return null;
        }
        DetailsInventaire detailsInventaire = new DetailsInventaire();
        detailsInventaire.setId(id);
        return detailsInventaire;
    }
}
