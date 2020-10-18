package com.kobe.nucleus.service.mapper;


import com.kobe.nucleus.domain.*;
import com.kobe.nucleus.service.dto.PermissionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Permission} and its DTO {@link PermissionDTO}.
 */
@Mapper(componentModel = "spring", uses = {UtilisateurMapper.class, MenuMapper.class})
public interface PermissionMapper extends EntityMapper<PermissionDTO, Permission> {

    @Mapping(source = "createdBy.id", target = "createdById")
    @Mapping(source = "createdBy.firstName", target = "createdByFirstName")
    @Mapping(source = "menu.id", target = "menuId")
    @Mapping(source = "menu.libelle", target = "menuLibelle")
    PermissionDTO toDto(Permission permission);

    @Mapping(source = "createdById", target = "createdBy")
    @Mapping(source = "menuId", target = "menu")
    Permission toEntity(PermissionDTO permissionDTO);

    default Permission fromId(Long id) {
        if (id == null) {
            return null;
        }
        Permission permission = new Permission();
        permission.setId(id);
        return permission;
    }
}
