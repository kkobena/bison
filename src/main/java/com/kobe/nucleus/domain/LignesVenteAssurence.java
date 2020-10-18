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
 * A LignesVenteAssurence.
 */
@Entity
@Table(name = "lignes_vente_assurence")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class LignesVenteAssurence implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "montant")
    private Integer montant;

    @Column(name = "ref_bon")
    private String refBon;

    @Column(name = "taux")
    private Integer taux;

    @Column(name = "montant_reste")
    private Integer montantReste;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "statut_facture", nullable = false)
    private StatutFacture statutFacture;

    @NotNull
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @NotNull
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @ManyToOne
    @JsonIgnoreProperties(value = "lignesVenteAssurences", allowSetters = true)
    private Vente vente;

    @ManyToOne
    @JsonIgnoreProperties(value = "lignesVenteAssurences", allowSetters = true)
    private CompteClient compteClient;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMontant() {
        return montant;
    }

    public LignesVenteAssurence montant(Integer montant) {
        this.montant = montant;
        return this;
    }

    public void setMontant(Integer montant) {
        this.montant = montant;
    }

    public String getRefBon() {
        return refBon;
    }

    public LignesVenteAssurence refBon(String refBon) {
        this.refBon = refBon;
        return this;
    }

    public void setRefBon(String refBon) {
        this.refBon = refBon;
    }

    public Integer getTaux() {
        return taux;
    }

    public LignesVenteAssurence taux(Integer taux) {
        this.taux = taux;
        return this;
    }

    public void setTaux(Integer taux) {
        this.taux = taux;
    }

    public Integer getMontantReste() {
        return montantReste;
    }

    public LignesVenteAssurence montantReste(Integer montantReste) {
        this.montantReste = montantReste;
        return this;
    }

    public void setMontantReste(Integer montantReste) {
        this.montantReste = montantReste;
    }

    public StatutFacture getStatutFacture() {
        return statutFacture;
    }

    public LignesVenteAssurence statutFacture(StatutFacture statutFacture) {
        this.statutFacture = statutFacture;
        return this;
    }

    public void setStatutFacture(StatutFacture statutFacture) {
        this.statutFacture = statutFacture;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public LignesVenteAssurence createdAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public LignesVenteAssurence updatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Vente getVente() {
        return vente;
    }

    public LignesVenteAssurence vente(Vente vente) {
        this.vente = vente;
        return this;
    }

    public void setVente(Vente vente) {
        this.vente = vente;
    }

    public CompteClient getCompteClient() {
        return compteClient;
    }

    public LignesVenteAssurence compteClient(CompteClient compteClient) {
        this.compteClient = compteClient;
        return this;
    }

    public void setCompteClient(CompteClient compteClient) {
        this.compteClient = compteClient;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LignesVenteAssurence)) {
            return false;
        }
        return id != null && id.equals(((LignesVenteAssurence) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LignesVenteAssurence{" +
            "id=" + getId() +
            ", montant=" + getMontant() +
            ", refBon='" + getRefBon() + "'" +
            ", taux=" + getTaux() +
            ", montantReste=" + getMontantReste() +
            ", statutFacture='" + getStatutFacture() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            "}";
    }
}
