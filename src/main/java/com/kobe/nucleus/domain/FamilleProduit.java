package com.kobe.nucleus.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;



/**
 * A FamilleProduit.
 */
@Entity
@Table(name = "famille_produit")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class FamilleProduit implements Serializable {

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


    @OneToMany(mappedBy = "famille")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Produit> produits = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "familleProduits", allowSetters = true)
    private CategorieProduit categorie;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public FamilleProduit code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public FamilleProduit libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

   

    public Set<Produit> getProduits() {
        return produits;
    }

    public FamilleProduit produits(Set<Produit> produits) {
        this.produits = produits;
        return this;
    }

    public FamilleProduit addProduit(Produit produit) {
        this.produits.add(produit);
        produit.setFamille(this);
        return this;
    }

    public FamilleProduit removeProduit(Produit produit) {
        this.produits.remove(produit);
        produit.setFamille(null);
        return this;
    }

    public void setProduits(Set<Produit> produits) {
        this.produits = produits;
    }

    public CategorieProduit getCategorie() {
        return categorie;
    }

    public FamilleProduit categorie(CategorieProduit categorieProduit) {
        this.categorie = categorieProduit;
        return this;
    }

    public void setCategorie(CategorieProduit categorieProduit) {
        this.categorie = categorieProduit;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FamilleProduit)) {
            return false;
        }
        return id != null && id.equals(((FamilleProduit) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FamilleProduit{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", libelle='" + getLibelle() + "'" +
          
            "}";
    }
}
