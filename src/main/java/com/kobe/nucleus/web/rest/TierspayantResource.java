package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.domain.enumeration.Status;
import com.kobe.nucleus.service.TierspayantService;
import com.kobe.nucleus.web.rest.dto.ResponseDTO;
import com.kobe.nucleus.web.rest.errors.BadRequestAlertException;
import com.kobe.nucleus.service.dto.TierspayantDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;

import org.apache.commons.lang3.RandomStringUtils;
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
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * REST controller for managing {@link com.kobe.nucleus.domain.Tierspayant}.
 */
@RestController
@RequestMapping("/api")
public class TierspayantResource {

	private final Logger log = LoggerFactory.getLogger(TierspayantResource.class);

	private static final String ENTITY_NAME = "tierspayant";

	@Value("${jhipster.clientApp.name}")
	private String applicationName;

	private final TierspayantService tierspayantService;

	public TierspayantResource(TierspayantService tierspayantService) {
		this.tierspayantService = tierspayantService;
	}

	/**
	 * {@code POST  /tierspayants} : Create a new tierspayant.
	 *
	 * @param tierspayantDTO the tierspayantDTO to create.
	 * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
	 *         body the new tierspayantDTO, or with status {@code 400 (Bad Request)}
	 *         if the tierspayant has already an ID.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PostMapping("/tierspayants")
	public ResponseEntity<TierspayantDTO> createTierspayant(@Valid @RequestBody TierspayantDTO tierspayantDTO)
			throws URISyntaxException {
		log.debug("REST request to save Tierspayant : {}", tierspayantDTO);
		if (tierspayantDTO.getId() != null) {
			throw new BadRequestAlertException("A new tierspayant cannot already have an ID", ENTITY_NAME, "idexists");
		}
		TierspayantDTO result = tierspayantService.save(tierspayantDTO
				.code(RandomStringUtils.random(4, true, true).toUpperCase())
				.enable(true)
				.status(Status.ACTIVE)
				.createdAt(Instant.now())
				.updatedAt(Instant.now())
				
				);
		return ResponseEntity
				.created(new URI("/api/tierspayants/" + result.getId())).headers(HeaderUtil
						.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
				.body(result);
	}

	/**
	 * {@code PUT  /tierspayants} : Updates an existing tierspayant.
	 *
	 * @param tierspayantDTO the tierspayantDTO to update.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
	 *         the updated tierspayantDTO, or with status {@code 400 (Bad Request)}
	 *         if the tierspayantDTO is not valid, or with status
	 *         {@code 500 (Internal Server Error)} if the tierspayantDTO couldn't be
	 *         updated.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PutMapping("/tierspayants")
	public ResponseEntity<TierspayantDTO> updateTierspayant(@Valid @RequestBody TierspayantDTO tierspayantDTO)
			throws URISyntaxException {
		log.debug("REST request to update Tierspayant : {}", tierspayantDTO);
		if (tierspayantDTO.getId() == null) {
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
		}
		TierspayantDTO result = tierspayantService.update(tierspayantDTO);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME,
				tierspayantDTO.getId().toString())).body(result);
	}

	/**
	 * {@code GET  /tierspayants} : get all the tierspayants.
	 *
	 * @param pageable the pagination information.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
	 *         of tierspayants in body.
	 */
	@GetMapping(value = "/tierspayants", params = { "search" })
	public ResponseEntity<List<TierspayantDTO>> getAllTierspayants(@RequestParam("search") String search,
			Pageable pageable) {
		log.debug("REST request to get a page of Tierspayants");
		Page<TierspayantDTO> page = tierspayantService.findAll(search, pageable);
		HttpHeaders headers = PaginationUtil
				.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
		return ResponseEntity.ok().headers(headers).body(page.getContent());
	}

	/**
	 * {@code GET  /tierspayants/:id} : get the "id" tierspayant.
	 *
	 * @param id the id of the tierspayantDTO to retrieve.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
	 *         the tierspayantDTO, or with status {@code 404 (Not Found)}.
	 */
	@GetMapping("/tierspayants/{id}")
	public ResponseEntity<TierspayantDTO> getTierspayant(@PathVariable Long id) {
		log.debug("REST request to get Tierspayant : {}", id);
		Optional<TierspayantDTO> tierspayantDTO = tierspayantService.findOne(id);
		return ResponseUtil.wrapOrNotFound(tierspayantDTO);
	}

	/**
	 * {@code DELETE  /tierspayants/:id} : delete the "id" tierspayant.
	 *
	 * @param id the id of the tierspayantDTO to delete.
	 * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
	 */
	@DeleteMapping("/tierspayants/{id}")
	public ResponseEntity<Void> deleteTierspayant(@PathVariable Long id) {
		log.debug("REST request to delete Tierspayant : {}", id);

		tierspayantService.delete(id);
		return ResponseEntity.noContent()
				.headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
				.build();
	}

	@PostMapping("/tierspayants/importcsv")
	public ResponseEntity<ResponseDTO> importcsv(@RequestParam("importcsv") MultipartFile file) throws ParseException {
		try {
			ResponseDTO dto = tierspayantService.processCsvStream(file.getInputStream());
			return ResponseEntity.ok()

					.body(dto);
		} catch (IOException e) {
			log.error("Erreur importation de fichier csv ", e);
			throw new BadRequestAlertException("Erreur importation de fichier csv", ENTITY_NAME, "idexists");
		}

	}

}
