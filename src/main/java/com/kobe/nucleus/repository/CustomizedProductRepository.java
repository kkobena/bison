package com.kobe.nucleus.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.SetJoin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.kobe.nucleus.domain.CommandeItem;
import com.kobe.nucleus.domain.DetailsInventaire;
import com.kobe.nucleus.domain.FamilleProduit;
import com.kobe.nucleus.domain.FormProduit;
import com.kobe.nucleus.domain.FournisseurProduit;
import com.kobe.nucleus.domain.GammeProduit;
import com.kobe.nucleus.domain.Laboratoire;
import com.kobe.nucleus.domain.LignesVente;
import com.kobe.nucleus.domain.Produit;
import com.kobe.nucleus.domain.StockProduit;
import com.kobe.nucleus.domain.Tva;
import com.kobe.nucleus.domain.enumeration.Status;
import com.kobe.nucleus.service.dto.FournisseurProduitDTO;
import com.kobe.nucleus.service.dto.ProduitCriteria;
import com.kobe.nucleus.service.dto.ProduitDTO;
import com.kobe.nucleus.service.dto.StockProduitDTO;

@Repository
@Transactional
public class CustomizedProductRepository implements CustomizedProductService {
	private final Logger LOG = LoggerFactory.getLogger(CustomizedProductRepository.class);
	@PersistenceContext
	private EntityManager em;
	@Autowired
	private StockProduitRepository stockProduitRepository;
	@Autowired
	private ParametreRepository parametreRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ProduitRepository produitRepository;
	

	@Transactional(readOnly = true)
	private List<Predicate> produitPredicate(CriteriaBuilder cb, Root<Produit> root, ProduitCriteria produitCriteria) {
		List<Predicate> predicates = new ArrayList<>();

		if (!StringUtils.isEmpty(produitCriteria.getSearch())) {
			String search = produitCriteria.getSearch().toUpperCase() + "%";

			SetJoin<Produit, FournisseurProduit> fp = root.joinSet("fournisseurProduits", JoinType.LEFT);
			predicates.add(cb.or(cb.like(cb.upper(root.get("libelle")), search),
					cb.like(cb.upper(root.get("codeCip")), search), cb.like(cb.upper(root.get("codeEan")), search),
					cb.like(cb.upper(fp.get("codeCip")), search)));

		}
		if (!StringUtils.isEmpty(produitCriteria.getStatus())) {
			predicates.add(cb.equal(root.get("status"), Status.valueOf(produitCriteria.getStatus())));
		}
		if (produitCriteria.getMagasinId() != null || produitCriteria.getRayonId() != null) {
			SetJoin<Produit, StockProduit> st = root.joinSet("stockProduits", JoinType.INNER);
			if (produitCriteria.getMagasinId() != null) {
				predicates.add(cb.equal(st.get("rayon").get("magasin").get("magasin").get("id"),
						produitCriteria.getMagasinId()));
			}
			if (produitCriteria.getRayonId() != null) {
				predicates.add(cb.equal(st.get("rayon").get("id"), produitCriteria.getRayonId()));
			}
		}

		if (produitCriteria.getFamilleId() != null) {
			Join<Produit, FamilleProduit> familleJoin = root.join("famille", JoinType.INNER);
			predicates.add(cb.equal(familleJoin.get("id"), produitCriteria.getFamilleId()));
		}
		if (produitCriteria.getLaboratoireId() != null) {
			Join<Produit, Laboratoire> laboratoireIdJoin = root.join("laboratoire", JoinType.INNER);
			predicates.add(cb.equal(laboratoireIdJoin.get("id"), produitCriteria.getLaboratoireId()));
		}
		if (produitCriteria.getGammeId() != null) {
			Join<Produit, GammeProduit> gammeJoin = root.join("gamme", JoinType.INNER);
			predicates.add(cb.equal(gammeJoin.get("id"), produitCriteria.getGammeId()));
		}
		if (produitCriteria.getFormeId() != null) {
			Join<Produit, FormProduit> formeJoin = root.join("forme", JoinType.INNER);
			predicates.add(cb.equal(formeJoin.get("id"), produitCriteria.getFormeId()));
		}
		if (produitCriteria.getTvaId() != null) {
			Join<Produit, Tva> tvaJoin = root.join("tva", JoinType.INNER);
			predicates.add(cb.equal(tvaJoin.get("id"), produitCriteria.getTvaId()));
		}
		if (produitCriteria.getDeconditionne() != null) {
			if (produitCriteria.getDeconditionne()) {
				predicates.add(cb.isNotNull(root.get("parent")));
			} else {
				predicates.add(cb.isNull(root.get("parent")));
			}

		}
		if (produitCriteria.getDeconditionnable() != null) {
			if (produitCriteria.getDeconditionnable()) {
				predicates.add(cb.isTrue(root.get("deconditionnable")));
			} else {
				predicates.add(cb.isFalse(root.get("deconditionnable")));
			}
		}
		if (produitCriteria.getDateperemption() != null) {
			if (produitCriteria.getDateperemption()) {
				predicates.add(cb.isTrue(root.get("dateperemption")));
			} else {
				predicates.add(cb.isFalse(root.get("dateperemption")));
			}
		}
		return predicates;
	}

