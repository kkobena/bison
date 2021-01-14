package com.kobe.nucleus.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.kobe.nucleus.domain.enumeration.StatutFacture;

import com.kobe.nucleus.domain.enumeration.OrderStatus;

import com.kobe.nucleus.domain.enumeration.TypeOrder;

/**
 * A Commande.
 */
@Entity
@Table(name = "commande", indexes = { @Index(columnList = "updated_at",name = "commande_updatedAt_index") ,
		@Index(columnList = "created_at",name = "commande_created_at_index") ,
		@Index(columnList = "status",name = "commande_status_index"),
		@Index(columnList = "type",name = "commande_type_index")
})
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
//@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
public class Commande implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @NotNull
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @Column(name = "num")
    private String num;

    @Column(name = "num_ref")
    private String numRef;

    @Column(name = "montant_cmd")
    private Integer montantCmd;

    @Column(name = "montant_tva")
    private Integer montantTva;

    @Column(name = "montant_ht")
    private Integer montantHT;

    @Column(name = "montant_ttc")
    private Integer montantTTC;

    @Column(name = "montant_regl")
    private Integer montantRegl;

    @Column(name = "montant_restant")
    private Integer montantRestant;

    @Column(name = "date_regl")
    private LocalDate dateRegl;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "date_livraison")
    private LocalDate dateLivraison;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "statut_facture", nullable = false)
    private StatutFacture statutFacture=StatutFacture.UNPAID;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "status", nullable = false)
    private OrderStatus status=OrderStatus.COMMANDEENCOURS;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private TypeOrder type;

    @OneToMany(mappedBy = "commande")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<CommandeItem> commandeItems = new HashSet<>();
    @NotNull
    @ManyToOne(optional = false)
    @JsonIgnoreProperties(value = "commandes", allowSetters = true)
    private Magasin magasin;
    @NotNull
    @ManyToOne(optional = false)
    @JsonIgnoreProperties(value = "commandes", allowSetters = true)
    private User createdBy;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Commande createdAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public Commande updatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getNum() {
        return num;
    }

    public Commande num(String num) {
        this.num = num;
        return this;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getNumRef() {
        return numRef;
    }

    public Commande numRef(String numRef) {
        this.numRef = numRef;
        return this;
    }

    public void setNumRef(String numRef) {
        this.numRef = numRef;
    }

    public Integer getMontantCmd() {
        return montantCmd;
    }

    public Commande montantCmd(Integer montantCmd) {
        this.montantCmd = montantCmd;
        return this;
    }

    public void setMontantCmd(Integer montantCmd) {
        this.montantCmd = montantCmd;
    }

    public Integer getMontantTva() {
        return montantTva;
    }

    public Commande montantTva(Integer montantTva) {
        this.montantTva = montantTva;
        return this;
    }

    public void setMontantTva(Integer montantTva) {
        this.montantTva = montantTva;
    }

    public Integer getMontantHT() {
        return montantHT;
    }

    public Commande montantHT(Integer montantHT) {
        this.montantHT = montantHT;
        return this;
    }

    public void setMontantHT(Integer montantHT) {
        this.montantHT = montantHT;
    }

    public Integer getMontantTTC() {
        return montantTTC;
    }

    public Commande montantTTC(Integer montantTTC) {
        this.montantTTC = montantTTC;
        return this;
    }

    public void setMontantTTC(Integer montantTTC) {
        this.montantTTC = montantTTC;
    }

    public Integer getMontantRegl() {
        return montantRegl;
    }

    public Commande montantRegl(Integer montantRegl) {
        this.montantRegl = montantRegl;
        return this;
    }

    public void setMontantRegl(Integer montantRegl) {
        this.montantRegl = montantRegl;
    }

    public Integer getMontantRestant() {
        return montantRestant;
    }

    public Commande montantRestant(Integer montantRestant) {
        this.montantRestant = montantRestant;
        return this;
    }

    public void setMontantRestant(Integer montantRestant) {
        this.montantRestant = montantRestant;
    }

    public LocalDate getDateRegl() {
        return dateRegl;
    }

    public Commande dateRegl(LocalDate dateRegl) {
        this.dateRegl = dateRegl;
        return this;
    }

    public void setDateRegl(LocalDate dateRegl) {
        this.dateRegl = dateRegl;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Commande endDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getDateLivraison() {
        return dateLivraison;
    }

    public Commande dateLivraison(LocalDate dateLivraison) {
        this.dateLivraison = dateLivraison;
        return this;
    }

    public void setDateLivraison(LocalDate dateLivraison) {
        this.dateLivraison = dateLivraison;
    }

    public StatutFacture getStatutFacture() {
        return statutFacture;
    }

    public Commande statutFacture(StatutFacture statutFacture) {
        this.statutFacture = statutFacture;
        return this;
    }

    public void setStatutFacture(StatutFacture statutFacture) {
        this.statutFacture = statutFacture;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public Commande status(OrderStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public TypeOrder getType() {
        return type;
    }

    public Commande type(TypeOrder type) {
        this.type = type;
        return this;
    }

    public void setType(TypeOrder type) {
        this.type = type;
    }

    public Set<CommandeItem> getCommandeItems() {
        return commandeItems;
    }

    public Commande commandeItems(Set<CommandeItem> commandeItems) {
        this.commandeItems = commandeItems;
        return this;
    }

    public Commande addCommandeItem(CommandeItem commandeItem) {
        this.commandeItems.add(commandeItem);
        commandeItem.setCommande(this);
        return this;
    }

    public Commande removeCommandeItem(CommandeItem commandeItem) {
        this.commandeItems.remove(commandeItem);
        commandeItem.setCommande(null);
        return this;
    }

    public void setCommandeItems(Set<CommandeItem> commandeItems) {
        this.commandeItems = commandeItems;
    }

    public Magasin getMagasin() {
        return magasin;
    }

    public Commande magasin(Magasin magasin) {
        this.magasin = magasin;
        return this;
    }

    public void setMagasin(Magasin magasin) {
        this.magasin = magasin;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public Commande createdBy(User utilisateur) {
        this.createdBy = utilisateur;
        return this;
    }

    public void setCreatedBy(User utilisateur) {
        this.createdBy = utilisateur;
    }
  

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Commande)) {
            return false;
        }
        return id != null && id.equals(((Commande) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }


    @Override
    public String toString() {
        return "Commande{" +
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
            "}";
    }
}
