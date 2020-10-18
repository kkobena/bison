package com.kobe.nucleus.service.mapper;


import com.kobe.nucleus.domain.*;
import com.kobe.nucleus.service.dto.ModePaiementDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ModePaiement} and its DTO {@link ModePaiementDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ModePaiementMapper extends EntityMapper<ModePaiementDTO, ModePaiement> {


    @Mapping(target = "ventes", ignore = true)
    @Mapping(target = "removeVente", ignore = true)
    ModePaiement toEntity(ModePaiementDTO modePaiementDTO);

    default ModePaiement fromId(Long id) {
        if (id == null) {
            return null;
        }
        ModePaiement modePaiement = new ModePaiement();
        modePaiement.setId(id);
        return modePaiement;
    }
}
