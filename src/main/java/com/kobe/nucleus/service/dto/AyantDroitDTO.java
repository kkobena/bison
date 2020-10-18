package com.kobe.nucleus.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import com.kobe.nucleus.domain.enumeration.Status;

/**
 * A DTO for the {@link com.kobe.nucleus.domain.AyantDroit} entity.
 */
public class AyantDroitDTO implements Serializable {
    
    private Long id;

    @NotNull
    private LocalDate createdAt;

    @NotNull
    private LocalDate updatedAt;

    @NotNull
    private Status status;

    private String num;

    @NotNull
    private String firstName;

    private String lastName;

    private String sexe;

    @NotNull
    private String codeInterne;

    private LocalDate datNaiss;


    private Long assureId;

    private String assureFirstName;

    private Long categorieId;

    private String categorieLibelle;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getCodeInterne() {
        return codeInterne;
    }

    public void setCodeInterne(String codeInterne) {
        this.codeInterne = codeInterne;
    }

    public LocalDate getDatNaiss() {
        return datNaiss;
    }

    public void setDatNaiss(LocalDate datNaiss) {
        this.datNaiss = datNaiss;
    }

    public Long getAssureId() {
        return assureId;
    }

    public void setAssureId(Long clientId) {
        this.assureId = clientId;
    }

    public String getAssureFirstName() {
        return assureFirstName;
    }

    public void setAssureFirstName(String clientFirstName) {
        this.assureFirstName = clientFirstName;
    }

    public Long getCategorieId() {
        return categorieId;
    }

    public void setCategorieId(Long categorieAyantDroitId) {
        this.categorieId = categorieAyantDroitId;
    }

    public String getCategorieLibelle() {
        return categorieLibelle;
    }

    public void setCategorieLibelle(String categorieAyantDroitLibelle) {
        this.categorieLibelle = categorieAyantDroitLibelle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AyantDroitDTO)) {
            return false;
        }

        return id != null && id.equals(((AyantDroitDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AyantDroitDTO{" +
            "id=" + getId() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", status='" + getStatus() + "'" +
            ", num='" + getNum() + "'" +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", sexe='" + getSexe() + "'" +
            ", codeInterne='" + getCodeInterne() + "'" +
            ", datNaiss='" + getDatNaiss() + "'" +
            ", assureId=" + getAssureId() +
            ", assureFirstName='" + getAssureFirstName() + "'" +
            ", categorieId=" + getCategorieId() +
            ", categorieLibelle='" + getCategorieLibelle() + "'" +
            "}";
    }
}
