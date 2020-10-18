package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.NucleusApp;
import com.kobe.nucleus.domain.Facture;
import com.kobe.nucleus.domain.Tierspayant;
import com.kobe.nucleus.repository.FactureRepository;
import com.kobe.nucleus.service.FactureService;
import com.kobe.nucleus.service.dto.FactureDTO;
import com.kobe.nucleus.service.mapper.FactureMapper;

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
import com.kobe.nucleus.domain.enumeration.StatutFacture;
import com.kobe.nucleus.domain.enumeration.TypeFacture;
/**
 * Integration tests for the {@link FactureResource} REST controller.
 */
@SpringBootTest(classes = NucleusApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class FactureResourceIT {

    private static final Instant DEFAULT_CREATED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_UPDATED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_GROUPE = "AAAAAAAAAA";
    private static final String UPDATED_CODE_GROUPE = "BBBBBBBBBB";

    private static final Status DEFAULT_STATUS = Status.ACTIVE;
    private static final Status UPDATED_STATUS = Status.ENATTENTE;

    private static final LocalDate DEFAULT_DATE_DEBUT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_DEBUT = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_FIN = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_FIN = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_MONTANT_FORFETAIRE = 1;
    private static final Integer UPDATED_MONTANT_FORFETAIRE = 2;

    private static final Integer DEFAULT_MONTANT_REMISE = 1;
    private static final Integer UPDATED_MONTANT_REMISE = 2;

    private static final Integer DEFAULT_MONTANT_PAYE = 1;
    private static final Integer UPDATED_MONTANT_PAYE = 2;

    private static final Integer DEFAULT_MONTANT_RESTANT = 1;
    private static final Integer UPDATED_MONTANT_RESTANT = 2;

    private static final Integer DEFAULT_MONTANT_NET = 1;
    private static final Integer UPDATED_MONTANT_NET = 2;

    private static final StatutFacture DEFAULT_STATUT_FACTURE = StatutFacture.PAID;
    private static final StatutFacture UPDATED_STATUT_FACTURE = StatutFacture.UNPAID;

    private static final TypeFacture DEFAULT_TYPE_FACTURE = TypeFacture.GROUPE;
    private static final TypeFacture UPDATED_TYPE_FACTURE = TypeFacture.INDIVIDUEL;

    private static final Integer DEFAULT_MONTANT_BRUT = 1;
    private static final Integer UPDATED_MONTANT_BRUT = 2;

    private static final Boolean DEFAULT_TEMPLATE = false;
    private static final Boolean UPDATED_TEMPLATE = true;

    @Autowired
    private FactureRepository factureRepository;

    @Autowired
    private FactureMapper factureMapper;

    @Autowired
    private FactureService factureService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFactureMockMvc;

    private Facture facture;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Facture createEntity(EntityManager em) {
        Facture facture = new Facture()
            .createdAt(DEFAULT_CREATED_AT)
            .updatedAt(DEFAULT_UPDATED_AT)
            .code(DEFAULT_CODE)
            .codeGroupe(DEFAULT_CODE_GROUPE)
            .status(DEFAULT_STATUS)
            .dateDebut(DEFAULT_DATE_DEBUT)
            .dateFin(DEFAULT_DATE_FIN)
            .montantForfetaire(DEFAULT_MONTANT_FORFETAIRE)
            .montantRemise(DEFAULT_MONTANT_REMISE)
            .montantPaye(DEFAULT_MONTANT_PAYE)
            .montantRestant(DEFAULT_MONTANT_RESTANT)
            .montantNet(DEFAULT_MONTANT_NET)
            .statutFacture(DEFAULT_STATUT_FACTURE)
            .typeFacture(DEFAULT_TYPE_FACTURE)
            .montantBrut(DEFAULT_MONTANT_BRUT)
            .template(DEFAULT_TEMPLATE);
        // Add required entity
        Tierspayant tierspayant;
        if (TestUtil.findAll(em, Tierspayant.class).isEmpty()) {
            tierspayant = TierspayantResourceIT.createEntity(em);
            em.persist(tierspayant);
            em.flush();
        } else {
            tierspayant = TestUtil.findAll(em, Tierspayant.class).get(0);
        }
        facture.setTierpayant(tierspayant);
        return facture;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Facture createUpdatedEntity(EntityManager em) {
        Facture facture = new Facture()
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .code(UPDATED_CODE)
            .codeGroupe(UPDATED_CODE_GROUPE)
            .status(UPDATED_STATUS)
            .dateDebut(UPDATED_DATE_DEBUT)
            .dateFin(UPDATED_DATE_FIN)
            .montantForfetaire(UPDATED_MONTANT_FORFETAIRE)
            .montantRemise(UPDATED_MONTANT_REMISE)
            .montantPaye(UPDATED_MONTANT_PAYE)
            .montantRestant(UPDATED_MONTANT_RESTANT)
            .montantNet(UPDATED_MONTANT_NET)
            .statutFacture(UPDATED_STATUT_FACTURE)
            .typeFacture(UPDATED_TYPE_FACTURE)
            .montantBrut(UPDATED_MONTANT_BRUT)
            .template(UPDATED_TEMPLATE);
        // Add required entity
        Tierspayant tierspayant;
        if (TestUtil.findAll(em, Tierspayant.class).isEmpty()) {
            tierspayant = TierspayantResourceIT.createUpdatedEntity(em);
            em.persist(tierspayant);
            em.flush();
        } else {
            tierspayant = TestUtil.findAll(em, Tierspayant.class).get(0);
        }
        facture.setTierpayant(tierspayant);
        return facture;
    }

    @BeforeEach
    public void initTest() {
        facture = createEntity(em);
    }

    @Test
    @Transactional
    public void createFacture() throws Exception {
        int databaseSizeBeforeCreate = factureRepository.findAll().size();
        // Create the Facture
        FactureDTO factureDTO = factureMapper.toDto(facture);
        restFactureMockMvc.perform(post("/api/factures").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(factureDTO)))
            .andExpect(status().isCreated());

        // Validate the Facture in the database
        List<Facture> factureList = factureRepository.findAll();
        assertThat(factureList).hasSize(databaseSizeBeforeCreate + 1);
        Facture testFacture = factureList.get(factureList.size() - 1);
        assertThat(testFacture.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testFacture.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testFacture.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testFacture.getCodeGroupe()).isEqualTo(DEFAULT_CODE_GROUPE);
        assertThat(testFacture.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testFacture.getDateDebut()).isEqualTo(DEFAULT_DATE_DEBUT);
        assertThat(testFacture.getDateFin()).isEqualTo(DEFAULT_DATE_FIN);
        assertThat(testFacture.getMontantForfetaire()).isEqualTo(DEFAULT_MONTANT_FORFETAIRE);
        assertThat(testFacture.getMontantRemise()).isEqualTo(DEFAULT_MONTANT_REMISE);
        assertThat(testFacture.getMontantPaye()).isEqualTo(DEFAULT_MONTANT_PAYE);
        assertThat(testFacture.getMontantRestant()).isEqualTo(DEFAULT_MONTANT_RESTANT);
        assertThat(testFacture.getMontantNet()).isEqualTo(DEFAULT_MONTANT_NET);
        assertThat(testFacture.getStatutFacture()).isEqualTo(DEFAULT_STATUT_FACTURE);
        assertThat(testFacture.getTypeFacture()).isEqualTo(DEFAULT_TYPE_FACTURE);
        assertThat(testFacture.getMontantBrut()).isEqualTo(DEFAULT_MONTANT_BRUT);
        assertThat(testFacture.isTemplate()).isEqualTo(DEFAULT_TEMPLATE);
    }

    @Test
    @Transactional
    public void createFactureWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = factureRepository.findAll().size();

        // Create the Facture with an existing ID
        facture.setId(1L);
        FactureDTO factureDTO = factureMapper.toDto(facture);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFactureMockMvc.perform(post("/api/factures").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(factureDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Facture in the database
        List<Facture> factureList = factureRepository.findAll();
        assertThat(factureList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = factureRepository.findAll().size();
        // set the field null
        facture.setCode(null);

        // Create the Facture, which fails.
        FactureDTO factureDTO = factureMapper.toDto(facture);


        restFactureMockMvc.perform(post("/api/factures").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(factureDTO)))
            .andExpect(status().isBadRequest());

        List<Facture> factureList = factureRepository.findAll();
        assertThat(factureList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = factureRepository.findAll().size();
        // set the field null
        facture.setStatus(null);

        // Create the Facture, which fails.
        FactureDTO factureDTO = factureMapper.toDto(facture);


        restFactureMockMvc.perform(post("/api/factures").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(factureDTO)))
            .andExpect(status().isBadRequest());

        List<Facture> factureList = factureRepository.findAll();
        assertThat(factureList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatutFactureIsRequired() throws Exception {
        int databaseSizeBeforeTest = factureRepository.findAll().size();
        // set the field null
        facture.setStatutFacture(null);

        // Create the Facture, which fails.
        FactureDTO factureDTO = factureMapper.toDto(facture);


        restFactureMockMvc.perform(post("/api/factures").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(factureDTO)))
            .andExpect(status().isBadRequest());

        List<Facture> factureList = factureRepository.findAll();
        assertThat(factureList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTypeFactureIsRequired() throws Exception {
        int databaseSizeBeforeTest = factureRepository.findAll().size();
        // set the field null
        facture.setTypeFacture(null);

        // Create the Facture, which fails.
        FactureDTO factureDTO = factureMapper.toDto(facture);


        restFactureMockMvc.perform(post("/api/factures").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(factureDTO)))
            .andExpect(status().isBadRequest());

        List<Facture> factureList = factureRepository.findAll();
        assertThat(factureList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTemplateIsRequired() throws Exception {
        int databaseSizeBeforeTest = factureRepository.findAll().size();
        // set the field null
        facture.setTemplate(null);

        // Create the Facture, which fails.
        FactureDTO factureDTO = factureMapper.toDto(facture);


        restFactureMockMvc.perform(post("/api/factures").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(factureDTO)))
            .andExpect(status().isBadRequest());

        List<Facture> factureList = factureRepository.findAll();
        assertThat(factureList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFactures() throws Exception {
        // Initialize the database
        factureRepository.saveAndFlush(facture);

        // Get all the factureList
        restFactureMockMvc.perform(get("/api/factures?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(facture.getId().intValue())))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(DEFAULT_UPDATED_AT.toString())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].codeGroupe").value(hasItem(DEFAULT_CODE_GROUPE)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].dateDebut").value(hasItem(DEFAULT_DATE_DEBUT.toString())))
            .andExpect(jsonPath("$.[*].dateFin").value(hasItem(DEFAULT_DATE_FIN.toString())))
            .andExpect(jsonPath("$.[*].montantForfetaire").value(hasItem(DEFAULT_MONTANT_FORFETAIRE)))
            .andExpect(jsonPath("$.[*].montantRemise").value(hasItem(DEFAULT_MONTANT_REMISE)))
            .andExpect(jsonPath("$.[*].montantPaye").value(hasItem(DEFAULT_MONTANT_PAYE)))
            .andExpect(jsonPath("$.[*].montantRestant").value(hasItem(DEFAULT_MONTANT_RESTANT)))
            .andExpect(jsonPath("$.[*].montantNet").value(hasItem(DEFAULT_MONTANT_NET)))
            .andExpect(jsonPath("$.[*].statutFacture").value(hasItem(DEFAULT_STATUT_FACTURE.toString())))
            .andExpect(jsonPath("$.[*].typeFacture").value(hasItem(DEFAULT_TYPE_FACTURE.toString())))
            .andExpect(jsonPath("$.[*].montantBrut").value(hasItem(DEFAULT_MONTANT_BRUT)))
            .andExpect(jsonPath("$.[*].template").value(hasItem(DEFAULT_TEMPLATE.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getFacture() throws Exception {
        // Initialize the database
        factureRepository.saveAndFlush(facture);

        // Get the facture
        restFactureMockMvc.perform(get("/api/factures/{id}", facture.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(facture.getId().intValue()))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.updatedAt").value(DEFAULT_UPDATED_AT.toString()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.codeGroupe").value(DEFAULT_CODE_GROUPE))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.dateDebut").value(DEFAULT_DATE_DEBUT.toString()))
            .andExpect(jsonPath("$.dateFin").value(DEFAULT_DATE_FIN.toString()))
            .andExpect(jsonPath("$.montantForfetaire").value(DEFAULT_MONTANT_FORFETAIRE))
            .andExpect(jsonPath("$.montantRemise").value(DEFAULT_MONTANT_REMISE))
            .andExpect(jsonPath("$.montantPaye").value(DEFAULT_MONTANT_PAYE))
            .andExpect(jsonPath("$.montantRestant").value(DEFAULT_MONTANT_RESTANT))
            .andExpect(jsonPath("$.montantNet").value(DEFAULT_MONTANT_NET))
            .andExpect(jsonPath("$.statutFacture").value(DEFAULT_STATUT_FACTURE.toString()))
            .andExpect(jsonPath("$.typeFacture").value(DEFAULT_TYPE_FACTURE.toString()))
            .andExpect(jsonPath("$.montantBrut").value(DEFAULT_MONTANT_BRUT))
            .andExpect(jsonPath("$.template").value(DEFAULT_TEMPLATE.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingFacture() throws Exception {
        // Get the facture
        restFactureMockMvc.perform(get("/api/factures/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFacture() throws Exception {
        // Initialize the database
        factureRepository.saveAndFlush(facture);

        int databaseSizeBeforeUpdate = factureRepository.findAll().size();

        // Update the facture
        Facture updatedFacture = factureRepository.findById(facture.getId()).get();
        // Disconnect from session so that the updates on updatedFacture are not directly saved in db
        em.detach(updatedFacture);
        updatedFacture
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .code(UPDATED_CODE)
            .codeGroupe(UPDATED_CODE_GROUPE)
            .status(UPDATED_STATUS)
            .dateDebut(UPDATED_DATE_DEBUT)
            .dateFin(UPDATED_DATE_FIN)
            .montantForfetaire(UPDATED_MONTANT_FORFETAIRE)
            .montantRemise(UPDATED_MONTANT_REMISE)
            .montantPaye(UPDATED_MONTANT_PAYE)
            .montantRestant(UPDATED_MONTANT_RESTANT)
            .montantNet(UPDATED_MONTANT_NET)
            .statutFacture(UPDATED_STATUT_FACTURE)
            .typeFacture(UPDATED_TYPE_FACTURE)
            .montantBrut(UPDATED_MONTANT_BRUT)
            .template(UPDATED_TEMPLATE);
        FactureDTO factureDTO = factureMapper.toDto(updatedFacture);

        restFactureMockMvc.perform(put("/api/factures").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(factureDTO)))
            .andExpect(status().isOk());

        // Validate the Facture in the database
        List<Facture> factureList = factureRepository.findAll();
        assertThat(factureList).hasSize(databaseSizeBeforeUpdate);
        Facture testFacture = factureList.get(factureList.size() - 1);
        assertThat(testFacture.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testFacture.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testFacture.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testFacture.getCodeGroupe()).isEqualTo(UPDATED_CODE_GROUPE);
        assertThat(testFacture.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testFacture.getDateDebut()).isEqualTo(UPDATED_DATE_DEBUT);
        assertThat(testFacture.getDateFin()).isEqualTo(UPDATED_DATE_FIN);
        assertThat(testFacture.getMontantForfetaire()).isEqualTo(UPDATED_MONTANT_FORFETAIRE);
        assertThat(testFacture.getMontantRemise()).isEqualTo(UPDATED_MONTANT_REMISE);
        assertThat(testFacture.getMontantPaye()).isEqualTo(UPDATED_MONTANT_PAYE);
        assertThat(testFacture.getMontantRestant()).isEqualTo(UPDATED_MONTANT_RESTANT);
        assertThat(testFacture.getMontantNet()).isEqualTo(UPDATED_MONTANT_NET);
        assertThat(testFacture.getStatutFacture()).isEqualTo(UPDATED_STATUT_FACTURE);
        assertThat(testFacture.getTypeFacture()).isEqualTo(UPDATED_TYPE_FACTURE);
        assertThat(testFacture.getMontantBrut()).isEqualTo(UPDATED_MONTANT_BRUT);
        assertThat(testFacture.isTemplate()).isEqualTo(UPDATED_TEMPLATE);
    }

    @Test
    @Transactional
    public void updateNonExistingFacture() throws Exception {
        int databaseSizeBeforeUpdate = factureRepository.findAll().size();

        // Create the Facture
        FactureDTO factureDTO = factureMapper.toDto(facture);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFactureMockMvc.perform(put("/api/factures").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(factureDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Facture in the database
        List<Facture> factureList = factureRepository.findAll();
        assertThat(factureList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFacture() throws Exception {
        // Initialize the database
        factureRepository.saveAndFlush(facture);

        int databaseSizeBeforeDelete = factureRepository.findAll().size();

        // Delete the facture
        restFactureMockMvc.perform(delete("/api/factures/{id}", facture.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Facture> factureList = factureRepository.findAll();
        assertThat(factureList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
