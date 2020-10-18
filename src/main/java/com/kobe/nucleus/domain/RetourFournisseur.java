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

import com.kobe.nucleus.domain.enumeration.RetourEnum;

/**
 * A RetourFournisseur.
 */
@Entity
@Table(name = "retour_fournisseur")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RetourFournisseur implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "answered", nullable = false)
    private Boolean answered;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private RetourEnum status;

    @NotNull
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @NotNull
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @NotNull
    @Column(name = "mvt_date", nullable = false)
    private LocalDate mvtDate;

    @OneToMany(mappedBy = "retourFournisseur")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<RetourItem> retourItems = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "retourFournisseurs", allowSetters = true)
    private Magasin magasin;

    @ManyToOne
    @JsonIgnoreProperties(value = "retourFournisseurs", allowSetters = true)
    private Utilisateur createdBy;

    @ManyToOne
    @JsonIgnoreProperties(value = "retourFournisseurs", allowSetters = true)
    private Motif motif;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public RetourFournisseur description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean isAnswered() {
        return answered;
    }

    public RetourFournisseur answered(Boolean answered) {
        this.answered = answered;
        return this;
    }

    public void setAnswered(Boolean answered) {
        this.answered = answered;
    }

    public RetourEnum getStatus() {
        return status;
    }

    public RetourFournisseur status(RetourEnum status) {
        this.status = status;
        return this;
    }

    public void setStatus(RetourEnum status) {
        this.status = status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public RetourFournisseur createdAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public RetourFournisseur updatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDate getMvtDate() {
        return mvtDate;
    }

    public RetourFournisseur mvtDate(LocalDate mvtDate) {
        this.mvtDate = mvtDate;
        return this;
    }

    public void setMvtDate(LocalDate mvtDate) {
        this.mvtDate = mvtDate;
    }

    public Set<RetourItem> getRetourItems() {
        return retourItems;
    }

    public RetourFournisseur retourItems(Set<RetourItem> retourItems) {
        this.retourItems = retourItems;
        return this;
    }

    public RetourFournisseur addRetourItem(RetourItem retourItem) {
        this.retourItems.add(retourItem);
        retourItem.setRetourFournisseur(this);
        return this;
    }

    public RetourFournisseur removeRetourItem(RetourItem retourItem) {
        this.retourItems.remove(retourItem);
        retourItem.setRetourFournisseur(null);
        return this;
    }

    public void setRetourItems(Set<RetourItem> retourItems) {
        this.retourItems = retourItems;
    }

    public Magasin getMagasin() {
        return magasin;
    }

    public RetourFournisseur magasin(Magasin magasin) {
        this.magasin = magasin;
        return this;
    }

    public void setMagasin(Magasin magasin) {
        this.magasin = magasin;
    }

    public Utilisateur getCreatedBy() {
        return createdBy;
    }

    public RetourFournisseur createdBy(Utilisateur utilisateur) {
        this.createdBy = utilisateur;
        return this;
    }

    public void setCreatedBy(Utilisateur utilisateur) {
        this.createdBy = utilisateur;
    }

    public Motif getMotif() {
        return motif;
    }

    public RetourFournisseur motif(Motif motif) {
        this.motif = motif;
        return this;
    }

    public void setMotif(Motif motif) {
        this.motif = motif;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RetourFournisseur)) {
            return false;
        }
        return id != null && id.equals(((RetourFournisseur) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RetourFournisseur{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", answered='" + isAnswered() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", mvtDate='" + getMvtDate() + "'" +
            "}";
    }
}
