package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.service.CompagnieService;
import com.kobe.nucleus.web.rest.errors.BadRequestAlertException;
import com.kobe.nucleus.service.dto.CompagnieDTO;

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
 * REST controller for managing {@link com.kobe.nucleus.domain.Compagnie}.
 */
@RestController
@RequestMapping("/api")
public class CompagnieResource {

    private final Logger log = LoggerFactory.getLogger(CompagnieResource.class);

    private static final String ENTITY_NAME = "compagnie";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CompagnieService compagnieService;

    public CompagnieResource(CompagnieService compagnieService) {
        this.compagnieService = compagnieService;
    }

    /**
     * {@code POST  /compagnies} : Create a new compagnie.
     *
     * @param compagnieDTO the compagnieDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new compagnieDTO, or with status {@code 400 (Bad Request)} if the compagnie has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/compagnies")
    public ResponseEntity<CompagnieDTO> createCompagnie(@Valid @RequestBody CompagnieDTO compagnieDTO) throws URISyntaxException {
        log.debug("REST request to save Compagnie : {}", compagnieDTO);
        if (compagnieDTO.getId() != null) {
            throw new BadRequestAlertException("A new compagnie cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CompagnieDTO result = compagnieService.save(compagnieDTO);
        return ResponseEntity.created(new URI("/api/compagnies/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /compagnies} : Updates an existing compagnie.
     *
     * @param compagnieDTO the compagnieDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated compagnieDTO,
     * or with status {@code 400 (Bad Request)} if the compagnieDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the compagnieDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/compagnies")
    public ResponseEntity<CompagnieDTO> updateCompagnie(@Valid @RequestBody CompagnieDTO compagnieDTO) throws URISyntaxException {
        log.debug("REST request to update Compagnie : {}", compagnieDTO);
        if (compagnieDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CompagnieDTO result = compagnieService.save(compagnieDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, compagnieDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /compagnies} : get all the compagnies.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of compagnies in body.
     */
    @GetMapping("/compagnies")
    public ResponseEntity<List<CompagnieDTO>> getAllCompagnies(Pageable pageable) {
        log.debug("REST request to get a page of Compagnies");
        Page<CompagnieDTO> page = compagnieService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /compagnies/:id} : get the "id" compagnie.
     *
     * @param id the id of the compagnieDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the compagnieDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/compagnies/{id}")
    public ResponseEntity<CompagnieDTO> getCompagnie(@PathVariable Long id) {
        log.debug("REST request to get Compagnie : {}", id);
        Optional<CompagnieDTO> compagnieDTO = compagnieService.findOne(id);
        return ResponseUtil.wrapOrNotFound(compagnieDTO);
    }

    /**
     * {@code DELETE  /compagnies/:id} : delete the "id" compagnie.
     *
     * @param id the id of the compagnieDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/compagnies/{id}")
    public ResponseEntity<Void> deleteCompagnie(@PathVariable Long id) {
        log.debug("REST request to delete Compagnie : {}", id);

        compagnieService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
