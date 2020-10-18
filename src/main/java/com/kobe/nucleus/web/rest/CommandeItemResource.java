package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.service.CommandeItemService;
import com.kobe.nucleus.web.rest.errors.BadRequestAlertException;
import com.kobe.nucleus.service.dto.CommandeItemDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.kobe.nucleus.domain.CommandeItem}.
 */
@RestController
@RequestMapping("/api")
public class CommandeItemResource {

    private final Logger log = LoggerFactory.getLogger(CommandeItemResource.class);

    private static final String ENTITY_NAME = "commandeItem";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CommandeItemService commandeItemService;

    public CommandeItemResource(CommandeItemService commandeItemService) {
        this.commandeItemService = commandeItemService;
    }

    /**
     * {@code POST  /commande-items} : Create a new commandeItem.
     *
     * @param commandeItemDTO the commandeItemDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new commandeItemDTO, or with status {@code 400 (Bad Request)} if the commandeItem has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/commande-items")
    public ResponseEntity<CommandeItemDTO> createCommandeItem(@Valid @RequestBody CommandeItemDTO commandeItemDTO) throws URISyntaxException {
        log.debug("REST request to save CommandeItem : {}", commandeItemDTO);
        if (commandeItemDTO.getId() != null) {
            throw new BadRequestAlertException("A new commandeItem cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CommandeItemDTO result = commandeItemService.save(commandeItemDTO);
        return ResponseEntity.created(new URI("/api/commande-items/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /commande-items} : Updates an existing commandeItem.
     *
     * @param commandeItemDTO the commandeItemDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated commandeItemDTO,
     * or with status {@code 400 (Bad Request)} if the commandeItemDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the commandeItemDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/commande-items")
    public ResponseEntity<CommandeItemDTO> updateCommandeItem(@Valid @RequestBody CommandeItemDTO commandeItemDTO) throws URISyntaxException {
        log.debug("REST request to update CommandeItem : {}", commandeItemDTO);
        if (commandeItemDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CommandeItemDTO result = commandeItemService.save(commandeItemDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, commandeItemDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /commande-items} : get all the commandeItems.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of commandeItems in body.
     */
    @GetMapping("/commande-items")
    public ResponseEntity<List<CommandeItemDTO>> getAllCommandeItems(Pageable pageable) {
        log.debug("REST request to get a page of CommandeItems");
        Page<CommandeItemDTO> page = commandeItemService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /commande-items/:id} : get the "id" commandeItem.
     *
     * @param id the id of the commandeItemDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the commandeItemDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/commande-items/{id}")
    public ResponseEntity<CommandeItemDTO> getCommandeItem(@PathVariable Long id) {
        log.debug("REST request to get CommandeItem : {}", id);
        Optional<CommandeItemDTO> commandeItemDTO = commandeItemService.findOne(id);
        return ResponseUtil.wrapOrNotFound(commandeItemDTO);
    }

    /**
     * {@code DELETE  /commande-items/:id} : delete the "id" commandeItem.
     *
     * @param id the id of the commandeItemDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/commande-items/{id}")
    public ResponseEntity<Void> deleteCommandeItem(@PathVariable Long id) {
        log.debug("REST request to delete CommandeItem : {}", id);

        commandeItemService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
