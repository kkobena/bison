package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.service.CategorieAyantDroitService;
import com.kobe.nucleus.web.rest.errors.BadRequestAlertException;
import com.kobe.nucleus.service.dto.CategorieAyantDroitDTO;

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
 * REST controller for managing {@link com.kobe.nucleus.domain.CategorieAyantDroit}.
 */
@RestController
@RequestMapping("/api")
public class CategorieAyantDroitResource {

    private final Logger log = LoggerFactory.getLogger(CategorieAyantDroitResource.class);

    private static final String ENTITY_NAME = "categorieAyantDroit";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CategorieAyantDroitService categorieAyantDroitService;

    public CategorieAyantDroitResource(CategorieAyantDroitService categorieAyantDroitService) {
        this.categorieAyantDroitService = categorieAyantDroitService;
    }

    /**
     * {@code POST  /categorie-ayant-droits} : Create a new categorieAyantDroit.
     *
     * @param categorieAyantDroitDTO the categorieAyantDroitDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new categorieAyantDroitDTO, or with status {@code 400 (Bad Request)} if the categorieAyantDroit has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/categorie-ayant-droits")
    public ResponseEntity<CategorieAyantDroitDTO> createCategorieAyantDroit(@Valid @RequestBody CategorieAyantDroitDTO categorieAyantDroitDTO) throws URISyntaxException {
        log.debug("REST request to save CategorieAyantDroit : {}", categorieAyantDroitDTO);
        if (categorieAyantDroitDTO.getId() != null) {
            throw new BadRequestAlertException("A new categorieAyantDroit cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CategorieAyantDroitDTO result = categorieAyantDroitService.save(categorieAyantDroitDTO);
        return ResponseEntity.created(new URI("/api/categorie-ayant-droits/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /categorie-ayant-droits} : Updates an existing categorieAyantDroit.
     *
     * @param categorieAyantDroitDTO the categorieAyantDroitDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated categorieAyantDroitDTO,
     * or with status {@code 400 (Bad Request)} if the categorieAyantDroitDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the categorieAyantDroitDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/categorie-ayant-droits")
    public ResponseEntity<CategorieAyantDroitDTO> updateCategorieAyantDroit(@Valid @RequestBody CategorieAyantDroitDTO categorieAyantDroitDTO) throws URISyntaxException {
        log.debug("REST request to update CategorieAyantDroit : {}", categorieAyantDroitDTO);
        if (categorieAyantDroitDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CategorieAyantDroitDTO result = categorieAyantDroitService.save(categorieAyantDroitDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, categorieAyantDroitDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /categorie-ayant-droits} : get all the categorieAyantDroits.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of categorieAyantDroits in body.
     */
    @GetMapping("/categorie-ayant-droits")
    public ResponseEntity<List<CategorieAyantDroitDTO>> getAllCategorieAyantDroits(Pageable pageable) {
        log.debug("REST request to get a page of CategorieAyantDroits");
        Page<CategorieAyantDroitDTO> page = categorieAyantDroitService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /categorie-ayant-droits/:id} : get the "id" categorieAyantDroit.
     *
     * @param id the id of the categorieAyantDroitDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the categorieAyantDroitDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/categorie-ayant-droits/{id}")
    public ResponseEntity<CategorieAyantDroitDTO> getCategorieAyantDroit(@PathVariable Long id) {
        log.debug("REST request to get CategorieAyantDroit : {}", id);
        Optional<CategorieAyantDroitDTO> categorieAyantDroitDTO = categorieAyantDroitService.findOne(id);
        return ResponseUtil.wrapOrNotFound(categorieAyantDroitDTO);
    }

    /**
     * {@code DELETE  /categorie-ayant-droits/:id} : delete the "id" categorieAyantDroit.
     *
     * @param id the id of the categorieAyantDroitDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/categorie-ayant-droits/{id}")
    public ResponseEntity<Void> deleteCategorieAyantDroit(@PathVariable Long id) {
        log.debug("REST request to delete CategorieAyantDroit : {}", id);
        categorieAyantDroitService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
