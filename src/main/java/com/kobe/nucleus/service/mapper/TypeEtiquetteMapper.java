package com.kobe.nucleus.service.mapper;


import com.kobe.nucleus.domain.*;
import com.kobe.nucleus.service.dto.TypeEtiquetteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link TypeEtiquette} and its DTO {@link TypeEtiquetteDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TypeEtiquetteMapper extends EntityMapper<TypeEtiquetteDTO, TypeEtiquette> {


    @Mapping(target = "produits", ignore = true)
    @Mapping(target = "removeProduit", ignore = true)
    TypeEtiquette toEntity(TypeEtiquetteDTO typeEtiquetteDTO);

    default TypeEtiquette fromId(Long id) {
        if (id == null) {
            return null;
        }
        TypeEtiquette typeEtiquette = new TypeEtiquette();
        typeEtiquette.setId(id);
        return typeEtiquette;
    }
}
