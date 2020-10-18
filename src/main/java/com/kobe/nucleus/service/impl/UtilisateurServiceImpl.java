package com.kobe.nucleus.service.impl;

import com.kobe.nucleus.service.UtilisateurService;
import com.kobe.nucleus.domain.Utilisateur;
import com.kobe.nucleus.repository.UtilisateurRepository;
import com.kobe.nucleus.service.dto.UtilisateurDTO;
import com.kobe.nucleus.service.mapper.UtilisateurMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing {@link Utilisateur}.
 */
@Service
@Transactional
public class UtilisateurServiceImpl implements UtilisateurService {

    private final Logger log = LoggerFactory.getLogger(UtilisateurServiceImpl.class);

    private final UtilisateurRepository utilisateurRepository;

    private final UtilisateurMapper utilisateurMapper;

    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository, UtilisateurMapper utilisateurMapper) {
        this.utilisateurRepository = utilisateurRepository;
        this.utilisateurMapper = utilisateurMapper;
    }

    /**
     * Save a utilisateur.
     *
     * @param utilisateurDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public UtilisateurDTO save(UtilisateurDTO utilisateurDTO) {
        log.debug("Request to save Utilisateur : {}", utilisateurDTO);
        Utilisateur utilisateur = utilisateurMapper.toEntity(utilisateurDTO);
        utilisateur = utilisateurRepository.save(utilisateur);
        return utilisateurMapper.toDto(utilisateur);
    }

    /**
     * Get all the utilisateurs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<UtilisateurDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Utilisateurs");
        return utilisateurRepository.findAll(pageable)
            .map(utilisateurMapper::toDto);
    }



    /**
     *  Get all the utilisateurs where RoleUtilisateur is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<UtilisateurDTO> findAllWhereRoleUtilisateurIsNull() {
        log.debug("Request to get all utilisateurs where RoleUtilisateur is null");
        return StreamSupport
            .stream(utilisateurRepository.findAll().spliterator(), false)
            .filter(utilisateur -> utilisateur.getRoleUtilisateur() == null)
            .map(utilisateurMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one utilisateur by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<UtilisateurDTO> findOne(Long id) {
        log.debug("Request to get Utilisateur : {}", id);
        return utilisateurRepository.findById(id)
            .map(utilisateurMapper::toDto);
    }

    /**
     * Delete the utilisateur by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Utilisateur : {}", id);

        utilisateurRepository.deleteById(id);
    }
}
