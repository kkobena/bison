package com.kobe.nucleus.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.kobe.nucleus.domain.AyantDroit;
import com.kobe.nucleus.domain.Client;
import com.kobe.nucleus.domain.CompteClient;
import com.kobe.nucleus.domain.Tierspayant;
import com.kobe.nucleus.domain.enumeration.Status;
import com.kobe.nucleus.domain.enumeration.TypeClient;
import com.kobe.nucleus.service.dto.AyantDroitDTO;
import com.kobe.nucleus.service.dto.ClientDTO;
import com.kobe.nucleus.service.dto.CompteClientDTO;
import com.kobe.nucleus.service.dto.VenteDTO;

@Repository
@Transactional
public class CustomizedClientRepository implements CustomizedClientService {
	@PersistenceContext
	private EntityManager em;
	private final Logger log = LoggerFactory.getLogger(CustomizedClientRepository.class);
	@Autowired
	private CompteClientRepository compteClientRepo;
	@Autowired
	private ClientRepository clientRepo;
	/*@Autowired
	private AyantDroitRepository ayantDroitRepod;
	@Autowired
	private TierspayantRepository tierspayantRepo;
*/
	@Override
	@Transactional(readOnly = true)
	public List<ClientDTO> findAll(String search, TypeClient typeClient, Status status) {

		try {

			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Client> cq = cb.createQuery(Client.class);
			Root<Client> root = cq.from(Client.class);
			root.fetch("ayantDroits", JoinType.LEFT);
			Join<Client, CompteClient> fetch = root.join("compteClients", JoinType.LEFT);
			cq.select(root).distinct(true).orderBy(cb.asc(root.get("firstName")), cb.asc(root.get("lastName")));
			List<Predicate> predicates = buldPredicates(search, typeClient, status, cb, root, fetch);
			cq.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
			TypedQuery<Client> q = em.createQuery(cq);
			return q.getResultList().stream().map(e -> new ClientDTO(e)).collect(Collectors.toList());
		} catch (Exception e) {
			log.debug("client count  : {}", e);
			return Collections.emptyList();
		}

	}

	private List<Predicate> buldPredicates(String search, TypeClient typeClient, Status status, CriteriaBuilder cb,
			Root<Client> root, Join<Client, CompteClient> join) {
		List<Predicate> predicates = new ArrayList<>();
		if (!StringUtils.isEmpty(search)) {
			search = search.toUpperCase();
			predicates.add(cb.or(cb.like(cb.upper(root.get("firstName")) , search + "%"),
					cb.like(cb.upper(root.get("lastName")), search + "%"), cb.like(cb.upper(join.get("numMaticule")), search + "%"),
					cb.like(cb.concat(cb.concat(cb.upper(root.get("firstName")), " "),cb.upper( root.get("lastName"))), search + "%")));
		}
		if (!ObjectUtils.isEmpty(typeClient)) {
			predicates.add(cb.equal(root.get("typeClient"), typeClient));
		}
		if (!ObjectUtils.isEmpty(status)) {
			predicates.add(cb.equal(root.get("status"), status));
		}
		return predicates;
	}

