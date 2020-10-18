package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.service.CategorieProduitService;
import com.kobe.nucleus.web.rest.errors.BadRequestAlertException;
import com.kobe.nucleus.service.dto.CategorieProduitDTO;

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
 * REST controller for managing {@link com.kobe.nucleus.domain.CategorieProduit}.
 */
@RestController
@RequestMapping("/api")
public class CategorieProduitResource {

    private final Logger log = LoggerFactory.getLogger(CategorieProduitResource.class);

    private static final String ENTITY_NAME = "categorieProduit";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CategorieProduitService categorieProduitService;

    public CategorieProduitResource(CategorieProduitService categorieProduitService) {
        this.categorieProduitService = categorieProduitService;
    }

    /**
     * {@code POST  /categorie-produits} : Create a new categorieProduit.
     *
     * @param categorieProduitDTO the categorieProduitDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new categorieProduitDTO, or with status {@code 400 (Bad Request)} if the categorieProduit has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/categorie-produits")
    public ResponseEntity<CategorieProduitDTO> createCategorieProduit(@Valid @RequestBody CategorieProduitDTO categorieProduitDTO) throws URISyntaxException {
        log.debug("REST request to save CategorieProduit : {}", categorieProduitDTO);
        if (categorieProduitDTO.getId() != null) {
            throw new BadRequestAlertException("A new categorieProduit cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CategorieProduitDTO result = categorieProduitService.save(categorieProduitDTO);
        return ResponseEntity.created(new URI("/api/categorie-produits/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /categorie-produits} : Updates an existing categorieProduit.
     *
     * @param categorieProduitDTO the categorieProduitDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated categorieProduitDTO,
     * or with status {@code 400 (Bad Request)} if the categorieProduitDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the categorieProduitDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/categorie-produits")
    public ResponseEntity<CategorieProduitDTO> updateCategorieProduit(@Valid @RequestBody CategorieProduitDTO categorieProduitDTO) throws URISyntaxException {
        log.debug("REST request to update CategorieProduit : {}", categorieProduitDTO);
        if (categorieProduitDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CategorieProduitDTO result = categorieProduitService.save(categorieProduitDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, categorieProduitDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /categorie-produits} : get all the categorieProduits.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of categorieProduits in body.
     */
    @GetMapping("/categorie-produits")
    public ResponseEntity<List<CategorieProduitDTO>> getAllCategorieProduits(Pageable pageable) {
        log.debug("REST request to get a page of CategorieProduits");
        Page<CategorieProduitDTO> page = categorieProduitService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /categorie-produits/:id} : get the "id" categorieProduit.
     *
     * @param id the id of the categorieProduitDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the categorieProduitDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/categorie-produits/{id}")
    public ResponseEntity<CategorieProduitDTO> getCategorieProduit(@PathVariable Long id) {
        log.debug("REST request to get CategorieProduit : {}", id);
        Optional<CategorieProduitDTO> categorieProduitDTO = categorieProduitService.findOne(id);
        return ResponseUtil.wrapOrNotFound(categorieProduitDTO);
    }

    /**
     * {@code DELETE  /categorie-produits/:id} : delete the "id" categorieProduit.
     *
     * @param id the id of the categorieProduitDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/categorie-produits/{id}")
    public ResponseEntity<Void> deleteCategorieProduit(@PathVariable Long id) {
        log.debug("REST request to delete CategorieProduit : {}", id);

        categorieProduitService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
