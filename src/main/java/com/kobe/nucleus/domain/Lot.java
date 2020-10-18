package com.kobe.nucleus.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;

import com.kobe.nucleus.domain.enumeration.Peremption;

/**
 * A Lot.
 */
@Entity
@Table(name = "lot")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Lot implements Serializable {

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

    @NotNull
    @Column(name = "qte", nullable = false)
    private Integer qte;

    @NotNull
    @Column(name = "qt_ug", nullable = false)
    private Integer qtUg;

    @Column(name = "num")
    private String num;

    @Column(name = "date_fabrication")
    private LocalDate dateFabrication;

    @Column(name = "peremption")
    private LocalDate peremption;

    @Enumerated(EnumType.STRING)
    @Column(name = "peremptionstatus")
    private Peremption peremptionstatus;

    @ManyToOne
    @JsonIgnoreProperties(value = "lots", allowSetters = true)
    private CommandeItem commandeItem;

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

    public Lot createdAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public Lot updatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getQte() {
        return qte;
    }

    public Lot qte(Integer qte) {
        this.qte = qte;
        return this;
    }

    public void setQte(Integer qte) {
        this.qte = qte;
    }

    public Integer getQtUg() {
        return qtUg;
    }

    public Lot qtUg(Integer qtUg) {
        this.qtUg = qtUg;
        return this;
    }

    public void setQtUg(Integer qtUg) {
        this.qtUg = qtUg;
    }

    public String getNum() {
        return num;
    }

    public Lot num(String num) {
        this.num = num;
        return this;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public LocalDate getDateFabrication() {
        return dateFabrication;
    }

    public Lot dateFabrication(LocalDate dateFabrication) {
        this.dateFabrication = dateFabrication;
        return this;
    }

    public void setDateFabrication(LocalDate dateFabrication) {
        this.dateFabrication = dateFabrication;
    }

    public LocalDate getPeremption() {
        return peremption;
    }

    public Lot peremption(LocalDate peremption) {
        this.peremption = peremption;
        return this;
    }

    public void setPeremption(LocalDate peremption) {
        this.peremption = peremption;
    }

    public Peremption getPeremptionstatus() {
        return peremptionstatus;
    }

    public Lot peremptionstatus(Peremption peremptionstatus) {
        this.peremptionstatus = peremptionstatus;
        return this;
    }

    public void setPeremptionstatus(Peremption peremptionstatus) {
        this.peremptionstatus = peremptionstatus;
    }

    public CommandeItem getCommandeItem() {
        return commandeItem;
    }

    public Lot commandeItem(CommandeItem commandeItem) {
        this.commandeItem = commandeItem;
        return this;
    }

    public void setCommandeItem(CommandeItem commandeItem) {
        this.commandeItem = commandeItem;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Lot)) {
            return false;
        }
        return id != null && id.equals(((Lot) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Lot{" +
            "id=" + getId() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", qte=" + getQte() +
            ", qtUg=" + getQtUg() +
            ", num='" + getNum() + "'" +
            ", dateFabrication='" + getDateFabrication() + "'" +
            ", peremption='" + getPeremption() + "'" +
            ", peremptionstatus='" + getPeremptionstatus() + "'" +
            "}";
    }
}
