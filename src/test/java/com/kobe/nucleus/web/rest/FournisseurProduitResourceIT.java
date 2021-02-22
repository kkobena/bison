package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.NucleusApp;
import com.kobe.nucleus.domain.FournisseurProduit;
import com.kobe.nucleus.domain.Produit;
import com.kobe.nucleus.domain.Fournisseur;
import com.kobe.nucleus.repository.FournisseurProduitRepository;
import com.kobe.nucleus.service.FournisseurProduitService;
import com.kobe.nucleus.service.dto.FournisseurProduitDTO;
import com.kobe.nucleus.service.mapper.FournisseurProduitMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link FournisseurProduitResource} REST controller.
 */
@SpringBootTest(classes = NucleusApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class FournisseurProduitResourceIT {

    private static final String DEFAULT_CODE_CIP = "AAAAAAAAAA";
    private static final String UPDATED_CODE_CIP = "BBBBBBBBBB";

    private static final Integer DEFAULT_PRIX_ACHAT = 0;
    private static final Integer UPDATED_PRIX_ACHAT = 1;

    private static final Integer DEFAULT_PRIX_UNI = 0;
    private static final Integer UPDATED_PRIX_UNI = 1;

    private static final Instant DEFAULT_CREATED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_UPDATED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Boolean DEFAULT_PRINCIPAL = false;
    private static final Boolean UPDATED_PRINCIPAL = true;

    @Autowired
    private FournisseurProduitRepository fournisseurProduitRepository;

    @Autowired
    private FournisseurProduitMapper fournisseurProduitMapper;

    @Autowired
    private FournisseurProduitService fournisseurProduitService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFournisseurProduitMockMvc;

    private FournisseurProduit fournisseurProduit;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FournisseurProduit createEntity(EntityManager em) {
        FournisseurProduit fournisseurProduit = new FournisseurProduit()
            .codeCip(DEFAULT_CODE_CIP)
            .prixAchat(DEFAULT_PRIX_ACHAT)
            .prixUni(DEFAULT_PRIX_UNI)
            .createdAt(DEFAULT_CREATED_AT)
            .updatedAt(DEFAULT_UPDATED_AT)
            .principal(DEFAULT_PRINCIPAL);
        // Add required entity
        Produit produit;
        if (TestUtil.findAll(em, Produit.class).isEmpty()) {
            produit = ProduitResourceIT.createEntity(em);
            em.persist(produit);
            em.flush();
        } else {
            produit = TestUtil.findAll(em, Produit.class).get(0);
        }
        fournisseurProduit.setProduit(produit);
        // Add required entity
        Fournisseur fournisseur;
        if (TestUtil.findAll(em, Fournisseur.class).isEmpty()) {
            fournisseur = FournisseurResourceIT.createEntity(em);
            em.persist(fournisseur);
            em.flush();
        } else {
            fournisseur = TestUtil.findAll(em, Fournisseur.class).get(0);
        }
        fournisseurProduit.setFournisseur(fournisseur);
        return fournisseurProduit;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FournisseurProduit createUpdatedEntity(EntityManager em) {
        FournisseurProduit fournisseurProduit = new FournisseurProduit()
            .codeCip(UPDATED_CODE_CIP)
            .prixAchat(UPDATED_PRIX_ACHAT)
            .prixUni(UPDATED_PRIX_UNI)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .principal(UPDATED_PRINCIPAL);
        // Add required entity
        Produit produit;
        if (TestUtil.findAll(em, Produit.class).isEmpty()) {
            produit = ProduitResourceIT.createUpdatedEntity(em);
            em.persist(produit);
            em.flush();
        } else {
            produit = TestUtil.findAll(em, Produit.class).get(0);
        }
        fournisseurProduit.setProduit(produit);
        // Add required entity
        Fournisseur fournisseur;
        if (TestUtil.findAll(em, Fournisseur.class).isEmpty()) {
            fournisseur = FournisseurResourceIT.createUpdatedEntity(em);
            em.persist(fournisseur);
            em.flush();
        } else {
            fournisseur = TestUtil.findAll(em, Fournisseur.class).get(0);
        }
        fournisseurProduit.setFournisseur(fournisseur);
        return fournisseurProduit;
    }

    @BeforeEach
    public void initTest() {
        fournisseurProduit = createEntity(em);
    }

    @Test
    @Transactional
    public void getAllFournisseurProduits() throws Exception {
        // Initialize the database
        fournisseurProduitRepository.saveAndFlush(fournisseurProduit);

        // Get all the fournisseurProduitList
        restFournisseurProduitMockMvc.perform(get("/api/fournisseur-produits?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fournisseurProduit.getId().intValue())))
            .andExpect(jsonPath("$.[*].codeCip").value(hasItem(DEFAULT_CODE_CIP)))
            .andExpect(jsonPath("$.[*].prixAchat").value(hasItem(DEFAULT_PRIX_ACHAT)))
            .andExpect(jsonPath("$.[*].prixUni").value(hasItem(DEFAULT_PRIX_UNI)))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(DEFAULT_UPDATED_AT.toString())))
            .andExpect(jsonPath("$.[*].principal").value(hasItem(DEFAULT_PRINCIPAL.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getFournisseurProduit() throws Exception {
        // Initialize the database
        fournisseurProduitRepository.saveAndFlush(fournisseurProduit);

        // Get the fournisseurProduit
        restFournisseurProduitMockMvc.perform(get("/api/fournisseur-produits/{id}", fournisseurProduit.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(fournisseurProduit.getId().intValue()))
            .andExpect(jsonPath("$.codeCip").value(DEFAULT_CODE_CIP))
            .andExpect(jsonPath("$.prixAchat").value(DEFAULT_PRIX_ACHAT))
            .andExpect(jsonPath("$.prixUni").value(DEFAULT_PRIX_UNI))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.updatedAt").value(DEFAULT_UPDATED_AT.toString()))
            .andExpect(jsonPath("$.principal").value(DEFAULT_PRINCIPAL.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingFournisseurProduit() throws Exception {
        // Get the fournisseurProduit
        restFournisseurProduitMockMvc.perform(get("/api/fournisseur-produits/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }
}
