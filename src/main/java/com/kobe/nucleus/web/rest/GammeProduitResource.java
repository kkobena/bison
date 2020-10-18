package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.service.GammeProduitService;
import com.kobe.nucleus.web.rest.errors.BadRequestAlertException;
import com.kobe.nucleus.service.dto.GammeProduitDTO;

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
 * REST controller for managing {@link com.kobe.nucleus.domain.GammeProduit}.
 */
@RestController
@RequestMapping("/api")
public class GammeProduitResource {

    private final Logger log = LoggerFactory.getLogger(GammeProduitResource.class);

    private static final String ENTITY_NAME = "gammeProduit";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GammeProduitService gammeProduitService;

    public GammeProduitResource(GammeProduitService gammeProduitService) {
        this.gammeProduitService = gammeProduitService;
    }

    /**
     * {@code POST  /gamme-produits} : Create a new gammeProduit.
     *
     * @param gammeProduitDTO the gammeProduitDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new gammeProduitDTO, or with status {@code 400 (Bad Request)} if the gammeProduit has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/gamme-produits")
    public ResponseEntity<GammeProduitDTO> createGammeProduit(@Valid @RequestBody GammeProduitDTO gammeProduitDTO) throws URISyntaxException {
        log.debug("REST request to save GammeProduit : {}", gammeProduitDTO);
        if (gammeProduitDTO.getId() != null) {
            throw new BadRequestAlertException("A new gammeProduit cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GammeProduitDTO result = gammeProduitService.save(gammeProduitDTO);
        return ResponseEntity.created(new URI("/api/gamme-produits/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /gamme-produits} : Updates an existing gammeProduit.
     *
     * @param gammeProduitDTO the gammeProduitDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated gammeProduitDTO,
     * or with status {@code 400 (Bad Request)} if the gammeProduitDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the gammeProduitDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/gamme-produits")
    public ResponseEntity<GammeProduitDTO> updateGammeProduit(@Valid @RequestBody GammeProduitDTO gammeProduitDTO) throws URISyntaxException {
        log.debug("REST request to update GammeProduit : {}", gammeProduitDTO);
        if (gammeProduitDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GammeProduitDTO result = gammeProduitService.save(gammeProduitDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, gammeProduitDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /gamme-produits} : get all the gammeProduits.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of gammeProduits in body.
     */
    @GetMapping(value = "/gamme-produits",params = {"search"})
    public ResponseEntity<List<GammeProduitDTO>> getAllGammeProduits
    (
        @RequestParam("search") String search,
        Pageable pageable
    ) {
        log.debug("REST request to get a page of GammeProduits");
        Page<GammeProduitDTO> page = gammeProduitService.findAll(search,pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /gamme-produits/:id} : get the "id" gammeProduit.
     *
     * @param id the id of the gammeProduitDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the gammeProduitDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/gamme-produits/{id}")
    public ResponseEntity<GammeProduitDTO> getGammeProduit(@PathVariable Long id) {
        log.debug("REST request to get GammeProduit : {}", id);
        Optional<GammeProduitDTO> gammeProduitDTO = gammeProduitService.findOne(id);
        return ResponseUtil.wrapOrNotFound(gammeProduitDTO);
    }

    /**
     * {@code DELETE  /gamme-produits/:id} : delete the "id" gammeProduit.
     *
     * @param id the id of the gammeProduitDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/gamme-produits/{id}")
    public ResponseEntity<Void> deleteGammeProduit(@PathVariable Long id) {
        log.debug("REST request to delete GammeProduit : {}", id);

        gammeProduitService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
