package com.kobe.nucleus.repository;

import com.kobe.nucleus.domain.RoleUtilisateur;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the RoleUtilisateur entity.
 */
@Repository
public interface RoleUtilisateurRepository extends JpaRepository<RoleUtilisateur, Long> {

    @Query(value = "select distinct roleUtilisateur from RoleUtilisateur roleUtilisateur left join fetch roleUtilisateur.menus",
        countQuery = "select count(distinct roleUtilisateur) from RoleUtilisateur roleUtilisateur")
    Page<RoleUtilisateur> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct roleUtilisateur from RoleUtilisateur roleUtilisateur left join fetch roleUtilisateur.menus")
    List<RoleUtilisateur> findAllWithEagerRelationships();

    @Query("select roleUtilisateur from RoleUtilisateur roleUtilisateur left join fetch roleUtilisateur.menus where roleUtilisateur.id =:id")
    Optional<RoleUtilisateur> findOneWithEagerRelationships(@Param("id") Long id);
}
