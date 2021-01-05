package com.kobe.nucleus.service.mapper;


import com.kobe.nucleus.domain.*;
import com.kobe.nucleus.service.dto.VenteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Vente} and its DTO {@link VenteDTO}.
 */
@Mapper(componentModel = "spring", uses = {MagasinMapper.class, UtilisateurMapper.class, ClientMapper.class, AyantDroitMapper.class, RemiseMapper.class, ModePaiementMapper.class, MedecinMapper.class})
public interface VenteMapper extends EntityMapper<VenteDTO, Vente> {

    @Mapping(source = "magasin.id", target = "magasinId")
    @Mapping(source = "magasin.nomCourt", target = "magasinNomCourt")
    @Mapping(source = "createdBy.id", target = "createdById")
    @Mapping(source = "createdBy.firstName", target = "createdByFirstName")
    @Mapping(source = "assure.id", target = "assureId")
    @Mapping(source = "assure.firstName", target = "assureFirstName")
    @Mapping(source = "ayantDroit.id", target = "ayantDroitId")
    @Mapping(source = "ayantDroit.firstName", target = "ayantDroitFirstName")
    @Mapping(source = "remise.id", target = "remiseId")
    @Mapping(source = "remise.valeur", target = "remiseValeur")
    @Mapping(source = "modePaiement.id", target = "modePaiementId")
    @Mapping(source = "modePaiement.libelle", target = "modePaiementLibelle")
    @Mapping(source = "medecin.id", target = "medecinId")
    VenteDTO toDto(Vente vente);

    @Mapping(target = "lignesVentes", ignore = true)
    @Mapping(target = "removeLignesVente", ignore = true)
    @Mapping(target = "lignesVenteAssurences", ignore = true)
    @Mapping(target = "removeLignesVenteAssurence", ignore = true)
    @Mapping(source = "magasinId", target = "magasin")
    @Mapping(source = "createdById", target = "createdBy")
    @Mapping(source = "assureId", target = "assure")
    @Mapping(source = "ayantDroitId", target = "ayantDroit")
    @Mapping(source = "remiseId", target = "remise")
    @Mapping(source = "modePaiementId", target = "modePaiement")
    @Mapping(source = "medecinId", target = "medecin")
    Vente toEntity(VenteDTO venteDTO);

    default Vente fromId(Long id) {
        if (id == null) {
            return null;
        }
        Vente vente = new Vente();
        vente.setId(id);
        return vente;
    }
}
