package com.kobe.nucleus.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * A StockProduit.
 */
@Entity
@Table(name = "stock_produit",
uniqueConstraints=
@UniqueConstraint(columnNames={"rayon_id", "produit_id"})
		)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
//@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
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
    @Column(name = "created_at", nullable = false)
    private Instant createdAt=Instant.now();

    @NotNull
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @OneToMany(mappedBy = "stockProduit")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<StockReport> stockReports = new HashSet<>();

    @OneToMany(mappedBy = "produit")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<MvtProduit> mvtProduits = new HashSet<>();

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "stockProduits", allowSetters = true)
    private Rayon rayon;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "stockProduits", allowSetters = true)
    private Produit produit;

    
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

    public Instant getCreatedAt() {
        return createdAt;
    }

    public StockProduit createdAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public StockProduit updatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
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
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            "}";
    }
}
