package com.kobe.nucleus.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import com.kobe.nucleus.domain.enumeration.Status;

/**
 * A DTO for the {@link com.kobe.nucleus.domain.FamilleProduit} entity.
 */
public class FamilleProduitDTO implements Serializable {

    private Long id;

    @NotNull
    private String code;

    @NotNull
    private String libelle;




    private Long categorieId;

    private String categorieLibelle;

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


    public Long getCategorieId() {
        return categorieId;
    }

    public void setCategorieId(Long categorieProduitId) {
        this.categorieId = categorieProduitId;
    }

    public String getCategorieLibelle() {
        return categorieLibelle;
    }

    public void setCategorieLibelle(String categorieProduitLibelle) {
        this.categorieLibelle = categorieProduitLibelle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FamilleProduitDTO)) {
            return false;
        }

        return id != null && id.equals(((FamilleProduitDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FamilleProduitDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", libelle='" + getLibelle() + "'" +
            ", categorieId=" + getCategorieId() +
            ", categorieLibelle='" + getCategorieLibelle() + "'" +
            "}";
    }
}
