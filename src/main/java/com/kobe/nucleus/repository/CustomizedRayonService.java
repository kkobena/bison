package com.kobe.nucleus.repository;

import java.time.Instant;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.kobe.nucleus.domain.Magasin;
import com.kobe.nucleus.domain.Rayon;
import com.kobe.nucleus.domain.enumeration.Status;
import com.kobe.nucleus.service.dto.RayonDTO;

public interface CustomizedRayonService {
	Page<RayonDTO> listRayonsByMagasinId(Long magasinId, String query, Pageable pageable);

	RayonDTO save(RayonDTO dto);

	RayonDTO update(RayonDTO dto);

	default RayonDTO buildRayonDTOFromRayon(Rayon rayon) {
		RayonDTO dto = new RayonDTO();
		dto.setCode(rayon.getCode());
		dto.setId(rayon.getId());
		Magasin magasin = rayon.getMagasin();
		dto.setMagasinId(magasin.getId());
		dto.setMagasinNomCourt(magasin.getNomLong());
		dto.setLibelle(rayon.getLibelle());
		dto.setExclude(rayon.isExclude());
		dto.setCreatedAt(rayon.getCreatedAt());
		dto.setUpdatedAt(rayon.getUpdatedAt());
		return dto;

	}

	default Rayon buildRayonFromRayonDTO(RayonDTO dto, Rayon rayon) {
		rayon.setCode(dto.getCode());
		rayon.setLibelle(dto.getLibelle());
		rayon.setUpdatedAt(Instant.now());
		return rayon;

	}

	default Rayon buildRayonFromRayonDTO(RayonDTO dto) {
		Rayon rayon = new Rayon();
		rayon.setCode(dto.getCode());
		rayon.setLibelle(dto.getLibelle());
		rayon.setUpdatedAt(Instant.now());
		rayon.setCreatedAt(Instant.now());
		rayon.setStatus(Status.ACTIVE);
		rayon.setMagasin(fromId(dto.getMagasinId()));
		return rayon;

	}

	default Magasin fromId(Long id) {
		Magasin magsin = new Magasin();
		magsin.setId(id);
		return magsin;
	}
	
	default Rayon cloner(Rayon rayon) {
		Rayon dto = new Rayon();
		dto.setCode(rayon.getCode());
		dto.setLibelle(rayon.getLibelle());
		dto.setExclude(rayon.isExclude());
		dto.setUpdatedAt(Instant.now());
		dto.setCreatedAt(Instant.now());
		dto.setStatus(Status.ACTIVE);
		return dto;

	}

}
