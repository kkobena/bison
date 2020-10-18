package com.kobe.nucleus.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A RetourItem.
 */
@Entity
@Table(name = "retour_item")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RetourItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "qte_confirme")
    private Integer qteConfirme;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @NotNull
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "qte_retourne")
    private Integer qteRetourne;

    @ManyToOne
    @JsonIgnoreProperties(value = "retourItems", allowSetters = true)
    private RetourFournisseur retourFournisseur;

    @ManyToOne
    @JsonIgnoreProperties(value = "retourItems", allowSetters = true)
    private CommandeItem commandeItem;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQteConfirme() {
        return qteConfirme;
    }

    public RetourItem qteConfirme(Integer qteConfirme) {
        this.qteConfirme = qteConfirme;
        return this;
    }

    public void setQteConfirme(Integer qteConfirme) {
        this.qteConfirme = qteConfirme;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public RetourItem updatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public RetourItem createdAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getQteRetourne() {
        return qteRetourne;
    }

    public RetourItem qteRetourne(Integer qteRetourne) {
        this.qteRetourne = qteRetourne;
        return this;
    }

    public void setQteRetourne(Integer qteRetourne) {
        this.qteRetourne = qteRetourne;
    }

    public RetourFournisseur getRetourFournisseur() {
        return retourFournisseur;
    }

    public RetourItem retourFournisseur(RetourFournisseur retourFournisseur) {
        this.retourFournisseur = retourFournisseur;
        return this;
    }

    public void setRetourFournisseur(RetourFournisseur retourFournisseur) {
        this.retourFournisseur = retourFournisseur;
    }

    public CommandeItem getCommandeItem() {
        return commandeItem;
    }

    public RetourItem commandeItem(CommandeItem commandeItem) {
        this.commandeItem = commandeItem;
        return this;
    }

    public void setCommandeItem(CommandeItem commandeItem) {
        this.commandeItem = commandeItem;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RetourItem)) {
            return false;
        }
        return id != null && id.equals(((RetourItem) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RetourItem{" +
            "id=" + getId() +
            ", qteConfirme=" + getQteConfirme() +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", qteRetourne=" + getQteRetourne() +
            "}";
    }
}
