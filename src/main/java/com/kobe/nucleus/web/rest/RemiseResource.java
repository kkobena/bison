package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.service.RemiseService;
import com.kobe.nucleus.web.rest.errors.BadRequestAlertException;
import com.kobe.nucleus.service.dto.RemiseDTO;

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
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.kobe.nucleus.domain.Remise}.
 */
@RestController
@RequestMapping("/api")
public class RemiseResource {

    private final Logger log = LoggerFactory.getLogger(RemiseResource.class);

    private static final String ENTITY_NAME = "remise";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RemiseService remiseService;

    public RemiseResource(RemiseService remiseService) {
        this.remiseService = remiseService;
    }

    /**
     * {@code POST  /remises} : Create a new remise.
     *
     * @param remiseDTO the remiseDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new remiseDTO, or with status {@code 400 (Bad Request)} if the remise has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/remises")
    public ResponseEntity<RemiseDTO> createRemise(@Valid @RequestBody RemiseDTO remiseDTO) throws URISyntaxException {
        log.debug("REST request to save Remise : {}", remiseDTO);
        if (remiseDTO.getId() != null) {
            throw new BadRequestAlertException("A new remise cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RemiseDTO result = remiseService.save(remiseDTO);
        return ResponseEntity.created(new URI("/api/remises/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /remises} : Updates an existing remise.
     *
     * @param remiseDTO the remiseDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated remiseDTO,
     * or with status {@code 400 (Bad Request)} if the remiseDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the remiseDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/remises")
    public ResponseEntity<RemiseDTO> updateRemise(@Valid @RequestBody RemiseDTO remiseDTO) throws URISyntaxException {
        log.debug("REST request to update Remise : {}", remiseDTO);
        if (remiseDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RemiseDTO result = remiseService.save(remiseDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, remiseDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /remises} : get all the remises.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of remises in body.
     */
    @GetMapping(value = "/remises" )
    public ResponseEntity<List<RemiseDTO>> getAllRemises(Pageable pageable) {
        log.debug("REST request to get a page of Remises");
        Page<RemiseDTO> page = remiseService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /remises/:id} : get the "id" remise.
     *
     * @param id the id of the remiseDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the remiseDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping(value = "/remises/{id}")
    public ResponseEntity<RemiseDTO> getRemise(@PathVariable Long id) {
        log.debug("REST request to get Remise : {}", id);
        Optional<RemiseDTO> remiseDTO = remiseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(remiseDTO);
    }

    /**
     * {@code DELETE  /remises/:id} : delete the "id" remise.
     *
     * @param id the id of the remiseDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/remises/{id}")
    public ResponseEntity<Void> deleteRemise(@PathVariable Long id) {
        log.debug("REST request to delete Remise : {}", id);

        remiseService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
    @DeleteMapping("/remises/disbale/{id}")
    public ResponseEntity<Void> disbaleRemise(@PathVariable Long id) {
        log.debug("REST request to disable Remise : {}", id);

        remiseService.disable(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
