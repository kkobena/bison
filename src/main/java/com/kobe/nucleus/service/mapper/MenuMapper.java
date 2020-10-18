package com.kobe.nucleus.service.mapper;


import com.kobe.nucleus.domain.*;
import com.kobe.nucleus.service.dto.MenuDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Menu} and its DTO {@link MenuDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MenuMapper extends EntityMapper<MenuDTO, Menu> {

    @Mapping(source = "parent.id", target = "parentId")
    @Mapping(source = "parent.libelle", target = "parentLibelle")
    MenuDTO toDto(Menu menu);

    @Mapping(target = "menus", ignore = true)
    @Mapping(target = "removeMenu", ignore = true)
    @Mapping(target = "permissions", ignore = true)
    @Mapping(target = "removePermission", ignore = true)
    @Mapping(source = "parentId", target = "parent")
    @Mapping(target = "roleUtilisateurs", ignore = true)
    @Mapping(target = "removeRoleUtilisateur", ignore = true)
    Menu toEntity(MenuDTO menuDTO);

    default Menu fromId(Long id) {
        if (id == null) {
            return null;
        }
        Menu menu = new Menu();
        menu.setId(id);
        return menu;
    }
}
