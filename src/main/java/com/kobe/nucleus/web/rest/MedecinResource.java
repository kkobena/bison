package com.kobe.nucleus.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.kobe.nucleus.service.MedecinService;
import com.kobe.nucleus.service.dto.MedecinDTO;
import com.kobe.nucleus.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.kobe.nucleus.domain.Medecin}.
 */
@RestController
@RequestMapping("/api")
public class MedecinResource {

	private final Logger log = LoggerFactory.getLogger(MedecinResource.class);

	private static final String ENTITY_NAME = "medecin";

	@Value("${jhipster.clientApp.name}")
	private String applicationName;

	private final MedecinService medecinService;

	public MedecinResource(MedecinService medecinService) {
		this.medecinService = medecinService;

	}

	/**
	 * {@code POST  /medecins} : Create a new medecin.
	 *
	 * @param medecinDTO the medecinDTO to create.
	 * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
	 *         body the new medecinDTO, or with status {@code 400 (Bad Request)} if
	 *         the medecin has already an ID.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PostMapping("/medecins")
	public ResponseEntity<MedecinDTO> createMedecin(@Valid @RequestBody MedecinDTO medecinDTO)
			throws URISyntaxException {
		log.debug("REST request to save Medecin : {}", medecinDTO);
		if (medecinDTO.getId() != null) {
			throw new BadRequestAlertException("A new medecin cannot already have an ID", ENTITY_NAME, "idexists");
		}
		MedecinDTO result = medecinService.save(medecinDTO);
		return ResponseEntity
				.created(new URI("/api/medecins/" + result.getId())).headers(HeaderUtil
						.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
				.body(result);
	}

	/**
	 * {@code PUT  /medecins} : Updates an existing medecin.
	 *
	 * @param medecinDTO the medecinDTO to update.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
	 *         the updated medecinDTO, or with status {@code 400 (Bad Request)} if
	 *         the medecinDTO is not valid, or with status
	 *         {@code 500 (Internal Server Error)} if the medecinDTO couldn't be
	 *         updated.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PutMapping("/medecins")
	public ResponseEntity<MedecinDTO> updateMedecin(@Valid @RequestBody MedecinDTO medecinDTO)
			throws URISyntaxException {
		log.debug("REST request to update Medecin : {}", medecinDTO);
		if (medecinDTO.getId() == null) {
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
		}
		MedecinDTO result = medecinService.save(medecinDTO);
		return ResponseEntity.ok().headers(
				HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, medecinDTO.getId().toString()))
				.body(result);
	}

	/**
	 * {@code GET  /medecins} : get all the medecins.
	 *
	 * @param pageable the pagination information.
	 * @param criteria the criteria which the requested entities should match.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
	 *         of medecins in body.
	 */
	@GetMapping(value = "/medecins", params = { "search" })
	public ResponseEntity<List<MedecinDTO>> getAllMedecins(@RequestParam("search") String search, Pageable pageable) {
		log.debug("REST request to get Medecins by criteria: {}", search);
		Page<MedecinDTO> page = medecinService.findAll(pageable);
		HttpHeaders headers = PaginationUtil
				.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
		return ResponseEntity.ok().headers(headers).body(page.getContent());
	}

	/**
	 * {@code GET  /medecins/:id} : get the "id" medecin.
	 *
	 * @param id the id of the medecinDTO to retrieve.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
	 *         the medecinDTO, or with status {@code 404 (Not Found)}.
	 */
	@GetMapping("/medecins/{id}")
	public ResponseEntity<MedecinDTO> getMedecin(@PathVariable Long id) {
		log.debug("REST request to get Medecin : {}", id);
		Optional<MedecinDTO> medecinDTO = medecinService.findOne(id);
		return ResponseUtil.wrapOrNotFound(medecinDTO);
	}

	/**
	 * {@code DELETE  /medecins/:id} : delete the "id" medecin.
	 *
	 * @param id the id of the medecinDTO to delete.
	 * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
	 */
	@DeleteMapping("/medecins/{id}")
	public ResponseEntity<Void> deleteMedecin(@PathVariable Long id) {
		log.debug("REST request to delete Medecin : {}", id);
		medecinService.delete(id);
		return ResponseEntity.noContent()
				.headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
				.build();
	}
}
