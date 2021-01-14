package com.kobe.nucleus.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.kobe.nucleus.domain.enumeration.Status;

/**
 * A Remise.
 */
@Entity
@Table(name = "remise")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Remise implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "valeur")
    private String valeur;

    @NotNull
    @Column(name = "remise_value", nullable = false)
    private Float remiseValue;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @OneToMany(mappedBy = "remise")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Vente> ventes = new HashSet<>();



 
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValeur() {
        return valeur;
    }

    public Remise valeur(String valeur) {
        this.valeur = valeur;
        return this;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    public Float getRemiseValue() {
        return remiseValue;
    }

    public Remise remiseValue(Float remiseValue) {
        this.remiseValue = remiseValue;
        return this;
    }

    public void setRemiseValue(Float remiseValue) {
        this.remiseValue = remiseValue;
    }

    public Status getStatus() {
        return status;
    }

    public Remise status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Set<Vente> getVentes() {
        return ventes;
    }

    public Remise ventes(Set<Vente> ventes) {
        this.ventes = ventes;
        return this;
    }

    public Remise addVente(Vente vente) {
        this.ventes.add(vente);
        vente.setRemise(this);
        return this;
    }

    public Remise removeVente(Vente vente) {
        this.ventes.remove(vente);
        vente.setRemise(null);
        return this;
    }

    public void setVentes(Set<Vente> ventes) {
        this.ventes = ventes;
    }

   

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Remise)) {
            return false;
        }
        return id != null && id.equals(((Remise) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Remise{" +
            "id=" + getId() +
            ", valeur='" + getValeur() + "'" +
            ", remiseValue=" + getRemiseValue() +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
