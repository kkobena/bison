package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.service.MvtsProduitService;
import com.kobe.nucleus.web.rest.errors.BadRequestAlertException;
import com.kobe.nucleus.service.dto.MvtsProduitDTO;

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
 * REST controller for managing {@link com.kobe.nucleus.domain.MvtsProduit}.
 */
@RestController
@RequestMapping("/api")
public class MvtsProduitResource {

    private final Logger log = LoggerFactory.getLogger(MvtsProduitResource.class);

    private static final String ENTITY_NAME = "mvtsProduit";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MvtsProduitService mvtsProduitService;

    public MvtsProduitResource(MvtsProduitService mvtsProduitService) {
        this.mvtsProduitService = mvtsProduitService;
    }

    /**
     * {@code POST  /mvts-produits} : Create a new mvtsProduit.
     *
     * @param mvtsProduitDTO the mvtsProduitDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mvtsProduitDTO, or with status {@code 400 (Bad Request)} if the mvtsProduit has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/mvts-produits")
    public ResponseEntity<MvtsProduitDTO> createMvtsProduit(@Valid @RequestBody MvtsProduitDTO mvtsProduitDTO) throws URISyntaxException {
        log.debug("REST request to save MvtsProduit : {}", mvtsProduitDTO);
        if (mvtsProduitDTO.getId() != null) {
            throw new BadRequestAlertException("A new mvtsProduit cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MvtsProduitDTO result = mvtsProduitService.save(mvtsProduitDTO);
        return ResponseEntity.created(new URI("/api/mvts-produits/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /mvts-produits} : Updates an existing mvtsProduit.
     *
     * @param mvtsProduitDTO the mvtsProduitDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mvtsProduitDTO,
     * or with status {@code 400 (Bad Request)} if the mvtsProduitDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mvtsProduitDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/mvts-produits")
    public ResponseEntity<MvtsProduitDTO> updateMvtsProduit(@Valid @RequestBody MvtsProduitDTO mvtsProduitDTO) throws URISyntaxException {
        log.debug("REST request to update MvtsProduit : {}", mvtsProduitDTO);
        if (mvtsProduitDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MvtsProduitDTO result = mvtsProduitService.save(mvtsProduitDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, mvtsProduitDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /mvts-produits} : get all the mvtsProduits.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mvtsProduits in body.
     */
    @GetMapping("/mvts-produits")
    public ResponseEntity<List<MvtsProduitDTO>> getAllMvtsProduits(Pageable pageable) {
        log.debug("REST request to get a page of MvtsProduits");
        Page<MvtsProduitDTO> page = mvtsProduitService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /mvts-produits/:id} : get the "id" mvtsProduit.
     *
     * @param id the id of the mvtsProduitDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mvtsProduitDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/mvts-produits/{id}")
    public ResponseEntity<MvtsProduitDTO> getMvtsProduit(@PathVariable Long id) {
        log.debug("REST request to get MvtsProduit : {}", id);
        Optional<MvtsProduitDTO> mvtsProduitDTO = mvtsProduitService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mvtsProduitDTO);
    }

    /**
     * {@code DELETE  /mvts-produits/:id} : delete the "id" mvtsProduit.
     *
     * @param id the id of the mvtsProduitDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/mvts-produits/{id}")
    public ResponseEntity<Void> deleteMvtsProduit(@PathVariable Long id) {
        log.debug("REST request to delete MvtsProduit : {}", id);

        mvtsProduitService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
