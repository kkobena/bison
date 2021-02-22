package com.kobe.nucleus.service.dto;

import java.io.Serializable;

public class ProduitCriteria implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String codeEan;
	private String codeCip;
	private String libelle;
	private String status;
	private Boolean dateperemption;
	private Boolean deconditionnable;
	private Long qtySeuilMini;
	private Integer qtyAppro;
	private Long parentId;
	private Integer prixPaf;
	private Integer prixUni;
	private Long formeId;
	private Long familleId;
	private Long gammeId;
	private Long fabriquantId;
	private Long laboratoireId;
	private Long tvaId;
	private Long magasinId;
	private Long rayonId;
	private String search;
	private Boolean deconditionne;
	private Long remiseId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodeEan() {
		return codeEan;
	}

	public void setCodeEan(String codeEan) {
		this.codeEan = codeEan;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Boolean getDateperemption() {
		return dateperemption;
	}

	public void setDateperemption(Boolean dateperemption) {
		this.dateperemption = dateperemption;
	}

	public Boolean getDeconditionnable() {
		return deconditionnable;
	}

	public void setDeconditionnable(Boolean deconditionnable) {
		this.deconditionnable = deconditionnable;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getCodeCip() {
		return codeCip;
	}

	public void setCodeCip(String codeCip) {
		this.codeCip = codeCip;
	}

	public Long getQtySeuilMini() {
		return qtySeuilMini;
	}

	public void setQtySeuilMini(Long qtySeuilMini) {
		this.qtySeuilMini = qtySeuilMini;
	}

	public Integer getQtyAppro() {
		return qtyAppro;
	}

	public void setQtyAppro(Integer qtyAppro) {
		this.qtyAppro = qtyAppro;
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



	@Override
	public String toString() {
		return "ProduitCriteria [id=" + id + ", codeEan=" + codeEan + ", codeCip=" + codeCip + ", libelle=" + libelle
				+ ", status=" + status + ", dateperemption=" + dateperemption + ", deconditionnable=" + deconditionnable
				+ ", qtySeuilMini=" + qtySeuilMini + ", qtyAppro=" + qtyAppro + ", parentId=" + parentId + ", prixPaf="
				+ prixPaf + ", prixUni=" + prixUni + ", formeId=" + formeId + ", familleId=" + familleId + ", gammeId="
				+ gammeId + ", fabriquantId=" + fabriquantId + ", laboratoireId=" + laboratoireId + ", tvaId=" + tvaId
				+ ", magasinId=" + magasinId + ", rayonId=" + rayonId + ", search=" + search + ", deconditionne="
				+ deconditionne + ", remiseId=" + remiseId + "]";
	}

	public Long getFormeId() {
		return formeId;
	}

	public void setFormeId(Long formeId) {
		this.formeId = formeId;
	}

	public Long getFamilleId() {
		return familleId;
	}

	public void setFamilleId(Long familleId) {
		this.familleId = familleId;
	}

	public Long getGammeId() {
		return gammeId;
	}

	public void setGammeId(Long gammeId) {
		this.gammeId = gammeId;
	}

	public Long getFabriquantId() {
		return fabriquantId;
	}

	public void setFabriquantId(Long fabriquantId) {
		this.fabriquantId = fabriquantId;
	}

	public Long getLaboratoireId() {
		return laboratoireId;
	}

	public void setLaboratoireId(Long laboratoireId) {
		this.laboratoireId = laboratoireId;
	}

	public Long getTvaId() {
		return tvaId;
	}

	public void setTvaId(Long tvaId) {
		this.tvaId = tvaId;
	}

	public Long getMagasinId() {
		return magasinId;
	}

	public void setMagasinId(Long magasinId) {
		this.magasinId = magasinId;
	}

	public Long getRayonId() {
		return rayonId;
	}

	public void setRayonId(Long rayonId) {
		this.rayonId = rayonId;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public Boolean getDeconditionne() {
		return deconditionne;
	}

	public void setDeconditionne(Boolean deconditionne) {
		this.deconditionne = deconditionne;
	}

	public Long getRemiseId() {
		return remiseId;
	}

	public void setRemiseId(Long remiseId) {
		this.remiseId = remiseId;
	}

}
