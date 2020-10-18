package com.kobe.nucleus.service.mapper;


import com.kobe.nucleus.domain.*;
import com.kobe.nucleus.service.dto.RetourItemDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link RetourItem} and its DTO {@link RetourItemDTO}.
 */
@Mapper(componentModel = "spring", uses = {RetourFournisseurMapper.class, CommandeItemMapper.class})
public interface RetourItemMapper extends EntityMapper<RetourItemDTO, RetourItem> {

    @Mapping(source = "retourFournisseur.id", target = "retourFournisseurId")
    @Mapping(source = "commandeItem.id", target = "commandeItemId")
    RetourItemDTO toDto(RetourItem retourItem);

    @Mapping(source = "retourFournisseurId", target = "retourFournisseur")
    @Mapping(source = "commandeItemId", target = "commandeItem")
    RetourItem toEntity(RetourItemDTO retourItemDTO);

    default RetourItem fromId(Long id) {
        if (id == null) {
            return null;
        }
        RetourItem retourItem = new RetourItem();
        retourItem.setId(id);
        return retourItem;
    }
}
