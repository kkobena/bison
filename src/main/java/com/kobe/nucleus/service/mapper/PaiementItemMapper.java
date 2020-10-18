package com.kobe.nucleus.service.mapper;


import com.kobe.nucleus.domain.*;
import com.kobe.nucleus.service.dto.PaiementItemDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link PaiementItem} and its DTO {@link PaiementItemDTO}.
 */
@Mapper(componentModel = "spring", uses = {PaiementMapper.class})
public interface PaiementItemMapper extends EntityMapper<PaiementItemDTO, PaiementItem> {

    @Mapping(source = "paiement.id", target = "paiementId")
    PaiementItemDTO toDto(PaiementItem paiementItem);

    @Mapping(source = "paiementId", target = "paiement")
    PaiementItem toEntity(PaiementItemDTO paiementItemDTO);

    default PaiementItem fromId(Long id) {
        if (id == null) {
            return null;
        }
        PaiementItem paiementItem = new PaiementItem();
        paiementItem.setId(id);
        return paiementItem;
    }
}
