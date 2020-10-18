package com.kobe.nucleus.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.kobe.nucleus.domain.StockReport} entity.
 */
public class StockReportDTO implements Serializable {
    
    private Long id;

    @NotNull
    private Integer qty;

    @NotNull
    private LocalDate mvtDate;

    @NotNull
    private Integer prixPaf;

    @NotNull
    private Integer prixUni;

    @NotNull
    private Integer qtyUg;

    @NotNull
    private Integer tva;


    private Long stockProduitId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public LocalDate getMvtDate() {
        return mvtDate;
    }

    public void setMvtDate(LocalDate mvtDate) {
        this.mvtDate = mvtDate;
    }

    public Integer getPrixPaf() {
        return prixPaf;
    }

    public void setPrixPaf(Integer prixPaf) {
        this.prixPaf = prixPaf;
    }

    public Integer getPrixUni() {
        return prixUni;
    }

    public void setPrixUni(Integer prixUni) {
        this.prixUni = prixUni;
    }

    public Integer getQtyUg() {
        return qtyUg;
    }

    public void setQtyUg(Integer qtyUg) {
        this.qtyUg = qtyUg;
    }

    public Integer getTva() {
        return tva;
    }

    public void setTva(Integer tva) {
        this.tva = tva;
    }

    public Long getStockProduitId() {
        return stockProduitId;
    }

    public void setStockProduitId(Long stockProduitId) {
        this.stockProduitId = stockProduitId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StockReportDTO)) {
            return false;
        }

        return id != null && id.equals(((StockReportDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "StockReportDTO{" +
            "id=" + getId() +
            ", qty=" + getQty() +
            ", mvtDate='" + getMvtDate() + "'" +
            ", prixPaf=" + getPrixPaf() +
            ", prixUni=" + getPrixUni() +
            ", qtyUg=" + getQtyUg() +
            ", tva=" + getTva() +
            ", stockProduitId=" + getStockProduitId() +
            "}";
    }
}
