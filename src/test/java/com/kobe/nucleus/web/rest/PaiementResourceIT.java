package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.NucleusApp;
import com.kobe.nucleus.domain.Paiement;
import com.kobe.nucleus.repository.PaiementRepository;
import com.kobe.nucleus.service.PaiementService;
import com.kobe.nucleus.service.dto.PaiementDTO;
import com.kobe.nucleus.service.mapper.PaiementMapper;

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

import com.kobe.nucleus.domain.enumeration.GroupeModePayment;
import com.kobe.nucleus.domain.enumeration.CategorieTransaction;
/**
 * Integration tests for the {@link PaiementResource} REST controller.
 */
@SpringBootTest(classes = NucleusApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PaiementResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_CHECKED = false;
    private static final Boolean UPDATED_CHECKED = true;

    private static final String DEFAULT_PKEY = "AAAAAAAAAA";
    private static final String UPDATED_PKEY = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_UPDATED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_MONTANT_NET = 1;
    private static final Integer UPDATED_MONTANT_NET = 2;

    private static final Integer DEFAULT_MONTANT_BRUT = 1;
    private static final Integer UPDATED_MONTANT_BRUT = 2;

    private static final Integer DEFAULT_MONTANT_REMISE = 1;
    private static final Integer UPDATED_MONTANT_REMISE = 2;

    private static final Integer DEFAULT_MONTANT_DEBIT = 1;
    private static final Integer UPDATED_MONTANT_DEBIT = 2;

    private static final Integer DEFAULT_MONTANT_CREDIT = 1;
    private static final Integer UPDATED_MONTANT_CREDIT = 2;

    private static final Integer DEFAULT_MONTANT_VERSE = 1;
    private static final Integer UPDATED_MONTANT_VERSE = 2;

    private static final Integer DEFAULT_MONTANT_RENDU = 1;
    private static final Integer UPDATED_MONTANT_RENDU = 2;

    private static final Integer DEFAULT_MONTANT_RESTANT = 1;
    private static final Integer UPDATED_MONTANT_RESTANT = 2;

    private static final GroupeModePayment DEFAULT_GROUPE_MODE = GroupeModePayment.COMPTANT;
    private static final GroupeModePayment UPDATED_GROUPE_MODE = GroupeModePayment.CREDIT;

    private static final CategorieTransaction DEFAULT_CATEGORIE = CategorieTransaction.DEBIT;
    private static final CategorieTransaction UPDATED_CATEGORIE = CategorieTransaction.CREDIT;

    private static final LocalDate DEFAULT_DATE_MVT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_MVT = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_REF = "AAAAAAAAAA";
    private static final String UPDATED_REF = "BBBBBBBBBB";

    private static final String DEFAULT_ORGANISME = "AAAAAAAAAA";
    private static final String UPDATED_ORGANISME = "BBBBBBBBBB";

    @Autowired
    private PaiementRepository paiementRepository;

    @Autowired
    private PaiementMapper paiementMapper;

    @Autowired
    private PaiementService paiementService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPaiementMockMvc;

    private Paiement paiement;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Paiement createEntity(EntityManager em) {
        Paiement paiement = new Paiement()
            .libelle(DEFAULT_LIBELLE)
            .checked(DEFAULT_CHECKED)
            .pkey(DEFAULT_PKEY)
            .createdAt(DEFAULT_CREATED_AT)
            .updatedAt(DEFAULT_UPDATED_AT)
            .montantNet(DEFAULT_MONTANT_NET)
            .montantBrut(DEFAULT_MONTANT_BRUT)
            .montantRemise(DEFAULT_MONTANT_REMISE)
            .montantDebit(DEFAULT_MONTANT_DEBIT)
            .montantCredit(DEFAULT_MONTANT_CREDIT)
            .montantVerse(DEFAULT_MONTANT_VERSE)
            .montantRendu(DEFAULT_MONTANT_RENDU)
            .montantRestant(DEFAULT_MONTANT_RESTANT)
            .groupeMode(DEFAULT_GROUPE_MODE)
            .categorie(DEFAULT_CATEGORIE)
            .dateMVT(DEFAULT_DATE_MVT)
            .ref(DEFAULT_REF)
            .organisme(DEFAULT_ORGANISME);
        return paiement;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Paiement createUpdatedEntity(EntityManager em) {
        Paiement paiement = new Paiement()
            .libelle(UPDATED_LIBELLE)
            .checked(UPDATED_CHECKED)
            .pkey(UPDATED_PKEY)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .montantNet(UPDATED_MONTANT_NET)
            .montantBrut(UPDATED_MONTANT_BRUT)
            .montantRemise(UPDATED_MONTANT_REMISE)
            .montantDebit(UPDATED_MONTANT_DEBIT)
            .montantCredit(UPDATED_MONTANT_CREDIT)
            .montantVerse(UPDATED_MONTANT_VERSE)
            .montantRendu(UPDATED_MONTANT_RENDU)
            .montantRestant(UPDATED_MONTANT_RESTANT)
            .groupeMode(UPDATED_GROUPE_MODE)
            .categorie(UPDATED_CATEGORIE)
            .dateMVT(UPDATED_DATE_MVT)
            .ref(UPDATED_REF)
            .organisme(UPDATED_ORGANISME);
        return paiement;
    }

    @BeforeEach
    public void initTest() {
        paiement = createEntity(em);
    }

    @Test
    @Transactional
    public void createPaiement() throws Exception {
        int databaseSizeBeforeCreate = paiementRepository.findAll().size();
        // Create the Paiement
        PaiementDTO paiementDTO = paiementMapper.toDto(paiement);
        restPaiementMockMvc.perform(post("/api/paiements").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paiementDTO)))
            .andExpect(status().isCreated());

        // Validate the Paiement in the database
        List<Paiement> paiementList = paiementRepository.findAll();
        assertThat(paiementList).hasSize(databaseSizeBeforeCreate + 1);
        Paiement testPaiement = paiementList.get(paiementList.size() - 1);
        assertThat(testPaiement.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
        assertThat(testPaiement.isChecked()).isEqualTo(DEFAULT_CHECKED);
        assertThat(testPaiement.getPkey()).isEqualTo(DEFAULT_PKEY);
        assertThat(testPaiement.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testPaiement.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testPaiement.getMontantNet()).isEqualTo(DEFAULT_MONTANT_NET);
        assertThat(testPaiement.getMontantBrut()).isEqualTo(DEFAULT_MONTANT_BRUT);
        assertThat(testPaiement.getMontantRemise()).isEqualTo(DEFAULT_MONTANT_REMISE);
        assertThat(testPaiement.getMontantDebit()).isEqualTo(DEFAULT_MONTANT_DEBIT);
        assertThat(testPaiement.getMontantCredit()).isEqualTo(DEFAULT_MONTANT_CREDIT);
        assertThat(testPaiement.getMontantVerse()).isEqualTo(DEFAULT_MONTANT_VERSE);
        assertThat(testPaiement.getMontantRendu()).isEqualTo(DEFAULT_MONTANT_RENDU);
        assertThat(testPaiement.getMontantRestant()).isEqualTo(DEFAULT_MONTANT_RESTANT);
        assertThat(testPaiement.getGroupeMode()).isEqualTo(DEFAULT_GROUPE_MODE);
        assertThat(testPaiement.getCategorie()).isEqualTo(DEFAULT_CATEGORIE);
        assertThat(testPaiement.getDateMVT()).isEqualTo(DEFAULT_DATE_MVT);
        assertThat(testPaiement.getRef()).isEqualTo(DEFAULT_REF);
        assertThat(testPaiement.getOrganisme()).isEqualTo(DEFAULT_ORGANISME);
    }

    @Test
    @Transactional
    public void createPaiementWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = paiementRepository.findAll().size();

        // Create the Paiement with an existing ID
        paiement.setId(1L);
        PaiementDTO paiementDTO = paiementMapper.toDto(paiement);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPaiementMockMvc.perform(post("/api/paiements").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paiementDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Paiement in the database
        List<Paiement> paiementList = paiementRepository.findAll();
        assertThat(paiementList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCheckedIsRequired() throws Exception {
        int databaseSizeBeforeTest = paiementRepository.findAll().size();
        // set the field null
        paiement.setChecked(null);

        // Create the Paiement, which fails.
        PaiementDTO paiementDTO = paiementMapper.toDto(paiement);


        restPaiementMockMvc.perform(post("/api/paiements").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paiementDTO)))
            .andExpect(status().isBadRequest());

        List<Paiement> paiementList = paiementRepository.findAll();
        assertThat(paiementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPkeyIsRequired() throws Exception {
        int databaseSizeBeforeTest = paiementRepository.findAll().size();
        // set the field null
        paiement.setPkey(null);

        // Create the Paiement, which fails.
        PaiementDTO paiementDTO = paiementMapper.toDto(paiement);


        restPaiementMockMvc.perform(post("/api/paiements").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paiementDTO)))
            .andExpect(status().isBadRequest());

        List<Paiement> paiementList = paiementRepository.findAll();
        assertThat(paiementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = paiementRepository.findAll().size();
        // set the field null
        paiement.setCreatedAt(null);

        // Create the Paiement, which fails.
        PaiementDTO paiementDTO = paiementMapper.toDto(paiement);


        restPaiementMockMvc.perform(post("/api/paiements").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paiementDTO)))
            .andExpect(status().isBadRequest());

        List<Paiement> paiementList = paiementRepository.findAll();
        assertThat(paiementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUpdatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = paiementRepository.findAll().size();
        // set the field null
        paiement.setUpdatedAt(null);

        // Create the Paiement, which fails.
        PaiementDTO paiementDTO = paiementMapper.toDto(paiement);


        restPaiementMockMvc.perform(post("/api/paiements").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paiementDTO)))
            .andExpect(status().isBadRequest());

        List<Paiement> paiementList = paiementRepository.findAll();
        assertThat(paiementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMontantNetIsRequired() throws Exception {
        int databaseSizeBeforeTest = paiementRepository.findAll().size();
        // set the field null
        paiement.setMontantNet(null);

        // Create the Paiement, which fails.
        PaiementDTO paiementDTO = paiementMapper.toDto(paiement);


        restPaiementMockMvc.perform(post("/api/paiements").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paiementDTO)))
            .andExpect(status().isBadRequest());

        List<Paiement> paiementList = paiementRepository.findAll();
        assertThat(paiementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMontantBrutIsRequired() throws Exception {
        int databaseSizeBeforeTest = paiementRepository.findAll().size();
        // set the field null
        paiement.setMontantBrut(null);

        // Create the Paiement, which fails.
        PaiementDTO paiementDTO = paiementMapper.toDto(paiement);


        restPaiementMockMvc.perform(post("/api/paiements").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paiementDTO)))
            .andExpect(status().isBadRequest());

        List<Paiement> paiementList = paiementRepository.findAll();
        assertThat(paiementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMontantRemiseIsRequired() throws Exception {
        int databaseSizeBeforeTest = paiementRepository.findAll().size();
        // set the field null
        paiement.setMontantRemise(null);

        // Create the Paiement, which fails.
        PaiementDTO paiementDTO = paiementMapper.toDto(paiement);


        restPaiementMockMvc.perform(post("/api/paiements").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paiementDTO)))
            .andExpect(status().isBadRequest());

        List<Paiement> paiementList = paiementRepository.findAll();
        assertThat(paiementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMontantDebitIsRequired() throws Exception {
        int databaseSizeBeforeTest = paiementRepository.findAll().size();
        // set the field null
        paiement.setMontantDebit(null);

        // Create the Paiement, which fails.
        PaiementDTO paiementDTO = paiementMapper.toDto(paiement);


        restPaiementMockMvc.perform(post("/api/paiements").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paiementDTO)))
            .andExpect(status().isBadRequest());

        List<Paiement> paiementList = paiementRepository.findAll();
        assertThat(paiementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMontantCreditIsRequired() throws Exception {
        int databaseSizeBeforeTest = paiementRepository.findAll().size();
        // set the field null
        paiement.setMontantCredit(null);

        // Create the Paiement, which fails.
        PaiementDTO paiementDTO = paiementMapper.toDto(paiement);


        restPaiementMockMvc.perform(post("/api/paiements").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paiementDTO)))
            .andExpect(status().isBadRequest());

        List<Paiement> paiementList = paiementRepository.findAll();
        assertThat(paiementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMontantVerseIsRequired() throws Exception {
        int databaseSizeBeforeTest = paiementRepository.findAll().size();
        // set the field null
        paiement.setMontantVerse(null);

        // Create the Paiement, which fails.
        PaiementDTO paiementDTO = paiementMapper.toDto(paiement);


        restPaiementMockMvc.perform(post("/api/paiements").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paiementDTO)))
            .andExpect(status().isBadRequest());

        List<Paiement> paiementList = paiementRepository.findAll();
        assertThat(paiementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMontantRenduIsRequired() throws Exception {
        int databaseSizeBeforeTest = paiementRepository.findAll().size();
        // set the field null
        paiement.setMontantRendu(null);

        // Create the Paiement, which fails.
        PaiementDTO paiementDTO = paiementMapper.toDto(paiement);


        restPaiementMockMvc.perform(post("/api/paiements").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paiementDTO)))
            .andExpect(status().isBadRequest());

        List<Paiement> paiementList = paiementRepository.findAll();
        assertThat(paiementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMontantRestantIsRequired() throws Exception {
        int databaseSizeBeforeTest = paiementRepository.findAll().size();
        // set the field null
        paiement.setMontantRestant(null);

        // Create the Paiement, which fails.
        PaiementDTO paiementDTO = paiementMapper.toDto(paiement);


        restPaiementMockMvc.perform(post("/api/paiements").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paiementDTO)))
            .andExpect(status().isBadRequest());

        List<Paiement> paiementList = paiementRepository.findAll();
        assertThat(paiementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPaiements() throws Exception {
        // Initialize the database
        paiementRepository.saveAndFlush(paiement);

        // Get all the paiementList
        restPaiementMockMvc.perform(get("/api/paiements?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(paiement.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)))
            .andExpect(jsonPath("$.[*].checked").value(hasItem(DEFAULT_CHECKED.booleanValue())))
            .andExpect(jsonPath("$.[*].pkey").value(hasItem(DEFAULT_PKEY)))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(DEFAULT_UPDATED_AT.toString())))
            .andExpect(jsonPath("$.[*].montantNet").value(hasItem(DEFAULT_MONTANT_NET)))
            .andExpect(jsonPath("$.[*].montantBrut").value(hasItem(DEFAULT_MONTANT_BRUT)))
            .andExpect(jsonPath("$.[*].montantRemise").value(hasItem(DEFAULT_MONTANT_REMISE)))
            .andExpect(jsonPath("$.[*].montantDebit").value(hasItem(DEFAULT_MONTANT_DEBIT)))
            .andExpect(jsonPath("$.[*].montantCredit").value(hasItem(DEFAULT_MONTANT_CREDIT)))
            .andExpect(jsonPath("$.[*].montantVerse").value(hasItem(DEFAULT_MONTANT_VERSE)))
            .andExpect(jsonPath("$.[*].montantRendu").value(hasItem(DEFAULT_MONTANT_RENDU)))
            .andExpect(jsonPath("$.[*].montantRestant").value(hasItem(DEFAULT_MONTANT_RESTANT)))
            .andExpect(jsonPath("$.[*].groupeMode").value(hasItem(DEFAULT_GROUPE_MODE.toString())))
            .andExpect(jsonPath("$.[*].categorie").value(hasItem(DEFAULT_CATEGORIE.toString())))
            .andExpect(jsonPath("$.[*].dateMVT").value(hasItem(DEFAULT_DATE_MVT.toString())))
            .andExpect(jsonPath("$.[*].ref").value(hasItem(DEFAULT_REF)))
            .andExpect(jsonPath("$.[*].organisme").value(hasItem(DEFAULT_ORGANISME)));
    }
    
    @Test
    @Transactional
    public void getPaiement() throws Exception {
        // Initialize the database
        paiementRepository.saveAndFlush(paiement);

        // Get the paiement
        restPaiementMockMvc.perform(get("/api/paiements/{id}", paiement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(paiement.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE))
            .andExpect(jsonPath("$.checked").value(DEFAULT_CHECKED.booleanValue()))
            .andExpect(jsonPath("$.pkey").value(DEFAULT_PKEY))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.updatedAt").value(DEFAULT_UPDATED_AT.toString()))
            .andExpect(jsonPath("$.montantNet").value(DEFAULT_MONTANT_NET))
            .andExpect(jsonPath("$.montantBrut").value(DEFAULT_MONTANT_BRUT))
            .andExpect(jsonPath("$.montantRemise").value(DEFAULT_MONTANT_REMISE))
            .andExpect(jsonPath("$.montantDebit").value(DEFAULT_MONTANT_DEBIT))
            .andExpect(jsonPath("$.montantCredit").value(DEFAULT_MONTANT_CREDIT))
            .andExpect(jsonPath("$.montantVerse").value(DEFAULT_MONTANT_VERSE))
            .andExpect(jsonPath("$.montantRendu").value(DEFAULT_MONTANT_RENDU))
            .andExpect(jsonPath("$.montantRestant").value(DEFAULT_MONTANT_RESTANT))
            .andExpect(jsonPath("$.groupeMode").value(DEFAULT_GROUPE_MODE.toString()))
            .andExpect(jsonPath("$.categorie").value(DEFAULT_CATEGORIE.toString()))
            .andExpect(jsonPath("$.dateMVT").value(DEFAULT_DATE_MVT.toString()))
            .andExpect(jsonPath("$.ref").value(DEFAULT_REF))
            .andExpect(jsonPath("$.organisme").value(DEFAULT_ORGANISME));
    }
    @Test
    @Transactional
    public void getNonExistingPaiement() throws Exception {
        // Get the paiement
        restPaiementMockMvc.perform(get("/api/paiements/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePaiement() throws Exception {
        // Initialize the database
        paiementRepository.saveAndFlush(paiement);

        int databaseSizeBeforeUpdate = paiementRepository.findAll().size();

        // Update the paiement
        Paiement updatedPaiement = paiementRepository.findById(paiement.getId()).get();
        // Disconnect from session so that the updates on updatedPaiement are not directly saved in db
        em.detach(updatedPaiement);
        updatedPaiement
            .libelle(UPDATED_LIBELLE)
            .checked(UPDATED_CHECKED)
            .pkey(UPDATED_PKEY)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .montantNet(UPDATED_MONTANT_NET)
            .montantBrut(UPDATED_MONTANT_BRUT)
            .montantRemise(UPDATED_MONTANT_REMISE)
            .montantDebit(UPDATED_MONTANT_DEBIT)
            .montantCredit(UPDATED_MONTANT_CREDIT)
            .montantVerse(UPDATED_MONTANT_VERSE)
            .montantRendu(UPDATED_MONTANT_RENDU)
            .montantRestant(UPDATED_MONTANT_RESTANT)
            .groupeMode(UPDATED_GROUPE_MODE)
            .categorie(UPDATED_CATEGORIE)
            .dateMVT(UPDATED_DATE_MVT)
            .ref(UPDATED_REF)
            .organisme(UPDATED_ORGANISME);
        PaiementDTO paiementDTO = paiementMapper.toDto(updatedPaiement);

        restPaiementMockMvc.perform(put("/api/paiements").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paiementDTO)))
            .andExpect(status().isOk());

        // Validate the Paiement in the database
        List<Paiement> paiementList = paiementRepository.findAll();
        assertThat(paiementList).hasSize(databaseSizeBeforeUpdate);
        Paiement testPaiement = paiementList.get(paiementList.size() - 1);
        assertThat(testPaiement.getLibelle()).isEqualTo(UPDATED_LIBELLE);
        assertThat(testPaiement.isChecked()).isEqualTo(UPDATED_CHECKED);
        assertThat(testPaiement.getPkey()).isEqualTo(UPDATED_PKEY);
        assertThat(testPaiement.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testPaiement.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testPaiement.getMontantNet()).isEqualTo(UPDATED_MONTANT_NET);
        assertThat(testPaiement.getMontantBrut()).isEqualTo(UPDATED_MONTANT_BRUT);
        assertThat(testPaiement.getMontantRemise()).isEqualTo(UPDATED_MONTANT_REMISE);
        assertThat(testPaiement.getMontantDebit()).isEqualTo(UPDATED_MONTANT_DEBIT);
        assertThat(testPaiement.getMontantCredit()).isEqualTo(UPDATED_MONTANT_CREDIT);
        assertThat(testPaiement.getMontantVerse()).isEqualTo(UPDATED_MONTANT_VERSE);
        assertThat(testPaiement.getMontantRendu()).isEqualTo(UPDATED_MONTANT_RENDU);
        assertThat(testPaiement.getMontantRestant()).isEqualTo(UPDATED_MONTANT_RESTANT);
        assertThat(testPaiement.getGroupeMode()).isEqualTo(UPDATED_GROUPE_MODE);
        assertThat(testPaiement.getCategorie()).isEqualTo(UPDATED_CATEGORIE);
        assertThat(testPaiement.getDateMVT()).isEqualTo(UPDATED_DATE_MVT);
        assertThat(testPaiement.getRef()).isEqualTo(UPDATED_REF);
        assertThat(testPaiement.getOrganisme()).isEqualTo(UPDATED_ORGANISME);
    }

    @Test
    @Transactional
    public void updateNonExistingPaiement() throws Exception {
        int databaseSizeBeforeUpdate = paiementRepository.findAll().size();

        // Create the Paiement
        PaiementDTO paiementDTO = paiementMapper.toDto(paiement);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPaiementMockMvc.perform(put("/api/paiements").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paiementDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Paiement in the database
        List<Paiement> paiementList = paiementRepository.findAll();
        assertThat(paiementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePaiement() throws Exception {
        // Initialize the database
        paiementRepository.saveAndFlush(paiement);

        int databaseSizeBeforeDelete = paiementRepository.findAll().size();

        // Delete the paiement
        restPaiementMockMvc.perform(delete("/api/paiements/{id}", paiement.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Paiement> paiementList = paiementRepository.findAll();
        assertThat(paiementList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
