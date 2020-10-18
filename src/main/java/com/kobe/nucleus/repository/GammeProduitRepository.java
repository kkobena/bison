package com.kobe.nucleus.repository;

import com.kobe.nucleus.domain.GammeProduit;

import com.kobe.nucleus.domain.Laboratoire;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the GammeProduit entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GammeProduitRepository extends JpaRepository<GammeProduit, Long>,JpaSpecificationExecutor<GammeProduit> {
}
