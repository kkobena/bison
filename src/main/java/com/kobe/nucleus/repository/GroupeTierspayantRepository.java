package com.kobe.nucleus.repository;

import com.kobe.nucleus.domain.GroupeTierspayant;

import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the GroupeTierspayant entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GroupeTierspayantRepository extends JpaRepository<GroupeTierspayant, Long> ,JpaSpecificationExecutor<GroupeTierspayant>{
 Optional<GroupeTierspayant> findOneByLibelle(String libelle);
}
