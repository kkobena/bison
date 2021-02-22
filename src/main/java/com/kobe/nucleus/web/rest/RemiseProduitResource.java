package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.domain.RemiseProduit;
import com.kobe.nucleus.repository.RemiseProduitRepository;
import com.kobe.nucleus.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.kobe.nucleus.domain.RemiseProduit}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class RemiseProduitResource {

    private final Logger log = LoggerFactory.getLogger(RemiseProduitResource.class);

    private static final String ENTITY_NAME = "remiseProduit";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RemiseProduitRepository remiseProduitRepository;

    public RemiseProduitResource(RemiseProduitRepository remiseProduitRepository) {
        this.remiseProduitRepository = remiseProduitRepository;
    }

    /**
     * {@code POST  /remise-produits} : Create a new remiseProduit.
     *
     * @param remiseProduit the remiseProduit to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new remiseProduit, or with status {@code 400 (Bad Request)} if the remiseProduit has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/remise-produits")
    public ResponseEntity<RemiseProduit> createRemiseProduit(@Valid @RequestBody RemiseProduit remiseProduit) throws URISyntaxException {
        log.debug("REST request to save RemiseProduit : {}", remiseProduit);
        if (remiseProduit.getId() != null) {
            throw new BadRequestAlertException("A new remiseProduit cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RemiseProduit result = remiseProduitRepository.save(remiseProduit);
        return ResponseEntity.created(new URI("/api/remise-produits/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /remise-produits} : Updates an existing remiseProduit.
     *
     * @param remiseProduit the remiseProduit to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated remiseProduit,
     * or with status {@code 400 (Bad Request)} if the remiseProduit is not valid,
     * or with status {@code 500 (Internal Server Error)} if the remiseProduit couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/remise-produits")
    public ResponseEntity<RemiseProduit> updateRemiseProduit(@Valid @RequestBody RemiseProduit remiseProduit) throws URISyntaxException {
        log.debug("REST request to update RemiseProduit : {}", remiseProduit);
        if (remiseProduit.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RemiseProduit result = remiseProduitRepository.save(remiseProduit);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, remiseProduit.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /remise-produits} : get all the remiseProduits.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of remiseProduits in body.
     */
    @GetMapping("/remise-produits")
    public List<RemiseProduit> getAllRemiseProduits() {
        log.debug("REST request to get all RemiseProduits");
        return remiseProduitRepository.findAll();
    }

    /**
     * {@code GET  /remise-produits/:id} : get the "id" remiseProduit.
     *
     * @param id the id of the remiseProduit to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the remiseProduit, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/remise-produits/{id}")
    public ResponseEntity<RemiseProduit> getRemiseProduit(@PathVariable Long id) {
        log.debug("REST request to get RemiseProduit : {}", id);
        Optional<RemiseProduit> remiseProduit = remiseProduitRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(remiseProduit);
    }

    /**
     * {@code DELETE  /remise-produits/:id} : delete the "id" remiseProduit.
     *
     * @param id the id of the remiseProduit to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/remise-produits/{id}")
    public ResponseEntity<Void> deleteRemiseProduit(@PathVariable Long id) {
        log.debug("REST request to delete RemiseProduit : {}", id);

        remiseProduitRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
