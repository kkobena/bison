package com.kobe.nucleus.repository;

import com.kobe.nucleus.domain.Client;

import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Client entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
	@EntityGraph(attributePaths = {"compteClients","ayantDroits"},
			type = EntityGraph.EntityGraphType.FETCH)
	Optional<Client> findById(Long id);
}
