package com.kobe.nucleus.service.mapper;


import com.kobe.nucleus.domain.*;
import com.kobe.nucleus.service.dto.GroupeFournisseurDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link GroupeFournisseur} and its DTO {@link GroupeFournisseurDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface GroupeFournisseurMapper extends EntityMapper<GroupeFournisseurDTO, GroupeFournisseur> {



    default GroupeFournisseur fromId(Long id) {
        if (id == null) {
            return null;
        }
        GroupeFournisseur groupeFournisseur = new GroupeFournisseur();
        groupeFournisseur.setId(id);
        return groupeFournisseur;
    }
}
