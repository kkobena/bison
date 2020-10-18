package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.service.MvtProduitService;
import com.kobe.nucleus.web.rest.errors.BadRequestAlertException;
import com.kobe.nucleus.service.dto.MvtProduitDTO;

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
 * REST controller for managing {@link com.kobe.nucleus.domain.MvtProduit}.
 */
@RestController
@RequestMapping("/api")
public class MvtProduitResource {

    private final Logger log = LoggerFactory.getLogger(MvtProduitResource.class);

    private static final String ENTITY_NAME = "mvtProduit";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MvtProduitService mvtProduitService;

    public MvtProduitResource(MvtProduitService mvtProduitService) {
        this.mvtProduitService = mvtProduitService;
    }

    /**
     * {@code POST  /mvt-produits} : Create a new mvtProduit.
     *
     * @param mvtProduitDTO the mvtProduitDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mvtProduitDTO, or with status {@code 400 (Bad Request)} if the mvtProduit has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/mvt-produits")
    public ResponseEntity<MvtProduitDTO> createMvtProduit(@Valid @RequestBody MvtProduitDTO mvtProduitDTO) throws URISyntaxException {
        log.debug("REST request to save MvtProduit : {}", mvtProduitDTO);
        if (mvtProduitDTO.getId() != null) {
            throw new BadRequestAlertException("A new mvtProduit cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MvtProduitDTO result = mvtProduitService.save(mvtProduitDTO);
        return ResponseEntity.created(new URI("/api/mvt-produits/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /mvt-produits} : Updates an existing mvtProduit.
     *
     * @param mvtProduitDTO the mvtProduitDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mvtProduitDTO,
     * or with status {@code 400 (Bad Request)} if the mvtProduitDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mvtProduitDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/mvt-produits")
    public ResponseEntity<MvtProduitDTO> updateMvtProduit(@Valid @RequestBody MvtProduitDTO mvtProduitDTO) throws URISyntaxException {
        log.debug("REST request to update MvtProduit : {}", mvtProduitDTO);
        if (mvtProduitDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MvtProduitDTO result = mvtProduitService.save(mvtProduitDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, mvtProduitDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /mvt-produits} : get all the mvtProduits.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mvtProduits in body.
     */
    @GetMapping("/mvt-produits")
    public ResponseEntity<List<MvtProduitDTO>> getAllMvtProduits(Pageable pageable) {
        log.debug("REST request to get a page of MvtProduits");
        Page<MvtProduitDTO> page = mvtProduitService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /mvt-produits/:id} : get the "id" mvtProduit.
     *
     * @param id the id of the mvtProduitDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mvtProduitDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/mvt-produits/{id}")
    public ResponseEntity<MvtProduitDTO> getMvtProduit(@PathVariable Long id) {
        log.debug("REST request to get MvtProduit : {}", id);
        Optional<MvtProduitDTO> mvtProduitDTO = mvtProduitService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mvtProduitDTO);
    }

    /**
     * {@code DELETE  /mvt-produits/:id} : delete the "id" mvtProduit.
     *
     * @param id the id of the mvtProduitDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/mvt-produits/{id}")
    public ResponseEntity<Void> deleteMvtProduit(@PathVariable Long id) {
        log.debug("REST request to delete MvtProduit : {}", id);

        mvtProduitService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
