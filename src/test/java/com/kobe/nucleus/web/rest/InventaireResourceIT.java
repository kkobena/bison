package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.NucleusApp;
import com.kobe.nucleus.domain.Inventaire;
import com.kobe.nucleus.repository.InventaireRepository;
import com.kobe.nucleus.service.InventaireService;
import com.kobe.nucleus.service.dto.InventaireDTO;
import com.kobe.nucleus.service.mapper.InventaireMapper;

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

import com.kobe.nucleus.domain.enumeration.Status;
/**
 * Integration tests for the {@link InventaireResource} REST controller.
 */
@SpringBootTest(classes = NucleusApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class InventaireResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_UPDATED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Status DEFAULT_STATUS = Status.ACTIVE;
    private static final Status UPDATED_STATUS = Status.ENATTENTE;

    private static final Boolean DEFAULT_PROGRAMMABLE = false;
    private static final Boolean UPDATED_PROGRAMMABLE = true;

    private static final LocalDate DEFAULT_DATE_DEBUT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_DEBUT = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_FIN = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_FIN = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_END_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_END_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Instant DEFAULT_BEGINNIN = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_BEGINNIN = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_ENDING = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_ENDING = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Double DEFAULT_VALEUR_ACHAT_AVANT = 1D;
    private static final Double UPDATED_VALEUR_ACHAT_AVANT = 2D;

    private static final Double DEFAULT_VALEUR_ACHAT_APRES = 1D;
    private static final Double UPDATED_VALEUR_ACHAT_APRES = 2D;

    private static final Double DEFAULT_VALEUR_VENTE_AVANT = 1D;
    private static final Double UPDATED_VALEUR_VENTE_AVANT = 2D;

    private static final Double DEFAULT_VALEUR_VENTE_APRES = 1D;
    private static final Double UPDATED_VALEUR_VENTE_APRES = 2D;

    @Autowired
    private InventaireRepository inventaireRepository;

    @Autowired
    private InventaireMapper inventaireMapper;

    @Autowired
    private InventaireService inventaireService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInventaireMockMvc;

    private Inventaire inventaire;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Inventaire createEntity(EntityManager em) {
        Inventaire inventaire = new Inventaire()
            .libelle(DEFAULT_LIBELLE)
            .createdAt(DEFAULT_CREATED_AT)
            .updatedAt(DEFAULT_UPDATED_AT)
            .status(DEFAULT_STATUS)
            .programmable(DEFAULT_PROGRAMMABLE)
            .dateDebut(DEFAULT_DATE_DEBUT)
            .dateFin(DEFAULT_DATE_FIN)
            .endDate(DEFAULT_END_DATE)
            .beginnin(DEFAULT_BEGINNIN)
            .ending(DEFAULT_ENDING)
            .valeurAchatAvant(DEFAULT_VALEUR_ACHAT_AVANT)
            .valeurAchatApres(DEFAULT_VALEUR_ACHAT_APRES)
            .valeurVenteAvant(DEFAULT_VALEUR_VENTE_AVANT)
            .valeurVenteApres(DEFAULT_VALEUR_VENTE_APRES);
        return inventaire;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Inventaire createUpdatedEntity(EntityManager em) {
        Inventaire inventaire = new Inventaire()
            .libelle(UPDATED_LIBELLE)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .status(UPDATED_STATUS)
            .programmable(UPDATED_PROGRAMMABLE)
            .dateDebut(UPDATED_DATE_DEBUT)
            .dateFin(UPDATED_DATE_FIN)
            .endDate(UPDATED_END_DATE)
            .beginnin(UPDATED_BEGINNIN)
            .ending(UPDATED_ENDING)
            .valeurAchatAvant(UPDATED_VALEUR_ACHAT_AVANT)
            .valeurAchatApres(UPDATED_VALEUR_ACHAT_APRES)
            .valeurVenteAvant(UPDATED_VALEUR_VENTE_AVANT)
            .valeurVenteApres(UPDATED_VALEUR_VENTE_APRES);
        return inventaire;
    }

    @BeforeEach
    public void initTest() {
        inventaire = createEntity(em);
    }

    @Test
    @Transactional
    public void createInventaire() throws Exception {
        int databaseSizeBeforeCreate = inventaireRepository.findAll().size();
        // Create the Inventaire
        InventaireDTO inventaireDTO = inventaireMapper.toDto(inventaire);
        restInventaireMockMvc.perform(post("/api/inventaires").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(inventaireDTO)))
            .andExpect(status().isCreated());

        // Validate the Inventaire in the database
        List<Inventaire> inventaireList = inventaireRepository.findAll();
        assertThat(inventaireList).hasSize(databaseSizeBeforeCreate + 1);
        Inventaire testInventaire = inventaireList.get(inventaireList.size() - 1);
        assertThat(testInventaire.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
        assertThat(testInventaire.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testInventaire.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testInventaire.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testInventaire.isProgrammable()).isEqualTo(DEFAULT_PROGRAMMABLE);
        assertThat(testInventaire.getDateDebut()).isEqualTo(DEFAULT_DATE_DEBUT);
        assertThat(testInventaire.getDateFin()).isEqualTo(DEFAULT_DATE_FIN);
        assertThat(testInventaire.getEndDate()).isEqualTo(DEFAULT_END_DATE);
        assertThat(testInventaire.getBeginnin()).isEqualTo(DEFAULT_BEGINNIN);
        assertThat(testInventaire.getEnding()).isEqualTo(DEFAULT_ENDING);
        assertThat(testInventaire.getValeurAchatAvant()).isEqualTo(DEFAULT_VALEUR_ACHAT_AVANT);
        assertThat(testInventaire.getValeurAchatApres()).isEqualTo(DEFAULT_VALEUR_ACHAT_APRES);
        assertThat(testInventaire.getValeurVenteAvant()).isEqualTo(DEFAULT_VALEUR_VENTE_AVANT);
        assertThat(testInventaire.getValeurVenteApres()).isEqualTo(DEFAULT_VALEUR_VENTE_APRES);
    }

    @Test
    @Transactional
    public void createInventaireWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = inventaireRepository.findAll().size();

        // Create the Inventaire with an existing ID
        inventaire.setId(1L);
        InventaireDTO inventaireDTO = inventaireMapper.toDto(inventaire);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInventaireMockMvc.perform(post("/api/inventaires").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(inventaireDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Inventaire in the database
        List<Inventaire> inventaireList = inventaireRepository.findAll();
        assertThat(inventaireList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCreatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = inventaireRepository.findAll().size();
        // set the field null
        inventaire.setCreatedAt(null);

        // Create the Inventaire, which fails.
        InventaireDTO inventaireDTO = inventaireMapper.toDto(inventaire);


        restInventaireMockMvc.perform(post("/api/inventaires").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(inventaireDTO)))
            .andExpect(status().isBadRequest());

        List<Inventaire> inventaireList = inventaireRepository.findAll();
        assertThat(inventaireList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUpdatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = inventaireRepository.findAll().size();
        // set the field null
        inventaire.setUpdatedAt(null);

        // Create the Inventaire, which fails.
        InventaireDTO inventaireDTO = inventaireMapper.toDto(inventaire);


        restInventaireMockMvc.perform(post("/api/inventaires").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(inventaireDTO)))
            .andExpect(status().isBadRequest());

        List<Inventaire> inventaireList = inventaireRepository.findAll();
        assertThat(inventaireList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = inventaireRepository.findAll().size();
        // set the field null
        inventaire.setStatus(null);

        // Create the Inventaire, which fails.
        InventaireDTO inventaireDTO = inventaireMapper.toDto(inventaire);


        restInventaireMockMvc.perform(post("/api/inventaires").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(inventaireDTO)))
            .andExpect(status().isBadRequest());

        List<Inventaire> inventaireList = inventaireRepository.findAll();
        assertThat(inventaireList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBeginninIsRequired() throws Exception {
        int databaseSizeBeforeTest = inventaireRepository.findAll().size();
        // set the field null
        inventaire.setBeginnin(null);

        // Create the Inventaire, which fails.
        InventaireDTO inventaireDTO = inventaireMapper.toDto(inventaire);


        restInventaireMockMvc.perform(post("/api/inventaires").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(inventaireDTO)))
            .andExpect(status().isBadRequest());

        List<Inventaire> inventaireList = inventaireRepository.findAll();
        assertThat(inventaireList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEndingIsRequired() throws Exception {
        int databaseSizeBeforeTest = inventaireRepository.findAll().size();
        // set the field null
        inventaire.setEnding(null);

        // Create the Inventaire, which fails.
        InventaireDTO inventaireDTO = inventaireMapper.toDto(inventaire);


        restInventaireMockMvc.perform(post("/api/inventaires").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(inventaireDTO)))
            .andExpect(status().isBadRequest());

        List<Inventaire> inventaireList = inventaireRepository.findAll();
        assertThat(inventaireList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkValeurAchatAvantIsRequired() throws Exception {
        int databaseSizeBeforeTest = inventaireRepository.findAll().size();
        // set the field null
        inventaire.setValeurAchatAvant(null);

        // Create the Inventaire, which fails.
        InventaireDTO inventaireDTO = inventaireMapper.toDto(inventaire);


        restInventaireMockMvc.perform(post("/api/inventaires").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(inventaireDTO)))
            .andExpect(status().isBadRequest());

        List<Inventaire> inventaireList = inventaireRepository.findAll();
        assertThat(inventaireList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkValeurAchatApresIsRequired() throws Exception {
        int databaseSizeBeforeTest = inventaireRepository.findAll().size();
        // set the field null
        inventaire.setValeurAchatApres(null);

        // Create the Inventaire, which fails.
        InventaireDTO inventaireDTO = inventaireMapper.toDto(inventaire);


        restInventaireMockMvc.perform(post("/api/inventaires").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(inventaireDTO)))
            .andExpect(status().isBadRequest());

        List<Inventaire> inventaireList = inventaireRepository.findAll();
        assertThat(inventaireList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkValeurVenteAvantIsRequired() throws Exception {
        int databaseSizeBeforeTest = inventaireRepository.findAll().size();
        // set the field null
        inventaire.setValeurVenteAvant(null);

        // Create the Inventaire, which fails.
        InventaireDTO inventaireDTO = inventaireMapper.toDto(inventaire);


        restInventaireMockMvc.perform(post("/api/inventaires").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(inventaireDTO)))
            .andExpect(status().isBadRequest());

        List<Inventaire> inventaireList = inventaireRepository.findAll();
        assertThat(inventaireList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkValeurVenteApresIsRequired() throws Exception {
        int databaseSizeBeforeTest = inventaireRepository.findAll().size();
        // set the field null
        inventaire.setValeurVenteApres(null);

        // Create the Inventaire, which fails.
        InventaireDTO inventaireDTO = inventaireMapper.toDto(inventaire);


        restInventaireMockMvc.perform(post("/api/inventaires").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(inventaireDTO)))
            .andExpect(status().isBadRequest());

        List<Inventaire> inventaireList = inventaireRepository.findAll();
        assertThat(inventaireList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllInventaires() throws Exception {
        // Initialize the database
        inventaireRepository.saveAndFlush(inventaire);

        // Get all the inventaireList
        restInventaireMockMvc.perform(get("/api/inventaires?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(inventaire.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(DEFAULT_UPDATED_AT.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].programmable").value(hasItem(DEFAULT_PROGRAMMABLE.booleanValue())))
            .andExpect(jsonPath("$.[*].dateDebut").value(hasItem(DEFAULT_DATE_DEBUT.toString())))
            .andExpect(jsonPath("$.[*].dateFin").value(hasItem(DEFAULT_DATE_FIN.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].beginnin").value(hasItem(DEFAULT_BEGINNIN.toString())))
            .andExpect(jsonPath("$.[*].ending").value(hasItem(DEFAULT_ENDING.toString())))
            .andExpect(jsonPath("$.[*].valeurAchatAvant").value(hasItem(DEFAULT_VALEUR_ACHAT_AVANT.doubleValue())))
            .andExpect(jsonPath("$.[*].valeurAchatApres").value(hasItem(DEFAULT_VALEUR_ACHAT_APRES.doubleValue())))
            .andExpect(jsonPath("$.[*].valeurVenteAvant").value(hasItem(DEFAULT_VALEUR_VENTE_AVANT.doubleValue())))
            .andExpect(jsonPath("$.[*].valeurVenteApres").value(hasItem(DEFAULT_VALEUR_VENTE_APRES.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getInventaire() throws Exception {
        // Initialize the database
        inventaireRepository.saveAndFlush(inventaire);

        // Get the inventaire
        restInventaireMockMvc.perform(get("/api/inventaires/{id}", inventaire.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(inventaire.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.updatedAt").value(DEFAULT_UPDATED_AT.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.programmable").value(DEFAULT_PROGRAMMABLE.booleanValue()))
            .andExpect(jsonPath("$.dateDebut").value(DEFAULT_DATE_DEBUT.toString()))
            .andExpect(jsonPath("$.dateFin").value(DEFAULT_DATE_FIN.toString()))
            .andExpect(jsonPath("$.endDate").value(DEFAULT_END_DATE.toString()))
            .andExpect(jsonPath("$.beginnin").value(DEFAULT_BEGINNIN.toString()))
            .andExpect(jsonPath("$.ending").value(DEFAULT_ENDING.toString()))
            .andExpect(jsonPath("$.valeurAchatAvant").value(DEFAULT_VALEUR_ACHAT_AVANT.doubleValue()))
            .andExpect(jsonPath("$.valeurAchatApres").value(DEFAULT_VALEUR_ACHAT_APRES.doubleValue()))
            .andExpect(jsonPath("$.valeurVenteAvant").value(DEFAULT_VALEUR_VENTE_AVANT.doubleValue()))
            .andExpect(jsonPath("$.valeurVenteApres").value(DEFAULT_VALEUR_VENTE_APRES.doubleValue()));
    }
    @Test
    @Transactional
    public void getNonExistingInventaire() throws Exception {
        // Get the inventaire
        restInventaireMockMvc.perform(get("/api/inventaires/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInventaire() throws Exception {
        // Initialize the database
        inventaireRepository.saveAndFlush(inventaire);

        int databaseSizeBeforeUpdate = inventaireRepository.findAll().size();

        // Update the inventaire
        Inventaire updatedInventaire = inventaireRepository.findById(inventaire.getId()).get();
        // Disconnect from session so that the updates on updatedInventaire are not directly saved in db
        em.detach(updatedInventaire);
        updatedInventaire
            .libelle(UPDATED_LIBELLE)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .status(UPDATED_STATUS)
            .programmable(UPDATED_PROGRAMMABLE)
            .dateDebut(UPDATED_DATE_DEBUT)
            .dateFin(UPDATED_DATE_FIN)
            .endDate(UPDATED_END_DATE)
            .beginnin(UPDATED_BEGINNIN)
            .ending(UPDATED_ENDING)
            .valeurAchatAvant(UPDATED_VALEUR_ACHAT_AVANT)
            .valeurAchatApres(UPDATED_VALEUR_ACHAT_APRES)
            .valeurVenteAvant(UPDATED_VALEUR_VENTE_AVANT)
            .valeurVenteApres(UPDATED_VALEUR_VENTE_APRES);
        InventaireDTO inventaireDTO = inventaireMapper.toDto(updatedInventaire);

        restInventaireMockMvc.perform(put("/api/inventaires").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(inventaireDTO)))
            .andExpect(status().isOk());

        // Validate the Inventaire in the database
        List<Inventaire> inventaireList = inventaireRepository.findAll();
        assertThat(inventaireList).hasSize(databaseSizeBeforeUpdate);
        Inventaire testInventaire = inventaireList.get(inventaireList.size() - 1);
        assertThat(testInventaire.getLibelle()).isEqualTo(UPDATED_LIBELLE);
        assertThat(testInventaire.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testInventaire.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testInventaire.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testInventaire.isProgrammable()).isEqualTo(UPDATED_PROGRAMMABLE);
        assertThat(testInventaire.getDateDebut()).isEqualTo(UPDATED_DATE_DEBUT);
        assertThat(testInventaire.getDateFin()).isEqualTo(UPDATED_DATE_FIN);
        assertThat(testInventaire.getEndDate()).isEqualTo(UPDATED_END_DATE);
        assertThat(testInventaire.getBeginnin()).isEqualTo(UPDATED_BEGINNIN);
        assertThat(testInventaire.getEnding()).isEqualTo(UPDATED_ENDING);
        assertThat(testInventaire.getValeurAchatAvant()).isEqualTo(UPDATED_VALEUR_ACHAT_AVANT);
        assertThat(testInventaire.getValeurAchatApres()).isEqualTo(UPDATED_VALEUR_ACHAT_APRES);
        assertThat(testInventaire.getValeurVenteAvant()).isEqualTo(UPDATED_VALEUR_VENTE_AVANT);
        assertThat(testInventaire.getValeurVenteApres()).isEqualTo(UPDATED_VALEUR_VENTE_APRES);
    }

    @Test
    @Transactional
    public void updateNonExistingInventaire() throws Exception {
        int databaseSizeBeforeUpdate = inventaireRepository.findAll().size();

        // Create the Inventaire
        InventaireDTO inventaireDTO = inventaireMapper.toDto(inventaire);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInventaireMockMvc.perform(put("/api/inventaires").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(inventaireDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Inventaire in the database
        List<Inventaire> inventaireList = inventaireRepository.findAll();
        assertThat(inventaireList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteInventaire() throws Exception {
        // Initialize the database
        inventaireRepository.saveAndFlush(inventaire);

        int databaseSizeBeforeDelete = inventaireRepository.findAll().size();

        // Delete the inventaire
        restInventaireMockMvc.perform(delete("/api/inventaires/{id}", inventaire.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Inventaire> inventaireList = inventaireRepository.findAll();
        assertThat(inventaireList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
