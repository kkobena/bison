package com.kobe.nucleus.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

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

import com.kobe.nucleus.domain.enumeration.Status;
import com.kobe.nucleus.domain.enumeration.TypeClient;
import com.kobe.nucleus.service.ClientService;
import com.kobe.nucleus.service.dto.ClientDTO;
import com.kobe.nucleus.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.kobe.nucleus.domain.Client}.
 */
@RestController
@RequestMapping("/api")
public class ClientResource {

	private final Logger log = LoggerFactory.getLogger(ClientResource.class);

	private static final String ENTITY_NAME = "client";

	@Value("${jhipster.clientApp.name}")
	private String applicationName;

	private final ClientService clientService;

	public ClientResource(ClientService clientService) {
		this.clientService = clientService;
	}

	/**
	 * {@code POST  /clients} : Create a new client.
	 *
	 * @param clientDTO the clientDTO to create.
	 * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
	 *         body the new clientDTO, or with status {@code 400 (Bad Request)} if
	 *         the client has already an ID.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PostMapping("/clients")
	public ResponseEntity<ClientDTO> createClient(@Valid @RequestBody ClientDTO clientDTO) throws URISyntaxException {
		log.debug("REST request to save Client : {}", clientDTO);
		if (clientDTO.getId() != null) {
			throw new BadRequestAlertException("A new client cannot already have an ID", ENTITY_NAME, "idexists");
		}
		try {
			ClientDTO result = clientService.save(clientDTO);
			return ResponseEntity
					.created(new URI("/api/clients/" + result.getId())).headers(HeaderUtil
							.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
					.body(result);
		} catch (Exception e) {
			log.debug("REST request to save Client : {}", e);
			throw new BadRequestAlertException( e.getLocalizedMessage(), ENTITY_NAME, e.getMessage());
		}

	}

	/**
	 * {@code PUT  /clients} : Updates an existing client.
	 *
	 * @param clientDTO the clientDTO to update.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
	 *         the updated clientDTO, or with status {@code 400 (Bad Request)} if
	 *         the clientDTO is not valid, or with status
	 *         {@code 500 (Internal Server Error)} if the clientDTO couldn't be
	 *         updated.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PutMapping("/clients")
	public ResponseEntity<ClientDTO> updateClient(@Valid @RequestBody ClientDTO clientDTO) throws URISyntaxException {
		log.debug("REST request to update Client : {}", clientDTO);
		if (clientDTO.getId() == null) {
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
		}
		try {
			ClientDTO result = clientService.save(clientDTO);
			return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME,
					clientDTO.getId().toString())).body(result);
		} catch (Exception e) {
			log.debug("REST request to save Client : {}", e);
			throw new BadRequestAlertException(e.getLocalizedMessage(), ENTITY_NAME, e.getMessage());
		}
	}

	/**
	 * {@code GET  /clients} : get all the clients.
	 *
	 * @param pageable the pagination information.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
	 *         of clients in body.
	 */
	@GetMapping("/clients")
	public ResponseEntity<List<ClientDTO>> getAllClients(String search, TypeClient typeClient, Status status) {
		log.debug("REST request to get a page of Clients");
		List<ClientDTO> data = clientService.findAll(search, typeClient, status);

		return ResponseEntity.ok().body(data);
	}

	/**
	 * {@code GET  /clients/:id} : get the "id" client.
	 *
	 * @param id the id of the clientDTO to retrieve.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
	 *         the clientDTO, or with status {@code 404 (Not Found)}.
	 */
	@GetMapping("/clients/{id}")
	public ResponseEntity<ClientDTO> getClient(@PathVariable Long id) {
		log.debug("REST request to get Client : {}", id);
		Optional<ClientDTO> clientDTO = clientService.findOne(id);
		return ResponseUtil.wrapOrNotFound(clientDTO);
	}

	/**
	 * {@code DELETE  /clients/:id} : delete the "id" client.
	 *
	 * @param id the id of the clientDTO to delete.
	 * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
	 */
	@DeleteMapping("/clients/{id}")
	public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
		log.debug("REST request to delete Client : {}", id);
		clientService.delete(id);
		return ResponseEntity.noContent()
				.headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
				.build();
	}
	
	@DeleteMapping("/clients/{id}/{enable}")
	public ResponseEntity<Void> activate(@PathVariable("id")  Long id,@PathVariable("enable") boolean enable) {
		log.debug("REST request to activate Client : {}", id);
		clientService.activate(id,enable);
		return ResponseEntity.ok()
				.build();
	}
}
