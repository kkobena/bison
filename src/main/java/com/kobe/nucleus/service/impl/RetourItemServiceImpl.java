package com.kobe.nucleus.service.impl;

import com.kobe.nucleus.service.RetourItemService;
import com.kobe.nucleus.domain.RetourItem;
import com.kobe.nucleus.repository.RetourItemRepository;
import com.kobe.nucleus.service.dto.RetourItemDTO;
import com.kobe.nucleus.service.mapper.RetourItemMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link RetourItem}.
 */
@Service
@Transactional
public class RetourItemServiceImpl implements RetourItemService {

    private final Logger log = LoggerFactory.getLogger(RetourItemServiceImpl.class);

    private final RetourItemRepository retourItemRepository;

    private final RetourItemMapper retourItemMapper;

    public RetourItemServiceImpl(RetourItemRepository retourItemRepository, RetourItemMapper retourItemMapper) {
        this.retourItemRepository = retourItemRepository;
        this.retourItemMapper = retourItemMapper;
    }

    /**
     * Save a retourItem.
     *
     * @param retourItemDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public RetourItemDTO save(RetourItemDTO retourItemDTO) {
        log.debug("Request to save RetourItem : {}", retourItemDTO);
        RetourItem retourItem = retourItemMapper.toEntity(retourItemDTO);
        retourItem = retourItemRepository.save(retourItem);
        return retourItemMapper.toDto(retourItem);
    }

    /**
     * Get all the retourItems.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RetourItemDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RetourItems");
        return retourItemRepository.findAll(pageable)
            .map(retourItemMapper::toDto);
    }


    /**
     * Get one retourItem by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RetourItemDTO> findOne(Long id) {
        log.debug("Request to get RetourItem : {}", id);
        return retourItemRepository.findById(id)
            .map(retourItemMapper::toDto);
    }

    /**
     * Delete the retourItem by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RetourItem : {}", id);

        retourItemRepository.deleteById(id);
    }
}
