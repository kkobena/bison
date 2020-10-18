package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.service.ModelFactureService;
import com.kobe.nucleus.web.rest.errors.BadRequestAlertException;
import com.kobe.nucleus.service.dto.ModelFactureDTO;

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
 * REST controller for managing {@link com.kobe.nucleus.domain.ModelFacture}.
 */
@RestController
@RequestMapping("/api")
public class ModelFactureResource {

    private final Logger log = LoggerFactory.getLogger(ModelFactureResource.class);

    private static final String ENTITY_NAME = "modelFacture";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ModelFactureService modelFactureService;

    public ModelFactureResource(ModelFactureService modelFactureService) {
        this.modelFactureService = modelFactureService;
    }

    /**
     * {@code POST  /model-factures} : Create a new modelFacture.
     *
     * @param modelFactureDTO the modelFactureDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new modelFactureDTO, or with status {@code 400 (Bad Request)} if the modelFacture has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/model-factures")
    public ResponseEntity<ModelFactureDTO> createModelFacture(@Valid @RequestBody ModelFactureDTO modelFactureDTO) throws URISyntaxException {
        log.debug("REST request to save ModelFacture : {}", modelFactureDTO);
        if (modelFactureDTO.getId() != null) {
            throw new BadRequestAlertException("A new modelFacture cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ModelFactureDTO result = modelFactureService.save(modelFactureDTO);
        return ResponseEntity.created(new URI("/api/model-factures/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /model-factures} : Updates an existing modelFacture.
     *
     * @param modelFactureDTO the modelFactureDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated modelFactureDTO,
     * or with status {@code 400 (Bad Request)} if the modelFactureDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the modelFactureDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/model-factures")
    public ResponseEntity<ModelFactureDTO> updateModelFacture(@Valid @RequestBody ModelFactureDTO modelFactureDTO) throws URISyntaxException {
        log.debug("REST request to update ModelFacture : {}", modelFactureDTO);
        if (modelFactureDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ModelFactureDTO result = modelFactureService.save(modelFactureDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, modelFactureDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /model-factures} : get all the modelFactures.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of modelFactures in body.
     */
    @GetMapping("/model-factures")
    public ResponseEntity<List<ModelFactureDTO>> getAllModelFactures(Pageable pageable) {
        log.debug("REST request to get a page of ModelFactures");
        Page<ModelFactureDTO> page = modelFactureService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /model-factures/:id} : get the "id" modelFacture.
     *
     * @param id the id of the modelFactureDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the modelFactureDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/model-factures/{id}")
    public ResponseEntity<ModelFactureDTO> getModelFacture(@PathVariable Long id) {
        log.debug("REST request to get ModelFacture : {}", id);
        Optional<ModelFactureDTO> modelFactureDTO = modelFactureService.findOne(id);
        return ResponseUtil.wrapOrNotFound(modelFactureDTO);
    }

    /**
     * {@code DELETE  /model-factures/:id} : delete the "id" modelFacture.
     *
     * @param id the id of the modelFactureDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/model-factures/{id}")
    public ResponseEntity<Void> deleteModelFacture(@PathVariable Long id) {
        log.debug("REST request to delete ModelFacture : {}", id);
        modelFactureService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
