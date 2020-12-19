package com.kobe.nucleus.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.kobe.nucleus.domain.enumeration.Status;

/**
 * A DTO for the {@link com.kobe.nucleus.domain.CategorieAyantDroit} entity.
 */
public class CategorieAyantDroitDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String code;

    @NotNull
    private String libelle;

    @NotNull
    private Status status;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CategorieAyantDroitDTO categorieAyantDroitDTO = (CategorieAyantDroitDTO) o;
        if (categorieAyantDroitDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), categorieAyantDroitDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CategorieAyantDroitDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", libelle='" + getLibelle() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
