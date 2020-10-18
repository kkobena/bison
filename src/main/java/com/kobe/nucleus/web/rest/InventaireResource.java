package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.service.InventaireService;
import com.kobe.nucleus.web.rest.errors.BadRequestAlertException;
import com.kobe.nucleus.service.dto.InventaireDTO;

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
 * REST controller for managing {@link com.kobe.nucleus.domain.Inventaire}.
 */
@RestController
@RequestMapping("/api")
public class InventaireResource {

    private final Logger log = LoggerFactory.getLogger(InventaireResource.class);

    private static final String ENTITY_NAME = "inventaire";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InventaireService inventaireService;

    public InventaireResource(InventaireService inventaireService) {
        this.inventaireService = inventaireService;
    }

    /**
     * {@code POST  /inventaires} : Create a new inventaire.
     *
     * @param inventaireDTO the inventaireDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new inventaireDTO, or with status {@code 400 (Bad Request)} if the inventaire has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/inventaires")
    public ResponseEntity<InventaireDTO> createInventaire(@Valid @RequestBody InventaireDTO inventaireDTO) throws URISyntaxException {
        log.debug("REST request to save Inventaire : {}", inventaireDTO);
        if (inventaireDTO.getId() != null) {
            throw new BadRequestAlertException("A new inventaire cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InventaireDTO result = inventaireService.save(inventaireDTO);
        return ResponseEntity.created(new URI("/api/inventaires/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /inventaires} : Updates an existing inventaire.
     *
     * @param inventaireDTO the inventaireDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated inventaireDTO,
     * or with status {@code 400 (Bad Request)} if the inventaireDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the inventaireDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/inventaires")
    public ResponseEntity<InventaireDTO> updateInventaire(@Valid @RequestBody InventaireDTO inventaireDTO) throws URISyntaxException {
        log.debug("REST request to update Inventaire : {}", inventaireDTO);
        if (inventaireDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InventaireDTO result = inventaireService.save(inventaireDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, inventaireDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /inventaires} : get all the inventaires.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of inventaires in body.
     */
    @GetMapping("/inventaires")
    public ResponseEntity<List<InventaireDTO>> getAllInventaires(Pageable pageable) {
        log.debug("REST request to get a page of Inventaires");
        Page<InventaireDTO> page = inventaireService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /inventaires/:id} : get the "id" inventaire.
     *
     * @param id the id of the inventaireDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the inventaireDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/inventaires/{id}")
    public ResponseEntity<InventaireDTO> getInventaire(@PathVariable Long id) {
        log.debug("REST request to get Inventaire : {}", id);
        Optional<InventaireDTO> inventaireDTO = inventaireService.findOne(id);
        return ResponseUtil.wrapOrNotFound(inventaireDTO);
    }

    /**
     * {@code DELETE  /inventaires/:id} : delete the "id" inventaire.
     *
     * @param id the id of the inventaireDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/inventaires/{id}")
    public ResponseEntity<Void> deleteInventaire(@PathVariable Long id) {
        log.debug("REST request to delete Inventaire : {}", id);

        inventaireService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
