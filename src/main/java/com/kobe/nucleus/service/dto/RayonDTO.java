package com.kobe.nucleus.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import com.kobe.nucleus.domain.enumeration.Status;
import com.kobe.nucleus.domain.enumeration.TypeEmplacement;

/**
 * A DTO for the {@link com.kobe.nucleus.domain.Rayon} entity.
 */
public class RayonDTO implements Serializable {
    
    private Long id;

    private Instant createdAt;

    private Instant updatedAt;

    @NotNull
    private Status status;

    @NotNull
    private String code;

    @NotNull
    private String libelle;

    @NotNull
    private TypeEmplacement typeRayon;


    private Long magasinId;

    private String magasinNomCourt;
    
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public TypeEmplacement getTypeRayon() {
        return typeRayon;
    }

    public void setTypeRayon(TypeEmplacement typeRayon) {
        this.typeRayon = typeRayon;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RayonDTO)) {
            return false;
        }

        return id != null && id.equals(((RayonDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RayonDTO{" +
            "id=" + getId() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", status='" + getStatus() + "'" +
            ", code='" + getCode() + "'" +
            ", libelle='" + getLibelle() + "'" +
            ", typeRayon='" + getTypeRayon() + "'" +
            ", magasinId=" + getMagasinId() +
            ", magasinNomCourt='" + getMagasinNomCourt() + "'" +
            "}";
    }
}
