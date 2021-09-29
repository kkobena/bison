package com.kobe.nucleus.service.dto;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

import com.kobe.nucleus.domain.FamilleProduit;
import com.kobe.nucleus.domain.FormProduit;
import com.kobe.nucleus.domain.GammeProduit;
import com.kobe.nucleus.domain.Laboratoire;
import com.kobe.nucleus.domain.Magasin;
import com.kobe.nucleus.domain.Produit;
import com.kobe.nucleus.domain.Rayon;
import com.kobe.nucleus.domain.RemiseProduit;
import com.kobe.nucleus.domain.Tva;
import com.kobe.nucleus.domain.TypeEtiquette;
import com.kobe.nucleus.domain.enumeration.Status;
import com.kobe.nucleus.domain.enumeration.TypeMagasin;
import com.kobe.nucleus.web.rest.ClientResource;

/**
 * A DTO for the {@link com.kobe.nucleus.domain.Produit} entity.
 */
public class ProduitDTO implements Serializable {

	private static final long serialVersionUID = 7777048661517241003L;
	private final Logger LOG = LoggerFactory.getLogger(ProduitDTO.class);
	private Long id;

	@NotNull
	private String libelle;

	@NotNull
	private String codeCip;
	private Status status = Status.ACTIVE;

	private Instant createdAt;

	private Instant updatedAt;
	private Instant lastDateOfSale;
	private Instant lastOrderDate;
	private Instant lastInventoryDate;

	private Integer qtyAppro = 0;

	private Integer qtySeuilMini = 0;

	private Boolean dateperemption = false;

	private Boolean chiffre = true;



	private Integer qtyDetails = 0;

	private Boolean deconditionnable = true;
    private String codeEan;
	private Long remiseId;
	private float tauxRemise;

	@NotNull
	private Integer prixPaf;

	@NotNull
	private Integer prixUni;

	private Integer prixMnp = 0;

	private Long parentId;

	private String parentLibelle;

	private Long laboratoireId;

	private String laboratoireLibelle;

	private Long formeId;

	private String formeLibelle;

	private Long typeEtyquetteId;

	private String typeEtyquetteLibelle;

	private Long familleId;

	private String familleLibelle;

	private Long gammeId;

	private String gammeLibelle;

	private Long tvaId;

	private Integer tvaTaux;
	private Set<StockProduitDTO> stockProduits = new HashSet<>();
	private Set<ProduitDTO> produits = new HashSet<>();
	private Set<FournisseurProduitDTO> fournisseurProduits = new HashSet<>();
	private StockProduitDTO stockProduit;
	private FournisseurProduitDTO fournisseurProduit;
	private String qtyStatus;

	private int totalQuantity;

	public int getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
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

	public Integer getQtyAppro() {
		return qtyAppro;
	}

	public void setQtyAppro(Integer qtyAppro) {
		this.qtyAppro = qtyAppro;
	}

	public Integer getQtySeuilMini() {
		return qtySeuilMini;
	}

	public void setQtySeuilMini(Integer qtySeuilMini) {
		this.qtySeuilMini = qtySeuilMini;
	}

	public Long getRemiseId() {
		return remiseId;
	}

	public void setRemiseId(Long remiseId) {
		this.remiseId = remiseId;
	}

	public float getTauxRemise() {
		return tauxRemise;
	}

	public void setTauxRemise(float tauxRemise) {
		this.tauxRemise = tauxRemise;
	}

	public Boolean isDateperemption() {
		return dateperemption;
	}

	public void setDateperemption(Boolean dateperemption) {
		this.dateperemption = dateperemption;
	}

	public Boolean isChiffre() {
		return chiffre;
	}

	public void setChiffre(Boolean chiffre) {
		this.chiffre = chiffre;
	}

	public String getCodeCip() {
		return codeCip;
	}

	public void setCodeCip(String codeCip) {
		this.codeCip = codeCip;
	}

