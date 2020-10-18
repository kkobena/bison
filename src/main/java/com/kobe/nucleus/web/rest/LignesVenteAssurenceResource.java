package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.service.LignesVenteAssurenceService;
import com.kobe.nucleus.web.rest.errors.BadRequestAlertException;
import com.kobe.nucleus.service.dto.LignesVenteAssurenceDTO;

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
 * REST controller for managing {@link com.kobe.nucleus.domain.LignesVenteAssurence}.
 */
@RestController
@RequestMapping("/api")
public class LignesVenteAssurenceResource {

    private final Logger log = LoggerFactory.getLogger(LignesVenteAssurenceResource.class);

    private static final String ENTITY_NAME = "lignesVenteAssurence";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LignesVenteAssurenceService lignesVenteAssurenceService;

    public LignesVenteAssurenceResource(LignesVenteAssurenceService lignesVenteAssurenceService) {
        this.lignesVenteAssurenceService = lignesVenteAssurenceService;
    }

    /**
     * {@code POST  /lignes-vente-assurences} : Create a new lignesVenteAssurence.
     *
     * @param lignesVenteAssurenceDTO the lignesVenteAssurenceDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new lignesVenteAssurenceDTO, or with status {@code 400 (Bad Request)} if the lignesVenteAssurence has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/lignes-vente-assurences")
    public ResponseEntity<LignesVenteAssurenceDTO> createLignesVenteAssurence(@Valid @RequestBody LignesVenteAssurenceDTO lignesVenteAssurenceDTO) throws URISyntaxException {
        log.debug("REST request to save LignesVenteAssurence : {}", lignesVenteAssurenceDTO);
        if (lignesVenteAssurenceDTO.getId() != null) {
            throw new BadRequestAlertException("A new lignesVenteAssurence cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LignesVenteAssurenceDTO result = lignesVenteAssurenceService.save(lignesVenteAssurenceDTO);
        return ResponseEntity.created(new URI("/api/lignes-vente-assurences/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /lignes-vente-assurences} : Updates an existing lignesVenteAssurence.
     *
     * @param lignesVenteAssurenceDTO the lignesVenteAssurenceDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated lignesVenteAssurenceDTO,
     * or with status {@code 400 (Bad Request)} if the lignesVenteAssurenceDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the lignesVenteAssurenceDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/lignes-vente-assurences")
    public ResponseEntity<LignesVenteAssurenceDTO> updateLignesVenteAssurence(@Valid @RequestBody LignesVenteAssurenceDTO lignesVenteAssurenceDTO) throws URISyntaxException {
        log.debug("REST request to update LignesVenteAssurence : {}", lignesVenteAssurenceDTO);
        if (lignesVenteAssurenceDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LignesVenteAssurenceDTO result = lignesVenteAssurenceService.save(lignesVenteAssurenceDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, lignesVenteAssurenceDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /lignes-vente-assurences} : get all the lignesVenteAssurences.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of lignesVenteAssurences in body.
     */
    @GetMapping("/lignes-vente-assurences")
    public ResponseEntity<List<LignesVenteAssurenceDTO>> getAllLignesVenteAssurences(Pageable pageable) {
        log.debug("REST request to get a page of LignesVenteAssurences");
        Page<LignesVenteAssurenceDTO> page = lignesVenteAssurenceService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /lignes-vente-assurences/:id} : get the "id" lignesVenteAssurence.
     *
     * @param id the id of the lignesVenteAssurenceDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the lignesVenteAssurenceDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/lignes-vente-assurences/{id}")
    public ResponseEntity<LignesVenteAssurenceDTO> getLignesVenteAssurence(@PathVariable Long id) {
        log.debug("REST request to get LignesVenteAssurence : {}", id);
        Optional<LignesVenteAssurenceDTO> lignesVenteAssurenceDTO = lignesVenteAssurenceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(lignesVenteAssurenceDTO);
    }

    /**
     * {@code DELETE  /lignes-vente-assurences/:id} : delete the "id" lignesVenteAssurence.
     *
     * @param id the id of the lignesVenteAssurenceDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/lignes-vente-assurences/{id}")
    public ResponseEntity<Void> deleteLignesVenteAssurence(@PathVariable Long id) {
        log.debug("REST request to delete LignesVenteAssurence : {}", id);

        lignesVenteAssurenceService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
