package com.kobe.nucleus.service.impl;

import com.kobe.nucleus.service.ClientService;
import com.kobe.nucleus.domain.Client;
import com.kobe.nucleus.domain.enumeration.Status;
import com.kobe.nucleus.domain.enumeration.TypeClient;
import com.kobe.nucleus.repository.ClientRepository;
import com.kobe.nucleus.repository.CustomizedClientService;
import com.kobe.nucleus.service.dto.ClientDTO;
import com.kobe.nucleus.service.mapper.ClientMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Client}.
 */
@Service
@Transactional
public class ClientServiceImpl implements ClientService {

	private final Logger log = LoggerFactory.getLogger(ClientServiceImpl.class);

	private final ClientRepository clientRepository;
	private final CustomizedClientService customizedClientService;
	private final ClientMapper clientMapper;

	public ClientServiceImpl(ClientRepository clientRepository, ClientMapper clientMapper,
			CustomizedClientService customizedClientService) {
		this.clientRepository = clientRepository;
		this.clientMapper = clientMapper;
		this.customizedClientService = customizedClientService;
	}

	/**
	 * Save a client.
	 *
	 * @param clientDTO the entity to save.
	 * @return the persisted entity.
	 */
	@Override
	public ClientDTO save(ClientDTO clientDTO) throws Exception {
		log.debug("Request to save Client : {}", clientDTO);
		if (clientDTO.getId() == null) {
			return customizedClientService.save(clientDTO);
		}
		return customizedClientService.update(clientDTO);
	}

	/**
	 * Get all the clients.
	 *
	 * @param pageable the pagination information.
	 * @return the list of entities.
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<ClientDTO> findAll(Pageable pageable) {
		log.debug("Request to get all Clients");
		return clientRepository.findAll(pageable).map(clientMapper::toDto);
	}

	/**
	 * Get one client by id.
	 *
	 * @param id the id of the entity.
	 * @return the entity.
	 */
	@Override
	@Transactional(readOnly = true)
	public Optional<ClientDTO> findOne(Long id) {
		log.debug("Request to get Client : {}", id);
		return clientRepository.findById(id).map(ClientDTO::new);
	}

	/**
	 * Delete the client by id.
	 *
	 * @param id the id of the entity.
	 */
	@Override
	public void delete(Long id) {
		log.debug("Request to delete Client : {}", id);
		clientRepository.deleteById(id);
	}

	/**
	 * Get all the clients with ayantDroits and compteClients.
	 * 
	 * @search Search criteria
	 * @param pageable the pagination information.
	 * @return the list of entities.
	 */
	@Override
	@Transactional(readOnly = true)
	public List<ClientDTO> findAll(String search, TypeClient typeClient, Status status) {
		log.debug("Request to get all Clients");
		return customizedClientService.findAll(search, typeClient, status);

	}

	@Override
	public void activate(Long id,final boolean enable) {
		clientRepository.findById(id).ifPresent(c->{
		 if(enable) {
			 c.setStatus(Status.ACTIVE);
		 }else {
			 c.setStatus(Status.DESACTIVE);
		 }
		 clientRepository.save(c);
		});
	
	}
	
	
}
