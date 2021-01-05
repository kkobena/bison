package com.kobe.nucleus.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

import com.kobe.nucleus.domain.CompteClient;
import com.kobe.nucleus.domain.Tierspayant;
import com.kobe.nucleus.domain.enumeration.CategorieAssurance;
import com.kobe.nucleus.domain.enumeration.Status;
import com.kobe.nucleus.domain.enumeration.TypeClient;

/**
 * A DTO for the {@link com.kobe.nucleus.domain.CompteClient} entity.
 */
public class CompteClientDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1108480329684792169L;

	private Long id;

	private Instant createdAt;

	private Instant updatedAt;

	private int encours = 0;

	private int version;

	private int plafondJournalier = 0;

	private int plafondMensuel = 0;

	private int consommation = 0;

	private int consoJournaliere = 0;

	private int taux = 0;

	private String numMaticule;

	private boolean enbale = true;

	private CategorieAssurance categorie;

	private Status status = Status.ACTIVE;

	private boolean absolute;
	@NotNull
	private TypeClient typeClient;

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

	public int getEncours() {
		return encours;
	}

	public void setEncours(int encours) {
		this.encours = encours;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getPlafondJournalier() {
		return plafondJournalier;
	}

	public void setPlafondJournalier(int plafondJournalier) {
		this.plafondJournalier = plafondJournalier;
	}

	public int getPlafondMensuel() {
		return plafondMensuel;
	}

	public void setPlafondMensuel(int plafondMensuel) {
		this.plafondMensuel = plafondMensuel;
	}

	public int getConsommation() {
		return consommation;
	}

	public void setConsommation(int consommation) {
		this.consommation = consommation;
	}

	public int getConsoJournaliere() {
		return consoJournaliere;
	}

	public void setConsoJournaliere(int consoJournaliere) {
		this.consoJournaliere = consoJournaliere;
	}

	public int getTaux() {
		return taux;
	}

	public void setTaux(int taux) {
		this.taux = taux;
	}

	public String getNumMaticule() {
		return numMaticule;
	}

	public void setNumMaticule(String numMaticule) {
		this.numMaticule = numMaticule;
	}

	public boolean isEnbale() {
		return enbale;
	}

	public void setEnbale(boolean enbale) {
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

	public boolean isAbsolute() {
		return absolute;
	}

	public void setAbsolute(boolean absolute) {
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

	public TypeClient getTypeClient() {
		return typeClient;
	}

	public void setTypeClient(TypeClient typeClient) {
		this.typeClient = typeClient;
	}

	public void setTierspayantId(Long tierspayantId) {
		this.tierspayantId = tierspayantId;
	}

	public CompteClientDTO() {
	}

	public CompteClientDTO(CompteClient cmp) {
		this.id = cmp.getId();
		this.createdAt = cmp.getCreatedAt();
		this.updatedAt = cmp.getUpdatedAt();
		this.encours = cmp.getEncours();
		this.plafondJournalier = cmp.getPlafondJournalier();
		this.plafondMensuel = cmp.getPlafondMensuel();
		this.consommation = cmp.getConsommation();
		this.consoJournaliere = cmp.getPlafondJournalier();
		this.taux = cmp.getTaux();
		this.typeClient = cmp.getTypeClient();
		this.numMaticule = cmp.getNumMaticule();
		this.enbale = cmp.isEnbale();
		this.absolute = cmp.isAbsolute();
		this.categorie = cmp.getCategorie();
		this.status = cmp.getStatus();
		this.typeClient = cmp.getTypeClient();
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
		return "CompteClientDTO{" + "id=" + getId() + ", createdAt='" + getCreatedAt() + "'" + ", updatedAt='"
				+ getUpdatedAt() + "'" + ", encours=" + getEncours() + ", version=" + getVersion()
				+ ", plafondJournalier=" + getPlafondJournalier() + ", plafondMensuel=" + getPlafondMensuel()
				+ ", consommation=" + getConsommation() + ", consoJournaliere=" + getConsoJournaliere() + ", taux="
				+ getTaux() + ", numMaticule='" + getNumMaticule() + "'" + ", enbale='" + isEnbale() + "'"
				+ ", categorie='" + getCategorie() + "'" + ", status='" + getStatus() + "'" + ", absolute='"
				+ isAbsolute() + "'" + ", clientId=" + getClientId() + ", tierspayantId=" + getTierspayantId() + "}";
	}
}
