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

import com.kobe.nucleus.domain.enumeration.StatutFacture;

import com.kobe.nucleus.domain.enumeration.TypeFacture;

/**
 * A Facture.
 */
@Entity
@Table(name = "facture")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Facture implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @NotNull
    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "code_groupe")
    private String codeGroupe;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @Column(name = "date_debut")
    private LocalDate dateDebut;

    @Column(name = "date_fin")
    private LocalDate dateFin;

    @Column(name = "montant_forfetaire")
    private Integer montantForfetaire;

    @Column(name = "montant_remise")
    private Integer montantRemise;

    @Column(name = "montant_paye")
    private Integer montantPaye;

    @Column(name = "montant_restant")
    private Integer montantRestant;

    @Column(name = "montant_net")
    private Integer montantNet;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "statut_facture", nullable = false)
    private StatutFacture statutFacture;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type_facture", nullable = false)
    private TypeFacture typeFacture;

    @Column(name = "montant_brut")
    private Integer montantBrut;

    @NotNull
    @Column(name = "template", nullable = false)
    private Boolean template;

    @OneToMany(mappedBy = "facture")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<FactureItem> factureItems = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "factures", allowSetters = true)
    private Magasin magasin;

    @ManyToOne
    @JsonIgnoreProperties(value = "factures", allowSetters = true)
    private User createdBy;

    @ManyToOne
    @JsonIgnoreProperties(value = "factures", allowSetters = true)
    private GroupeTierspayant groupetp;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "factures", allowSetters = true)
    private Tierspayant tierpayant;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Facture createdAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public Facture updatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCode() {
        return code;
    }

    public Facture code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCodeGroupe() {
        return codeGroupe;
    }

    public Facture codeGroupe(String codeGroupe) {
        this.codeGroupe = codeGroupe;
        return this;
    }

    public void setCodeGroupe(String codeGroupe) {
        this.codeGroupe = codeGroupe;
    }

    public Status getStatus() {
        return status;
    }

    public Facture status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public Facture dateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
        return this;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public Facture dateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
        return this;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public Integer getMontantForfetaire() {
        return montantForfetaire;
    }

    public Facture montantForfetaire(Integer montantForfetaire) {
        this.montantForfetaire = montantForfetaire;
        return this;
    }

    public void setMontantForfetaire(Integer montantForfetaire) {
        this.montantForfetaire = montantForfetaire;
    }

    public Integer getMontantRemise() {
        return montantRemise;
    }

    public Facture montantRemise(Integer montantRemise) {
        this.montantRemise = montantRemise;
        return this;
    }

    public void setMontantRemise(Integer montantRemise) {
        this.montantRemise = montantRemise;
    }

    public Integer getMontantPaye() {
        return montantPaye;
    }

    public Facture montantPaye(Integer montantPaye) {
        this.montantPaye = montantPaye;
        return this;
    }

    public void setMontantPaye(Integer montantPaye) {
        this.montantPaye = montantPaye;
    }

    public Integer getMontantRestant() {
        return montantRestant;
    }

    public Facture montantRestant(Integer montantRestant) {
        this.montantRestant = montantRestant;
        return this;
    }

    public void setMontantRestant(Integer montantRestant) {
        this.montantRestant = montantRestant;
    }

    public Integer getMontantNet() {
        return montantNet;
    }

    public Facture montantNet(Integer montantNet) {
        this.montantNet = montantNet;
        return this;
    }

    public void setMontantNet(Integer montantNet) {
        this.montantNet = montantNet;
    }

    public StatutFacture getStatutFacture() {
        return statutFacture;
    }

    public Facture statutFacture(StatutFacture statutFacture) {
        this.statutFacture = statutFacture;
        return this;
    }

    public void setStatutFacture(StatutFacture statutFacture) {
        this.statutFacture = statutFacture;
    }

    public TypeFacture getTypeFacture() {
        return typeFacture;
    }

    public Facture typeFacture(TypeFacture typeFacture) {
        this.typeFacture = typeFacture;
        return this;
    }

    public void setTypeFacture(TypeFacture typeFacture) {
        this.typeFacture = typeFacture;
    }

    public Integer getMontantBrut() {
        return montantBrut;
    }

    public Facture montantBrut(Integer montantBrut) {
        this.montantBrut = montantBrut;
        return this;
    }

    public void setMontantBrut(Integer montantBrut) {
        this.montantBrut = montantBrut;
    }

    public Boolean isTemplate() {
        return template;
    }

    public Facture template(Boolean template) {
        this.template = template;
        return this;
    }

    public void setTemplate(Boolean template) {
        this.template = template;
    }

    public Set<FactureItem> getFactureItems() {
        return factureItems;
    }

    public Facture factureItems(Set<FactureItem> factureItems) {
        this.factureItems = factureItems;
        return this;
    }

    public Facture addFactureItem(FactureItem factureItem) {
        this.factureItems.add(factureItem);
        factureItem.setFacture(this);
        return this;
    }

    public Facture removeFactureItem(FactureItem factureItem) {
        this.factureItems.remove(factureItem);
        factureItem.setFacture(null);
        return this;
    }

    public void setFactureItems(Set<FactureItem> factureItems) {
        this.factureItems = factureItems;
    }

    public Magasin getMagasin() {
        return magasin;
    }

    public Facture magasin(Magasin magasin) {
        this.magasin = magasin;
        return this;
    }

    public void setMagasin(Magasin magasin) {
        this.magasin = magasin;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public Facture createdBy(User utilisateur) {
        this.createdBy = utilisateur;
        return this;
    }

    public void setCreatedBy(User utilisateur) {
        this.createdBy = utilisateur;
    }

    public GroupeTierspayant getGroupetp() {
        return groupetp;
    }

    public Facture groupetp(GroupeTierspayant groupeTierspayant) {
        this.groupetp = groupeTierspayant;
        return this;
    }

    public void setGroupetp(GroupeTierspayant groupeTierspayant) {
        this.groupetp = groupeTierspayant;
    }

    public Tierspayant getTierpayant() {
        return tierpayant;
    }

    public Facture tierpayant(Tierspayant tierspayant) {
        this.tierpayant = tierspayant;
        return this;
    }

    public void setTierpayant(Tierspayant tierspayant) {
        this.tierpayant = tierspayant;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Facture)) {
            return false;
        }
        return id != null && id.equals(((Facture) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Facture{" +
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
            "}";
    }
}
