package com.kobe.nucleus.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A GroupeTierspayant.
 */
@Entity
@Table(name = "groupe_tierspayant")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GroupeTierspayant implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "code")
    private String code;

    @NotNull
    @Column(name = "libelle", nullable = false,unique = true)
    private String libelle;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "groupetp")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Facture> factures = new HashSet<>();

    @OneToMany(mappedBy = "groupetp")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Tierspayant> tierspayants = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public GroupeTierspayant code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public GroupeTierspayant libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getPhone() {
        return phone;
    }

    public GroupeTierspayant phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public GroupeTierspayant address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<Facture> getFactures() {
        return factures;
    }

    public GroupeTierspayant factures(Set<Facture> factures) {
        this.factures = factures;
        return this;
    }

    public GroupeTierspayant addFacture(Facture facture) {
        this.factures.add(facture);
        facture.setGroupetp(this);
        return this;
    }

    public GroupeTierspayant removeFacture(Facture facture) {
        this.factures.remove(facture);
        facture.setGroupetp(null);
        return this;
    }

    public void setFactures(Set<Facture> factures) {
        this.factures = factures;
    }

    public Set<Tierspayant> getTierspayants() {
        return tierspayants;
    }

    public GroupeTierspayant tierspayants(Set<Tierspayant> tierspayants) {
        this.tierspayants = tierspayants;
        return this;
    }

    public GroupeTierspayant addTierspayant(Tierspayant tierspayant) {
        this.tierspayants.add(tierspayant);
        tierspayant.setGroupetp(this);
        return this;
    }

    public GroupeTierspayant removeTierspayant(Tierspayant tierspayant) {
        this.tierspayants.remove(tierspayant);
        tierspayant.setGroupetp(null);
        return this;
    }

    public void setTierspayants(Set<Tierspayant> tierspayants) {
        this.tierspayants = tierspayants;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GroupeTierspayant)) {
            return false;
        }
        return id != null && id.equals(((GroupeTierspayant) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GroupeTierspayant{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", libelle='" + getLibelle() + "'" +
            ", phone='" + getPhone() + "'" +
            ", address='" + getAddress() + "'" +
            "}";
    }
}
