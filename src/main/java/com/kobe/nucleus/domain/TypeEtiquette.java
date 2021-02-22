package com.kobe.nucleus.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.kobe.nucleus.domain.enumeration.Status;

/**
 * A TypeEtiquette.
 */
@Entity
@Table(name = "type_etiquette")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TypeEtiquette implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "libelle", nullable = false, unique = true)
    private String libelle;

   

    @OneToMany(mappedBy = "typeEtyquette")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Produit> produits = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public TypeEtiquette libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

  

    public Set<Produit> getProduits() {
        return produits;
    }

    public TypeEtiquette produits(Set<Produit> produits) {
        this.produits = produits;
        return this;
    }

    public TypeEtiquette addProduit(Produit produit) {
        this.produits.add(produit);
        produit.setTypeEtyquette(this);
        return this;
    }

    public TypeEtiquette removeProduit(Produit produit) {
        this.produits.remove(produit);
        produit.setTypeEtyquette(null);
        return this;
    }

    public void setProduits(Set<Produit> produits) {
        this.produits = produits;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TypeEtiquette)) {
            return false;
        }
        return id != null && id.equals(((TypeEtiquette) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TypeEtiquette{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
          
            "}";
    }
}
