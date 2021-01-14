package com.kobe.nucleus.repository;

import com.kobe.nucleus.domain.Magasin;
import com.kobe.nucleus.domain.enumeration.TypeMagasin;

import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data repository for the Magasin entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MagasinRepository extends JpaRepository<Magasin, Long> {
	Optional<Magasin> findOneByTypeMagasin(TypeMagasin typeMagasin);
}
