package com.kobe.nucleus.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kobe.nucleus.domain.User;
import com.kobe.nucleus.domain.enumeration.TypeMagasin;
import com.kobe.nucleus.repository.CustomizedMagasinService;
import com.kobe.nucleus.service.MagasinService;
import com.kobe.nucleus.service.UserService;
import com.kobe.nucleus.service.dto.MagasinDTO;
import com.kobe.nucleus.service.dto.MagasinInfos;
import com.kobe.nucleus.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.kobe.nucleus.domain.Magasin}.
 */
@RestController
@RequestMapping("/api")
public class MagasinResource {

	private final Logger log = LoggerFactory.getLogger(MagasinResource.class);

	private static final String ENTITY_NAME = "magasin";

	@Value("${jhipster.clientApp.name}")
	private String applicationName;

	private final MagasinService magasinService;
	private final UserService userService;
	private final CustomizedMagasinService customizedMagasinService;

	public MagasinResource(MagasinService magasinService, UserService userService,
			CustomizedMagasinService customizedMagasinService) {
		this.magasinService = magasinService;
		this.userService = userService;
		this.customizedMagasinService = customizedMagasinService;
	}

	/**
	 * {@code POST  /magasins} : Create a new magasin.
	 *
	 * @param magasinDTO the magasinDTO to create.
	 * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
	 *         body the new magasinDTO, or with status {@code 400 (Bad Request)} if
	 *         the magasin has already an ID.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PostMapping("/magasins")
	public ResponseEntity<MagasinDTO> createMagasin(@Valid @RequestBody MagasinDTO magasinDTO)
			throws URISyntaxException, Exception {
		log.debug("REST request to save Magasin : {}", magasinDTO);
		if (magasinDTO.getId() != null) {
			throw new BadRequestAlertException("A new magasin cannot already have an ID", ENTITY_NAME, "idexists");
		}
		MagasinDTO result = customizedMagasinService.save(magasinDTO);
		return ResponseEntity
				.created(new URI("/api/magasins/" + result.getId())).headers(HeaderUtil
						.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
				.body(result);
	}

	/**
	 * {@code PUT  /magasins} : Updates an existing magasin.
	 *
	 * @param magasinDTO the magasinDTO to update.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
	 *         the updated magasinDTO, or with status {@code 400 (Bad Request)} if
	 *         the magasinDTO is not valid, or with status
	 *         {@code 500 (Internal Server Error)} if the magasinDTO couldn't be
	 *         updated.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PutMapping("/magasins")
	public ResponseEntity<MagasinDTO> updateMagasin(@Valid @RequestBody MagasinDTO magasinDTO)
			throws URISyntaxException, Exception {
		log.debug("REST request to update Magasin : {}", magasinDTO);
		if (magasinDTO.getId() == null) {
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
		}
		MagasinDTO result = customizedMagasinService.update(magasinDTO);
		return ResponseEntity.ok().headers(
				HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, magasinDTO.getId().toString()))
				.body(result);
	}

	/**
	 * {@code GET  /magasins} : get all the magasins.
	 *
	 * @param  the pagination information.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
	 *         of magasins in body.
	 */
	@GetMapping("/magasins")
	public ResponseEntity<List<MagasinDTO>> getAllMagasins() {
		log.debug("REST request to get a page of Magasins");
		return ResponseEntity.ok().body(magasinService.findAll());
	}

	/**
	 * {@code GET  /magasins/:id} : get the "id" magasin.
	 *
	 * @param id the id of the magasinDTO to retrieve.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
	 *         the magasinDTO, or with status {@code 404 (Not Found)}.
	 */
	@GetMapping("/magasins/{id}")
	public ResponseEntity<MagasinDTO> getMagasin(@PathVariable Long id) {
		log.debug("REST request to get Magasin : {}", id);
		Optional<MagasinDTO> magasinDTO = magasinService.findOne(id);
		return ResponseUtil.wrapOrNotFound(magasinDTO);
	}

	/**
	 * {@code DELETE  /magasins/:id} : delete the "id" magasin.
	 *
	 * @param id the id of the magasinDTO to delete.
	 * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
	 */
	@DeleteMapping("/magasins/{id}")
	public ResponseEntity<Void> deleteMagasin(@PathVariable Long id) {
		log.debug("REST request to delete Magasin : {}", id);

		magasinService.delete(id);
		return ResponseEntity.noContent()
				.headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
				.build();
	}

	@GetMapping("/magasins/user/magasin")
	public ResponseEntity<MagasinDTO> connectedUserMagasin()

	{
		Optional<User> connectedUser = userService.getUserWithAuthorities();
		if (connectedUser.isPresent()) {
			return ResponseUtil.wrapOrNotFound(magasinService.findOne(connectedUser.get().getMagasin().getId()));
		}
		// for testing purpose, to remove in production profile
		return ResponseUtil.wrapOrNotFound(magasinService.findOne(1l));
		// return ResponseUtil.wrapOrNotFound(Optional.empty());
	}

