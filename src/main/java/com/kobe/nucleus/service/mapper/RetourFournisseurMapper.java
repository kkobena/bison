package com.kobe.nucleus.service.mapper;


import com.kobe.nucleus.domain.*;
import com.kobe.nucleus.service.dto.RetourFournisseurDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link RetourFournisseur} and its DTO {@link RetourFournisseurDTO}.
 */
@Mapper(componentModel = "spring", uses = {MagasinMapper.class, UtilisateurMapper.class, MotifMapper.class})
public interface RetourFournisseurMapper extends EntityMapper<RetourFournisseurDTO, RetourFournisseur> {

    @Mapping(source = "magasin.id", target = "magasinId")
    @Mapping(source = "magasin.nomCourt", target = "magasinNomCourt")
    @Mapping(source = "createdBy.id", target = "createdById")
    @Mapping(source = "createdBy.firstName", target = "createdByFirstName")
    @Mapping(source = "motif.id", target = "motifId")
    @Mapping(source = "motif.libelle", target = "motifLibelle")
    RetourFournisseurDTO toDto(RetourFournisseur retourFournisseur);

    @Mapping(target = "retourItems", ignore = true)
    @Mapping(target = "removeRetourItem", ignore = true)
    @Mapping(source = "magasinId", target = "magasin")
    @Mapping(source = "createdById", target = "createdBy")
    @Mapping(source = "motifId", target = "motif")
    RetourFournisseur toEntity(RetourFournisseurDTO retourFournisseurDTO);

    default RetourFournisseur fromId(Long id) {
        if (id == null) {
            return null;
        }
        RetourFournisseur retourFournisseur = new RetourFournisseur();
        retourFournisseur.setId(id);
        return retourFournisseur;
    }
}
