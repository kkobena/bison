package com.kobe.nucleus.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

import com.kobe.nucleus.domain.enumeration.StatutFacture;

/**
 * A FactureItem.
 */
@Entity
@Table(name = "facture_item")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class FactureItem implements Serializable {

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

    @Column(name = "montant_remise")
    private Integer montantRemise;

    @Column(name = "montant_paye")
    private Integer montantPaye;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "statut_facture", nullable = false)
    private StatutFacture statutFacture;

    @Column(name = "montant_restant")
    private Integer montantRestant;

    @Column(name = "montant_net")
    private Integer montantNet;

    @Column(name = "montant_brut")
    private Integer montantBrut;

    @ManyToOne
    @JsonIgnoreProperties(value = "factureItems", allowSetters = true)
    private Facture facture;

    @ManyToOne
    @JsonIgnoreProperties(value = "factureItems", allowSetters = true)
    private Client client;

    @ManyToOne
    @JsonIgnoreProperties(value = "factureItems", allowSetters = true)
    private AyantDroit ayantDroit;

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

    public FactureItem createdAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public FactureItem updatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getMontantRemise() {
        return montantRemise;
    }

    public FactureItem montantRemise(Integer montantRemise) {
        this.montantRemise = montantRemise;
        return this;
    }

    public void setMontantRemise(Integer montantRemise) {
        this.montantRemise = montantRemise;
    }

    public Integer getMontantPaye() {
        return montantPaye;
    }

    public FactureItem montantPaye(Integer montantPaye) {
        this.montantPaye = montantPaye;
        return this;
    }

    public void setMontantPaye(Integer montantPaye) {
        this.montantPaye = montantPaye;
    }

    public StatutFacture getStatutFacture() {
        return statutFacture;
    }

    public FactureItem statutFacture(StatutFacture statutFacture) {
        this.statutFacture = statutFacture;
        return this;
    }

    public void setStatutFacture(StatutFacture statutFacture) {
        this.statutFacture = statutFacture;
    }

    public Integer getMontantRestant() {
        return montantRestant;
    }

    public FactureItem montantRestant(Integer montantRestant) {
        this.montantRestant = montantRestant;
        return this;
    }

    public void setMontantRestant(Integer montantRestant) {
        this.montantRestant = montantRestant;
    }

    public Integer getMontantNet() {
        return montantNet;
    }

    public FactureItem montantNet(Integer montantNet) {
        this.montantNet = montantNet;
        return this;
    }

    public void setMontantNet(Integer montantNet) {
        this.montantNet = montantNet;
    }

    public Integer getMontantBrut() {
        return montantBrut;
    }

    public FactureItem montantBrut(Integer montantBrut) {
        this.montantBrut = montantBrut;
        return this;
    }

    public void setMontantBrut(Integer montantBrut) {
        this.montantBrut = montantBrut;
    }

    public Facture getFacture() {
        return facture;
    }

    public FactureItem facture(Facture facture) {
        this.facture = facture;
        return this;
    }

    public void setFacture(Facture facture) {
        this.facture = facture;
    }

    public Client getClient() {
        return client;
    }

    public FactureItem client(Client client) {
        this.client = client;
        return this;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public AyantDroit getAyantDroit() {
        return ayantDroit;
    }

    public FactureItem ayantDroit(AyantDroit ayantDroit) {
        this.ayantDroit = ayantDroit;
        return this;
    }

    public void setAyantDroit(AyantDroit ayantDroit) {
        this.ayantDroit = ayantDroit;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FactureItem)) {
            return false;
        }
        return id != null && id.equals(((FactureItem) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FactureItem{" +
            "id=" + getId() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", montantRemise=" + getMontantRemise() +
            ", montantPaye=" + getMontantPaye() +
            ", statutFacture='" + getStatutFacture() + "'" +
            ", montantRestant=" + getMontantRestant() +
            ", montantNet=" + getMontantNet() +
            ", montantBrut=" + getMontantBrut() +
            "}";
    }
}
