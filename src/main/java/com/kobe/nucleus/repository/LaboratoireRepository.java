package com.kobe.nucleus.repository;

import com.kobe.nucleus.domain.Laboratoire;

import com.kobe.nucleus.domain.Remise;
import com.kobe.nucleus.domain.enumeration.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Laboratoire entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LaboratoireRepository extends JpaRepository<Laboratoire, Long>,JpaSpecificationExecutor<Laboratoire> {

}
