package com.kobe.nucleus.repository;

import com.kobe.nucleus.domain.CommandeItem;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CommandeItem entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CommandeItemRepository extends JpaRepository<CommandeItem, Long> {
}
