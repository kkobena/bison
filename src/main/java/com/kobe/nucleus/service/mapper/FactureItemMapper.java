package com.kobe.nucleus.service.mapper;


import com.kobe.nucleus.domain.*;
import com.kobe.nucleus.service.dto.FactureItemDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link FactureItem} and its DTO {@link FactureItemDTO}.
 */
@Mapper(componentModel = "spring", uses = {FactureMapper.class, ClientMapper.class, AyantDroitMapper.class})
public interface FactureItemMapper extends EntityMapper<FactureItemDTO, FactureItem> {

    @Mapping(source = "facture.id", target = "factureId")
    @Mapping(source = "client.id", target = "clientId")
    @Mapping(source = "ayantDroit.id", target = "ayantDroitId")
    FactureItemDTO toDto(FactureItem factureItem);

    @Mapping(source = "factureId", target = "facture")
    @Mapping(source = "clientId", target = "client")
    @Mapping(source = "ayantDroitId", target = "ayantDroit")
    FactureItem toEntity(FactureItemDTO factureItemDTO);

    default FactureItem fromId(Long id) {
        if (id == null) {
            return null;
        }
        FactureItem factureItem = new FactureItem();
        factureItem.setId(id);
        return factureItem;
    }
}
