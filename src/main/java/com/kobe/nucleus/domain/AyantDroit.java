package com.kobe.nucleus.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.kobe.nucleus.domain.enumeration.Status;

/**
 * A AyantDroit.
 */
@Entity
@Table(name = "ayant_droit")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AyantDroit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    @NotNull
    @Column(name = "updated_at", nullable = false)
    private LocalDate updatedAt;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @Column(name = "num")
    private String num;

    @NotNull
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "sexe")
    private String sexe;

    @Column(name = "dat_naiss")
    private LocalDate datNaiss;

    @Column(name = "mobile")
    private String mobile;

    @OneToMany(mappedBy = "ayantDroit")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<FactureItem> factureItems = new HashSet<>();

    @OneToMany(mappedBy = "ayantDroit")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Vente> ventes = new HashSet<>();

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("ayantDroits")
    private Client assure;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public AyantDroit createdAt(LocalDate createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public AyantDroit updatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Status getStatus() {
        return status;
    }

    public AyantDroit status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getNum() {
        return num;
    }

    public AyantDroit num(String num) {
        this.num = num;
        return this;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getFirstName() {
        return firstName;
    }

    public AyantDroit firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public AyantDroit lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSexe() {
        return sexe;
    }

    public AyantDroit sexe(String sexe) {
        this.sexe = sexe;
        return this;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public LocalDate getDatNaiss() {
        return datNaiss;
    }

    public AyantDroit datNaiss(LocalDate datNaiss) {
        this.datNaiss = datNaiss;
        return this;
    }

    public void setDatNaiss(LocalDate datNaiss) {
        this.datNaiss = datNaiss;
    }

    public String getMobile() {
        return mobile;
    }

    public AyantDroit mobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Set<FactureItem> getFactureItems() {
        return factureItems;
    }

    public AyantDroit factureItems(Set<FactureItem> factureItems) {
        this.factureItems = factureItems;
        return this;
    }

    public AyantDroit addFactureItem(FactureItem factureItem) {
        this.factureItems.add(factureItem);
        factureItem.setAyantDroit(this);
        return this;
    }

    public AyantDroit removeFactureItem(FactureItem factureItem) {
        this.factureItems.remove(factureItem);
        factureItem.setAyantDroit(null);
        return this;
    }

    public void setFactureItems(Set<FactureItem> factureItems) {
        this.factureItems = factureItems;
    }

    public Set<Vente> getVentes() {
        return ventes;
    }

    public AyantDroit ventes(Set<Vente> ventes) {
        this.ventes = ventes;
        return this;
    }

    public AyantDroit addVente(Vente vente) {
        this.ventes.add(vente);
        vente.setAyantDroit(this);
        return this;
    }

    public AyantDroit removeVente(Vente vente) {
        this.ventes.remove(vente);
        vente.setAyantDroit(null);
        return this;
    }

    public void setVentes(Set<Vente> ventes) {
        this.ventes = ventes;
    }

    public Client getAssure() {
        return assure;
    }

    public AyantDroit assure(Client client) {
        this.assure = client;
        return this;
    }

    public void setAssure(Client client) {
        this.assure = client;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AyantDroit)) {
            return false;
        }
        return id != null && id.equals(((AyantDroit) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "AyantDroit{" +
            "id=" + getId() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", status='" + getStatus() + "'" +
            ", num='" + getNum() + "'" +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", sexe='" + getSexe() + "'" +
            ", datNaiss='" + getDatNaiss() + "'" +
            ", mobile='" + getMobile() + "'" +
            "}";
    }
}
