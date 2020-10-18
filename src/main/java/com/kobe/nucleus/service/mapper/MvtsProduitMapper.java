package com.kobe.nucleus.service.mapper;


import com.kobe.nucleus.domain.*;
import com.kobe.nucleus.service.dto.MvtsProduitDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MvtsProduit} and its DTO {@link MvtsProduitDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MvtsProduitMapper extends EntityMapper<MvtsProduitDTO, MvtsProduit> {



    default MvtsProduit fromId(Long id) {
        if (id == null) {
            return null;
        }
        MvtsProduit mvtsProduit = new MvtsProduit();
        mvtsProduit.setId(id);
        return mvtsProduit;
    }
}
