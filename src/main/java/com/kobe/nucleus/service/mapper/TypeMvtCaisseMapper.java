package com.kobe.nucleus.service.mapper;


import com.kobe.nucleus.domain.*;
import com.kobe.nucleus.service.dto.TypeMvtCaisseDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link TypeMvtCaisse} and its DTO {@link TypeMvtCaisseDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TypeMvtCaisseMapper extends EntityMapper<TypeMvtCaisseDTO, TypeMvtCaisse> {



    default TypeMvtCaisse fromId(Long id) {
        if (id == null) {
            return null;
        }
        TypeMvtCaisse typeMvtCaisse = new TypeMvtCaisse();
        typeMvtCaisse.setId(id);
        return typeMvtCaisse;
    }
}
