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
 * A Motif.
 */
@Entity
@Table(name = "motif")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Motif implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "libelle", nullable = false, unique = true)
    private String libelle;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @OneToMany(mappedBy = "motif")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<RetourFournisseur> retourFournisseurs = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public Motif libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Status getStatus() {
        return status;
    }

    public Motif status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Set<RetourFournisseur> getRetourFournisseurs() {
        return retourFournisseurs;
    }

    public Motif retourFournisseurs(Set<RetourFournisseur> retourFournisseurs) {
        this.retourFournisseurs = retourFournisseurs;
        return this;
    }

    public Motif addRetourFournisseur(RetourFournisseur retourFournisseur) {
        this.retourFournisseurs.add(retourFournisseur);
        retourFournisseur.setMotif(this);
        return this;
    }

    public Motif removeRetourFournisseur(RetourFournisseur retourFournisseur) {
        this.retourFournisseurs.remove(retourFournisseur);
        retourFournisseur.setMotif(null);
        return this;
    }

    public void setRetourFournisseurs(Set<RetourFournisseur> retourFournisseurs) {
        this.retourFournisseurs = retourFournisseurs;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Motif)) {
            return false;
        }
        return id != null && id.equals(((Motif) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Motif{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
