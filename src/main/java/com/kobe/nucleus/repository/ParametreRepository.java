package com.kobe.nucleus.repository;

import com.kobe.nucleus.domain.Parametre;

import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Parametre entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ParametreRepository extends JpaRepository<Parametre, Long> {
	Optional<Parametre> findOneByKey(String key);
}
