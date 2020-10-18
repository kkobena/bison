package com.kobe.nucleus.service.impl;

import com.kobe.nucleus.domain.enumeration.Status;
import com.kobe.nucleus.repository.util.Condition;
import com.kobe.nucleus.repository.util.SpecificationBuilder;
import com.kobe.nucleus.service.ClientService;
import com.kobe.nucleus.domain.Client;
import com.kobe.nucleus.repository.ClientRepository;
import com.kobe.nucleus.service.dto.ClientDTO;
import com.kobe.nucleus.service.mapper.ClientMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Client}.
 */
@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    private final Logger log = LoggerFactory.getLogger(ClientServiceImpl.class);

    private final ClientRepository clientRepository;

    private final ClientMapper clientMapper;

    public ClientServiceImpl(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    /**
     * Save a client.
     *
     * @param clientDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ClientDTO save(ClientDTO clientDTO) {
        log.debug("Request to save Client : {}", clientDTO);
        Client client = clientMapper.toEntity(clientDTO);
        client = clientRepository.save(client);
        return clientMapper.toDto(client);
    }

    /**
     * Get all the clients.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ClientDTO> findAll(String search, Pageable pageable) {

        log.debug("Request to get all Clients");
        Pageable page = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
            Sort.by(Sort.Direction.ASC, "firstName", "lastName"));
        Specification<Client> spec=(root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("status"), Status.ACTIVE);

        if (search != null && !search.isBlank()) {
            SpecificationBuilder<Client> builder = new SpecificationBuilder<>();
            Specification<Client> _spec  = builder
                .with(new String[]{"firstName"}, search + "%", Condition.OperationType.LIKE, Condition.LogicalOperatorType.OR)
                .with(new String[]{"firstName"}, search + "%", Condition.OperationType.LIKE, Condition.LogicalOperatorType.OR)
                .with(new String[]{"num"}, search + "%", Condition.OperationType.LIKE, Condition.LogicalOperatorType.OR)
                .with(new String[]{"codeInterne"}, search + "%", Condition.OperationType.LIKE, Condition.LogicalOperatorType.END)
                .build();
            spec=  spec.and(_spec);
        }
        spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("status"), Status.ACTIVE));
        return clientRepository.findAll(spec, page).map(clientMapper::toDto);


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
        return clientRepository.findById(id)
            .map(clientMapper::toDto);
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

    @Override
    public Page<ClientDTO> findAllByTiersPayant(@NotNull Integer tiersPayantId, String search, Pageable pageable) {
        return null;
    }
}
