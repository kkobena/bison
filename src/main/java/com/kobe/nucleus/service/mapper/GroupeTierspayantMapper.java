package com.kobe.nucleus.service.mapper;


import com.kobe.nucleus.domain.*;
import com.kobe.nucleus.service.dto.GroupeTierspayantDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link GroupeTierspayant} and its DTO {@link GroupeTierspayantDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface GroupeTierspayantMapper extends EntityMapper<GroupeTierspayantDTO, GroupeTierspayant> {


    @Mapping(target = "factures", ignore = true)
    @Mapping(target = "removeFacture", ignore = true)
    @Mapping(target = "tierspayants", ignore = true)
    @Mapping(target = "removeTierspayant", ignore = true)
    GroupeTierspayant toEntity(GroupeTierspayantDTO groupeTierspayantDTO);

    default GroupeTierspayant fromId(Long id) {
        if (id == null) {
            return null;
        }
        GroupeTierspayant groupeTierspayant = new GroupeTierspayant();
        groupeTierspayant.setId(id);
        return groupeTierspayant;
    }
}