	@GetMapping("/magasins/typemagasin/{typemagasin}")
	public ResponseEntity<List<MagasinDTO>> getAllTypeMagasins(
			@PathVariable(name = "typemagasin") TypeMagasin typeMagasin) {
		log.debug("REST request to get all type Magasins");
		return ResponseEntity.ok().body(magasinService.findByTypeMagasin(typeMagasin));
	}

	@PostMapping("/magasins/depot")
	public ResponseEntity<MagasinDTO> createMagasinDepot(@Valid @RequestBody MagasinDTO magasinDTO)
			throws URISyntaxException, Exception {
		log.debug("REST request to save Magasin : {}", magasinDTO);
		if (magasinDTO.getId() != null) {
			throw new BadRequestAlertException("A new magasin cannot already have an ID", ENTITY_NAME, "idexists");
		}
		MagasinDTO result = customizedMagasinService.save(magasinDTO);
		return ResponseEntity
				.created(new URI("/api/magasins/depot" + result.getId())).headers(HeaderUtil
						.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
				.body(result);
	}

	@PutMapping("/magasins/depot")
	public ResponseEntity<MagasinDTO> updateMagasinDepot(@Valid @RequestBody MagasinDTO magasinDTO)
			throws URISyntaxException, Exception {
		log.debug("REST request to update Magasin : {}", magasinDTO);
		if (magasinDTO.getId() == null) {
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
		}
		MagasinDTO result = customizedMagasinService.update(magasinDTO);
		return ResponseEntity.ok().headers(
				HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, magasinDTO.getId().toString()))
				.body(result);
	}

	@PutMapping("/magasins/infosticket")
	public ResponseEntity<MagasinDTO> updateInfoTicket(@Valid @RequestBody MagasinInfos magasinInfos)
			throws URISyntaxException, Exception {
		log.debug("REST request to update infosticket : {}", magasinInfos);

		MagasinDTO result = customizedMagasinService.update(magasinInfos);
		return ResponseEntity.ok().headers(
				HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, magasinInfos.getId() + ""))
				.body(result);
	}

	@PostMapping("/magasins/stockage")
	public ResponseEntity<MagasinDTO> createMagasinStockage(@Valid @RequestBody MagasinDTO magasinDTO)
			throws URISyntaxException, Exception {
		log.debug("REST request to save Magasin : {}", magasinDTO);
		if (magasinDTO.getId() != null) {
			throw new BadRequestAlertException("A new magasin cannot already have an ID", ENTITY_NAME, "idexists");
		}
		MagasinDTO result = customizedMagasinService.save(magasinDTO);
		return ResponseEntity
				.created(new URI("/api/magasins/depot" + result.getId())).headers(HeaderUtil
						.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
				.body(result);
	}

	@PutMapping("/magasins/stockage")
	public ResponseEntity<MagasinDTO> updateStockage(@Valid @RequestBody MagasinDTO magasinDTO)
			throws URISyntaxException, Exception {
		log.debug("REST request to update Magasin : {}", magasinDTO);
		if (magasinDTO.getId() == null) {
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
		}
		MagasinDTO result = customizedMagasinService.updateStockage(magasinDTO);
		return ResponseEntity.ok().headers(
				HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, magasinDTO.getId().toString()))
				.body(result);
	}

	@PutMapping("/magasins/{id}/user/{managerId}")
	public ResponseEntity<MagasinDTO> updateManager(@PathVariable(name = "id") Long id,@PathVariable(name = "managerId") Long managerId)
			throws URISyntaxException, Exception {
		log.debug("REST request to update id : {}", id);
		log.debug("REST request to update managerId : {}", managerId);
		MagasinDTO result = customizedMagasinService.updateManager(id, managerId);
		return ResponseEntity.ok()
				.headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, id.toString()))
				.body(result);
	}

	@GetMapping("/magasins/user/stockages")
	public ResponseEntity<Set<MagasinDTO>> connectedUserStockages()

	{
		Set<MagasinDTO> stockages = new HashSet<>();
		Optional<User> connectedUser = userService.getUserWithAuthorities();
		if (connectedUser.isPresent()) {
			Optional<MagasinDTO> dto = magasinService.findOne(connectedUser.get().getMagasin().getId());
			if (dto.isPresent()) {
				stockages = dto.get().getMagasins();
				if (stockages.isEmpty()) {
					stockages.add(dto.get());
				}

			}
			return ResponseEntity.ok().body(stockages);
		}

		return ResponseEntity.ok().body(magasinService.findOne(1l).get().getMagasins());

	}


	@GetMapping("/magasins/principals")
	public ResponseEntity<List<MagasinDTO>> getAllMagasinsOrDepots() {
		log.debug("REST request to get a page of Magasins");
		return ResponseEntity.ok().body(magasinService.findAllMagasinAndDepots());
	}
}
