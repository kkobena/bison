package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.service.DetailsInventaireService;
import com.kobe.nucleus.web.rest.errors.BadRequestAlertException;
import com.kobe.nucleus.service.dto.DetailsInventaireDTO;

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
 * REST controller for managing {@link com.kobe.nucleus.domain.DetailsInventaire}.
 */
@RestController
@RequestMapping("/api")
public class DetailsInventaireResource {

    private final Logger log = LoggerFactory.getLogger(DetailsInventaireResource.class);

    private static final String ENTITY_NAME = "detailsInventaire";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DetailsInventaireService detailsInventaireService;

    public DetailsInventaireResource(DetailsInventaireService detailsInventaireService) {
        this.detailsInventaireService = detailsInventaireService;
    }

    /**
     * {@code POST  /details-inventaires} : Create a new detailsInventaire.
     *
     * @param detailsInventaireDTO the detailsInventaireDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new detailsInventaireDTO, or with status {@code 400 (Bad Request)} if the detailsInventaire has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/details-inventaires")
    public ResponseEntity<DetailsInventaireDTO> createDetailsInventaire(@Valid @RequestBody DetailsInventaireDTO detailsInventaireDTO) throws URISyntaxException {
        log.debug("REST request to save DetailsInventaire : {}", detailsInventaireDTO);
        if (detailsInventaireDTO.getId() != null) {
            throw new BadRequestAlertException("A new detailsInventaire cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DetailsInventaireDTO result = detailsInventaireService.save(detailsInventaireDTO);
        return ResponseEntity.created(new URI("/api/details-inventaires/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /details-inventaires} : Updates an existing detailsInventaire.
     *
     * @param detailsInventaireDTO the detailsInventaireDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated detailsInventaireDTO,
     * or with status {@code 400 (Bad Request)} if the detailsInventaireDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the detailsInventaireDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/details-inventaires")
    public ResponseEntity<DetailsInventaireDTO> updateDetailsInventaire(@Valid @RequestBody DetailsInventaireDTO detailsInventaireDTO) throws URISyntaxException {
        log.debug("REST request to update DetailsInventaire : {}", detailsInventaireDTO);
        if (detailsInventaireDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DetailsInventaireDTO result = detailsInventaireService.save(detailsInventaireDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, detailsInventaireDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /details-inventaires} : get all the detailsInventaires.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of detailsInventaires in body.
     */
    @GetMapping("/details-inventaires")
    public ResponseEntity<List<DetailsInventaireDTO>> getAllDetailsInventaires(Pageable pageable) {
        log.debug("REST request to get a page of DetailsInventaires");
        Page<DetailsInventaireDTO> page = detailsInventaireService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /details-inventaires/:id} : get the "id" detailsInventaire.
     *
     * @param id the id of the detailsInventaireDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the detailsInventaireDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/details-inventaires/{id}")
    public ResponseEntity<DetailsInventaireDTO> getDetailsInventaire(@PathVariable Long id) {
        log.debug("REST request to get DetailsInventaire : {}", id);
        Optional<DetailsInventaireDTO> detailsInventaireDTO = detailsInventaireService.findOne(id);
        return ResponseUtil.wrapOrNotFound(detailsInventaireDTO);
    }

    /**
     * {@code DELETE  /details-inventaires/:id} : delete the "id" detailsInventaire.
     *
     * @param id the id of the detailsInventaireDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/details-inventaires/{id}")
    public ResponseEntity<Void> deleteDetailsInventaire(@PathVariable Long id) {
        log.debug("REST request to delete DetailsInventaire : {}", id);

        detailsInventaireService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
