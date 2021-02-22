package com.kobe.nucleus.repository;

import com.kobe.nucleus.domain.Rayon;
import com.kobe.nucleus.domain.User;
import com.kobe.nucleus.domain.enumeration.Status;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data repository for the Rayon entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RayonRepository extends JpaRepository<Rayon, Long> {
	Page<Rayon> findByMagasinIdAndStatusAndLibelleContainingOrCodeContainingAllIgnoreCaseOrderByLibelleAsc(
			Long magasinId, Status status, String libelle, String code, Pageable pageable);
}
