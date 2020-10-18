package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.service.TypeInventaireService;
import com.kobe.nucleus.web.rest.errors.BadRequestAlertException;
import com.kobe.nucleus.service.dto.TypeInventaireDTO;

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
 * REST controller for managing {@link com.kobe.nucleus.domain.TypeInventaire}.
 */
@RestController
@RequestMapping("/api")
public class TypeInventaireResource {

    private final Logger log = LoggerFactory.getLogger(TypeInventaireResource.class);

    private static final String ENTITY_NAME = "typeInventaire";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TypeInventaireService typeInventaireService;

    public TypeInventaireResource(TypeInventaireService typeInventaireService) {
        this.typeInventaireService = typeInventaireService;
    }

    /**
     * {@code POST  /type-inventaires} : Create a new typeInventaire.
     *
     * @param typeInventaireDTO the typeInventaireDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new typeInventaireDTO, or with status {@code 400 (Bad Request)} if the typeInventaire has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/type-inventaires")
    public ResponseEntity<TypeInventaireDTO> createTypeInventaire(@Valid @RequestBody TypeInventaireDTO typeInventaireDTO) throws URISyntaxException {
        log.debug("REST request to save TypeInventaire : {}", typeInventaireDTO);
        if (typeInventaireDTO.getId() != null) {
            throw new BadRequestAlertException("A new typeInventaire cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TypeInventaireDTO result = typeInventaireService.save(typeInventaireDTO);
        return ResponseEntity.created(new URI("/api/type-inventaires/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /type-inventaires} : Updates an existing typeInventaire.
     *
     * @param typeInventaireDTO the typeInventaireDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated typeInventaireDTO,
     * or with status {@code 400 (Bad Request)} if the typeInventaireDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the typeInventaireDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/type-inventaires")
    public ResponseEntity<TypeInventaireDTO> updateTypeInventaire(@Valid @RequestBody TypeInventaireDTO typeInventaireDTO) throws URISyntaxException {
        log.debug("REST request to update TypeInventaire : {}", typeInventaireDTO);
        if (typeInventaireDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TypeInventaireDTO result = typeInventaireService.save(typeInventaireDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, typeInventaireDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /type-inventaires} : get all the typeInventaires.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of typeInventaires in body.
     */
    @GetMapping("/type-inventaires")
    public ResponseEntity<List<TypeInventaireDTO>> getAllTypeInventaires(Pageable pageable) {
        log.debug("REST request to get a page of TypeInventaires");
        Page<TypeInventaireDTO> page = typeInventaireService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /type-inventaires/:id} : get the "id" typeInventaire.
     *
     * @param id the id of the typeInventaireDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the typeInventaireDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/type-inventaires/{id}")
    public ResponseEntity<TypeInventaireDTO> getTypeInventaire(@PathVariable Long id) {
        log.debug("REST request to get TypeInventaire : {}", id);
        Optional<TypeInventaireDTO> typeInventaireDTO = typeInventaireService.findOne(id);
        return ResponseUtil.wrapOrNotFound(typeInventaireDTO);
    }

    /**
     * {@code DELETE  /type-inventaires/:id} : delete the "id" typeInventaire.
     *
     * @param id the id of the typeInventaireDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/type-inventaires/{id}")
    public ResponseEntity<Void> deleteTypeInventaire(@PathVariable Long id) {
        log.debug("REST request to delete TypeInventaire : {}", id);

        typeInventaireService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
