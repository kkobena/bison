package com.kobe.nucleus.service.dto;

import java.time.Instant;
import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import com.kobe.nucleus.domain.enumeration.Status;
import com.kobe.nucleus.domain.enumeration.NatureVente;
import com.kobe.nucleus.domain.enumeration.TypeVente;

/**
 * A DTO for the {@link com.kobe.nucleus.domain.Vente} entity.
 */
public class VenteDTO implements Serializable {
    
    private Long id;

    @NotNull
    private Instant createdAt;

    @NotNull
    private Instant updatedAt;

    private LocalDate dateMVT;

    @NotNull
    private Status status;

    private NatureVente natureVente;

    private TypeVente typeVente;

    private String refVente;

    private String numTicket;

    private Double tauxremise;

    private Integer montantVente;

    private Integer montantTva;

    private Integer montantNet;

    private Integer montanRemise;

    private Integer valeurMarge;

    private Boolean avoir;

    private Integer montantPaye;

    private Integer montantRestant;

    private Integer montantTp;

    private Integer montantClient;

    private Integer montantVerse;

    private Integer montantRendu;

    private String refBon;

    @NotNull
    private Integer avoidAmount;


    private Long magasinId;

    private String magasinNomCourt;

    private Long createdById;

    private String createdByFirstName;

    private Long assureId;

    private String assureFirstName;

    private Long ayantDroitId;

    private String ayantDroitFirstName;

    private Long remiseId;

    private String remiseValeur;

    private Long modePaiementId;

