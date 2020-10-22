package com.kobe.nucleus.service.mapper;

import com.kobe.nucleus.domain.*;

import com.kobe.nucleus.service.dto.MedecinDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Medecin} and its DTO {@link MedecinDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MedecinMapper extends EntityMapper<MedecinDTO, Medecin> {
	MedecinDTO toDto(Medecin medecin);

	@Mapping(target = "ventes", ignore = true)
//	@Mapping(target = "removeVente", ignore = true)
	Medecin toEntity(MedecinDTO medecinDTO);

	default Medecin fromId(Long id) {
		if (id == null) {
			return null;
		}
		Medecin medecin = new Medecin();
		medecin.setId(id);
		return medecin;
	}
}
