package com.kobe.nucleus.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.kobe.nucleus.domain.Client;
import com.kobe.nucleus.domain.Compagnie;
import com.kobe.nucleus.domain.Remise;
import com.kobe.nucleus.domain.enumeration.Status;
import com.kobe.nucleus.domain.enumeration.TypeClient;

/**
 * A DTO for the {@link com.kobe.nucleus.domain.Client} entity.
 */
public class ClientDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private LocalDate createdAt;

	private LocalDate updatedAt;

	private Status status;

	@NotNull
	private String firstName;

	private String lastName;

	private String sexe;

	private LocalDate datNaiss;
	private String numMaticule;

	@NotNull
	private TypeClient typeClient;
	private List<CompteClientDTO> compteClients = new ArrayList<>();
	private List<AyantDroitDTO> ayantDroits= new ArrayList<>();

	public List<CompteClientDTO> getCompteClients() {
		return compteClients;
	}

	public void setCompteClients(List<CompteClientDTO> compteClients) {
		this.compteClients = compteClients;
	}

	private Long compagnieId;

	private String compagnieLibelle;

	private Long remiseId;

	private String remiseValeur;

	public Long getId() {
		return id;
	}

	public String getNumMaticule() {
		return numMaticule;
	}

	public void setNumMaticule(String numMaticule) {
		this.numMaticule = numMaticule;
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
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		ClientDTO clientDTO = (ClientDTO) o;
		if (clientDTO.getId() == null || getId() == null) {
			return false;
		}
		return Objects.equals(getId(), clientDTO.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getId());
	}

	@Override
	public String toString() {
		return "ClientDTO{" + "id=" + getId() + ", createdAt='" + getCreatedAt() + "'" + ", updatedAt='"
				+ getUpdatedAt() + "'" + ", status='" + getStatus() + "'" + ", firstName='" + getFirstName() + "'"
				+ ", lastName='" + getLastName() + "'" + ", sexe='" + getSexe() + "'" + ", datNaiss='" + getDatNaiss()
				+ "'" + ", typeClient='" + getTypeClient() + "'" + ", compagnieId=" + getCompagnieId()
				+ ", compagnieLibelle='" + getCompagnieLibelle() + "'" + ", remiseId=" + getRemiseId()
				+ ", remiseValeur='" + getRemiseValeur() + "'" + "}";
	}

	public ClientDTO() {
	}

	public ClientDTO(Client client) {
		super();
		this.id = client.getId();
		this.createdAt = client.getCreatedAt();
		this.updatedAt = client.getUpdatedAt();
		this.status = client.getStatus();
		this.firstName = client.getFirstName();
		this.lastName = client.getLastName();
		this.sexe = client.getSexe();
		this.datNaiss = client.getDatNaiss();
		this.typeClient = client.getTypeClient();
		this.compteClients = client.getCompteClients().stream().sorted(Comparator.comparing(c->c.getCategorie())).map(e -> new CompteClientDTO(e))
				.collect(Collectors.toList());
		this.compteClients.stream().filter(a -> a.isPrincipal()).findFirst().ifPresent(e -> {
			this.numMaticule = e.getNumMaticule();
		});
		;
		Compagnie compagnie = client.getCompagnie();
		if (compagnie != null) {
			this.compagnieId = compagnie.getId();
			this.compagnieLibelle = compagnie.getLibelle();
		}
		Remise remise = client.getRemise();
		if (remise != null) {
			this.remiseId = remise.getId();
			this.remiseValeur = remise.getValeur();
		}
		this.ayantDroits=client.getAyantDroits().stream().map(arg0)

	}

	public List<AyantDroitDTO> getAyantDroits() {
		return ayantDroits;
	}

	public void setAyantDroits(List<AyantDroitDTO> ayantDroits) {
		this.ayantDroits = ayantDroits;
	}

}
