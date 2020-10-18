package com.kobe.nucleus.service.dto;

import java.time.Instant;
import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import com.kobe.nucleus.domain.enumeration.Status;
import com.kobe.nucleus.domain.enumeration.StatutFacture;
import com.kobe.nucleus.domain.enumeration.TypeFacture;

/**
 * A DTO for the {@link com.kobe.nucleus.domain.Facture} entity.
 */
public class FactureDTO implements Serializable {
    
    private Long id;

    private Instant createdAt;

    private Instant updatedAt;

    @NotNull
    private String code;

    private String codeGroupe;

    @NotNull
    private Status status;

    private LocalDate dateDebut;

    private LocalDate dateFin;

    private Integer montantForfetaire;

    private Integer montantRemise;

    private Integer montantPaye;

    private Integer montantRestant;

    private Integer montantNet;

    @NotNull
    private StatutFacture statutFacture;

    @NotNull
    private TypeFacture typeFacture;

    private Integer montantBrut;

    @NotNull
    private Boolean template;


    private Long magasinId;

    private String magasinNomCourt;

    private Long createdById;

    private String createdByFirstName;

    private Long groupetpId;

    private String groupetpLibelle;

    private Long tierpayantId;

    private String tierpayantLibelCourt;
    
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCodeGroupe() {
        return codeGroupe;
    }

    public void setCodeGroupe(String codeGroupe) {
        this.codeGroupe = codeGroupe;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public Integer getMontantForfetaire() {
        return montantForfetaire;
    }

    public void setMontantForfetaire(Integer montantForfetaire) {
        this.montantForfetaire = montantForfetaire;
    }

    public Integer getMontantRemise() {
        return montantRemise;
    }

    public void setMontantRemise(Integer montantRemise) {
        this.montantRemise = montantRemise;
    }

    public Integer getMontantPaye() {
        return montantPaye;
    }

    public void setMontantPaye(Integer montantPaye) {
        this.montantPaye = montantPaye;
    }

    public Integer getMontantRestant() {
        return montantRestant;
    }

    public void setMontantRestant(Integer montantRestant) {
        this.montantRestant = montantRestant;
    }

    public Integer getMontantNet() {
        return montantNet;
    }

    public void setMontantNet(Integer montantNet) {
        this.montantNet = montantNet;
    }

    public StatutFacture getStatutFacture() {
        return statutFacture;
    }

    public void setStatutFacture(StatutFacture statutFacture) {
        this.statutFacture = statutFacture;
    }

    public TypeFacture getTypeFacture() {
        return typeFacture;
    }

    public void setTypeFacture(TypeFacture typeFacture) {
        this.typeFacture = typeFacture;
    }

    public Integer getMontantBrut() {
        return montantBrut;
    }

    public void setMontantBrut(Integer montantBrut) {
        this.montantBrut = montantBrut;
    }

    public Boolean isTemplate() {
        return template;
    }

    public void setTemplate(Boolean template) {
        this.template = template;
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

    public Long getGroupetpId() {
        return groupetpId;
    }

    public void setGroupetpId(Long groupeTierspayantId) {
        this.groupetpId = groupeTierspayantId;
    }

    public String getGroupetpLibelle() {
        return groupetpLibelle;
    }

    public void setGroupetpLibelle(String groupeTierspayantLibelle) {
        this.groupetpLibelle = groupeTierspayantLibelle;
    }

    public Long getTierpayantId() {
        return tierpayantId;
    }

    public void setTierpayantId(Long tierspayantId) {
        this.tierpayantId = tierspayantId;
    }

    public String getTierpayantLibelCourt() {
        return tierpayantLibelCourt;
    }

    public void setTierpayantLibelCourt(String tierspayantLibelCourt) {
        this.tierpayantLibelCourt = tierspayantLibelCourt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FactureDTO)) {
            return false;
        }

        return id != null && id.equals(((FactureDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FactureDTO{" +
            "id=" + getId() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", code='" + getCode() + "'" +
            ", codeGroupe='" + getCodeGroupe() + "'" +
            ", status='" + getStatus() + "'" +
            ", dateDebut='" + getDateDebut() + "'" +
            ", dateFin='" + getDateFin() + "'" +
            ", montantForfetaire=" + getMontantForfetaire() +
            ", montantRemise=" + getMontantRemise() +
            ", montantPaye=" + getMontantPaye() +
            ", montantRestant=" + getMontantRestant() +
            ", montantNet=" + getMontantNet() +
            ", statutFacture='" + getStatutFacture() + "'" +
            ", typeFacture='" + getTypeFacture() + "'" +
            ", montantBrut=" + getMontantBrut() +
            ", template='" + isTemplate() + "'" +
            ", magasinId=" + getMagasinId() +
            ", magasinNomCourt='" + getMagasinNomCourt() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByFirstName='" + getCreatedByFirstName() + "'" +
            ", groupetpId=" + getGroupetpId() +
            ", groupetpLibelle='" + getGroupetpLibelle() + "'" +
            ", tierpayantId=" + getTierpayantId() +
            ", tierpayantLibelCourt='" + getTierpayantLibelCourt() + "'" +
            "}";
    }
}
