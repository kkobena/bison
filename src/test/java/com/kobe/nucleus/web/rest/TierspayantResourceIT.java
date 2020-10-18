package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.NucleusApp;
import com.kobe.nucleus.domain.Tierspayant;
import com.kobe.nucleus.repository.TierspayantRepository;
import com.kobe.nucleus.service.TierspayantService;
import com.kobe.nucleus.service.dto.TierspayantDTO;
import com.kobe.nucleus.service.mapper.TierspayantMapper;

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

import com.kobe.nucleus.domain.enumeration.TypeTierspayant;
import com.kobe.nucleus.domain.enumeration.Status;
/**
 * Integration tests for the {@link TierspayantResource} REST controller.
 */
@SpringBootTest(classes = NucleusApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TierspayantResourceIT {

    private static final Instant DEFAULT_CREATED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_UPDATED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_LIBEL_COURT = "AAAAAAAAAA";
    private static final String UPDATED_LIBEL_COURT = "BBBBBBBBBB";

    private static final String DEFAULT_LIBEL_LONG = "AAAAAAAAAA";
    private static final String UPDATED_LIBEL_LONG = "BBBBBBBBBB";

    private static final Integer DEFAULT_PLAFOND = 1;
    private static final Integer UPDATED_PLAFOND = 2;

    private static final Boolean DEFAULT_TYPE_PLAFOND = false;
    private static final Boolean UPDATED_TYPE_PLAFOND = true;

    private static final Integer DEFAULT_CONSO_JOURNALIERE = 1;
    private static final Integer UPDATED_CONSO_JOURNALIERE = 2;

    private static final Integer DEFAULT_CONSO_MENSUELLE = 1;
    private static final Integer UPDATED_CONSO_MENSUELLE = 2;

    private static final TypeTierspayant DEFAULT_TYPE_TP = TypeTierspayant.ASSURANCE;
    private static final TypeTierspayant UPDATED_TYPE_TP = TypeTierspayant.CARNET;

    private static final String DEFAULT_CODE_COMPTABLE = "AAAAAAAAAA";
    private static final String UPDATED_CODE_COMPTABLE = "BBBBBBBBBB";

    private static final Integer DEFAULT_NBRE_BORDEREAUX = 1;
    private static final Integer UPDATED_NBRE_BORDEREAUX = 2;

    private static final String DEFAULT_REGISTRECOMMERCE = "AAAAAAAAAA";
    private static final String UPDATED_REGISTRECOMMERCE = "BBBBBBBBBB";

    private static final Status DEFAULT_STATUS = Status.ACTIVE;
    private static final Status UPDATED_STATUS = Status.ENATTENTE;

    private static final String DEFAULT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_MOBILE = "AAAAAAAAAA";
    private static final String UPDATED_MOBILE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ENABLE = false;
    private static final Boolean UPDATED_ENABLE = true;

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final Integer DEFAULT_MONTANT_MAX_FACTURE = 0;
    private static final Integer UPDATED_MONTANT_MAX_FACTURE = 1;

    private static final Integer DEFAULT_REMISE_FORFETAIRE = 0;
    private static final Integer UPDATED_REMISE_FORFETAIRE = 1;

    @Autowired
    private TierspayantRepository tierspayantRepository;

    @Autowired
    private TierspayantMapper tierspayantMapper;

    @Autowired
    private TierspayantService tierspayantService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTierspayantMockMvc;

    private Tierspayant tierspayant;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Tierspayant createEntity(EntityManager em) {
        Tierspayant tierspayant = new Tierspayant()
            .createdAt(DEFAULT_CREATED_AT)
            .updatedAt(DEFAULT_UPDATED_AT)
            .code(DEFAULT_CODE)
            .libelCourt(DEFAULT_LIBEL_COURT)
            .libelLong(DEFAULT_LIBEL_LONG)
            .plafond(DEFAULT_PLAFOND)
            .typePlafond(DEFAULT_TYPE_PLAFOND)
            .consoJournaliere(DEFAULT_CONSO_JOURNALIERE)
            .consoMensuelle(DEFAULT_CONSO_MENSUELLE)
            .typeTp(DEFAULT_TYPE_TP)
            .codeComptable(DEFAULT_CODE_COMPTABLE)
            .nbreBordereaux(DEFAULT_NBRE_BORDEREAUX)
            .registrecommerce(DEFAULT_REGISTRECOMMERCE)
            .status(DEFAULT_STATUS)
            .phone(DEFAULT_PHONE)
            .mobile(DEFAULT_MOBILE)
            .enable(DEFAULT_ENABLE)
            .address(DEFAULT_ADDRESS)
            .montantMaxFacture(DEFAULT_MONTANT_MAX_FACTURE)
            .remiseForfetaire(DEFAULT_REMISE_FORFETAIRE);
        return tierspayant;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Tierspayant createUpdatedEntity(EntityManager em) {
        Tierspayant tierspayant = new Tierspayant()
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .code(UPDATED_CODE)
            .libelCourt(UPDATED_LIBEL_COURT)
            .libelLong(UPDATED_LIBEL_LONG)
            .plafond(UPDATED_PLAFOND)
            .typePlafond(UPDATED_TYPE_PLAFOND)
            .consoJournaliere(UPDATED_CONSO_JOURNALIERE)
            .consoMensuelle(UPDATED_CONSO_MENSUELLE)
            .typeTp(UPDATED_TYPE_TP)
            .codeComptable(UPDATED_CODE_COMPTABLE)
            .nbreBordereaux(UPDATED_NBRE_BORDEREAUX)
            .registrecommerce(UPDATED_REGISTRECOMMERCE)
            .status(UPDATED_STATUS)
            .phone(UPDATED_PHONE)
            .mobile(UPDATED_MOBILE)
            .enable(UPDATED_ENABLE)
            .address(UPDATED_ADDRESS)
            .montantMaxFacture(UPDATED_MONTANT_MAX_FACTURE)
            .remiseForfetaire(UPDATED_REMISE_FORFETAIRE);
        return tierspayant;
    }

    @BeforeEach
    public void initTest() {
        tierspayant = createEntity(em);
    }

    @Test
    @Transactional
    public void createTierspayant() throws Exception {
        int databaseSizeBeforeCreate = tierspayantRepository.findAll().size();
        // Create the Tierspayant
        TierspayantDTO tierspayantDTO = tierspayantMapper.toDto(tierspayant);
        restTierspayantMockMvc.perform(post("/api/tierspayants").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tierspayantDTO)))
            .andExpect(status().isCreated());

        // Validate the Tierspayant in the database
        List<Tierspayant> tierspayantList = tierspayantRepository.findAll();
        assertThat(tierspayantList).hasSize(databaseSizeBeforeCreate + 1);
        Tierspayant testTierspayant = tierspayantList.get(tierspayantList.size() - 1);
        assertThat(testTierspayant.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testTierspayant.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testTierspayant.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testTierspayant.getLibelCourt()).isEqualTo(DEFAULT_LIBEL_COURT);
        assertThat(testTierspayant.getLibelLong()).isEqualTo(DEFAULT_LIBEL_LONG);
        assertThat(testTierspayant.getPlafond()).isEqualTo(DEFAULT_PLAFOND);
        assertThat(testTierspayant.isTypePlafond()).isEqualTo(DEFAULT_TYPE_PLAFOND);
        assertThat(testTierspayant.getConsoJournaliere()).isEqualTo(DEFAULT_CONSO_JOURNALIERE);
        assertThat(testTierspayant.getConsoMensuelle()).isEqualTo(DEFAULT_CONSO_MENSUELLE);
        assertThat(testTierspayant.getTypeTp()).isEqualTo(DEFAULT_TYPE_TP);
        assertThat(testTierspayant.getCodeComptable()).isEqualTo(DEFAULT_CODE_COMPTABLE);
        assertThat(testTierspayant.getNbreBordereaux()).isEqualTo(DEFAULT_NBRE_BORDEREAUX);
        assertThat(testTierspayant.getRegistrecommerce()).isEqualTo(DEFAULT_REGISTRECOMMERCE);
        assertThat(testTierspayant.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testTierspayant.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testTierspayant.getMobile()).isEqualTo(DEFAULT_MOBILE);
        assertThat(testTierspayant.isEnable()).isEqualTo(DEFAULT_ENABLE);
        assertThat(testTierspayant.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testTierspayant.getMontantMaxFacture()).isEqualTo(DEFAULT_MONTANT_MAX_FACTURE);
        assertThat(testTierspayant.getRemiseForfetaire()).isEqualTo(DEFAULT_REMISE_FORFETAIRE);
    }

    @Test
    @Transactional
    public void createTierspayantWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tierspayantRepository.findAll().size();

        // Create the Tierspayant with an existing ID
        tierspayant.setId(1L);
        TierspayantDTO tierspayantDTO = tierspayantMapper.toDto(tierspayant);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTierspayantMockMvc.perform(post("/api/tierspayants").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tierspayantDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Tierspayant in the database
        List<Tierspayant> tierspayantList = tierspayantRepository.findAll();
        assertThat(tierspayantList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCreatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = tierspayantRepository.findAll().size();
        // set the field null
        tierspayant.setCreatedAt(null);

        // Create the Tierspayant, which fails.
        TierspayantDTO tierspayantDTO = tierspayantMapper.toDto(tierspayant);


        restTierspayantMockMvc.perform(post("/api/tierspayants").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tierspayantDTO)))
            .andExpect(status().isBadRequest());

        List<Tierspayant> tierspayantList = tierspayantRepository.findAll();
        assertThat(tierspayantList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUpdatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = tierspayantRepository.findAll().size();
        // set the field null
        tierspayant.setUpdatedAt(null);

        // Create the Tierspayant, which fails.
        TierspayantDTO tierspayantDTO = tierspayantMapper.toDto(tierspayant);


        restTierspayantMockMvc.perform(post("/api/tierspayants").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tierspayantDTO)))
            .andExpect(status().isBadRequest());

        List<Tierspayant> tierspayantList = tierspayantRepository.findAll();
        assertThat(tierspayantList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = tierspayantRepository.findAll().size();
        // set the field null
        tierspayant.setStatus(null);

        // Create the Tierspayant, which fails.
        TierspayantDTO tierspayantDTO = tierspayantMapper.toDto(tierspayant);


        restTierspayantMockMvc.perform(post("/api/tierspayants").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tierspayantDTO)))
            .andExpect(status().isBadRequest());

        List<Tierspayant> tierspayantList = tierspayantRepository.findAll();
        assertThat(tierspayantList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEnableIsRequired() throws Exception {
        int databaseSizeBeforeTest = tierspayantRepository.findAll().size();
        // set the field null
        tierspayant.setEnable(null);

        // Create the Tierspayant, which fails.
        TierspayantDTO tierspayantDTO = tierspayantMapper.toDto(tierspayant);


        restTierspayantMockMvc.perform(post("/api/tierspayants").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tierspayantDTO)))
            .andExpect(status().isBadRequest());

        List<Tierspayant> tierspayantList = tierspayantRepository.findAll();
        assertThat(tierspayantList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRemiseForfetaireIsRequired() throws Exception {
        int databaseSizeBeforeTest = tierspayantRepository.findAll().size();
        // set the field null
        tierspayant.setRemiseForfetaire(null);

        // Create the Tierspayant, which fails.
        TierspayantDTO tierspayantDTO = tierspayantMapper.toDto(tierspayant);


        restTierspayantMockMvc.perform(post("/api/tierspayants").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tierspayantDTO)))
            .andExpect(status().isBadRequest());

        List<Tierspayant> tierspayantList = tierspayantRepository.findAll();
        assertThat(tierspayantList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTierspayants() throws Exception {
        // Initialize the database
        tierspayantRepository.saveAndFlush(tierspayant);

        // Get all the tierspayantList
        restTierspayantMockMvc.perform(get("/api/tierspayants?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tierspayant.getId().intValue())))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(DEFAULT_UPDATED_AT.toString())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].libelCourt").value(hasItem(DEFAULT_LIBEL_COURT)))
            .andExpect(jsonPath("$.[*].libelLong").value(hasItem(DEFAULT_LIBEL_LONG)))
            .andExpect(jsonPath("$.[*].plafond").value(hasItem(DEFAULT_PLAFOND)))
            .andExpect(jsonPath("$.[*].typePlafond").value(hasItem(DEFAULT_TYPE_PLAFOND.booleanValue())))
            .andExpect(jsonPath("$.[*].consoJournaliere").value(hasItem(DEFAULT_CONSO_JOURNALIERE)))
            .andExpect(jsonPath("$.[*].consoMensuelle").value(hasItem(DEFAULT_CONSO_MENSUELLE)))
            .andExpect(jsonPath("$.[*].typeTp").value(hasItem(DEFAULT_TYPE_TP.toString())))
            .andExpect(jsonPath("$.[*].codeComptable").value(hasItem(DEFAULT_CODE_COMPTABLE)))
            .andExpect(jsonPath("$.[*].nbreBordereaux").value(hasItem(DEFAULT_NBRE_BORDEREAUX)))
            .andExpect(jsonPath("$.[*].registrecommerce").value(hasItem(DEFAULT_REGISTRECOMMERCE)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE)))
            .andExpect(jsonPath("$.[*].mobile").value(hasItem(DEFAULT_MOBILE)))
            .andExpect(jsonPath("$.[*].enable").value(hasItem(DEFAULT_ENABLE.booleanValue())))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)))
            .andExpect(jsonPath("$.[*].montantMaxFacture").value(hasItem(DEFAULT_MONTANT_MAX_FACTURE)))
            .andExpect(jsonPath("$.[*].remiseForfetaire").value(hasItem(DEFAULT_REMISE_FORFETAIRE)));
    }
    
    @Test
    @Transactional
    public void getTierspayant() throws Exception {
        // Initialize the database
        tierspayantRepository.saveAndFlush(tierspayant);

        // Get the tierspayant
        restTierspayantMockMvc.perform(get("/api/tierspayants/{id}", tierspayant.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tierspayant.getId().intValue()))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.updatedAt").value(DEFAULT_UPDATED_AT.toString()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.libelCourt").value(DEFAULT_LIBEL_COURT))
            .andExpect(jsonPath("$.libelLong").value(DEFAULT_LIBEL_LONG))
            .andExpect(jsonPath("$.plafond").value(DEFAULT_PLAFOND))
            .andExpect(jsonPath("$.typePlafond").value(DEFAULT_TYPE_PLAFOND.booleanValue()))
            .andExpect(jsonPath("$.consoJournaliere").value(DEFAULT_CONSO_JOURNALIERE))
            .andExpect(jsonPath("$.consoMensuelle").value(DEFAULT_CONSO_MENSUELLE))
            .andExpect(jsonPath("$.typeTp").value(DEFAULT_TYPE_TP.toString()))
            .andExpect(jsonPath("$.codeComptable").value(DEFAULT_CODE_COMPTABLE))
            .andExpect(jsonPath("$.nbreBordereaux").value(DEFAULT_NBRE_BORDEREAUX))
            .andExpect(jsonPath("$.registrecommerce").value(DEFAULT_REGISTRECOMMERCE))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE))
            .andExpect(jsonPath("$.mobile").value(DEFAULT_MOBILE))
            .andExpect(jsonPath("$.enable").value(DEFAULT_ENABLE.booleanValue()))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS))
            .andExpect(jsonPath("$.montantMaxFacture").value(DEFAULT_MONTANT_MAX_FACTURE))
            .andExpect(jsonPath("$.remiseForfetaire").value(DEFAULT_REMISE_FORFETAIRE));
    }
    @Test
    @Transactional
    public void getNonExistingTierspayant() throws Exception {
        // Get the tierspayant
        restTierspayantMockMvc.perform(get("/api/tierspayants/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTierspayant() throws Exception {
        // Initialize the database
        tierspayantRepository.saveAndFlush(tierspayant);

        int databaseSizeBeforeUpdate = tierspayantRepository.findAll().size();

        // Update the tierspayant
        Tierspayant updatedTierspayant = tierspayantRepository.findById(tierspayant.getId()).get();
        // Disconnect from session so that the updates on updatedTierspayant are not directly saved in db
        em.detach(updatedTierspayant);
        updatedTierspayant
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .code(UPDATED_CODE)
            .libelCourt(UPDATED_LIBEL_COURT)
            .libelLong(UPDATED_LIBEL_LONG)
            .plafond(UPDATED_PLAFOND)
            .typePlafond(UPDATED_TYPE_PLAFOND)
            .consoJournaliere(UPDATED_CONSO_JOURNALIERE)
            .consoMensuelle(UPDATED_CONSO_MENSUELLE)
            .typeTp(UPDATED_TYPE_TP)
            .codeComptable(UPDATED_CODE_COMPTABLE)
            .nbreBordereaux(UPDATED_NBRE_BORDEREAUX)
            .registrecommerce(UPDATED_REGISTRECOMMERCE)
            .status(UPDATED_STATUS)
            .phone(UPDATED_PHONE)
            .mobile(UPDATED_MOBILE)
            .enable(UPDATED_ENABLE)
            .address(UPDATED_ADDRESS)
            .montantMaxFacture(UPDATED_MONTANT_MAX_FACTURE)
            .remiseForfetaire(UPDATED_REMISE_FORFETAIRE);
        TierspayantDTO tierspayantDTO = tierspayantMapper.toDto(updatedTierspayant);

        restTierspayantMockMvc.perform(put("/api/tierspayants").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tierspayantDTO)))
            .andExpect(status().isOk());

        // Validate the Tierspayant in the database
        List<Tierspayant> tierspayantList = tierspayantRepository.findAll();
        assertThat(tierspayantList).hasSize(databaseSizeBeforeUpdate);
        Tierspayant testTierspayant = tierspayantList.get(tierspayantList.size() - 1);
        assertThat(testTierspayant.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testTierspayant.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testTierspayant.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testTierspayant.getLibelCourt()).isEqualTo(UPDATED_LIBEL_COURT);
        assertThat(testTierspayant.getLibelLong()).isEqualTo(UPDATED_LIBEL_LONG);
        assertThat(testTierspayant.getPlafond()).isEqualTo(UPDATED_PLAFOND);
        assertThat(testTierspayant.isTypePlafond()).isEqualTo(UPDATED_TYPE_PLAFOND);
        assertThat(testTierspayant.getConsoJournaliere()).isEqualTo(UPDATED_CONSO_JOURNALIERE);
        assertThat(testTierspayant.getConsoMensuelle()).isEqualTo(UPDATED_CONSO_MENSUELLE);
        assertThat(testTierspayant.getTypeTp()).isEqualTo(UPDATED_TYPE_TP);
        assertThat(testTierspayant.getCodeComptable()).isEqualTo(UPDATED_CODE_COMPTABLE);
        assertThat(testTierspayant.getNbreBordereaux()).isEqualTo(UPDATED_NBRE_BORDEREAUX);
        assertThat(testTierspayant.getRegistrecommerce()).isEqualTo(UPDATED_REGISTRECOMMERCE);
        assertThat(testTierspayant.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testTierspayant.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testTierspayant.getMobile()).isEqualTo(UPDATED_MOBILE);
        assertThat(testTierspayant.isEnable()).isEqualTo(UPDATED_ENABLE);
        assertThat(testTierspayant.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testTierspayant.getMontantMaxFacture()).isEqualTo(UPDATED_MONTANT_MAX_FACTURE);
        assertThat(testTierspayant.getRemiseForfetaire()).isEqualTo(UPDATED_REMISE_FORFETAIRE);
    }

    @Test
    @Transactional
    public void updateNonExistingTierspayant() throws Exception {
        int databaseSizeBeforeUpdate = tierspayantRepository.findAll().size();

        // Create the Tierspayant
        TierspayantDTO tierspayantDTO = tierspayantMapper.toDto(tierspayant);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTierspayantMockMvc.perform(put("/api/tierspayants").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tierspayantDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Tierspayant in the database
        List<Tierspayant> tierspayantList = tierspayantRepository.findAll();
        assertThat(tierspayantList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTierspayant() throws Exception {
        // Initialize the database
        tierspayantRepository.saveAndFlush(tierspayant);

        int databaseSizeBeforeDelete = tierspayantRepository.findAll().size();

        // Delete the tierspayant
        restTierspayantMockMvc.perform(delete("/api/tierspayants/{id}", tierspayant.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Tierspayant> tierspayantList = tierspayantRepository.findAll();
        assertThat(tierspayantList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
