package com.kobe.nucleus.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.kobe.nucleus.domain.enumeration.Status;

/**
 * A Inventaire.
 */
@Entity
@Table(name = "inventaire")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Inventaire implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "libelle")
    private String libelle;

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

    @Column(name = "programmable")
    private Boolean programmable;

    @Column(name = "date_debut")
    private LocalDate dateDebut;

    @Column(name = "date_fin")
    private LocalDate dateFin;

    @Column(name = "end_date")
    private LocalDate endDate;

    @NotNull
    @Column(name = "beginnin", nullable = false)
    private Instant beginnin;

    @NotNull
    @Column(name = "ending", nullable = false)
    private Instant ending;

    @NotNull
    @Column(name = "valeur_achat_avant", nullable = false)
    private Double valeurAchatAvant;

    @NotNull
    @Column(name = "valeur_achat_apres", nullable = false)
    private Double valeurAchatApres;

    @NotNull
    @Column(name = "valeur_vente_avant", nullable = false)
    private Double valeurVenteAvant;

    @NotNull
    @Column(name = "valeur_vente_apres", nullable = false)
    private Double valeurVenteApres;

    @OneToMany(mappedBy = "inventaire")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<DetailsInventaire> detailsInventaires = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "inventaires", allowSetters = true)
    private Magasin magasin;

    @ManyToOne
    @JsonIgnoreProperties(value = "inventaires", allowSetters = true)
    private Utilisateur createdBy;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public Inventaire libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Inventaire createdAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public Inventaire updatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Status getStatus() {
        return status;
    }

    public Inventaire status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Boolean isProgrammable() {
        return programmable;
    }

    public Inventaire programmable(Boolean programmable) {
        this.programmable = programmable;
        return this;
    }

    public void setProgrammable(Boolean programmable) {
        this.programmable = programmable;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public Inventaire dateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
        return this;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public Inventaire dateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
        return this;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Inventaire endDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Instant getBeginnin() {
        return beginnin;
    }

    public Inventaire beginnin(Instant beginnin) {
        this.beginnin = beginnin;
        return this;
    }

    public void setBeginnin(Instant beginnin) {
        this.beginnin = beginnin;
    }

    public Instant getEnding() {
        return ending;
    }

    public Inventaire ending(Instant ending) {
        this.ending = ending;
        return this;
    }

    public void setEnding(Instant ending) {
        this.ending = ending;
    }

    public Double getValeurAchatAvant() {
        return valeurAchatAvant;
    }

    public Inventaire valeurAchatAvant(Double valeurAchatAvant) {
        this.valeurAchatAvant = valeurAchatAvant;
        return this;
    }

    public void setValeurAchatAvant(Double valeurAchatAvant) {
        this.valeurAchatAvant = valeurAchatAvant;
    }

    public Double getValeurAchatApres() {
        return valeurAchatApres;
    }

    public Inventaire valeurAchatApres(Double valeurAchatApres) {
        this.valeurAchatApres = valeurAchatApres;
        return this;
    }

    public void setValeurAchatApres(Double valeurAchatApres) {
        this.valeurAchatApres = valeurAchatApres;
    }

    public Double getValeurVenteAvant() {
        return valeurVenteAvant;
    }

    public Inventaire valeurVenteAvant(Double valeurVenteAvant) {
        this.valeurVenteAvant = valeurVenteAvant;
        return this;
    }

    public void setValeurVenteAvant(Double valeurVenteAvant) {
        this.valeurVenteAvant = valeurVenteAvant;
    }

    public Double getValeurVenteApres() {
        return valeurVenteApres;
    }

    public Inventaire valeurVenteApres(Double valeurVenteApres) {
        this.valeurVenteApres = valeurVenteApres;
        return this;
    }

    public void setValeurVenteApres(Double valeurVenteApres) {
        this.valeurVenteApres = valeurVenteApres;
    }

    public Set<DetailsInventaire> getDetailsInventaires() {
        return detailsInventaires;
    }

    public Inventaire detailsInventaires(Set<DetailsInventaire> detailsInventaires) {
        this.detailsInventaires = detailsInventaires;
        return this;
    }

    public Inventaire addDetailsInventaire(DetailsInventaire detailsInventaire) {
        this.detailsInventaires.add(detailsInventaire);
        detailsInventaire.setInventaire(this);
        return this;
    }

    public Inventaire removeDetailsInventaire(DetailsInventaire detailsInventaire) {
        this.detailsInventaires.remove(detailsInventaire);
        detailsInventaire.setInventaire(null);
        return this;
    }

    public void setDetailsInventaires(Set<DetailsInventaire> detailsInventaires) {
        this.detailsInventaires = detailsInventaires;
    }

    public Magasin getMagasin() {
        return magasin;
    }

    public Inventaire magasin(Magasin magasin) {
        this.magasin = magasin;
        return this;
    }

    public void setMagasin(Magasin magasin) {
        this.magasin = magasin;
    }

    public Utilisateur getCreatedBy() {
        return createdBy;
    }

    public Inventaire createdBy(Utilisateur utilisateur) {
        this.createdBy = utilisateur;
        return this;
    }

    public void setCreatedBy(Utilisateur utilisateur) {
        this.createdBy = utilisateur;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Inventaire)) {
            return false;
        }
        return id != null && id.equals(((Inventaire) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Inventaire{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", status='" + getStatus() + "'" +
            ", programmable='" + isProgrammable() + "'" +
            ", dateDebut='" + getDateDebut() + "'" +
            ", dateFin='" + getDateFin() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", beginnin='" + getBeginnin() + "'" +
            ", ending='" + getEnding() + "'" +
            ", valeurAchatAvant=" + getValeurAchatAvant() +
            ", valeurAchatApres=" + getValeurAchatApres() +
            ", valeurVenteAvant=" + getValeurVenteAvant() +
            ", valeurVenteApres=" + getValeurVenteApres() +
            "}";
    }
}
