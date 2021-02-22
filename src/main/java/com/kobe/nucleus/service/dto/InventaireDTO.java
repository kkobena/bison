package com.kobe.nucleus.service.dto;

import java.time.Instant;
import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import com.kobe.nucleus.domain.enumeration.Status;

/**
 * A DTO for the {@link com.kobe.nucleus.domain.Inventaire} entity.
 */
public class InventaireDTO implements Serializable {
    
    private Long id;

    private String libelle;

    @NotNull
    private Instant createdAt;

    @NotNull
    private Instant updatedAt;

    @NotNull
    private Status status;

    private Boolean programmable;

    private LocalDate dateDebut;

    private LocalDate dateFin;

    private LocalDate endDate;

    @NotNull
    private Instant beginnin;

    @NotNull
    private Instant ending;

    @NotNull
    private Double valeurAchatAvant;

    @NotNull
    private Double valeurAchatApres;

    @NotNull
    private Double valeurVenteAvant;

    @NotNull
    private Double valeurVenteApres;


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

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Boolean isProgrammable() {
        return programmable;
    }

    public void setProgrammable(Boolean programmable) {
        this.programmable = programmable;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Instant getBeginnin() {
        return beginnin;
    }

    public void setBeginnin(Instant beginnin) {
        this.beginnin = beginnin;
    }

    public Instant getEnding() {
        return ending;
    }

    public void setEnding(Instant ending) {
        this.ending = ending;
    }

    public Double getValeurAchatAvant() {
        return valeurAchatAvant;
    }

    public void setValeurAchatAvant(Double valeurAchatAvant) {
        this.valeurAchatAvant = valeurAchatAvant;
    }

    public Double getValeurAchatApres() {
        return valeurAchatApres;
    }

    public void setValeurAchatApres(Double valeurAchatApres) {
        this.valeurAchatApres = valeurAchatApres;
    }

    public Double getValeurVenteAvant() {
        return valeurVenteAvant;
    }

    public void setValeurVenteAvant(Double valeurVenteAvant) {
        this.valeurVenteAvant = valeurVenteAvant;
    }

    public Double getValeurVenteApres() {
        return valeurVenteApres;
    }

    public void setValeurVenteApres(Double valeurVenteApres) {
        this.valeurVenteApres = valeurVenteApres;
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
        if (!(o instanceof InventaireDTO)) {
            return false;
        }

        return id != null && id.equals(((InventaireDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InventaireDTO{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", status='" + getStatus() + "'" +
            ", programmable='" + isProgrammable() + "'" +
            ", dateDebut='" + getDateDebut() + "'" +
            ", dateFin='" + getDateFin() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", beginnin='" + getBeginnin() + "'" +
            ", ending='" + getEnding() + "'" +
            ", valeurAchatAvant=" + getValeurAchatAvant() +
            ", valeurAchatApres=" + getValeurAchatApres() +
            ", valeurVenteAvant=" + getValeurVenteAvant() +
            ", valeurVenteApres=" + getValeurVenteApres() +
            ", magasinId=" + getMagasinId() +
            ", magasinNomCourt='" + getMagasinNomCourt() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByFirstName='" + getCreatedByFirstName() + "'" +
            "}";
    }
}
