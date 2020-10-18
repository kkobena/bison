package com.kobe.nucleus.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.kobe.nucleus.domain.enumeration.TypeMagasin;

import com.kobe.nucleus.domain.enumeration.Status;

/**
 * A Magasin.
 */
@Entity
@Table(name = "magasin")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Magasin implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type_magasin", nullable = false, unique = true)
    private TypeMagasin typeMagasin;

    @Column(name = "nom_court")
    private String nomCourt;

    @NotNull
    @Column(name = "nom_long", nullable = false, unique = true)
    private String nomLong;

    @Column(name = "addresse_postal")
    private String addressePostal;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @Column(name = "phone")
    private String phone;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "commentaire")
    private String commentaire;

    @Column(name = "autre_commentaire")
    private String autreCommentaire;

    @Column(name = "entete")
    private String entete;

    @Column(name = "compte_contribuable")
    private String compteContribuable;

    @Column(name = "registre_commerce")
    private String registreCommerce;

    @Column(name = "registre_imposition")
    private String registreImposition;

    @Column(name = "centre_imposition")
    private String centreImposition;

    @Column(name = "num_comptable")
    private String numComptable;

    @OneToMany(mappedBy = "magasin")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Ajustement> ajustements = new HashSet<>();

    @OneToMany(mappedBy = "magasin")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Paiement> paiements = new HashSet<>();

    @OneToMany(mappedBy = "magasin")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Inventaire> inventaires = new HashSet<>();

    @OneToMany(mappedBy = "magasin")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<RetourFournisseur> retourFournisseurs = new HashSet<>();

    @OneToMany(mappedBy = "magasin")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Rayon> rayons = new HashSet<>();

    @OneToMany(mappedBy = "magasin")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Vente> ventes = new HashSet<>();

    @OneToMany(mappedBy = "magasin")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Decondition> deconditions = new HashSet<>();

    @OneToMany(mappedBy = "magasin")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Facture> factures = new HashSet<>();

    @OneToMany(mappedBy = "magasin")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<MvtProduit> mvtProduits = new HashSet<>();

    @OneToMany(mappedBy = "magasin")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Commande> commandes = new HashSet<>();

    @OneToMany(mappedBy = "magasin")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<StockProduit> stockProduits = new HashSet<>();

    @OneToMany(mappedBy = "magasin")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Utilisateur> utilisateurs = new HashSet<>();

    @OneToMany(mappedBy = "magasin")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Magasin> magasins = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "magasins", allowSetters = true)
    private Magasin magasin;

    @ManyToOne
    @JsonIgnoreProperties(value = "magasins", allowSetters = true)
    private Utilisateur manager;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TypeMagasin getTypeMagasin() {
        return typeMagasin;
    }

    public Magasin typeMagasin(TypeMagasin typeMagasin) {
        this.typeMagasin = typeMagasin;
        return this;
    }

    public void setTypeMagasin(TypeMagasin typeMagasin) {
        this.typeMagasin = typeMagasin;
    }

    public String getNomCourt() {
        return nomCourt;
    }

    public Magasin nomCourt(String nomCourt) {
        this.nomCourt = nomCourt;
        return this;
    }

    public void setNomCourt(String nomCourt) {
        this.nomCourt = nomCourt;
    }

    public String getNomLong() {
        return nomLong;
    }

    public Magasin nomLong(String nomLong) {
        this.nomLong = nomLong;
        return this;
    }

    public void setNomLong(String nomLong) {
        this.nomLong = nomLong;
    }

    public String getAddressePostal() {
        return addressePostal;
    }

    public Magasin addressePostal(String addressePostal) {
        this.addressePostal = addressePostal;
        return this;
    }

    public void setAddressePostal(String addressePostal) {
        this.addressePostal = addressePostal;
    }

    public Status getStatus() {
        return status;
    }

    public Magasin status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public Magasin phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public Magasin mobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public Magasin commentaire(String commentaire) {
        this.commentaire = commentaire;
        return this;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getAutreCommentaire() {
        return autreCommentaire;
    }

    public Magasin autreCommentaire(String autreCommentaire) {
        this.autreCommentaire = autreCommentaire;
        return this;
    }

    public void setAutreCommentaire(String autreCommentaire) {
        this.autreCommentaire = autreCommentaire;
    }

    public String getEntete() {
        return entete;
    }

    public Magasin entete(String entete) {
        this.entete = entete;
        return this;
    }

    public void setEntete(String entete) {
        this.entete = entete;
    }

    public String getCompteContribuable() {
        return compteContribuable;
    }

    public Magasin compteContribuable(String compteContribuable) {
        this.compteContribuable = compteContribuable;
        return this;
    }

    public void setCompteContribuable(String compteContribuable) {
        this.compteContribuable = compteContribuable;
    }

    public String getRegistreCommerce() {
        return registreCommerce;
    }

    public Magasin registreCommerce(String registreCommerce) {
        this.registreCommerce = registreCommerce;
        return this;
    }

    public void setRegistreCommerce(String registreCommerce) {
        this.registreCommerce = registreCommerce;
    }

    public String getRegistreImposition() {
        return registreImposition;
    }

    public Magasin registreImposition(String registreImposition) {
        this.registreImposition = registreImposition;
        return this;
    }

    public void setRegistreImposition(String registreImposition) {
        this.registreImposition = registreImposition;
    }

    public String getCentreImposition() {
        return centreImposition;
    }

    public Magasin centreImposition(String centreImposition) {
        this.centreImposition = centreImposition;
        return this;
    }

    public void setCentreImposition(String centreImposition) {
        this.centreImposition = centreImposition;
    }

    public String getNumComptable() {
        return numComptable;
    }

    public Magasin numComptable(String numComptable) {
        this.numComptable = numComptable;
        return this;
    }

    public void setNumComptable(String numComptable) {
        this.numComptable = numComptable;
    }

    public Set<Ajustement> getAjustements() {
        return ajustements;
    }

    public Magasin ajustements(Set<Ajustement> ajustements) {
        this.ajustements = ajustements;
        return this;
    }

    public Magasin addAjustement(Ajustement ajustement) {
        this.ajustements.add(ajustement);
        ajustement.setMagasin(this);
        return this;
    }

    public Magasin removeAjustement(Ajustement ajustement) {
        this.ajustements.remove(ajustement);
        ajustement.setMagasin(null);
        return this;
    }

    public void setAjustements(Set<Ajustement> ajustements) {
        this.ajustements = ajustements;
    }

    public Set<Paiement> getPaiements() {
        return paiements;
    }

    public Magasin paiements(Set<Paiement> paiements) {
        this.paiements = paiements;
        return this;
    }

    public Magasin addPaiement(Paiement paiement) {
        this.paiements.add(paiement);
        paiement.setMagasin(this);
        return this;
    }

    public Magasin removePaiement(Paiement paiement) {
        this.paiements.remove(paiement);
        paiement.setMagasin(null);
        return this;
    }

    public void setPaiements(Set<Paiement> paiements) {
        this.paiements = paiements;
    }

    public Set<Inventaire> getInventaires() {
        return inventaires;
    }

    public Magasin inventaires(Set<Inventaire> inventaires) {
        this.inventaires = inventaires;
        return this;
    }

    public Magasin addInventaire(Inventaire inventaire) {
        this.inventaires.add(inventaire);
        inventaire.setMagasin(this);
        return this;
    }

    public Magasin removeInventaire(Inventaire inventaire) {
        this.inventaires.remove(inventaire);
        inventaire.setMagasin(null);
        return this;
    }

    public void setInventaires(Set<Inventaire> inventaires) {
        this.inventaires = inventaires;
    }

    public Set<RetourFournisseur> getRetourFournisseurs() {
        return retourFournisseurs;
    }

    public Magasin retourFournisseurs(Set<RetourFournisseur> retourFournisseurs) {
        this.retourFournisseurs = retourFournisseurs;
        return this;
    }

    public Magasin addRetourFournisseur(RetourFournisseur retourFournisseur) {
        this.retourFournisseurs.add(retourFournisseur);
        retourFournisseur.setMagasin(this);
        return this;
    }

    public Magasin removeRetourFournisseur(RetourFournisseur retourFournisseur) {
        this.retourFournisseurs.remove(retourFournisseur);
        retourFournisseur.setMagasin(null);
        return this;
    }

    public void setRetourFournisseurs(Set<RetourFournisseur> retourFournisseurs) {
        this.retourFournisseurs = retourFournisseurs;
    }

    public Set<Rayon> getRayons() {
        return rayons;
    }

    public Magasin rayons(Set<Rayon> rayons) {
        this.rayons = rayons;
        return this;
    }

    public Magasin addRayon(Rayon rayon) {
        this.rayons.add(rayon);
        rayon.setMagasin(this);
        return this;
    }

    public Magasin removeRayon(Rayon rayon) {
        this.rayons.remove(rayon);
        rayon.setMagasin(null);
        return this;
    }

    public void setRayons(Set<Rayon> rayons) {
        this.rayons = rayons;
    }

    public Set<Vente> getVentes() {
        return ventes;
    }

    public Magasin ventes(Set<Vente> ventes) {
        this.ventes = ventes;
        return this;
    }

    public Magasin addVente(Vente vente) {
        this.ventes.add(vente);
        vente.setMagasin(this);
        return this;
    }

    public Magasin removeVente(Vente vente) {
        this.ventes.remove(vente);
        vente.setMagasin(null);
        return this;
    }

    public void setVentes(Set<Vente> ventes) {
        this.ventes = ventes;
    }

    public Set<Decondition> getDeconditions() {
        return deconditions;
    }

    public Magasin deconditions(Set<Decondition> deconditions) {
        this.deconditions = deconditions;
        return this;
    }

    public Magasin addDecondition(Decondition decondition) {
        this.deconditions.add(decondition);
        decondition.setMagasin(this);
        return this;
    }

    public Magasin removeDecondition(Decondition decondition) {
        this.deconditions.remove(decondition);
        decondition.setMagasin(null);
        return this;
    }

    public void setDeconditions(Set<Decondition> deconditions) {
        this.deconditions = deconditions;
    }

    public Set<Facture> getFactures() {
        return factures;
    }

    public Magasin factures(Set<Facture> factures) {
        this.factures = factures;
        return this;
    }

    public Magasin addFacture(Facture facture) {
        this.factures.add(facture);
        facture.setMagasin(this);
        return this;
    }

    public Magasin removeFacture(Facture facture) {
        this.factures.remove(facture);
        facture.setMagasin(null);
        return this;
    }

    public void setFactures(Set<Facture> factures) {
        this.factures = factures;
    }

    public Set<MvtProduit> getMvtProduits() {
        return mvtProduits;
    }

    public Magasin mvtProduits(Set<MvtProduit> mvtProduits) {
        this.mvtProduits = mvtProduits;
        return this;
    }

    public Magasin addMvtProduit(MvtProduit mvtProduit) {
        this.mvtProduits.add(mvtProduit);
        mvtProduit.setMagasin(this);
        return this;
    }

    public Magasin removeMvtProduit(MvtProduit mvtProduit) {
        this.mvtProduits.remove(mvtProduit);
        mvtProduit.setMagasin(null);
        return this;
    }

    public void setMvtProduits(Set<MvtProduit> mvtProduits) {
        this.mvtProduits = mvtProduits;
    }

    public Set<Commande> getCommandes() {
        return commandes;
    }

    public Magasin commandes(Set<Commande> commandes) {
        this.commandes = commandes;
        return this;
    }

    public Magasin addCommande(Commande commande) {
        this.commandes.add(commande);
        commande.setMagasin(this);
        return this;
    }

    public Magasin removeCommande(Commande commande) {
        this.commandes.remove(commande);
        commande.setMagasin(null);
        return this;
    }

    public void setCommandes(Set<Commande> commandes) {
        this.commandes = commandes;
    }

    public Set<StockProduit> getStockProduits() {
        return stockProduits;
    }

    public Magasin stockProduits(Set<StockProduit> stockProduits) {
        this.stockProduits = stockProduits;
        return this;
    }

    public Magasin addStockProduit(StockProduit stockProduit) {
        this.stockProduits.add(stockProduit);
        stockProduit.setMagasin(this);
        return this;
    }

    public Magasin removeStockProduit(StockProduit stockProduit) {
        this.stockProduits.remove(stockProduit);
        stockProduit.setMagasin(null);
        return this;
    }

    public void setStockProduits(Set<StockProduit> stockProduits) {
        this.stockProduits = stockProduits;
    }

    public Set<Utilisateur> getUtilisateurs() {
        return utilisateurs;
    }

    public Magasin utilisateurs(Set<Utilisateur> utilisateurs) {
        this.utilisateurs = utilisateurs;
        return this;
    }

    public Magasin addUtilisateur(Utilisateur utilisateur) {
        this.utilisateurs.add(utilisateur);
        utilisateur.setMagasin(this);
        return this;
    }

    public Magasin removeUtilisateur(Utilisateur utilisateur) {
        this.utilisateurs.remove(utilisateur);
        utilisateur.setMagasin(null);
        return this;
    }

    public void setUtilisateurs(Set<Utilisateur> utilisateurs) {
        this.utilisateurs = utilisateurs;
    }

    public Set<Magasin> getMagasins() {
        return magasins;
    }

    public Magasin magasins(Set<Magasin> magasins) {
        this.magasins = magasins;
        return this;
    }

    public Magasin addMagasin(Magasin magasin) {
        this.magasins.add(magasin);
        magasin.setMagasin(this);
        return this;
    }

    public Magasin removeMagasin(Magasin magasin) {
        this.magasins.remove(magasin);
        magasin.setMagasin(null);
        return this;
    }

    public void setMagasins(Set<Magasin> magasins) {
        this.magasins = magasins;
    }

    public Magasin getMagasin() {
        return magasin;
    }

    public Magasin magasin(Magasin magasin) {
        this.magasin = magasin;
        return this;
    }

    public void setMagasin(Magasin magasin) {
        this.magasin = magasin;
    }

    public Utilisateur getManager() {
        return manager;
    }

    public Magasin manager(Utilisateur utilisateur) {
        this.manager = utilisateur;
        return this;
    }

    public void setManager(Utilisateur utilisateur) {
        this.manager = utilisateur;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Magasin)) {
            return false;
        }
        return id != null && id.equals(((Magasin) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Magasin{" +
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
            "}";
    }
}
