package com.kobe.nucleus.service.mapper;


import com.kobe.nucleus.domain.*;
import com.kobe.nucleus.service.dto.MagasinDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Magasin} and its DTO {@link MagasinDTO}.
 */
@Mapper(componentModel = "spring", uses = {UtilisateurMapper.class})
public interface MagasinMapper extends EntityMapper<MagasinDTO, Magasin> {

    @Mapping(source = "magasin.id", target = "magasinId")
    @Mapping(source = "magasin.nomCourt", target = "magasinNomCourt")
    @Mapping(source = "manager.id", target = "managerId")
    @Mapping(source = "manager.firstName", target = "managerFirstName")
    MagasinDTO toDto(Magasin magasin);

    @Mapping(target = "ajustements", ignore = true)
    @Mapping(target = "removeAjustement", ignore = true)
    @Mapping(target = "paiements", ignore = true)
    @Mapping(target = "removePaiement", ignore = true)
    @Mapping(target = "inventaires", ignore = true)
    @Mapping(target = "removeInventaire", ignore = true)
    @Mapping(target = "retourFournisseurs", ignore = true)
    @Mapping(target = "removeRetourFournisseur", ignore = true)
    @Mapping(target = "rayons", ignore = true)
    @Mapping(target = "removeRayon", ignore = true)
    @Mapping(target = "ventes", ignore = true)
    @Mapping(target = "removeVente", ignore = true)
    @Mapping(target = "deconditions", ignore = true)
    @Mapping(target = "removeDecondition", ignore = true)
    @Mapping(target = "factures", ignore = true)
    @Mapping(target = "removeFacture", ignore = true)
    @Mapping(target = "mvtProduits", ignore = true)
    @Mapping(target = "removeMvtProduit", ignore = true)
    @Mapping(target = "commandes", ignore = true)
    @Mapping(target = "removeCommande", ignore = true)
    @Mapping(target = "stockProduits", ignore = true)
    @Mapping(target = "removeStockProduit", ignore = true)
    @Mapping(target = "utilisateurs", ignore = true)
    @Mapping(target = "removeUtilisateur", ignore = true)
    @Mapping(target = "magasins", ignore = true)
    @Mapping(target = "removeMagasin", ignore = true)
    @Mapping(source = "magasinId", target = "magasin")
    @Mapping(source = "managerId", target = "manager")
    Magasin toEntity(MagasinDTO magasinDTO);

    default Magasin fromId(Long id) {
        if (id == null) {
            return null;
        }
        Magasin magasin = new Magasin();
        magasin.setId(id);
        return magasin;
    }
}
