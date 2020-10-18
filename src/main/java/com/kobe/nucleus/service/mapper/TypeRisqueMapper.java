package com.kobe.nucleus.service.mapper;


import com.kobe.nucleus.domain.*;
import com.kobe.nucleus.service.dto.TypeRisqueDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link TypeRisque} and its DTO {@link TypeRisqueDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TypeRisqueMapper extends EntityMapper<TypeRisqueDTO, TypeRisque> {

    TypeRisque toEntity(TypeRisqueDTO typeRisqueDTO);

    default TypeRisque fromId(Long id) {
        if (id == null) {
            return null;
        }
        TypeRisque typeRisque = new TypeRisque();
        typeRisque.setId(id);
        return typeRisque;
    }
}
