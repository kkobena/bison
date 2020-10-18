package com.kobe.nucleus.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import com.kobe.nucleus.domain.enumeration.Optimisation;

/**
 * A DTO for the {@link com.kobe.nucleus.domain.CodeGestion} entity.
 */
public class CodeGestionDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String code;

    @NotNull
    private Integer jourcouverture;

    @NotNull
    private Integer moishistorique;

    @NotNull
    private Integer datebutoir;

    @NotNull
    private Integer dateextrapolation;

    @NotNull
    private Boolean seuilCmde;

    @NotNull
    private Boolean cofficientpmd;

    private Optimisation typeOptimisation;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getJourcouverture() {
        return jourcouverture;
    }

    public void setJourcouverture(Integer jourcouverture) {
        this.jourcouverture = jourcouverture;
    }

    public Integer getMoishistorique() {
        return moishistorique;
    }

    public void setMoishistorique(Integer moishistorique) {
        this.moishistorique = moishistorique;
    }

    public Integer getDatebutoir() {
        return datebutoir;
    }

    public void setDatebutoir(Integer datebutoir) {
        this.datebutoir = datebutoir;
    }

    public Integer getDateextrapolation() {
        return dateextrapolation;
    }

    public void setDateextrapolation(Integer dateextrapolation) {
        this.dateextrapolation = dateextrapolation;
    }

    public Boolean isSeuilCmde() {
        return seuilCmde;
    }

    public void setSeuilCmde(Boolean seuilCmde) {
        this.seuilCmde = seuilCmde;
    }

    public Boolean isCofficientpmd() {
        return cofficientpmd;
    }

    public void setCofficientpmd(Boolean cofficientpmd) {
        this.cofficientpmd = cofficientpmd;
    }

    public Optimisation getTypeOptimisation() {
        return typeOptimisation;
    }

    public void setTypeOptimisation(Optimisation typeOptimisation) {
        this.typeOptimisation = typeOptimisation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CodeGestionDTO)) {
            return false;
        }

        return id != null && id.equals(((CodeGestionDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CodeGestionDTO{" +
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
