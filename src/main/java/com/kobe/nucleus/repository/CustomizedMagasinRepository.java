package com.kobe.nucleus.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kobe.nucleus.domain.Magasin;
import com.kobe.nucleus.domain.Rayon;
import com.kobe.nucleus.service.dto.MagasinDTO;
import com.kobe.nucleus.service.dto.MagasinInfos;

@Repository
@Transactional
public class CustomizedMagasinRepository implements CustomizedMagasinService {
	private final Logger LOG = LoggerFactory.getLogger(CustomizedMagasinRepository.class);

	private final MagasinRepository magasinRepository;
	private final RayonRepository rayonRepository;

	@Override
	public MagasinDTO save(MagasinDTO dto) throws Exception {
		Magasin magasin = magasinRepository.saveAndFlush(buildMagasinFromDTO(dto));
		Rayon defautRayon=defaultRayon();
		defautRayon.setMagasin(magasin);
		rayonRepository.save(defautRayon);
		return new MagasinDTO(magasin);
	}

	@Override
	public MagasinDTO update(MagasinDTO dto) throws Exception {
		Magasin magasin = magasinRepository
				.saveAndFlush(buildMagasinFromDTO(dto, magasinRepository.getOne(dto.getId())));
		return new MagasinDTO(magasin);
	}

	@Override
	public MagasinDTO update(MagasinInfos dto) throws Exception {
		Magasin magasin = magasinRepository
				.saveAndFlush(buildMagasinFromDTO(dto, magasinRepository.getOne(dto.getId())));
		return new MagasinDTO(magasin);
	}

	@Override
	public MagasinDTO updateManager(Long id, Long managerId) throws Exception {
		Magasin magasin = magasinRepository.getOne(id);
		magasin.setManager(fromManagerId(managerId));
		magasin = magasinRepository.saveAndFlush(magasin);
		return new MagasinDTO(magasin);
	}

	public CustomizedMagasinRepository(MagasinRepository magasinRepository, RayonRepository rayonRepository) {
		this.rayonRepository = rayonRepository;
		this.magasinRepository = magasinRepository;
	}

	@Override
	public MagasinDTO updateStockage(MagasinDTO dto) throws Exception {
		Magasin magasin = magasinRepository
				.saveAndFlush(buildStockageFromDTO(dto, magasinRepository.getOne(dto.getId())));
		return new MagasinDTO(magasin);
	}

}
