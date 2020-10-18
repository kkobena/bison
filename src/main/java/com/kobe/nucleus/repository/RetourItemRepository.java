package com.kobe.nucleus.repository;

import com.kobe.nucleus.domain.RetourItem;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the RetourItem entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RetourItemRepository extends JpaRepository<RetourItem, Long> {
}
