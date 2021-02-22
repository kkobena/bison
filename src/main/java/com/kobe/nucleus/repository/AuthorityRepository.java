package com.kobe.nucleus.repository;

import com.kobe.nucleus.domain.Authority;
import com.kobe.nucleus.service.dto.AuthorityProjection;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the {@link Authority} entity.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {
	List<AuthorityProjection> findByNameNot(String name);
}
