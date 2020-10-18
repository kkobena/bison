package com.kobe.nucleus.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import com.kobe.nucleus.domain.enumeration.StatutFacture;

/**
 * A DTO for the {@link com.kobe.nucleus.domain.FactureItem} entity.
 */
public class FactureItemDTO implements Serializable {
    
    private Long id;

    @NotNull
    private Instant createdAt;

    @NotNull
    private Instant updatedAt;

    private Integer montantRemise;

    private Integer montantPaye;

    @NotNull
    private StatutFacture statutFacture;

    private Integer montantRestant;

    private Integer montantNet;

    private Integer montantBrut;


    private Long factureId;

    private Long clientId;

    private Long ayantDroitId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getMontantRemise() {
        return montantRemise;
    }

    public void setMontantRemise(Integer montantRemise) {
        this.montantRemise = montantRemise;
    }

    public Integer getMontantPaye() {
        return montantPaye;
    }

    public void setMontantPaye(Integer montantPaye) {
        this.montantPaye = montantPaye;
    }

    public StatutFacture getStatutFacture() {
        return statutFacture;
    }

    public void setStatutFacture(StatutFacture statutFacture) {
        this.statutFacture = statutFacture;
    }

    public Integer getMontantRestant() {
        return montantRestant;
    }

    public void setMontantRestant(Integer montantRestant) {
        this.montantRestant = montantRestant;
    }

    public Integer getMontantNet() {
        return montantNet;
    }

    public void setMontantNet(Integer montantNet) {
        this.montantNet = montantNet;
    }

    public Integer getMontantBrut() {
        return montantBrut;
    }

    public void setMontantBrut(Integer montantBrut) {
        this.montantBrut = montantBrut;
    }

    public Long getFactureId() {
        return factureId;
    }

    public void setFactureId(Long factureId) {
        this.factureId = factureId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getAyantDroitId() {
        return ayantDroitId;
    }

    public void setAyantDroitId(Long ayantDroitId) {
        this.ayantDroitId = ayantDroitId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FactureItemDTO)) {
            return false;
        }

        return id != null && id.equals(((FactureItemDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FactureItemDTO{" +
            "id=" + getId() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", montantRemise=" + getMontantRemise() +
            ", montantPaye=" + getMontantPaye() +
            ", statutFacture='" + getStatutFacture() + "'" +
            ", montantRestant=" + getMontantRestant() +
            ", montantNet=" + getMontantNet() +
            ", montantBrut=" + getMontantBrut() +
            ", factureId=" + getFactureId() +
            ", clientId=" + getClientId() +
            ", ayantDroitId=" + getAyantDroitId() +
            "}";
    }
}
