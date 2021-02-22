package com.kobe.nucleus.service.impl;

import com.kobe.nucleus.service.MagasinService;
import com.kobe.nucleus.domain.Magasin;
import com.kobe.nucleus.domain.enumeration.TypeMagasin;
import com.kobe.nucleus.repository.MagasinRepository;
import com.kobe.nucleus.service.dto.MagasinDTO;
import com.kobe.nucleus.service.mapper.MagasinMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Magasin}.
 */
@Service
@Transactional
public class MagasinServiceImpl implements MagasinService {

	private final Logger log = LoggerFactory.getLogger(MagasinServiceImpl.class);

	private final MagasinRepository magasinRepository;

	private final MagasinMapper magasinMapper;

	public MagasinServiceImpl(MagasinRepository magasinRepository, MagasinMapper magasinMapper) {
		this.magasinRepository = magasinRepository;
		this.magasinMapper = magasinMapper;
	}

	/**
	 * Save a magasin.
	 *
	 * @param magasinDTO the entity to save.
	 * @return the persisted entity.
	 */
	@Override
	public MagasinDTO save(MagasinDTO magasinDTO) {
		log.debug("Request to save Magasin : {}", magasinDTO);
		Magasin magasin = magasinMapper.toEntity(magasinDTO);
		magasin = magasinRepository.save(magasin);
		return magasinMapper.toDto(magasin);
	}

	/**
	 * Get all the magasins.
	 *
	 * @param pageable the pagination information.
	 * @return the list of entities.
	 */
	@Override
	@Transactional(readOnly = true)
	public List<MagasinDTO> findAll() {
		log.debug("Request to get all Magasins");
		return magasinRepository.findAllByMagasinIsNull().stream().map(MagasinDTO::new).collect(Collectors.toList());
	}

	/**
	 * Get one magasin by id.
	 *
	 * @param id the id of the entity.
	 * @return the entity.
	 */
	@Override
	@Transactional(readOnly = true)
	public Optional<MagasinDTO> findOne(Long id) {
		log.debug("Request to get Magasin : {}", id);
		return magasinRepository.findOneById(id).map(MagasinDTO::new);
	}

	/**
	 * Delete the magasin by id.
	 *
	 * @param id the id of the entity.
	 */
	@Override
	public void delete(Long id) {
		log.debug("Request to delete Magasin : {}", id);
		magasinRepository.deleteById(id);
	}

	@Override
	public List<MagasinDTO> findByTypeMagasin(TypeMagasin typeMagasin) {
		return magasinRepository.findByTypeMagasin(typeMagasin).stream().map(MagasinDTO::new)
				.collect(Collectors.toList());
	}
	/**
	 * Get all the magasins and Depot.
	 *
	 * @param pageable the pagination information.
	 * @return the list of entities.
	 */
	@Override
	@Transactional(readOnly = true)
	public List<MagasinDTO> findAllMagasinAndDepots() {
		log.debug("Request to get all Magasins");
		return magasinRepository.findByTypeMagasinIn(List.of(TypeMagasin.PRINCIPAL,TypeMagasin.DEPOT,TypeMagasin.DEPOT_AGREE)).stream().map(MagasinDTO::new).collect(Collectors.toList());
	}
}
