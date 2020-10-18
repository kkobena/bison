package com.kobe.nucleus.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A StockReport.
 */
@Entity
@Table(name = "stock_report")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class StockReport implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "qty", nullable = false)
    private Integer qty;

    @NotNull
    @Column(name = "mvt_date", nullable = false)
    private LocalDate mvtDate;

    @NotNull
    @Column(name = "prix_paf", nullable = false)
    private Integer prixPaf;

    @NotNull
    @Column(name = "prix_uni", nullable = false)
    private Integer prixUni;

    @NotNull
    @Column(name = "qty_ug", nullable = false)
    private Integer qtyUg;

    @NotNull
    @Column(name = "tva", nullable = false)
    private Integer tva;

    @ManyToOne
    @JsonIgnoreProperties(value = "stockReports", allowSetters = true)
    private StockProduit stockProduit;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQty() {
        return qty;
    }

    public StockReport qty(Integer qty) {
        this.qty = qty;
        return this;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public LocalDate getMvtDate() {
        return mvtDate;
    }

    public StockReport mvtDate(LocalDate mvtDate) {
        this.mvtDate = mvtDate;
        return this;
    }

    public void setMvtDate(LocalDate mvtDate) {
        this.mvtDate = mvtDate;
    }

    public Integer getPrixPaf() {
        return prixPaf;
    }

    public StockReport prixPaf(Integer prixPaf) {
        this.prixPaf = prixPaf;
        return this;
    }

    public void setPrixPaf(Integer prixPaf) {
        this.prixPaf = prixPaf;
    }

    public Integer getPrixUni() {
        return prixUni;
    }

    public StockReport prixUni(Integer prixUni) {
        this.prixUni = prixUni;
        return this;
    }

    public void setPrixUni(Integer prixUni) {
        this.prixUni = prixUni;
    }

    public Integer getQtyUg() {
        return qtyUg;
    }

    public StockReport qtyUg(Integer qtyUg) {
        this.qtyUg = qtyUg;
        return this;
    }

    public void setQtyUg(Integer qtyUg) {
        this.qtyUg = qtyUg;
    }

    public Integer getTva() {
        return tva;
    }

    public StockReport tva(Integer tva) {
        this.tva = tva;
        return this;
    }

    public void setTva(Integer tva) {
        this.tva = tva;
    }

    public StockProduit getStockProduit() {
        return stockProduit;
    }

    public StockReport stockProduit(StockProduit stockProduit) {
        this.stockProduit = stockProduit;
        return this;
    }

    public void setStockProduit(StockProduit stockProduit) {
        this.stockProduit = stockProduit;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StockReport)) {
            return false;
        }
        return id != null && id.equals(((StockReport) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "StockReport{" +
            "id=" + getId() +
            ", qty=" + getQty() +
            ", mvtDate='" + getMvtDate() + "'" +
            ", prixPaf=" + getPrixPaf() +
            ", prixUni=" + getPrixUni() +
            ", qtyUg=" + getQtyUg() +
            ", tva=" + getTva() +
            "}";
    }
}
