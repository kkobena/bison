package com.kobe.nucleus.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.kobe.nucleus.domain.enumeration.Status;

/**
 * A Utilisateur.
 */
@Entity
@Table(name = "utilisateur")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Utilisateur implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    @NotNull
    @Column(name = "updated_at", nullable = false)
    private LocalDate updatedAt;

    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotNull
    @Column(name = "user_name", nullable = false)
    private String userName;

    @NotNull
    @Column(name = "pword", nullable = false)
    private String pword;

    @Column(name = "enable")
    private Boolean enable;

    @Column(name = "phone")
    private String phone;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "manager")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Magasin> magasins = new HashSet<>();

    @OneToMany(mappedBy = "createdBy")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Paiement> paiements = new HashSet<>();

    @OneToMany(mappedBy = "createdBy")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Decondition> deconditions = new HashSet<>();

    @OneToMany(mappedBy = "createdBy")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Vente> ventes = new HashSet<>();

    @OneToMany(mappedBy = "createdBy")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Ajustement> ajustements = new HashSet<>();

    @OneToMany(mappedBy = "createdBy")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Commande> commandes = new HashSet<>();

    @OneToMany(mappedBy = "createdBy")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Inventaire> inventaires = new HashSet<>();

    @OneToMany(mappedBy = "createdBy")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<MvtProduit> mvtProduits = new HashSet<>();

    @OneToMany(mappedBy = "createdBy")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<RetourFournisseur> retourFournisseurs = new HashSet<>();

    @OneToMany(mappedBy = "createdBy")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Facture> factures = new HashSet<>();

    @OneToMany(mappedBy = "createdBy")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Permission> permissions = new HashSet<>();

    @OneToOne(mappedBy = "utilisateur")
    @JsonIgnore
    private RoleUtilisateur roleUtilisateur;

    @ManyToOne
    @JsonIgnoreProperties(value = "utilisateurs", allowSetters = true)
    private Magasin magasin;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public Utilisateur createdAt(LocalDate createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public Utilisateur updatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getLastName() {
        return lastName;
    }

    public Utilisateur lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public Utilisateur firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getUserName() {
        return userName;
    }

    public Utilisateur userName(String userName) {
        this.userName = userName;
        return this;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPword() {
        return pword;
    }

    public Utilisateur pword(String pword) {
        this.pword = pword;
        return this;
    }

    public void setPword(String pword) {
        this.pword = pword;
    }

    public Boolean isEnable() {
        return enable;
    }

    public Utilisateur enable(Boolean enable) {
        this.enable = enable;
        return this;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public String getPhone() {
        return phone;
    }

    public Utilisateur phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Status getStatus() {
        return status;
    }

    public Utilisateur status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public Utilisateur email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Magasin> getMagasins() {
        return magasins;
    }

    public Utilisateur magasins(Set<Magasin> magasins) {
        this.magasins = magasins;
        return this;
    }

    public Utilisateur addMagasin(Magasin magasin) {
        this.magasins.add(magasin);
        magasin.setManager(this);
        return this;
    }

    public Utilisateur removeMagasin(Magasin magasin) {
        this.magasins.remove(magasin);
        magasin.setManager(null);
        return this;
    }

    public void setMagasins(Set<Magasin> magasins) {
        this.magasins = magasins;
    }

    public Set<Paiement> getPaiements() {
        return paiements;
    }

    public Utilisateur paiements(Set<Paiement> paiements) {
        this.paiements = paiements;
        return this;
    }

    public Utilisateur addPaiement(Paiement paiement) {
        this.paiements.add(paiement);
        paiement.setCreatedBy(this);
        return this;
    }

    public Utilisateur removePaiement(Paiement paiement) {
        this.paiements.remove(paiement);
        paiement.setCreatedBy(null);
        return this;
    }

    public void setPaiements(Set<Paiement> paiements) {
        this.paiements = paiements;
    }

    public Set<Decondition> getDeconditions() {
        return deconditions;
    }

    public Utilisateur deconditions(Set<Decondition> deconditions) {
        this.deconditions = deconditions;
        return this;
    }

    public Utilisateur addDecondition(Decondition decondition) {
        this.deconditions.add(decondition);
        decondition.setCreatedBy(this);
        return this;
    }

    public Utilisateur removeDecondition(Decondition decondition) {
        this.deconditions.remove(decondition);
        decondition.setCreatedBy(null);
        return this;
    }

    public void setDeconditions(Set<Decondition> deconditions) {
        this.deconditions = deconditions;
    }

    public Set<Vente> getVentes() {
        return ventes;
    }

    public Utilisateur ventes(Set<Vente> ventes) {
        this.ventes = ventes;
        return this;
    }

    public Utilisateur addVente(Vente vente) {
        this.ventes.add(vente);
        vente.setCreatedBy(this);
        return this;
    }

    public Utilisateur removeVente(Vente vente) {
        this.ventes.remove(vente);
        vente.setCreatedBy(null);
        return this;
    }

    public void setVentes(Set<Vente> ventes) {
        this.ventes = ventes;
    }

    public Set<Ajustement> getAjustements() {
        return ajustements;
    }

    public Utilisateur ajustements(Set<Ajustement> ajustements) {
        this.ajustements = ajustements;
        return this;
    }

    public Utilisateur addAjustement(Ajustement ajustement) {
        this.ajustements.add(ajustement);
        ajustement.setCreatedBy(this);
        return this;
    }

    public Utilisateur removeAjustement(Ajustement ajustement) {
        this.ajustements.remove(ajustement);
        ajustement.setCreatedBy(null);
        return this;
    }

    public void setAjustements(Set<Ajustement> ajustements) {
        this.ajustements = ajustements;
    }

    public Set<Commande> getCommandes() {
        return commandes;
    }

    public Utilisateur commandes(Set<Commande> commandes) {
        this.commandes = commandes;
        return this;
    }

    public Utilisateur addCommande(Commande commande) {
        this.commandes.add(commande);
        commande.setCreatedBy(this);
        return this;
    }

    public Utilisateur removeCommande(Commande commande) {
        this.commandes.remove(commande);
        commande.setCreatedBy(null);
        return this;
    }

    public void setCommandes(Set<Commande> commandes) {
        this.commandes = commandes;
    }

    public Set<Inventaire> getInventaires() {
        return inventaires;
    }

    public Utilisateur inventaires(Set<Inventaire> inventaires) {
        this.inventaires = inventaires;
        return this;
    }

    public Utilisateur addInventaire(Inventaire inventaire) {
        this.inventaires.add(inventaire);
        inventaire.setCreatedBy(this);
        return this;
    }

    public Utilisateur removeInventaire(Inventaire inventaire) {
        this.inventaires.remove(inventaire);
        inventaire.setCreatedBy(null);
        return this;
    }

    public void setInventaires(Set<Inventaire> inventaires) {
        this.inventaires = inventaires;
    }

    public Set<MvtProduit> getMvtProduits() {
        return mvtProduits;
    }

    public Utilisateur mvtProduits(Set<MvtProduit> mvtProduits) {
        this.mvtProduits = mvtProduits;
        return this;
    }

    public Utilisateur addMvtProduit(MvtProduit mvtProduit) {
        this.mvtProduits.add(mvtProduit);
        mvtProduit.setCreatedBy(this);
        return this;
    }

    public Utilisateur removeMvtProduit(MvtProduit mvtProduit) {
        this.mvtProduits.remove(mvtProduit);
        mvtProduit.setCreatedBy(null);
        return this;
    }

    public void setMvtProduits(Set<MvtProduit> mvtProduits) {
        this.mvtProduits = mvtProduits;
    }

    public Set<RetourFournisseur> getRetourFournisseurs() {
        return retourFournisseurs;
    }

    public Utilisateur retourFournisseurs(Set<RetourFournisseur> retourFournisseurs) {
        this.retourFournisseurs = retourFournisseurs;
        return this;
    }

    public Utilisateur addRetourFournisseur(RetourFournisseur retourFournisseur) {
        this.retourFournisseurs.add(retourFournisseur);
        retourFournisseur.setCreatedBy(this);
        return this;
    }

    public Utilisateur removeRetourFournisseur(RetourFournisseur retourFournisseur) {
        this.retourFournisseurs.remove(retourFournisseur);
        retourFournisseur.setCreatedBy(null);
        return this;
    }

    public void setRetourFournisseurs(Set<RetourFournisseur> retourFournisseurs) {
        this.retourFournisseurs = retourFournisseurs;
    }

    public Set<Facture> getFactures() {
        return factures;
    }

    public Utilisateur factures(Set<Facture> factures) {
        this.factures = factures;
        return this;
    }

    public Utilisateur addFacture(Facture facture) {
        this.factures.add(facture);
        facture.setCreatedBy(this);
        return this;
    }

    public Utilisateur removeFacture(Facture facture) {
        this.factures.remove(facture);
        facture.setCreatedBy(null);
        return this;
    }

    public void setFactures(Set<Facture> factures) {
        this.factures = factures;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Utilisateur permissions(Set<Permission> permissions) {
        this.permissions = permissions;
        return this;
    }

    public Utilisateur addPermission(Permission permission) {
        this.permissions.add(permission);
        permission.setCreatedBy(this);
        return this;
    }

    public Utilisateur removePermission(Permission permission) {
        this.permissions.remove(permission);
        permission.setCreatedBy(null);
        return this;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public RoleUtilisateur getRoleUtilisateur() {
        return roleUtilisateur;
    }

    public Utilisateur roleUtilisateur(RoleUtilisateur roleUtilisateur) {
        this.roleUtilisateur = roleUtilisateur;
        return this;
    }

    public void setRoleUtilisateur(RoleUtilisateur roleUtilisateur) {
        this.roleUtilisateur = roleUtilisateur;
    }

    public Magasin getMagasin() {
        return magasin;
    }

    public Utilisateur magasin(Magasin magasin) {
        this.magasin = magasin;
        return this;
    }

    public void setMagasin(Magasin magasin) {
        this.magasin = magasin;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Utilisateur)) {
            return false;
        }
        return id != null && id.equals(((Utilisateur) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Utilisateur{" +
            "id=" + getId() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", firstName='" + getFirstName() + "'" +
            ", userName='" + getUserName() + "'" +
            ", pword='" + getPword() + "'" +
            ", enable='" + isEnable() + "'" +
            ", phone='" + getPhone() + "'" +
            ", status='" + getStatus() + "'" +
            ", email='" + getEmail() + "'" +
            "}";
    }
}
