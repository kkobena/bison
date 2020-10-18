package com.kobe.nucleus.service.mapper;


import com.kobe.nucleus.domain.*;
import com.kobe.nucleus.service.dto.ModelFactureDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ModelFacture} and its DTO {@link ModelFactureDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ModelFactureMapper extends EntityMapper<ModelFactureDTO, ModelFacture> {


    @Mapping(target = "tierspayants", ignore = true)
    @Mapping(target = "removeTierspayant", ignore = true)
    ModelFacture toEntity(ModelFactureDTO modelFactureDTO);

    default ModelFacture fromId(Long id) {
        if (id == null) {
            return null;
        }
        ModelFacture modelFacture = new ModelFacture();
        modelFacture.setId(id);
        return modelFacture;
    }
}