	public String getCodeEan() {
		return codeEan;
	}

	public void setCodeEan(String codeEan) {
		this.codeEan = codeEan;
	}

	public Integer getQtyDetails() {
		return qtyDetails;
	}

	public void setQtyDetails(Integer qtyDetails) {
		this.qtyDetails = qtyDetails;
	}

	public Boolean isDeconditionnable() {
		return deconditionnable;
	}

	public void setDeconditionnable(Boolean deconditionnable) {
		this.deconditionnable = deconditionnable;
	}

	public Integer getPrixPaf() {
		return prixPaf;
	}

	public void setPrixPaf(Integer prixPaf) {
		this.prixPaf = prixPaf;
	}

	public Integer getPrixUni() {
		return prixUni;
	}

	public void setPrixUni(Integer prixUni) {
		this.prixUni = prixUni;
	}

	public Integer getPrixMnp() {
		return prixMnp;
	}

	public void setPrixMnp(Integer prixMnp) {
		this.prixMnp = prixMnp;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long produitId) {
		this.parentId = produitId;
	}

	public String getParentLibelle() {
		return parentLibelle;
	}

	public void setParentLibelle(String produitLibelle) {
		this.parentLibelle = produitLibelle;
	}

	public Long getLaboratoireId() {
		return laboratoireId;
	}

	public void setLaboratoireId(Long laboratoireId) {
		this.laboratoireId = laboratoireId;
	}

	public String getLaboratoireLibelle() {
		return laboratoireLibelle;
	}

	public void setLaboratoireLibelle(String laboratoireLibelle) {
		this.laboratoireLibelle = laboratoireLibelle;
	}

	public Long getFormeId() {
		return formeId;
	}

	public void setFormeId(Long formProduitId) {
		this.formeId = formProduitId;
	}

	public String getFormeLibelle() {
		return formeLibelle;
	}

	public void setFormeLibelle(String formProduitLibelle) {
		this.formeLibelle = formProduitLibelle;
	}

	public Long getTypeEtyquetteId() {
		return typeEtyquetteId;
	}

	public void setTypeEtyquetteId(Long typeEtiquetteId) {
		this.typeEtyquetteId = typeEtiquetteId;
	}

	public String getTypeEtyquetteLibelle() {
		return typeEtyquetteLibelle;
	}

	public void setTypeEtyquetteLibelle(String typeEtiquetteLibelle) {
		this.typeEtyquetteLibelle = typeEtiquetteLibelle;
	}

	public Long getFamilleId() {
		return familleId;
	}

	public void setFamilleId(Long familleProduitId) {
		this.familleId = familleProduitId;
	}

	public String getFamilleLibelle() {
		return familleLibelle;
	}

	public void setFamilleLibelle(String familleProduitLibelle) {
		this.familleLibelle = familleProduitLibelle;
	}

	public Long getGammeId() {
		return gammeId;
	}

	public void setGammeId(Long gammeProduitId) {
		this.gammeId = gammeProduitId;
	}

	public String getGammeLibelle() {
		return gammeLibelle;
	}

	public void setGammeLibelle(String gammeProduitLibelle) {
		this.gammeLibelle = gammeProduitLibelle;
	}

	public Long getTvaId() {
		return tvaId;
	}

	public void setTvaId(Long tvaId) {
		this.tvaId = tvaId;
	}

	public Integer getTvaTaux() {
		return tvaTaux;
	}

	public void setTvaTaux(Integer tvaTaux) {
		this.tvaTaux = tvaTaux;
	}

	public Set<StockProduitDTO> getStockProduits() {
		return stockProduits;
	}

	public void setStockProduits(Set<StockProduitDTO> stockProduits) {
		this.stockProduits = stockProduits;
	}

	public Set<ProduitDTO> getProduits() {
		return produits;
	}

	public void setProduits(Set<ProduitDTO> produits) {
		this.produits = produits;
	}

