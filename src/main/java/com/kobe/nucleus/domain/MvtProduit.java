package com.kobe.nucleus.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;

/**
 * A MvtProduit.
 */
@Entity
@Table(name = "mvt_produit")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MvtProduit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "mvt_date", nullable = false)
    private LocalDate mvtDate;

    @NotNull
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @NotNull
    @Column(name = "checked", nullable = false)
    private Boolean checked;

    @NotNull
    @Column(name = "qte_debut", nullable = false)
    private Integer qteDebut;

    @NotNull
    @Column(name = "qte_finale", nullable = false)
    private Integer qteFinale;

    @NotNull
    @Column(name = "prix_paf", nullable = false)
    private Integer prixPaf;

    @NotNull
    @Column(name = "prix_uni", nullable = false)
    private Integer prixUni;

    @NotNull
    @Column(name = "valeur_tva", nullable = false)
    private Integer valeurTva;

    @NotNull
    @Column(name = "montant_tva", nullable = false)
    private Integer montantTva;

    @NotNull
    @Column(name = "pkey", nullable = false, unique = true)
    private String pkey;

    @ManyToOne
    @JsonIgnoreProperties(value = "mvtProduits", allowSetters = true)
    private Magasin magasin;

    @ManyToOne
    @JsonIgnoreProperties(value = "mvtProduits", allowSetters = true)
    private Utilisateur createdBy;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "mvtProduits", allowSetters = true)
    private StockProduit produit;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getMvtDate() {
        return mvtDate;
    }

    public MvtProduit mvtDate(LocalDate mvtDate) {
        this.mvtDate = mvtDate;
        return this;
    }

    public void setMvtDate(LocalDate mvtDate) {
        this.mvtDate = mvtDate;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public MvtProduit createdAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean isChecked() {
        return checked;
    }

    public MvtProduit checked(Boolean checked) {
        this.checked = checked;
        return this;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public Integer getQteDebut() {
        return qteDebut;
    }

    public MvtProduit qteDebut(Integer qteDebut) {
        this.qteDebut = qteDebut;
        return this;
    }

    public void setQteDebut(Integer qteDebut) {
        this.qteDebut = qteDebut;
    }

    public Integer getQteFinale() {
        return qteFinale;
    }

    public MvtProduit qteFinale(Integer qteFinale) {
        this.qteFinale = qteFinale;
        return this;
    }

    public void setQteFinale(Integer qteFinale) {
        this.qteFinale = qteFinale;
    }

    public Integer getPrixPaf() {
        return prixPaf;
    }

    public MvtProduit prixPaf(Integer prixPaf) {
        this.prixPaf = prixPaf;
        return this;
    }

    public void setPrixPaf(Integer prixPaf) {
        this.prixPaf = prixPaf;
    }

    public Integer getPrixUni() {
        return prixUni;
    }

    public MvtProduit prixUni(Integer prixUni) {
        this.prixUni = prixUni;
        return this;
    }

    public void setPrixUni(Integer prixUni) {
        this.prixUni = prixUni;
    }

    public Integer getValeurTva() {
        return valeurTva;
    }

    public MvtProduit valeurTva(Integer valeurTva) {
        this.valeurTva = valeurTva;
        return this;
    }

    public void setValeurTva(Integer valeurTva) {
        this.valeurTva = valeurTva;
    }

    public Integer getMontantTva() {
        return montantTva;
    }

    public MvtProduit montantTva(Integer montantTva) {
        this.montantTva = montantTva;
        return this;
    }

    public void setMontantTva(Integer montantTva) {
        this.montantTva = montantTva;
    }

    public String getPkey() {
        return pkey;
    }

    public MvtProduit pkey(String pkey) {
        this.pkey = pkey;
        return this;
    }

    public void setPkey(String pkey) {
        this.pkey = pkey;
    }

    public Magasin getMagasin() {
        return magasin;
    }

    public MvtProduit magasin(Magasin magasin) {
        this.magasin = magasin;
        return this;
    }

    public void setMagasin(Magasin magasin) {
        this.magasin = magasin;
    }

    public Utilisateur getCreatedBy() {
        return createdBy;
    }

    public MvtProduit createdBy(Utilisateur utilisateur) {
        this.createdBy = utilisateur;
        return this;
    }

    public void setCreatedBy(Utilisateur utilisateur) {
        this.createdBy = utilisateur;
    }

    public StockProduit getProduit() {
        return produit;
    }

    public MvtProduit produit(StockProduit stockProduit) {
        this.produit = stockProduit;
        return this;
    }

    public void setProduit(StockProduit stockProduit) {
        this.produit = stockProduit;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MvtProduit)) {
            return false;
        }
        return id != null && id.equals(((MvtProduit) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MvtProduit{" +
            "id=" + getId() +
            ", mvtDate='" + getMvtDate() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", checked='" + isChecked() + "'" +
            ", qteDebut=" + getQteDebut() +
            ", qteFinale=" + getQteFinale() +
            ", prixPaf=" + getPrixPaf() +
            ", prixUni=" + getPrixUni() +
            ", valeurTva=" + getValeurTva() +
            ", montantTva=" + getMontantTva() +
            ", pkey='" + getPkey() + "'" +
            "}";
    }
}
