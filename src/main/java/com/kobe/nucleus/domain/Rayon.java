package com.kobe.nucleus.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import com.kobe.nucleus.domain.enumeration.Status;

/**
 * A Rayon.
 */
@Entity
@Table(name = "rayon", uniqueConstraints = { @UniqueConstraint(columnNames = { "libelle", "magasin_id" }),
		@UniqueConstraint(columnNames = { "code", "magasin_id" }) })
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Rayon implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
	@SequenceGenerator(name = "sequenceGenerator")
	private Long id;

	@Column(name = "created_at")
	private Instant createdAt;

	@Column(name = "updated_at")
	private Instant updatedAt;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	private Status status = Status.ACTIVE;

	@NotNull
	@Column(name = "code", nullable = false)
	private String code;

	@NotNull
	@Column(name = "libelle", nullable = false)
	private String libelle;

	@OneToMany(mappedBy = "rayon")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Set<StockProduit> stockProduits = new HashSet<>();

	@ManyToOne(optional = false)
	@NotNull
	@JsonIgnoreProperties(value = "rayons", allowSetters = true)
	private Magasin magasin;
	@Column(name = "exclude",columnDefinition = "boolean default false")
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

	public Rayon createdAt(Instant createdAt) {
		this.createdAt = createdAt;
		return this;
	}

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}

	public Instant getUpdatedAt() {
		return updatedAt;
	}

	public Rayon updatedAt(Instant updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

	public void setUpdatedAt(Instant updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Status getStatus() {
		return status;
	}

	public Rayon status(Status status) {
		this.status = status;
		return this;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public Rayon code(String code) {
		this.code = code;
		return this;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLibelle() {
		return libelle;
	}

	public Rayon libelle(String libelle) {
		this.libelle = libelle;
		return this;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Set<StockProduit> getStockProduits() {
		return stockProduits;
	}

	public Rayon stockProduits(Set<StockProduit> stockProduits) {
		this.stockProduits = stockProduits;
		return this;
	}

	public Rayon addStockProduit(StockProduit stockProduit) {
		this.stockProduits.add(stockProduit);
		stockProduit.setRayon(this);
		return this;
	}

	public Rayon removeStockProduit(StockProduit stockProduit) {
		this.stockProduits.remove(stockProduit);
		stockProduit.setRayon(null);
		return this;
	}

	public void setStockProduits(Set<StockProduit> stockProduits) {
		this.stockProduits = stockProduits;
	}

	public Magasin getMagasin() {
		return magasin;
	}

	public Rayon magasin(Magasin magasin) {
		this.magasin = magasin;
		return this;
	}

	public void setMagasin(Magasin magasin) {
		this.magasin = magasin;
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
		if (!(o instanceof Rayon)) {
			return false;
		}
		return id != null && id.equals(((Rayon) o).id);
	}

	@Override
	public int hashCode() {
		return 31;
	}

	// prettier-ignore
	@Override
	public String toString() {
		return "Rayon{" + "id=" + getId() + ", createdAt='" + getCreatedAt() + "'" + ", updatedAt='" + getUpdatedAt()
				+ "'" + ", status='" + getStatus() + "'" + ", code='" + getCode() + "'" + ", libelle='" + getLibelle()
				+ "'" + "}";
	}
}