	public Set<FournisseurProduitDTO> getFournisseurProduits() {
		return fournisseurProduits;
	}

	public void setFournisseurProduits(Set<FournisseurProduitDTO> fournisseurProduits) {
		this.fournisseurProduits = fournisseurProduits;
	}

	public ProduitDTO() {

	}

	public ProduitDTO lastDateOfSale(Instant lastDateOfSale) {
		this.lastDateOfSale = lastDateOfSale;
		return this;
	}

	public ProduitDTO lastOrderDate(Instant lastOrderDate) {
		this.lastOrderDate = lastOrderDate;
		return this;
	}

	public Instant getLastDateOfSale() {
		return lastDateOfSale;
	}

	public void setLastDateOfSale(Instant lastDateOfSale) {
		this.lastDateOfSale = lastDateOfSale;
	}

	public Instant getLastOrderDate() {
		return lastOrderDate;
	}

	public void setLastOrderDate(Instant lastOrderDate) {
		this.lastOrderDate = lastOrderDate;
	}

	public ProduitDTO lastInventoryDate(Instant lastInventoryDate) {
		this.lastInventoryDate = lastInventoryDate;
		return this;
	}

	public Instant getLastInventoryDate() {
		return lastInventoryDate;
	}

	public void setLastInventoryDate(Instant lastInventoryDate) {
		this.lastInventoryDate = lastInventoryDate;
	}

	public ProduitDTO(Produit produit) {
		this.id = produit.getId();
		this.libelle = produit.getLibelle();
		this.codeCip = produit.getCodeCip();
		this.status = produit.getStatus();
		this.createdAt = produit.getCreatedAt();
		this.updatedAt = produit.getUpdatedAt();
		this.qtyAppro = produit.getQtyAppro();
		this.qtySeuilMini = produit.getQtySeuilMini();
		this.dateperemption = produit.isDateperemption();
		this.chiffre = produit.isChiffre();
		this.codeEan = produit.getCodeEan();
		this.qtyDetails = produit.getQtyDetails();
		this.deconditionnable = produit.isDeconditionnable();
		this.prixPaf = produit.getPrixPaf();
		this.prixUni = produit.getPrixUni();
		this.prixMnp = produit.getPrixMnp();
		Produit parent = produit.getParent();
		if (parent != null) {
			this.parentId = parent.getId();
			this.parentLibelle = parent.getLibelle();
		}
		Laboratoire laboratoire = produit.getLaboratoire();
		if (laboratoire != null) {
			this.laboratoireId = laboratoire.getId();
			this.laboratoireLibelle = laboratoire.getLibelle();
		}
		FormProduit formProduit = produit.getForme();
		if (formProduit != null) {
			this.formeId = produit.getId();
			this.formeLibelle = produit.getLibelle();
		}
		FamilleProduit familleProduit = produit.getFamille();
		if (familleProduit != null) {
			this.familleId = familleProduit.getId();
			this.familleLibelle = familleProduit.getLibelle();
		}
		TypeEtiquette typeEtiquette = produit.getTypeEtyquette();
		if (typeEtiquette != null) {
			this.typeEtyquetteId = typeEtiquette.getId();
			this.typeEtyquetteLibelle = typeEtiquette.getLibelle();
		}
		GammeProduit gamme = produit.getGamme();
		if (gamme != null) {
			this.gammeId = gamme.getId();
			this.gammeLibelle = gamme.getLibelle();
		}

		Tva tva = produit.getTva();
		if (tva != null) {
			this.tvaId = tva.getId();
			this.tvaTaux = tva.getTaux();
		}
		RemiseProduit remise = produit.getRemise();
		if (remise != null) {
			this.remiseId = remise.getId();
			this.tauxRemise = remise.getRemiseValue();
		}
		this.produits = produit.getProduits().stream().map(ProduitDTO::new).collect(Collectors.toSet());
		produit.getStockProduits().forEach(stock -> {
			LOG.info("Stock ========>>> {} ", stock);
			this.totalQuantity += stock.getQtyStock();
			StockProduitDTO stockDto=new StockProduitDTO(stock);
			LOG.info("stockDto ========>>> {} ", stockDto);
			this.getStockProduits().add(stockDto);
			LOG.info("SIZE  ========>>> {} ", this.getStockProduits().size());
			Rayon rayon=stock.getRayon();
			if(rayon!=null) {
				Magasin magasin=rayon.getMagasin();
				if(magasin!=null && magasin.getTypeMagasin()==TypeMagasin.PRINCIPAL) {
					this.stockProduit=stockDto;
				}
			}

		});

		if (this.getTotalQuantity() <= 0) {
			this.qtyStatus = "danger";
		}else if(this.getTotalQuantity() >0 && this.getTotalQuantity() < this.getQtySeuilMini()) {
			this.qtyStatus = "warning";
		}else {
			this.qtyStatus = "info";
		}

		this.fournisseurProduits = produit.getFournisseurProduits().stream().map(FournisseurProduitDTO::new)
				.collect(Collectors.toSet());
		produit.getFournisseurProduits().stream().filter(f -> f.isPrincipal()).findAny().ifPresent(fourProduit -> {
			this.fournisseurProduit = new FournisseurProduitDTO(fourProduit);
		});
	}

