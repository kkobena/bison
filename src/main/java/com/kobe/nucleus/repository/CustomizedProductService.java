package com.kobe.nucleus.repository;

import java.time.Instant;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.kobe.nucleus.domain.CommandeItem;
import com.kobe.nucleus.domain.DetailsInventaire;
import com.kobe.nucleus.domain.FamilleProduit;
import com.kobe.nucleus.domain.FormProduit;
import com.kobe.nucleus.domain.Fournisseur;
import com.kobe.nucleus.domain.FournisseurProduit;
import com.kobe.nucleus.domain.GammeProduit;
import com.kobe.nucleus.domain.Laboratoire;
import com.kobe.nucleus.domain.LignesVente;
import com.kobe.nucleus.domain.Magasin;
import com.kobe.nucleus.domain.Produit;
import com.kobe.nucleus.domain.Rayon;
import com.kobe.nucleus.domain.RemiseProduit;
import com.kobe.nucleus.domain.StockProduit;
import com.kobe.nucleus.domain.Tva;
import com.kobe.nucleus.domain.TypeEtiquette;
import com.kobe.nucleus.service.dto.FournisseurProduitDTO;
import com.kobe.nucleus.service.dto.ProduitCriteria;
import com.kobe.nucleus.service.dto.ProduitDTO;
import com.kobe.nucleus.service.dto.StockProduitDTO;

public interface CustomizedProductService {
	List<ProduitDTO> findAll(ProduitCriteria produitCriteria) throws Exception;

	Page<ProduitDTO> findAll(ProduitCriteria produitCriteria, Pageable pageable) throws Exception;

	ProduitDTO save(ProduitDTO dto) throws Exception;

	ProduitDTO update(ProduitDTO dto) throws Exception;

	void save(StockProduitDTO dto) throws Exception;

	void update(StockProduitDTO dto) throws Exception;

	void save(FournisseurProduitDTO dto) throws Exception;

	void update(FournisseurProduitDTO dto) throws Exception;

	void removeFournisseurProduit(Long id) throws Exception;

	default FournisseurProduit buildFournisseurProduitFromFournisseurProduitDTO(FournisseurProduitDTO dto) {
		FournisseurProduit fournisseurProduit = new FournisseurProduit();
		fournisseurProduit.setCodeCip(dto.getCodeCip());
		fournisseurProduit.setCreatedAt(Instant.now());
		fournisseurProduit.setUpdatedAt(Instant.now());
		fournisseurProduit.setPrixAchat(dto.getPrixAchat());
		fournisseurProduit.setPrixUni(dto.getPrixUni());
		fournisseurProduit.setFournisseur(fournisseurFromId(dto.getFournisseurId()));
		fournisseurProduit.setPrincipal(dto.isPrincipal());
		return fournisseurProduit;
	}

	default Fournisseur fournisseurFromId(Long id) {
		Fournisseur fournisseur = new Fournisseur();
		fournisseur.setId(id);
		return fournisseur;
	}

	default FournisseurProduit buildFournisseurProduitFromFournisseurProduitDTO(FournisseurProduitDTO dto,
			FournisseurProduit fournisseurProduit) {
		fournisseurProduit.setCodeCip(dto.getCodeCip());
		fournisseurProduit.setUpdatedAt(Instant.now());
		fournisseurProduit.setPrixAchat(dto.getPrixAchat());
		fournisseurProduit.setPrixUni(dto.getPrixUni());
		fournisseurProduit.setPrincipal(dto.isPrincipal());
		fournisseurProduit.setFournisseur(fournisseurFromId(dto.getFournisseurId()));
		return fournisseurProduit;
	}

	default StockProduit buildStockProduitFromStockProduitDTO(StockProduitDTO dto, StockProduit stockProduit) {
		stockProduit.setQtyStock(dto.getQtyStock());
		stockProduit.setUpdatedAt(Instant.now());
		stockProduit.setQtyVirtual(dto.getQtyVirtual());
		stockProduit.setQtyUG(dto.getQtyUG());
		stockProduit.setRayon(rayonFromId(dto.getRayonId()));
		return stockProduit;
	}

	default StockProduit buildStockProduitFromStockProduitDTO(StockProduitDTO dto) {
		StockProduit stockProduit = new StockProduit();
		stockProduit.setCreatedAt(Instant.now());
		stockProduit.setQtyStock(dto.getQtyStock());
		stockProduit.setUpdatedAt(Instant.now());
		stockProduit.setQtyVirtual(dto.getQtyVirtual());
		stockProduit.setQtyUG(dto.getQtyUG());
		stockProduit.setRayon(rayonFromId(dto.getRayonId()));
		return stockProduit;
	}

	default Rayon rayonFromId(Long id) {
		if (id == null) {
			return null;
		}
		Rayon rayon = new Rayon();
		rayon.setId(id);
		return rayon;

	}

