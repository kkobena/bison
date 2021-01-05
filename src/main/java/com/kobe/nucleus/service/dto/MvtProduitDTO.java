package com.kobe.nucleus.service.dto;

import java.time.Instant;
import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.kobe.nucleus.domain.MvtProduit} entity.
 */
public class MvtProduitDTO implements Serializable {
    
    private Long id;

    @NotNull
    private LocalDate mvtDate;

    @NotNull
    private Instant createdAt;

    @NotNull
    private Boolean checked;

    @NotNull
    private Integer qteDebut;

    @NotNull
    private Integer qteFinale;

    @NotNull
    private Integer prixPaf;

    @NotNull
    private Integer prixUni;

    @NotNull
    private Integer valeurTva;

    @NotNull
    private Integer montantTva;

    @NotNull
    private String pkey;


    private Long magasinId;

    private String magasinNomCourt;

    private Long createdById;

    private String createdByFirstName;

 

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getMvtDate() {
        return mvtDate;
    }

    public void setMvtDate(LocalDate mvtDate) {
        this.mvtDate = mvtDate;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean isChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public Integer getQteDebut() {
        return qteDebut;
    }

    public void setQteDebut(Integer qteDebut) {
        this.qteDebut = qteDebut;
    }

    public Integer getQteFinale() {
        return qteFinale;
    }

    public void setQteFinale(Integer qteFinale) {
        this.qteFinale = qteFinale;
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

    public Integer getValeurTva() {
        return valeurTva;
    }

    public void setValeurTva(Integer valeurTva) {
        this.valeurTva = valeurTva;
    }

    public Integer getMontantTva() {
        return montantTva;
    }

    public void setMontantTva(Integer montantTva) {
        this.montantTva = montantTva;
    }

    public String getPkey() {
        return pkey;
    }

    public void setPkey(String pkey) {
        this.pkey = pkey;
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

    public Long getCreatedById() {
        return createdById;
    }

    public void setCreatedById(Long utilisateurId) {
        this.createdById = utilisateurId;
    }

    public String getCreatedByFirstName() {
        return createdByFirstName;
    }

    public void setCreatedByFirstName(String utilisateurFirstName) {
        this.createdByFirstName = utilisateurFirstName;
    }

   

   
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MvtProduitDTO)) {
            return false;
        }

        return id != null && id.equals(((MvtProduitDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MvtProduitDTO{" +
            "id=" + getId() +
            ", mvtDate='" + getMvtDate() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", checked='" + isChecked() + "'" +
            ", qteDebut=" + getQteDebut() +
            ", qteFinale=" + getQteFinale() +
            ", prixPaf=" + getPrixPaf() +
            ", prixUni=" + getPrixUni() +
            ", valeurTva=" + getValeurTva() +
            ", montantTva=" + getMontantTva() +
            ", pkey='" + getPkey() + "'" +
            ", magasinId=" + getMagasinId() +
            ", magasinNomCourt='" + getMagasinNomCourt() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByFirstName='" + getCreatedByFirstName() + "'" +
        
        
            "}";
    }
}
