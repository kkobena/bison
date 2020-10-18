package com.kobe.nucleus.service.impl;

import com.kobe.nucleus.repository.util.Condition;
import com.kobe.nucleus.repository.util.SpecificationBuilder;
import com.kobe.nucleus.service.LaboratoireService;
import com.kobe.nucleus.domain.Laboratoire;
import com.kobe.nucleus.repository.LaboratoireRepository;
import com.kobe.nucleus.service.dto.LaboratoireDTO;
import com.kobe.nucleus.service.mapper.LaboratoireMapper;
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

import java.util.Optional;

/**
 * Service Implementation for managing {@link Laboratoire}.
 */
@Service
@Transactional
public class LaboratoireServiceImpl implements LaboratoireService {

    private final Logger log = LoggerFactory.getLogger(LaboratoireServiceImpl.class);

    private final LaboratoireRepository laboratoireRepository;

    private final LaboratoireMapper laboratoireMapper;

    public LaboratoireServiceImpl(LaboratoireRepository laboratoireRepository, LaboratoireMapper laboratoireMapper) {
        this.laboratoireRepository = laboratoireRepository;
        this.laboratoireMapper = laboratoireMapper;
    }

    /**
     * Save a laboratoire.
     *
     * @param laboratoireDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public LaboratoireDTO save(LaboratoireDTO laboratoireDTO) {
        log.debug("Request to save Laboratoire : {}", laboratoireDTO);
        Laboratoire laboratoire = laboratoireMapper.toEntity(laboratoireDTO);
        laboratoire = laboratoireRepository.save(laboratoire);
        return laboratoireMapper.toDto(laboratoire);
    }

    /**
     * Get all the laboratoires.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<LaboratoireDTO> findAll(String libelle, Pageable pageable) {
        log.debug("Request to get all Laboratoires");
        Pageable page = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
            Sort.by(Sort.Direction.ASC, "libelle"));
        if(!StringUtils.isEmpty(libelle)){
            SpecificationBuilder<Laboratoire> builder=new SpecificationBuilder<>();
            Specification<Laboratoire> spec = builder
                .with(new String[]{"libelle"}, libelle+"%", Condition.OperationType.LIKE, Condition.LogicalOperatorType.END)
                .build();
            return  laboratoireRepository.findAll(spec, page).map(laboratoireMapper::toDto);
        }
        return laboratoireRepository.findAll(page)
            .map(laboratoireMapper::toDto);
    }


    /**
     * Get one laboratoire by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<LaboratoireDTO> findOne(Long id) {
        log.debug("Request to get Laboratoire : {}", id);
        return laboratoireRepository.findById(id)
            .map(laboratoireMapper::toDto);
    }

    /**
     * Delete the laboratoire by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Laboratoire : {}", id);

        laboratoireRepository.deleteById(id);
    }
}
