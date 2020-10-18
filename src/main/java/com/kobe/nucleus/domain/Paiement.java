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

import com.kobe.nucleus.domain.enumeration.GroupeModePayment;

import com.kobe.nucleus.domain.enumeration.CategorieTransaction;

/**
 * A Paiement.
 */
@Entity
@Table(name = "paiement")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Paiement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "libelle")
    private String libelle;

    @NotNull
    @Column(name = "checked", nullable = false)
    private Boolean checked;

    @NotNull
    @Column(name = "pkey", nullable = false, unique = true)
    private String pkey;

    @NotNull
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @NotNull
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @NotNull
    @Column(name = "montant_net", nullable = false)
    private Integer montantNet;

    @NotNull
    @Column(name = "montant_brut", nullable = false)
    private Integer montantBrut;

    @NotNull
    @Column(name = "montant_remise", nullable = false)
    private Integer montantRemise;

    @NotNull
    @Column(name = "montant_debit", nullable = false)
    private Integer montantDebit;

    @NotNull
    @Column(name = "montant_credit", nullable = false)
    private Integer montantCredit;

    @NotNull
    @Column(name = "montant_verse", nullable = false)
    private Integer montantVerse;

    @NotNull
    @Column(name = "montant_rendu", nullable = false)
    private Integer montantRendu;

    @NotNull
    @Column(name = "montant_restant", nullable = false)
    private Integer montantRestant;

    @Enumerated(EnumType.STRING)
    @Column(name = "groupe_mode")
    private GroupeModePayment groupeMode;

    @Enumerated(EnumType.STRING)
    @Column(name = "categorie")
    private CategorieTransaction categorie;

    @Column(name = "date_mvt")
    private LocalDate dateMVT;

    @Column(name = "ref")
    private String ref;

    @Column(name = "organisme")
    private String organisme;

    @OneToMany(mappedBy = "paiement")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<PaiementItem> paiementItems = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "paiements", allowSetters = true)
    private Magasin magasin;

    @ManyToOne
    @JsonIgnoreProperties(value = "paiements", allowSetters = true)
    private Utilisateur createdBy;

    @ManyToOne
    @JsonIgnoreProperties(value = "paiements", allowSetters = true)
    private Banque banque;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public Paiement libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Boolean isChecked() {
        return checked;
    }

    public Paiement checked(Boolean checked) {
        this.checked = checked;
        return this;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public String getPkey() {
        return pkey;
    }

    public Paiement pkey(String pkey) {
        this.pkey = pkey;
        return this;
    }

    public void setPkey(String pkey) {
        this.pkey = pkey;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Paiement createdAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public Paiement updatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getMontantNet() {
        return montantNet;
    }

    public Paiement montantNet(Integer montantNet) {
        this.montantNet = montantNet;
        return this;
    }

    public void setMontantNet(Integer montantNet) {
        this.montantNet = montantNet;
    }

    public Integer getMontantBrut() {
        return montantBrut;
    }

    public Paiement montantBrut(Integer montantBrut) {
        this.montantBrut = montantBrut;
        return this;
    }

    public void setMontantBrut(Integer montantBrut) {
        this.montantBrut = montantBrut;
    }

    public Integer getMontantRemise() {
        return montantRemise;
    }

    public Paiement montantRemise(Integer montantRemise) {
        this.montantRemise = montantRemise;
        return this;
    }

    public void setMontantRemise(Integer montantRemise) {
        this.montantRemise = montantRemise;
    }

    public Integer getMontantDebit() {
        return montantDebit;
    }

    public Paiement montantDebit(Integer montantDebit) {
        this.montantDebit = montantDebit;
        return this;
    }

    public void setMontantDebit(Integer montantDebit) {
        this.montantDebit = montantDebit;
    }

    public Integer getMontantCredit() {
        return montantCredit;
    }

    public Paiement montantCredit(Integer montantCredit) {
        this.montantCredit = montantCredit;
        return this;
    }

    public void setMontantCredit(Integer montantCredit) {
        this.montantCredit = montantCredit;
    }

    public Integer getMontantVerse() {
        return montantVerse;
    }

    public Paiement montantVerse(Integer montantVerse) {
        this.montantVerse = montantVerse;
        return this;
    }

    public void setMontantVerse(Integer montantVerse) {
        this.montantVerse = montantVerse;
    }

    public Integer getMontantRendu() {
        return montantRendu;
    }

    public Paiement montantRendu(Integer montantRendu) {
        this.montantRendu = montantRendu;
        return this;
    }

    public void setMontantRendu(Integer montantRendu) {
        this.montantRendu = montantRendu;
    }

    public Integer getMontantRestant() {
        return montantRestant;
    }

    public Paiement montantRestant(Integer montantRestant) {
        this.montantRestant = montantRestant;
        return this;
    }

    public void setMontantRestant(Integer montantRestant) {
        this.montantRestant = montantRestant;
    }

    public GroupeModePayment getGroupeMode() {
        return groupeMode;
    }

    public Paiement groupeMode(GroupeModePayment groupeMode) {
        this.groupeMode = groupeMode;
        return this;
    }

    public void setGroupeMode(GroupeModePayment groupeMode) {
        this.groupeMode = groupeMode;
    }

    public CategorieTransaction getCategorie() {
        return categorie;
    }

    public Paiement categorie(CategorieTransaction categorie) {
        this.categorie = categorie;
        return this;
    }

    public void setCategorie(CategorieTransaction categorie) {
        this.categorie = categorie;
    }

    public LocalDate getDateMVT() {
        return dateMVT;
    }

    public Paiement dateMVT(LocalDate dateMVT) {
        this.dateMVT = dateMVT;
        return this;
    }

    public void setDateMVT(LocalDate dateMVT) {
        this.dateMVT = dateMVT;
    }

    public String getRef() {
        return ref;
    }

    public Paiement ref(String ref) {
        this.ref = ref;
        return this;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getOrganisme() {
        return organisme;
    }

    public Paiement organisme(String organisme) {
        this.organisme = organisme;
        return this;
    }

    public void setOrganisme(String organisme) {
        this.organisme = organisme;
    }

    public Set<PaiementItem> getPaiementItems() {
        return paiementItems;
    }

    public Paiement paiementItems(Set<PaiementItem> paiementItems) {
        this.paiementItems = paiementItems;
        return this;
    }

    public Paiement addPaiementItem(PaiementItem paiementItem) {
        this.paiementItems.add(paiementItem);
        paiementItem.setPaiement(this);
        return this;
    }

    public Paiement removePaiementItem(PaiementItem paiementItem) {
        this.paiementItems.remove(paiementItem);
        paiementItem.setPaiement(null);
        return this;
    }

    public void setPaiementItems(Set<PaiementItem> paiementItems) {
        this.paiementItems = paiementItems;
    }

    public Magasin getMagasin() {
        return magasin;
    }

    public Paiement magasin(Magasin magasin) {
        this.magasin = magasin;
        return this;
    }

    public void setMagasin(Magasin magasin) {
        this.magasin = magasin;
    }

    public Utilisateur getCreatedBy() {
        return createdBy;
    }

    public Paiement createdBy(Utilisateur utilisateur) {
        this.createdBy = utilisateur;
        return this;
    }

    public void setCreatedBy(Utilisateur utilisateur) {
        this.createdBy = utilisateur;
    }

    public Banque getBanque() {
        return banque;
    }

    public Paiement banque(Banque banque) {
        this.banque = banque;
        return this;
    }

    public void setBanque(Banque banque) {
        this.banque = banque;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Paiement)) {
            return false;
        }
        return id != null && id.equals(((Paiement) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Paiement{" +
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
            "}";
    }
}
