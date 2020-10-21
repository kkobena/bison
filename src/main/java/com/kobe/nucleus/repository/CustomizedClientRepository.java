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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.kobe.nucleus.domain.Client;
import com.kobe.nucleus.domain.CompteClient;
import com.kobe.nucleus.domain.enumeration.TypeClient;
import com.kobe.nucleus.service.dto.ClientDTO;
import com.kobe.nucleus.web.rest.EtiquetteResource;

import liquibase.pro.packaged.iF;

public class CustomizedClientRepository implements CustomizedClientService {
	@PersistenceContext
	private EntityManager em;
	private final Logger log = LoggerFactory.getLogger(CustomizedClientRepository.class);

	@Override
	public Page<ClientDTO> findAll(String search, TypeClient typeClient, Pageable pageable) {
		long count = count(search, typeClient);
		try {
			if (count == 0)
				return new PageImpl<>(Collections.emptyList());
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Client> cq = cb.createQuery(Client.class);
			Root<Client> root = cq.from(Client.class);
			root.fetch("ayantDroits");
			Fetch<Client, CompteClient> fetch = root.fetch("compteClients", JoinType.LEFT);
			Join<Client, CompteClient> join = (Join<Client, CompteClient>) fetch;
			cq.select(root).distinct(true).orderBy(cb.asc(root.get("firstName")), cb.asc(root.get("lastName")));
			List<Predicate> predicates = buldPredicates(search, typeClient, cb, root, join);
			cq.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
			TypedQuery<Client> q = em.createQuery(cq);
			if (pageable != null) {
				q.setFirstResult((int) pageable.getOffset());
				q.setMaxResults(pageable.getPageSize());
			}
			return new PageImpl<ClientDTO>(
					q.getResultList().stream().map(e -> new ClientDTO(e)).collect(Collectors.toList()),pageable,count);
		} catch (Exception e) {
			log.debug("client count  : {}", e);
		}
		return null;
	}

	private List<Predicate> buldPredicates(String search, TypeClient typeClient, CriteriaBuilder cb, Root<Client> root,
			Join<Client, CompteClient> join) {
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
		return predicates;
	}

	private long count(String search, TypeClient typeClient) {
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Long> cq = cb.createQuery(Long.class);

			Root<Client> root = cq.from(Client.class);
			Fetch<Client, CompteClient> fetch = root.fetch("compteClients", JoinType.LEFT);
			Join<Client, CompteClient> join = (Join<Client, CompteClient>) fetch;
			cq.select(cb.countDistinct(root));
			List<Predicate> predicates = buldPredicates(search, typeClient, cb, root, join);
			cq.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
			Query q = em.createQuery(cq);

			return ((Long) q.getSingleResult()).intValue();
		} catch (Exception e) {
			log.debug("client count  : {}", e);
			return 0;
		}
	}
}
