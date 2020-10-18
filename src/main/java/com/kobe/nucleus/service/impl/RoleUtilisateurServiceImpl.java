package com.kobe.nucleus.service.impl;

import com.kobe.nucleus.service.RoleUtilisateurService;
import com.kobe.nucleus.domain.RoleUtilisateur;
import com.kobe.nucleus.repository.RoleUtilisateurRepository;
import com.kobe.nucleus.service.dto.RoleUtilisateurDTO;
import com.kobe.nucleus.service.mapper.RoleUtilisateurMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link RoleUtilisateur}.
 */
@Service
@Transactional
public class RoleUtilisateurServiceImpl implements RoleUtilisateurService {

    private final Logger log = LoggerFactory.getLogger(RoleUtilisateurServiceImpl.class);

    private final RoleUtilisateurRepository roleUtilisateurRepository;

    private final RoleUtilisateurMapper roleUtilisateurMapper;

    public RoleUtilisateurServiceImpl(RoleUtilisateurRepository roleUtilisateurRepository, RoleUtilisateurMapper roleUtilisateurMapper) {
        this.roleUtilisateurRepository = roleUtilisateurRepository;
        this.roleUtilisateurMapper = roleUtilisateurMapper;
    }

    /**
     * Save a roleUtilisateur.
     *
     * @param roleUtilisateurDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public RoleUtilisateurDTO save(RoleUtilisateurDTO roleUtilisateurDTO) {
        log.debug("Request to save RoleUtilisateur : {}", roleUtilisateurDTO);
        RoleUtilisateur roleUtilisateur = roleUtilisateurMapper.toEntity(roleUtilisateurDTO);
        roleUtilisateur = roleUtilisateurRepository.save(roleUtilisateur);
        return roleUtilisateurMapper.toDto(roleUtilisateur);
    }

    /**
     * Get all the roleUtilisateurs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RoleUtilisateurDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RoleUtilisateurs");
        return roleUtilisateurRepository.findAll(pageable)
            .map(roleUtilisateurMapper::toDto);
    }


    /**
     * Get all the roleUtilisateurs with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<RoleUtilisateurDTO> findAllWithEagerRelationships(Pageable pageable) {
        return roleUtilisateurRepository.findAllWithEagerRelationships(pageable).map(roleUtilisateurMapper::toDto);
    }

    /**
     * Get one roleUtilisateur by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RoleUtilisateurDTO> findOne(Long id) {
        log.debug("Request to get RoleUtilisateur : {}", id);
        return roleUtilisateurRepository.findOneWithEagerRelationships(id)
            .map(roleUtilisateurMapper::toDto);
    }

    /**
     * Delete the roleUtilisateur by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RoleUtilisateur : {}", id);

        roleUtilisateurRepository.deleteById(id);
    }
}
