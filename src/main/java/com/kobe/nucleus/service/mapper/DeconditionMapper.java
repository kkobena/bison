package com.kobe.nucleus.service.mapper;


import com.kobe.nucleus.domain.*;
import com.kobe.nucleus.service.dto.DeconditionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Decondition} and its DTO {@link DeconditionDTO}.
 */
@Mapper(componentModel = "spring", uses = {MagasinMapper.class, UtilisateurMapper.class})
public interface DeconditionMapper extends EntityMapper<DeconditionDTO, Decondition> {

    @Mapping(source = "magasin.id", target = "magasinId")
    @Mapping(source = "magasin.nomCourt", target = "magasinNomCourt")
    @Mapping(source = "createdBy.id", target = "createdById")
    @Mapping(source = "createdBy.firstName", target = "createdByFirstName")
    DeconditionDTO toDto(Decondition decondition);

    @Mapping(source = "magasinId", target = "magasin")
    @Mapping(source = "createdById", target = "createdBy")
    Decondition toEntity(DeconditionDTO deconditionDTO);

    default Decondition fromId(Long id) {
        if (id == null) {
            return null;
        }
        Decondition decondition = new Decondition();
        decondition.setId(id);
        return decondition;
    }
}
