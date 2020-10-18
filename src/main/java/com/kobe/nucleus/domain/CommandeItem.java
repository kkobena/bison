package com.kobe.nucleus.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.kobe.nucleus.domain.enumeration.Status;

/**
 * A CommandeItem.
 */
@Entity
@Table(name = "commande_item")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CommandeItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "montant_cmd")
    private Integer montantCmd;

    @Column(name = "prix_paf_cmd")
    private Integer prixPafCmd;

    @Column(name = "prix_uni_cmd")
    private Integer prixUniCmd;

    @Column(name = "qtecmde")
    private Integer qtecmde;

    @Column(name = "qterecu")
    private Integer qterecu;

    @Column(name = "qte_init")
    private Integer qteInit;

    @Column(name = "qtemaquant")
    private Integer qtemaquant;

    @Column(name = "qtegratuite")
    private Integer qtegratuite;

    @Column(name = "montant_bon")
    private Integer montantBon;

    @Column(name = "prix_paf_bon")
    private Integer prixPafBon;

    @Column(name = "prix_uni_bon")
    private Integer prixUniBon;

    @Column(name = "peremption")
    private LocalDate peremption;

    @Column(name = "date_fabrication")
    private LocalDate dateFabrication;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @OneToMany(mappedBy = "commandeItem")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<RetourItem> retourItems = new HashSet<>();

    @OneToMany(mappedBy = "commandeItem")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Lot> lots = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "commandeItems", allowSetters = true)
    private Commande commande;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "commandeItems", allowSetters = true)
    private Produit produit;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMontantCmd() {
        return montantCmd;
    }

    public CommandeItem montantCmd(Integer montantCmd) {
        this.montantCmd = montantCmd;
        return this;
    }

    public void setMontantCmd(Integer montantCmd) {
        this.montantCmd = montantCmd;
    }

    public Integer getPrixPafCmd() {
        return prixPafCmd;
    }

    public CommandeItem prixPafCmd(Integer prixPafCmd) {
        this.prixPafCmd = prixPafCmd;
        return this;
    }

    public void setPrixPafCmd(Integer prixPafCmd) {
        this.prixPafCmd = prixPafCmd;
    }

    public Integer getPrixUniCmd() {
        return prixUniCmd;
    }

    public CommandeItem prixUniCmd(Integer prixUniCmd) {
        this.prixUniCmd = prixUniCmd;
        return this;
    }

    public void setPrixUniCmd(Integer prixUniCmd) {
        this.prixUniCmd = prixUniCmd;
    }

    public Integer getQtecmde() {
        return qtecmde;
    }

    public CommandeItem qtecmde(Integer qtecmde) {
        this.qtecmde = qtecmde;
        return this;
    }

    public void setQtecmde(Integer qtecmde) {
        this.qtecmde = qtecmde;
    }

    public Integer getQterecu() {
        return qterecu;
    }

    public CommandeItem qterecu(Integer qterecu) {
        this.qterecu = qterecu;
        return this;
    }

    public void setQterecu(Integer qterecu) {
        this.qterecu = qterecu;
    }

    public Integer getQteInit() {
        return qteInit;
    }

    public CommandeItem qteInit(Integer qteInit) {
        this.qteInit = qteInit;
        return this;
    }

    public void setQteInit(Integer qteInit) {
        this.qteInit = qteInit;
    }

    public Integer getQtemaquant() {
        return qtemaquant;
    }

    public CommandeItem qtemaquant(Integer qtemaquant) {
        this.qtemaquant = qtemaquant;
        return this;
    }

    public void setQtemaquant(Integer qtemaquant) {
        this.qtemaquant = qtemaquant;
    }

    public Integer getQtegratuite() {
        return qtegratuite;
    }

    public CommandeItem qtegratuite(Integer qtegratuite) {
        this.qtegratuite = qtegratuite;
        return this;
    }

    public void setQtegratuite(Integer qtegratuite) {
        this.qtegratuite = qtegratuite;
    }

    public Integer getMontantBon() {
        return montantBon;
    }

    public CommandeItem montantBon(Integer montantBon) {
        this.montantBon = montantBon;
        return this;
    }

    public void setMontantBon(Integer montantBon) {
        this.montantBon = montantBon;
    }

    public Integer getPrixPafBon() {
        return prixPafBon;
    }

    public CommandeItem prixPafBon(Integer prixPafBon) {
        this.prixPafBon = prixPafBon;
        return this;
    }

    public void setPrixPafBon(Integer prixPafBon) {
        this.prixPafBon = prixPafBon;
    }

    public Integer getPrixUniBon() {
        return prixUniBon;
    }

    public CommandeItem prixUniBon(Integer prixUniBon) {
        this.prixUniBon = prixUniBon;
        return this;
    }

    public void setPrixUniBon(Integer prixUniBon) {
        this.prixUniBon = prixUniBon;
    }

    public LocalDate getPeremption() {
        return peremption;
    }

    public CommandeItem peremption(LocalDate peremption) {
        this.peremption = peremption;
        return this;
    }

    public void setPeremption(LocalDate peremption) {
        this.peremption = peremption;
    }

    public LocalDate getDateFabrication() {
        return dateFabrication;
    }

    public CommandeItem dateFabrication(LocalDate dateFabrication) {
        this.dateFabrication = dateFabrication;
        return this;
    }

    public void setDateFabrication(LocalDate dateFabrication) {
        this.dateFabrication = dateFabrication;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public CommandeItem createdAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public CommandeItem updatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Status getStatus() {
        return status;
    }

    public CommandeItem status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Set<RetourItem> getRetourItems() {
        return retourItems;
    }

    public CommandeItem retourItems(Set<RetourItem> retourItems) {
        this.retourItems = retourItems;
        return this;
    }

    public CommandeItem addRetourItem(RetourItem retourItem) {
        this.retourItems.add(retourItem);
        retourItem.setCommandeItem(this);
        return this;
    }

    public CommandeItem removeRetourItem(RetourItem retourItem) {
        this.retourItems.remove(retourItem);
        retourItem.setCommandeItem(null);
        return this;
    }

    public void setRetourItems(Set<RetourItem> retourItems) {
        this.retourItems = retourItems;
    }

    public Set<Lot> getLots() {
        return lots;
    }

    public CommandeItem lots(Set<Lot> lots) {
        this.lots = lots;
        return this;
    }

    public CommandeItem addLot(Lot lot) {
        this.lots.add(lot);
        lot.setCommandeItem(this);
        return this;
    }

    public CommandeItem removeLot(Lot lot) {
        this.lots.remove(lot);
        lot.setCommandeItem(null);
        return this;
    }

    public void setLots(Set<Lot> lots) {
        this.lots = lots;
    }

    public Commande getCommande() {
        return commande;
    }

    public CommandeItem commande(Commande commande) {
        this.commande = commande;
        return this;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public Produit getProduit() {
        return produit;
    }

    public CommandeItem produit(Produit produit) {
        this.produit = produit;
        return this;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CommandeItem)) {
            return false;
        }
        return id != null && id.equals(((CommandeItem) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CommandeItem{" +
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
            "}";
    }
}
