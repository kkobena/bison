package com.kobe.nucleus.service.mapper;


import com.kobe.nucleus.domain.*;
import com.kobe.nucleus.service.dto.AjustementDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Ajustement} and its DTO {@link AjustementDTO}.
 */
@Mapper(componentModel = "spring", uses = {MagasinMapper.class, UserMapper.class})
public interface AjustementMapper extends EntityMapper<AjustementDTO, Ajustement> {

    @Mapping(source = "magasin.id", target = "magasinId")
    @Mapping(source = "magasin.nomCourt", target = "magasinNomCourt")
    @Mapping(source = "createdBy.id", target = "createdById")
    @Mapping(source = "createdBy.firstName", target = "createdByFirstName")
    AjustementDTO toDto(Ajustement ajustement);

    @Mapping(source = "magasinId", target = "magasin")
    @Mapping(source = "createdById", target = "createdBy")
    Ajustement toEntity(AjustementDTO ajustementDTO);

    default Ajustement fromId(Long id) {
        if (id == null) {
            return null;
        }
        Ajustement ajustement = new Ajustement();
        ajustement.setId(id);
        return ajustement;
    }
}
