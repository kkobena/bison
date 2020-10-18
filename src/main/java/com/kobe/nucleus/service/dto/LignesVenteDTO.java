package com.kobe.nucleus.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import com.kobe.nucleus.domain.enumeration.Status;

/**
 * A DTO for the {@link com.kobe.nucleus.domain.LignesVente} entity.
 */
public class LignesVenteDTO implements Serializable {
    
    private Long id;

    private Integer montant;

    private Integer qty;

    private Integer qtyServi;

    private Integer prixUni;

    private Integer prixPaf;

    private Integer montantremise;

    private Integer montantTva;

    private Integer valeurTva;

    private Integer montantNet;

    @NotNull
    private Status status;

    @NotNull
    private Instant createdAt;

    @NotNull
    private Instant updatedAt;

    @NotNull
    private Boolean checked;


    private Long venteId;

    private Long produitStockId;

    private String produitStockCodeCip;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMontant() {
        return montant;
    }

    public void setMontant(Integer montant) {
        this.montant = montant;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Integer getQtyServi() {
        return qtyServi;
    }

    public void setQtyServi(Integer qtyServi) {
        this.qtyServi = qtyServi;
    }

    public Integer getPrixUni() {
        return prixUni;
    }

    public void setPrixUni(Integer prixUni) {
        this.prixUni = prixUni;
    }

    public Integer getPrixPaf() {
        return prixPaf;
    }

    public void setPrixPaf(Integer prixPaf) {
        this.prixPaf = prixPaf;
    }

    public Integer getMontantremise() {
        return montantremise;
    }

    public void setMontantremise(Integer montantremise) {
        this.montantremise = montantremise;
    }

    public Integer getMontantTva() {
        return montantTva;
    }

    public void setMontantTva(Integer montantTva) {
        this.montantTva = montantTva;
    }

    public Integer getValeurTva() {
        return valeurTva;
    }

    public void setValeurTva(Integer valeurTva) {
        this.valeurTva = valeurTva;
    }

    public Integer getMontantNet() {
        return montantNet;
    }

    public void setMontantNet(Integer montantNet) {
        this.montantNet = montantNet;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Boolean isChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public Long getVenteId() {
        return venteId;
    }

    public void setVenteId(Long venteId) {
        this.venteId = venteId;
    }

    public Long getProduitStockId() {
        return produitStockId;
    }

    public void setProduitStockId(Long stockProduitId) {
        this.produitStockId = stockProduitId;
    }

    public String getProduitStockCodeCip() {
        return produitStockCodeCip;
    }

    public void setProduitStockCodeCip(String stockProduitCodeCip) {
        this.produitStockCodeCip = stockProduitCodeCip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LignesVenteDTO)) {
            return false;
        }

        return id != null && id.equals(((LignesVenteDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LignesVenteDTO{" +
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
            ", venteId=" + getVenteId() +
            ", produitStockId=" + getProduitStockId() +
            ", produitStockCodeCip='" + getProduitStockCodeCip() + "'" +
            "}";
    }
}