	default Magasin magasinFromId(Long id) {
		if (id == null) {
			return null;
		}
		Magasin magasin = new Magasin();
		magasin.setId(id);
		return magasin;

	}

	default Laboratoire laboratoireFromId(Long id) {
		if (id == null) {
			return null;
		}
		Laboratoire laboratoire = new Laboratoire();
		laboratoire.setId(id);
		return laboratoire;

	}

	default FormProduit formeFromId(Long id) {
		if (id == null) {
			return null;
		}
		FormProduit formProduit = new FormProduit();
		formProduit.setId(id);
		return formProduit;

	}

	default Produit parentFromId(Long id) {
		if (id == null) {
			return null;
		}
		Produit produit = new Produit();
		produit.setId(id);
		return produit;

	}

	default TypeEtiquette typeEtiquetteFromId(Long id) {
		if (id == null) {
			return null;
		}
		TypeEtiquette typeEtiquette = new TypeEtiquette();
		typeEtiquette.setId(id);
		return typeEtiquette;

	}

	default FamilleProduit familleFromId(Long id) {
		if (id == null) {
			return null;
		}
		FamilleProduit famille = new FamilleProduit();
		famille.setId(id);
		return famille;

	}
	default RemiseProduit remiseProduitFromId(Long id) {
		if (id == null) {
			return null;
		}
		RemiseProduit remiseProduit = new RemiseProduit();
		remiseProduit.setId(id);
		return remiseProduit;

	}
	default GammeProduit gammeFromId(Long id) {
		if (id == null) {
			return null;
		}
		GammeProduit famille = new GammeProduit();
		famille.setId(id);
		return famille;

	}

	default Tva tvaFromId(Long id) {
		if (id == null) {
			return null;
		}
		Tva tva = new Tva();
		tva.setId(id);
		return tva;

	}

	default Produit buildProduitFromProduitDTO(ProduitDTO dto, Produit produit) {
		produit.setCodeCip(dto.getCodeCip());
		produit.setUpdatedAt(Instant.now());
		produit.setPrixPaf(dto.getPrixPaf());
		produit.setPrixUni(dto.getPrixUni());
		produit.setChiffre(dto.isChiffre());
		produit.setCodeEan(dto.getCodeEan());
		produit.setDateperemption(dto.isDateperemption());
		produit.setDeconditionnable(dto.isDeconditionnable());
		produit.setPrixMnp(dto.getPrixMnp());
		produit.setLibelle(dto.getLibelle());
		produit.setQtySeuilMini(dto.getQtySeuilMini());
		produit.setQtyAppro(dto.getQtyAppro());
		produit.setQtyDetails(dto.getQtyDetails());
		produit.setTva(tvaFromId(dto.getTvaId()));
		produit.setForme(formeFromId(dto.getFormeId()));
		produit.setTypeEtyquette(typeEtiquetteFromId(dto.getTypeEtyquetteId()));
		produit.setFamille(familleFromId(dto.getFamilleId()));
		produit.setLaboratoire(laboratoireFromId(dto.getLaboratoireId()));
		produit.setParent(parentFromId(dto.getParentId()));
		produit.setGamme(gammeFromId(dto.getGammeId()));
		produit.setRemise(remiseProduitFromId(dto.getRemiseId()));
		return produit;
	}

	default Produit buildProduitFromProduitDTO(ProduitDTO dto) {
		Produit produit = new Produit();
		produit.setCodeCip(dto.getCodeCip());
		produit.setCreatedAt(Instant.now());
		produit.setUpdatedAt(Instant.now());
		produit.setPrixPaf(dto.getPrixPaf());
		produit.setPrixUni(dto.getPrixUni());
		produit.setChiffre(dto.isChiffre());
		produit.setCodeEan(dto.getCodeEan());
		produit.setDateperemption(dto.isDateperemption());
		produit.setDeconditionnable(dto.isDeconditionnable());
		produit.setPrixMnp(dto.getPrixMnp());
		produit.setLibelle(dto.getLibelle());
		produit.setQtySeuilMini(dto.getQtySeuilMini());
		produit.setQtyAppro(dto.getQtyAppro());
		produit.setQtyDetails(dto.getQtyDetails());
		produit.setTva(tvaFromId(dto.getTvaId()));
		produit.setForme(formeFromId(dto.getFormeId()));
		produit.setTypeEtyquette(typeEtiquetteFromId(dto.getTypeEtyquetteId()));
		produit.setFamille(familleFromId(dto.getFamilleId()));
		produit.setLaboratoire(laboratoireFromId(dto.getLaboratoireId()));
		produit.setParent(parentFromId(dto.getParentId()));
		produit.setGamme(gammeFromId(dto.getGammeId()));
		produit.setRemise(remiseProduitFromId(dto.getRemiseId()));
		return produit;
	}

	LignesVente lastSale(ProduitCriteria produitCriteria);

	DetailsInventaire lastInventory(ProduitCriteria produitCriteria);

	CommandeItem lastOrder(ProduitCriteria produitCriteria);
}
