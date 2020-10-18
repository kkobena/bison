package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.service.TypeMvtCaisseService;
import com.kobe.nucleus.web.rest.errors.BadRequestAlertException;
import com.kobe.nucleus.service.dto.TypeMvtCaisseDTO;

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
 * REST controller for managing {@link com.kobe.nucleus.domain.TypeMvtCaisse}.
 */
@RestController
@RequestMapping("/api")
public class TypeMvtCaisseResource {

    private final Logger log = LoggerFactory.getLogger(TypeMvtCaisseResource.class);

    private static final String ENTITY_NAME = "typeMvtCaisse";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TypeMvtCaisseService typeMvtCaisseService;

    public TypeMvtCaisseResource(TypeMvtCaisseService typeMvtCaisseService) {
        this.typeMvtCaisseService = typeMvtCaisseService;
    }

    /**
     * {@code POST  /type-mvt-caisses} : Create a new typeMvtCaisse.
     *
     * @param typeMvtCaisseDTO the typeMvtCaisseDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new typeMvtCaisseDTO, or with status {@code 400 (Bad Request)} if the typeMvtCaisse has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/type-mvt-caisses")
    public ResponseEntity<TypeMvtCaisseDTO> createTypeMvtCaisse(@Valid @RequestBody TypeMvtCaisseDTO typeMvtCaisseDTO) throws URISyntaxException {
        log.debug("REST request to save TypeMvtCaisse : {}", typeMvtCaisseDTO);
        if (typeMvtCaisseDTO.getId() != null) {
            throw new BadRequestAlertException("A new typeMvtCaisse cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TypeMvtCaisseDTO result = typeMvtCaisseService.save(typeMvtCaisseDTO);
        return ResponseEntity.created(new URI("/api/type-mvt-caisses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /type-mvt-caisses} : Updates an existing typeMvtCaisse.
     *
     * @param typeMvtCaisseDTO the typeMvtCaisseDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated typeMvtCaisseDTO,
     * or with status {@code 400 (Bad Request)} if the typeMvtCaisseDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the typeMvtCaisseDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/type-mvt-caisses")
    public ResponseEntity<TypeMvtCaisseDTO> updateTypeMvtCaisse(@Valid @RequestBody TypeMvtCaisseDTO typeMvtCaisseDTO) throws URISyntaxException {
        log.debug("REST request to update TypeMvtCaisse : {}", typeMvtCaisseDTO);
        if (typeMvtCaisseDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TypeMvtCaisseDTO result = typeMvtCaisseService.save(typeMvtCaisseDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, typeMvtCaisseDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /type-mvt-caisses} : get all the typeMvtCaisses.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of typeMvtCaisses in body.
     */
    @GetMapping("/type-mvt-caisses")
    public ResponseEntity<List<TypeMvtCaisseDTO>> getAllTypeMvtCaisses(Pageable pageable) {
        log.debug("REST request to get a page of TypeMvtCaisses");
        Page<TypeMvtCaisseDTO> page = typeMvtCaisseService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /type-mvt-caisses/:id} : get the "id" typeMvtCaisse.
     *
     * @param id the id of the typeMvtCaisseDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the typeMvtCaisseDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/type-mvt-caisses/{id}")
    public ResponseEntity<TypeMvtCaisseDTO> getTypeMvtCaisse(@PathVariable Long id) {
        log.debug("REST request to get TypeMvtCaisse : {}", id);
        Optional<TypeMvtCaisseDTO> typeMvtCaisseDTO = typeMvtCaisseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(typeMvtCaisseDTO);
    }

    /**
     * {@code DELETE  /type-mvt-caisses/:id} : delete the "id" typeMvtCaisse.
     *
     * @param id the id of the typeMvtCaisseDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/type-mvt-caisses/{id}")
    public ResponseEntity<Void> deleteTypeMvtCaisse(@PathVariable Long id) {
        log.debug("REST request to delete TypeMvtCaisse : {}", id);

        typeMvtCaisseService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
