package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.service.GroupeTierspayantService;
import com.kobe.nucleus.web.rest.errors.BadRequestAlertException;
import com.kobe.nucleus.service.dto.GroupeTierspayantDTO;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.kobe.nucleus.domain.GroupeTierspayant}.
 */
@RestController
@RequestMapping("/api")
public class GroupeTierspayantResource {

    private final Logger log = LoggerFactory.getLogger(GroupeTierspayantResource.class);

    private static final String ENTITY_NAME = "groupeTierspayant";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GroupeTierspayantService groupeTierspayantService;

    public GroupeTierspayantResource(GroupeTierspayantService groupeTierspayantService) {
        this.groupeTierspayantService = groupeTierspayantService;
    }

    /**
     * {@code POST  /groupe-tierspayants} : Create a new groupeTierspayant.
     *
     * @param groupeTierspayantDTO the groupeTierspayantDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new groupeTierspayantDTO, or with status {@code 400 (Bad Request)} if the groupeTierspayant has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/groupe-tierspayants")
    public ResponseEntity<GroupeTierspayantDTO> createGroupeTierspayant(@Valid @RequestBody GroupeTierspayantDTO groupeTierspayantDTO) throws URISyntaxException {
        log.debug("REST request to save GroupeTierspayant : {}", groupeTierspayantDTO);
        if (groupeTierspayantDTO.getId() != null) {
            throw new BadRequestAlertException("A new groupeTierspayant cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GroupeTierspayantDTO result = groupeTierspayantService.save(groupeTierspayantDTO);
        return ResponseEntity.created(new URI("/api/groupe-tierspayants/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /groupe-tierspayants} : Updates an existing groupeTierspayant.
     *
     * @param groupeTierspayantDTO the groupeTierspayantDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated groupeTierspayantDTO,
     * or with status {@code 400 (Bad Request)} if the groupeTierspayantDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the groupeTierspayantDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/groupe-tierspayants")
    public ResponseEntity<GroupeTierspayantDTO> updateGroupeTierspayant(@Valid @RequestBody GroupeTierspayantDTO groupeTierspayantDTO) throws URISyntaxException {
        log.debug("REST request to update GroupeTierspayant : {}", groupeTierspayantDTO);
        if (groupeTierspayantDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GroupeTierspayantDTO result = groupeTierspayantService.save(groupeTierspayantDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, groupeTierspayantDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /groupe-tierspayants} : get all the groupeTierspayants.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of groupeTierspayants in body.
     */
    @GetMapping(value = "/groupe-tierspayants",params = {"search"})
    public ResponseEntity<List<GroupeTierspayantDTO>> getAllGroupeTierspayants(@RequestParam("search") String search,Pageable pageable) {
        log.debug("REST request to get a page of GroupeTierspayants");
        Page<GroupeTierspayantDTO> page = groupeTierspayantService.findAll(search,pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /groupe-tierspayants/:id} : get the "id" groupeTierspayant.
     *
     * @param id the id of the groupeTierspayantDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the groupeTierspayantDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/groupe-tierspayants/{id}")
    public ResponseEntity<GroupeTierspayantDTO> getGroupeTierspayant(@PathVariable Long id) {
        log.debug("REST request to get GroupeTierspayant : {}", id);
        Optional<GroupeTierspayantDTO> groupeTierspayantDTO = groupeTierspayantService.findOne(id);
        return ResponseUtil.wrapOrNotFound(groupeTierspayantDTO);
    }

    /**
     * {@code DELETE  /groupe-tierspayants/:id} : delete the "id" groupeTierspayant.
     *
     * @param id the id of the groupeTierspayantDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/groupe-tierspayants/{id}")
    public ResponseEntity<Void> deleteGroupeTierspayant(@PathVariable Long id) {
        log.debug("REST request to delete GroupeTierspayant : {}", id);

        groupeTierspayantService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    @PostMapping("/groupe-tierspayants/importcsv")
    public ResponseEntity<Void> uploadFile(@RequestPart("importcsv") MultipartFile file) throws URISyntaxException , IOException {
        groupeTierspayantService.importGroupeTierspayant(file.getInputStream());

        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, "")).build();
    }
}