    private String modePaiementLibelle;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getCreatedAt() {
        return createdAt;
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

    public LocalDate getDateMVT() {
        return dateMVT;
    }

    public void setDateMVT(LocalDate dateMVT) {
        this.dateMVT = dateMVT;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public NatureVente getNatureVente() {
        return natureVente;
    }

    public void setNatureVente(NatureVente natureVente) {
        this.natureVente = natureVente;
    }

    public TypeVente getTypeVente() {
        return typeVente;
    }

    public void setTypeVente(TypeVente typeVente) {
        this.typeVente = typeVente;
    }

    public String getRefVente() {
        return refVente;
    }

    public void setRefVente(String refVente) {
        this.refVente = refVente;
    }

    public String getNumTicket() {
        return numTicket;
    }

    public void setNumTicket(String numTicket) {
        this.numTicket = numTicket;
    }

    public Double getTauxremise() {
        return tauxremise;
    }

    public void setTauxremise(Double tauxremise) {
        this.tauxremise = tauxremise;
    }

    public Integer getMontantVente() {
        return montantVente;
    }

    public void setMontantVente(Integer montantVente) {
        this.montantVente = montantVente;
    }

    public Integer getMontantTva() {
        return montantTva;
    }

    public void setMontantTva(Integer montantTva) {
        this.montantTva = montantTva;
    }

    public Integer getMontantNet() {
        return montantNet;
    }

    public void setMontantNet(Integer montantNet) {
        this.montantNet = montantNet;
    }

    public Integer getMontanRemise() {
        return montanRemise;
    }

    public void setMontanRemise(Integer montanRemise) {
        this.montanRemise = montanRemise;
    }

    public Integer getValeurMarge() {
        return valeurMarge;
    }

    public void setValeurMarge(Integer valeurMarge) {
        this.valeurMarge = valeurMarge;
    }

    public Boolean isAvoir() {
        return avoir;
    }

    public void setAvoir(Boolean avoir) {
        this.avoir = avoir;
    }

    public Integer getMontantPaye() {
        return montantPaye;
    }

    public void setMontantPaye(Integer montantPaye) {
        this.montantPaye = montantPaye;
    }

    public Integer getMontantRestant() {
        return montantRestant;
    }

    public void setMontantRestant(Integer montantRestant) {
        this.montantRestant = montantRestant;
    }

    public Integer getMontantTp() {
        return montantTp;
    }

    public void setMontantTp(Integer montantTp) {
        this.montantTp = montantTp;
    }

    public Integer getMontantClient() {
        return montantClient;
    }

    public void setMontantClient(Integer montantClient) {
        this.montantClient = montantClient;
    }

    public Integer getMontantVerse() {
        return montantVerse;
    }

    public void setMontantVerse(Integer montantVerse) {
        this.montantVerse = montantVerse;
    }

    public Integer getMontantRendu() {
        return montantRendu;
    }

    public void setMontantRendu(Integer montantRendu) {
        this.montantRendu = montantRendu;
    }

    public String getRefBon() {
        return refBon;
    }

    public void setRefBon(String refBon) {
        this.refBon = refBon;
    }

    public Integer getAvoidAmount() {
        return avoidAmount;
    }

    public void setAvoidAmount(Integer avoidAmount) {
        this.avoidAmount = avoidAmount;
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

    public Long getCreatedById() {
        return createdById;
    }

    public void setCreatedById(Long utilisateurId) {
        this.createdById = utilisateurId;
    }

    public String getCreatedByFirstName() {
        return createdByFirstName;
    }

    public void setCreatedByFirstName(String utilisateurFirstName) {
        this.createdByFirstName = utilisateurFirstName;
    }

    public Long getAssureId() {
        return assureId;
    }

    public void setAssureId(Long clientId) {
        this.assureId = clientId;
    }

    public String getAssureFirstName() {
        return assureFirstName;
    }

    public void setAssureFirstName(String clientFirstName) {
        this.assureFirstName = clientFirstName;
    }

    public Long getAyantDroitId() {
        return ayantDroitId;
    }

    public void setAyantDroitId(Long ayantDroitId) {
        this.ayantDroitId = ayantDroitId;
    }

    public String getAyantDroitFirstName() {
        return ayantDroitFirstName;
    }

    public void setAyantDroitFirstName(String ayantDroitFirstName) {
        this.ayantDroitFirstName = ayantDroitFirstName;
    }

    public Long getRemiseId() {
        return remiseId;
    }

    public void setRemiseId(Long remiseId) {
        this.remiseId = remiseId;
    }

    public String getRemiseValeur() {
        return remiseValeur;
    }

    public void setRemiseValeur(String remiseValeur) {
        this.remiseValeur = remiseValeur;
    }

    public Long getModePaiementId() {
        return modePaiementId;
    }

    public void setModePaiementId(Long modePaiementId) {
        this.modePaiementId = modePaiementId;
    }

    public String getModePaiementLibelle() {
        return modePaiementLibelle;
    }

    public void setModePaiementLibelle(String modePaiementLibelle) {
        this.modePaiementLibelle = modePaiementLibelle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VenteDTO)) {
            return false;
        }

        return id != null && id.equals(((VenteDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "VenteDTO{" +
            "id=" + getId() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", dateMVT='" + getDateMVT() + "'" +
            ", status='" + getStatus() + "'" +
            ", natureVente='" + getNatureVente() + "'" +
            ", typeVente='" + getTypeVente() + "'" +
            ", refVente='" + getRefVente() + "'" +
            ", numTicket='" + getNumTicket() + "'" +
            ", tauxremise=" + getTauxremise() +
            ", montantVente=" + getMontantVente() +
            ", montantTva=" + getMontantTva() +
            ", montantNet=" + getMontantNet() +
            ", montanRemise=" + getMontanRemise() +
            ", valeurMarge=" + getValeurMarge() +
            ", avoir='" + isAvoir() + "'" +
            ", montantPaye=" + getMontantPaye() +
            ", montantRestant=" + getMontantRestant() +
            ", montantTp=" + getMontantTp() +
            ", montantClient=" + getMontantClient() +
            ", montantVerse=" + getMontantVerse() +
            ", montantRendu=" + getMontantRendu() +
            ", refBon='" + getRefBon() + "'" +
            ", avoidAmount=" + getAvoidAmount() +
            ", magasinId=" + getMagasinId() +
            ", magasinNomCourt='" + getMagasinNomCourt() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByFirstName='" + getCreatedByFirstName() + "'" +
            ", assureId=" + getAssureId() +
            ", assureFirstName='" + getAssureFirstName() + "'" +
            ", ayantDroitId=" + getAyantDroitId() +
            ", ayantDroitFirstName='" + getAyantDroitFirstName() + "'" +
            ", remiseId=" + getRemiseId() +
            ", remiseValeur='" + getRemiseValeur() + "'" +
            ", modePaiementId=" + getModePaiementId() +
            ", modePaiementLibelle='" + getModePaiementLibelle() + "'" +
            "}";
    }
}
