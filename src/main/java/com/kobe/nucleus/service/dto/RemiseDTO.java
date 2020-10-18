package com.kobe.nucleus.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import com.kobe.nucleus.domain.enumeration.Status;

/**
 * A DTO for the {@link com.kobe.nucleus.domain.Remise} entity.
 */
public class RemiseDTO implements Serializable {

    private Long id;

    private String valeur;

    @NotNull
    private Float remiseValue;

    private Status status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    public Float getRemiseValue() {
        return remiseValue;
    }

    public void setRemiseValue(Float remiseValue) {
        this.remiseValue = remiseValue;
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
        if (!(o instanceof RemiseDTO)) {
            return false;
        }

        return id != null && id.equals(((RemiseDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RemiseDTO{" +
            "id=" + getId() +
            ", valeur='" + getValeur() + "'" +
            ", remiseValue=" + getRemiseValue() +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
