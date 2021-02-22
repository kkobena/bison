package com.kobe.nucleus.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Optional;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
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

import com.kobe.nucleus.domain.GammeProduit;
import com.kobe.nucleus.repository.GammeProduitRepository;
import com.kobe.nucleus.repository.util.Condition;
import com.kobe.nucleus.repository.util.SpecificationBuilder;
import com.kobe.nucleus.service.GammeProduitService;
import com.kobe.nucleus.service.dto.GammeProduitDTO;
import com.kobe.nucleus.service.mapper.GammeProduitMapper;

/**
 * Service Implementation for managing {@link GammeProduit}.
 */
@Service
@Transactional
public class GammeProduitServiceImpl implements GammeProduitService {

    private final Logger log = LoggerFactory.getLogger(GammeProduitServiceImpl.class);

    private final GammeProduitRepository gammeProduitRepository;

    private final GammeProduitMapper gammeProduitMapper;

    public GammeProduitServiceImpl(GammeProduitRepository gammeProduitRepository, GammeProduitMapper gammeProduitMapper) {
        this.gammeProduitRepository = gammeProduitRepository;
        this.gammeProduitMapper = gammeProduitMapper;
    }

    /**
     * Save a gammeProduit.
     *
     * @param gammeProduitDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public GammeProduitDTO save(GammeProduitDTO gammeProduitDTO) {
        log.debug("Request to save GammeProduit : {}", gammeProduitDTO);
        GammeProduit gammeProduit = gammeProduitMapper.toEntity(gammeProduitDTO);
        gammeProduit = gammeProduitRepository.save(gammeProduit);
        return gammeProduitMapper.toDto(gammeProduit);
    }

    /**
     * Get all the gammeProduits.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<GammeProduitDTO> findAll(String search,Pageable pageable) {
        log.debug("Request to get all GammeProduits");
        Pageable page = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
            Sort.by(Sort.Direction.ASC, "libelle"));

        if(!StringUtils.isEmpty(search)){
            SpecificationBuilder<GammeProduit> builder=new SpecificationBuilder<>();
            Specification<GammeProduit> spec = builder
                .with(new String[]{"libelle"}, search+"%", Condition.OperationType.LIKE, Condition.LogicalOperatorType.OR)
                .with(new String[]{"code"}, search+"%", Condition.OperationType.LIKE, Condition.LogicalOperatorType.END)
                .build();
            return  gammeProduitRepository.findAll(spec, page).map(gammeProduitMapper::toDto);
        }
        return gammeProduitRepository.findAll(page)
            .map(gammeProduitMapper::toDto);
    }


    /**
     * Get one gammeProduit by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<GammeProduitDTO> findOne(Long id) {
        log.debug("Request to get GammeProduit : {}", id);
        return gammeProduitRepository.findById(id)
            .map(gammeProduitMapper::toDto);
    }

    /**
     * Delete the gammeProduit by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete GammeProduit : {}", id);
        gammeProduitRepository.deleteById(id);
    }
    
    @Override
	public void importation(InputStream inputStream) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
			Iterable<CSVRecord> records = CSVFormat.DEFAULT.withDelimiter(';').withFirstRecordAsHeader().parse(br);
			records.forEach(record -> {
				GammeProduit gammeProduit = new GammeProduit();
				gammeProduit.setLibelle(record.get(0));
				gammeProduitRepository.save(gammeProduit);
			});
		} catch (IOException e) {
			log.debug("importation : {}", e);
		}

	
		
	}
    
}
