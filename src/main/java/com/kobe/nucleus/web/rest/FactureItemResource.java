package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.service.FactureItemService;
import com.kobe.nucleus.web.rest.errors.BadRequestAlertException;
import com.kobe.nucleus.service.dto.FactureItemDTO;

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
 * REST controller for managing {@link com.kobe.nucleus.domain.FactureItem}.
 */
@RestController
@RequestMapping("/api")
public class FactureItemResource {

    private final Logger log = LoggerFactory.getLogger(FactureItemResource.class);

    private static final String ENTITY_NAME = "factureItem";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FactureItemService factureItemService;

    public FactureItemResource(FactureItemService factureItemService) {
        this.factureItemService = factureItemService;
    }

    /**
     * {@code POST  /facture-items} : Create a new factureItem.
     *
     * @param factureItemDTO the factureItemDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new factureItemDTO, or with status {@code 400 (Bad Request)} if the factureItem has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/facture-items")
    public ResponseEntity<FactureItemDTO> createFactureItem(@Valid @RequestBody FactureItemDTO factureItemDTO) throws URISyntaxException {
        log.debug("REST request to save FactureItem : {}", factureItemDTO);
        if (factureItemDTO.getId() != null) {
            throw new BadRequestAlertException("A new factureItem cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FactureItemDTO result = factureItemService.save(factureItemDTO);
        return ResponseEntity.created(new URI("/api/facture-items/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /facture-items} : Updates an existing factureItem.
     *
     * @param factureItemDTO the factureItemDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated factureItemDTO,
     * or with status {@code 400 (Bad Request)} if the factureItemDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the factureItemDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/facture-items")
    public ResponseEntity<FactureItemDTO> updateFactureItem(@Valid @RequestBody FactureItemDTO factureItemDTO) throws URISyntaxException {
        log.debug("REST request to update FactureItem : {}", factureItemDTO);
        if (factureItemDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FactureItemDTO result = factureItemService.save(factureItemDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, factureItemDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /facture-items} : get all the factureItems.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of factureItems in body.
     */
    @GetMapping("/facture-items")
    public ResponseEntity<List<FactureItemDTO>> getAllFactureItems(Pageable pageable) {
        log.debug("REST request to get a page of FactureItems");
        Page<FactureItemDTO> page = factureItemService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /facture-items/:id} : get the "id" factureItem.
     *
     * @param id the id of the factureItemDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the factureItemDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/facture-items/{id}")
    public ResponseEntity<FactureItemDTO> getFactureItem(@PathVariable Long id) {
        log.debug("REST request to get FactureItem : {}", id);
        Optional<FactureItemDTO> factureItemDTO = factureItemService.findOne(id);
        return ResponseUtil.wrapOrNotFound(factureItemDTO);
    }

    /**
     * {@code DELETE  /facture-items/:id} : delete the "id" factureItem.
     *
     * @param id the id of the factureItemDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/facture-items/{id}")
    public ResponseEntity<Void> deleteFactureItem(@PathVariable Long id) {
        log.debug("REST request to delete FactureItem : {}", id);

        factureItemService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
