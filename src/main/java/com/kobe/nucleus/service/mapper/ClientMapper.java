package com.kobe.nucleus.service.mapper;


import com.kobe.nucleus.domain.*;
import com.kobe.nucleus.service.dto.ClientDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Client} and its DTO {@link ClientDTO}.
 */
@Mapper(componentModel = "spring", uses = {CompagnieMapper.class, RemiseMapper.class})
public interface ClientMapper extends EntityMapper<ClientDTO, Client> {

    @Mapping(source = "compagnie.id", target = "compagnieId")
    @Mapping(source = "compagnie.libelle", target = "compagnieLibelle")
    @Mapping(source = "remise.id", target = "remiseId")
    @Mapping(source = "remise.valeur", target = "remiseValeur")
    ClientDTO toDto(Client client);

    @Mapping(target = "factureItems", ignore = true)
    @Mapping(target = "removeFactureItem", ignore = true)
    @Mapping(target = "ventes", ignore = true)
    @Mapping(target = "removeVente", ignore = true)
    @Mapping(target = "ayantDroits", ignore = true)
    @Mapping(target = "removeAyantDroit", ignore = true)
    @Mapping(target = "compteClients", ignore = true)
    @Mapping(target = "removeCompteClient", ignore = true)
    @Mapping(source = "compagnieId", target = "compagnie")
    @Mapping(source = "remiseId", target = "remise")
    Client toEntity(ClientDTO clientDTO);

    default Client fromId(Long id) {
        if (id == null) {
            return null;
        }
        Client client = new Client();
        client.setId(id);
        return client;
    }
}
