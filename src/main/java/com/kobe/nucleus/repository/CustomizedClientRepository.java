package com.kobe.nucleus.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.kobe.nucleus.domain.Client;
import com.kobe.nucleus.domain.CompteClient;
import com.kobe.nucleus.domain.enumeration.Status;
import com.kobe.nucleus.domain.enumeration.TypeClient;
import com.kobe.nucleus.service.dto.ClientDTO;
import com.kobe.nucleus.service.dto.VenteDTO;
import com.kobe.nucleus.web.rest.EtiquetteResource;


import liquibase.pro.packaged.iF;

@Repository
@Transactional
public class CustomizedClientRepository implements CustomizedClientService {
	@PersistenceContext
	private EntityManager em;
	private final Logger log = LoggerFactory.getLogger(CustomizedClientRepository.class);
	@Autowired
	private CompteClientRepository compteClientRepo;

	@Override
	@Transactional(readOnly = true)
	public List<ClientDTO> findAll(String search, TypeClient typeClient, Status status) {

		try {

			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Client> cq = cb.createQuery(Client.class);
			Root<Client> root = cq.from(Client.class);
			root.fetch("ayantDroits");
			Join<Client, CompteClient> fetch = root.join("compteClients", JoinType.LEFT);
			// Join<Client, CompteClient> join = (Join<Client, CompteClient>) fetch;
			cq.select(root).distinct(true).orderBy(cb.asc(root.get("firstName")), cb.asc(root.get("lastName")));
			List<Predicate> predicates = buldPredicates(search, typeClient,status, cb, root, fetch);
			cq.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
			TypedQuery<Client> q = em.createQuery(cq);
			/*
			 * if (pageable != null) { q.setFirstResult((int) pageable.getOffset());
			 * q.setMaxResults(pageable.getPageSize()); }
			 */

			return q.getResultList().stream().map(e -> new ClientDTO(e)).collect(Collectors.toList());
		} catch (Exception e) {
			log.debug("client count  : {}", e);
		}
		return null;
	}

	private List<Predicate> buldPredicates(String search, TypeClient typeClient, Status status, CriteriaBuilder cb,
			Root<Client> root, Join<Client, CompteClient> join) {
		List<Predicate> predicates = new ArrayList<>();
		if (!StringUtils.isEmpty(search)) {
			search = search.toUpperCase();
			predicates.add(cb.or(cb.like(root.get("firstName"), search + "%"),
					cb.like(root.get("lastName"), search + "%"), cb.like(join.get("numMaticule"), search + "%"),
					cb.like(cb.concat(cb.concat(root.get("firstName"), " "), root.get("lastName")), search + "%")));
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
	private long count(String search, TypeClient typeClient,Status status)  {
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Long> cq = cb.createQuery(Long.class);

			Root<Client> root = cq.from(Client.class);
			Join<Client, CompteClient> fetch = root.join("compteClients", JoinType.LEFT);
			// Join<Client, CompteClient> join = (Join<Client, CompteClient>) fetch;
			cq.select(cb.countDistinct(root));
			List<Predicate> predicates = buldPredicates(search, typeClient,status, cb, root, fetch);
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
	public ClientDTO save(ClientDTO client) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClientDTO update(ClientDTO client) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<VenteDTO> findVenteByClientId(long clientId, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
