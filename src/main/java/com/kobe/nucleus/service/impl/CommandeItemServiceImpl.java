package com.kobe.nucleus.service.impl;

import com.kobe.nucleus.service.CommandeItemService;
import com.kobe.nucleus.domain.CommandeItem;
import com.kobe.nucleus.repository.CommandeItemRepository;
import com.kobe.nucleus.service.dto.CommandeItemDTO;
import com.kobe.nucleus.service.mapper.CommandeItemMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CommandeItem}.
 */
@Service
@Transactional
public class CommandeItemServiceImpl implements CommandeItemService {

    private final Logger log = LoggerFactory.getLogger(CommandeItemServiceImpl.class);

    private final CommandeItemRepository commandeItemRepository;

    private final CommandeItemMapper commandeItemMapper;

    public CommandeItemServiceImpl(CommandeItemRepository commandeItemRepository, CommandeItemMapper commandeItemMapper) {
        this.commandeItemRepository = commandeItemRepository;
        this.commandeItemMapper = commandeItemMapper;
    }

    /**
     * Save a commandeItem.
     *
     * @param commandeItemDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public CommandeItemDTO save(CommandeItemDTO commandeItemDTO) {
        log.debug("Request to save CommandeItem : {}", commandeItemDTO);
        CommandeItem commandeItem = commandeItemMapper.toEntity(commandeItemDTO);
        commandeItem = commandeItemRepository.save(commandeItem);
        return commandeItemMapper.toDto(commandeItem);
    }

    /**
     * Get all the commandeItems.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CommandeItemDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CommandeItems");
        return commandeItemRepository.findAll(pageable)
            .map(commandeItemMapper::toDto);
    }


    /**
     * Get one commandeItem by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CommandeItemDTO> findOne(Long id) {
        log.debug("Request to get CommandeItem : {}", id);
        return commandeItemRepository.findById(id)
            .map(commandeItemMapper::toDto);
    }

    /**
     * Delete the commandeItem by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CommandeItem : {}", id);

        commandeItemRepository.deleteById(id);
    }
}
