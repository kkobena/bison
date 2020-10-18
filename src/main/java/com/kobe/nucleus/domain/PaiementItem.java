package com.kobe.nucleus.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A PaiementItem.
 */
@Entity
@Table(name = "paiement_item")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PaiementItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "code_ref")
    private String codeRef;

    @Column(name = "pkey")
    private String pkey;

    @NotNull
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @NotNull
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @Column(name = "montant_attendu")
    private Integer montantAttendu;

    @Column(name = "montant_regl")
    private Integer montantRegl;

    @Column(name = "montant_restant")
    private Integer montantRestant;

    @ManyToOne
    @JsonIgnoreProperties(value = "paiementItems", allowSetters = true)
    private Paiement paiement;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeRef() {
        return codeRef;
    }

    public PaiementItem codeRef(String codeRef) {
        this.codeRef = codeRef;
        return this;
    }

    public void setCodeRef(String codeRef) {
        this.codeRef = codeRef;
    }

    public String getPkey() {
        return pkey;
    }

    public PaiementItem pkey(String pkey) {
        this.pkey = pkey;
        return this;
    }

    public void setPkey(String pkey) {
        this.pkey = pkey;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public PaiementItem createdAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public PaiementItem updatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getMontantAttendu() {
        return montantAttendu;
    }

    public PaiementItem montantAttendu(Integer montantAttendu) {
        this.montantAttendu = montantAttendu;
        return this;
    }

    public void setMontantAttendu(Integer montantAttendu) {
        this.montantAttendu = montantAttendu;
    }

    public Integer getMontantRegl() {
        return montantRegl;
    }

    public PaiementItem montantRegl(Integer montantRegl) {
        this.montantRegl = montantRegl;
        return this;
    }

    public void setMontantRegl(Integer montantRegl) {
        this.montantRegl = montantRegl;
    }

    public Integer getMontantRestant() {
        return montantRestant;
    }

    public PaiementItem montantRestant(Integer montantRestant) {
        this.montantRestant = montantRestant;
        return this;
    }

    public void setMontantRestant(Integer montantRestant) {
        this.montantRestant = montantRestant;
    }

    public Paiement getPaiement() {
        return paiement;
    }

    public PaiementItem paiement(Paiement paiement) {
        this.paiement = paiement;
        return this;
    }

    public void setPaiement(Paiement paiement) {
        this.paiement = paiement;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PaiementItem)) {
            return false;
        }
        return id != null && id.equals(((PaiementItem) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PaiementItem{" +
            "id=" + getId() +
            ", codeRef='" + getCodeRef() + "'" +
            ", pkey='" + getPkey() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", montantAttendu=" + getMontantAttendu() +
            ", montantRegl=" + getMontantRegl() +
            ", montantRestant=" + getMontantRestant() +
            "}";
    }
}
