package com.kobe.nucleus.service.impl;
import com.kobe.nucleus.domain.CategorieProduit;
import com.kobe.nucleus.service.FamilleProduitService;
import com.kobe.nucleus.domain.FamilleProduit;
import com.kobe.nucleus.domain.Fournisseur;
import com.kobe.nucleus.domain.GroupeFournisseur;
import com.kobe.nucleus.domain.constants.EntityConstant;
import com.kobe.nucleus.domain.enumeration.Status;
import com.kobe.nucleus.repository.CategorieProduitRepository;
import com.kobe.nucleus.repository.FamilleProduitRepository;
import com.kobe.nucleus.repository.util.Condition;
import com.kobe.nucleus.repository.util.SpecificationBuilder;
import com.kobe.nucleus.service.dto.FamilleProduitDTO;
import com.kobe.nucleus.service.mapper.FamilleProduitMapper;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Optional;

/**
 * Service Implementation for managing {@link FamilleProduit}.
 */
@Service
@Transactional
public class FamilleProduitServiceImpl implements FamilleProduitService {

    private final Logger log = LoggerFactory.getLogger(FamilleProduitServiceImpl.class);

    private final FamilleProduitRepository familleProduitRepository;
    private final CategorieProduitRepository categorieProduitRepository;

    private final FamilleProduitMapper familleProduitMapper;

    public FamilleProduitServiceImpl(FamilleProduitRepository familleProduitRepository, FamilleProduitMapper familleProduitMapper,CategorieProduitRepository categorieProduitRepository) {
        this.familleProduitRepository = familleProduitRepository;
        this.familleProduitMapper = familleProduitMapper;
        this.categorieProduitRepository=categorieProduitRepository;
    }

    /**
     * Save a familleProduit.
     *
     * @param familleProduitDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public FamilleProduitDTO save(FamilleProduitDTO familleProduitDTO) {
        log.debug("Request to save FamilleProduit : {}", familleProduitDTO);
        FamilleProduit familleProduit = familleProduitMapper.toEntity(familleProduitDTO);
        familleProduit = familleProduitRepository.save(familleProduit);
        return familleProduitMapper.toDto(familleProduit);
    }

    /**
     * Get all the familleProduits.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<FamilleProduitDTO> findAll(String search,Pageable pageable) {
    	 Pageable page = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
	                Sort.by(Sort.Direction.ASC, "libelle"));
	            if(!StringUtils.isEmpty(search)){
	                SpecificationBuilder<FamilleProduit> builder=new SpecificationBuilder<>();
	                Specification<FamilleProduit> spec = builder
	                    .with(new String[]{"libelle"}, search+"%", Condition.OperationType.LIKE, Condition.LogicalOperatorType.END)
	                    .build();
	                return  familleProduitRepository.findAll(spec, page).map(familleProduitMapper::toDto);
	            }
        return familleProduitRepository.findAll(page)
            .map(familleProduitMapper::toDto);
    }


    /**
     * Get one familleProduit by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<FamilleProduitDTO> findOne(Long id) {
        log.debug("Request to get FamilleProduit : {}", id);
        return familleProduitRepository.findById(id)
            .map(familleProduitMapper::toDto);
    }

    /**
     * Delete the familleProduit by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete FamilleProduit : {}", id);

        familleProduitRepository.deleteById(id);
    }

	@Override
	public void importation(InputStream inputStream) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
			Iterable<CSVRecord> records = CSVFormat.DEFAULT.withDelimiter(';').withFirstRecordAsHeader().parse(br);
			records.forEach(record -> {
				FamilleProduit familleProduit = new FamilleProduit();
				familleProduit.setCode(record.get(1));
				familleProduit.setLibelle(record.get(0));
				
				if (!StringUtils.isEmpty(record.get(2))) {
					Optional<CategorieProduit> op = categorieProduitRepository.findOneByLibelle(record.get(2));
					log.debug("Optional  : {}", op);
					if (op.isPresent()) {
						familleProduit.setCategorie(op.get());;

					} 

				}

				familleProduitRepository.save(familleProduit);
			});
		} catch (IOException e) {
			log.debug("importation : {}", e);
		}

	
		
	}
    
    
    
}
