package com.kobe.nucleus.repository;

import com.kobe.nucleus.domain.Remise;

import com.kobe.nucleus.domain.enumeration.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Remise entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RemiseRepository extends JpaRepository<Remise, Long> {
    Page<Remise> findByStatus(Status status, Pageable page);
}
