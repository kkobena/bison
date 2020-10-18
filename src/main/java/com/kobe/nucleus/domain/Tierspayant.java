package com.kobe.nucleus.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import com.kobe.nucleus.domain.enumeration.TypeTierspayant;

import com.kobe.nucleus.domain.enumeration.Status;

/**
 * A Tierspayant.
 */
@Entity
@Table(name = "tierspayant")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Tierspayant implements Serializable {

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

    @Column(name = "code")
    private String code;

    @Column(name = "libel_court")
    private String libelCourt;

    @Column(name = "libel_long")
    private String libelLong;

    @Column(name = "plafond")
    private Integer plafond;

    @Column(name = "type_plafond")
    private Boolean typePlafond;

    @Column(name = "conso_journaliere")
    private Integer consoJournaliere;

    @Column(name = "conso_mensuelle")
    private Integer consoMensuelle;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_tp")
    private TypeTierspayant typeTp;

    @Column(name = "code_comptable")
    private String codeComptable;

    @Column(name = "nbre_bordereaux")
    private Integer nbreBordereaux;

    @Column(name = "registrecommerce")
    private String registrecommerce;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @Size(max = 20)
    @Column(name = "phone", length = 20)
    private String phone;

    @Size(max = 20)
    @Column(name = "mobile", length = 20)
    private String mobile;

    @NotNull
    @Column(name = "enable", nullable = false)
    private Boolean enable;

    @Size(max = 100)
    @Column(name = "address", length = 100)
    private String address;

    @Min(value = 0)
    @Column(name = "montant_max_facture")
    private Integer montantMaxFacture;

    @NotNull
    @Min(value = 0)
    @Column(name = "remise_forfetaire", nullable = false)
    private Integer remiseForfetaire;

    @OneToMany(mappedBy = "tierpayant")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Facture> factures = new HashSet<>();

    @OneToMany(mappedBy = "tierspayant")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<CompteClient> compteClients = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "tierspayants", allowSetters = true)
    private GroupeTierspayant groupetp;

    @ManyToOne
    @JsonIgnoreProperties(value = "tierspayants", allowSetters = true)
    private Risque risque;

    @ManyToOne
    @JsonIgnoreProperties(value = "tierspayants", allowSetters = true)
    private ModelFacture modelFacture;

   
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Tierspayant createdAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public Tierspayant updatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCode() {
        return code;
    }

    public Tierspayant code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelCourt() {
        return libelCourt;
    }

    public Tierspayant libelCourt(String libelCourt) {
        this.libelCourt = libelCourt;
        return this;
    }

    public void setLibelCourt(String libelCourt) {
        this.libelCourt = libelCourt;
    }

    public String getLibelLong() {
        return libelLong;
    }

    public Tierspayant libelLong(String libelLong) {
        this.libelLong = libelLong;
        return this;
    }

    public void setLibelLong(String libelLong) {
        this.libelLong = libelLong;
    }

    public Integer getPlafond() {
        return plafond;
    }

    public Tierspayant plafond(Integer plafond) {
        this.plafond = plafond;
        return this;
    }

    public void setPlafond(Integer plafond) {
        this.plafond = plafond;
    }

    public Boolean isTypePlafond() {
        return typePlafond;
    }

    public Tierspayant typePlafond(Boolean typePlafond) {
        this.typePlafond = typePlafond;
        return this;
    }

    public void setTypePlafond(Boolean typePlafond) {
        this.typePlafond = typePlafond;
    }

    public Integer getConsoJournaliere() {
        return consoJournaliere;
    }

    public Tierspayant consoJournaliere(Integer consoJournaliere) {
        this.consoJournaliere = consoJournaliere;
        return this;
    }

    public void setConsoJournaliere(Integer consoJournaliere) {
        this.consoJournaliere = consoJournaliere;
    }

    public Integer getConsoMensuelle() {
        return consoMensuelle;
    }

    public Tierspayant consoMensuelle(Integer consoMensuelle) {
        this.consoMensuelle = consoMensuelle;
        return this;
    }

    public void setConsoMensuelle(Integer consoMensuelle) {
        this.consoMensuelle = consoMensuelle;
    }

    public TypeTierspayant getTypeTp() {
        return typeTp;
    }

    public Tierspayant typeTp(TypeTierspayant typeTp) {
        this.typeTp = typeTp;
        return this;
    }

    public void setTypeTp(TypeTierspayant typeTp) {
        this.typeTp = typeTp;
    }

    public String getCodeComptable() {
        return codeComptable;
    }

    public Tierspayant codeComptable(String codeComptable) {
        this.codeComptable = codeComptable;
        return this;
    }

    public void setCodeComptable(String codeComptable) {
        this.codeComptable = codeComptable;
    }

    public Integer getNbreBordereaux() {
        return nbreBordereaux;
    }

    public Tierspayant nbreBordereaux(Integer nbreBordereaux) {
        this.nbreBordereaux = nbreBordereaux;
        return this;
    }

    public void setNbreBordereaux(Integer nbreBordereaux) {
        this.nbreBordereaux = nbreBordereaux;
    }

    public String getRegistrecommerce() {
        return registrecommerce;
    }

    public Tierspayant registrecommerce(String registrecommerce) {
        this.registrecommerce = registrecommerce;
        return this;
    }

    public void setRegistrecommerce(String registrecommerce) {
        this.registrecommerce = registrecommerce;
    }

    public Status getStatus() {
        return status;
    }

    public Tierspayant status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public Tierspayant phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public Tierspayant mobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Boolean isEnable() {
        return enable;
    }

    public Tierspayant enable(Boolean enable) {
        this.enable = enable;
        return this;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public String getAddress() {
        return address;
    }

    public Tierspayant address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getMontantMaxFacture() {
        return montantMaxFacture;
    }

    public Tierspayant montantMaxFacture(Integer montantMaxFacture) {
        this.montantMaxFacture = montantMaxFacture;
        return this;
    }

    public void setMontantMaxFacture(Integer montantMaxFacture) {
        this.montantMaxFacture = montantMaxFacture;
    }

    public Integer getRemiseForfetaire() {
        return remiseForfetaire;
    }

    public Tierspayant remiseForfetaire(Integer remiseForfetaire) {
        this.remiseForfetaire = remiseForfetaire;
        return this;
    }

    public void setRemiseForfetaire(Integer remiseForfetaire) {
        this.remiseForfetaire = remiseForfetaire;
    }

    public Set<Facture> getFactures() {
        return factures;
    }

    public Tierspayant factures(Set<Facture> factures) {
        this.factures = factures;
        return this;
    }

    public Tierspayant addFacture(Facture facture) {
        this.factures.add(facture);
        facture.setTierpayant(this);
        return this;
    }

    public Tierspayant removeFacture(Facture facture) {
        this.factures.remove(facture);
        facture.setTierpayant(null);
        return this;
    }

    public void setFactures(Set<Facture> factures) {
        this.factures = factures;
    }

    public Set<CompteClient> getCompteClients() {
        return compteClients;
    }

    public Tierspayant compteClients(Set<CompteClient> compteClients) {
        this.compteClients = compteClients;
        return this;
    }

    public Tierspayant addCompteClient(CompteClient compteClient) {
        this.compteClients.add(compteClient);
        compteClient.setTierspayant(this);
        return this;
    }

    public Tierspayant removeCompteClient(CompteClient compteClient) {
        this.compteClients.remove(compteClient);
        compteClient.setTierspayant(null);
        return this;
    }

    public void setCompteClients(Set<CompteClient> compteClients) {
        this.compteClients = compteClients;
    }

    public GroupeTierspayant getGroupetp() {
        return groupetp;
    }

    public Tierspayant groupetp(GroupeTierspayant groupeTierspayant) {
        this.groupetp = groupeTierspayant;
        return this;
    }

    public void setGroupetp(GroupeTierspayant groupeTierspayant) {
        this.groupetp = groupeTierspayant;
    }

    public Risque getRisque() {
        return risque;
    }

    public Tierspayant risque(Risque risque) {
        this.risque = risque;
        return this;
    }

    public void setRisque(Risque risque) {
        this.risque = risque;
    }

    public ModelFacture getModelFacture() {
        return modelFacture;
    }

    public Tierspayant modelFacture(ModelFacture modelFacture) {
        this.modelFacture = modelFacture;
        return this;
    }

    public void setModelFacture(ModelFacture modelFacture) {
        this.modelFacture = modelFacture;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Tierspayant)) {
            return false;
        }
        return id != null && id.equals(((Tierspayant) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Tierspayant{" +
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
            "}";
    }
}
