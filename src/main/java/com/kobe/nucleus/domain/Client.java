package com.kobe.nucleus.domain;

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
 * A Client.
 */
@Entity
@Table(name = "client", indexes = {
	//	  @Index(name = "client_type_client", columnList = "type_client"),
        @Index(name = "client_first_name", columnList = "first_name"),
        @Index(name = "client_status", columnList = "status"),
        @Index(name = "client_last_name", columnList = "last_name")
    })
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Client implements Serializable {

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

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @NotNull
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "sexe")
    private String sexe;

    @Column(name = "dat_naiss")
    private LocalDate datNaiss;

 

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "mail")
    private String mail;

    @OneToMany(mappedBy = "client")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<FactureItem> factureItems = new HashSet<>();

    @OneToMany(mappedBy = "assure")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Vente> ventes = new HashSet<>();

    @OneToMany(mappedBy = "assure")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<AyantDroit> ayantDroits = new HashSet<>();

    @OneToMany(mappedBy = "client")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<CompteClient> compteClients = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("clients")
    private Compagnie compagnie;

    @ManyToOne
    @JsonIgnoreProperties("clients")
    private Remise remise;

  
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public Client createdAt(LocalDate createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public Client updatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Status getStatus() {
        return status;
    }

    public Client status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getFirstName() {
        return firstName;
    }

    public Client firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Client lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSexe() {
        return sexe;
    }

    public Client sexe(String sexe) {
        this.sexe = sexe;
        return this;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public LocalDate getDatNaiss() {
        return datNaiss;
    }

    public Client datNaiss(LocalDate datNaiss) {
        this.datNaiss = datNaiss;
        return this;
    }

    public void setDatNaiss(LocalDate datNaiss) {
        this.datNaiss = datNaiss;
    }

  
    public String getMobile() {
        return mobile;
    }

    public Client mobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMail() {
        return mail;
    }

    public Client mail(String mail) {
        this.mail = mail;
        return this;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Set<FactureItem> getFactureItems() {
        return factureItems;
    }

    public Client factureItems(Set<FactureItem> factureItems) {
        this.factureItems = factureItems;
        return this;
    }

    public Client addFactureItem(FactureItem factureItem) {
        this.factureItems.add(factureItem);
        factureItem.setClient(this);
        return this;
    }

    public Client removeFactureItem(FactureItem factureItem) {
        this.factureItems.remove(factureItem);
        factureItem.setClient(null);
        return this;
    }

    public void setFactureItems(Set<FactureItem> factureItems) {
        this.factureItems = factureItems;
    }

    public Set<Vente> getVentes() {
        return ventes;
    }

    public Client ventes(Set<Vente> ventes) {
        this.ventes = ventes;
        return this;
    }

    public Client addVente(Vente vente) {
        this.ventes.add(vente);
        vente.setAssure(this);
        return this;
    }

    public Client removeVente(Vente vente) {
        this.ventes.remove(vente);
        vente.setAssure(null);
        return this;
    }

    public void setVentes(Set<Vente> ventes) {
        this.ventes = ventes;
    }

    public Set<AyantDroit> getAyantDroits() {
        return ayantDroits;
    }

    public Client ayantDroits(Set<AyantDroit> ayantDroits) {
        this.ayantDroits = ayantDroits;
        return this;
    }

    public Client addAyantDroit(AyantDroit ayantDroit) {
        this.ayantDroits.add(ayantDroit);
        ayantDroit.setAssure(this);
        return this;
    }

    public Client removeAyantDroit(AyantDroit ayantDroit) {
        this.ayantDroits.remove(ayantDroit);
        ayantDroit.setAssure(null);
        return this;
    }

    public void setAyantDroits(Set<AyantDroit> ayantDroits) {
        this.ayantDroits = ayantDroits;
    }

    public Set<CompteClient> getCompteClients() {
        return compteClients;
    }

    public Client compteClients(Set<CompteClient> compteClients) {
        this.compteClients = compteClients;
        return this;
    }

    public Client addCompteClient(CompteClient compteClient) {
        this.compteClients.add(compteClient);
        compteClient.setClient(this);
        return this;
    }

    public Client removeCompteClient(CompteClient compteClient) {
        this.compteClients.remove(compteClient);
        compteClient.setClient(null);
        return this;
    }

    public void setCompteClients(Set<CompteClient> compteClients) {
        this.compteClients = compteClients;
    }

    public Compagnie getCompagnie() {
        return compagnie;
    }

    public Client compagnie(Compagnie compagnie) {
        this.compagnie = compagnie;
        return this;
    }

    public void setCompagnie(Compagnie compagnie) {
        this.compagnie = compagnie;
    }

    public Remise getRemise() {
        return remise;
    }

    public Client remise(Remise remise) {
        this.remise = remise;
        return this;
    }

    public void setRemise(Remise remise) {
        this.remise = remise;
    }
   

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Client)) {
            return false;
        }
        return id != null && id.equals(((Client) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Client{" +
            "id=" + getId() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", status='" + getStatus() + "'" +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", sexe='" + getSexe() + "'" +
            ", datNaiss='" + getDatNaiss() + "'" +
           
            ", mobile='" + getMobile() + "'" +
            ", mail='" + getMail() + "'" +
            "}";
    }
}
