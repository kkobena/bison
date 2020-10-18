package com.kobe.nucleus.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

import com.kobe.nucleus.domain.enumeration.CatMvtCaisse;

import com.kobe.nucleus.domain.enumeration.Status;

/**
 * A TypeMvtCaisse.
 */
@Entity
@Table(name = "type_mvt_caisse")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TypeMvtCaisse implements Serializable {

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
    @Enumerated(EnumType.STRING)
    @Column(name = "categorie", nullable = false)
    private CatMvtCaisse categorie;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public TypeMvtCaisse libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getCode() {
        return code;
    }

    public TypeMvtCaisse code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public CatMvtCaisse getCategorie() {
        return categorie;
    }

    public TypeMvtCaisse categorie(CatMvtCaisse categorie) {
        this.categorie = categorie;
        return this;
    }

    public void setCategorie(CatMvtCaisse categorie) {
        this.categorie = categorie;
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TypeMvtCaisse)) {
            return false;
        }
        return id != null && id.equals(((TypeMvtCaisse) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TypeMvtCaisse{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", code='" + getCode() + "'" +
            ", categorie='" + getCategorie() + "'" +

            "}";
    }
}
