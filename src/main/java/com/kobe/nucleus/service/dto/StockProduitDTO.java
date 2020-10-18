package com.kobe.nucleus.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import com.kobe.nucleus.domain.enumeration.Status;

/**
 * A DTO for the {@link com.kobe.nucleus.domain.StockProduit} entity.
 */
public class StockProduitDTO implements Serializable {
    
    private Long id;

    @NotNull
    private Integer qtyStock;

    @NotNull
    private Integer qtyVirtual;

    @NotNull
    private Integer qtyUG;

    @NotNull
    private Status status;

    @NotNull
    private String codeCip;

    private Integer version;

    @NotNull
    private Integer prixPaf;

    @NotNull
    private Integer prixUni;


    private Long magasinId;

    private String magasinNomCourt;

    private Long rayonId;

    private String rayonLibelle;

    private Long produitId;

    private String produitLibelle;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQtyStock() {
        return qtyStock;
    }

    public void setQtyStock(Integer qtyStock) {
        this.qtyStock = qtyStock;
    }

    public Integer getQtyVirtual() {
        return qtyVirtual;
    }

    public void setQtyVirtual(Integer qtyVirtual) {
        this.qtyVirtual = qtyVirtual;
    }

    public Integer getQtyUG() {
        return qtyUG;
    }

    public void setQtyUG(Integer qtyUG) {
        this.qtyUG = qtyUG;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getCodeCip() {
        return codeCip;
    }

    public void setCodeCip(String codeCip) {
        this.codeCip = codeCip;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
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

    public Long getMagasinId() {
        return magasinId;
    }

    public void setMagasinId(Long magasinId) {
        this.magasinId = magasinId;
    }

    public String getMagasinNomCourt() {
        return magasinNomCourt;
    }

    public void setMagasinNomCourt(String magasinNomCourt) {
        this.magasinNomCourt = magasinNomCourt;
    }

    public Long getRayonId() {
        return rayonId;
    }

    public void setRayonId(Long rayonId) {
        this.rayonId = rayonId;
    }

    public String getRayonLibelle() {
        return rayonLibelle;
    }

    public void setRayonLibelle(String rayonLibelle) {
        this.rayonLibelle = rayonLibelle;
    }

    public Long getProduitId() {
        return produitId;
    }

    public void setProduitId(Long produitId) {
        this.produitId = produitId;
    }

    public String getProduitLibelle() {
        return produitLibelle;
    }

    public void setProduitLibelle(String produitLibelle) {
        this.produitLibelle = produitLibelle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StockProduitDTO)) {
            return false;
        }

        return id != null && id.equals(((StockProduitDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "StockProduitDTO{" +
            "id=" + getId() +
            ", qtyStock=" + getQtyStock() +
            ", qtyVirtual=" + getQtyVirtual() +
            ", qtyUG=" + getQtyUG() +
            ", status='" + getStatus() + "'" +
            ", codeCip='" + getCodeCip() + "'" +
            ", version=" + getVersion() +
            ", prixPaf=" + getPrixPaf() +
            ", prixUni=" + getPrixUni() +
            ", magasinId=" + getMagasinId() +
            ", magasinNomCourt='" + getMagasinNomCourt() + "'" +
            ", rayonId=" + getRayonId() +
            ", rayonLibelle='" + getRayonLibelle() + "'" +
            ", produitId=" + getProduitId() +
            ", produitLibelle='" + getProduitLibelle() + "'" +
            "}";
    }
}
