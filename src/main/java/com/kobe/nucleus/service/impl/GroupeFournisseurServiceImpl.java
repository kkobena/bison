package com.kobe.nucleus.service.impl;

import com.kobe.nucleus.service.GroupeFournisseurService;
import com.kobe.nucleus.domain.GroupeFournisseur;
import com.kobe.nucleus.domain.GroupeTierspayant;
import com.kobe.nucleus.domain.Laboratoire;
import com.kobe.nucleus.repository.GroupeFournisseurRepository;
import com.kobe.nucleus.repository.util.Condition;
import com.kobe.nucleus.repository.util.SpecificationBuilder;
import com.kobe.nucleus.service.dto.GroupeFournisseurDTO;
import com.kobe.nucleus.service.mapper.GroupeFournisseurMapper;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.RandomUtils;
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
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Service Implementation for managing {@link GroupeFournisseur}.
 */
@Service
@Transactional
public class GroupeFournisseurServiceImpl implements GroupeFournisseurService {

    private final Logger log = LoggerFactory.getLogger(GroupeFournisseurServiceImpl.class);

    private final GroupeFournisseurRepository groupeFournisseurRepository;

    private final GroupeFournisseurMapper groupeFournisseurMapper;

    public GroupeFournisseurServiceImpl(GroupeFournisseurRepository groupeFournisseurRepository, GroupeFournisseurMapper groupeFournisseurMapper) {
        this.groupeFournisseurRepository = groupeFournisseurRepository;
        this.groupeFournisseurMapper = groupeFournisseurMapper;
    }

    /**
     * Save a groupeFournisseur.
     *
     * @param groupeFournisseurDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public GroupeFournisseurDTO save(GroupeFournisseurDTO groupeFournisseurDTO) {
        log.debug("Request to save GroupeFournisseur : {}", groupeFournisseurDTO);
        GroupeFournisseur groupeFournisseur = groupeFournisseurMapper.toEntity(groupeFournisseurDTO);
        groupeFournisseur = groupeFournisseurRepository.save(groupeFournisseur);
        return groupeFournisseurMapper.toDto(groupeFournisseur);
    }

    /**
     * Get all the groupeFournisseurs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<GroupeFournisseurDTO> findAll(String search,Pageable pageable) {
        log.debug("Request to get all GroupeFournisseurs");
        Pageable page = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
                Sort.by(Sort.Direction.ASC, "libelle"));
            if(!StringUtils.isEmpty(search)){
                SpecificationBuilder<GroupeFournisseur> builder=new SpecificationBuilder<>();
                Specification<GroupeFournisseur> spec = builder
                    .with(new String[]{"libelle"}, search+"%", Condition.OperationType.LIKE, Condition.LogicalOperatorType.END)
                    .build();
                return  groupeFournisseurRepository.findAll(spec, page).map(groupeFournisseurMapper::toDto);
            }
       
        return groupeFournisseurRepository.findAll(page)
            .map(groupeFournisseurMapper::toDto);
    }


    /**
     * Get one groupeFournisseur by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<GroupeFournisseurDTO> findOne(Long id) {
        log.debug("Request to get GroupeFournisseur : {}", id);
        return groupeFournisseurRepository.findById(id)
            .map(groupeFournisseurMapper::toDto);
    }

    /**
     * Delete the groupeFournisseur by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete GroupeFournisseur : {}", id);

        groupeFournisseurRepository.deleteById(id);
    }
    
    @Override
    public void importation(InputStream inputStream) {
       try(BufferedReader br=new BufferedReader(new InputStreamReader(inputStream))) {
           Iterable<CSVRecord> records = CSVFormat.DEFAULT.withDelimiter(';')
               .withFirstRecordAsHeader()
               .parse(br);
           AtomicInteger count = new AtomicInteger();
           records.forEach(record->{
        	   GroupeFournisseur groupeFournisseur=new GroupeFournisseur();
        	   groupeFournisseur.setLibelle(record.get(0));
        	   groupeFournisseur.setOdre(count.incrementAndGet());
        	   
        	   
        	   groupeFournisseurRepository.save(groupeFournisseur);
           });
       }catch (IOException e){
           log.debug("importation : {}", e);
       }


    } 
}
