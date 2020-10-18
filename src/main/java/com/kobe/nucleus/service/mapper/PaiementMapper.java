package com.kobe.nucleus.service.mapper;


import com.kobe.nucleus.domain.*;
import com.kobe.nucleus.service.dto.PaiementDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Paiement} and its DTO {@link PaiementDTO}.
 */
@Mapper(componentModel = "spring", uses = {MagasinMapper.class, UtilisateurMapper.class, BanqueMapper.class})
public interface PaiementMapper extends EntityMapper<PaiementDTO, Paiement> {

    @Mapping(source = "magasin.id", target = "magasinId")
    @Mapping(source = "magasin.nomCourt", target = "magasinNomCourt")
    @Mapping(source = "createdBy.id", target = "createdById")
    @Mapping(source = "createdBy.firstName", target = "createdByFirstName")
    @Mapping(source = "banque.id", target = "banqueId")
    @Mapping(source = "banque.libelle", target = "banqueLibelle")
    PaiementDTO toDto(Paiement paiement);

    @Mapping(target = "paiementItems", ignore = true)
    @Mapping(target = "removePaiementItem", ignore = true)
    @Mapping(source = "magasinId", target = "magasin")
    @Mapping(source = "createdById", target = "createdBy")
    @Mapping(source = "banqueId", target = "banque")
    Paiement toEntity(PaiementDTO paiementDTO);

    default Paiement fromId(Long id) {
        if (id == null) {
            return null;
        }
        Paiement paiement = new Paiement();
        paiement.setId(id);
        return paiement;
    }
}
