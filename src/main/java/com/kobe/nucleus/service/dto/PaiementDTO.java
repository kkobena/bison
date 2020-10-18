package com.kobe.nucleus.service.dto;

import java.time.Instant;
import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import com.kobe.nucleus.domain.enumeration.GroupeModePayment;
import com.kobe.nucleus.domain.enumeration.CategorieTransaction;

/**
 * A DTO for the {@link com.kobe.nucleus.domain.Paiement} entity.
 */
public class PaiementDTO implements Serializable {
    
    private Long id;

    private String libelle;

    @NotNull
    private Boolean checked;

    @NotNull
    private String pkey;

    @NotNull
    private Instant createdAt;

    @NotNull
    private Instant updatedAt;

    @NotNull
    private Integer montantNet;

    @NotNull
    private Integer montantBrut;

    @NotNull
    private Integer montantRemise;

    @NotNull
    private Integer montantDebit;

    @NotNull
    private Integer montantCredit;

    @NotNull
    private Integer montantVerse;

    @NotNull
    private Integer montantRendu;

    @NotNull
    private Integer montantRestant;

    private GroupeModePayment groupeMode;

    private CategorieTransaction categorie;

    private LocalDate dateMVT;

    private String ref;

    private String organisme;


    private Long magasinId;

    private String magasinNomCourt;

    private Long createdById;

    private String createdByFirstName;

    private Long banqueId;

    private String banqueLibelle;
    
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

    public Boolean isChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public String getPkey() {
        return pkey;
    }

    public void setPkey(String pkey) {
        this.pkey = pkey;
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

    public Integer getMontantNet() {
        return montantNet;
    }

    public void setMontantNet(Integer montantNet) {
        this.montantNet = montantNet;
    }

    public Integer getMontantBrut() {
        return montantBrut;
    }

    public void setMontantBrut(Integer montantBrut) {
        this.montantBrut = montantBrut;
    }

    public Integer getMontantRemise() {
        return montantRemise;
    }

    public void setMontantRemise(Integer montantRemise) {
        this.montantRemise = montantRemise;
    }

    public Integer getMontantDebit() {
        return montantDebit;
    }

    public void setMontantDebit(Integer montantDebit) {
        this.montantDebit = montantDebit;
    }

    public Integer getMontantCredit() {
        return montantCredit;
    }

    public void setMontantCredit(Integer montantCredit) {
        this.montantCredit = montantCredit;
    }

    public Integer getMontantVerse() {
        return montantVerse;
    }

    public void setMontantVerse(Integer montantVerse) {
        this.montantVerse = montantVerse;
    }

    public Integer getMontantRendu() {
        return montantRendu;
    }

    public void setMontantRendu(Integer montantRendu) {
        this.montantRendu = montantRendu;
    }

    public Integer getMontantRestant() {
        return montantRestant;
    }

    public void setMontantRestant(Integer montantRestant) {
        this.montantRestant = montantRestant;
    }

    public GroupeModePayment getGroupeMode() {
        return groupeMode;
    }

    public void setGroupeMode(GroupeModePayment groupeMode) {
        this.groupeMode = groupeMode;
    }

    public CategorieTransaction getCategorie() {
        return categorie;
    }

    public void setCategorie(CategorieTransaction categorie) {
        this.categorie = categorie;
    }

    public LocalDate getDateMVT() {
        return dateMVT;
    }

    public void setDateMVT(LocalDate dateMVT) {
        this.dateMVT = dateMVT;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getOrganisme() {
        return organisme;
    }

    public void setOrganisme(String organisme) {
        this.organisme = organisme;
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

    public Long getBanqueId() {
        return banqueId;
    }

    public void setBanqueId(Long banqueId) {
        this.banqueId = banqueId;
    }

    public String getBanqueLibelle() {
        return banqueLibelle;
    }

    public void setBanqueLibelle(String banqueLibelle) {
        this.banqueLibelle = banqueLibelle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PaiementDTO)) {
            return false;
        }

        return id != null && id.equals(((PaiementDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PaiementDTO{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", checked='" + isChecked() + "'" +
            ", pkey='" + getPkey() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", montantNet=" + getMontantNet() +
            ", montantBrut=" + getMontantBrut() +
            ", montantRemise=" + getMontantRemise() +
            ", montantDebit=" + getMontantDebit() +
            ", montantCredit=" + getMontantCredit() +
            ", montantVerse=" + getMontantVerse() +
            ", montantRendu=" + getMontantRendu() +
            ", montantRestant=" + getMontantRestant() +
            ", groupeMode='" + getGroupeMode() + "'" +
            ", categorie='" + getCategorie() + "'" +
            ", dateMVT='" + getDateMVT() + "'" +
            ", ref='" + getRef() + "'" +
            ", organisme='" + getOrganisme() + "'" +
            ", magasinId=" + getMagasinId() +
            ", magasinNomCourt='" + getMagasinNomCourt() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByFirstName='" + getCreatedByFirstName() + "'" +
            ", banqueId=" + getBanqueId() +
            ", banqueLibelle='" + getBanqueLibelle() + "'" +
            "}";
    }
}
