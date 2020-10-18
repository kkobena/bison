package com.kobe.nucleus.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import com.kobe.nucleus.domain.enumeration.CatMvtCaisse;
import com.kobe.nucleus.domain.enumeration.Status;

/**
 * A DTO for the {@link com.kobe.nucleus.domain.TypeMvtCaisse} entity.
 */
public class TypeMvtCaisseDTO implements Serializable {

    private Long id;

    @NotNull
    private String libelle;

    @NotNull
    private String code;

    private CatMvtCaisse categorie;




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

    public CatMvtCaisse getCategorie() {
        return categorie;
    }

    public void setCategorie(CatMvtCaisse categorie) {
        this.categorie = categorie;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TypeMvtCaisseDTO)) {
            return false;
        }

        return id != null && id.equals(((TypeMvtCaisseDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TypeMvtCaisseDTO{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", code='" + getCode() + "'" +
            ", categorie='" + getCategorie() + "'" +

            "}";
    }
}
