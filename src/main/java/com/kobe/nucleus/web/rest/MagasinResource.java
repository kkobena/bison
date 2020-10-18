package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.service.MagasinService;
import com.kobe.nucleus.web.rest.errors.BadRequestAlertException;
import com.kobe.nucleus.service.dto.MagasinDTO;

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
 * REST controller for managing {@link com.kobe.nucleus.domain.Magasin}.
 */
@RestController
@RequestMapping("/api")
public class MagasinResource {

    private final Logger log = LoggerFactory.getLogger(MagasinResource.class);

    private static final String ENTITY_NAME = "magasin";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MagasinService magasinService;

    public MagasinResource(MagasinService magasinService) {
        this.magasinService = magasinService;
    }

    /**
     * {@code POST  /magasins} : Create a new magasin.
     *
     * @param magasinDTO the magasinDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new magasinDTO, or with status {@code 400 (Bad Request)} if the magasin has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/magasins")
    public ResponseEntity<MagasinDTO> createMagasin(@Valid @RequestBody MagasinDTO magasinDTO) throws URISyntaxException {
        log.debug("REST request to save Magasin : {}", magasinDTO);
        if (magasinDTO.getId() != null) {
            throw new BadRequestAlertException("A new magasin cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MagasinDTO result = magasinService.save(magasinDTO);
        return ResponseEntity.created(new URI("/api/magasins/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /magasins} : Updates an existing magasin.
     *
     * @param magasinDTO the magasinDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated magasinDTO,
     * or with status {@code 400 (Bad Request)} if the magasinDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the magasinDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/magasins")
    public ResponseEntity<MagasinDTO> updateMagasin(@Valid @RequestBody MagasinDTO magasinDTO) throws URISyntaxException {
        log.debug("REST request to update Magasin : {}", magasinDTO);
        if (magasinDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MagasinDTO result = magasinService.save(magasinDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, magasinDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /magasins} : get all the magasins.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of magasins in body.
     */
    @GetMapping("/magasins")
    public ResponseEntity<List<MagasinDTO>> getAllMagasins(Pageable pageable) {
        log.debug("REST request to get a page of Magasins");
        Page<MagasinDTO> page = magasinService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /magasins/:id} : get the "id" magasin.
     *
     * @param id the id of the magasinDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the magasinDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/magasins/{id}")
    public ResponseEntity<MagasinDTO> getMagasin(@PathVariable Long id) {
        log.debug("REST request to get Magasin : {}", id);
        Optional<MagasinDTO> magasinDTO = magasinService.findOne(id);
        return ResponseUtil.wrapOrNotFound(magasinDTO);
    }

    /**
     * {@code DELETE  /magasins/:id} : delete the "id" magasin.
     *
     * @param id the id of the magasinDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/magasins/{id}")
    public ResponseEntity<Void> deleteMagasin(@PathVariable Long id) {
        log.debug("REST request to delete Magasin : {}", id);

        magasinService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
