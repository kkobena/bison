package com.kobe.nucleus.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.kobe.nucleus.domain.ModelFacture} entity.
 */
public class ModelFactureDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String libelle;

    @NotNull
    private String code;

    @NotNull
    private String fichier;

    
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFichier() {
        return fichier;
    }

    public void setFichier(String fichier) {
        this.fichier = fichier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ModelFactureDTO modelFactureDTO = (ModelFactureDTO) o;
        if (modelFactureDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), modelFactureDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ModelFactureDTO{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", code='" + getCode() + "'" +
            ", fichier='" + getFichier() + "'" +
            "}";
    }
}
