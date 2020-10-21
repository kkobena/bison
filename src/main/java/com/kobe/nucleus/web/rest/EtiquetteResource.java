package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.service.EtiquetteService;
import com.kobe.nucleus.web.rest.errors.BadRequestAlertException;
import com.kobe.nucleus.service.dto.EtiquetteDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.kobe.nucleus.domain.Etiquette}.
 */
@RestController
@RequestMapping("/api")
public class EtiquetteResource {

    private final Logger log = LoggerFactory.getLogger(EtiquetteResource.class);

    private static final String ENTITY_NAME = "etiquette";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EtiquetteService etiquetteService;

    public EtiquetteResource(EtiquetteService etiquetteService) {
        this.etiquetteService = etiquetteService;
    }

    /**
     * {@code POST  /etiquettes} : Create a new etiquette.
     *
     * @param etiquetteDTO the etiquetteDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new etiquetteDTO, or with status {@code 400 (Bad Request)} if the etiquette has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/etiquettes")
    public ResponseEntity<EtiquetteDTO> createEtiquette(@RequestBody EtiquetteDTO etiquetteDTO) throws URISyntaxException {
        log.debug("REST request to save Etiquette : {}", etiquetteDTO);
        if (etiquetteDTO.getId() != null) {
            throw new BadRequestAlertException("A new etiquette cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EtiquetteDTO result = etiquetteService.save(etiquetteDTO);
        return ResponseEntity.created(new URI("/api/etiquettes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /etiquettes} : Updates an existing etiquette.
     *
     * @param etiquetteDTO the etiquetteDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated etiquetteDTO,
     * or with status {@code 400 (Bad Request)} if the etiquetteDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the etiquetteDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/etiquettes")
    public ResponseEntity<EtiquetteDTO> updateEtiquette(@RequestBody EtiquetteDTO etiquetteDTO) throws URISyntaxException {
        log.debug("REST request to update Etiquette : {}", etiquetteDTO);
        if (etiquetteDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EtiquetteDTO result = etiquetteService.save(etiquetteDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, etiquetteDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /etiquettes} : get all the etiquettes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of etiquettes in body.
     */
    @GetMapping("/etiquettes")
    public ResponseEntity<List<EtiquetteDTO>> getAllEtiquettes(Pageable pageable) {
        log.debug("REST request to get a page of Etiquettes");
        Page<EtiquetteDTO> page = etiquetteService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /etiquettes/:id} : get the "id" etiquette.
     *
     * @param id the id of the etiquetteDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the etiquetteDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/etiquettes/{id}")
    public ResponseEntity<EtiquetteDTO> getEtiquette(@PathVariable Long id) {
        log.debug("REST request to get Etiquette : {}", id);
        Optional<EtiquetteDTO> etiquetteDTO = etiquetteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(etiquetteDTO);
    }

    /**
     * {@code DELETE  /etiquettes/:id} : delete the "id" etiquette.
     *
     * @param id the id of the etiquetteDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/etiquettes/{id}")
    public ResponseEntity<Void> deleteEtiquette(@PathVariable Long id) {
        log.debug("REST request to delete Etiquette : {}", id);

        etiquetteService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
