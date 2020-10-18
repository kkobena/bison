package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.service.FamilleProduitService;
import com.kobe.nucleus.web.rest.errors.BadRequestAlertException;
import com.kobe.nucleus.service.dto.FamilleProduitDTO;

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
 * REST controller for managing {@link com.kobe.nucleus.domain.FamilleProduit}.
 */
@RestController
@RequestMapping("/api")
public class FamilleProduitResource {

    private final Logger log = LoggerFactory.getLogger(FamilleProduitResource.class);

    private static final String ENTITY_NAME = "familleProduit";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FamilleProduitService familleProduitService;

    public FamilleProduitResource(FamilleProduitService familleProduitService) {
        this.familleProduitService = familleProduitService;
    }

    /**
     * {@code POST  /famille-produits} : Create a new familleProduit.
     *
     * @param familleProduitDTO the familleProduitDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new familleProduitDTO, or with status {@code 400 (Bad Request)} if the familleProduit has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/famille-produits")
    public ResponseEntity<FamilleProduitDTO> createFamilleProduit(@Valid @RequestBody FamilleProduitDTO familleProduitDTO) throws URISyntaxException {
        log.debug("REST request to save FamilleProduit : {}", familleProduitDTO);
        if (familleProduitDTO.getId() != null) {
            throw new BadRequestAlertException("A new familleProduit cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FamilleProduitDTO result = familleProduitService.save(familleProduitDTO);
        return ResponseEntity.created(new URI("/api/famille-produits/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /famille-produits} : Updates an existing familleProduit.
     *
     * @param familleProduitDTO the familleProduitDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated familleProduitDTO,
     * or with status {@code 400 (Bad Request)} if the familleProduitDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the familleProduitDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/famille-produits")
    public ResponseEntity<FamilleProduitDTO> updateFamilleProduit(@Valid @RequestBody FamilleProduitDTO familleProduitDTO) throws URISyntaxException {
        log.debug("REST request to update FamilleProduit : {}", familleProduitDTO);
        if (familleProduitDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FamilleProduitDTO result = familleProduitService.save(familleProduitDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, familleProduitDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /famille-produits} : get all the familleProduits.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of familleProduits in body.
     */
    @GetMapping("/famille-produits")
    public ResponseEntity<List<FamilleProduitDTO>> getAllFamilleProduits(Pageable pageable) {
        log.debug("REST request to get a page of FamilleProduits");
        Page<FamilleProduitDTO> page = familleProduitService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /famille-produits/:id} : get the "id" familleProduit.
     *
     * @param id the id of the familleProduitDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the familleProduitDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/famille-produits/{id}")
    public ResponseEntity<FamilleProduitDTO> getFamilleProduit(@PathVariable Long id) {
        log.debug("REST request to get FamilleProduit : {}", id);
        Optional<FamilleProduitDTO> familleProduitDTO = familleProduitService.findOne(id);
        return ResponseUtil.wrapOrNotFound(familleProduitDTO);
    }

    /**
     * {@code DELETE  /famille-produits/:id} : delete the "id" familleProduit.
     *
     * @param id the id of the familleProduitDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/famille-produits/{id}")
    public ResponseEntity<Void> deleteFamilleProduit(@PathVariable Long id) {
        log.debug("REST request to delete FamilleProduit : {}", id);

        familleProduitService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
