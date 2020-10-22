package com.kobe.nucleus.service.impl;

import com.kobe.nucleus.repository.util.Condition;
import com.kobe.nucleus.repository.util.SpecificationBuilder;
import com.kobe.nucleus.service.TierspayantService;
import com.kobe.nucleus.domain.Tierspayant;
import com.kobe.nucleus.domain.enumeration.Status;
import com.kobe.nucleus.domain.enumeration.TypeTierspayant;
import com.kobe.nucleus.repository.GroupeTierspayantRepository;
import com.kobe.nucleus.repository.ModelFactureRepository;
import com.kobe.nucleus.repository.RisqueRepository;
import com.kobe.nucleus.repository.TierspayantRepository;
import com.kobe.nucleus.service.dto.ResponseDTO;
import com.kobe.nucleus.service.dto.TierspayantDTO;
import com.kobe.nucleus.service.mapper.TierspayantMapper;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.time.Instant;
import java.util.Locale;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Tierspayant}.
 */
@Service
@Transactional
public class TierspayantServiceImpl implements TierspayantService {

	private final Logger log = LoggerFactory.getLogger(TierspayantServiceImpl.class);

	private final TierspayantRepository tierspayantRepository;
	private final RisqueRepository risqueRepository;
	private final GroupeTierspayantRepository groupeTierspayantRepository;
	private final ModelFactureRepository modeleFactureRepository;
	private final TierspayantMapper tierspayantMapper;

	public TierspayantServiceImpl(TierspayantRepository tierspayantRepository, TierspayantMapper tierspayantMapper,
			GroupeTierspayantRepository groupeTierspayantRepository, ModelFactureRepository modeleFactureRepository,
			RisqueRepository risqueRepository) {
		this.tierspayantRepository = tierspayantRepository;
		this.tierspayantMapper = tierspayantMapper;
		this.risqueRepository = risqueRepository;
		this.groupeTierspayantRepository = groupeTierspayantRepository;
		this.modeleFactureRepository = modeleFactureRepository;
	}

	/**
	 * Save a tierspayant.
	 *
	 * @param tierspayantDTO the entity to save.
	 * @return the persisted entity.
	 */
	@Override
	public TierspayantDTO save(TierspayantDTO tierspayantDTO) {
		log.debug("Request to save Tierspayant : {}", tierspayantDTO);
		Tierspayant tierspayant = tierspayantMapper.toEntity(tierspayantDTO);
		tierspayant = tierspayantRepository.save(tierspayant);
		return tierspayantMapper.toDto(tierspayant);
	}

	/**
	 * Get all the tierspayants.
	 *
	 * @param pageable the pagination information.
	 * @return the list of entities.
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<TierspayantDTO> findAll(String search, Pageable pageable) {
		log.debug("Request to get all Tierspayants");
		Pageable page = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
				Sort.by(Sort.Direction.ASC, "libelLong"));
		if (!StringUtils.isEmpty(search)) {
			SpecificationBuilder<Tierspayant> builder = new SpecificationBuilder<>();
			Specification<Tierspayant> spec = builder
					.with(new String[] { "libelCourt" }, search + "%", Condition.OperationType.LIKE,
							Condition.LogicalOperatorType.OR)
					.with(new String[] { "libelLong" }, search + "%", Condition.OperationType.LIKE,
							Condition.LogicalOperatorType.END)

					.build();
			return tierspayantRepository.findAll(spec, page).map(tierspayantMapper::toDto);
		}

		return tierspayantRepository.findAll(page).map(tierspayantMapper::toDto);
	}

	/**
	 * Get one tierspayant by id.
	 *
	 * @param id the id of the entity.
	 * @return the entity.
	 */
	@Override
	@Transactional(readOnly = true)
	public Optional<TierspayantDTO> findOne(Long id) {
		log.debug("Request to get Tierspayant : {}", id);
		return tierspayantRepository.findById(id).map(tierspayantMapper::toDto);
	}

	/**
	 * Delete the tierspayant by id.
	 *
	 * @param id the id of the entity.
	 */
	@Override
	public void delete(Long id) {
		log.debug("Request to delete Tierspayant : {}", id);

		tierspayantRepository.deleteById(id);
	}

