package com.kobe.nucleus.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.kobe.nucleus.domain.Tva} entity.
 */
public class TvaDTO implements Serializable {
    
    private Long id;

    @NotNull
    private Integer taux;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTaux() {
        return taux;
    }

    public void setTaux(Integer taux) {
        this.taux = taux;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TvaDTO)) {
            return false;
        }

        return id != null && id.equals(((TvaDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TvaDTO{" +
            "id=" + getId() +
            ", taux=" + getTaux() +
            "}";
    }
}
