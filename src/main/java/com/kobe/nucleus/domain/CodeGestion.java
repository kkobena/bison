package com.kobe.nucleus.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

import com.kobe.nucleus.domain.enumeration.Optimisation;

/**
 * A CodeGestion.
 */
@Entity
@Table(name = "code_gestion")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CodeGestion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "code", nullable = false)
    private String code;

    @NotNull
    @Column(name = "jourcouverture", nullable = false)
    private Integer jourcouverture;

    @NotNull
    @Column(name = "moishistorique", nullable = false)
    private Integer moishistorique;

    @NotNull
    @Column(name = "datebutoir", nullable = false)
    private Integer datebutoir;

    @NotNull
    @Column(name = "dateextrapolation", nullable = false)
    private Integer dateextrapolation;

    @NotNull
    @Column(name = "seuil_cmde", nullable = false)
    private Boolean seuilCmde;

    @NotNull
    @Column(name = "cofficientpmd", nullable = false)
    private Boolean cofficientpmd;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_optimisation")
    private Optimisation typeOptimisation;

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

    public CodeGestion code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getJourcouverture() {
        return jourcouverture;
    }

    public CodeGestion jourcouverture(Integer jourcouverture) {
        this.jourcouverture = jourcouverture;
        return this;
    }

    public void setJourcouverture(Integer jourcouverture) {
        this.jourcouverture = jourcouverture;
    }

    public Integer getMoishistorique() {
        return moishistorique;
    }

    public CodeGestion moishistorique(Integer moishistorique) {
        this.moishistorique = moishistorique;
        return this;
    }

    public void setMoishistorique(Integer moishistorique) {
        this.moishistorique = moishistorique;
    }

    public Integer getDatebutoir() {
        return datebutoir;
    }

    public CodeGestion datebutoir(Integer datebutoir) {
        this.datebutoir = datebutoir;
        return this;
    }

    public void setDatebutoir(Integer datebutoir) {
        this.datebutoir = datebutoir;
    }

    public Integer getDateextrapolation() {
        return dateextrapolation;
    }

    public CodeGestion dateextrapolation(Integer dateextrapolation) {
        this.dateextrapolation = dateextrapolation;
        return this;
    }

    public void setDateextrapolation(Integer dateextrapolation) {
        this.dateextrapolation = dateextrapolation;
    }

    public Boolean isSeuilCmde() {
        return seuilCmde;
    }

    public CodeGestion seuilCmde(Boolean seuilCmde) {
        this.seuilCmde = seuilCmde;
        return this;
    }

    public void setSeuilCmde(Boolean seuilCmde) {
        this.seuilCmde = seuilCmde;
    }

    public Boolean isCofficientpmd() {
        return cofficientpmd;
    }

    public CodeGestion cofficientpmd(Boolean cofficientpmd) {
        this.cofficientpmd = cofficientpmd;
        return this;
    }

    public void setCofficientpmd(Boolean cofficientpmd) {
        this.cofficientpmd = cofficientpmd;
    }

    public Optimisation getTypeOptimisation() {
        return typeOptimisation;
    }

    public CodeGestion typeOptimisation(Optimisation typeOptimisation) {
        this.typeOptimisation = typeOptimisation;
        return this;
    }

    public void setTypeOptimisation(Optimisation typeOptimisation) {
        this.typeOptimisation = typeOptimisation;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CodeGestion)) {
            return false;
        }
        return id != null && id.equals(((CodeGestion) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CodeGestion{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", jourcouverture=" + getJourcouverture() +
            ", moishistorique=" + getMoishistorique() +
            ", datebutoir=" + getDatebutoir() +
            ", dateextrapolation=" + getDateextrapolation() +
            ", seuilCmde='" + isSeuilCmde() + "'" +
            ", cofficientpmd='" + isCofficientpmd() + "'" +
            ", typeOptimisation='" + getTypeOptimisation() + "'" +
            "}";
    }
}
