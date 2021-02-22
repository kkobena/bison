package com.kobe.nucleus.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

import com.kobe.nucleus.domain.enumeration.Status;

/**
 * A LignesVente.
 */
@Entity
@Table(name = "lignes_vente")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class LignesVente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "montant")
    private Integer montant;

    @Column(name = "qty")
    private Integer qty;

    @Column(name = "qty_servi")
    private Integer qtyServi;

    @Column(name = "prix_uni")
    private Integer prixUni;

    @Column(name = "prix_paf")
    private Integer prixPaf;

    @Column(name = "montantremise")
    private Integer montantremise;

    @Column(name = "montant_tva")
    private Integer montantTva;

    @Column(name = "valeur_tva")
    private Integer valeurTva;

    @Column(name = "montant_net")
    private Integer montantNet;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @NotNull
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @NotNull
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @NotNull
    @Column(name = "checked", nullable = false)
    private Boolean checked;

    @ManyToOne
    @JsonIgnoreProperties(value = "lignesVentes", allowSetters = true)
    private Vente vente;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "lignesVentes", allowSetters = true)
    private StockProduit produitStock;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMontant() {
        return montant;
    }

    public LignesVente montant(Integer montant) {
        this.montant = montant;
        return this;
    }

    public void setMontant(Integer montant) {
        this.montant = montant;
    }

    public Integer getQty() {
        return qty;
    }

    public LignesVente qty(Integer qty) {
        this.qty = qty;
        return this;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Integer getQtyServi() {
        return qtyServi;
    }

    public LignesVente qtyServi(Integer qtyServi) {
        this.qtyServi = qtyServi;
        return this;
    }

    public void setQtyServi(Integer qtyServi) {
        this.qtyServi = qtyServi;
    }

    public Integer getPrixUni() {
        return prixUni;
    }

    public LignesVente prixUni(Integer prixUni) {
        this.prixUni = prixUni;
        return this;
    }

    public void setPrixUni(Integer prixUni) {
        this.prixUni = prixUni;
    }

    public Integer getPrixPaf() {
        return prixPaf;
    }

    public LignesVente prixPaf(Integer prixPaf) {
        this.prixPaf = prixPaf;
        return this;
    }

    public void setPrixPaf(Integer prixPaf) {
        this.prixPaf = prixPaf;
    }

    public Integer getMontantremise() {
        return montantremise;
    }

    public LignesVente montantremise(Integer montantremise) {
        this.montantremise = montantremise;
        return this;
    }

    public void setMontantremise(Integer montantremise) {
        this.montantremise = montantremise;
    }

    public Integer getMontantTva() {
        return montantTva;
    }

    public LignesVente montantTva(Integer montantTva) {
        this.montantTva = montantTva;
        return this;
    }

    public void setMontantTva(Integer montantTva) {
        this.montantTva = montantTva;
    }

    public Integer getValeurTva() {
        return valeurTva;
    }

    public LignesVente valeurTva(Integer valeurTva) {
        this.valeurTva = valeurTva;
        return this;
    }

    public void setValeurTva(Integer valeurTva) {
        this.valeurTva = valeurTva;
    }

    public Integer getMontantNet() {
        return montantNet;
    }

    public LignesVente montantNet(Integer montantNet) {
        this.montantNet = montantNet;
        return this;
    }

    public void setMontantNet(Integer montantNet) {
        this.montantNet = montantNet;
    }

    public Status getStatus() {
        return status;
    }

    public LignesVente status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public LignesVente createdAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public LignesVente updatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Boolean isChecked() {
        return checked;
    }

    public LignesVente checked(Boolean checked) {
        this.checked = checked;
        return this;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public Vente getVente() {
        return vente;
    }

    public LignesVente vente(Vente vente) {
        this.vente = vente;
        return this;
    }

    public void setVente(Vente vente) {
        this.vente = vente;
    }

    public StockProduit getProduitStock() {
        return produitStock;
    }

    public LignesVente produitStock(StockProduit stockProduit) {
        this.produitStock = stockProduit;
        return this;
    }

    public void setProduitStock(StockProduit stockProduit) {
        this.produitStock = stockProduit;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LignesVente)) {
            return false;
        }
        return id != null && id.equals(((LignesVente) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LignesVente{" +
            "id=" + getId() +
            ", montant=" + getMontant() +
            ", qty=" + getQty() +
            ", qtyServi=" + getQtyServi() +
            ", prixUni=" + getPrixUni() +
            ", prixPaf=" + getPrixPaf() +
            ", montantremise=" + getMontantremise() +
            ", montantTva=" + getMontantTva() +
            ", valeurTva=" + getValeurTva() +
            ", montantNet=" + getMontantNet() +
            ", status='" + getStatus() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", checked='" + isChecked() + "'" +
            "}";
    }
}