	public StockProduitDTO getStockProduit() {
		return stockProduit;
	}

	public void setStockProduit(StockProduitDTO stockProduit) {
		this.stockProduit = stockProduit;
	}

	public FournisseurProduitDTO getFournisseurProduit() {
		return fournisseurProduit;
	}

	public void setFournisseurProduit(FournisseurProduitDTO fournisseurProduit) {
		this.fournisseurProduit = fournisseurProduit;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof ProduitDTO)) {
			return false;
		}

		return id != null && id.equals(((ProduitDTO) o).id);
	}

	@Override
	public int hashCode() {
		return 31;
	}

	public String getQtyStatus() {
		return qtyStatus;
	}

	public void setQtyStatus(String qtyStatus) {
		this.qtyStatus = qtyStatus;
	}

	@Override
	public String toString() {
		return "ProduitDTO{" + "id=" + getId() + ", libelle='" + getLibelle() + "'" + ", status='" + getStatus() + "'"
				+ ", createdAt='" + getCreatedAt() + "'" + ", updatedAt='" + getUpdatedAt() + "'" + ", qtyAppro="
				+ getQtyAppro() + ", qtySeuilMini=" + getQtySeuilMini() + ", dateperemption='" + isDateperemption()
				+ "'" + ", chiffre='" + isChiffre() + "'" + ", codeCip='" + getCodeCip() + "'" + ", codeEan='"
				+ getCodeEan() + "'" + ", qtyDetails=" + getQtyDetails() + ", deconditionnable='" + isDeconditionnable()
				+ "'" + ", prixPaf=" + getPrixPaf() + ", prixUni=" + getPrixUni() + ", prixMnp=" + getPrixMnp()
				+ ", parentId=" + getParentId() + ", parentLibelle='" + getParentLibelle() + "'" + ", laboratoireId="
				+ getLaboratoireId() + ", laboratoireLibelle='" + getLaboratoireLibelle() + "'" + ", formeId="
				+ getFormeId() + ", formeLibelle='" + getFormeLibelle() + "'" + ", typeEtyquetteId="
				+ getTypeEtyquetteId() + ", typeEtyquetteLibelle='" + getTypeEtyquetteLibelle() + "'" + ", familleId="
				+ getFamilleId() + ", familleLibelle='" + getFamilleLibelle() + "'" + ", gammeId=" + getGammeId()
				+ ", gammeLibelle='" + getGammeLibelle() + "'"

				+ getTvaTaux() + "'" + "}";
	}
}
