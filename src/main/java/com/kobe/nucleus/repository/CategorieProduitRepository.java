package com.kobe.nucleus.repository;

import com.kobe.nucleus.domain.CategorieProduit;
import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CategorieProduit entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CategorieProduitRepository extends JpaRepository<CategorieProduit, Long>  {
	Optional<CategorieProduit> findOneByLibelle(String libelle);
}
