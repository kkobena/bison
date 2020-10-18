package com.kobe.nucleus.service.mapper;


import com.kobe.nucleus.domain.*;
import com.kobe.nucleus.service.dto.EtiquetteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Etiquette} and its DTO {@link EtiquetteDTO}.
 */
@Mapper(componentModel = "spring", uses = {ProduitMapper.class})
public interface EtiquetteMapper extends EntityMapper<EtiquetteDTO, Etiquette> {

    @Mapping(source = "produit.id", target = "produitId")
    @Mapping(source = "produit.libelle", target = "produitLibelle")
    EtiquetteDTO toDto(Etiquette etiquette);

    @Mapping(source = "produitId", target = "produit")
    Etiquette toEntity(EtiquetteDTO etiquetteDTO);

    default Etiquette fromId(Long id) {
        if (id == null) {
            return null;
        }
        Etiquette etiquette = new Etiquette();
        etiquette.setId(id);
        return etiquette;
    }
}
