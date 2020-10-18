package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.NucleusApp;
import com.kobe.nucleus.domain.LignesVente;
import com.kobe.nucleus.domain.StockProduit;
import com.kobe.nucleus.repository.LignesVenteRepository;
import com.kobe.nucleus.service.LignesVenteService;
import com.kobe.nucleus.service.dto.LignesVenteDTO;
import com.kobe.nucleus.service.mapper.LignesVenteMapper;

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
 * Integration tests for the {@link LignesVenteResource} REST controller.
 */
@SpringBootTest(classes = NucleusApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class LignesVenteResourceIT {

    private static final Integer DEFAULT_MONTANT = 1;
    private static final Integer UPDATED_MONTANT = 2;

    private static final Integer DEFAULT_QTY = 1;
    private static final Integer UPDATED_QTY = 2;

    private static final Integer DEFAULT_QTY_SERVI = 1;
    private static final Integer UPDATED_QTY_SERVI = 2;

    private static final Integer DEFAULT_PRIX_UNI = 1;
    private static final Integer UPDATED_PRIX_UNI = 2;

    private static final Integer DEFAULT_PRIX_PAF = 1;
    private static final Integer UPDATED_PRIX_PAF = 2;

    private static final Integer DEFAULT_MONTANTREMISE = 1;
    private static final Integer UPDATED_MONTANTREMISE = 2;

    private static final Integer DEFAULT_MONTANT_TVA = 1;
    private static final Integer UPDATED_MONTANT_TVA = 2;

    private static final Integer DEFAULT_VALEUR_TVA = 1;
    private static final Integer UPDATED_VALEUR_TVA = 2;

    private static final Integer DEFAULT_MONTANT_NET = 1;
    private static final Integer UPDATED_MONTANT_NET = 2;

    private static final Status DEFAULT_STATUS = Status.ACTIVE;
    private static final Status UPDATED_STATUS = Status.ENATTENTE;

    private static final Instant DEFAULT_CREATED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_UPDATED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Boolean DEFAULT_CHECKED = false;
    private static final Boolean UPDATED_CHECKED = true;

    @Autowired
    private LignesVenteRepository lignesVenteRepository;

    @Autowired
    private LignesVenteMapper lignesVenteMapper;

    @Autowired
    private LignesVenteService lignesVenteService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLignesVenteMockMvc;

    private LignesVente lignesVente;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LignesVente createEntity(EntityManager em) {
        LignesVente lignesVente = new LignesVente()
            .montant(DEFAULT_MONTANT)
            .qty(DEFAULT_QTY)
            .qtyServi(DEFAULT_QTY_SERVI)
            .prixUni(DEFAULT_PRIX_UNI)
            .prixPaf(DEFAULT_PRIX_PAF)
            .montantremise(DEFAULT_MONTANTREMISE)
            .montantTva(DEFAULT_MONTANT_TVA)
            .valeurTva(DEFAULT_VALEUR_TVA)
            .montantNet(DEFAULT_MONTANT_NET)
            .status(DEFAULT_STATUS)
            .createdAt(DEFAULT_CREATED_AT)
            .updatedAt(DEFAULT_UPDATED_AT)
            .checked(DEFAULT_CHECKED);
        // Add required entity
        StockProduit stockProduit;
        if (TestUtil.findAll(em, StockProduit.class).isEmpty()) {
            stockProduit = StockProduitResourceIT.createEntity(em);
            em.persist(stockProduit);
            em.flush();
        } else {
            stockProduit = TestUtil.findAll(em, StockProduit.class).get(0);
        }
        lignesVente.setProduitStock(stockProduit);
        return lignesVente;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LignesVente createUpdatedEntity(EntityManager em) {
        LignesVente lignesVente = new LignesVente()
            .montant(UPDATED_MONTANT)
            .qty(UPDATED_QTY)
            .qtyServi(UPDATED_QTY_SERVI)
            .prixUni(UPDATED_PRIX_UNI)
            .prixPaf(UPDATED_PRIX_PAF)
            .montantremise(UPDATED_MONTANTREMISE)
            .montantTva(UPDATED_MONTANT_TVA)
            .valeurTva(UPDATED_VALEUR_TVA)
            .montantNet(UPDATED_MONTANT_NET)
            .status(UPDATED_STATUS)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .checked(UPDATED_CHECKED);
        // Add required entity
        StockProduit stockProduit;
        if (TestUtil.findAll(em, StockProduit.class).isEmpty()) {
            stockProduit = StockProduitResourceIT.createUpdatedEntity(em);
            em.persist(stockProduit);
            em.flush();
        } else {
            stockProduit = TestUtil.findAll(em, StockProduit.class).get(0);
        }
        lignesVente.setProduitStock(stockProduit);
        return lignesVente;
    }

    @BeforeEach
    public void initTest() {
        lignesVente = createEntity(em);
    }

    @Test
    @Transactional
    public void createLignesVente() throws Exception {
        int databaseSizeBeforeCreate = lignesVenteRepository.findAll().size();
        // Create the LignesVente
        LignesVenteDTO lignesVenteDTO = lignesVenteMapper.toDto(lignesVente);
        restLignesVenteMockMvc.perform(post("/api/lignes-ventes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(lignesVenteDTO)))
            .andExpect(status().isCreated());

        // Validate the LignesVente in the database
        List<LignesVente> lignesVenteList = lignesVenteRepository.findAll();
        assertThat(lignesVenteList).hasSize(databaseSizeBeforeCreate + 1);
        LignesVente testLignesVente = lignesVenteList.get(lignesVenteList.size() - 1);
        assertThat(testLignesVente.getMontant()).isEqualTo(DEFAULT_MONTANT);
        assertThat(testLignesVente.getQty()).isEqualTo(DEFAULT_QTY);
        assertThat(testLignesVente.getQtyServi()).isEqualTo(DEFAULT_QTY_SERVI);
        assertThat(testLignesVente.getPrixUni()).isEqualTo(DEFAULT_PRIX_UNI);
        assertThat(testLignesVente.getPrixPaf()).isEqualTo(DEFAULT_PRIX_PAF);
        assertThat(testLignesVente.getMontantremise()).isEqualTo(DEFAULT_MONTANTREMISE);
        assertThat(testLignesVente.getMontantTva()).isEqualTo(DEFAULT_MONTANT_TVA);
        assertThat(testLignesVente.getValeurTva()).isEqualTo(DEFAULT_VALEUR_TVA);
        assertThat(testLignesVente.getMontantNet()).isEqualTo(DEFAULT_MONTANT_NET);
        assertThat(testLignesVente.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testLignesVente.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testLignesVente.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testLignesVente.isChecked()).isEqualTo(DEFAULT_CHECKED);
    }

    @Test
    @Transactional
    public void createLignesVenteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = lignesVenteRepository.findAll().size();

        // Create the LignesVente with an existing ID
        lignesVente.setId(1L);
        LignesVenteDTO lignesVenteDTO = lignesVenteMapper.toDto(lignesVente);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLignesVenteMockMvc.perform(post("/api/lignes-ventes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(lignesVenteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the LignesVente in the database
        List<LignesVente> lignesVenteList = lignesVenteRepository.findAll();
        assertThat(lignesVenteList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = lignesVenteRepository.findAll().size();
        // set the field null
        lignesVente.setStatus(null);

        // Create the LignesVente, which fails.
        LignesVenteDTO lignesVenteDTO = lignesVenteMapper.toDto(lignesVente);


        restLignesVenteMockMvc.perform(post("/api/lignes-ventes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(lignesVenteDTO)))
            .andExpect(status().isBadRequest());

        List<LignesVente> lignesVenteList = lignesVenteRepository.findAll();
        assertThat(lignesVenteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = lignesVenteRepository.findAll().size();
        // set the field null
        lignesVente.setCreatedAt(null);

        // Create the LignesVente, which fails.
        LignesVenteDTO lignesVenteDTO = lignesVenteMapper.toDto(lignesVente);


        restLignesVenteMockMvc.perform(post("/api/lignes-ventes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(lignesVenteDTO)))
            .andExpect(status().isBadRequest());

        List<LignesVente> lignesVenteList = lignesVenteRepository.findAll();
        assertThat(lignesVenteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUpdatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = lignesVenteRepository.findAll().size();
        // set the field null
        lignesVente.setUpdatedAt(null);

        // Create the LignesVente, which fails.
        LignesVenteDTO lignesVenteDTO = lignesVenteMapper.toDto(lignesVente);


        restLignesVenteMockMvc.perform(post("/api/lignes-ventes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(lignesVenteDTO)))
            .andExpect(status().isBadRequest());

        List<LignesVente> lignesVenteList = lignesVenteRepository.findAll();
        assertThat(lignesVenteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCheckedIsRequired() throws Exception {
        int databaseSizeBeforeTest = lignesVenteRepository.findAll().size();
        // set the field null
        lignesVente.setChecked(null);

        // Create the LignesVente, which fails.
        LignesVenteDTO lignesVenteDTO = lignesVenteMapper.toDto(lignesVente);


        restLignesVenteMockMvc.perform(post("/api/lignes-ventes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(lignesVenteDTO)))
            .andExpect(status().isBadRequest());

        List<LignesVente> lignesVenteList = lignesVenteRepository.findAll();
        assertThat(lignesVenteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllLignesVentes() throws Exception {
        // Initialize the database
        lignesVenteRepository.saveAndFlush(lignesVente);

        // Get all the lignesVenteList
        restLignesVenteMockMvc.perform(get("/api/lignes-ventes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(lignesVente.getId().intValue())))
            .andExpect(jsonPath("$.[*].montant").value(hasItem(DEFAULT_MONTANT)))
            .andExpect(jsonPath("$.[*].qty").value(hasItem(DEFAULT_QTY)))
            .andExpect(jsonPath("$.[*].qtyServi").value(hasItem(DEFAULT_QTY_SERVI)))
            .andExpect(jsonPath("$.[*].prixUni").value(hasItem(DEFAULT_PRIX_UNI)))
            .andExpect(jsonPath("$.[*].prixPaf").value(hasItem(DEFAULT_PRIX_PAF)))
            .andExpect(jsonPath("$.[*].montantremise").value(hasItem(DEFAULT_MONTANTREMISE)))
            .andExpect(jsonPath("$.[*].montantTva").value(hasItem(DEFAULT_MONTANT_TVA)))
            .andExpect(jsonPath("$.[*].valeurTva").value(hasItem(DEFAULT_VALEUR_TVA)))
            .andExpect(jsonPath("$.[*].montantNet").value(hasItem(DEFAULT_MONTANT_NET)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(DEFAULT_UPDATED_AT.toString())))
            .andExpect(jsonPath("$.[*].checked").value(hasItem(DEFAULT_CHECKED.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getLignesVente() throws Exception {
        // Initialize the database
        lignesVenteRepository.saveAndFlush(lignesVente);

        // Get the lignesVente
        restLignesVenteMockMvc.perform(get("/api/lignes-ventes/{id}", lignesVente.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(lignesVente.getId().intValue()))
            .andExpect(jsonPath("$.montant").value(DEFAULT_MONTANT))
            .andExpect(jsonPath("$.qty").value(DEFAULT_QTY))
            .andExpect(jsonPath("$.qtyServi").value(DEFAULT_QTY_SERVI))
            .andExpect(jsonPath("$.prixUni").value(DEFAULT_PRIX_UNI))
            .andExpect(jsonPath("$.prixPaf").value(DEFAULT_PRIX_PAF))
            .andExpect(jsonPath("$.montantremise").value(DEFAULT_MONTANTREMISE))
            .andExpect(jsonPath("$.montantTva").value(DEFAULT_MONTANT_TVA))
            .andExpect(jsonPath("$.valeurTva").value(DEFAULT_VALEUR_TVA))
            .andExpect(jsonPath("$.montantNet").value(DEFAULT_MONTANT_NET))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.updatedAt").value(DEFAULT_UPDATED_AT.toString()))
            .andExpect(jsonPath("$.checked").value(DEFAULT_CHECKED.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingLignesVente() throws Exception {
        // Get the lignesVente
        restLignesVenteMockMvc.perform(get("/api/lignes-ventes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLignesVente() throws Exception {
        // Initialize the database
        lignesVenteRepository.saveAndFlush(lignesVente);

        int databaseSizeBeforeUpdate = lignesVenteRepository.findAll().size();

        // Update the lignesVente
        LignesVente updatedLignesVente = lignesVenteRepository.findById(lignesVente.getId()).get();
        // Disconnect from session so that the updates on updatedLignesVente are not directly saved in db
        em.detach(updatedLignesVente);
        updatedLignesVente
            .montant(UPDATED_MONTANT)
            .qty(UPDATED_QTY)
            .qtyServi(UPDATED_QTY_SERVI)
            .prixUni(UPDATED_PRIX_UNI)
            .prixPaf(UPDATED_PRIX_PAF)
            .montantremise(UPDATED_MONTANTREMISE)
            .montantTva(UPDATED_MONTANT_TVA)
            .valeurTva(UPDATED_VALEUR_TVA)
            .montantNet(UPDATED_MONTANT_NET)
            .status(UPDATED_STATUS)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .checked(UPDATED_CHECKED);
        LignesVenteDTO lignesVenteDTO = lignesVenteMapper.toDto(updatedLignesVente);

        restLignesVenteMockMvc.perform(put("/api/lignes-ventes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(lignesVenteDTO)))
            .andExpect(status().isOk());

        // Validate the LignesVente in the database
        List<LignesVente> lignesVenteList = lignesVenteRepository.findAll();
        assertThat(lignesVenteList).hasSize(databaseSizeBeforeUpdate);
        LignesVente testLignesVente = lignesVenteList.get(lignesVenteList.size() - 1);
        assertThat(testLignesVente.getMontant()).isEqualTo(UPDATED_MONTANT);
        assertThat(testLignesVente.getQty()).isEqualTo(UPDATED_QTY);
        assertThat(testLignesVente.getQtyServi()).isEqualTo(UPDATED_QTY_SERVI);
        assertThat(testLignesVente.getPrixUni()).isEqualTo(UPDATED_PRIX_UNI);
        assertThat(testLignesVente.getPrixPaf()).isEqualTo(UPDATED_PRIX_PAF);
        assertThat(testLignesVente.getMontantremise()).isEqualTo(UPDATED_MONTANTREMISE);
        assertThat(testLignesVente.getMontantTva()).isEqualTo(UPDATED_MONTANT_TVA);
        assertThat(testLignesVente.getValeurTva()).isEqualTo(UPDATED_VALEUR_TVA);
        assertThat(testLignesVente.getMontantNet()).isEqualTo(UPDATED_MONTANT_NET);
        assertThat(testLignesVente.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testLignesVente.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testLignesVente.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testLignesVente.isChecked()).isEqualTo(UPDATED_CHECKED);
    }

    @Test
    @Transactional
    public void updateNonExistingLignesVente() throws Exception {
        int databaseSizeBeforeUpdate = lignesVenteRepository.findAll().size();

        // Create the LignesVente
        LignesVenteDTO lignesVenteDTO = lignesVenteMapper.toDto(lignesVente);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLignesVenteMockMvc.perform(put("/api/lignes-ventes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(lignesVenteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the LignesVente in the database
        List<LignesVente> lignesVenteList = lignesVenteRepository.findAll();
        assertThat(lignesVenteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLignesVente() throws Exception {
        // Initialize the database
        lignesVenteRepository.saveAndFlush(lignesVente);

        int databaseSizeBeforeDelete = lignesVenteRepository.findAll().size();

        // Delete the lignesVente
        restLignesVenteMockMvc.perform(delete("/api/lignes-ventes/{id}", lignesVente.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<LignesVente> lignesVenteList = lignesVenteRepository.findAll();
        assertThat(lignesVenteList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
