package com.kobe.nucleus.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

import com.kobe.nucleus.domain.CompteClient;
import com.kobe.nucleus.domain.Tierspayant;
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

    private String numMaticule;

    private Boolean enbale;

    private CategorieAssurance categorie;

    @NotNull
    private Status status;

    @NotNull
    private Boolean absolute;


    private Long clientId;
    private String libelleTiersPayantF;
    private Long tierspayantId;
    public String getLibelleTiersPayantF() {
		return libelleTiersPayantF;
	}

	public void setLibelleTiersPayantF(String libelleTiersPayantF) {
		this.libelleTiersPayantF = libelleTiersPayantF;
	}
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

    public Boolean isAbsolute() {
        return absolute;
    }

    public void setAbsolute(Boolean absolute) {
        this.absolute = absolute;
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
    public CompteClientDTO() {
	}

	public CompteClientDTO(CompteClient cmp) {
		super();
		this.id = cmp.getId();
		this.createdAt = cmp.getCreatedAt();
		this.updatedAt = cmp.getUpdatedAt();
		this.encours = cmp.getEncours();

		this.plafondJournalier = cmp.getPlafondJournalier();
		this.plafondMensuel = cmp.getPlafondMensuel();
		this.consommation = cmp.getConsommation();
		this.consoJournaliere = cmp.getPlafondJournalier();
		this.taux = cmp.getTaux();
		
		this.numMaticule = cmp.getNumMaticule();
		this.enbale = cmp.isEnbale();
		this.absolute = cmp.isAbsolute();
		this.categorie = cmp.getCategorie();
		this.status = cmp.getStatus();
		Tierspayant tierspayant = cmp.getTierspayant();
		if (tierspayant != null) {
			this.tierspayantId = tierspayant.getId();
			this.libelleTiersPayantF = tierspayant.getLibelLong();
		}

	}
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CompteClientDTO compteClientDTO = (CompteClientDTO) o;
        if (compteClientDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), compteClientDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

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
            ", numMaticule='" + getNumMaticule() + "'" +
            ", enbale='" + isEnbale() + "'" +
            ", categorie='" + getCategorie() + "'" +
            ", status='" + getStatus() + "'" +
            ", absolute='" + isAbsolute() + "'" +
            ", clientId=" + getClientId() +
            ", tierspayantId=" + getTierspayantId() +
            "}";
    }
}
