package com.kobe.nucleus.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Banque.
 */
@Entity
@Table(name = "banque")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Banque implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "libelle")
    private String libelle;

    @Column(name = "ref_paiement")
    private String refPaiement;

    @Column(name = "lieux")
    private String lieux;

    @OneToMany(mappedBy = "banque")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Paiement> paiements = new HashSet<>();

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

    public Banque libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getRefPaiement() {
        return refPaiement;
    }

    public Banque refPaiement(String refPaiement) {
        this.refPaiement = refPaiement;
        return this;
    }

    public void setRefPaiement(String refPaiement) {
        this.refPaiement = refPaiement;
    }

    public String getLieux() {
        return lieux;
    }

    public Banque lieux(String lieux) {
        this.lieux = lieux;
        return this;
    }

    public void setLieux(String lieux) {
        this.lieux = lieux;
    }

    public Set<Paiement> getPaiements() {
        return paiements;
    }

    public Banque paiements(Set<Paiement> paiements) {
        this.paiements = paiements;
        return this;
    }

    public Banque addPaiement(Paiement paiement) {
        this.paiements.add(paiement);
        paiement.setBanque(this);
        return this;
    }

    public Banque removePaiement(Paiement paiement) {
        this.paiements.remove(paiement);
        paiement.setBanque(null);
        return this;
    }

    public void setPaiements(Set<Paiement> paiements) {
        this.paiements = paiements;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Banque)) {
            return false;
        }
        return id != null && id.equals(((Banque) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Banque{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", refPaiement='" + getRefPaiement() + "'" +
            ", lieux='" + getLieux() + "'" +
            "}";
    }
}
