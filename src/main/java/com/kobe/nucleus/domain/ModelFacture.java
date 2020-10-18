package com.kobe.nucleus.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.HashSet;
import java.util.Set;

/**
 * A ModelFacture.
 */
@Entity
@Table(name = "model_facture")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ModelFacture implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "libelle", nullable = false, unique = true)
    private String libelle;

    @NotNull
    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @NotNull
    @Column(name = "fichier", nullable = false, unique = true)
    private String fichier;

    @OneToMany(mappedBy = "modelFacture")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Tierspayant> tierspayants = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public ModelFacture libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getCode() {
        return code;
    }

    public ModelFacture code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFichier() {
        return fichier;
    }

    public ModelFacture fichier(String fichier) {
        this.fichier = fichier;
        return this;
    }

    public void setFichier(String fichier) {
        this.fichier = fichier;
    }

    public Set<Tierspayant> getTierspayants() {
        return tierspayants;
    }

    public ModelFacture tierspayants(Set<Tierspayant> tierspayants) {
        this.tierspayants = tierspayants;
        return this;
    }

    public ModelFacture addTierspayant(Tierspayant tierspayant) {
        this.tierspayants.add(tierspayant);
        tierspayant.setModelFacture(this);
        return this;
    }

    public ModelFacture removeTierspayant(Tierspayant tierspayant) {
        this.tierspayants.remove(tierspayant);
        tierspayant.setModelFacture(null);
        return this;
    }

    public void setTierspayants(Set<Tierspayant> tierspayants) {
        this.tierspayants = tierspayants;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ModelFacture)) {
            return false;
        }
        return id != null && id.equals(((ModelFacture) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ModelFacture{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", code='" + getCode() + "'" +
            ", fichier='" + getFichier() + "'" +
            "}";
    }
}
