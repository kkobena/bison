package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.service.CodeGestionService;
import com.kobe.nucleus.web.rest.errors.BadRequestAlertException;
import com.kobe.nucleus.service.dto.CodeGestionDTO;

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
 * REST controller for managing {@link com.kobe.nucleus.domain.CodeGestion}.
 */
@RestController
@RequestMapping("/api")
public class CodeGestionResource {

    private final Logger log = LoggerFactory.getLogger(CodeGestionResource.class);

    private static final String ENTITY_NAME = "codeGestion";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CodeGestionService codeGestionService;

    public CodeGestionResource(CodeGestionService codeGestionService) {
        this.codeGestionService = codeGestionService;
    }

    /**
     * {@code POST  /code-gestions} : Create a new codeGestion.
     *
     * @param codeGestionDTO the codeGestionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new codeGestionDTO, or with status {@code 400 (Bad Request)} if the codeGestion has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/code-gestions")
    public ResponseEntity<CodeGestionDTO> createCodeGestion(@Valid @RequestBody CodeGestionDTO codeGestionDTO) throws URISyntaxException {
        log.debug("REST request to save CodeGestion : {}", codeGestionDTO);
        if (codeGestionDTO.getId() != null) {
            throw new BadRequestAlertException("A new codeGestion cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CodeGestionDTO result = codeGestionService.save(codeGestionDTO);
        return ResponseEntity.created(new URI("/api/code-gestions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /code-gestions} : Updates an existing codeGestion.
     *
     * @param codeGestionDTO the codeGestionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated codeGestionDTO,
     * or with status {@code 400 (Bad Request)} if the codeGestionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the codeGestionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/code-gestions")
    public ResponseEntity<CodeGestionDTO> updateCodeGestion(@Valid @RequestBody CodeGestionDTO codeGestionDTO) throws URISyntaxException {
        log.debug("REST request to update CodeGestion : {}", codeGestionDTO);
        if (codeGestionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CodeGestionDTO result = codeGestionService.save(codeGestionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, codeGestionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /code-gestions} : get all the codeGestions.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of codeGestions in body.
     */
    @GetMapping("/code-gestions")
    public ResponseEntity<List<CodeGestionDTO>> getAllCodeGestions(Pageable pageable) {
        log.debug("REST request to get a page of CodeGestions");
        Page<CodeGestionDTO> page = codeGestionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /code-gestions/:id} : get the "id" codeGestion.
     *
     * @param id the id of the codeGestionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the codeGestionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/code-gestions/{id}")
    public ResponseEntity<CodeGestionDTO> getCodeGestion(@PathVariable Long id) {
        log.debug("REST request to get CodeGestion : {}", id);
        Optional<CodeGestionDTO> codeGestionDTO = codeGestionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(codeGestionDTO);
    }

    /**
     * {@code DELETE  /code-gestions/:id} : delete the "id" codeGestion.
     *
     * @param id the id of the codeGestionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/code-gestions/{id}")
    public ResponseEntity<Void> deleteCodeGestion(@PathVariable Long id) {
        log.debug("REST request to delete CodeGestion : {}", id);

        codeGestionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