	@Transactional(readOnly = true)
	private long findAllCount(ProduitCriteria produitCriteria) throws Exception {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<Produit> root = cq.from(Produit.class);
		cq.select(cb.countDistinct(root));
		List<Predicate> predicates = produitPredicate(cb, root, produitCriteria);
		cq.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
		TypedQuery<Long> q = em.createQuery(cq);
		Long v = q.getSingleResult();
		return v != null ? v : 0;

	}

	@Override
	@Transactional(readOnly = true)
	public List<ProduitDTO> findAll(ProduitCriteria produitCriteria) throws Exception {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Produit> cq = cb.createQuery(Produit.class);
		Root<Produit> root = cq.from(Produit.class);
		root.fetch("stockProduits",JoinType.LEFT);
		cq.select(root).distinct(true).orderBy(cb.asc(root.get("libelle")));
		List<Predicate> predicates = rechercheProduitPredicate(cb, root, produitCriteria);
		cq.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
		TypedQuery<Produit> q = em.createQuery(cq);
		return q.getResultList().stream().map(ProduitDTO::new).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	private List<Predicate> rechercheProduitPredicate(CriteriaBuilder cb, Root<Produit> root,
			ProduitCriteria produitCriteria) {
		List<Predicate> predicates = new ArrayList<>();

		if (!StringUtils.isEmpty(produitCriteria.getSearch())) {
			String search = produitCriteria.getSearch().toUpperCase() + "%";
			SetJoin<Produit, FournisseurProduit> fp = root.joinSet("fournisseurProduits", JoinType.LEFT);
			predicates.add(cb.or(cb.like(cb.upper(root.get("libelle")), search), cb.like(root.get("codeCip"), search),
					cb.like(root.get("codeEan"), search), cb.like(fp.get("codeCip"), search)));
		}
		if (!StringUtils.isEmpty(produitCriteria.getStatus())) {
			predicates.add(cb.equal(root.get("status"), Status.valueOf(produitCriteria.getStatus())));
		}
		if (produitCriteria.getMagasinId() != null || produitCriteria.getRayonId() != null) {
			SetJoin<Produit, StockProduit> st = root.joinSet("stockProduits", JoinType.INNER);
			if (produitCriteria.getMagasinId() != null) {
				predicates.add(cb.equal(st.get("rayon").get("magasin").get("magasin").get("id"),
						produitCriteria.getMagasinId()));
			}

		}

		return predicates;
	}

	@Override
	@Transactional(readOnly = true)
	public Page<ProduitDTO> findAll(ProduitCriteria produitCriteria, Pageable pageable) throws Exception {
		long total = findAllCount(produitCriteria);
		List<ProduitDTO> list = new ArrayList<>();
		if (total > 0) {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Produit> cq = cb.createQuery(Produit.class);
			Root<Produit> root = cq.from(Produit.class);
			cq.select(root).distinct(true).orderBy(cb.asc(root.get("libelle")));
			List<Predicate> predicates = produitPredicate(cb, root, produitCriteria);
			cq.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
			TypedQuery<Produit> q = em.createQuery(cq);
			q.setFirstResult(pageable.getPageNumber());
			q.setMaxResults(pageable.getPageSize());
			q.getResultList().forEach(p -> {
				ProduitDTO dto = new ProduitDTO(p);
				LignesVente lignesVente = lastSale(produitCriteria);
				if (lignesVente != null) {
					dto.setLastDateOfSale(lignesVente.getUpdatedAt());
				}
				DetailsInventaire detailsInventaire = lastInventory(produitCriteria);
				if (detailsInventaire != null) {
					dto.setLastInventoryDate(detailsInventaire.getUpdatedAt());
				}
				CommandeItem commandeItem = lastOrder(produitCriteria);
				if (commandeItem != null) {
					dto.setLastOrderDate(commandeItem.getUpdatedAt());
				}
				list.add(dto);
			});

		}
		return new PageImpl<ProduitDTO>(list, pageable, total);

	}

	@Override
	public ProduitDTO save(ProduitDTO dto) throws Exception {
		final Produit produit = buildProduitFromProduitDTO(dto);
		LOG.info("getStockProduit()>>>>>>>>>>>>>>>>>>>  => {}", dto.getStockProduit());
		if (dto.getStockProduit() != null) {
			produit.addStockProduit(buildStockProduitFromStockProduitDTO(dto.getStockProduit()));
		}

		produit.addFournisseurProduit(buildFournisseurProduitFromFournisseurProduitDTO(dto.getFournisseurProduit()));
		dto.getFournisseurProduits().stream().forEach(fournisseurProduit -> {
			produit.addFournisseurProduit(buildFournisseurProduitFromFournisseurProduitDTO(fournisseurProduit));
		});

		dto.getStockProduits().stream().forEach(stockProduit -> {
			produit.addStockProduit(buildStockProduitFromStockProduitDTO(stockProduit));
		});
		Produit produit_=produitRepository.findById( produitRepository.saveAndFlush(produit).getId()).get();
		em.refresh(produit_);
		return new ProduitDTO(produit_);

	}

	@Override
	public ProduitDTO update(ProduitDTO dto) throws Exception {

		final Produit produit = buildProduitFromProduitDTO(dto, em.find(Produit.class, dto.getId()));
		if (dto.getStockProduit() != null) {
			stockProduitRepository.findById(dto.getStockProduit().getId()).ifPresent(s -> {
				s.setRayon(rayonFromId(dto.getStockProduit().getRayonId()));
				produit.addStockProduit(s);
			});
		}

		produit.addStockProduit(buildStockProduitFromStockProduitDTO(dto.getStockProduit()));
		produit.addFournisseurProduit(buildFournisseurProduitFromFournisseurProduitDTO(dto.getFournisseurProduit()));
		dto.getFournisseurProduits().stream().forEach(fournisseurProduit -> {
			if (fournisseurProduit.getId() != null) {
				FournisseurProduit fournProduit = em.find(FournisseurProduit.class, fournisseurProduit.getId());
				produit.addFournisseurProduit(
						buildFournisseurProduitFromFournisseurProduitDTO(fournisseurProduit, fournProduit));
			}

		});
		return new ProduitDTO(em.merge(produit));

	}

	@Override
	public void save(StockProduitDTO dto) throws Exception {
		StockProduit stockProduit = buildStockProduitFromStockProduitDTO(dto);
		Produit produit = em.find(Produit.class, dto.getProduitId());
		stockProduit.setProduit(produit);
		em.merge(stockProduit);
		em.refresh(produit);

	}

	@Override
	public void update(StockProduitDTO dto) throws Exception {
		StockProduit stockProduit = buildStockProduitFromStockProduitDTO(dto, em.find(StockProduit.class, dto.getId()));
		em.merge(stockProduit);

	}

	@Override
	public void save(FournisseurProduitDTO dto) throws Exception {
		FournisseurProduit fournisseurProduit = buildFournisseurProduitFromFournisseurProduitDTO(dto);
		Produit produit = em.find(Produit.class, dto.getProduitId());
		fournisseurProduit.setProduit(produit);
		em.merge(fournisseurProduit);
		em.refresh(produit);

	}

	@Override
	public void update(FournisseurProduitDTO dto) throws Exception {
		FournisseurProduit fournisseurProduit = buildFournisseurProduitFromFournisseurProduitDTO(dto,
				em.find(FournisseurProduit.class, dto.getId()));
		Produit produit = em.find(Produit.class, dto.getProduitId());
		fournisseurProduit.setProduit(produit);
		em.merge(fournisseurProduit);
		em.refresh(produit);
	}

	@Override
	public void removeFournisseurProduit(Long id) throws Exception {
		FournisseurProduit fournisseurProduit = em.find(FournisseurProduit.class, id);
		Produit produit = fournisseurProduit.getProduit();
		produit.removeFournisseurProduit(fournisseurProduit);
		em.remove(fournisseurProduit);

	}

	@Override
	@Transactional(readOnly = true)
	public LignesVente lastSale(ProduitCriteria produitCriteria) {
		try {
			TypedQuery<LignesVente> q = em.createQuery(
					"SELECT o FROM LignesVente o WHERE o.vente.status=?1 AND o.produitStock.produit.id=?2 "
							+ " AND (o.produitStock.rayon.magasin.id=?3 OR o.produitStock.rayon.magasin.magasin.id=?3) ORDER BY  o.vente.updatedAt DESC",
					LignesVente.class);
			q.setMaxResults(1);
			q.setParameter(1, Status.CLOSED);
			q.setParameter(2, produitCriteria.getId());
			q.setParameter(3, produitCriteria.getMagasinId());
			return q.getSingleResult();
		} catch (Exception e) {
			LOG.debug("lastSale=====>>>> {}", e);
		}
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public DetailsInventaire lastInventory(ProduitCriteria produitCriteria) {
		try {
			TypedQuery<DetailsInventaire> q = em.createQuery(
					"SELECT o FROM DetailsInventaire o WHERE o.inventaire.status=?1 AND o.stockProduit.produit.id=?2 "
							+ " AND (o.stockProduit.rayon.magasin.id=?3 OR o.stockProduit.rayon.magasin.magasin.id=?3) ORDER BY  o.inventaire.updatedAt DESC",
					DetailsInventaire.class);
			q.setMaxResults(1);
			q.setParameter(1, Status.CLOSED);
			q.setParameter(2, produitCriteria.getId());
			q.setParameter(3, produitCriteria.getMagasinId());
			return q.getSingleResult();
		} catch (Exception e) {
			LOG.debug("lastInventory =====>>>> {}", e);
		}
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public CommandeItem lastOrder(ProduitCriteria produitCriteria) {
		try {
			TypedQuery<CommandeItem> q = em
					.createQuery(
							"SELECT o FROM CommandeItem o WHERE o.commande.status=?1 AND o.produit.id=?2 "
									+ " AND o.commande.magasin.id=?3 ORDER BY  o.commande.updatedAt DESC",
							CommandeItem.class);
			q.setMaxResults(1);
			q.setParameter(1, Status.CLOSED);
			q.setParameter(2, produitCriteria.getId());
			q.setParameter(3, produitCriteria.getMagasinId());
			return q.getSingleResult();
		} catch (Exception e) {
			LOG.debug("lastOrder =====>>>> {}", e);
		}
		return null;
	}

}
