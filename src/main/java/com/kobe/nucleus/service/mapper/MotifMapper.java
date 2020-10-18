package com.kobe.nucleus.service.mapper;


import com.kobe.nucleus.domain.*;
import com.kobe.nucleus.service.dto.MotifDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Motif} and its DTO {@link MotifDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MotifMapper extends EntityMapper<MotifDTO, Motif> {


    @Mapping(target = "retourFournisseurs", ignore = true)
    @Mapping(target = "removeRetourFournisseur", ignore = true)
    Motif toEntity(MotifDTO motifDTO);

    default Motif fromId(Long id) {
        if (id == null) {
            return null;
        }
        Motif motif = new Motif();
        motif.setId(id);
        return motif;
    }
}
