package com.kobe.nucleus.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.kobe.nucleus.domain.PaiementItem} entity.
 */
public class PaiementItemDTO implements Serializable {
    
    private Long id;

    private String codeRef;

    private String pkey;

    @NotNull
    private Instant createdAt;

    @NotNull
    private Instant updatedAt;

    private Integer montantAttendu;

    private Integer montantRegl;

    private Integer montantRestant;


    private Long paiementId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeRef() {
        return codeRef;
    }

    public void setCodeRef(String codeRef) {
        this.codeRef = codeRef;
    }

    public String getPkey() {
        return pkey;
    }

    public void setPkey(String pkey) {
        this.pkey = pkey;
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

    public Integer getMontantAttendu() {
        return montantAttendu;
    }

    public void setMontantAttendu(Integer montantAttendu) {
        this.montantAttendu = montantAttendu;
    }

    public Integer getMontantRegl() {
        return montantRegl;
    }

    public void setMontantRegl(Integer montantRegl) {
        this.montantRegl = montantRegl;
    }

    public Integer getMontantRestant() {
        return montantRestant;
    }

    public void setMontantRestant(Integer montantRestant) {
        this.montantRestant = montantRestant;
    }

    public Long getPaiementId() {
        return paiementId;
    }

    public void setPaiementId(Long paiementId) {
        this.paiementId = paiementId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PaiementItemDTO)) {
            return false;
        }

        return id != null && id.equals(((PaiementItemDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PaiementItemDTO{" +
            "id=" + getId() +
            ", codeRef='" + getCodeRef() + "'" +
            ", pkey='" + getPkey() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", montantAttendu=" + getMontantAttendu() +
            ", montantRegl=" + getMontantRegl() +
            ", montantRestant=" + getMontantRestant() +
            ", paiementId=" + getPaiementId() +
            "}";
    }
}
