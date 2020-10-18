package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.service.MotifService;
import com.kobe.nucleus.web.rest.errors.BadRequestAlertException;
import com.kobe.nucleus.service.dto.MotifDTO;

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
 * REST controller for managing {@link com.kobe.nucleus.domain.Motif}.
 */
@RestController
@RequestMapping("/api")
public class MotifResource {

    private final Logger log = LoggerFactory.getLogger(MotifResource.class);

    private static final String ENTITY_NAME = "motif";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MotifService motifService;

    public MotifResource(MotifService motifService) {
        this.motifService = motifService;
    }

    /**
     * {@code POST  /motifs} : Create a new motif.
     *
     * @param motifDTO the motifDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new motifDTO, or with status {@code 400 (Bad Request)} if the motif has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/motifs")
    public ResponseEntity<MotifDTO> createMotif(@Valid @RequestBody MotifDTO motifDTO) throws URISyntaxException {
        log.debug("REST request to save Motif : {}", motifDTO);
        if (motifDTO.getId() != null) {
            throw new BadRequestAlertException("A new motif cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MotifDTO result = motifService.save(motifDTO);
        return ResponseEntity.created(new URI("/api/motifs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /motifs} : Updates an existing motif.
     *
     * @param motifDTO the motifDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated motifDTO,
     * or with status {@code 400 (Bad Request)} if the motifDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the motifDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/motifs")
    public ResponseEntity<MotifDTO> updateMotif(@Valid @RequestBody MotifDTO motifDTO) throws URISyntaxException {
        log.debug("REST request to update Motif : {}", motifDTO);
        if (motifDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MotifDTO result = motifService.save(motifDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, motifDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /motifs} : get all the motifs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of motifs in body.
     */
    @GetMapping("/motifs")
    public ResponseEntity<List<MotifDTO>> getAllMotifs(Pageable pageable) {
        log.debug("REST request to get a page of Motifs");
        Page<MotifDTO> page = motifService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /motifs/:id} : get the "id" motif.
     *
     * @param id the id of the motifDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the motifDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/motifs/{id}")
    public ResponseEntity<MotifDTO> getMotif(@PathVariable Long id) {
        log.debug("REST request to get Motif : {}", id);
        Optional<MotifDTO> motifDTO = motifService.findOne(id);
        return ResponseUtil.wrapOrNotFound(motifDTO);
    }

    /**
     * {@code DELETE  /motifs/:id} : delete the "id" motif.
     *
     * @param id the id of the motifDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/motifs/{id}")
    public ResponseEntity<Void> deleteMotif(@PathVariable Long id) {
        log.debug("REST request to delete Motif : {}", id);

        motifService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
