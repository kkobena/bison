package com.kobe.nucleus.repository;

import com.kobe.nucleus.domain.Tierspayant;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Tierspayant entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TierspayantRepository extends JpaRepository<Tierspayant, Long> ,JpaSpecificationExecutor<Tierspayant>{
}
