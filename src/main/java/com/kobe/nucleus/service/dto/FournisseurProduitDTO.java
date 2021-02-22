package com.kobe.nucleus.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;

import com.kobe.nucleus.domain.Fournisseur;
import com.kobe.nucleus.domain.FournisseurProduit;
import com.kobe.nucleus.domain.Produit;

import java.io.Serializable;

/**
 * A DTO for the {@link com.kobe.nucleus.domain.FournisseurProduit} entity.
 */
public class FournisseurProduitDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;


	private String codeCip;

	@NotNull
	@Min(value = 1)
	private Integer prixAchat;

	@NotNull
	@Min(value = 1)
	private Integer prixUni;

	private Instant createdAt;

	private Instant updatedAt;

	private Long produitId;

	private String produitLibelle;

	private Long fournisseurId;

	private String fournisseurLibelle;
	private boolean principal;

	public boolean isPrincipal() {
		return principal;
	}

	public void setPrincipal(boolean principal) {
		this.principal = principal;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodeCip() {
		return codeCip;
	}

	public void setCodeCip(String codeCip) {
		this.codeCip = codeCip;
	}

	public Integer getPrixAchat() {
		return prixAchat;
	}

	public void setPrixAchat(Integer prixAchat) {
		this.prixAchat = prixAchat;
	}

	public Integer getPrixUni() {
		return prixUni;
	}

	public void setPrixUni(Integer prixUni) {
		this.prixUni = prixUni;
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

	public Long getProduitId() {
		return produitId;
	}

	public void setProduitId(Long produitId) {
		this.produitId = produitId;
	}

	public String getProduitLibelle() {
		return produitLibelle;
	}

	public void setProduitLibelle(String produitLibelle) {
		this.produitLibelle = produitLibelle;
	}

	public Long getFournisseurId() {
		return fournisseurId;
	}

	public void setFournisseurId(Long fournisseurId) {
		this.fournisseurId = fournisseurId;
	}

	public String getFournisseurLibelle() {
		return fournisseurLibelle;
	}

	public void setFournisseurLibelle(String fournisseurLibelle) {
		this.fournisseurLibelle = fournisseurLibelle;
	}

	public FournisseurProduitDTO() {

	}

	public FournisseurProduitDTO(FournisseurProduit fournisseurProduit) {
		this.id = fournisseurProduit.getId();
		this.codeCip = fournisseurProduit.getCodeCip();
		this.prixAchat = fournisseurProduit.getPrixAchat();
		this.prixUni = fournisseurProduit.getPrixUni();
		this.createdAt = fournisseurProduit.getCreatedAt();
		this.updatedAt = fournisseurProduit.getUpdatedAt();
		Produit produit = fournisseurProduit.getProduit();
		this.produitId = produit.getId();
		this.produitLibelle = produit.getLibelle();
		Fournisseur fournisseur = fournisseurProduit.getFournisseur();
		this.fournisseurId = fournisseur.getId();
		this.fournisseurLibelle = fournisseur.getLibelle();
		this.principal=fournisseurProduit.isPrincipal();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof FournisseurProduitDTO)) {
			return false;
		}

		return id != null && id.equals(((FournisseurProduitDTO) o).id);
	}

	@Override
	public int hashCode() {
		return 31;
	}

	@Override
	public String toString() {
		return "FournisseurProduitDTO{" + "id=" + getId() + ", codeCip='" + getCodeCip() + "'" + ", prixAchat="
				+ getPrixAchat() + ", prixUni=" + getPrixUni() + ", createdAt='" + getCreatedAt() + "'"
				+ ", updatedAt='" + getUpdatedAt() + "'" + ", produitId=" + getProduitId() + ", produitLibelle='"
				+ getProduitLibelle() + "'" + ", fournisseurId=" + getFournisseurId() + ", fournisseurLibelle='"
				+ getFournisseurLibelle() + "'" + "}";
	}
}
