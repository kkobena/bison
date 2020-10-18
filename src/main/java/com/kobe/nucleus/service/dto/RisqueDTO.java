package com.kobe.nucleus.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import com.kobe.nucleus.domain.enumeration.Status;

/**
 * A DTO for the {@link com.kobe.nucleus.domain.Risque} entity.
 */
public class RisqueDTO implements Serializable {
    
    private Long id;

    private String code;

    @NotNull
    private String libelle;

    @NotNull
    private Status status;


    private Long typerisqueId;

    private String typerisqueLibelle;
    
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

    public Long getTyperisqueId() {
        return typerisqueId;
    }

    public void setTyperisqueId(Long typeRisqueId) {
        this.typerisqueId = typeRisqueId;
    }

    public String getTyperisqueLibelle() {
        return typerisqueLibelle;
    }

    public void setTyperisqueLibelle(String typeRisqueLibelle) {
        this.typerisqueLibelle = typeRisqueLibelle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RisqueDTO)) {
            return false;
        }

        return id != null && id.equals(((RisqueDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RisqueDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", libelle='" + getLibelle() + "'" +
            ", status='" + getStatus() + "'" +
            ", typerisqueId=" + getTyperisqueId() +
            ", typerisqueLibelle='" + getTyperisqueLibelle() + "'" +
            "}";
    }
}
