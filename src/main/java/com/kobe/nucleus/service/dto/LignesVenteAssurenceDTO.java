package com.kobe.nucleus.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import com.kobe.nucleus.domain.enumeration.StatutFacture;

/**
 * A DTO for the {@link com.kobe.nucleus.domain.LignesVenteAssurence} entity.
 */
public class LignesVenteAssurenceDTO implements Serializable {
    
    private Long id;

    private Integer montant;

    private String refBon;

    private Integer taux;

    private Integer montantReste;

    @NotNull
    private StatutFacture statutFacture;

    @NotNull
    private Instant createdAt;

    @NotNull
    private Instant updatedAt;


    private Long venteId;

    private Long compteClientId;
    
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

    public String getRefBon() {
        return refBon;
    }

    public void setRefBon(String refBon) {
        this.refBon = refBon;
    }

    public Integer getTaux() {
        return taux;
    }

    public void setTaux(Integer taux) {
        this.taux = taux;
    }

    public Integer getMontantReste() {
        return montantReste;
    }

    public void setMontantReste(Integer montantReste) {
        this.montantReste = montantReste;
    }

    public StatutFacture getStatutFacture() {
        return statutFacture;
    }

    public void setStatutFacture(StatutFacture statutFacture) {
        this.statutFacture = statutFacture;
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

    public Long getVenteId() {
        return venteId;
    }

    public void setVenteId(Long venteId) {
        this.venteId = venteId;
    }

    public Long getCompteClientId() {
        return compteClientId;
    }

    public void setCompteClientId(Long compteClientId) {
        this.compteClientId = compteClientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LignesVenteAssurenceDTO)) {
            return false;
        }

        return id != null && id.equals(((LignesVenteAssurenceDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LignesVenteAssurenceDTO{" +
            "id=" + getId() +
            ", montant=" + getMontant() +
            ", refBon='" + getRefBon() + "'" +
            ", taux=" + getTaux() +
            ", montantReste=" + getMontantReste() +
            ", statutFacture='" + getStatutFacture() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", venteId=" + getVenteId() +
            ", compteClientId=" + getCompteClientId() +
            "}";
    }
}
