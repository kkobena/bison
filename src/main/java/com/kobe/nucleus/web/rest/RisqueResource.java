package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.service.RisqueService;
import com.kobe.nucleus.web.rest.errors.BadRequestAlertException;
import com.kobe.nucleus.service.dto.RisqueDTO;

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
 * REST controller for managing {@link com.kobe.nucleus.domain.Risque}.
 */
@RestController
@RequestMapping("/api")
public class RisqueResource {

    private final Logger log = LoggerFactory.getLogger(RisqueResource.class);

    private static final String ENTITY_NAME = "risque";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RisqueService risqueService;

    public RisqueResource(RisqueService risqueService) {
        this.risqueService = risqueService;
    }

    /**
     * {@code POST  /risques} : Create a new risque.
     *
     * @param risqueDTO the risqueDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new risqueDTO, or with status {@code 400 (Bad Request)} if the risque has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/risques")
    public ResponseEntity<RisqueDTO> createRisque(@Valid @RequestBody RisqueDTO risqueDTO) throws URISyntaxException {
        log.debug("REST request to save Risque : {}", risqueDTO);
        if (risqueDTO.getId() != null) {
            throw new BadRequestAlertException("A new risque cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RisqueDTO result = risqueService.save(risqueDTO);
        return ResponseEntity.created(new URI("/api/risques/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /risques} : Updates an existing risque.
     *
     * @param risqueDTO the risqueDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated risqueDTO,
     * or with status {@code 400 (Bad Request)} if the risqueDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the risqueDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/risques")
    public ResponseEntity<RisqueDTO> updateRisque(@Valid @RequestBody RisqueDTO risqueDTO) throws URISyntaxException {
        log.debug("REST request to update Risque : {}", risqueDTO);
        if (risqueDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RisqueDTO result = risqueService.save(risqueDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, risqueDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /risques} : get all the risques.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of risques in body.
     */
    @GetMapping("/risques")
    public ResponseEntity<List<RisqueDTO>> getAllRisques(Pageable pageable) {
        log.debug("REST request to get a page of Risques");
        Page<RisqueDTO> page = risqueService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /risques/:id} : get the "id" risque.
     *
     * @param id the id of the risqueDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the risqueDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/risques/{id}")
    public ResponseEntity<RisqueDTO> getRisque(@PathVariable Long id) {
        log.debug("REST request to get Risque : {}", id);
        Optional<RisqueDTO> risqueDTO = risqueService.findOne(id);
        return ResponseUtil.wrapOrNotFound(risqueDTO);
    }

    /**
     * {@code DELETE  /risques/:id} : delete the "id" risque.
     *
     * @param id the id of the risqueDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/risques/{id}")
    public ResponseEntity<Void> deleteRisque(@PathVariable Long id) {
        log.debug("REST request to delete Risque : {}", id);

        risqueService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
