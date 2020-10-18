package com.kobe.nucleus.service.impl;

import com.kobe.nucleus.service.GroupeFournisseurService;
import com.kobe.nucleus.domain.GroupeFournisseur;
import com.kobe.nucleus.repository.GroupeFournisseurRepository;
import com.kobe.nucleus.service.dto.GroupeFournisseurDTO;
import com.kobe.nucleus.service.mapper.GroupeFournisseurMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
    public Page<GroupeFournisseurDTO> findAll(Pageable pageable) {
        log.debug("Request to get all GroupeFournisseurs");
        return groupeFournisseurRepository.findAll(pageable)
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
}
