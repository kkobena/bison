package com.kobe.nucleus.service.mapper;


import com.kobe.nucleus.domain.*;
import com.kobe.nucleus.service.dto.RoleUtilisateurDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link RoleUtilisateur} and its DTO {@link RoleUtilisateurDTO}.
 */
@Mapper(componentModel = "spring", uses = {UtilisateurMapper.class, MenuMapper.class})
public interface RoleUtilisateurMapper extends EntityMapper<RoleUtilisateurDTO, RoleUtilisateur> {

    @Mapping(source = "utilisateur.id", target = "utilisateurId")
    RoleUtilisateurDTO toDto(RoleUtilisateur roleUtilisateur);

    @Mapping(source = "utilisateurId", target = "utilisateur")
    @Mapping(target = "removeMenu", ignore = true)
    RoleUtilisateur toEntity(RoleUtilisateurDTO roleUtilisateurDTO);

    default RoleUtilisateur fromId(Long id) {
        if (id == null) {
            return null;
        }
        RoleUtilisateur roleUtilisateur = new RoleUtilisateur();
        roleUtilisateur.setId(id);
        return roleUtilisateur;
    }
}
