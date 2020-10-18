package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.service.RoleUtilisateurService;
import com.kobe.nucleus.web.rest.errors.BadRequestAlertException;
import com.kobe.nucleus.service.dto.RoleUtilisateurDTO;

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
 * REST controller for managing {@link com.kobe.nucleus.domain.RoleUtilisateur}.
 */
@RestController
@RequestMapping("/api")
public class RoleUtilisateurResource {

    private final Logger log = LoggerFactory.getLogger(RoleUtilisateurResource.class);

    private static final String ENTITY_NAME = "roleUtilisateur";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RoleUtilisateurService roleUtilisateurService;

    public RoleUtilisateurResource(RoleUtilisateurService roleUtilisateurService) {
        this.roleUtilisateurService = roleUtilisateurService;
    }

    /**
     * {@code POST  /role-utilisateurs} : Create a new roleUtilisateur.
     *
     * @param roleUtilisateurDTO the roleUtilisateurDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new roleUtilisateurDTO, or with status {@code 400 (Bad Request)} if the roleUtilisateur has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/role-utilisateurs")
    public ResponseEntity<RoleUtilisateurDTO> createRoleUtilisateur(@Valid @RequestBody RoleUtilisateurDTO roleUtilisateurDTO) throws URISyntaxException {
        log.debug("REST request to save RoleUtilisateur : {}", roleUtilisateurDTO);
        if (roleUtilisateurDTO.getId() != null) {
            throw new BadRequestAlertException("A new roleUtilisateur cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RoleUtilisateurDTO result = roleUtilisateurService.save(roleUtilisateurDTO);
        return ResponseEntity.created(new URI("/api/role-utilisateurs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /role-utilisateurs} : Updates an existing roleUtilisateur.
     *
     * @param roleUtilisateurDTO the roleUtilisateurDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated roleUtilisateurDTO,
     * or with status {@code 400 (Bad Request)} if the roleUtilisateurDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the roleUtilisateurDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/role-utilisateurs")
    public ResponseEntity<RoleUtilisateurDTO> updateRoleUtilisateur(@Valid @RequestBody RoleUtilisateurDTO roleUtilisateurDTO) throws URISyntaxException {
        log.debug("REST request to update RoleUtilisateur : {}", roleUtilisateurDTO);
        if (roleUtilisateurDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RoleUtilisateurDTO result = roleUtilisateurService.save(roleUtilisateurDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, roleUtilisateurDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /role-utilisateurs} : get all the roleUtilisateurs.
     *
     * @param pageable the pagination information.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of roleUtilisateurs in body.
     */
    @GetMapping("/role-utilisateurs")
    public ResponseEntity<List<RoleUtilisateurDTO>> getAllRoleUtilisateurs(Pageable pageable, @RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get a page of RoleUtilisateurs");
        Page<RoleUtilisateurDTO> page;
        if (eagerload) {
            page = roleUtilisateurService.findAllWithEagerRelationships(pageable);
        } else {
            page = roleUtilisateurService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /role-utilisateurs/:id} : get the "id" roleUtilisateur.
     *
     * @param id the id of the roleUtilisateurDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the roleUtilisateurDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/role-utilisateurs/{id}")
    public ResponseEntity<RoleUtilisateurDTO> getRoleUtilisateur(@PathVariable Long id) {
        log.debug("REST request to get RoleUtilisateur : {}", id);
        Optional<RoleUtilisateurDTO> roleUtilisateurDTO = roleUtilisateurService.findOne(id);
        return ResponseUtil.wrapOrNotFound(roleUtilisateurDTO);
    }

    /**
     * {@code DELETE  /role-utilisateurs/:id} : delete the "id" roleUtilisateur.
     *
     * @param id the id of the roleUtilisateurDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/role-utilisateurs/{id}")
    public ResponseEntity<Void> deleteRoleUtilisateur(@PathVariable Long id) {
        log.debug("REST request to delete RoleUtilisateur : {}", id);

        roleUtilisateurService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
