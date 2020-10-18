package com.kobe.nucleus.service.mapper;


import com.kobe.nucleus.domain.*;
import com.kobe.nucleus.service.dto.FactureDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Facture} and its DTO {@link FactureDTO}.
 */
@Mapper(componentModel = "spring", uses = {MagasinMapper.class, UtilisateurMapper.class, GroupeTierspayantMapper.class, TierspayantMapper.class})
public interface FactureMapper extends EntityMapper<FactureDTO, Facture> {

    @Mapping(source = "magasin.id", target = "magasinId")
    @Mapping(source = "magasin.nomCourt", target = "magasinNomCourt")
    @Mapping(source = "createdBy.id", target = "createdById")
    @Mapping(source = "createdBy.firstName", target = "createdByFirstName")
    @Mapping(source = "groupetp.id", target = "groupetpId")
    @Mapping(source = "groupetp.libelle", target = "groupetpLibelle")
    @Mapping(source = "tierpayant.id", target = "tierpayantId")
    @Mapping(source = "tierpayant.libelCourt", target = "tierpayantLibelCourt")
    FactureDTO toDto(Facture facture);

    @Mapping(target = "factureItems", ignore = true)
    @Mapping(target = "removeFactureItem", ignore = true)
    @Mapping(source = "magasinId", target = "magasin")
    @Mapping(source = "createdById", target = "createdBy")
    @Mapping(source = "groupetpId", target = "groupetp")
    @Mapping(source = "tierpayantId", target = "tierpayant")
    Facture toEntity(FactureDTO factureDTO);

    default Facture fromId(Long id) {
        if (id == null) {
            return null;
        }
        Facture facture = new Facture();
        facture.setId(id);
        return facture;
    }
}
