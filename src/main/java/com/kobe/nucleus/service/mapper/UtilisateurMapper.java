package com.kobe.nucleus.service.mapper;


import com.kobe.nucleus.domain.*;
import com.kobe.nucleus.service.dto.UtilisateurDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Utilisateur} and its DTO {@link UtilisateurDTO}.
 */
@Mapper(componentModel = "spring", uses = {MagasinMapper.class})
public interface UtilisateurMapper extends EntityMapper<UtilisateurDTO, Utilisateur> {

    @Mapping(source = "magasin.id", target = "magasinId")
    @Mapping(source = "magasin.nomCourt", target = "magasinNomCourt")
    UtilisateurDTO toDto(Utilisateur utilisateur);

    @Mapping(target = "magasins", ignore = true)
    @Mapping(target = "removeMagasin", ignore = true)
    @Mapping(target = "paiements", ignore = true)
    @Mapping(target = "removePaiement", ignore = true)
    @Mapping(target = "deconditions", ignore = true)
    @Mapping(target = "removeDecondition", ignore = true)
    @Mapping(target = "ventes", ignore = true)
    @Mapping(target = "removeVente", ignore = true)
    @Mapping(target = "ajustements", ignore = true)
    @Mapping(target = "removeAjustement", ignore = true)
    @Mapping(target = "commandes", ignore = true)
    @Mapping(target = "removeCommande", ignore = true)
    @Mapping(target = "inventaires", ignore = true)
    @Mapping(target = "removeInventaire", ignore = true)
    @Mapping(target = "mvtProduits", ignore = true)
    @Mapping(target = "removeMvtProduit", ignore = true)
    @Mapping(target = "retourFournisseurs", ignore = true)
    @Mapping(target = "removeRetourFournisseur", ignore = true)
    @Mapping(target = "factures", ignore = true)
    @Mapping(target = "removeFacture", ignore = true)
    @Mapping(target = "permissions", ignore = true)
    @Mapping(target = "removePermission", ignore = true)
    @Mapping(target = "roleUtilisateur", ignore = true)
    @Mapping(source = "magasinId", target = "magasin")
    Utilisateur toEntity(UtilisateurDTO utilisateurDTO);

    default Utilisateur fromId(Long id) {
        if (id == null) {
            return null;
        }
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(id);
        return utilisateur;
    }
}
