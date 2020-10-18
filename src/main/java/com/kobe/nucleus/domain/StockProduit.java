package com.kobe.nucleus.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.kobe.nucleus.domain.enumeration.Status;

/**
 * A StockProduit.
 */
@Entity
@Table(name = "stock_produit")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class StockProduit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "qty_stock", nullable = false)
    private Integer qtyStock;

    @NotNull
    @Column(name = "qty_virtual", nullable = false)
    private Integer qtyVirtual;

    @NotNull
    @Column(name = "qty_ug", nullable = false)
    private Integer qtyUG;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @NotNull
    @Column(name = "code_cip", nullable = false)
    private String codeCip;

    @Column(name = "version")
    private Integer version;

    @NotNull
    @Column(name = "prix_paf", nullable = false)
    private Integer prixPaf;

    @NotNull
    @Column(name = "prix_uni", nullable = false)
    private Integer prixUni;

    @OneToMany(mappedBy = "stockProduit")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<StockReport> stockReports = new HashSet<>();

    @OneToMany(mappedBy = "produit")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<MvtProduit> mvtProduits = new HashSet<>();

    @OneToMany(mappedBy = "produitStock")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<LignesVente> lignesVentes = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "stockProduits", allowSetters = true)
    private Magasin magasin;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "stockProduits", allowSetters = true)
    private Rayon rayon;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "stockProduits", allowSetters = true)
    private Produit produit;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQtyStock() {
        return qtyStock;
    }

    public StockProduit qtyStock(Integer qtyStock) {
        this.qtyStock = qtyStock;
        return this;
    }

    public void setQtyStock(Integer qtyStock) {
        this.qtyStock = qtyStock;
    }

    public Integer getQtyVirtual() {
        return qtyVirtual;
    }

    public StockProduit qtyVirtual(Integer qtyVirtual) {
        this.qtyVirtual = qtyVirtual;
        return this;
    }

    public void setQtyVirtual(Integer qtyVirtual) {
        this.qtyVirtual = qtyVirtual;
    }

    public Integer getQtyUG() {
        return qtyUG;
    }

    public StockProduit qtyUG(Integer qtyUG) {
        this.qtyUG = qtyUG;
        return this;
    }

    public void setQtyUG(Integer qtyUG) {
        this.qtyUG = qtyUG;
    }

    public Status getStatus() {
        return status;
    }

    public StockProduit status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getCodeCip() {
        return codeCip;
    }

    public StockProduit codeCip(String codeCip) {
        this.codeCip = codeCip;
        return this;
    }

    public void setCodeCip(String codeCip) {
        this.codeCip = codeCip;
    }

    public Integer getVersion() {
        return version;
    }

    public StockProduit version(Integer version) {
        this.version = version;
        return this;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getPrixPaf() {
        return prixPaf;
    }

    public StockProduit prixPaf(Integer prixPaf) {
        this.prixPaf = prixPaf;
        return this;
    }

    public void setPrixPaf(Integer prixPaf) {
        this.prixPaf = prixPaf;
    }

    public Integer getPrixUni() {
        return prixUni;
    }

    public StockProduit prixUni(Integer prixUni) {
        this.prixUni = prixUni;
        return this;
    }

    public void setPrixUni(Integer prixUni) {
        this.prixUni = prixUni;
    }

    public Set<StockReport> getStockReports() {
        return stockReports;
    }

    public StockProduit stockReports(Set<StockReport> stockReports) {
        this.stockReports = stockReports;
        return this;
    }

    public StockProduit addStockReport(StockReport stockReport) {
        this.stockReports.add(stockReport);
        stockReport.setStockProduit(this);
        return this;
    }

    public StockProduit removeStockReport(StockReport stockReport) {
        this.stockReports.remove(stockReport);
        stockReport.setStockProduit(null);
        return this;
    }

    public void setStockReports(Set<StockReport> stockReports) {
        this.stockReports = stockReports;
    }

    public Set<MvtProduit> getMvtProduits() {
        return mvtProduits;
    }

    public StockProduit mvtProduits(Set<MvtProduit> mvtProduits) {
        this.mvtProduits = mvtProduits;
        return this;
    }

    public StockProduit addMvtProduit(MvtProduit mvtProduit) {
        this.mvtProduits.add(mvtProduit);
        mvtProduit.setProduit(this);
        return this;
    }

    public StockProduit removeMvtProduit(MvtProduit mvtProduit) {
        this.mvtProduits.remove(mvtProduit);
        mvtProduit.setProduit(null);
        return this;
    }

    public void setMvtProduits(Set<MvtProduit> mvtProduits) {
        this.mvtProduits = mvtProduits;
    }

    public Set<LignesVente> getLignesVentes() {
        return lignesVentes;
    }

    public StockProduit lignesVentes(Set<LignesVente> lignesVentes) {
        this.lignesVentes = lignesVentes;
        return this;
    }

    public StockProduit addLignesVente(LignesVente lignesVente) {
        this.lignesVentes.add(lignesVente);
        lignesVente.setProduitStock(this);
        return this;
    }

    public StockProduit removeLignesVente(LignesVente lignesVente) {
        this.lignesVentes.remove(lignesVente);
        lignesVente.setProduitStock(null);
        return this;
    }

    public void setLignesVentes(Set<LignesVente> lignesVentes) {
        this.lignesVentes = lignesVentes;
    }

    public Magasin getMagasin() {
        return magasin;
    }

    public StockProduit magasin(Magasin magasin) {
        this.magasin = magasin;
        return this;
    }

    public void setMagasin(Magasin magasin) {
        this.magasin = magasin;
    }

    public Rayon getRayon() {
        return rayon;
    }

    public StockProduit rayon(Rayon rayon) {
        this.rayon = rayon;
        return this;
    }

    public void setRayon(Rayon rayon) {
        this.rayon = rayon;
    }

    public Produit getProduit() {
        return produit;
    }

    public StockProduit produit(Produit produit) {
        this.produit = produit;
        return this;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StockProduit)) {
            return false;
        }
        return id != null && id.equals(((StockProduit) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "StockProduit{" +
            "id=" + getId() +
            ", qtyStock=" + getQtyStock() +
            ", qtyVirtual=" + getQtyVirtual() +
            ", qtyUG=" + getQtyUG() +
            ", status='" + getStatus() + "'" +
            ", codeCip='" + getCodeCip() + "'" +
            ", version=" + getVersion() +
            ", prixPaf=" + getPrixPaf() +
            ", prixUni=" + getPrixUni() +
            "}";
    }
}
