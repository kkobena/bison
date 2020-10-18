package com.kobe.nucleus.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.kobe.nucleus.domain.Banque} entity.
 */
public class BanqueDTO implements Serializable {
    
    private Long id;

    private String libelle;

    private String refPaiement;

    private String lieux;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getRefPaiement() {
        return refPaiement;
    }

    public void setRefPaiement(String refPaiement) {
        this.refPaiement = refPaiement;
    }

    public String getLieux() {
        return lieux;
    }

    public void setLieux(String lieux) {
        this.lieux = lieux;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BanqueDTO)) {
            return false;
        }

        return id != null && id.equals(((BanqueDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BanqueDTO{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", refPaiement='" + getRefPaiement() + "'" +
            ", lieux='" + getLieux() + "'" +
            "}";
    }
}
