package com.kobe.nucleus.service;

import com.kobe.nucleus.domain.CommandeItem;
import com.kobe.nucleus.domain.DetailsInventaire;
import com.kobe.nucleus.domain.LignesVente;
import com.kobe.nucleus.service.dto.ProduitCriteria;
import com.kobe.nucleus.service.dto.ProduitDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.kobe.nucleus.domain.Produit}.
 */
public interface ProduitService {

	/**
	 * Save a produit.
	 *
	 * @param produitDTO the entity to save.
	 * @return the persisted entity.
	 */
	ProduitDTO save(ProduitDTO produitDTO);

	/**
	 * Get all the produits.
	 *
	 * @param pageable the pagination information.
	 * @return the list of entities.
	 */
	Page<ProduitDTO> findAll(Pageable pageable);

	/**
	 * Get the "id" produit.
	 *
	 * @param id the id of the entity.
	 * @return the entity.
	 */
	Optional<ProduitDTO> findOne(Long id);

	/**
	 * Delete the "id" produit.
	 *
	 * @param id the id of the entity.
	 */
	void delete(Long id);
	Page<ProduitDTO> findAll(ProduitCriteria produitCriteria, Pageable pageable);
	LignesVente lastSale(ProduitCriteria produitCriteria);
	DetailsInventaire lastInventory(ProduitCriteria produitCriteria);
	CommandeItem lastOrder(ProduitCriteria produitCriteria);
	ProduitDTO findOne(ProduitCriteria produitCriteria);
	List<ProduitDTO> findWithCriteria(ProduitCriteria produitCriteria) ;
	ProduitDTO update(ProduitDTO produitDTO);
}
