package com.kobe.nucleus.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kobe.nucleus.domain.Magasin;
import com.kobe.nucleus.domain.Rayon;
import com.kobe.nucleus.domain.enumeration.Status;
import com.kobe.nucleus.repository.CustomizedRayonService;
import com.kobe.nucleus.repository.RayonRepository;
import com.kobe.nucleus.service.RayonService;
import com.kobe.nucleus.service.dto.RayonDTO;
import com.kobe.nucleus.service.dto.ResponseDTO;
import com.kobe.nucleus.service.mapper.RayonMapper;

/**
 * Service Implementation for managing {@link Rayon}.
 */
@Service
@Transactional
public class RayonServiceImpl implements RayonService {

	private final Logger log = LoggerFactory.getLogger(RayonServiceImpl.class);

	private final RayonRepository rayonRepository;

	private final RayonMapper rayonMapper;
	private final CustomizedRayonService customizedRayonService;

	public RayonServiceImpl(RayonRepository rayonRepository, RayonMapper rayonMapper,
			CustomizedRayonService customizedRayonService) {
		this.rayonRepository = rayonRepository;
		this.rayonMapper = rayonMapper;
		this.customizedRayonService = customizedRayonService;
	}

	/**
	 * Save a rayon.
	 *
	 * @param rayonDTO the entity to save.
	 * @return the persisted entity.
	 */
	@Override
	public RayonDTO save(RayonDTO rayonDTO) {
		log.debug("Request to save Rayon : {}", rayonDTO);
		return customizedRayonService.save(rayonDTO);
	}

	@Override
	public RayonDTO update(RayonDTO rayonDTO) {
		log.debug("Request to save Rayon : {}", rayonDTO);
		return customizedRayonService.update(rayonDTO);
	}

	/**
	 * Get all the rayons.
	 *
	 * @param pageable the pagination information.
	 * @return the list of entities.
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<RayonDTO> findAll(Long magasinId, String query, Pageable pageable) {
		log.debug("Request to get all Rayons");
		return customizedRayonService.listRayonsByMagasinId(magasinId, query, pageable);
	}

	/**
	 * Get one rayon by id.
	 *
	 * @param id the id of the entity.
	 * @return the entity.
	 */
	@Override
	@Transactional(readOnly = true)
	public Optional<RayonDTO> findOne(Long id) {
		log.debug("Request to get Rayon : {}", id);
		return rayonRepository.findById(id).map(rayonMapper::toDto);
	}

	/**
	 * Delete the rayon by id.
	 *
	 * @param id the id of the entity.
	 */
	@Override
	public void delete(Long id) {
		log.debug("Request to delete Rayon : {}", id);
		rayonRepository.deleteById(id);
	}

	@Override
	public ResponseDTO importation(InputStream inputStream, Long magasinId) {
		AtomicInteger count = new AtomicInteger(0);
		try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
			Iterable<CSVRecord> records = CSVFormat.DEFAULT.withDelimiter(';').withFirstRecordAsHeader().parse(br);

			records.forEach(record -> {
				Rayon rayon = new Rayon();
				rayon.setLibelle(record.get(0).trim());
				rayon.setCode(record.get(1).trim());
				try {
					boolean exclude = Boolean.valueOf(Integer.valueOf(record.get(2)) == 0);
					rayon.setExclude(exclude);
				} catch (Exception e) {
					// TODO: handle exception
				}
				rayon.setCreatedAt(Instant.now());
				rayon.setUpdatedAt(Instant.now());
				rayon.setStatus(Status.ACTIVE);
				rayon.setMagasin(customizedRayonService.fromId(magasinId));
				rayonRepository.save(rayon);
				count.incrementAndGet();

			});
		} catch (IOException e) {
			log.debug("importation : {}", e);
		}

		return new ResponseDTO().size(count.get());
	}

	@Override
	public ResponseDTO cloner(List<RayonDTO> rayonIds, Long magasinId) {
		int count = 0;
		Magasin magasin = customizedRayonService.fromId(magasinId);
		for (RayonDTO rayonDTO : rayonIds) {
			Rayon rayon = customizedRayonService.buildRayonFromRayonDTO(rayonDTO);
			rayon.setMagasin(magasin);
			rayonRepository.save(rayon);
			count++;
		}
		return new ResponseDTO().size(count);
	}
}
