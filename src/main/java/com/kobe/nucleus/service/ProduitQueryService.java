package com.kobe.nucleus.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kobe.nucleus.domain.Produit;
import com.kobe.nucleus.repository.ProduitRepository;

import io.github.jhipster.service.QueryService;


@Service
@Transactional(readOnly = true)
public class ProduitQueryService extends QueryService<Produit> {
	 private final Logger LOG = LoggerFactory.getLogger(ProduitQueryService.class);

	    private final ProduitRepository produitRepository;

	    public ProduitQueryService(ProduitRepository produitRepository) {
	        this.produitRepository = produitRepository;
	    }
	  
}
