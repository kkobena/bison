package com.kobe.nucleus.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 * A RemiseProduit.
 */
@Entity
public class RemiseProduit extends Remise implements Serializable {

	private static final long serialVersionUID = 1L;
	@OneToMany(mappedBy = "remise")
    private Set<Produit> produits = new HashSet<>();
    public Set<Produit> getProduits() {
        return produits;
    }

    public RemiseProduit produits(Set<Produit> produits) {
        this.produits = produits;
        return this;
    }

    public RemiseProduit addProduit(Produit produit) {
        this.produits.add(produit);
        produit.setRemise(this);
        return this;
    }

    public RemiseProduit removeProduit(Produit produit) {
        this.produits.remove(produit);
        produit.setRemise(null);
        return this;
    }

    public void setProduits(Set<Produit> produits) {
        this.produits = produits;
    }
  

}
