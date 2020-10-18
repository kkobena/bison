package com.kobe.nucleus.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import com.kobe.nucleus.domain.enumeration.Status;
import com.kobe.nucleus.domain.enumeration.TypeClient;

/**
 * A DTO for the {@link com.kobe.nucleus.domain.Client} entity.
 */
public class ClientDTO implements Serializable {
    
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

    @NotNull
    private TypeClient typeClient;


    private Long compagnieId;

    private String compagnieLibelle;

    private Long remiseId;

    private String remiseValeur;
    
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

    public TypeClient getTypeClient() {
        return typeClient;
    }

    public void setTypeClient(TypeClient typeClient) {
        this.typeClient = typeClient;
    }

    public Long getCompagnieId() {
        return compagnieId;
    }

    public void setCompagnieId(Long compagnieId) {
        this.compagnieId = compagnieId;
    }

    public String getCompagnieLibelle() {
        return compagnieLibelle;
    }

    public void setCompagnieLibelle(String compagnieLibelle) {
        this.compagnieLibelle = compagnieLibelle;
    }

    public Long getRemiseId() {
        return remiseId;
    }

    public void setRemiseId(Long remiseId) {
        this.remiseId = remiseId;
    }

    public String getRemiseValeur() {
        return remiseValeur;
    }

    public void setRemiseValeur(String remiseValeur) {
        this.remiseValeur = remiseValeur;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ClientDTO)) {
            return false;
        }

        return id != null && id.equals(((ClientDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ClientDTO{" +
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
            ", typeClient='" + getTypeClient() + "'" +
            ", compagnieId=" + getCompagnieId() +
            ", compagnieLibelle='" + getCompagnieLibelle() + "'" +
            ", remiseId=" + getRemiseId() +
            ", remiseValeur='" + getRemiseValeur() + "'" +
            "}";
    }
}
