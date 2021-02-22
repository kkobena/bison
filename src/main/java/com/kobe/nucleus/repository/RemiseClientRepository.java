package com.kobe.nucleus.repository;

import com.kobe.nucleus.domain.RemiseClient;
import com.kobe.nucleus.domain.RemiseProduit;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the RemiseProduit entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RemiseClientRepository extends JpaRepository<RemiseClient, Long> {
}
