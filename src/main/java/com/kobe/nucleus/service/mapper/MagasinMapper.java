package com.kobe.nucleus.service.mapper;


import com.kobe.nucleus.domain.*;
import com.kobe.nucleus.service.dto.MagasinDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Magasin} and its DTO {@link MagasinDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface MagasinMapper extends EntityMapper<MagasinDTO, Magasin> {

    @Mapping(source = "magasin.id", target = "magasinId")
    @Mapping(source = "magasin.nomCourt", target = "magasinNomCourt")
    @Mapping(source = "manager.id", target = "managerId")
    @Mapping(source = "manager.firstName", target = "managerFirstName")
    MagasinDTO toDto(Magasin magasin);

    

    @Mapping(target = "rayons", ignore = true)
    @Mapping(target = "removeRayon", ignore = true)


    @Mapping(target = "utilisateurs", ignore = true)
    @Mapping(target = "removeUtilisateur", ignore = true)
    @Mapping(target = "magasins", ignore = true)
    @Mapping(target = "removeMagasin", ignore = true)
    @Mapping(source = "magasinId", target = "magasin")
    @Mapping(source = "managerId", target = "manager")
    Magasin toEntity(MagasinDTO magasinDTO);

    default Magasin fromId(Long id) {
        if (id == null) {
            return null;
        }
        Magasin magasin = new Magasin();
        magasin.setId(id);
        return magasin;
    }
}
