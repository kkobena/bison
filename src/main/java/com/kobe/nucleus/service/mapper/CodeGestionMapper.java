package com.kobe.nucleus.service.mapper;


import com.kobe.nucleus.domain.*;
import com.kobe.nucleus.service.dto.CodeGestionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CodeGestion} and its DTO {@link CodeGestionDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CodeGestionMapper extends EntityMapper<CodeGestionDTO, CodeGestion> {



    default CodeGestion fromId(Long id) {
        if (id == null) {
            return null;
        }
        CodeGestion codeGestion = new CodeGestion();
        codeGestion.setId(id);
        return codeGestion;
    }
}
