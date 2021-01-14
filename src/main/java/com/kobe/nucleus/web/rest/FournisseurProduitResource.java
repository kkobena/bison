package com.kobe.nucleus.web.rest;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kobe.nucleus.service.FournisseurProduitService;
import com.kobe.nucleus.service.dto.FournisseurProduitDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.kobe.nucleus.domain.FournisseurProduit}.
 */
@RestController
@RequestMapping("/api")
public class FournisseurProduitResource {

    private final Logger log = LoggerFactory.getLogger(FournisseurProduitResource.class);

    private final FournisseurProduitService fournisseurProduitService;

    public FournisseurProduitResource(FournisseurProduitService fournisseurProduitService) {
        this.fournisseurProduitService = fournisseurProduitService;
    }

    /**
     * {@code GET  /fournisseur-produits} : get all the fournisseurProduits.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fournisseurProduits in body.
     */
    @GetMapping("/fournisseur-produits")
    public List<FournisseurProduitDTO> getAllFournisseurProduits() {
        log.debug("REST request to get all FournisseurProduits");
        return fournisseurProduitService.findAll();
    }

    /**
     * {@code GET  /fournisseur-produits/:id} : get the "id" fournisseurProduit.
     *
     * @param id the id of the fournisseurProduitDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fournisseurProduitDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/fournisseur-produits/{id}")
    public ResponseEntity<FournisseurProduitDTO> getFournisseurProduit(@PathVariable Long id) {
        log.debug("REST request to get FournisseurProduit : {}", id);
        Optional<FournisseurProduitDTO> fournisseurProduitDTO = fournisseurProduitService.findOne(id);
        return ResponseUtil.wrapOrNotFound(fournisseurProduitDTO);
    }
    
   
}
