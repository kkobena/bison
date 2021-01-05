package com.kobe.nucleus.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import com.kobe.nucleus.domain.enumeration.TypeMagasin;
import com.kobe.nucleus.domain.enumeration.Status;

/**
 * A DTO for the {@link com.kobe.nucleus.domain.Magasin} entity.
 */
public class MagasinDTO implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

    @NotNull
    private TypeMagasin typeMagasin;

    private String nomCourt;

    @NotNull
    private String nomLong;

    private String addressePostal;

  
    private Status status;

    private String phone;

    private String mobile;

    private String commentaire;

    private String autreCommentaire;

    private String entete;

    private String compteContribuable;

    private String registreCommerce;

    private String registreImposition;

    private String centreImposition;

    private String numComptable;


    private Long magasinId;

    private String magasinNomCourt;

    private Long managerId;

    private String managerFirstName;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TypeMagasin getTypeMagasin() {
        return typeMagasin;
    }

    public void setTypeMagasin(TypeMagasin typeMagasin) {
        this.typeMagasin = typeMagasin;
    }

    public String getNomCourt() {
        return nomCourt;
    }

    public void setNomCourt(String nomCourt) {
        this.nomCourt = nomCourt;
    }

    public String getNomLong() {
        return nomLong;
    }

    public void setNomLong(String nomLong) {
        this.nomLong = nomLong;
    }

    public String getAddressePostal() {
        return addressePostal;
    }

    public void setAddressePostal(String addressePostal) {
        this.addressePostal = addressePostal;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getAutreCommentaire() {
        return autreCommentaire;
    }

    public void setAutreCommentaire(String autreCommentaire) {
        this.autreCommentaire = autreCommentaire;
    }

    public String getEntete() {
        return entete;
    }

    public void setEntete(String entete) {
        this.entete = entete;
    }

    public String getCompteContribuable() {
        return compteContribuable;
    }

    public void setCompteContribuable(String compteContribuable) {
        this.compteContribuable = compteContribuable;
    }

    public String getRegistreCommerce() {
        return registreCommerce;
    }

    public void setRegistreCommerce(String registreCommerce) {
        this.registreCommerce = registreCommerce;
    }

    public String getRegistreImposition() {
        return registreImposition;
    }

    public void setRegistreImposition(String registreImposition) {
        this.registreImposition = registreImposition;
    }

    public String getCentreImposition() {
        return centreImposition;
    }

    public void setCentreImposition(String centreImposition) {
        this.centreImposition = centreImposition;
    }

    public String getNumComptable() {
        return numComptable;
    }

    public void setNumComptable(String numComptable) {
        this.numComptable = numComptable;
    }

    public Long getMagasinId() {
        return magasinId;
    }

    public void setMagasinId(Long magasinId) {
        this.magasinId = magasinId;
    }

    public String getMagasinNomCourt() {
        return magasinNomCourt;
    }

    public void setMagasinNomCourt(String magasinNomCourt) {
        this.magasinNomCourt = magasinNomCourt;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long utilisateurId) {
        this.managerId = utilisateurId;
    }

    public String getManagerFirstName() {
        return managerFirstName;
    }

    public void setManagerFirstName(String utilisateurFirstName) {
        this.managerFirstName = utilisateurFirstName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MagasinDTO)) {
            return false;
        }

        return id != null && id.equals(((MagasinDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MagasinDTO{" +
            "id=" + getId() +
            ", typeMagasin='" + getTypeMagasin() + "'" +
            ", nomCourt='" + getNomCourt() + "'" +
            ", nomLong='" + getNomLong() + "'" +
            ", addressePostal='" + getAddressePostal() + "'" +
            ", status='" + getStatus() + "'" +
            ", phone='" + getPhone() + "'" +
            ", mobile='" + getMobile() + "'" +
            ", commentaire='" + getCommentaire() + "'" +
            ", autreCommentaire='" + getAutreCommentaire() + "'" +
            ", entete='" + getEntete() + "'" +
            ", compteContribuable='" + getCompteContribuable() + "'" +
            ", registreCommerce='" + getRegistreCommerce() + "'" +
            ", registreImposition='" + getRegistreImposition() + "'" +
            ", centreImposition='" + getCentreImposition() + "'" +
            ", numComptable='" + getNumComptable() + "'" +
            ", magasinId=" + getMagasinId() +
            ", magasinNomCourt='" + getMagasinNomCourt() + "'" +
            ", managerId=" + getManagerId() +
            ", managerFirstName='" + getManagerFirstName() + "'" +
            "}";
    }
}
