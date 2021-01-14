package com.kobe.nucleus.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Decondition.
 */
@Entity
@Table(name = "decondition")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Decondition implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "qty_stock", nullable = false)
    private Integer qtyStock;

    @NotNull
    @Column(name = "qty_init", nullable = false)
    private Integer qtyInit;

    @ManyToOne
    @JsonIgnoreProperties(value = "deconditions", allowSetters = true)
    private Magasin magasin;

    @ManyToOne
    @JsonIgnoreProperties(value = "deconditions", allowSetters = true)
    private User createdBy;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQtyStock() {
        return qtyStock;
    }

    public Decondition qtyStock(Integer qtyStock) {
        this.qtyStock = qtyStock;
        return this;
    }

    public void setQtyStock(Integer qtyStock) {
        this.qtyStock = qtyStock;
    }

    public Integer getQtyInit() {
        return qtyInit;
    }

    public Decondition qtyInit(Integer qtyInit) {
        this.qtyInit = qtyInit;
        return this;
    }

    public void setQtyInit(Integer qtyInit) {
        this.qtyInit = qtyInit;
    }

    public Magasin getMagasin() {
        return magasin;
    }

    public Decondition magasin(Magasin magasin) {
        this.magasin = magasin;
        return this;
    }

    public void setMagasin(Magasin magasin) {
        this.magasin = magasin;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public Decondition createdBy(User utilisateur) {
        this.createdBy = utilisateur;
        return this;
    }

    public void setCreatedBy(User utilisateur) {
        this.createdBy = utilisateur;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Decondition)) {
            return false;
        }
        return id != null && id.equals(((Decondition) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Decondition{" +
            "id=" + getId() +
            ", qtyStock=" + getQtyStock() +
            ", qtyInit=" + getQtyInit() +
            "}";
    }
}
