package com.kobe.nucleus.service.mapper;


import com.kobe.nucleus.domain.*;
import com.kobe.nucleus.service.dto.FournisseurDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Fournisseur} and its DTO {@link FournisseurDTO}.
 */
@Mapper(componentModel = "spring", uses = {GroupeFournisseurMapper.class})
public interface FournisseurMapper extends EntityMapper<FournisseurDTO, Fournisseur> {

    @Mapping(source = "groupeFournisseur.id", target = "groupeFournisseurId")
    @Mapping(source = "groupeFournisseur.libelle", target = "groupeFournisseurLibelle")
    FournisseurDTO toDto(Fournisseur fournisseur);

    @Mapping(source = "groupeFournisseurId", target = "groupeFournisseur")
    Fournisseur toEntity(FournisseurDTO fournisseurDTO);

    default Fournisseur fromId(Long id) {
        if (id == null) {
            return null;
        }
        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setId(id);
        return fournisseur;
    }
}
