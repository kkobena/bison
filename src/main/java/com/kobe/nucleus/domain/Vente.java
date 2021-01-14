package com.kobe.nucleus.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import com.kobe.nucleus.domain.enumeration.Status;

import com.kobe.nucleus.domain.enumeration.NatureVente;

import com.kobe.nucleus.domain.enumeration.TypeVente;

/**
 * A Vente.
 */
@Entity
@Table(name = "vente")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
//@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
public class Vente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @NotNull
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @Enumerated(EnumType.STRING)
    @Column(name = "nature_vente")
    private NatureVente natureVente;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_vente")
    private TypeVente typeVente;

    @Column(name = "ref_vente")
    private String refVente;

    @Column(name = "num_ticket")
    private String numTicket;

    @Column(name = "tauxremise")
    private Double tauxremise;

    @Column(name = "montant_vente")
    private Integer montantVente;

    @Column(name = "montant_tva")
    private Integer montantTva;

    @Column(name = "montant_net")
    private Integer montantNet;

    @Column(name = "montan_remise")
    private Integer montanRemise;

    @Column(name = "valeur_marge")
    private Integer valeurMarge;

    @Column(name = "avoir")
    private Boolean avoir;

    @Column(name = "montant_paye")
    private Integer montantPaye;

    @Column(name = "montant_restant")
    private Integer montantRestant;

    @Column(name = "montant_tp")
    private Integer montantTp;

    @Column(name = "montant_client")
    private Integer montantClient;

    @Column(name = "montant_verse")
    private Integer montantVerse;

    @Column(name = "montant_rendu")
    private Integer montantRendu;

    @Column(name = "ref_bon")
    private String refBon;

    @NotNull
    @Column(name = "avoid_amount", nullable = false)
    private Integer avoidAmount;

    @NotNull
    @Column(name = "cost_amount", nullable = false)
    private Double costAmount;

    @OneToMany(mappedBy = "vente")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<LignesVente> lignesVentes = new HashSet<>();

    @OneToMany(mappedBy = "vente")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<LignesVenteAssurence> lignesVenteAssurences = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "ventes", allowSetters = true)
    private Magasin magasin;

    @ManyToOne
    @JsonIgnoreProperties(value = "ventes", allowSetters = true)
    private User createdBy;

    @ManyToOne
    @JsonIgnoreProperties(value = "ventes", allowSetters = true)
    private Client assure;

    @ManyToOne
    @JsonIgnoreProperties(value = "ventes", allowSetters = true)
    private AyantDroit ayantDroit;

    @ManyToOne
    @JsonIgnoreProperties(value = "ventes", allowSetters = true)
    private Remise remise;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "ventes", allowSetters = true)
    private ModePaiement modePaiement;

    @ManyToOne
    @JsonIgnoreProperties(value = "ventes", allowSetters = true)
    private Medecin medecin;

  
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Vente createdAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public Vente updatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Status getStatus() {
        return status;
    }

    public Vente status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public NatureVente getNatureVente() {
        return natureVente;
    }

    public Vente natureVente(NatureVente natureVente) {
        this.natureVente = natureVente;
        return this;
    }

    public void setNatureVente(NatureVente natureVente) {
        this.natureVente = natureVente;
    }

    public TypeVente getTypeVente() {
        return typeVente;
    }

    public Vente typeVente(TypeVente typeVente) {
        this.typeVente = typeVente;
        return this;
    }

    public void setTypeVente(TypeVente typeVente) {
        this.typeVente = typeVente;
    }

    public String getRefVente() {
        return refVente;
    }

    public Vente refVente(String refVente) {
        this.refVente = refVente;
        return this;
    }

    public void setRefVente(String refVente) {
        this.refVente = refVente;
    }

    public String getNumTicket() {
        return numTicket;
    }

    public Vente numTicket(String numTicket) {
        this.numTicket = numTicket;
        return this;
    }

    public void setNumTicket(String numTicket) {
        this.numTicket = numTicket;
    }

    public Double getTauxremise() {
        return tauxremise;
    }

    public Vente tauxremise(Double tauxremise) {
        this.tauxremise = tauxremise;
        return this;
    }

    public void setTauxremise(Double tauxremise) {
        this.tauxremise = tauxremise;
    }

    public Integer getMontantVente() {
        return montantVente;
    }

    public Vente montantVente(Integer montantVente) {
        this.montantVente = montantVente;
        return this;
    }

    public void setMontantVente(Integer montantVente) {
        this.montantVente = montantVente;
    }

    public Integer getMontantTva() {
        return montantTva;
    }

    public Vente montantTva(Integer montantTva) {
        this.montantTva = montantTva;
        return this;
    }

    public void setMontantTva(Integer montantTva) {
        this.montantTva = montantTva;
    }

    public Integer getMontantNet() {
        return montantNet;
    }

    public Vente montantNet(Integer montantNet) {
        this.montantNet = montantNet;
        return this;
    }

    public void setMontantNet(Integer montantNet) {
        this.montantNet = montantNet;
    }

    public Integer getMontanRemise() {
        return montanRemise;
    }

    public Vente montanRemise(Integer montanRemise) {
        this.montanRemise = montanRemise;
        return this;
    }

    public void setMontanRemise(Integer montanRemise) {
        this.montanRemise = montanRemise;
    }

    public Integer getValeurMarge() {
        return valeurMarge;
    }

    public Vente valeurMarge(Integer valeurMarge) {
        this.valeurMarge = valeurMarge;
        return this;
    }

    public void setValeurMarge(Integer valeurMarge) {
        this.valeurMarge = valeurMarge;
    }

    public Boolean isAvoir() {
        return avoir;
    }

    public Vente avoir(Boolean avoir) {
        this.avoir = avoir;
        return this;
    }

    public void setAvoir(Boolean avoir) {
        this.avoir = avoir;
    }

    public Integer getMontantPaye() {
        return montantPaye;
    }

    public Vente montantPaye(Integer montantPaye) {
        this.montantPaye = montantPaye;
        return this;
    }

    public void setMontantPaye(Integer montantPaye) {
        this.montantPaye = montantPaye;
    }

    public Integer getMontantRestant() {
        return montantRestant;
    }

    public Vente montantRestant(Integer montantRestant) {
        this.montantRestant = montantRestant;
        return this;
    }

    public void setMontantRestant(Integer montantRestant) {
        this.montantRestant = montantRestant;
    }

    public Integer getMontantTp() {
        return montantTp;
    }

    public Vente montantTp(Integer montantTp) {
        this.montantTp = montantTp;
        return this;
    }

    public void setMontantTp(Integer montantTp) {
        this.montantTp = montantTp;
    }

    public Integer getMontantClient() {
        return montantClient;
    }

    public Vente montantClient(Integer montantClient) {
        this.montantClient = montantClient;
        return this;
    }

    public void setMontantClient(Integer montantClient) {
        this.montantClient = montantClient;
    }

    public Integer getMontantVerse() {
        return montantVerse;
    }

    public Vente montantVerse(Integer montantVerse) {
        this.montantVerse = montantVerse;
        return this;
    }

    public void setMontantVerse(Integer montantVerse) {
        this.montantVerse = montantVerse;
    }

    public Integer getMontantRendu() {
        return montantRendu;
    }

    public Vente montantRendu(Integer montantRendu) {
        this.montantRendu = montantRendu;
        return this;
    }

    public void setMontantRendu(Integer montantRendu) {
        this.montantRendu = montantRendu;
    }

    public String getRefBon() {
        return refBon;
    }

    public Vente refBon(String refBon) {
        this.refBon = refBon;
        return this;
    }

    public void setRefBon(String refBon) {
        this.refBon = refBon;
    }

    public Integer getAvoidAmount() {
        return avoidAmount;
    }

    public Vente avoidAmount(Integer avoidAmount) {
        this.avoidAmount = avoidAmount;
        return this;
    }

    public void setAvoidAmount(Integer avoidAmount) {
        this.avoidAmount = avoidAmount;
    }

    public Double getCostAmount() {
        return costAmount;
    }

    public Vente costAmount(Double costAmount) {
        this.costAmount = costAmount;
        return this;
    }

    public void setCostAmount(Double costAmount) {
        this.costAmount = costAmount;
    }

    public Set<LignesVente> getLignesVentes() {
        return lignesVentes;
    }

    public Vente lignesVentes(Set<LignesVente> lignesVentes) {
        this.lignesVentes = lignesVentes;
        return this;
    }

    public Vente addLignesVente(LignesVente lignesVente) {
        this.lignesVentes.add(lignesVente);
        lignesVente.setVente(this);
        return this;
    }

    public Vente removeLignesVente(LignesVente lignesVente) {
        this.lignesVentes.remove(lignesVente);
        lignesVente.setVente(null);
        return this;
    }

    public void setLignesVentes(Set<LignesVente> lignesVentes) {
        this.lignesVentes = lignesVentes;
    }

    public Set<LignesVenteAssurence> getLignesVenteAssurences() {
        return lignesVenteAssurences;
    }

    public Vente lignesVenteAssurences(Set<LignesVenteAssurence> lignesVenteAssurences) {
        this.lignesVenteAssurences = lignesVenteAssurences;
        return this;
    }

    public Vente addLignesVenteAssurence(LignesVenteAssurence lignesVenteAssurence) {
        this.lignesVenteAssurences.add(lignesVenteAssurence);
        lignesVenteAssurence.setVente(this);
        return this;
    }

    public Vente removeLignesVenteAssurence(LignesVenteAssurence lignesVenteAssurence) {
        this.lignesVenteAssurences.remove(lignesVenteAssurence);
        lignesVenteAssurence.setVente(null);
        return this;
    }

    public void setLignesVenteAssurences(Set<LignesVenteAssurence> lignesVenteAssurences) {
        this.lignesVenteAssurences = lignesVenteAssurences;
    }

    public Magasin getMagasin() {
        return magasin;
    }

    public Vente magasin(Magasin magasin) {
        this.magasin = magasin;
        return this;
    }

    public void setMagasin(Magasin magasin) {
        this.magasin = magasin;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public Vente createdBy(User utilisateur) {
        this.createdBy = utilisateur;
        return this;
    }

    public void setCreatedBy(User utilisateur) {
        this.createdBy = utilisateur;
    }

    public Client getAssure() {
        return assure;
    }

    public Vente assure(Client client) {
        this.assure = client;
        return this;
    }

    public void setAssure(Client client) {
        this.assure = client;
    }

    public AyantDroit getAyantDroit() {
        return ayantDroit;
    }

    public Vente ayantDroit(AyantDroit ayantDroit) {
        this.ayantDroit = ayantDroit;
        return this;
    }

    public void setAyantDroit(AyantDroit ayantDroit) {
        this.ayantDroit = ayantDroit;
    }

    public Remise getRemise() {
        return remise;
    }

    public Vente remise(Remise remise) {
        this.remise = remise;
        return this;
    }

    public void setRemise(Remise remise) {
        this.remise = remise;
    }

    public ModePaiement getModePaiement() {
        return modePaiement;
    }

    public Vente modePaiement(ModePaiement modePaiement) {
        this.modePaiement = modePaiement;
        return this;
    }

    public void setModePaiement(ModePaiement modePaiement) {
        this.modePaiement = modePaiement;
    }

    public Medecin getMedecin() {
        return medecin;
    }

    public Vente medecin(Medecin medecin) {
        this.medecin = medecin;
        return this;
    }

    public void setMedecin(Medecin medecin) {
        this.medecin = medecin;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Vente)) {
            return false;
        }
        return id != null && id.equals(((Vente) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Vente{" +
            "id=" + getId() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
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
            ", costAmount=" + getCostAmount() +
            "}";
    }
}
