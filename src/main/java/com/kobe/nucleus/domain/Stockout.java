package com.kobe.nucleus.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A Stockout.
 */
@Entity
@Table(name = "stockout")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Stockout implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "mvt_day", nullable = false)
    private LocalDate mvtDay;

    @NotNull
    @Column(name = "qty", nullable = false)
    private Integer qty;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "stockouts", allowSetters = true)
    private Produit produit;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getMvtDay() {
        return mvtDay;
    }

    public Stockout mvtDay(LocalDate mvtDay) {
        this.mvtDay = mvtDay;
        return this;
    }

    public void setMvtDay(LocalDate mvtDay) {
        this.mvtDay = mvtDay;
    }

    public Integer getQty() {
        return qty;
    }

    public Stockout qty(Integer qty) {
        this.qty = qty;
        return this;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Produit getProduit() {
        return produit;
    }

    public Stockout produit(Produit produit) {
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
        if (!(o instanceof Stockout)) {
            return false;
        }
        return id != null && id.equals(((Stockout) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Stockout{" +
            "id=" + getId() +
            ", mvtDay='" + getMvtDay() + "'" +
            ", qty=" + getQty() +
            "}";
    }
}
