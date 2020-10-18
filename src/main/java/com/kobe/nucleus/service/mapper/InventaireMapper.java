package com.kobe.nucleus.service.mapper;


import com.kobe.nucleus.domain.*;
import com.kobe.nucleus.service.dto.InventaireDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Inventaire} and its DTO {@link InventaireDTO}.
 */
@Mapper(componentModel = "spring", uses = {MagasinMapper.class, UtilisateurMapper.class})
public interface InventaireMapper extends EntityMapper<InventaireDTO, Inventaire> {

    @Mapping(source = "magasin.id", target = "magasinId")
    @Mapping(source = "magasin.nomCourt", target = "magasinNomCourt")
    @Mapping(source = "createdBy.id", target = "createdById")
    @Mapping(source = "createdBy.firstName", target = "createdByFirstName")
    InventaireDTO toDto(Inventaire inventaire);

    @Mapping(target = "detailsInventaires", ignore = true)
    @Mapping(target = "removeDetailsInventaire", ignore = true)
    @Mapping(source = "magasinId", target = "magasin")
    @Mapping(source = "createdById", target = "createdBy")
    Inventaire toEntity(InventaireDTO inventaireDTO);

    default Inventaire fromId(Long id) {
        if (id == null) {
            return null;
        }
        Inventaire inventaire = new Inventaire();
        inventaire.setId(id);
        return inventaire;
    }
}
