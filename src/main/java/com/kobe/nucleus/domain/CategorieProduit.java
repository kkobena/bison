package com.kobe.nucleus.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;



/**
 * A CategorieProduit.
 */
@Entity
@Table(name = "categorie_produit")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CategorieProduit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @NotNull
    @Column(name = "libelle", nullable = false, unique = true)
    private String libelle;

   

    @OneToMany(mappedBy = "categorie")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<FamilleProduit> familleProduits = new HashSet<>();

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public CategorieProduit code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public CategorieProduit libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

   

    public Set<FamilleProduit> getFamilleProduits() {
        return familleProduits;
    }

    public CategorieProduit familleProduits(Set<FamilleProduit> familleProduits) {
        this.familleProduits = familleProduits;
        return this;
    }

    public CategorieProduit addFamilleProduit(FamilleProduit familleProduit) {
        this.familleProduits.add(familleProduit);
        familleProduit.setCategorie(this);
        return this;
    }

    public CategorieProduit removeFamilleProduit(FamilleProduit familleProduit) {
        this.familleProduits.remove(familleProduit);
        familleProduit.setCategorie(null);
        return this;
    }

    public void setFamilleProduits(Set<FamilleProduit> familleProduits) {
        this.familleProduits = familleProduits;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CategorieProduit)) {
            return false;
        }
        return id != null && id.equals(((CategorieProduit) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CategorieProduit{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", libelle='" + getLibelle() + "'" +
            "}";
    }
}
