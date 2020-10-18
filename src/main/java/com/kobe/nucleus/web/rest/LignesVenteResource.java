package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.service.LignesVenteService;
import com.kobe.nucleus.web.rest.errors.BadRequestAlertException;
import com.kobe.nucleus.service.dto.LignesVenteDTO;

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
 * REST controller for managing {@link com.kobe.nucleus.domain.LignesVente}.
 */
@RestController
@RequestMapping("/api")
public class LignesVenteResource {

    private final Logger log = LoggerFactory.getLogger(LignesVenteResource.class);

    private static final String ENTITY_NAME = "lignesVente";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LignesVenteService lignesVenteService;

    public LignesVenteResource(LignesVenteService lignesVenteService) {
        this.lignesVenteService = lignesVenteService;
    }

    /**
     * {@code POST  /lignes-ventes} : Create a new lignesVente.
     *
     * @param lignesVenteDTO the lignesVenteDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new lignesVenteDTO, or with status {@code 400 (Bad Request)} if the lignesVente has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/lignes-ventes")
    public ResponseEntity<LignesVenteDTO> createLignesVente(@Valid @RequestBody LignesVenteDTO lignesVenteDTO) throws URISyntaxException {
        log.debug("REST request to save LignesVente : {}", lignesVenteDTO);
        if (lignesVenteDTO.getId() != null) {
            throw new BadRequestAlertException("A new lignesVente cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LignesVenteDTO result = lignesVenteService.save(lignesVenteDTO);
        return ResponseEntity.created(new URI("/api/lignes-ventes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /lignes-ventes} : Updates an existing lignesVente.
     *
     * @param lignesVenteDTO the lignesVenteDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated lignesVenteDTO,
     * or with status {@code 400 (Bad Request)} if the lignesVenteDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the lignesVenteDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/lignes-ventes")
    public ResponseEntity<LignesVenteDTO> updateLignesVente(@Valid @RequestBody LignesVenteDTO lignesVenteDTO) throws URISyntaxException {
        log.debug("REST request to update LignesVente : {}", lignesVenteDTO);
        if (lignesVenteDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LignesVenteDTO result = lignesVenteService.save(lignesVenteDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, lignesVenteDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /lignes-ventes} : get all the lignesVentes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of lignesVentes in body.
     */
    @GetMapping("/lignes-ventes")
    public ResponseEntity<List<LignesVenteDTO>> getAllLignesVentes(Pageable pageable) {
        log.debug("REST request to get a page of LignesVentes");
        Page<LignesVenteDTO> page = lignesVenteService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /lignes-ventes/:id} : get the "id" lignesVente.
     *
     * @param id the id of the lignesVenteDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the lignesVenteDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/lignes-ventes/{id}")
    public ResponseEntity<LignesVenteDTO> getLignesVente(@PathVariable Long id) {
        log.debug("REST request to get LignesVente : {}", id);
        Optional<LignesVenteDTO> lignesVenteDTO = lignesVenteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(lignesVenteDTO);
    }

    /**
     * {@code DELETE  /lignes-ventes/:id} : delete the "id" lignesVente.
     *
     * @param id the id of the lignesVenteDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/lignes-ventes/{id}")
    public ResponseEntity<Void> deleteLignesVente(@PathVariable Long id) {
        log.debug("REST request to delete LignesVente : {}", id);

        lignesVenteService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
