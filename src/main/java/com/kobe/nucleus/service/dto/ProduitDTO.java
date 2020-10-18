package com.kobe.nucleus.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import com.kobe.nucleus.domain.enumeration.Status;

/**
 * A DTO for the {@link com.kobe.nucleus.domain.Produit} entity.
 */
public class ProduitDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String libelle;

    @NotNull
    private Status status;

    @NotNull
    private Instant createdAt;

    @NotNull
    private Instant updatedAt;

    private Integer qtyAppro;

    private Integer qtySeuilMini;

    private Boolean etiquetable;

    private Boolean dateperemption;

    private Boolean chiffre;

    @NotNull
    private String codeCip;

    private String codeEan;

    private Integer qtyDetails;

    @NotNull
    private Boolean deconditionnable;

    private Boolean remisable;

    @NotNull
    private Integer prixPaf;

    @NotNull
    private Integer prixUni;

    @NotNull
    private Integer prixMnp;


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

    private Long fabriquantId;

    private String fabriquantLibelle;

    private Long tvaId;

    private String tvaTaux;
    
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

    public Boolean isEtiquetable() {
        return etiquetable;
    }

    public void setEtiquetable(Boolean etiquetable) {
        this.etiquetable = etiquetable;
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

    public Boolean isRemisable() {
        return remisable;
    }

    public void setRemisable(Boolean remisable) {
        this.remisable = remisable;
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

    public Long getFabriquantId() {
        return fabriquantId;
    }

    public void setFabriquantId(Long fabriquantId) {
        this.fabriquantId = fabriquantId;
    }

    public String getFabriquantLibelle() {
        return fabriquantLibelle;
    }

    public void setFabriquantLibelle(String fabriquantLibelle) {
        this.fabriquantLibelle = fabriquantLibelle;
    }

    public Long getTvaId() {
        return tvaId;
    }

    public void setTvaId(Long tvaId) {
        this.tvaId = tvaId;
    }

    public String getTvaTaux() {
        return tvaTaux;
    }

    public void setTvaTaux(String tvaTaux) {
        this.tvaTaux = tvaTaux;
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

    // prettier-ignore
    @Override
    public String toString() {
        return "ProduitDTO{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", qtyAppro=" + getQtyAppro() +
            ", qtySeuilMini=" + getQtySeuilMini() +
            ", etiquetable='" + isEtiquetable() + "'" +
            ", dateperemption='" + isDateperemption() + "'" +
            ", chiffre='" + isChiffre() + "'" +
            ", codeCip='" + getCodeCip() + "'" +
            ", codeEan='" + getCodeEan() + "'" +
            ", qtyDetails=" + getQtyDetails() +
            ", deconditionnable='" + isDeconditionnable() + "'" +
            ", remisable='" + isRemisable() + "'" +
            ", prixPaf=" + getPrixPaf() +
            ", prixUni=" + getPrixUni() +
            ", prixMnp=" + getPrixMnp() +
            ", parentId=" + getParentId() +
            ", parentLibelle='" + getParentLibelle() + "'" +
            ", laboratoireId=" + getLaboratoireId() +
            ", laboratoireLibelle='" + getLaboratoireLibelle() + "'" +
            ", formeId=" + getFormeId() +
            ", formeLibelle='" + getFormeLibelle() + "'" +
            ", typeEtyquetteId=" + getTypeEtyquetteId() +
            ", typeEtyquetteLibelle='" + getTypeEtyquetteLibelle() + "'" +
            ", familleId=" + getFamilleId() +
            ", familleLibelle='" + getFamilleLibelle() + "'" +
            ", gammeId=" + getGammeId() +
            ", gammeLibelle='" + getGammeLibelle() + "'" +
            ", fabriquantId=" + getFabriquantId() +
            ", fabriquantLibelle='" + getFabriquantLibelle() + "'" +
            ", tvaId=" + getTvaId() +
            ", tvaTaux='" + getTvaTaux() + "'" +
            "}";
    }
}
