package com.kobe.nucleus.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import com.kobe.nucleus.domain.enumeration.CategorieAssurance;
import com.kobe.nucleus.domain.enumeration.Status;

/**
 * A DTO for the {@link com.kobe.nucleus.domain.CompteClient} entity.
 */
public class CompteClientDTO implements Serializable {
    
    private Long id;

    private Instant createdAt;

    private Instant updatedAt;

    private Integer encours;

    private Integer version;

    private Integer plafondJournalier;

    private Integer plafondMensuel;

    private Integer consommation;

    private Integer consoJournaliere;

    private Integer taux;

    private Boolean principal;

    private String numMaticule;

    private Boolean enbale;

    private Boolean bIsAbsolute;

    private CategorieAssurance categorie;

    @NotNull
    private Status status;


    private Long clientId;

    private Long tierspayantId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getEncours() {
        return encours;
    }

    public void setEncours(Integer encours) {
        this.encours = encours;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getPlafondJournalier() {
        return plafondJournalier;
    }

    public void setPlafondJournalier(Integer plafondJournalier) {
        this.plafondJournalier = plafondJournalier;
    }

    public Integer getPlafondMensuel() {
        return plafondMensuel;
    }

    public void setPlafondMensuel(Integer plafondMensuel) {
        this.plafondMensuel = plafondMensuel;
    }

    public Integer getConsommation() {
        return consommation;
    }

    public void setConsommation(Integer consommation) {
        this.consommation = consommation;
    }

    public Integer getConsoJournaliere() {
        return consoJournaliere;
    }

    public void setConsoJournaliere(Integer consoJournaliere) {
        this.consoJournaliere = consoJournaliere;
    }

    public Integer getTaux() {
        return taux;
    }

    public void setTaux(Integer taux) {
        this.taux = taux;
    }

    public Boolean isPrincipal() {
        return principal;
    }

    public void setPrincipal(Boolean principal) {
        this.principal = principal;
    }

    public String getNumMaticule() {
        return numMaticule;
    }

    public void setNumMaticule(String numMaticule) {
        this.numMaticule = numMaticule;
    }

    public Boolean isEnbale() {
        return enbale;
    }

    public void setEnbale(Boolean enbale) {
        this.enbale = enbale;
    }

    public Boolean isbIsAbsolute() {
        return bIsAbsolute;
    }

    public void setbIsAbsolute(Boolean bIsAbsolute) {
        this.bIsAbsolute = bIsAbsolute;
    }

    public CategorieAssurance getCategorie() {
        return categorie;
    }

    public void setCategorie(CategorieAssurance categorie) {
        this.categorie = categorie;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getTierspayantId() {
        return tierspayantId;
    }

    public void setTierspayantId(Long tierspayantId) {
        this.tierspayantId = tierspayantId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CompteClientDTO)) {
            return false;
        }

        return id != null && id.equals(((CompteClientDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CompteClientDTO{" +
            "id=" + getId() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", encours=" + getEncours() +
            ", version=" + getVersion() +
            ", plafondJournalier=" + getPlafondJournalier() +
            ", plafondMensuel=" + getPlafondMensuel() +
            ", consommation=" + getConsommation() +
            ", consoJournaliere=" + getConsoJournaliere() +
            ", taux=" + getTaux() +
            ", principal='" + isPrincipal() + "'" +
            ", numMaticule='" + getNumMaticule() + "'" +
            ", enbale='" + isEnbale() + "'" +
            ", bIsAbsolute='" + isbIsAbsolute() + "'" +
            ", categorie='" + getCategorie() + "'" +
            ", status='" + getStatus() + "'" +
            ", clientId=" + getClientId() +
            ", tierspayantId=" + getTierspayantId() +
            "}";
    }
}
