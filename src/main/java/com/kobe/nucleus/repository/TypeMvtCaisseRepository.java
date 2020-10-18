package com.kobe.nucleus.repository;

import com.kobe.nucleus.domain.TypeMvtCaisse;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TypeMvtCaisse entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TypeMvtCaisseRepository extends JpaRepository<TypeMvtCaisse, Long> {
}
