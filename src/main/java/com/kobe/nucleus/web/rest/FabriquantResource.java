package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.service.FabriquantService;
import com.kobe.nucleus.web.rest.errors.BadRequestAlertException;
import com.kobe.nucleus.service.dto.FabriquantDTO;

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
 * REST controller for managing {@link com.kobe.nucleus.domain.Fabriquant}.
 */
@RestController
@RequestMapping("/api")
public class FabriquantResource {

    private final Logger log = LoggerFactory.getLogger(FabriquantResource.class);

    private static final String ENTITY_NAME = "fabriquant";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FabriquantService fabriquantService;

    public FabriquantResource(FabriquantService fabriquantService) {
        this.fabriquantService = fabriquantService;
    }

    /**
     * {@code POST  /fabriquants} : Create a new fabriquant.
     *
     * @param fabriquantDTO the fabriquantDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fabriquantDTO, or with status {@code 400 (Bad Request)} if the fabriquant has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/fabriquants")
    public ResponseEntity<FabriquantDTO> createFabriquant(@Valid @RequestBody FabriquantDTO fabriquantDTO) throws URISyntaxException {
        log.debug("REST request to save Fabriquant : {}", fabriquantDTO);
        if (fabriquantDTO.getId() != null) {
            throw new BadRequestAlertException("A new fabriquant cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FabriquantDTO result = fabriquantService.save(fabriquantDTO);
        return ResponseEntity.created(new URI("/api/fabriquants/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /fabriquants} : Updates an existing fabriquant.
     *
     * @param fabriquantDTO the fabriquantDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fabriquantDTO,
     * or with status {@code 400 (Bad Request)} if the fabriquantDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the fabriquantDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/fabriquants")
    public ResponseEntity<FabriquantDTO> updateFabriquant(@Valid @RequestBody FabriquantDTO fabriquantDTO) throws URISyntaxException {
        log.debug("REST request to update Fabriquant : {}", fabriquantDTO);
        if (fabriquantDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FabriquantDTO result = fabriquantService.save(fabriquantDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, fabriquantDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /fabriquants} : get all the fabriquants.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fabriquants in body.
     */
    @GetMapping("/fabriquants")
    public ResponseEntity<List<FabriquantDTO>> getAllFabriquants(Pageable pageable) {
        log.debug("REST request to get a page of Fabriquants");
        Page<FabriquantDTO> page = fabriquantService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /fabriquants/:id} : get the "id" fabriquant.
     *
     * @param id the id of the fabriquantDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fabriquantDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/fabriquants/{id}")
    public ResponseEntity<FabriquantDTO> getFabriquant(@PathVariable Long id) {
        log.debug("REST request to get Fabriquant : {}", id);
        Optional<FabriquantDTO> fabriquantDTO = fabriquantService.findOne(id);
        return ResponseUtil.wrapOrNotFound(fabriquantDTO);
    }

    /**
     * {@code DELETE  /fabriquants/:id} : delete the "id" fabriquant.
     *
     * @param id the id of the fabriquantDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/fabriquants/{id}")
    public ResponseEntity<Void> deleteFabriquant(@PathVariable Long id) {
        log.debug("REST request to delete Fabriquant : {}", id);

        fabriquantService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
