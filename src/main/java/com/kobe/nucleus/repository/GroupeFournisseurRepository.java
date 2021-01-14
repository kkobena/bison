package com.kobe.nucleus.repository;

import com.kobe.nucleus.domain.GroupeFournisseur;
import com.kobe.nucleus.domain.Laboratoire;

import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the GroupeFournisseur entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GroupeFournisseurRepository extends JpaRepository<GroupeFournisseur, Long>,JpaSpecificationExecutor<GroupeFournisseur>  {
	Optional<GroupeFournisseur> findOneByLibelle(String libelle);
}
