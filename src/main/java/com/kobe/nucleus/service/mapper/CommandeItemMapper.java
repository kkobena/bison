package com.kobe.nucleus.service.mapper;


import com.kobe.nucleus.domain.*;
import com.kobe.nucleus.service.dto.CommandeItemDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CommandeItem} and its DTO {@link CommandeItemDTO}.
 */
@Mapper(componentModel = "spring", uses = {CommandeMapper.class, ProduitMapper.class})
public interface CommandeItemMapper extends EntityMapper<CommandeItemDTO, CommandeItem> {

    @Mapping(source = "commande.id", target = "commandeId")
    @Mapping(source = "produit.id", target = "produitId")
    @Mapping(source = "produit.libelle", target = "produitLibelle")
    CommandeItemDTO toDto(CommandeItem commandeItem);

    @Mapping(target = "retourItems", ignore = true)
    @Mapping(target = "removeRetourItem", ignore = true)
    @Mapping(target = "lots", ignore = true)
    @Mapping(target = "removeLot", ignore = true)
    @Mapping(source = "commandeId", target = "commande")
    @Mapping(source = "produitId", target = "produit")
    CommandeItem toEntity(CommandeItemDTO commandeItemDTO);

    default CommandeItem fromId(Long id) {
        if (id == null) {
            return null;
        }
        CommandeItem commandeItem = new CommandeItem();
        commandeItem.setId(id);
        return commandeItem;
    }
}
