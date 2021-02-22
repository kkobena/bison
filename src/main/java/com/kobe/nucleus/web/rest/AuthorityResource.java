package com.kobe.nucleus.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.kobe.nucleus.domain.Authority;
import com.kobe.nucleus.repository.AuthorityRepository;
import com.kobe.nucleus.security.AuthoritiesConstants;
import com.kobe.nucleus.service.dto.AjustementDTO;
import com.kobe.nucleus.service.dto.AuthorityDTO;
import com.kobe.nucleus.service.dto.AuthorityProjection;
import com.kobe.nucleus.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;

@RestController
@RequestMapping("/api")
public class AuthorityResource {
	private final Logger LOG = LoggerFactory.getLogger(AuthorityResource.class);

	private static final String ENTITY_NAME = "autority";

	@Value("${jhipster.clientApp.name}")
	private String applicationName;
	private final AuthorityRepository authorityResource;

	public AuthorityResource(AuthorityRepository authorityResource) {

		this.authorityResource = authorityResource;
	}

	@GetMapping("/authorities")
	public ResponseEntity<List<AuthorityProjection>> getAll() {
		return ResponseEntity.ok().body(authorityResource.findByNameNot(AuthoritiesConstants.ADMIN));
	}

	@PostMapping("/authorities")
	public ResponseEntity<AuthorityDTO> addAuthority(@Valid @RequestBody AuthorityDTO authorityDTO)
			throws URISyntaxException {
		LOG.debug("REST request to save authorityDTO : {}", authorityDTO);
		Authority authority = new Authority();
		authority.setLibelle(authorityDTO.getLibelle());
		authority.setName(StringUtils.prependIfMissing(authorityDTO.getName(), AuthoritiesConstants.PREFIX_ROLE));
		authority = authorityResource.saveAndFlush(authority);
		return ResponseEntity.created(new URI("/api/authorities/" + authority.getName()))
				.headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, authority.getName()))
				.body(new AuthorityDTO(authority));
	}

	@PutMapping("/authorities")
	public ResponseEntity<AuthorityDTO> updateAuthority(@Valid @RequestBody AuthorityDTO authorityDTO)
			throws URISyntaxException {
		LOG.debug("REST request to save authorityDTO : {}", authorityDTO);
		if (authorityDTO.getName() == null) {
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
		}
		Authority authority = null;
		Optional<Authority> authorityOP = authorityResource.findById(authorityDTO.getName());
		if (authorityOP.isPresent()) {
			authority = authorityOP.get();
		} else {
			authority = new Authority();
			authority.setName(StringUtils.prependIfMissing(authorityDTO.getName(), AuthoritiesConstants.PREFIX_ROLE));
		}
		authority.setLibelle(authorityDTO.getLibelle());
		authority = authorityResource.saveAndFlush(authority);
		return ResponseEntity.ok()
				.headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, authorityDTO.getName()))
				.body(new AuthorityDTO(authority));
	}

	@DeleteMapping("/authorities/{name}")
	public ResponseEntity<Void> deleteAjustement(@PathVariable String name) {

		authorityResource.deleteById(name);
		return ResponseEntity.noContent()
				.headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, name)).build();
	}
}
