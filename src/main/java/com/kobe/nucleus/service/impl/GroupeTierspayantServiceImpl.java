package com.kobe.nucleus.service.impl;

import com.kobe.nucleus.domain.Laboratoire;
import com.kobe.nucleus.repository.util.Condition;
import com.kobe.nucleus.repository.util.SpecificationBuilder;
import com.kobe.nucleus.service.GroupeTierspayantService;
import com.kobe.nucleus.domain.GroupeTierspayant;
import com.kobe.nucleus.repository.GroupeTierspayantRepository;
import com.kobe.nucleus.service.dto.GroupeTierspayantDTO;
import com.kobe.nucleus.service.mapper.GroupeTierspayantMapper;
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Optional;

/**
 * Service Implementation for managing {@link GroupeTierspayant}.
 */
@Service
@Transactional
public class GroupeTierspayantServiceImpl implements GroupeTierspayantService {

    private final Logger log = LoggerFactory.getLogger(GroupeTierspayantServiceImpl.class);

    private final GroupeTierspayantRepository groupeTierspayantRepository;

    private final GroupeTierspayantMapper groupeTierspayantMapper;

    public GroupeTierspayantServiceImpl(GroupeTierspayantRepository groupeTierspayantRepository, GroupeTierspayantMapper groupeTierspayantMapper) {
        this.groupeTierspayantRepository = groupeTierspayantRepository;
        this.groupeTierspayantMapper = groupeTierspayantMapper;
    }

    /**
     * Save a groupeTierspayant.
     *
     * @param groupeTierspayantDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public GroupeTierspayantDTO save(GroupeTierspayantDTO groupeTierspayantDTO) {
        log.debug("Request to save GroupeTierspayant : {}", groupeTierspayantDTO);
        GroupeTierspayant groupeTierspayant = groupeTierspayantMapper.toEntity(groupeTierspayantDTO);
        groupeTierspayant = groupeTierspayantRepository.save(groupeTierspayant);
        return groupeTierspayantMapper.toDto(groupeTierspayant);
    }

    /**
     * Get all the groupeTierspayants.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<GroupeTierspayantDTO> findAll(String search, Pageable pageable) {
        log.debug("Request to get all GroupeTierspayants");
        Pageable page = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
            Sort.by(Sort.Direction.ASC, "libelle"));
        if(!StringUtils.isEmpty(search)){
            SpecificationBuilder<GroupeTierspayant> builder=new SpecificationBuilder<>();
            Specification<GroupeTierspayant> spec = builder
                .with(new String[]{"libelle"}, search+"%", Condition.OperationType.LIKE, Condition.LogicalOperatorType.OR)
                .with(new String[]{"code"}, search+"%", Condition.OperationType.LIKE, Condition.LogicalOperatorType.END)

                .build();
            return  groupeTierspayantRepository.findAll(spec, page).map(groupeTierspayantMapper::toDto);
        }
        return groupeTierspayantRepository.findAll(page)
            .map(groupeTierspayantMapper::toDto);
    }


    /**
     * Get one groupeTierspayant by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<GroupeTierspayantDTO> findOne(Long id) {
        log.debug("Request to get GroupeTierspayant : {}", id);
        return groupeTierspayantRepository.findById(id)
            .map(groupeTierspayantMapper::toDto);
    }

    /**
     * Delete the groupeTierspayant by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete GroupeTierspayant : {}", id);

        groupeTierspayantRepository.deleteById(id);
    }

    @Override
    public void importGroupeTierspayant(InputStream inputStream) {
       try(BufferedReader br=new BufferedReader(new InputStreamReader(inputStream))) {
           Iterable<CSVRecord> records = CSVFormat.DEFAULT.withDelimiter(';')
               .withFirstRecordAsHeader()
               .parse(br);
           records.forEach(record->{
              groupeTierspayantRepository.save(new GroupeTierspayant().libelle(record.get(0)));
           });
       }catch (IOException e){
           log.debug("importGroupeTierspayant : {}", e);
       }


    }
}
