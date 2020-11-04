package com.kobe.nucleus.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import com.kobe.nucleus.domain.enumeration.CategorieAssurance;

import com.kobe.nucleus.domain.enumeration.Status;

/**
 * A CompteClient.
 */
@Entity
@Table(name = "compte_client", indexes = { @Index(name = "compte_num_maticule", columnList = "num_maticule"),
		@Index(name = "compte_client_enbale", columnList = "enbale"),
		@Index(name = "compte_client_status", columnList = "status"),
		@Index(name = "compte_client_categorie", columnList = "categorie") }, uniqueConstraints = {
				@UniqueConstraint(name = "UNQ_compte_client_client_tierspayant", columnNames = { "client_id",
						"tierspayant_id" }),
				@UniqueConstraint(name = "UNQ_compte_client_categorie", columnNames = { "client_id", "categorie" }),
				@UniqueConstraint(name = "UNQ_compte_client_num_maticule", columnNames = { "client_id",
						"num_maticule" }) }

)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CompteClient implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
	@SequenceGenerator(name = "sequenceGenerator")
	private Long id;

	@Column(name = "created_at")
	private Instant createdAt;

	@Column(name = "updated_at")
	private Instant updatedAt;

	@Column(name = "encours")
	private Integer encours;

	@Column(name = "version")
	private Integer version;

	@Column(name = "plafond_journalier")
	private Integer plafondJournalier;

	@Column(name = "plafond_mensuel")
	private Integer plafondMensuel;

	@Column(name = "consommation")
	private Integer consommation;

	@Column(name = "conso_journaliere")
	private Integer consoJournaliere;

	@Column(name = "taux")
	private Integer taux;

	@Column(name = "num_maticule")
	private String numMaticule;

	@Column(name = "enbale")
	private Boolean enbale;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "categorie")
	private CategorieAssurance categorie;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	private Status status;

	@NotNull
	@Column(name = "absolute", nullable = false)
	private Boolean absolute;

	@OneToMany(mappedBy = "compteClient")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Set<LignesVenteAssurence> lignesVenteAssurences = new HashSet<>();

	@ManyToOne(optional = false)
	@NotNull
	@JsonIgnoreProperties("compteClients")
	private Client client;

	@ManyToOne
	@JsonIgnoreProperties("compteClients")
	private Tierspayant tierspayant;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public CompteClient createdAt(Instant createdAt) {
		this.createdAt = createdAt;
		return this;
	}

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}

	public Instant getUpdatedAt() {
		return updatedAt;
	}

	public CompteClient updatedAt(Instant updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

	public void setUpdatedAt(Instant updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Integer getEncours() {
		return encours;
	}

	public CompteClient encours(Integer encours) {
		this.encours = encours;
		return this;
	}

	public void setEncours(Integer encours) {
		this.encours = encours;
	}

	public Integer getVersion() {
		return version;
	}

	public CompteClient version(Integer version) {
		this.version = version;
		return this;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getPlafondJournalier() {
		return plafondJournalier;
	}

	public CompteClient plafondJournalier(Integer plafondJournalier) {
		this.plafondJournalier = plafondJournalier;
		return this;
	}

	public void setPlafondJournalier(Integer plafondJournalier) {
		this.plafondJournalier = plafondJournalier;
	}

	public Integer getPlafondMensuel() {
		return plafondMensuel;
	}

	public CompteClient plafondMensuel(Integer plafondMensuel) {
		this.plafondMensuel = plafondMensuel;
		return this;
	}

	public void setPlafondMensuel(Integer plafondMensuel) {
		this.plafondMensuel = plafondMensuel;
	}

	public Integer getConsommation() {
		return consommation;
	}

	public CompteClient consommation(Integer consommation) {
		this.consommation = consommation;
		return this;
	}

	public void setConsommation(Integer consommation) {
		this.consommation = consommation;
	}

	public Integer getConsoJournaliere() {
		return consoJournaliere;
	}

	public CompteClient consoJournaliere(Integer consoJournaliere) {
		this.consoJournaliere = consoJournaliere;
		return this;
	}

	public void setConsoJournaliere(Integer consoJournaliere) {
		this.consoJournaliere = consoJournaliere;
	}

	public Integer getTaux() {
		return taux;
	}

	public CompteClient taux(Integer taux) {
		this.taux = taux;
		return this;
	}

	public void setTaux(Integer taux) {
		this.taux = taux;
	}

	public String getNumMaticule() {
		return numMaticule;
	}

	public CompteClient numMaticule(String numMaticule) {
		this.numMaticule = numMaticule;
		return this;
	}

	public void setNumMaticule(String numMaticule) {
		this.numMaticule = numMaticule;
	}

	public Boolean isEnbale() {
		return enbale;
	}

	public CompteClient enbale(Boolean enbale) {
		this.enbale = enbale;
		return this;
	}

	public void setEnbale(Boolean enbale) {
		this.enbale = enbale;
	}

	public CategorieAssurance getCategorie() {
		return categorie;
	}

	public CompteClient categorie(CategorieAssurance categorie) {
		this.categorie = categorie;
		return this;
	}

	public void setCategorie(CategorieAssurance categorie) {
		this.categorie = categorie;
	}

	public Status getStatus() {
		return status;
	}

	public CompteClient status(Status status) {
		this.status = status;
		return this;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Boolean isAbsolute() {
		return absolute;
	}

	public CompteClient absolute(Boolean absolute) {
		this.absolute = absolute;
		return this;
	}

	public void setAbsolute(Boolean absolute) {
		this.absolute = absolute;
	}

	public Set<LignesVenteAssurence> getLignesVenteAssurences() {
		return lignesVenteAssurences;
	}

	public CompteClient lignesVenteAssurences(Set<LignesVenteAssurence> lignesVenteAssurences) {
		this.lignesVenteAssurences = lignesVenteAssurences;
		return this;
	}

	public CompteClient addLignesVenteAssurence(LignesVenteAssurence lignesVenteAssurence) {
		this.lignesVenteAssurences.add(lignesVenteAssurence);
		lignesVenteAssurence.setCompteClient(this);
		return this;
	}

	public CompteClient removeLignesVenteAssurence(LignesVenteAssurence lignesVenteAssurence) {
		this.lignesVenteAssurences.remove(lignesVenteAssurence);
		lignesVenteAssurence.setCompteClient(null);
		return this;
	}

	public void setLignesVenteAssurences(Set<LignesVenteAssurence> lignesVenteAssurences) {
		this.lignesVenteAssurences = lignesVenteAssurences;
	}

	public Client getClient() {
		return client;
	}

	public CompteClient client(Client client) {
		this.client = client;
		return this;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Tierspayant getTierspayant() {
		return tierspayant;
	}

	public CompteClient tierspayant(Tierspayant tierspayant) {
		this.tierspayant = tierspayant;
		return this;
	}

	public void setTierspayant(Tierspayant tierspayant) {
		this.tierspayant = tierspayant;
	}
	// jhipster-needle-entity-add-getters-setters - JHipster will add getters and
	// setters here, do not remove

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof CompteClient)) {
			return false;
		}
		return id != null && id.equals(((CompteClient) o).id);
	}

	@Override
	public int hashCode() {
		return 31;
	}

	@Override
	public String toString() {
		return "CompteClient{" + "id=" + getId() + ", createdAt='" + getCreatedAt() + "'" + ", updatedAt='"
				+ getUpdatedAt() + "'" + ", encours=" + getEncours() + ", version=" + getVersion()
				+ ", plafondJournalier=" + getPlafondJournalier() + ", plafondMensuel=" + getPlafondMensuel()
				+ ", consommation=" + getConsommation() + ", consoJournaliere=" + getConsoJournaliere() + ", taux="
				+ getTaux() + ", numMaticule='" + getNumMaticule() + "'" + ", enbale='" + isEnbale() + "'"
				+ ", categorie='" + getCategorie() + "'" + ", status='" + getStatus() + "'" + ", absolute='"
				+ isAbsolute() + "'" + "}";
	}
}
