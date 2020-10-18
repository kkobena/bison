package com.kobe.nucleus.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A DetailsAjustement.
 */
@Entity
@Table(name = "details_ajustement")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DetailsAjustement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "qte_init", nullable = false)
    private Integer qteInit;

    @NotNull
    @Column(name = "qte_finale", nullable = false)
    private Integer qteFinale;

    @NotNull
    @Column(name = "qte_ajuste", nullable = false)
    private Integer qteAjuste;

    @NotNull
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @NotNull
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "detailsAjustements", allowSetters = true)
    private Produit produit;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQteInit() {
        return qteInit;
    }

    public DetailsAjustement qteInit(Integer qteInit) {
        this.qteInit = qteInit;
        return this;
    }

    public void setQteInit(Integer qteInit) {
        this.qteInit = qteInit;
    }

    public Integer getQteFinale() {
        return qteFinale;
    }

    public DetailsAjustement qteFinale(Integer qteFinale) {
        this.qteFinale = qteFinale;
        return this;
    }

    public void setQteFinale(Integer qteFinale) {
        this.qteFinale = qteFinale;
    }

    public Integer getQteAjuste() {
        return qteAjuste;
    }

    public DetailsAjustement qteAjuste(Integer qteAjuste) {
        this.qteAjuste = qteAjuste;
        return this;
    }

    public void setQteAjuste(Integer qteAjuste) {
        this.qteAjuste = qteAjuste;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public DetailsAjustement createdAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public DetailsAjustement updatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Produit getProduit() {
        return produit;
    }

    public DetailsAjustement produit(Produit produit) {
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
        if (!(o instanceof DetailsAjustement)) {
            return false;
        }
        return id != null && id.equals(((DetailsAjustement) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DetailsAjustement{" +
            "id=" + getId() +
            ", qteInit=" + getQteInit() +
            ", qteFinale=" + getQteFinale() +
            ", qteAjuste=" + getQteAjuste() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            "}";
    }
}
