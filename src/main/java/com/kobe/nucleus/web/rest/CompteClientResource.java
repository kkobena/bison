package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.service.CompteClientService;
import com.kobe.nucleus.web.rest.errors.BadRequestAlertException;
import com.kobe.nucleus.service.dto.CompteClientDTO;

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
 * REST controller for managing {@link com.kobe.nucleus.domain.CompteClient}.
 */
@RestController
@RequestMapping("/api")
public class CompteClientResource {

    private final Logger log = LoggerFactory.getLogger(CompteClientResource.class);

    private static final String ENTITY_NAME = "compteClient";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CompteClientService compteClientService;

    public CompteClientResource(CompteClientService compteClientService) {
        this.compteClientService = compteClientService;
    }

    /**
     * {@code POST  /compte-clients} : Create a new compteClient.
     *
     * @param compteClientDTO the compteClientDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new compteClientDTO, or with status {@code 400 (Bad Request)} if the compteClient has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/compte-clients")
    public ResponseEntity<CompteClientDTO> createCompteClient(@Valid @RequestBody CompteClientDTO compteClientDTO) throws URISyntaxException {
        log.debug("REST request to save CompteClient : {}", compteClientDTO);
        if (compteClientDTO.getId() != null) {
            throw new BadRequestAlertException("A new compteClient cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CompteClientDTO result = compteClientService.save(compteClientDTO);
        return ResponseEntity.created(new URI("/api/compte-clients/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /compte-clients} : Updates an existing compteClient.
     *
     * @param compteClientDTO the compteClientDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated compteClientDTO,
     * or with status {@code 400 (Bad Request)} if the compteClientDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the compteClientDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/compte-clients")
    public ResponseEntity<CompteClientDTO> updateCompteClient(@Valid @RequestBody CompteClientDTO compteClientDTO) throws URISyntaxException {
        log.debug("REST request to update CompteClient : {}", compteClientDTO);
        if (compteClientDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CompteClientDTO result = compteClientService.save(compteClientDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, compteClientDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /compte-clients} : get all the compteClients.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of compteClients in body.
     */
    @GetMapping("/compte-clients")
    public ResponseEntity<List<CompteClientDTO>> getAllCompteClients(Pageable pageable) {
        log.debug("REST request to get a page of CompteClients");
        Page<CompteClientDTO> page = compteClientService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /compte-clients/:id} : get the "id" compteClient.
     *
     * @param id the id of the compteClientDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the compteClientDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/compte-clients/{id}")
    public ResponseEntity<CompteClientDTO> getCompteClient(@PathVariable Long id) {
        log.debug("REST request to get CompteClient : {}", id);
        Optional<CompteClientDTO> compteClientDTO = compteClientService.findOne(id);
        return ResponseUtil.wrapOrNotFound(compteClientDTO);
    }

    /**
     * {@code DELETE  /compte-clients/:id} : delete the "id" compteClient.
     *
     * @param id the id of the compteClientDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/compte-clients/{id}")
    public ResponseEntity<Void> deleteCompteClient(@PathVariable Long id) {
        log.debug("REST request to delete CompteClient : {}", id);
        compteClientService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
