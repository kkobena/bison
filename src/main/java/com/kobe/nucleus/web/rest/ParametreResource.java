package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.domain.Parametre;
import com.kobe.nucleus.repository.ParametreRepository;
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
 * REST controller for managing {@link com.kobe.nucleus.domain.Parametre}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ParametreResource {

    private final Logger log = LoggerFactory.getLogger(ParametreResource.class);

    private static final String ENTITY_NAME = "parametre";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ParametreRepository parametreRepository;

    public ParametreResource(ParametreRepository parametreRepository) {
        this.parametreRepository = parametreRepository;
    }

    /**
     * {@code POST  /parametres} : Create a new parametre.
     *
     * @param parametre the parametre to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new parametre, or with status {@code 400 (Bad Request)} if the parametre has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/parametres")
    public ResponseEntity<Parametre> createParametre(@Valid @RequestBody Parametre parametre) throws URISyntaxException {
        log.debug("REST request to save Parametre : {}", parametre);
        if (parametre.getId() != null) {
            throw new BadRequestAlertException("A new parametre cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Parametre result = parametreRepository.save(parametre);
        return ResponseEntity.created(new URI("/api/parametres/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /parametres} : Updates an existing parametre.
     *
     * @param parametre the parametre to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated parametre,
     * or with status {@code 400 (Bad Request)} if the parametre is not valid,
     * or with status {@code 500 (Internal Server Error)} if the parametre couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/parametres")
    public ResponseEntity<Parametre> updateParametre(@Valid @RequestBody Parametre parametre) throws URISyntaxException {
        log.debug("REST request to update Parametre : {}", parametre);
        if (parametre.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Parametre result = parametreRepository.save(parametre);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, parametre.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /parametres} : get all the parametres.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of parametres in body.
     */
    @GetMapping("/parametres")
    public List<Parametre> getAllParametres() {
        log.debug("REST request to get all Parametres");
        return parametreRepository.findAll();
    }

    /**
     * {@code GET  /parametres/:id} : get the "id" parametre.
     *
     * @param id the id of the parametre to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the parametre, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/parametres/{id}")
    public ResponseEntity<Parametre> getParametre(@PathVariable Long id) {
        log.debug("REST request to get Parametre : {}", id);
        Optional<Parametre> parametre = parametreRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(parametre);
    }

    /**
     * {@code DELETE  /parametres/:id} : delete the "id" parametre.
     *
     * @param id the id of the parametre to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/parametres/{id}")
    public ResponseEntity<Void> deleteParametre(@PathVariable Long id) {
        log.debug("REST request to delete Parametre : {}", id);

        parametreRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
