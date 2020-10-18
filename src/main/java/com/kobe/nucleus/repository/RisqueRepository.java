package com.kobe.nucleus.repository;

import com.kobe.nucleus.domain.Risque;

import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data repository for the Risque entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RisqueRepository extends JpaRepository<Risque, Long> {

	Optional<Risque> findOneByCode(String code);
}
