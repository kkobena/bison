package com.kobe.nucleus.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.kobe.nucleus.domain.DetailsInventaire} entity.
 */
public class DetailsInventaireDTO implements Serializable {
    
    private Long id;

    @NotNull
    private Integer qty;

    @NotNull
    private Integer qtyInit;

    @NotNull
    private Instant createdAt;

    @NotNull
    private Instant updatedAt;

    @NotNull
    private Boolean isUpdated;


    private Long inventaireId;
    
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

    public Integer getQtyInit() {
        return qtyInit;
    }

    public void setQtyInit(Integer qtyInit) {
        this.qtyInit = qtyInit;
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

    public Boolean isIsUpdated() {
        return isUpdated;
    }

    public void setIsUpdated(Boolean isUpdated) {
        this.isUpdated = isUpdated;
    }

    public Long getInventaireId() {
        return inventaireId;
    }

    public void setInventaireId(Long inventaireId) {
        this.inventaireId = inventaireId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DetailsInventaireDTO)) {
            return false;
        }

        return id != null && id.equals(((DetailsInventaireDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DetailsInventaireDTO{" +
            "id=" + getId() +
            ", qty=" + getQty() +
            ", qtyInit=" + getQtyInit() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", isUpdated='" + isIsUpdated() + "'" +
            ", inventaireId=" + getInventaireId() +
            "}";
    }
}
