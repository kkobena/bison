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
 * A CategorieAyantDroit.
 */
@Entity
@Table(name = "categorie_ayant_droit")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CategorieAyantDroit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @NotNull
    @Column(name = "libelle", nullable = false, unique = true)
    private String libelle;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @OneToMany(mappedBy = "categorie")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<AyantDroit> ayantDroits = new HashSet<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public CategorieAyantDroit code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public CategorieAyantDroit libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Status getStatus() {
        return status;
    }

    public CategorieAyantDroit status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Set<AyantDroit> getAyantDroits() {
        return ayantDroits;
    }

    public CategorieAyantDroit ayantDroits(Set<AyantDroit> ayantDroits) {
        this.ayantDroits = ayantDroits;
        return this;
    }

    public CategorieAyantDroit addAyantDroit(AyantDroit ayantDroit) {
        this.ayantDroits.add(ayantDroit);
        ayantDroit.setCategorie(this);
        return this;
    }

    public CategorieAyantDroit removeAyantDroit(AyantDroit ayantDroit) {
        this.ayantDroits.remove(ayantDroit);
        ayantDroit.setCategorie(null);
        return this;
    }

    public void setAyantDroits(Set<AyantDroit> ayantDroits) {
        this.ayantDroits = ayantDroits;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CategorieAyantDroit)) {
            return false;
        }
        return id != null && id.equals(((CategorieAyantDroit) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CategorieAyantDroit{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", libelle='" + getLibelle() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
