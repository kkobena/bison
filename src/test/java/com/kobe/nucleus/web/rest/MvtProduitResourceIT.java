package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.NucleusApp;
import com.kobe.nucleus.domain.MvtProduit;
import com.kobe.nucleus.domain.StockProduit;
import com.kobe.nucleus.repository.MvtProduitRepository;
import com.kobe.nucleus.service.MvtProduitService;
import com.kobe.nucleus.service.dto.MvtProduitDTO;
import com.kobe.nucleus.service.mapper.MvtProduitMapper;

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
import java.time.LocalDate;
import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link MvtProduitResource} REST controller.
 */
@SpringBootTest(classes = NucleusApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class MvtProduitResourceIT {

    private static final LocalDate DEFAULT_MVT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_MVT_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Instant DEFAULT_CREATED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Boolean DEFAULT_CHECKED = false;
    private static final Boolean UPDATED_CHECKED = true;

    private static final Integer DEFAULT_QTE_DEBUT = 1;
    private static final Integer UPDATED_QTE_DEBUT = 2;

    private static final Integer DEFAULT_QTE_FINALE = 1;
    private static final Integer UPDATED_QTE_FINALE = 2;

    private static final Integer DEFAULT_PRIX_PAF = 1;
    private static final Integer UPDATED_PRIX_PAF = 2;

    private static final Integer DEFAULT_PRIX_UNI = 1;
    private static final Integer UPDATED_PRIX_UNI = 2;

    private static final Integer DEFAULT_VALEUR_TVA = 1;
    private static final Integer UPDATED_VALEUR_TVA = 2;

    private static final Integer DEFAULT_MONTANT_TVA = 1;
    private static final Integer UPDATED_MONTANT_TVA = 2;

    private static final String DEFAULT_PKEY = "AAAAAAAAAA";
    private static final String UPDATED_PKEY = "BBBBBBBBBB";

    @Autowired
    private MvtProduitRepository mvtProduitRepository;

    @Autowired
    private MvtProduitMapper mvtProduitMapper;

    @Autowired
    private MvtProduitService mvtProduitService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMvtProduitMockMvc;

    private MvtProduit mvtProduit;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MvtProduit createEntity(EntityManager em) {
        MvtProduit mvtProduit = new MvtProduit()
            .mvtDate(DEFAULT_MVT_DATE)
            .createdAt(DEFAULT_CREATED_AT)
            .checked(DEFAULT_CHECKED)
            .qteDebut(DEFAULT_QTE_DEBUT)
            .qteFinale(DEFAULT_QTE_FINALE)
            .prixPaf(DEFAULT_PRIX_PAF)
            .prixUni(DEFAULT_PRIX_UNI)
            .valeurTva(DEFAULT_VALEUR_TVA)
            .montantTva(DEFAULT_MONTANT_TVA)
            .pkey(DEFAULT_PKEY);
        // Add required entity
        StockProduit stockProduit;
        if (TestUtil.findAll(em, StockProduit.class).isEmpty()) {
            stockProduit = StockProduitResourceIT.createEntity(em);
            em.persist(stockProduit);
            em.flush();
        } else {
            stockProduit = TestUtil.findAll(em, StockProduit.class).get(0);
        }
        mvtProduit.setProduit(stockProduit);
        return mvtProduit;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MvtProduit createUpdatedEntity(EntityManager em) {
        MvtProduit mvtProduit = new MvtProduit()
            .mvtDate(UPDATED_MVT_DATE)
            .createdAt(UPDATED_CREATED_AT)
            .checked(UPDATED_CHECKED)
            .qteDebut(UPDATED_QTE_DEBUT)
            .qteFinale(UPDATED_QTE_FINALE)
            .prixPaf(UPDATED_PRIX_PAF)
            .prixUni(UPDATED_PRIX_UNI)
            .valeurTva(UPDATED_VALEUR_TVA)
            .montantTva(UPDATED_MONTANT_TVA)
            .pkey(UPDATED_PKEY);
        // Add required entity
        StockProduit stockProduit;
        if (TestUtil.findAll(em, StockProduit.class).isEmpty()) {
            stockProduit = StockProduitResourceIT.createUpdatedEntity(em);
            em.persist(stockProduit);
            em.flush();
        } else {
            stockProduit = TestUtil.findAll(em, StockProduit.class).get(0);
        }
        mvtProduit.setProduit(stockProduit);
        return mvtProduit;
    }

    @BeforeEach
    public void initTest() {
        mvtProduit = createEntity(em);
    }

    @Test
    @Transactional
    public void createMvtProduit() throws Exception {
        int databaseSizeBeforeCreate = mvtProduitRepository.findAll().size();
        // Create the MvtProduit
        MvtProduitDTO mvtProduitDTO = mvtProduitMapper.toDto(mvtProduit);
        restMvtProduitMockMvc.perform(post("/api/mvt-produits").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mvtProduitDTO)))
            .andExpect(status().isCreated());

        // Validate the MvtProduit in the database
        List<MvtProduit> mvtProduitList = mvtProduitRepository.findAll();
        assertThat(mvtProduitList).hasSize(databaseSizeBeforeCreate + 1);
        MvtProduit testMvtProduit = mvtProduitList.get(mvtProduitList.size() - 1);
        assertThat(testMvtProduit.getMvtDate()).isEqualTo(DEFAULT_MVT_DATE);
        assertThat(testMvtProduit.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testMvtProduit.isChecked()).isEqualTo(DEFAULT_CHECKED);
        assertThat(testMvtProduit.getQteDebut()).isEqualTo(DEFAULT_QTE_DEBUT);
        assertThat(testMvtProduit.getQteFinale()).isEqualTo(DEFAULT_QTE_FINALE);
        assertThat(testMvtProduit.getPrixPaf()).isEqualTo(DEFAULT_PRIX_PAF);
        assertThat(testMvtProduit.getPrixUni()).isEqualTo(DEFAULT_PRIX_UNI);
        assertThat(testMvtProduit.getValeurTva()).isEqualTo(DEFAULT_VALEUR_TVA);
        assertThat(testMvtProduit.getMontantTva()).isEqualTo(DEFAULT_MONTANT_TVA);
        assertThat(testMvtProduit.getPkey()).isEqualTo(DEFAULT_PKEY);
    }

    @Test
    @Transactional
    public void createMvtProduitWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mvtProduitRepository.findAll().size();

        // Create the MvtProduit with an existing ID
        mvtProduit.setId(1L);
        MvtProduitDTO mvtProduitDTO = mvtProduitMapper.toDto(mvtProduit);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMvtProduitMockMvc.perform(post("/api/mvt-produits").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mvtProduitDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MvtProduit in the database
        List<MvtProduit> mvtProduitList = mvtProduitRepository.findAll();
        assertThat(mvtProduitList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkMvtDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = mvtProduitRepository.findAll().size();
        // set the field null
        mvtProduit.setMvtDate(null);

        // Create the MvtProduit, which fails.
        MvtProduitDTO mvtProduitDTO = mvtProduitMapper.toDto(mvtProduit);


        restMvtProduitMockMvc.perform(post("/api/mvt-produits").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mvtProduitDTO)))
            .andExpect(status().isBadRequest());

        List<MvtProduit> mvtProduitList = mvtProduitRepository.findAll();
        assertThat(mvtProduitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = mvtProduitRepository.findAll().size();
        // set the field null
        mvtProduit.setCreatedAt(null);

        // Create the MvtProduit, which fails.
        MvtProduitDTO mvtProduitDTO = mvtProduitMapper.toDto(mvtProduit);


        restMvtProduitMockMvc.perform(post("/api/mvt-produits").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mvtProduitDTO)))
            .andExpect(status().isBadRequest());

        List<MvtProduit> mvtProduitList = mvtProduitRepository.findAll();
        assertThat(mvtProduitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCheckedIsRequired() throws Exception {
        int databaseSizeBeforeTest = mvtProduitRepository.findAll().size();
        // set the field null
        mvtProduit.setChecked(null);

        // Create the MvtProduit, which fails.
        MvtProduitDTO mvtProduitDTO = mvtProduitMapper.toDto(mvtProduit);


        restMvtProduitMockMvc.perform(post("/api/mvt-produits").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mvtProduitDTO)))
            .andExpect(status().isBadRequest());

        List<MvtProduit> mvtProduitList = mvtProduitRepository.findAll();
        assertThat(mvtProduitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQteDebutIsRequired() throws Exception {
        int databaseSizeBeforeTest = mvtProduitRepository.findAll().size();
        // set the field null
        mvtProduit.setQteDebut(null);

        // Create the MvtProduit, which fails.
        MvtProduitDTO mvtProduitDTO = mvtProduitMapper.toDto(mvtProduit);


        restMvtProduitMockMvc.perform(post("/api/mvt-produits").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mvtProduitDTO)))
            .andExpect(status().isBadRequest());

        List<MvtProduit> mvtProduitList = mvtProduitRepository.findAll();
        assertThat(mvtProduitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQteFinaleIsRequired() throws Exception {
        int databaseSizeBeforeTest = mvtProduitRepository.findAll().size();
        // set the field null
        mvtProduit.setQteFinale(null);

        // Create the MvtProduit, which fails.
        MvtProduitDTO mvtProduitDTO = mvtProduitMapper.toDto(mvtProduit);


        restMvtProduitMockMvc.perform(post("/api/mvt-produits").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mvtProduitDTO)))
            .andExpect(status().isBadRequest());

        List<MvtProduit> mvtProduitList = mvtProduitRepository.findAll();
        assertThat(mvtProduitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPrixPafIsRequired() throws Exception {
        int databaseSizeBeforeTest = mvtProduitRepository.findAll().size();
        // set the field null
        mvtProduit.setPrixPaf(null);

        // Create the MvtProduit, which fails.
        MvtProduitDTO mvtProduitDTO = mvtProduitMapper.toDto(mvtProduit);


        restMvtProduitMockMvc.perform(post("/api/mvt-produits").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mvtProduitDTO)))
            .andExpect(status().isBadRequest());

        List<MvtProduit> mvtProduitList = mvtProduitRepository.findAll();
        assertThat(mvtProduitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPrixUniIsRequired() throws Exception {
        int databaseSizeBeforeTest = mvtProduitRepository.findAll().size();
        // set the field null
        mvtProduit.setPrixUni(null);

        // Create the MvtProduit, which fails.
        MvtProduitDTO mvtProduitDTO = mvtProduitMapper.toDto(mvtProduit);


        restMvtProduitMockMvc.perform(post("/api/mvt-produits").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mvtProduitDTO)))
            .andExpect(status().isBadRequest());

        List<MvtProduit> mvtProduitList = mvtProduitRepository.findAll();
        assertThat(mvtProduitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkValeurTvaIsRequired() throws Exception {
        int databaseSizeBeforeTest = mvtProduitRepository.findAll().size();
        // set the field null
        mvtProduit.setValeurTva(null);

        // Create the MvtProduit, which fails.
        MvtProduitDTO mvtProduitDTO = mvtProduitMapper.toDto(mvtProduit);


        restMvtProduitMockMvc.perform(post("/api/mvt-produits").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mvtProduitDTO)))
            .andExpect(status().isBadRequest());

        List<MvtProduit> mvtProduitList = mvtProduitRepository.findAll();
        assertThat(mvtProduitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMontantTvaIsRequired() throws Exception {
        int databaseSizeBeforeTest = mvtProduitRepository.findAll().size();
        // set the field null
        mvtProduit.setMontantTva(null);

        // Create the MvtProduit, which fails.
        MvtProduitDTO mvtProduitDTO = mvtProduitMapper.toDto(mvtProduit);


        restMvtProduitMockMvc.perform(post("/api/mvt-produits").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mvtProduitDTO)))
            .andExpect(status().isBadRequest());

        List<MvtProduit> mvtProduitList = mvtProduitRepository.findAll();
        assertThat(mvtProduitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPkeyIsRequired() throws Exception {
        int databaseSizeBeforeTest = mvtProduitRepository.findAll().size();
        // set the field null
        mvtProduit.setPkey(null);

        // Create the MvtProduit, which fails.
        MvtProduitDTO mvtProduitDTO = mvtProduitMapper.toDto(mvtProduit);


        restMvtProduitMockMvc.perform(post("/api/mvt-produits").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mvtProduitDTO)))
            .andExpect(status().isBadRequest());

        List<MvtProduit> mvtProduitList = mvtProduitRepository.findAll();
        assertThat(mvtProduitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMvtProduits() throws Exception {
        // Initialize the database
        mvtProduitRepository.saveAndFlush(mvtProduit);

        // Get all the mvtProduitList
        restMvtProduitMockMvc.perform(get("/api/mvt-produits?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mvtProduit.getId().intValue())))
            .andExpect(jsonPath("$.[*].mvtDate").value(hasItem(DEFAULT_MVT_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].checked").value(hasItem(DEFAULT_CHECKED.booleanValue())))
            .andExpect(jsonPath("$.[*].qteDebut").value(hasItem(DEFAULT_QTE_DEBUT)))
            .andExpect(jsonPath("$.[*].qteFinale").value(hasItem(DEFAULT_QTE_FINALE)))
            .andExpect(jsonPath("$.[*].prixPaf").value(hasItem(DEFAULT_PRIX_PAF)))
            .andExpect(jsonPath("$.[*].prixUni").value(hasItem(DEFAULT_PRIX_UNI)))
            .andExpect(jsonPath("$.[*].valeurTva").value(hasItem(DEFAULT_VALEUR_TVA)))
            .andExpect(jsonPath("$.[*].montantTva").value(hasItem(DEFAULT_MONTANT_TVA)))
            .andExpect(jsonPath("$.[*].pkey").value(hasItem(DEFAULT_PKEY)));
    }
    
    @Test
    @Transactional
    public void getMvtProduit() throws Exception {
        // Initialize the database
        mvtProduitRepository.saveAndFlush(mvtProduit);

        // Get the mvtProduit
        restMvtProduitMockMvc.perform(get("/api/mvt-produits/{id}", mvtProduit.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(mvtProduit.getId().intValue()))
            .andExpect(jsonPath("$.mvtDate").value(DEFAULT_MVT_DATE.toString()))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.checked").value(DEFAULT_CHECKED.booleanValue()))
            .andExpect(jsonPath("$.qteDebut").value(DEFAULT_QTE_DEBUT))
            .andExpect(jsonPath("$.qteFinale").value(DEFAULT_QTE_FINALE))
            .andExpect(jsonPath("$.prixPaf").value(DEFAULT_PRIX_PAF))
            .andExpect(jsonPath("$.prixUni").value(DEFAULT_PRIX_UNI))
            .andExpect(jsonPath("$.valeurTva").value(DEFAULT_VALEUR_TVA))
            .andExpect(jsonPath("$.montantTva").value(DEFAULT_MONTANT_TVA))
            .andExpect(jsonPath("$.pkey").value(DEFAULT_PKEY));
    }
    @Test
    @Transactional
    public void getNonExistingMvtProduit() throws Exception {
        // Get the mvtProduit
        restMvtProduitMockMvc.perform(get("/api/mvt-produits/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMvtProduit() throws Exception {
        // Initialize the database
        mvtProduitRepository.saveAndFlush(mvtProduit);

        int databaseSizeBeforeUpdate = mvtProduitRepository.findAll().size();

        // Update the mvtProduit
        MvtProduit updatedMvtProduit = mvtProduitRepository.findById(mvtProduit.getId()).get();
        // Disconnect from session so that the updates on updatedMvtProduit are not directly saved in db
        em.detach(updatedMvtProduit);
        updatedMvtProduit
            .mvtDate(UPDATED_MVT_DATE)
            .createdAt(UPDATED_CREATED_AT)
            .checked(UPDATED_CHECKED)
            .qteDebut(UPDATED_QTE_DEBUT)
            .qteFinale(UPDATED_QTE_FINALE)
            .prixPaf(UPDATED_PRIX_PAF)
            .prixUni(UPDATED_PRIX_UNI)
            .valeurTva(UPDATED_VALEUR_TVA)
            .montantTva(UPDATED_MONTANT_TVA)
            .pkey(UPDATED_PKEY);
        MvtProduitDTO mvtProduitDTO = mvtProduitMapper.toDto(updatedMvtProduit);

        restMvtProduitMockMvc.perform(put("/api/mvt-produits").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mvtProduitDTO)))
            .andExpect(status().isOk());

        // Validate the MvtProduit in the database
        List<MvtProduit> mvtProduitList = mvtProduitRepository.findAll();
        assertThat(mvtProduitList).hasSize(databaseSizeBeforeUpdate);
        MvtProduit testMvtProduit = mvtProduitList.get(mvtProduitList.size() - 1);
        assertThat(testMvtProduit.getMvtDate()).isEqualTo(UPDATED_MVT_DATE);
        assertThat(testMvtProduit.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testMvtProduit.isChecked()).isEqualTo(UPDATED_CHECKED);
        assertThat(testMvtProduit.getQteDebut()).isEqualTo(UPDATED_QTE_DEBUT);
        assertThat(testMvtProduit.getQteFinale()).isEqualTo(UPDATED_QTE_FINALE);
        assertThat(testMvtProduit.getPrixPaf()).isEqualTo(UPDATED_PRIX_PAF);
        assertThat(testMvtProduit.getPrixUni()).isEqualTo(UPDATED_PRIX_UNI);
        assertThat(testMvtProduit.getValeurTva()).isEqualTo(UPDATED_VALEUR_TVA);
        assertThat(testMvtProduit.getMontantTva()).isEqualTo(UPDATED_MONTANT_TVA);
        assertThat(testMvtProduit.getPkey()).isEqualTo(UPDATED_PKEY);
    }

    @Test
    @Transactional
    public void updateNonExistingMvtProduit() throws Exception {
        int databaseSizeBeforeUpdate = mvtProduitRepository.findAll().size();

        // Create the MvtProduit
        MvtProduitDTO mvtProduitDTO = mvtProduitMapper.toDto(mvtProduit);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMvtProduitMockMvc.perform(put("/api/mvt-produits").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mvtProduitDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MvtProduit in the database
        List<MvtProduit> mvtProduitList = mvtProduitRepository.findAll();
        assertThat(mvtProduitList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMvtProduit() throws Exception {
        // Initialize the database
        mvtProduitRepository.saveAndFlush(mvtProduit);

        int databaseSizeBeforeDelete = mvtProduitRepository.findAll().size();

        // Delete the mvtProduit
        restMvtProduitMockMvc.perform(delete("/api/mvt-produits/{id}", mvtProduit.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MvtProduit> mvtProduitList = mvtProduitRepository.findAll();
        assertThat(mvtProduitList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
