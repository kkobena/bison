package com.kobe.nucleus.repository;

import com.kobe.nucleus.domain.Magasin;
import com.kobe.nucleus.domain.enumeration.TypeMagasin;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data repository for the Magasin entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MagasinRepository extends JpaRepository<Magasin, Long> {
	@EntityGraph(attributePaths = { "magasins", "utilisateurs" }, type = EntityGraph.EntityGraphType.FETCH)
	Optional<Magasin> findOneByTypeMagasin(TypeMagasin typeMagasin);

	@EntityGraph(attributePaths = { "magasins", "utilisateurs" }, type = EntityGraph.EntityGraphType.FETCH)
	Optional<Magasin> findOneById(Long id);

	@EntityGraph(attributePaths = { "magasins", "utilisateurs" }, type = EntityGraph.EntityGraphType.FETCH)
	List<Magasin> findAllByMagasinIsNull();

	@EntityGraph(attributePaths = { "magasins", "utilisateurs" }, type = EntityGraph.EntityGraphType.FETCH)
	List<Magasin> findByTypeMagasin(TypeMagasin typeMagasin);

	@EntityGraph(attributePaths = { "magasins", "utilisateurs" }, type = EntityGraph.EntityGraphType.FETCH)
	List<Magasin> findByTypeMagasinIn(List<TypeMagasin> typeMagasins);
}
