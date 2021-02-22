package com.kobe.nucleus.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import com.kobe.nucleus.domain.enumeration.Status;

/**
 * A DTO for the {@link com.kobe.nucleus.domain.TypeEtiquette} entity.
 */
public class TypeEtiquetteDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String libelle;

 
    
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

    

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TypeEtiquetteDTO)) {
            return false;
        }

        return id != null && id.equals(((TypeEtiquetteDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TypeEtiquetteDTO{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
       
            "}";
    }
}
