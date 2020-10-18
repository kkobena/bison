package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.service.DeconditionService;
import com.kobe.nucleus.web.rest.errors.BadRequestAlertException;
import com.kobe.nucleus.service.dto.DeconditionDTO;

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
 * REST controller for managing {@link com.kobe.nucleus.domain.Decondition}.
 */
@RestController
@RequestMapping("/api")
public class DeconditionResource {

    private final Logger log = LoggerFactory.getLogger(DeconditionResource.class);

    private static final String ENTITY_NAME = "decondition";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DeconditionService deconditionService;

    public DeconditionResource(DeconditionService deconditionService) {
        this.deconditionService = deconditionService;
    }

    /**
     * {@code POST  /deconditions} : Create a new decondition.
     *
     * @param deconditionDTO the deconditionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new deconditionDTO, or with status {@code 400 (Bad Request)} if the decondition has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/deconditions")
    public ResponseEntity<DeconditionDTO> createDecondition(@Valid @RequestBody DeconditionDTO deconditionDTO) throws URISyntaxException {
        log.debug("REST request to save Decondition : {}", deconditionDTO);
        if (deconditionDTO.getId() != null) {
            throw new BadRequestAlertException("A new decondition cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DeconditionDTO result = deconditionService.save(deconditionDTO);
        return ResponseEntity.created(new URI("/api/deconditions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /deconditions} : Updates an existing decondition.
     *
     * @param deconditionDTO the deconditionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated deconditionDTO,
     * or with status {@code 400 (Bad Request)} if the deconditionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the deconditionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/deconditions")
    public ResponseEntity<DeconditionDTO> updateDecondition(@Valid @RequestBody DeconditionDTO deconditionDTO) throws URISyntaxException {
        log.debug("REST request to update Decondition : {}", deconditionDTO);
        if (deconditionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DeconditionDTO result = deconditionService.save(deconditionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, deconditionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /deconditions} : get all the deconditions.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of deconditions in body.
     */
    @GetMapping("/deconditions")
    public ResponseEntity<List<DeconditionDTO>> getAllDeconditions(Pageable pageable) {
        log.debug("REST request to get a page of Deconditions");
        Page<DeconditionDTO> page = deconditionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /deconditions/:id} : get the "id" decondition.
     *
     * @param id the id of the deconditionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the deconditionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/deconditions/{id}")
    public ResponseEntity<DeconditionDTO> getDecondition(@PathVariable Long id) {
        log.debug("REST request to get Decondition : {}", id);
        Optional<DeconditionDTO> deconditionDTO = deconditionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(deconditionDTO);
    }

    /**
     * {@code DELETE  /deconditions/:id} : delete the "id" decondition.
     *
     * @param id the id of the deconditionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/deconditions/{id}")
    public ResponseEntity<Void> deleteDecondition(@PathVariable Long id) {
        log.debug("REST request to delete Decondition : {}", id);

        deconditionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
