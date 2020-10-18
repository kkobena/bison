package com.kobe.nucleus.service.mapper;


import com.kobe.nucleus.domain.*;
import com.kobe.nucleus.service.dto.FormProduitDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link FormProduit} and its DTO {@link FormProduitDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FormProduitMapper extends EntityMapper<FormProduitDTO, FormProduit> {


    @Mapping(target = "produits", ignore = true)
    @Mapping(target = "removeProduit", ignore = true)
    FormProduit toEntity(FormProduitDTO formProduitDTO);

    default FormProduit fromId(Long id) {
        if (id == null) {
            return null;
        }
        FormProduit formProduit = new FormProduit();
        formProduit.setId(id);
        return formProduit;
    }
}
