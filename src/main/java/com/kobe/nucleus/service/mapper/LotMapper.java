package com.kobe.nucleus.service.mapper;


import com.kobe.nucleus.domain.*;
import com.kobe.nucleus.service.dto.LotDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Lot} and its DTO {@link LotDTO}.
 */
@Mapper(componentModel = "spring", uses = {CommandeItemMapper.class})
public interface LotMapper extends EntityMapper<LotDTO, Lot> {

    @Mapping(source = "commandeItem.id", target = "commandeItemId")
    LotDTO toDto(Lot lot);

    @Mapping(source = "commandeItemId", target = "commandeItem")
    Lot toEntity(LotDTO lotDTO);

    default Lot fromId(Long id) {
        if (id == null) {
            return null;
        }
        Lot lot = new Lot();
        lot.setId(id);
        return lot;
    }
}