	@Transactional(readOnly = true)
	private long count(String search, TypeClient typeClient, Status status) {
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Long> cq = cb.createQuery(Long.class);

			Root<Client> root = cq.from(Client.class);
			Join<Client, CompteClient> fetch = root.join("compteClients", JoinType.LEFT);
			cq.select(cb.countDistinct(root));
			List<Predicate> predicates = buldPredicates(search, typeClient, status, cb, root, fetch);
			cq.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
			Query q = em.createQuery(cq);

			return ((Long) q.getSingleResult()).intValue();
		} catch (Exception e) {
			log.debug("client count  : {}", e);
			return 0;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Integer encoursClient(long clientId) {
		try {
			return compteClientRepo.findByClientId(clientId).stream().map(e -> e.getEncours()).reduce(0, Integer::sum);

		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}

	@Override
	@Transactional
	public ClientDTO save(ClientDTO dto) throws Exception {
		final Client client = buildClientFromClientDTO(dto);
		dto.getAyantDroits().stream().forEach(ayantDroitDto -> {
			client.addAyantDroit(buildAyantDroitFromAyantDroitDTO(ayantDroitDto));
		});
		CompteClientDTO compteClientDTO = dto.getCompteClient();
		if (compteClientDTO != null) {
			client.addCompteClient(buildCompteClientFromCompteClientDTO(compteClientDTO));
		}

		dto.getCompteClients().stream().forEach(cmpt -> {
			client.addCompteClient(buildCompteClientFromCompteClientDTO(cmpt));
		});
		Client clt = em.merge(client);
		Optional<Client> op = clientRepo.findById(clt.getId());
		if (op.isPresent()) {

			return new ClientDTO(op.get());
		}
		return null;
	}

	@Override
	public ClientDTO update(ClientDTO dto) throws Exception {
		final Client client = buildClientFromClientDTO(dto, clientRepo.getOne(dto.getId()));
		dto.getAyantDroits().stream().forEach(ayantDroitDto -> {
			client.addAyantDroit(buildAyantDroitFromAyantDroitDTO(ayantDroitDto));
		});
		CompteClientDTO compteClientDTO = dto.getCompteClient();
		if (compteClientDTO != null) {
			if (compteClientDTO.getId() != null) {
				compteClientRepo.findById(compteClientDTO.getId()).ifPresent(optional -> {
					client.addCompteClient(buildCompteClientFromCompteClientDTO(dto.getCompteClient(), optional));
				});
			} else {
				client.addCompteClient(buildCompteClientFromCompteClientDTO(dto.getCompteClient()));
			}

		}

		dto.getCompteClients().stream().forEach(cmpt -> {
			if (cmpt.getId() != null) {
				compteClientRepo.findById(cmpt.getId()).ifPresent(optional -> {
					client.addCompteClient(buildCompteClientFromCompteClientDTO(cmpt, optional));
				});
			} else {
				client.addCompteClient(buildCompteClientFromCompteClientDTO(cmpt));
			}

		});
		Client clt = clientRepo.saveAndFlush(client);
		Optional<Client> op = clientRepo.findById(clt.getId());
		if (op.isPresent()) {
			return new ClientDTO(op.get());
		}
		return null;

	}

	@Override
	public Page<VenteDTO> findVenteByClientId(long clientId, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CompteClientDTO save(CompteClientDTO dto) throws Exception {
		CompteClient compteClient = buildCompteClientFromCompteClientDTO(dto);
		Client client = em.find(Client.class, dto.getClientId());
		compteClient.setClient(client);
		compteClient.setTierspayant(em.find(Tierspayant.class, dto.getTierspayantId()));
		compteClient = em.merge(compteClient);
		em.refresh(client);
		return new CompteClientDTO(compteClient);
	}

	@Override
	public CompteClientDTO update(CompteClientDTO dto) throws Exception {
		CompteClient compteClient = buildCompteClientFromCompteClientDTO(dto, compteClientRepo.getOne(dto.getId()));
		Client client = em.find(Client.class, dto.getClientId());
		compteClient.setClient(client);
		compteClient.setTierspayant(em.find(Tierspayant.class, dto.getTierspayantId()));
		compteClient = em.merge(compteClient);
		em.refresh(client);
		return new CompteClientDTO(compteClient);
	}

	@Override
	public AyantDroitDTO save(AyantDroitDTO dto) throws Exception {
		AyantDroit ayantDroit = buildAyantDroitFromAyantDroitDTO(dto);
		Client client = em.find(Client.class, dto.getAssureId());
		ayantDroit.setAssure(client);
		ayantDroit = em.merge(ayantDroit);
		em.refresh(client);
		return new AyantDroitDTO(ayantDroit);

	}

	@Override
	public AyantDroitDTO update(AyantDroitDTO dto) throws Exception {
		AyantDroit ayantDroit=em.find(AyantDroit.class, dto.getId());
		 ayantDroit = buildAyantDroitFromAyantDroitDTO(dto, ayantDroit);
		ayantDroit =  em.merge(ayantDroit);
		em.refresh(ayantDroit.getAssure());
		return new AyantDroitDTO(ayantDroit);

	}

	@Override
	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Client> cq = cb.createQuery(Client.class);
		Root<Client> root = cq.from(Client.class);
		root.fetch("ayantDroits");
		root.fetch("compteClients");
		cq.select(root).distinct(true);
		cq.where(cb.equal(root.get("id"), id));
		TypedQuery<Client> q = em.createQuery(cq);
		q.setMaxResults(1);
		return new ClientDTO(q.getSingleResult());
	}

}
