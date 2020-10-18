package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.service.RetourItemService;
import com.kobe.nucleus.web.rest.errors.BadRequestAlertException;
import com.kobe.nucleus.service.dto.RetourItemDTO;

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
 * REST controller for managing {@link com.kobe.nucleus.domain.RetourItem}.
 */
@RestController
@RequestMapping("/api")
public class RetourItemResource {

    private final Logger log = LoggerFactory.getLogger(RetourItemResource.class);

    private static final String ENTITY_NAME = "retourItem";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RetourItemService retourItemService;

    public RetourItemResource(RetourItemService retourItemService) {
        this.retourItemService = retourItemService;
    }

    /**
     * {@code POST  /retour-items} : Create a new retourItem.
     *
     * @param retourItemDTO the retourItemDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new retourItemDTO, or with status {@code 400 (Bad Request)} if the retourItem has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/retour-items")
    public ResponseEntity<RetourItemDTO> createRetourItem(@Valid @RequestBody RetourItemDTO retourItemDTO) throws URISyntaxException {
        log.debug("REST request to save RetourItem : {}", retourItemDTO);
        if (retourItemDTO.getId() != null) {
            throw new BadRequestAlertException("A new retourItem cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RetourItemDTO result = retourItemService.save(retourItemDTO);
        return ResponseEntity.created(new URI("/api/retour-items/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /retour-items} : Updates an existing retourItem.
     *
     * @param retourItemDTO the retourItemDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated retourItemDTO,
     * or with status {@code 400 (Bad Request)} if the retourItemDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the retourItemDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/retour-items")
    public ResponseEntity<RetourItemDTO> updateRetourItem(@Valid @RequestBody RetourItemDTO retourItemDTO) throws URISyntaxException {
        log.debug("REST request to update RetourItem : {}", retourItemDTO);
        if (retourItemDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RetourItemDTO result = retourItemService.save(retourItemDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, retourItemDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /retour-items} : get all the retourItems.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of retourItems in body.
     */
    @GetMapping("/retour-items")
    public ResponseEntity<List<RetourItemDTO>> getAllRetourItems(Pageable pageable) {
        log.debug("REST request to get a page of RetourItems");
        Page<RetourItemDTO> page = retourItemService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /retour-items/:id} : get the "id" retourItem.
     *
     * @param id the id of the retourItemDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the retourItemDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/retour-items/{id}")
    public ResponseEntity<RetourItemDTO> getRetourItem(@PathVariable Long id) {
        log.debug("REST request to get RetourItem : {}", id);
        Optional<RetourItemDTO> retourItemDTO = retourItemService.findOne(id);
        return ResponseUtil.wrapOrNotFound(retourItemDTO);
    }

    /**
     * {@code DELETE  /retour-items/:id} : delete the "id" retourItem.
     *
     * @param id the id of the retourItemDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/retour-items/{id}")
    public ResponseEntity<Void> deleteRetourItem(@PathVariable Long id) {
        log.debug("REST request to delete RetourItem : {}", id);

        retourItemService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
