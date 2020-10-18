package com.kobe.nucleus.service.mapper;


import com.kobe.nucleus.domain.*;
import com.kobe.nucleus.service.dto.TypeInventaireDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link TypeInventaire} and its DTO {@link TypeInventaireDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TypeInventaireMapper extends EntityMapper<TypeInventaireDTO, TypeInventaire> {



    default TypeInventaire fromId(Long id) {
        if (id == null) {
            return null;
        }
        TypeInventaire typeInventaire = new TypeInventaire();
        typeInventaire.setId(id);
        return typeInventaire;
    }
}
