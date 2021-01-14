package com.kobe.nucleus.service.dto;

import javax.validation.constraints.*;

import com.kobe.nucleus.domain.Magasin;
import com.kobe.nucleus.domain.Produit;
import com.kobe.nucleus.domain.Rayon;
import com.kobe.nucleus.domain.StockProduit;
import com.kobe.nucleus.domain.enumeration.TypeMagasin;

import java.io.Serializable;
import java.time.Instant;

/**
 * A DTO for the {@link com.kobe.nucleus.domain.StockProduit} entity.
 */
public class StockProduitDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	@NotNull
	private Integer qtyStock;

	private int qtyVirtual;

	private int qtyUG;

	private Long rayonId;

	private String rayonLibelle;
	private String rayonCode;
	private String produitCip;
	private Long produitId;
	private Instant createdAt ;
	private Instant updatedAt;
	private String produitLibelle;
	private String nomMagasin;
	private String nomLongMagasin;
	private TypeMagasin typeStock;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getQtyStock() {
		return qtyStock;
	}

	public void setQtyStock(Integer qtyStock) {
		this.qtyStock = qtyStock;
	}

	public int getQtyVirtual() {
		return qtyVirtual;
	}

	public void setQtyVirtual(int qtyVirtual) {
		this.qtyVirtual = qtyVirtual;
	}

	public int getQtyUG() {
		return qtyUG;
	}

	public void setQtyUG(int qtyUG) {
		this.qtyUG = qtyUG;
	}

	public Long getRayonId() {
		return rayonId;
	}

	public void setRayonId(Long rayonId) {
		this.rayonId = rayonId;
	}

	public String getRayonLibelle() {
		return rayonLibelle;
	}

	public void setRayonLibelle(String rayonLibelle) {
		this.rayonLibelle = rayonLibelle;
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

	public String getRayonCode() {
		return rayonCode;
	}

	public void setRayonCode(String rayonCode) {
		this.rayonCode = rayonCode;
	}

	public String getProduitCip() {
		return produitCip;
	}

	public void setProduitCip(String produitCip) {
		this.produitCip = produitCip;
	}

	public StockProduitDTO() {

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

	public TypeMagasin getTypeStock() {
		return typeStock;
	}

	public void setTypeStock(TypeMagasin typeStock) {
		this.typeStock = typeStock;
	}

	public StockProduitDTO(StockProduit stockProduit) {
		this.id = stockProduit.getId();
		this.qtyStock = stockProduit.getQtyStock();
		this.qtyVirtual = stockProduit.getQtyVirtual();
		this.qtyUG = stockProduit.getQtyUG();
		Rayon rayon = stockProduit.getRayon();
		this.rayonId = rayon.getId();
		this.rayonCode = rayon.getCode();
		this.rayonLibelle = rayon.getLibelle();
		Magasin magasin = rayon.getMagasin();
		if (magasin != null) {
			this.nomLongMagasin = magasin.getNomLong();
			this.nomMagasin = magasin.getNomCourt();
			this.typeStock=magasin.getTypeMagasin();
		}
		Produit produit = stockProduit.getProduit();
		this.produitId = produit.getId();
		this.produitLibelle = produit.getLibelle();
		this.produitCip = produit.getCodeCip();
		this.createdAt = stockProduit.getCreatedAt();
		this.updatedAt = stockProduit.getUpdatedAt();
	}

	
	
	public String getNomMagasin() {
		return nomMagasin;
	}

	public void setNomMagasin(String nomMagasin) {
		this.nomMagasin = nomMagasin;
	}

	public String getNomLongMagasin() {
		return nomLongMagasin;
	}

	public void setNomLongMagasin(String nomLongMagasin) {
		this.nomLongMagasin = nomLongMagasin;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof StockProduitDTO)) {
			return false;
		}

		return id != null && id.equals(((StockProduitDTO) o).id);
	}

	@Override
	public int hashCode() {
		return 31;
	}

	@Override
	public String toString() {
		return "StockProduitDTO{" + "id=" + getId() + ", qtyStock=" + getQtyStock() + ", qtyVirtual=" + getQtyVirtual()
				+ ", qtyUG=" + getQtyUG() + ", rayonId=" + getRayonId() + ", rayonLibelle='" + getRayonLibelle() + "'"
				+ ", produitId=" + getProduitId() + ", produitLibelle='" + getProduitLibelle() + "'" + "}";
	}
}