	@Override
	public ResponseDTO processCsvStream(InputStream intput) throws IOException, ParseException {
		Reader reader = new BufferedReader(new InputStreamReader(intput));
		int count = 0;
		try (reader) {
			Iterable<CSVRecord> records = CSVFormat.EXCEL.withDelimiter(';').withFirstRecordAsHeader().parse(reader);

			for (CSVRecord record : records) {

				TierspayantDTO dto = new TierspayantDTO();
				dto.setConsoJournaliere(0);
				dto.setCode(RandomStringUtils.random(4, true, true).toUpperCase());
				dto.setConsoMensuelle(0);
				dto.setCreatedAt(Instant.now());
				dto.setStatus(Status.ACTIVE);
				dto.setUpdatedAt(Instant.now());
				dto.setLibelLong(record.get(0));
				dto.setLibelCourt(record.get(1));
				dto.setPhone(record.get(2));
				dto.setMobile(record.get(3));
				dto.setAddress(record.get(4));
				dto.setRegistrecommerce(record.get(5));
				dto.setNbreBordereaux(Integer.valueOf(record.get(6)));
				dto.setTypePlafond(Boolean.valueOf(record.get(7)));
				DecimalFormat format = new DecimalFormat("", new DecimalFormatSymbols(Locale.FRENCH));
				int plafond = format.parse(record.get(8)).intValue();
				dto.setPlafond(plafond);
				dto.setEnable(Boolean.valueOf(record.get(9)));
				int montantMaxFacture = Integer.valueOf(record.get(10));
				if (montantMaxFacture <= 0) {
					montantMaxFacture = Integer.MAX_VALUE;
				}
				dto.setMontantMaxFacture(montantMaxFacture);
				int remise = format.parse(record.get(11)).intValue();
				dto.setRemiseForfetaire(remise);
				dto.setCodeComptable(record.get(14));
				risqueRepository.findOneByCode(record.get(12)).ifPresent(risque -> {
					dto.setRisqueId(risque.getId());

				});
//				groupeTierspayantRepository.findOneByLibelle(record.get(15)).ifPresent(groupe -> {
//					dto.setGroupetpId(groupe.getId());
//				});
				TypeTierspayant typeTierspayant = TypeTierspayant.valueOfLabel(record.get(13));
				dto.setTypeTp(typeTierspayant);

				Tierspayant tierspayant = tierspayantMapper.toEntity(dto);
				tierspayantRepository.save(tierspayant);
				count++;

			}

		}
		return new ResponseDTO().size(count).totalSize(count).success(true);
	}

	/**
	 * Update a tierspayant.
	 *
	 * @param tierspayantDTO the entity to update.
	 * @return the persisted entity.
	 */
	@Override
	public TierspayantDTO update(TierspayantDTO tierspayantDTO) {
		log.debug("Request to update Tierspayant : {}", tierspayantDTO);
		final Tierspayant dto = tierspayantRepository.getOne(tierspayantDTO.getId());
		dto.setUpdatedAt(Instant.now());
		dto.setLibelLong(tierspayantDTO.getLibelLong());
		dto.setLibelCourt(tierspayantDTO.getLibelCourt());
		dto.setMobile(tierspayantDTO.getMobile());
		dto.setAddress(tierspayantDTO.getAddress());
		dto.setNbreBordereaux(tierspayantDTO.getNbreBordereaux());
		dto.setTypePlafond(tierspayantDTO.isTypePlafond());
		dto.setPlafond(tierspayantDTO.getPlafond());
		dto.setMontantMaxFacture(tierspayantDTO.getMontantMaxFacture());
		dto.setRemiseForfetaire(tierspayantDTO.getRemiseForfetaire());
		dto.setCodeComptable(tierspayantDTO.getCodeComptable());
		if (tierspayantDTO.getRisqueId() != null) {
			risqueRepository.findById(tierspayantDTO.getRisqueId()).ifPresent(risque -> {
				dto.setRisque(risque);

			});
		}

		if (tierspayantDTO.getGroupetpId() != null) {
			groupeTierspayantRepository.findById(tierspayantDTO.getGroupetpId()).ifPresent(groupe -> {
				dto.setGroupetp(groupe);
			});
		}

		dto.setTypeTp(tierspayantDTO.getTypeTp());
		if (tierspayantDTO.getModelFactureId() != null) {
			modeleFactureRepository.findById(tierspayantDTO.getModelFactureId()).ifPresent(modelFacture -> {
				dto.setModelFacture(modelFacture);
			});
		}

		tierspayantRepository.save(dto);
		return tierspayantMapper.toDto(dto);
	}
}
