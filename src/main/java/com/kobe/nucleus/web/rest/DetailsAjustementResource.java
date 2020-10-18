package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.service.DetailsAjustementService;
import com.kobe.nucleus.web.rest.errors.BadRequestAlertException;
import com.kobe.nucleus.service.dto.DetailsAjustementDTO;

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
 * REST controller for managing {@link com.kobe.nucleus.domain.DetailsAjustement}.
 */
@RestController
@RequestMapping("/api")
public class DetailsAjustementResource {

    private final Logger log = LoggerFactory.getLogger(DetailsAjustementResource.class);

    private static final String ENTITY_NAME = "detailsAjustement";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DetailsAjustementService detailsAjustementService;

    public DetailsAjustementResource(DetailsAjustementService detailsAjustementService) {
        this.detailsAjustementService = detailsAjustementService;
    }

    /**
     * {@code POST  /details-ajustements} : Create a new detailsAjustement.
     *
     * @param detailsAjustementDTO the detailsAjustementDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new detailsAjustementDTO, or with status {@code 400 (Bad Request)} if the detailsAjustement has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/details-ajustements")
    public ResponseEntity<DetailsAjustementDTO> createDetailsAjustement(@Valid @RequestBody DetailsAjustementDTO detailsAjustementDTO) throws URISyntaxException {
        log.debug("REST request to save DetailsAjustement : {}", detailsAjustementDTO);
        if (detailsAjustementDTO.getId() != null) {
            throw new BadRequestAlertException("A new detailsAjustement cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DetailsAjustementDTO result = detailsAjustementService.save(detailsAjustementDTO);
        return ResponseEntity.created(new URI("/api/details-ajustements/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /details-ajustements} : Updates an existing detailsAjustement.
     *
     * @param detailsAjustementDTO the detailsAjustementDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated detailsAjustementDTO,
     * or with status {@code 400 (Bad Request)} if the detailsAjustementDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the detailsAjustementDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/details-ajustements")
    public ResponseEntity<DetailsAjustementDTO> updateDetailsAjustement(@Valid @RequestBody DetailsAjustementDTO detailsAjustementDTO) throws URISyntaxException {
        log.debug("REST request to update DetailsAjustement : {}", detailsAjustementDTO);
        if (detailsAjustementDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DetailsAjustementDTO result = detailsAjustementService.save(detailsAjustementDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, detailsAjustementDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /details-ajustements} : get all the detailsAjustements.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of detailsAjustements in body.
     */
    @GetMapping("/details-ajustements")
    public ResponseEntity<List<DetailsAjustementDTO>> getAllDetailsAjustements(Pageable pageable) {
        log.debug("REST request to get a page of DetailsAjustements");
        Page<DetailsAjustementDTO> page = detailsAjustementService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /details-ajustements/:id} : get the "id" detailsAjustement.
     *
     * @param id the id of the detailsAjustementDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the detailsAjustementDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/details-ajustements/{id}")
    public ResponseEntity<DetailsAjustementDTO> getDetailsAjustement(@PathVariable Long id) {
        log.debug("REST request to get DetailsAjustement : {}", id);
        Optional<DetailsAjustementDTO> detailsAjustementDTO = detailsAjustementService.findOne(id);
        return ResponseUtil.wrapOrNotFound(detailsAjustementDTO);
    }

    /**
     * {@code DELETE  /details-ajustements/:id} : delete the "id" detailsAjustement.
     *
     * @param id the id of the detailsAjustementDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/details-ajustements/{id}")
    public ResponseEntity<Void> deleteDetailsAjustement(@PathVariable Long id) {
        log.debug("REST request to delete DetailsAjustement : {}", id);

        detailsAjustementService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
