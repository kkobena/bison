package com.kobe.nucleus.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import com.kobe.nucleus.domain.enumeration.Status;

/**
 * A DTO for the {@link com.kobe.nucleus.domain.ModePaiement} entity.
 */
public class ModePaiementDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String libelle;

    @NotNull
    private Status status;

    @NotNull
    @Size(max = 20)
    private String code;

    
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ModePaiementDTO)) {
            return false;
        }

        return id != null && id.equals(((ModePaiementDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ModePaiementDTO{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", status='" + getStatus() + "'" +
            ", code='" + getCode() + "'" +
            "}";
    }
}
