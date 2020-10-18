package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.service.AjustementService;
import com.kobe.nucleus.web.rest.errors.BadRequestAlertException;
import com.kobe.nucleus.service.dto.AjustementDTO;

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
 * REST controller for managing {@link com.kobe.nucleus.domain.Ajustement}.
 */
@RestController
@RequestMapping("/api")
public class AjustementResource {

    private final Logger log = LoggerFactory.getLogger(AjustementResource.class);

    private static final String ENTITY_NAME = "ajustement";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AjustementService ajustementService;

    public AjustementResource(AjustementService ajustementService) {
        this.ajustementService = ajustementService;
    }

    /**
     * {@code POST  /ajustements} : Create a new ajustement.
     *
     * @param ajustementDTO the ajustementDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ajustementDTO, or with status {@code 400 (Bad Request)} if the ajustement has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ajustements")
    public ResponseEntity<AjustementDTO> createAjustement(@Valid @RequestBody AjustementDTO ajustementDTO) throws URISyntaxException {
        log.debug("REST request to save Ajustement : {}", ajustementDTO);
        if (ajustementDTO.getId() != null) {
            throw new BadRequestAlertException("A new ajustement cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AjustementDTO result = ajustementService.save(ajustementDTO);
        return ResponseEntity.created(new URI("/api/ajustements/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ajustements} : Updates an existing ajustement.
     *
     * @param ajustementDTO the ajustementDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ajustementDTO,
     * or with status {@code 400 (Bad Request)} if the ajustementDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ajustementDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ajustements")
    public ResponseEntity<AjustementDTO> updateAjustement(@Valid @RequestBody AjustementDTO ajustementDTO) throws URISyntaxException {
        log.debug("REST request to update Ajustement : {}", ajustementDTO);
        if (ajustementDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AjustementDTO result = ajustementService.save(ajustementDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ajustementDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ajustements} : get all the ajustements.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ajustements in body.
     */
    @GetMapping("/ajustements")
    public ResponseEntity<List<AjustementDTO>> getAllAjustements(Pageable pageable) {
        log.debug("REST request to get a page of Ajustements");
        Page<AjustementDTO> page = ajustementService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /ajustements/:id} : get the "id" ajustement.
     *
     * @param id the id of the ajustementDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ajustementDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ajustements/{id}")
    public ResponseEntity<AjustementDTO> getAjustement(@PathVariable Long id) {
        log.debug("REST request to get Ajustement : {}", id);
        Optional<AjustementDTO> ajustementDTO = ajustementService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ajustementDTO);
    }

    /**
     * {@code DELETE  /ajustements/:id} : delete the "id" ajustement.
     *
     * @param id the id of the ajustementDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ajustements/{id}")
    public ResponseEntity<Void> deleteAjustement(@PathVariable Long id) {
        log.debug("REST request to delete Ajustement : {}", id);

        ajustementService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
