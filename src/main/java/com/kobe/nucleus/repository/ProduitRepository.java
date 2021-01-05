package com.kobe.nucleus.repository;

import com.kobe.nucleus.domain.Produit;

import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.stereotype.Repository;

/**
 * Spring Data repository for the Produit entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {
	@EntityGraph(attributePaths = {"stockProduits","fournisseurProduits"},type = EntityGraphType.FETCH)
	Optional<Produit> findById(Long id);
}
