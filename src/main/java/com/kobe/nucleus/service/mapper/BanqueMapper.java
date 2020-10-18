package com.kobe.nucleus.service.mapper;


import com.kobe.nucleus.domain.*;
import com.kobe.nucleus.service.dto.BanqueDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Banque} and its DTO {@link BanqueDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BanqueMapper extends EntityMapper<BanqueDTO, Banque> {


    @Mapping(target = "paiements", ignore = true)
    @Mapping(target = "removePaiement", ignore = true)
    Banque toEntity(BanqueDTO banqueDTO);

    default Banque fromId(Long id) {
        if (id == null) {
            return null;
        }
        Banque banque = new Banque();
        banque.setId(id);
        return banque;
    }
}
