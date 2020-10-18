package com.kobe.nucleus.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.kobe.nucleus.domain.RetourItem} entity.
 */
public class RetourItemDTO implements Serializable {
    
    private Long id;

    private Integer qteConfirme;

    private Instant updatedAt;

    @NotNull
    private Instant createdAt;

    private Integer qteRetourne;


    private Long retourFournisseurId;

    private Long commandeItemId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQteConfirme() {
        return qteConfirme;
    }

    public void setQteConfirme(Integer qteConfirme) {
        this.qteConfirme = qteConfirme;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getQteRetourne() {
        return qteRetourne;
    }

    public void setQteRetourne(Integer qteRetourne) {
        this.qteRetourne = qteRetourne;
    }

    public Long getRetourFournisseurId() {
        return retourFournisseurId;
    }

    public void setRetourFournisseurId(Long retourFournisseurId) {
        this.retourFournisseurId = retourFournisseurId;
    }

    public Long getCommandeItemId() {
        return commandeItemId;
    }

    public void setCommandeItemId(Long commandeItemId) {
        this.commandeItemId = commandeItemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RetourItemDTO)) {
            return false;
        }

        return id != null && id.equals(((RetourItemDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RetourItemDTO{" +
            "id=" + getId() +
            ", qteConfirme=" + getQteConfirme() +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", qteRetourne=" + getQteRetourne() +
            ", retourFournisseurId=" + getRetourFournisseurId() +
            ", commandeItemId=" + getCommandeItemId() +
            "}";
    }
}
