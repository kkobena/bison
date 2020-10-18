package com.kobe.nucleus.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import com.kobe.nucleus.domain.enumeration.TypeTierspayant;
import com.kobe.nucleus.domain.enumeration.Status;

/**
 * A DTO for the {@link com.kobe.nucleus.domain.Tierspayant} entity.
 */
public class TierspayantDTO implements Serializable {
  
	private static final long serialVersionUID = 1L;


	private Long id;

  
    private Instant createdAt;

    
    private Instant updatedAt;

    private String code;

    private String libelCourt;

    private String libelLong;

    private Integer plafond;

    private Boolean typePlafond;

    private Integer consoJournaliere;

    private Integer consoMensuelle;

    private TypeTierspayant typeTp;

    private String codeComptable;

    private Integer nbreBordereaux;

    private String registrecommerce;

 
    private Status status;

 
    private String phone;

   
    private String mobile;

   
    private Boolean enable;


    private String address;


    private Integer montantMaxFacture;


    private Integer remiseForfetaire;


    private Long groupetpId;

    private String groupetpLibelle;

    private Long risqueId;

    private String risqueLibelle;

    private Long modelFactureId;

    private String modelFactureLibelle;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public TierspayantDTO nbreBordereaux(int nbreBordereaux) {
        this.nbreBordereaux = nbreBordereaux;
        return this;
    }
    public TierspayantDTO montantMaxFacture(int montantMaxFacture) {
        this.montantMaxFacture = montantMaxFacture;
        return this;
    }
    public Instant getCreatedAt() {
        return createdAt;
    }
    public TierspayantDTO status(Status status) {
        this.status = status;
        return this;
    }
    public TierspayantDTO enable(Boolean enable) {
        this.enable = enable;
        return this;
    }
    public TierspayantDTO code(String code) {
        this.code = code;
        return this;
    }
    public TierspayantDTO createdAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public TierspayantDTO updatedAt(Instant updatedAt) {
    	  this.updatedAt = updatedAt;
        return this;
    }
    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelCourt() {
        return libelCourt;
    }

    public void setLibelCourt(String libelCourt) {
        this.libelCourt = libelCourt;
    }

    public String getLibelLong() {
        return libelLong;
    }

    public void setLibelLong(String libelLong) {
        this.libelLong = libelLong;
    }

    public Integer getPlafond() {
        return plafond;
    }

    public void setPlafond(Integer plafond) {
        this.plafond = plafond;
    }

    public Boolean isTypePlafond() {
        return typePlafond;
    }

    public void setTypePlafond(Boolean typePlafond) {
        this.typePlafond = typePlafond;
    }

    public Integer getConsoJournaliere() {
        return consoJournaliere;
    }

    public void setConsoJournaliere(Integer consoJournaliere) {
        this.consoJournaliere = consoJournaliere;
    }

    public Integer getConsoMensuelle() {
        return consoMensuelle;
    }

    public void setConsoMensuelle(Integer consoMensuelle) {
        this.consoMensuelle = consoMensuelle;
    }

    public TypeTierspayant getTypeTp() {
        return typeTp;
    }

    public void setTypeTp(TypeTierspayant typeTp) {
        this.typeTp = typeTp;
    }

    public String getCodeComptable() {
        return codeComptable;
    }

    public void setCodeComptable(String codeComptable) {
        this.codeComptable = codeComptable;
    }

    public Integer getNbreBordereaux() {
        return nbreBordereaux;
    }

    public void setNbreBordereaux(Integer nbreBordereaux) {
        this.nbreBordereaux = nbreBordereaux;
    }

    public String getRegistrecommerce() {
        return registrecommerce;
    }

    public void setRegistrecommerce(String registrecommerce) {
        this.registrecommerce = registrecommerce;
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

    public Boolean isEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getMontantMaxFacture() {
        return montantMaxFacture;
    }

    public void setMontantMaxFacture(Integer montantMaxFacture) {
        this.montantMaxFacture = montantMaxFacture;
    }

    public Integer getRemiseForfetaire() {
        return remiseForfetaire;
    }

    public void setRemiseForfetaire(Integer remiseForfetaire) {
        this.remiseForfetaire = remiseForfetaire;
    }

    public Long getGroupetpId() {
        return groupetpId;
    }

    public void setGroupetpId(Long groupeTierspayantId) {
        this.groupetpId = groupeTierspayantId;
    }

    public String getGroupetpLibelle() {
        return groupetpLibelle;
    }

    public void setGroupetpLibelle(String groupeTierspayantLibelle) {
        this.groupetpLibelle = groupeTierspayantLibelle;
    }

    public Long getRisqueId() {
        return risqueId;
    }

    public void setRisqueId(Long risqueId) {
        this.risqueId = risqueId;
    }

    public String getRisqueLibelle() {
        return risqueLibelle;
    }

    public void setRisqueLibelle(String risqueLibelle) {
        this.risqueLibelle = risqueLibelle;
    }

    public Long getModelFactureId() {
        return modelFactureId;
    }

    public void setModelFactureId(Long modelFactureId) {
        this.modelFactureId = modelFactureId;
    }

    public String getModelFactureLibelle() {
        return modelFactureLibelle;
    }

    public void setModelFactureLibelle(String modelFactureLibelle) {
        this.modelFactureLibelle = modelFactureLibelle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TierspayantDTO)) {
            return false;
        }

        return id != null && id.equals(((TierspayantDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TierspayantDTO{" +
            "id=" + getId() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", code='" + getCode() + "'" +
            ", libelCourt='" + getLibelCourt() + "'" +
            ", libelLong='" + getLibelLong() + "'" +
            ", plafond=" + getPlafond() +
            ", typePlafond='" + isTypePlafond() + "'" +
            ", consoJournaliere=" + getConsoJournaliere() +
            ", consoMensuelle=" + getConsoMensuelle() +
            ", typeTp='" + getTypeTp() + "'" +
            ", codeComptable='" + getCodeComptable() + "'" +
            ", nbreBordereaux=" + getNbreBordereaux() +
            ", registrecommerce='" + getRegistrecommerce() + "'" +
            ", status='" + getStatus() + "'" +
            ", phone='" + getPhone() + "'" +
            ", mobile='" + getMobile() + "'" +
            ", enable='" + isEnable() + "'" +
            ", address='" + getAddress() + "'" +
            ", montantMaxFacture=" + getMontantMaxFacture() +
            ", remiseForfetaire=" + getRemiseForfetaire() +
            ", groupetpId=" + getGroupetpId() +
            ", groupetpLibelle='" + getGroupetpLibelle() + "'" +
            ", risqueId=" + getRisqueId() +
            ", risqueLibelle='" + getRisqueLibelle() + "'" +
            ", modelFactureId=" + getModelFactureId() +
            ", modelFactureLibelle='" + getModelFactureLibelle() + "'" +
            "}";
    }
}
