package com.kobe.nucleus.service.dto;

import java.time.Instant;
import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import com.kobe.nucleus.domain.enumeration.Status;

/**
 * A DTO for the {@link com.kobe.nucleus.domain.CommandeItem} entity.
 */
public class CommandeItemDTO implements Serializable {
    
    private Long id;

    private Integer montantCmd;

    private Integer prixPafCmd;

    private Integer prixUniCmd;

    private Integer qtecmde;

    private Integer qterecu;

    private Integer qteInit;

    private Integer qtemaquant;

    private Integer qtegratuite;

    private Integer montantBon;

    private Integer prixPafBon;

    private Integer prixUniBon;

    private LocalDate peremption;

    private LocalDate dateFabrication;

    private Instant createdAt;

    private Instant updatedAt;

    @NotNull
    private Status status;


    private Long commandeId;

    private Long produitId;

    private String produitLibelle;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMontantCmd() {
        return montantCmd;
    }

    public void setMontantCmd(Integer montantCmd) {
        this.montantCmd = montantCmd;
    }

    public Integer getPrixPafCmd() {
        return prixPafCmd;
    }

    public void setPrixPafCmd(Integer prixPafCmd) {
        this.prixPafCmd = prixPafCmd;
    }

    public Integer getPrixUniCmd() {
        return prixUniCmd;
    }

    public void setPrixUniCmd(Integer prixUniCmd) {
        this.prixUniCmd = prixUniCmd;
    }

    public Integer getQtecmde() {
        return qtecmde;
    }

    public void setQtecmde(Integer qtecmde) {
        this.qtecmde = qtecmde;
    }

    public Integer getQterecu() {
        return qterecu;
    }

    public void setQterecu(Integer qterecu) {
        this.qterecu = qterecu;
    }

    public Integer getQteInit() {
        return qteInit;
    }

    public void setQteInit(Integer qteInit) {
        this.qteInit = qteInit;
    }

    public Integer getQtemaquant() {
        return qtemaquant;
    }

    public void setQtemaquant(Integer qtemaquant) {
        this.qtemaquant = qtemaquant;
    }

    public Integer getQtegratuite() {
        return qtegratuite;
    }

    public void setQtegratuite(Integer qtegratuite) {
        this.qtegratuite = qtegratuite;
    }

    public Integer getMontantBon() {
        return montantBon;
    }

    public void setMontantBon(Integer montantBon) {
        this.montantBon = montantBon;
    }

    public Integer getPrixPafBon() {
        return prixPafBon;
    }

    public void setPrixPafBon(Integer prixPafBon) {
        this.prixPafBon = prixPafBon;
    }

    public Integer getPrixUniBon() {
        return prixUniBon;
    }

    public void setPrixUniBon(Integer prixUniBon) {
        this.prixUniBon = prixUniBon;
    }

    public LocalDate getPeremption() {
        return peremption;
    }

    public void setPeremption(LocalDate peremption) {
        this.peremption = peremption;
    }

    public LocalDate getDateFabrication() {
        return dateFabrication;
    }

    public void setDateFabrication(LocalDate dateFabrication) {
        this.dateFabrication = dateFabrication;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getCommandeId() {
        return commandeId;
    }

    public void setCommandeId(Long commandeId) {
        this.commandeId = commandeId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CommandeItemDTO)) {
            return false;
        }

        return id != null && id.equals(((CommandeItemDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CommandeItemDTO{" +
            "id=" + getId() +
            ", montantCmd=" + getMontantCmd() +
            ", prixPafCmd=" + getPrixPafCmd() +
            ", prixUniCmd=" + getPrixUniCmd() +
            ", qtecmde=" + getQtecmde() +
            ", qterecu=" + getQterecu() +
            ", qteInit=" + getQteInit() +
            ", qtemaquant=" + getQtemaquant() +
            ", qtegratuite=" + getQtegratuite() +
            ", montantBon=" + getMontantBon() +
            ", prixPafBon=" + getPrixPafBon() +
            ", prixUniBon=" + getPrixUniBon() +
            ", peremption='" + getPeremption() + "'" +
            ", dateFabrication='" + getDateFabrication() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", status='" + getStatus() + "'" +
            ", commandeId=" + getCommandeId() +
            ", produitId=" + getProduitId() +
            ", produitLibelle='" + getProduitLibelle() + "'" +
            "}";
    }
}
