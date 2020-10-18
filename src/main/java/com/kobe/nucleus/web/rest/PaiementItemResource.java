package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.service.PaiementItemService;
import com.kobe.nucleus.web.rest.errors.BadRequestAlertException;
import com.kobe.nucleus.service.dto.PaiementItemDTO;

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
 * REST controller for managing {@link com.kobe.nucleus.domain.PaiementItem}.
 */
@RestController
@RequestMapping("/api")
public class PaiementItemResource {

    private final Logger log = LoggerFactory.getLogger(PaiementItemResource.class);

    private static final String ENTITY_NAME = "paiementItem";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PaiementItemService paiementItemService;

    public PaiementItemResource(PaiementItemService paiementItemService) {
        this.paiementItemService = paiementItemService;
    }

    /**
     * {@code POST  /paiement-items} : Create a new paiementItem.
     *
     * @param paiementItemDTO the paiementItemDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new paiementItemDTO, or with status {@code 400 (Bad Request)} if the paiementItem has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/paiement-items")
    public ResponseEntity<PaiementItemDTO> createPaiementItem(@Valid @RequestBody PaiementItemDTO paiementItemDTO) throws URISyntaxException {
        log.debug("REST request to save PaiementItem : {}", paiementItemDTO);
        if (paiementItemDTO.getId() != null) {
            throw new BadRequestAlertException("A new paiementItem cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PaiementItemDTO result = paiementItemService.save(paiementItemDTO);
        return ResponseEntity.created(new URI("/api/paiement-items/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /paiement-items} : Updates an existing paiementItem.
     *
     * @param paiementItemDTO the paiementItemDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated paiementItemDTO,
     * or with status {@code 400 (Bad Request)} if the paiementItemDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the paiementItemDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/paiement-items")
    public ResponseEntity<PaiementItemDTO> updatePaiementItem(@Valid @RequestBody PaiementItemDTO paiementItemDTO) throws URISyntaxException {
        log.debug("REST request to update PaiementItem : {}", paiementItemDTO);
        if (paiementItemDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PaiementItemDTO result = paiementItemService.save(paiementItemDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, paiementItemDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /paiement-items} : get all the paiementItems.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of paiementItems in body.
     */
    @GetMapping("/paiement-items")
    public ResponseEntity<List<PaiementItemDTO>> getAllPaiementItems(Pageable pageable) {
        log.debug("REST request to get a page of PaiementItems");
        Page<PaiementItemDTO> page = paiementItemService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /paiement-items/:id} : get the "id" paiementItem.
     *
     * @param id the id of the paiementItemDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the paiementItemDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/paiement-items/{id}")
    public ResponseEntity<PaiementItemDTO> getPaiementItem(@PathVariable Long id) {
        log.debug("REST request to get PaiementItem : {}", id);
        Optional<PaiementItemDTO> paiementItemDTO = paiementItemService.findOne(id);
        return ResponseUtil.wrapOrNotFound(paiementItemDTO);
    }

    /**
     * {@code DELETE  /paiement-items/:id} : delete the "id" paiementItem.
     *
     * @param id the id of the paiementItemDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/paiement-items/{id}")
    public ResponseEntity<Void> deletePaiementItem(@PathVariable Long id) {
        log.debug("REST request to delete PaiementItem : {}", id);

        paiementItemService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
