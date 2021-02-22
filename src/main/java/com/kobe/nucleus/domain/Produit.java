package com.kobe.nucleus.domain;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kobe.nucleus.domain.enumeration.Status;

/**
 * A Produit.
 */
@Entity
@Table(name = "produit", indexes = { @Index(columnList = "libelle", name = "produit_libelle_index"),
		@Index(columnList = "status", name = "produit_status_index"),
		@Index(columnList = "code_cip", name = "produit_codeCip_index"),
		@Index(columnList = "code_ean", name = "produit_codeean_index") })
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
//@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
public class Produit implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
	@SequenceGenerator(name = "sequenceGenerator")
	private Long id;

	@NotNull
	@Column(name = "libelle", nullable = false, unique = true)
	private String libelle;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	private Status status = Status.ACTIVE;

	@NotNull
	@Column(name = "created_at", nullable = false)
	private Instant createdAt = Instant.now();

	@NotNull
	@Column(name = "updated_at", nullable = false)
	private Instant updatedAt;

	@Column(name = "qty_appro")
	private Integer qtyAppro;

	@Column(name = "qty_seuil_mini")
	private Integer qtySeuilMini;

	@Column(name = "dateperemption")
	private Boolean dateperemption;

	@Column(name = "chiffre")
	private Boolean chiffre;

	@NotNull
	@Column(name = "code_cip", nullable = false, unique = true)
	private String codeCip;

	@Column(name = "code_ean")
	private String codeEan;

	@Column(name = "qty_details")
	private Integer qtyDetails;

	@NotNull
	@Column(name = "deconditionnable", nullable = false)
	private Boolean deconditionnable;

	@NotNull
	@Min(value = 1)
	@Column(name = "prix_paf", nullable = false)
	private Integer prixPaf;

	@NotNull
	@Min(value = 1)
	@Column(name = "prix_uni", nullable = false)
	private Integer prixUni;
	@Column(name = "exclude", columnDefinition = "boolean default false")
	private boolean exclude;
	@NotNull
	@Column(name = "prix_mnp", nullable = false)
	private Integer prixMnp;

	@OneToMany(mappedBy = "parent")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Set<Produit> produits = new HashSet<>();

	@OneToMany(mappedBy = "produit")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Set<CommandeItem> commandeItems = new HashSet<>();

	@OneToMany(mappedBy = "produit")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Set<Etiquette> etiquettes = new HashSet<>();

	@OneToMany(mappedBy = "produit")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Set<DetailsAjustement> detailsAjustements = new HashSet<>();

	@OneToMany(mappedBy = "produit", cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Set<StockProduit> stockProduits = new HashSet<>();

	@OneToMany(mappedBy = "produit", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE })
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Set<FournisseurProduit> fournisseurProduits = new HashSet<>();

	@ManyToOne
	@JsonIgnoreProperties(value = "produits", allowSetters = true)
	private Produit parent;

	@ManyToOne
	@JsonIgnoreProperties(value = "produits", allowSetters = true)
	private Laboratoire laboratoire;

	@ManyToOne
	@JsonIgnoreProperties(value = "produits", allowSetters = true)
	private FormProduit forme;

	@ManyToOne
	@JsonIgnoreProperties(value = "produits", allowSetters = true)
	private TypeEtiquette typeEtyquette;

	@ManyToOne
	@JsonIgnoreProperties(value = "produits", allowSetters = true)
	private FamilleProduit famille;

	@ManyToOne
	@JsonIgnoreProperties(value = "produits", allowSetters = true)
	private GammeProduit gamme;

	@ManyToOne
	@JsonIgnoreProperties(value = "produits", allowSetters = true)
	private Tva tva;
	@ManyToOne
	@JsonIgnoreProperties(value = "produits", allowSetters = true)
	private RemiseProduit remise;

	public RemiseProduit getRemise() {
		return remise;
	}

	public void setRemise(RemiseProduit remise) {
		this.remise = remise;
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

	public Produit libelle(String libelle) {
		this.libelle = libelle;
		return this;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Status getStatus() {
		return status;
	}

	public Produit status(Status status) {
		this.status = status;
		return this;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public Produit createdAt(Instant createdAt) {
		this.createdAt = createdAt;
		return this;
	}

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}

	public Instant getUpdatedAt() {
		return updatedAt;
	}

	public Produit updatedAt(Instant updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

	public void setUpdatedAt(Instant updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Integer getQtyAppro() {
		return qtyAppro;
	}

	public Produit qtyAppro(Integer qtyAppro) {
		this.qtyAppro = qtyAppro;
		return this;
	}

	public void setQtyAppro(Integer qtyAppro) {
		this.qtyAppro = qtyAppro;
	}

	public Integer getQtySeuilMini() {
		return qtySeuilMini;
	}

	public Produit qtySeuilMini(Integer qtySeuilMini) {
		this.qtySeuilMini = qtySeuilMini;
		return this;
	}

	public void setQtySeuilMini(Integer qtySeuilMini) {
		this.qtySeuilMini = qtySeuilMini;
	}

	public Boolean isDateperemption() {
		return dateperemption;
	}

	public Produit dateperemption(Boolean dateperemption) {
		this.dateperemption = dateperemption;
		return this;
	}

	public void setDateperemption(Boolean dateperemption) {
		this.dateperemption = dateperemption;
	}

	public Boolean isChiffre() {
		return chiffre;
	}

	public Produit chiffre(Boolean chiffre) {
		this.chiffre = chiffre;
		return this;
	}

	public void setChiffre(Boolean chiffre) {
		this.chiffre = chiffre;
	}

	public String getCodeCip() {
		return codeCip;
	}

	public Produit codeCip(String codeCip) {
		this.codeCip = codeCip;
		return this;
	}

	public void setCodeCip(String codeCip) {
		this.codeCip = codeCip;
	}

	public String getCodeEan() {
		return codeEan;
	}

	public Produit codeEan(String codeEan) {
		this.codeEan = codeEan;
		return this;
	}

	public void setCodeEan(String codeEan) {
		this.codeEan = codeEan;
	}

	public Integer getQtyDetails() {
		return qtyDetails;
	}

	public Produit qtyDetails(Integer qtyDetails) {
		this.qtyDetails = qtyDetails;
		return this;
	}

	public void setQtyDetails(Integer qtyDetails) {
		this.qtyDetails = qtyDetails;
	}

	public Boolean isDeconditionnable() {
		return deconditionnable;
	}

	public Produit deconditionnable(Boolean deconditionnable) {
		this.deconditionnable = deconditionnable;
		return this;
	}

	public void setDeconditionnable(Boolean deconditionnable) {
		this.deconditionnable = deconditionnable;
	}

	public Integer getPrixPaf() {
		return prixPaf;
	}

	public Produit prixPaf(Integer prixPaf) {
		this.prixPaf = prixPaf;
		return this;
	}

	public void setPrixPaf(Integer prixPaf) {
		this.prixPaf = prixPaf;
	}

	public Integer getPrixUni() {
		return prixUni;
	}

	public Produit prixUni(Integer prixUni) {
		this.prixUni = prixUni;
		return this;
	}

	public void setPrixUni(Integer prixUni) {
		this.prixUni = prixUni;
	}

	public Integer getPrixMnp() {
		return prixMnp;
	}

	public Produit prixMnp(Integer prixMnp) {
		this.prixMnp = prixMnp;
		return this;
	}

	public void setPrixMnp(Integer prixMnp) {
		this.prixMnp = prixMnp;
	}

	public Set<Produit> getProduits() {
		return produits;
	}

	public Produit produits(Set<Produit> produits) {
		this.produits = produits;
		return this;
	}

	public Produit addProduit(Produit produit) {
		this.produits.add(produit);
		produit.setParent(this);
		return this;
	}

	public Produit removeProduit(Produit produit) {
		this.produits.remove(produit);
		produit.setParent(null);
		return this;
	}

	public void setProduits(Set<Produit> produits) {
		this.produits = produits;
	}

	public Set<CommandeItem> getCommandeItems() {
		return commandeItems;
	}

	public Produit commandeItems(Set<CommandeItem> commandeItems) {
		this.commandeItems = commandeItems;
		return this;
	}

	public Produit addCommandeItem(CommandeItem commandeItem) {
		this.commandeItems.add(commandeItem);
		commandeItem.setProduit(this);
		return this;
	}

	public Produit removeCommandeItem(CommandeItem commandeItem) {
		this.commandeItems.remove(commandeItem);
		commandeItem.setProduit(null);
		return this;
	}

	public void setCommandeItems(Set<CommandeItem> commandeItems) {
		this.commandeItems = commandeItems;
	}

	public Set<Etiquette> getEtiquettes() {
		return etiquettes;
	}

	public Produit etiquettes(Set<Etiquette> etiquettes) {
		this.etiquettes = etiquettes;
		return this;
	}

	public Produit addEtiquette(Etiquette etiquette) {
		this.etiquettes.add(etiquette);
		etiquette.setProduit(this);
		return this;
	}

	public Produit removeEtiquette(Etiquette etiquette) {
		this.etiquettes.remove(etiquette);
		etiquette.setProduit(null);
		return this;
	}

	public void setEtiquettes(Set<Etiquette> etiquettes) {
		this.etiquettes = etiquettes;
	}

	public Set<DetailsAjustement> getDetailsAjustements() {
		return detailsAjustements;
	}

	public Produit detailsAjustements(Set<DetailsAjustement> detailsAjustements) {
		this.detailsAjustements = detailsAjustements;
		return this;
	}

	public Produit addDetailsAjustement(DetailsAjustement detailsAjustement) {
		this.detailsAjustements.add(detailsAjustement);
		detailsAjustement.setProduit(this);
		return this;
	}

	public Produit removeDetailsAjustement(DetailsAjustement detailsAjustement) {
		this.detailsAjustements.remove(detailsAjustement);
		detailsAjustement.setProduit(null);
		return this;
	}

	public void setDetailsAjustements(Set<DetailsAjustement> detailsAjustements) {
		this.detailsAjustements = detailsAjustements;
	}

	public Set<StockProduit> getStockProduits() {
		return stockProduits;
	}

	public Produit stockProduits(Set<StockProduit> stockProduits) {
		this.stockProduits = stockProduits;
		return this;
	}

	public Produit addStockProduit(StockProduit stockProduit) {
		this.stockProduits.add(stockProduit);
		stockProduit.setProduit(this);
		return this;
	}

	public Produit removeStockProduit(StockProduit stockProduit) {
		this.stockProduits.remove(stockProduit);
		stockProduit.setProduit(null);
		return this;
	}

	public Produit addFournisseurProduit(FournisseurProduit fournisseurProduit) {
		this.fournisseurProduits.add(fournisseurProduit);
		fournisseurProduit.setProduit(this);
		return this;
	}

	public Produit removeFournisseurProduit(FournisseurProduit fournisseurProduit) {
		this.fournisseurProduits.remove(fournisseurProduit);
		fournisseurProduit.setProduit(null);
		return this;
	}

	public void setStockProduits(Set<StockProduit> stockProduits) {
		this.stockProduits = stockProduits;
	}

	public Produit getParent() {
		return parent;
	}

	public Produit parent(Produit produit) {
		this.parent = produit;
		return this;
	}

	public void setParent(Produit produit) {
		this.parent = produit;
	}

	public Laboratoire getLaboratoire() {
		return laboratoire;
	}

	public Produit laboratoire(Laboratoire laboratoire) {
		this.laboratoire = laboratoire;
		return this;
	}

	public void setLaboratoire(Laboratoire laboratoire) {
		this.laboratoire = laboratoire;
	}

	public FormProduit getForme() {
		return forme;
	}

	public Produit forme(FormProduit formProduit) {
		this.forme = formProduit;
		return this;
	}

	public void setForme(FormProduit formProduit) {
		this.forme = formProduit;
	}

	public TypeEtiquette getTypeEtyquette() {
		return typeEtyquette;
	}

	public Produit typeEtyquette(TypeEtiquette typeEtiquette) {
		this.typeEtyquette = typeEtiquette;
		return this;
	}

	public void setTypeEtyquette(TypeEtiquette typeEtiquette) {
		this.typeEtyquette = typeEtiquette;
	}

	public FamilleProduit getFamille() {
		return famille;
	}

	public Produit famille(FamilleProduit familleProduit) {
		this.famille = familleProduit;
		return this;
	}

	public void setFamille(FamilleProduit familleProduit) {
		this.famille = familleProduit;
	}

	public GammeProduit getGamme() {
		return gamme;
	}

	public Produit gamme(GammeProduit gammeProduit) {
		this.gamme = gammeProduit;
		return this;
	}

	public void setGamme(GammeProduit gammeProduit) {
		this.gamme = gammeProduit;
	}

	public Tva getTva() {
		return tva;
	}

	public boolean isExclude() {
		return exclude;
	}

	public void setExclude(boolean exclude) {
		this.exclude = exclude;
	}

	public Produit tva(Tva tva) {
		this.tva = tva;
		return this;
	}

	public void setTva(Tva tva) {
		this.tva = tva;
	}

	public Set<FournisseurProduit> getFournisseurProduits() {
		return fournisseurProduits;
	}

	public void setFournisseurProduits(Set<FournisseurProduit> fournisseurProduits) {
		this.fournisseurProduits = fournisseurProduits;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Produit)) {
			return false;
		}
		return id != null && id.equals(((Produit) o).id);
	}

	@Override
	public int hashCode() {
		return 31;
	}

	// prettier-ignore
	@Override
	public String toString() {
		return "Produit{" + "id=" + getId() + ", libelle='" + getLibelle() + "'" + ", status='" + getStatus() + "'"
				+ ", createdAt='" + getCreatedAt() + "'" + ", updatedAt='" + getUpdatedAt() + "'" + ", qtyAppro="
				+ getQtyAppro() + ", qtySeuilMini=" + getQtySeuilMini() + ", dateperemption='" + isDateperemption()
				+ "'" + ", chiffre='" + isChiffre() + "'" + ", codeCip='" + getCodeCip() + "'" + ", codeEan='"
				+ getCodeEan() + "'" + ", qtyDetails=" + getQtyDetails() + ", deconditionnable='" + isDeconditionnable()
				+ "'" + ", prixPaf=" + getPrixPaf() + ", prixUni=" + getPrixUni() + ", prixMnp=" + getPrixMnp() + "}";
	}
}
