package com.kobe.nucleus.service.impl;

import com.kobe.nucleus.service.ProduitService;
import com.kobe.nucleus.domain.CommandeItem;
import com.kobe.nucleus.domain.DetailsInventaire;
import com.kobe.nucleus.domain.LignesVente;
import com.kobe.nucleus.domain.Produit;
import com.kobe.nucleus.repository.CustomizedProductService;
import com.kobe.nucleus.repository.ProduitRepository;
import com.kobe.nucleus.service.dto.ProduitCriteria;
import com.kobe.nucleus.service.dto.ProduitDTO;
import com.kobe.nucleus.service.mapper.ProduitMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Produit}.
 */
@Service
@Transactional
public class ProduitServiceImpl implements ProduitService {

	private final Logger log = LoggerFactory.getLogger(ProduitServiceImpl.class);

	private final ProduitRepository produitRepository;
	private final CustomizedProductService customizedProductService;

	private final ProduitMapper produitMapper;

	public ProduitServiceImpl(ProduitRepository produitRepository, ProduitMapper produitMapper,
			CustomizedProductService customizedProductService) {
		this.produitRepository = produitRepository;
		this.produitMapper = produitMapper;
		this.customizedProductService = customizedProductService;
	}

	/**
	 * Save a produit.
	 *
	 * @param produitDTO the entity to save.
	 * @return the persisted entity.
	 */
	@Override
	public ProduitDTO save(ProduitDTO produitDTO) {
		log.debug("Request to save Produit : {}", produitDTO);
		try {
			return customizedProductService.save(produitDTO);
		} catch (Exception e) {
			log.debug("Request to save Produit : {}", e);
			return null;
		}

	}

	/**
	 * Get all the produits.
	 *
	 * @param pageable the pagination information.
	 * @return the list of entities.
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<ProduitDTO> findAll(Pageable pageable) {
		log.debug("Request to get all Produits");
		return produitRepository.findAll(pageable).map(produitMapper::toDto);
	}

	/**
	 * Get one produit by id.
	 *
	 * @param id the id of the entity.
	 * @return the entity.
	 */
	@Override
	@Transactional(readOnly = true)
	public Optional<ProduitDTO> findOne(Long id) {
		log.debug("Request to get Produit : {}", id);
		return produitRepository.findById(id).map(ProduitDTO::new);
	}

	/**
	 * Delete the produit by id.
	 *
	 * @param id the id of the entity.
	 */
	@Override
	public void delete(Long id) {
		log.debug("Request to delete Produit : {}", id);

		produitRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<ProduitDTO> findAll(ProduitCriteria produitCriteria, Pageable pageable) {
		log.debug("Request to get all Produits {} ", produitCriteria);
		try {
			return customizedProductService.findAll(produitCriteria, pageable);
		} catch (Exception e) {
			log.debug("Request findAll  Produits : {}", e);
			return Page.empty();
		}

	}

	@Override
	@Transactional(readOnly = true)
	public ProduitDTO findOne(ProduitCriteria produitCriteria) {
		log.debug("Request to get Produit : {}", produitCriteria);
		Optional<ProduitDTO> produit = produitRepository.findById(produitCriteria.getId()).map(ProduitDTO::new);
		ProduitDTO dto = null;
		if (produit.isPresent()) {
			dto = produit.get();
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
		}

		return dto;
	}

	@Override
	@Transactional(readOnly = true)
	public LignesVente lastSale(ProduitCriteria produitCriteria) {
		return customizedProductService.lastSale(produitCriteria);
	}

	@Override
	@Transactional(readOnly = true)
	public DetailsInventaire lastInventory(ProduitCriteria produitCriteria) {
		return customizedProductService.lastInventory(produitCriteria);
	}

	@Override
	@Transactional(readOnly = true)
	public CommandeItem lastOrder(ProduitCriteria produitCriteria) {
		return customizedProductService.lastOrder(produitCriteria);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ProduitDTO> findWithCriteria(ProduitCriteria produitCriteria) {
		log.debug("Request to  findWithCriteria {} ", produitCriteria);
		try {
			return customizedProductService.findAll(produitCriteria);
		} catch (Exception e) {
			log.debug("Request findWithCriteria  Produits : {}", e);
			return Collections.emptyList();
		}

	}
	
	@Override
	public ProduitDTO update(ProduitDTO produitDTO) {
		log.debug("Request to update Produit : {}", produitDTO);
		try {
			return customizedProductService.update(produitDTO);
		} catch (Exception e) {
			log.debug("Request to update Produit : {}", e);
			return null;
		}

	}
	
}