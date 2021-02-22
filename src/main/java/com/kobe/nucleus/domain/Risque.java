package com.kobe.nucleus.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.kobe.nucleus.domain.enumeration.Status;

/**
 * A Risque.
 */
@Entity
@Table(name = "risque")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Risque implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "code")
    private String code;

    @NotNull
    @Column(name = "libelle", nullable = false, unique = true)
    private String libelle;



    @OneToMany(mappedBy = "risque")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Tierspayant> tierspayants = new HashSet<>();

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "risques", allowSetters = true)
    private TypeRisque typerisque;

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

    public Risque code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public Risque libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }



    public Set<Tierspayant> getTierspayants() {
        return tierspayants;
    }

    public Risque tierspayants(Set<Tierspayant> tierspayants) {
        this.tierspayants = tierspayants;
        return this;
    }

    public Risque addTierspayant(Tierspayant tierspayant) {
        this.tierspayants.add(tierspayant);
        tierspayant.setRisque(this);
        return this;
    }

    public Risque removeTierspayant(Tierspayant tierspayant) {
        this.tierspayants.remove(tierspayant);
        tierspayant.setRisque(null);
        return this;
    }

    public void setTierspayants(Set<Tierspayant> tierspayants) {
        this.tierspayants = tierspayants;
    }

    public TypeRisque getTyperisque() {
        return typerisque;
    }

    public Risque typerisque(TypeRisque typeRisque) {
        this.typerisque = typeRisque;
        return this;
    }

    public void setTyperisque(TypeRisque typeRisque) {
        this.typerisque = typeRisque;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Risque)) {
            return false;
        }
        return id != null && id.equals(((Risque) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Risque{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", libelle='" + getLibelle() + "'" +
          
            "}";
    }
}
