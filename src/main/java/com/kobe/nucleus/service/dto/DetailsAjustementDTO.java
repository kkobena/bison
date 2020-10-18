package com.kobe.nucleus.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.kobe.nucleus.domain.DetailsAjustement} entity.
 */
public class DetailsAjustementDTO implements Serializable {
    
    private Long id;

    @NotNull
    private Integer qteInit;

    @NotNull
    private Integer qteFinale;

    @NotNull
    private Integer qteAjuste;

    @NotNull
    private Instant createdAt;

    @NotNull
    private Instant updatedAt;


    private Long produitId;

    private String produitLibelle;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQteInit() {
        return qteInit;
    }

    public void setQteInit(Integer qteInit) {
        this.qteInit = qteInit;
    }

    public Integer getQteFinale() {
        return qteFinale;
    }

    public void setQteFinale(Integer qteFinale) {
        this.qteFinale = qteFinale;
    }

    public Integer getQteAjuste() {
        return qteAjuste;
    }

    public void setQteAjuste(Integer qteAjuste) {
        this.qteAjuste = qteAjuste;
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
        if (!(o instanceof DetailsAjustementDTO)) {
            return false;
        }

        return id != null && id.equals(((DetailsAjustementDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DetailsAjustementDTO{" +
            "id=" + getId() +
            ", qteInit=" + getQteInit() +
            ", qteFinale=" + getQteFinale() +
            ", qteAjuste=" + getQteAjuste() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", produitId=" + getProduitId() +
            ", produitLibelle='" + getProduitLibelle() + "'" +
            "}";
    }
}
