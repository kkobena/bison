package com.kobe.nucleus.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A DetailsInventaire.
 */
@Entity
@Table(name = "details_inventaire")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DetailsInventaire implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "qty", nullable = false)
    private Integer qty;

    @NotNull
    @Column(name = "qty_init", nullable = false)
    private Integer qtyInit;

    @NotNull
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @NotNull
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @NotNull
    @Column(name = "is_updated", nullable = false)
    private Boolean isUpdated;

    @NotNull
    @Column(name = "taux", nullable = false)
    //taux d'accepation ecart
    private Float taux;

    @ManyToOne
    @JsonIgnoreProperties(value = "detailsInventaires", allowSetters = true)
    private Inventaire inventaire;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "detailsInventaires", allowSetters = true)
    private StockProduit stockProduit;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQty() {
        return qty;
    }

    public DetailsInventaire qty(Integer qty) {
        this.qty = qty;
        return this;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Integer getQtyInit() {
        return qtyInit;
    }

    public DetailsInventaire qtyInit(Integer qtyInit) {
        this.qtyInit = qtyInit;
        return this;
    }

    public void setQtyInit(Integer qtyInit) {
        this.qtyInit = qtyInit;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public DetailsInventaire createdAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public DetailsInventaire updatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Boolean isIsUpdated() {
        return isUpdated;
    }

    public DetailsInventaire isUpdated(Boolean isUpdated) {
        this.isUpdated = isUpdated;
        return this;
    }

    public void setIsUpdated(Boolean isUpdated) {
        this.isUpdated = isUpdated;
    }

    public Float getTaux() {
        return taux;
    }

    public DetailsInventaire taux(Float taux) {
        this.taux = taux;
        return this;
    }

    public void setTaux(Float taux) {
        this.taux = taux;
    }

    public Inventaire getInventaire() {
        return inventaire;
    }

    public DetailsInventaire inventaire(Inventaire inventaire) {
        this.inventaire = inventaire;
        return this;
    }

    public void setInventaire(Inventaire inventaire) {
        this.inventaire = inventaire;
    }

    public StockProduit getStockProduit() {
        return stockProduit;
    }

    public DetailsInventaire stockProduit(StockProduit stockProduit) {
        this.stockProduit = stockProduit;
        return this;
    }

    public void setStockProduit(StockProduit stockProduit) {
        this.stockProduit = stockProduit;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DetailsInventaire)) {
            return false;
        }
        return id != null && id.equals(((DetailsInventaire) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DetailsInventaire{" +
            "id=" + getId() +
            ", qty=" + getQty() +
            ", qtyInit=" + getQtyInit() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", isUpdated='" + isIsUpdated() + "'" +
            ", taux=" + getTaux() +
            "}";
    }
}
