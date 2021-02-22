package com.kobe.nucleus.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;

import com.kobe.nucleus.domain.Magasin;
import com.kobe.nucleus.domain.Rayon;
import com.kobe.nucleus.domain.enumeration.Status;

/**
 * A DTO for the {@link com.kobe.nucleus.domain.Rayon} entity.
 */
public class RayonDTO implements Serializable {

	private static final long serialVersionUID = -8538723222419813431L;

	private Long id;

	private Instant createdAt;

	private Instant updatedAt;

	private Status status;

	private String code;

	@NotNull
	private String libelle;

	private Long magasinId;

	private String magasinNomCourt;
	private boolean exclude;
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

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Long getMagasinId() {
		return magasinId;
	}

	public void setMagasinId(Long magasinId) {
		this.magasinId = magasinId;
	}

	public String getMagasinNomCourt() {
		return magasinNomCourt;
	}

	public void setMagasinNomCourt(String magasinNomCourt) {
		this.magasinNomCourt = magasinNomCourt;
	}

	
	
	public boolean isExclude() {
		return exclude;
	}

	public void setExclude(boolean exclude) {
		this.exclude = exclude;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof RayonDTO)) {
			return false;
		}

		return id != null && id.equals(((RayonDTO) o).id);
	}

	@Override
	public int hashCode() {
		return 31;
	}

	@Override
	public String toString() {
		return "RayonDTO{" + "id=" + getId() + ", createdAt='" + getCreatedAt() + "'" + ", updatedAt='" + getUpdatedAt()
				+ "'" + ", status='" + getStatus() + "'" + ", code='" + getCode() + "'" + ", libelle='" + getLibelle()
				+ "'" + ", magasinId=" + getMagasinId() + ", magasinNomCourt='" + getMagasinNomCourt() + "'" + "}";
	}
	public RayonDTO() {
		
	}
	public RayonDTO(Rayon rayon) {
		this.id = rayon.getId();
		this.createdAt = rayon.getCreatedAt();
		this.updatedAt = rayon.getUpdatedAt();
		this.code = rayon.getCode();
		this.libelle = rayon.getLibelle();
		this.exclude=rayon.isExclude();
		Magasin magasin = rayon.getMagasin();
		if (magasin != null) {
			this.magasinId = magasin.getId();
			this.magasinNomCourt = magasin.getNomLong();
		}

	}

}
