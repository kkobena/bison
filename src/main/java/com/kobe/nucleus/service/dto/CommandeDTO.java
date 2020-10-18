package com.kobe.nucleus.service.dto;

import java.time.Instant;
import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import com.kobe.nucleus.domain.enumeration.StatutFacture;
import com.kobe.nucleus.domain.enumeration.OrderStatus;
import com.kobe.nucleus.domain.enumeration.TypeOrder;

/**
 * A DTO for the {@link com.kobe.nucleus.domain.Commande} entity.
 */
public class CommandeDTO implements Serializable {
    
    private Long id;

    @NotNull
    private Instant createdAt;

    @NotNull
    private Instant updatedAt;

    private String num;

    private String numRef;

    private Integer montantCmd;

    private Integer montantTva;

    private Integer montantHT;

    private Integer montantTTC;

    private Integer montantRegl;

    private Integer montantRestant;

    private LocalDate dateRegl;

    private LocalDate endDate;

    private LocalDate dateLivraison;

    @NotNull
    private StatutFacture statutFacture;

    private OrderStatus status;

    private TypeOrder type;


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

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getNumRef() {
        return numRef;
    }

    public void setNumRef(String numRef) {
        this.numRef = numRef;
    }

    public Integer getMontantCmd() {
        return montantCmd;
    }

    public void setMontantCmd(Integer montantCmd) {
        this.montantCmd = montantCmd;
    }

    public Integer getMontantTva() {
        return montantTva;
    }

    public void setMontantTva(Integer montantTva) {
        this.montantTva = montantTva;
    }

    public Integer getMontantHT() {
        return montantHT;
    }

    public void setMontantHT(Integer montantHT) {
        this.montantHT = montantHT;
    }

    public Integer getMontantTTC() {
        return montantTTC;
    }

    public void setMontantTTC(Integer montantTTC) {
        this.montantTTC = montantTTC;
    }

    public Integer getMontantRegl() {
        return montantRegl;
    }

    public void setMontantRegl(Integer montantRegl) {
        this.montantRegl = montantRegl;
    }

    public Integer getMontantRestant() {
        return montantRestant;
    }

    public void setMontantRestant(Integer montantRestant) {
        this.montantRestant = montantRestant;
    }

    public LocalDate getDateRegl() {
        return dateRegl;
    }

    public void setDateRegl(LocalDate dateRegl) {
        this.dateRegl = dateRegl;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getDateLivraison() {
        return dateLivraison;
    }

    public void setDateLivraison(LocalDate dateLivraison) {
        this.dateLivraison = dateLivraison;
    }

    public StatutFacture getStatutFacture() {
        return statutFacture;
    }

    public void setStatutFacture(StatutFacture statutFacture) {
        this.statutFacture = statutFacture;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public TypeOrder getType() {
        return type;
    }

    public void setType(TypeOrder type) {
        this.type = type;
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
        if (!(o instanceof CommandeDTO)) {
            return false;
        }

        return id != null && id.equals(((CommandeDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CommandeDTO{" +
            "id=" + getId() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", num='" + getNum() + "'" +
            ", numRef='" + getNumRef() + "'" +
            ", montantCmd=" + getMontantCmd() +
            ", montantTva=" + getMontantTva() +
            ", montantHT=" + getMontantHT() +
            ", montantTTC=" + getMontantTTC() +
            ", montantRegl=" + getMontantRegl() +
            ", montantRestant=" + getMontantRestant() +
            ", dateRegl='" + getDateRegl() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", dateLivraison='" + getDateLivraison() + "'" +
            ", statutFacture='" + getStatutFacture() + "'" +
            ", status='" + getStatus() + "'" +
            ", type='" + getType() + "'" +
            ", magasinId=" + getMagasinId() +
            ", magasinNomCourt='" + getMagasinNomCourt() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByFirstName='" + getCreatedByFirstName() + "'" +
            "}";
    }
}
