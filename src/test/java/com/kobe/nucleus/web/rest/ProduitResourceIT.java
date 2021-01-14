package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.NucleusApp;
import com.kobe.nucleus.domain.Produit;
import com.kobe.nucleus.repository.ProduitRepository;
import com.kobe.nucleus.service.ProduitService;
import com.kobe.nucleus.service.dto.ProduitDTO;
import com.kobe.nucleus.service.mapper.ProduitMapper;

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

import com.kobe.nucleus.domain.enumeration.Status;
/**
 * Integration tests for the {@link ProduitResource} REST controller.
 */
@SpringBootTest(classes = NucleusApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ProduitResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    private static final Status DEFAULT_STATUS = Status.ACTIVE;
    private static final Status UPDATED_STATUS = Status.ENATTENTE;

    private static final Instant DEFAULT_CREATED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_UPDATED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_QTY_APPRO = 1;
    private static final Integer UPDATED_QTY_APPRO = 2;

    private static final Integer DEFAULT_QTY_SEUIL_MINI = 1;
    private static final Integer UPDATED_QTY_SEUIL_MINI = 2;

    private static final Boolean DEFAULT_DATEPEREMPTION = false;
    private static final Boolean UPDATED_DATEPEREMPTION = true;

    private static final Boolean DEFAULT_CHIFFRE = false;
    private static final Boolean UPDATED_CHIFFRE = true;

    private static final String DEFAULT_CODE_CIP = "AAAAAAAAAA";
    private static final String UPDATED_CODE_CIP = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_EAN = "AAAAAAAAAA";
    private static final String UPDATED_CODE_EAN = "BBBBBBBBBB";

    private static final Integer DEFAULT_QTY_DETAILS = 1;
    private static final Integer UPDATED_QTY_DETAILS = 2;

    private static final Boolean DEFAULT_DECONDITIONNABLE = false;
    private static final Boolean UPDATED_DECONDITIONNABLE = true;

    private static final Integer DEFAULT_PRIX_PAF = 1;
    private static final Integer UPDATED_PRIX_PAF = 2;

    private static final Integer DEFAULT_PRIX_UNI = 1;
    private static final Integer UPDATED_PRIX_UNI = 2;

    private static final Integer DEFAULT_PRIX_MNP = 1;
    private static final Integer UPDATED_PRIX_MNP = 2;

    @Autowired
    private ProduitRepository produitRepository;

    @Autowired
    private ProduitMapper produitMapper;

    @Autowired
    private ProduitService produitService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProduitMockMvc;

    private Produit produit;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Produit createEntity(EntityManager em) {
        Produit produit = new Produit()
            .libelle(DEFAULT_LIBELLE)
            .status(DEFAULT_STATUS)
            .createdAt(DEFAULT_CREATED_AT)
            .updatedAt(DEFAULT_UPDATED_AT)
            .qtyAppro(DEFAULT_QTY_APPRO)
            .qtySeuilMini(DEFAULT_QTY_SEUIL_MINI)
            .dateperemption(DEFAULT_DATEPEREMPTION)
            .chiffre(DEFAULT_CHIFFRE)
            .codeCip(DEFAULT_CODE_CIP)
            .codeEan(DEFAULT_CODE_EAN)
            .qtyDetails(DEFAULT_QTY_DETAILS)
            .deconditionnable(DEFAULT_DECONDITIONNABLE)
            .prixPaf(DEFAULT_PRIX_PAF)
            .prixUni(DEFAULT_PRIX_UNI)
            .prixMnp(DEFAULT_PRIX_MNP);
        return produit;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Produit createUpdatedEntity(EntityManager em) {
        Produit produit = new Produit()
            .libelle(UPDATED_LIBELLE)
            .status(UPDATED_STATUS)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .qtyAppro(UPDATED_QTY_APPRO)
            .qtySeuilMini(UPDATED_QTY_SEUIL_MINI)
            .dateperemption(UPDATED_DATEPEREMPTION)
            .chiffre(UPDATED_CHIFFRE)
            .codeCip(UPDATED_CODE_CIP)
            .codeEan(UPDATED_CODE_EAN)
            .qtyDetails(UPDATED_QTY_DETAILS)
            .deconditionnable(UPDATED_DECONDITIONNABLE)
            .prixPaf(UPDATED_PRIX_PAF)
            .prixUni(UPDATED_PRIX_UNI)
            .prixMnp(UPDATED_PRIX_MNP);
        return produit;
    }

    @BeforeEach
    public void initTest() {
        produit = createEntity(em);
    }

    @Test
    @Transactional
    public void createProduit() throws Exception {
        int databaseSizeBeforeCreate = produitRepository.findAll().size();
        // Create the Produit
        ProduitDTO produitDTO = produitMapper.toDto(produit);
        restProduitMockMvc.perform(post("/api/produits").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(produitDTO)))
            .andExpect(status().isCreated());

        // Validate the Produit in the database
        List<Produit> produitList = produitRepository.findAll();
        assertThat(produitList).hasSize(databaseSizeBeforeCreate + 1);
        Produit testProduit = produitList.get(produitList.size() - 1);
        assertThat(testProduit.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
        assertThat(testProduit.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testProduit.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testProduit.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testProduit.getQtyAppro()).isEqualTo(DEFAULT_QTY_APPRO);
        assertThat(testProduit.getQtySeuilMini()).isEqualTo(DEFAULT_QTY_SEUIL_MINI);
        assertThat(testProduit.isDateperemption()).isEqualTo(DEFAULT_DATEPEREMPTION);
        assertThat(testProduit.isChiffre()).isEqualTo(DEFAULT_CHIFFRE);
        assertThat(testProduit.getCodeCip()).isEqualTo(DEFAULT_CODE_CIP);
        assertThat(testProduit.getCodeEan()).isEqualTo(DEFAULT_CODE_EAN);
        assertThat(testProduit.getQtyDetails()).isEqualTo(DEFAULT_QTY_DETAILS);
        assertThat(testProduit.isDeconditionnable()).isEqualTo(DEFAULT_DECONDITIONNABLE);
        assertThat(testProduit.getPrixPaf()).isEqualTo(DEFAULT_PRIX_PAF);
        assertThat(testProduit.getPrixUni()).isEqualTo(DEFAULT_PRIX_UNI);
        assertThat(testProduit.getPrixMnp()).isEqualTo(DEFAULT_PRIX_MNP);
    }

    @Test
    @Transactional
    public void createProduitWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = produitRepository.findAll().size();

        // Create the Produit with an existing ID
        produit.setId(1L);
        ProduitDTO produitDTO = produitMapper.toDto(produit);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProduitMockMvc.perform(post("/api/produits").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(produitDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Produit in the database
        List<Produit> produitList = produitRepository.findAll();
        assertThat(produitList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLibelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = produitRepository.findAll().size();
        // set the field null
        produit.setLibelle(null);

        // Create the Produit, which fails.
        ProduitDTO produitDTO = produitMapper.toDto(produit);


        restProduitMockMvc.perform(post("/api/produits").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(produitDTO)))
            .andExpect(status().isBadRequest());

        List<Produit> produitList = produitRepository.findAll();
        assertThat(produitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = produitRepository.findAll().size();
        // set the field null
        produit.setStatus(null);

        // Create the Produit, which fails.
        ProduitDTO produitDTO = produitMapper.toDto(produit);


        restProduitMockMvc.perform(post("/api/produits").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(produitDTO)))
            .andExpect(status().isBadRequest());

        List<Produit> produitList = produitRepository.findAll();
        assertThat(produitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = produitRepository.findAll().size();
        // set the field null
        produit.setCreatedAt(null);

        // Create the Produit, which fails.
        ProduitDTO produitDTO = produitMapper.toDto(produit);


        restProduitMockMvc.perform(post("/api/produits").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(produitDTO)))
            .andExpect(status().isBadRequest());

        List<Produit> produitList = produitRepository.findAll();
        assertThat(produitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUpdatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = produitRepository.findAll().size();
        // set the field null
        produit.setUpdatedAt(null);

        // Create the Produit, which fails.
        ProduitDTO produitDTO = produitMapper.toDto(produit);


        restProduitMockMvc.perform(post("/api/produits").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(produitDTO)))
            .andExpect(status().isBadRequest());

        List<Produit> produitList = produitRepository.findAll();
        assertThat(produitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCodeCipIsRequired() throws Exception {
        int databaseSizeBeforeTest = produitRepository.findAll().size();
        // set the field null
        produit.setCodeCip(null);

        // Create the Produit, which fails.
        ProduitDTO produitDTO = produitMapper.toDto(produit);


        restProduitMockMvc.perform(post("/api/produits").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(produitDTO)))
            .andExpect(status().isBadRequest());

        List<Produit> produitList = produitRepository.findAll();
        assertThat(produitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDeconditionnableIsRequired() throws Exception {
        int databaseSizeBeforeTest = produitRepository.findAll().size();
        // set the field null
        produit.setDeconditionnable(null);

        // Create the Produit, which fails.
        ProduitDTO produitDTO = produitMapper.toDto(produit);


        restProduitMockMvc.perform(post("/api/produits").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(produitDTO)))
            .andExpect(status().isBadRequest());

        List<Produit> produitList = produitRepository.findAll();
        assertThat(produitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPrixPafIsRequired() throws Exception {
        int databaseSizeBeforeTest = produitRepository.findAll().size();
        // set the field null
        produit.setPrixPaf(null);

        // Create the Produit, which fails.
        ProduitDTO produitDTO = produitMapper.toDto(produit);


        restProduitMockMvc.perform(post("/api/produits").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(produitDTO)))
            .andExpect(status().isBadRequest());

        List<Produit> produitList = produitRepository.findAll();
        assertThat(produitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPrixUniIsRequired() throws Exception {
        int databaseSizeBeforeTest = produitRepository.findAll().size();
        // set the field null
        produit.setPrixUni(null);

        // Create the Produit, which fails.
        ProduitDTO produitDTO = produitMapper.toDto(produit);


        restProduitMockMvc.perform(post("/api/produits").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(produitDTO)))
            .andExpect(status().isBadRequest());

        List<Produit> produitList = produitRepository.findAll();
        assertThat(produitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPrixMnpIsRequired() throws Exception {
        int databaseSizeBeforeTest = produitRepository.findAll().size();
        // set the field null
        produit.setPrixMnp(null);

        // Create the Produit, which fails.
        ProduitDTO produitDTO = produitMapper.toDto(produit);


        restProduitMockMvc.perform(post("/api/produits").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(produitDTO)))
            .andExpect(status().isBadRequest());

        List<Produit> produitList = produitRepository.findAll();
        assertThat(produitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllProduits() throws Exception {
        // Initialize the database
        produitRepository.saveAndFlush(produit);

        // Get all the produitList
        restProduitMockMvc.perform(get("/api/produits?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(produit.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(DEFAULT_UPDATED_AT.toString())))
            .andExpect(jsonPath("$.[*].qtyAppro").value(hasItem(DEFAULT_QTY_APPRO)))
            .andExpect(jsonPath("$.[*].qtySeuilMini").value(hasItem(DEFAULT_QTY_SEUIL_MINI)))
            .andExpect(jsonPath("$.[*].dateperemption").value(hasItem(DEFAULT_DATEPEREMPTION.booleanValue())))
            .andExpect(jsonPath("$.[*].chiffre").value(hasItem(DEFAULT_CHIFFRE.booleanValue())))
            .andExpect(jsonPath("$.[*].codeCip").value(hasItem(DEFAULT_CODE_CIP)))
            .andExpect(jsonPath("$.[*].codeEan").value(hasItem(DEFAULT_CODE_EAN)))
            .andExpect(jsonPath("$.[*].qtyDetails").value(hasItem(DEFAULT_QTY_DETAILS)))
            .andExpect(jsonPath("$.[*].deconditionnable").value(hasItem(DEFAULT_DECONDITIONNABLE.booleanValue())))
            .andExpect(jsonPath("$.[*].prixPaf").value(hasItem(DEFAULT_PRIX_PAF)))
            .andExpect(jsonPath("$.[*].prixUni").value(hasItem(DEFAULT_PRIX_UNI)))
            .andExpect(jsonPath("$.[*].prixMnp").value(hasItem(DEFAULT_PRIX_MNP)));
    }
    
    @Test
    @Transactional
    public void getProduit() throws Exception {
        // Initialize the database
        produitRepository.saveAndFlush(produit);

        // Get the produit
        restProduitMockMvc.perform(get("/api/produits/{id}", produit.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(produit.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.updatedAt").value(DEFAULT_UPDATED_AT.toString()))
            .andExpect(jsonPath("$.qtyAppro").value(DEFAULT_QTY_APPRO))
            .andExpect(jsonPath("$.qtySeuilMini").value(DEFAULT_QTY_SEUIL_MINI))
            .andExpect(jsonPath("$.dateperemption").value(DEFAULT_DATEPEREMPTION.booleanValue()))
            .andExpect(jsonPath("$.chiffre").value(DEFAULT_CHIFFRE.booleanValue()))
            .andExpect(jsonPath("$.codeCip").value(DEFAULT_CODE_CIP))
            .andExpect(jsonPath("$.codeEan").value(DEFAULT_CODE_EAN))
            .andExpect(jsonPath("$.qtyDetails").value(DEFAULT_QTY_DETAILS))
            .andExpect(jsonPath("$.deconditionnable").value(DEFAULT_DECONDITIONNABLE.booleanValue()))
            .andExpect(jsonPath("$.prixPaf").value(DEFAULT_PRIX_PAF))
            .andExpect(jsonPath("$.prixUni").value(DEFAULT_PRIX_UNI))
            .andExpect(jsonPath("$.prixMnp").value(DEFAULT_PRIX_MNP));
    }
    @Test
    @Transactional
    public void getNonExistingProduit() throws Exception {
        // Get the produit
        restProduitMockMvc.perform(get("/api/produits/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProduit() throws Exception {
        // Initialize the database
        produitRepository.saveAndFlush(produit);

        int databaseSizeBeforeUpdate = produitRepository.findAll().size();

        // Update the produit
        Produit updatedProduit = produitRepository.findById(produit.getId()).get();
        // Disconnect from session so that the updates on updatedProduit are not directly saved in db
        em.detach(updatedProduit);
        updatedProduit
            .libelle(UPDATED_LIBELLE)
            .status(UPDATED_STATUS)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .qtyAppro(UPDATED_QTY_APPRO)
            .qtySeuilMini(UPDATED_QTY_SEUIL_MINI)
            .dateperemption(UPDATED_DATEPEREMPTION)
            .chiffre(UPDATED_CHIFFRE)
            .codeCip(UPDATED_CODE_CIP)
            .codeEan(UPDATED_CODE_EAN)
            .qtyDetails(UPDATED_QTY_DETAILS)
            .deconditionnable(UPDATED_DECONDITIONNABLE)
            .prixPaf(UPDATED_PRIX_PAF)
            .prixUni(UPDATED_PRIX_UNI)
            .prixMnp(UPDATED_PRIX_MNP);
        ProduitDTO produitDTO = produitMapper.toDto(updatedProduit);

        restProduitMockMvc.perform(put("/api/produits").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(produitDTO)))
            .andExpect(status().isOk());

        // Validate the Produit in the database
        List<Produit> produitList = produitRepository.findAll();
        assertThat(produitList).hasSize(databaseSizeBeforeUpdate);
        Produit testProduit = produitList.get(produitList.size() - 1);
        assertThat(testProduit.getLibelle()).isEqualTo(UPDATED_LIBELLE);
        assertThat(testProduit.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testProduit.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testProduit.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testProduit.getQtyAppro()).isEqualTo(UPDATED_QTY_APPRO);
        assertThat(testProduit.getQtySeuilMini()).isEqualTo(UPDATED_QTY_SEUIL_MINI);
        assertThat(testProduit.isDateperemption()).isEqualTo(UPDATED_DATEPEREMPTION);
        assertThat(testProduit.isChiffre()).isEqualTo(UPDATED_CHIFFRE);
        assertThat(testProduit.getCodeCip()).isEqualTo(UPDATED_CODE_CIP);
        assertThat(testProduit.getCodeEan()).isEqualTo(UPDATED_CODE_EAN);
        assertThat(testProduit.getQtyDetails()).isEqualTo(UPDATED_QTY_DETAILS);
        assertThat(testProduit.isDeconditionnable()).isEqualTo(UPDATED_DECONDITIONNABLE);
        assertThat(testProduit.getPrixPaf()).isEqualTo(UPDATED_PRIX_PAF);
        assertThat(testProduit.getPrixUni()).isEqualTo(UPDATED_PRIX_UNI);
        assertThat(testProduit.getPrixMnp()).isEqualTo(UPDATED_PRIX_MNP);
    }

    @Test
    @Transactional
    public void updateNonExistingProduit() throws Exception {
        int databaseSizeBeforeUpdate = produitRepository.findAll().size();

        // Create the Produit
        ProduitDTO produitDTO = produitMapper.toDto(produit);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProduitMockMvc.perform(put("/api/produits").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(produitDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Produit in the database
        List<Produit> produitList = produitRepository.findAll();
        assertThat(produitList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProduit() throws Exception {
        // Initialize the database
        produitRepository.saveAndFlush(produit);

        int databaseSizeBeforeDelete = produitRepository.findAll().size();

        // Delete the produit
        restProduitMockMvc.perform(delete("/api/produits/{id}", produit.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Produit> produitList = produitRepository.findAll();
        assertThat(produitList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
