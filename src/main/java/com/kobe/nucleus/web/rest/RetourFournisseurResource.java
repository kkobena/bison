package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.service.RetourFournisseurService;
import com.kobe.nucleus.web.rest.errors.BadRequestAlertException;
import com.kobe.nucleus.service.dto.RetourFournisseurDTO;

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
 * REST controller for managing {@link com.kobe.nucleus.domain.RetourFournisseur}.
 */
@RestController
@RequestMapping("/api")
public class RetourFournisseurResource {

    private final Logger log = LoggerFactory.getLogger(RetourFournisseurResource.class);

    private static final String ENTITY_NAME = "retourFournisseur";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RetourFournisseurService retourFournisseurService;

    public RetourFournisseurResource(RetourFournisseurService retourFournisseurService) {
        this.retourFournisseurService = retourFournisseurService;
    }

    /**
     * {@code POST  /retour-fournisseurs} : Create a new retourFournisseur.
     *
     * @param retourFournisseurDTO the retourFournisseurDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new retourFournisseurDTO, or with status {@code 400 (Bad Request)} if the retourFournisseur has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/retour-fournisseurs")
    public ResponseEntity<RetourFournisseurDTO> createRetourFournisseur(@Valid @RequestBody RetourFournisseurDTO retourFournisseurDTO) throws URISyntaxException {
        log.debug("REST request to save RetourFournisseur : {}", retourFournisseurDTO);
        if (retourFournisseurDTO.getId() != null) {
            throw new BadRequestAlertException("A new retourFournisseur cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RetourFournisseurDTO result = retourFournisseurService.save(retourFournisseurDTO);
        return ResponseEntity.created(new URI("/api/retour-fournisseurs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /retour-fournisseurs} : Updates an existing retourFournisseur.
     *
     * @param retourFournisseurDTO the retourFournisseurDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated retourFournisseurDTO,
     * or with status {@code 400 (Bad Request)} if the retourFournisseurDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the retourFournisseurDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/retour-fournisseurs")
    public ResponseEntity<RetourFournisseurDTO> updateRetourFournisseur(@Valid @RequestBody RetourFournisseurDTO retourFournisseurDTO) throws URISyntaxException {
        log.debug("REST request to update RetourFournisseur : {}", retourFournisseurDTO);
        if (retourFournisseurDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RetourFournisseurDTO result = retourFournisseurService.save(retourFournisseurDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, retourFournisseurDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /retour-fournisseurs} : get all the retourFournisseurs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of retourFournisseurs in body.
     */
    @GetMapping("/retour-fournisseurs")
    public ResponseEntity<List<RetourFournisseurDTO>> getAllRetourFournisseurs(Pageable pageable) {
        log.debug("REST request to get a page of RetourFournisseurs");
        Page<RetourFournisseurDTO> page = retourFournisseurService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /retour-fournisseurs/:id} : get the "id" retourFournisseur.
     *
     * @param id the id of the retourFournisseurDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the retourFournisseurDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/retour-fournisseurs/{id}")
    public ResponseEntity<RetourFournisseurDTO> getRetourFournisseur(@PathVariable Long id) {
        log.debug("REST request to get RetourFournisseur : {}", id);
        Optional<RetourFournisseurDTO> retourFournisseurDTO = retourFournisseurService.findOne(id);
        return ResponseUtil.wrapOrNotFound(retourFournisseurDTO);
    }

    /**
     * {@code DELETE  /retour-fournisseurs/:id} : delete the "id" retourFournisseur.
     *
     * @param id the id of the retourFournisseurDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/retour-fournisseurs/{id}")
    public ResponseEntity<Void> deleteRetourFournisseur(@PathVariable Long id) {
        log.debug("REST request to delete RetourFournisseur : {}", id);

        retourFournisseurService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
