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
    private Status status=Status.ACTIVE;

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
    private Set<Rayon> rayons = new HashSet<>();

   
    @OneToMany(mappedBy = "magasin")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<User> utilisateurs = new HashSet<>();

    @OneToMany(mappedBy = "magasin")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Magasin> magasins = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "magasins", allowSetters = true)
    private Magasin magasin;

    @ManyToOne
    @JsonIgnoreProperties(value = "magasins", allowSetters = true)
    private User manager;

 
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

 

    public Set<User> getUtilisateurs() {
        return utilisateurs;
    }

    public Magasin utilisateurs(Set<User> utilisateurs) {
        this.utilisateurs = utilisateurs;
        return this;
    }

    public Magasin addUtilisateur(User utilisateur) {
        this.utilisateurs.add(utilisateur);
     
        return this;
    }

    public Magasin removeUtilisateur(User utilisateur) {
        this.utilisateurs.remove(utilisateur);
    
        return this;
    }

    public void setUtilisateurs(Set<User> utilisateurs) {
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

    public User getManager() {
        return manager;
    }

    public Magasin manager(User utilisateur) {
        this.manager = utilisateur;
        return this;
    }

    public void setManager(User utilisateur) {
        this.manager = utilisateur;
    }


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
