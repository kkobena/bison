package com.kobe.nucleus.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.kobe.nucleus.domain.Decondition} entity.
 */
public class DeconditionDTO implements Serializable {
    
    private Long id;

    @NotNull
    private Integer qtyStock;

    @NotNull
    private Integer qtyInit;


    private Long magasinId;

    private String magasinNomCourt;

    private Long createdById;

    private String createdByFirstName;
    
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

    public Integer getQtyInit() {
        return qtyInit;
    }

    public void setQtyInit(Integer qtyInit) {
        this.qtyInit = qtyInit;
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

    public Long getCreatedById() {
        return createdById;
    }

    public void setCreatedById(Long utilisateurId) {
        this.createdById = utilisateurId;
    }

    public String getCreatedByFirstName() {
        return createdByFirstName;
    }

    public void setCreatedByFirstName(String utilisateurFirstName) {
        this.createdByFirstName = utilisateurFirstName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DeconditionDTO)) {
            return false;
        }

        return id != null && id.equals(((DeconditionDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DeconditionDTO{" +
            "id=" + getId() +
            ", qtyStock=" + getQtyStock() +
            ", qtyInit=" + getQtyInit() +
            ", magasinId=" + getMagasinId() +
            ", magasinNomCourt='" + getMagasinNomCourt() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByFirstName='" + getCreatedByFirstName() + "'" +
            "}";
    }
}
