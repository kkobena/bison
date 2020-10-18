package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.service.TypeRisqueService;
import com.kobe.nucleus.web.rest.errors.BadRequestAlertException;
import com.kobe.nucleus.service.dto.TypeRisqueDTO;

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
 * REST controller for managing {@link com.kobe.nucleus.domain.TypeRisque}.
 */
@RestController
@RequestMapping("/api")
public class TypeRisqueResource {

    private final Logger log = LoggerFactory.getLogger(TypeRisqueResource.class);

    private static final String ENTITY_NAME = "typeRisque";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TypeRisqueService typeRisqueService;

    public TypeRisqueResource(TypeRisqueService typeRisqueService) {
        this.typeRisqueService = typeRisqueService;
    }

    /**
     * {@code POST  /type-risques} : Create a new typeRisque.
     *
     * @param typeRisqueDTO the typeRisqueDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new typeRisqueDTO, or with status {@code 400 (Bad Request)} if the typeRisque has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/type-risques")
    public ResponseEntity<TypeRisqueDTO> createTypeRisque(@Valid @RequestBody TypeRisqueDTO typeRisqueDTO) throws URISyntaxException {
        log.debug("REST request to save TypeRisque : {}", typeRisqueDTO);
        if (typeRisqueDTO.getId() != null) {
            throw new BadRequestAlertException("A new typeRisque cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TypeRisqueDTO result = typeRisqueService.save(typeRisqueDTO);
        return ResponseEntity.created(new URI("/api/type-risques/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /type-risques} : Updates an existing typeRisque.
     *
     * @param typeRisqueDTO the typeRisqueDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated typeRisqueDTO,
     * or with status {@code 400 (Bad Request)} if the typeRisqueDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the typeRisqueDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/type-risques")
    public ResponseEntity<TypeRisqueDTO> updateTypeRisque(@Valid @RequestBody TypeRisqueDTO typeRisqueDTO) throws URISyntaxException {
        log.debug("REST request to update TypeRisque : {}", typeRisqueDTO);
        if (typeRisqueDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TypeRisqueDTO result = typeRisqueService.save(typeRisqueDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, typeRisqueDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /type-risques} : get all the typeRisques.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of typeRisques in body.
     */
    @GetMapping("/type-risques")
    public ResponseEntity<List<TypeRisqueDTO>> getAllTypeRisques(Pageable pageable) {
        log.debug("REST request to get a page of TypeRisques");
        Page<TypeRisqueDTO> page = typeRisqueService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /type-risques/:id} : get the "id" typeRisque.
     *
     * @param id the id of the typeRisqueDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the typeRisqueDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/type-risques/{id}")
    public ResponseEntity<TypeRisqueDTO> getTypeRisque(@PathVariable Long id) {
        log.debug("REST request to get TypeRisque : {}", id);
        Optional<TypeRisqueDTO> typeRisqueDTO = typeRisqueService.findOne(id);
        return ResponseUtil.wrapOrNotFound(typeRisqueDTO);
    }

    /**
     * {@code DELETE  /type-risques/:id} : delete the "id" typeRisque.
     *
     * @param id the id of the typeRisqueDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/type-risques/{id}")
    public ResponseEntity<Void> deleteTypeRisque(@PathVariable Long id) {
        log.debug("REST request to delete TypeRisque : {}", id);

        typeRisqueService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
    @GetMapping( path = "/type-risques-query", params = {"query"})
    public ResponseEntity<List<TypeRisqueDTO>> getAllTypeRisquesBySearchValue( @RequestParam(value = "query") String  query,Pageable pageable) {
        log.debug("REST request to get a page of TypeRisques");
        Page<TypeRisqueDTO> page = typeRisqueService.findByCodeOrLibelle(query,query,pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
