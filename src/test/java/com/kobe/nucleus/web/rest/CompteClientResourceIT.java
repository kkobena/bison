package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.NucleusApp;
import com.kobe.nucleus.domain.CompteClient;
import com.kobe.nucleus.domain.Client;
import com.kobe.nucleus.repository.CompteClientRepository;
import com.kobe.nucleus.service.CompteClientService;
import com.kobe.nucleus.service.dto.CompteClientDTO;
import com.kobe.nucleus.service.mapper.CompteClientMapper;

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

import com.kobe.nucleus.domain.enumeration.CategorieAssurance;
import com.kobe.nucleus.domain.enumeration.Status;
/**
 * Integration tests for the {@link CompteClientResource} REST controller.
 */
@SpringBootTest(classes = NucleusApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class CompteClientResourceIT {

    private static final Instant DEFAULT_CREATED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_UPDATED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_ENCOURS = 1;
    private static final Integer UPDATED_ENCOURS = 2;

    private static final Integer DEFAULT_VERSION = 1;
    private static final Integer UPDATED_VERSION = 2;

    private static final Integer DEFAULT_PLAFOND_JOURNALIER = 1;
    private static final Integer UPDATED_PLAFOND_JOURNALIER = 2;

    private static final Integer DEFAULT_PLAFOND_MENSUEL = 1;
    private static final Integer UPDATED_PLAFOND_MENSUEL = 2;

    private static final Integer DEFAULT_CONSOMMATION = 1;
    private static final Integer UPDATED_CONSOMMATION = 2;

    private static final Integer DEFAULT_CONSO_JOURNALIERE = 1;
    private static final Integer UPDATED_CONSO_JOURNALIERE = 2;

    private static final Integer DEFAULT_TAUX = 1;
    private static final Integer UPDATED_TAUX = 2;

    private static final String DEFAULT_NUM_MATICULE = "AAAAAAAAAA";
    private static final String UPDATED_NUM_MATICULE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ENBALE = false;
    private static final Boolean UPDATED_ENBALE = true;

    private static final CategorieAssurance DEFAULT_CATEGORIE = CategorieAssurance.RO;
    private static final CategorieAssurance UPDATED_CATEGORIE = CategorieAssurance.RC1;

    private static final Status DEFAULT_STATUS = Status.ACTIVE;
    private static final Status UPDATED_STATUS = Status.ENATTENTE;

    private static final Boolean DEFAULT_ABSOLUTE = false;
    private static final Boolean UPDATED_ABSOLUTE = true;

    @Autowired
    private CompteClientRepository compteClientRepository;

    @Autowired
    private CompteClientMapper compteClientMapper;

    @Autowired
    private CompteClientService compteClientService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCompteClientMockMvc;

    private CompteClient compteClient;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CompteClient createEntity(EntityManager em) {
        CompteClient compteClient = new CompteClient()
            .createdAt(DEFAULT_CREATED_AT)
            .updatedAt(DEFAULT_UPDATED_AT)
            .encours(DEFAULT_ENCOURS)
            .version(DEFAULT_VERSION)
            .plafondJournalier(DEFAULT_PLAFOND_JOURNALIER)
            .plafondMensuel(DEFAULT_PLAFOND_MENSUEL)
            .consommation(DEFAULT_CONSOMMATION)
            .consoJournaliere(DEFAULT_CONSO_JOURNALIERE)
            .taux(DEFAULT_TAUX)
            .numMaticule(DEFAULT_NUM_MATICULE)
            .enbale(DEFAULT_ENBALE)
            .categorie(DEFAULT_CATEGORIE)
            .status(DEFAULT_STATUS)
            .absolute(DEFAULT_ABSOLUTE);
        // Add required entity
        Client client;
        if (TestUtil.findAll(em, Client.class).isEmpty()) {
            client = ClientResourceIT.createEntity(em);
            em.persist(client);
            em.flush();
        } else {
            client = TestUtil.findAll(em, Client.class).get(0);
        }
        compteClient.setClient(client);
        return compteClient;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CompteClient createUpdatedEntity(EntityManager em) {
        CompteClient compteClient = new CompteClient()
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .encours(UPDATED_ENCOURS)
            .version(UPDATED_VERSION)
            .plafondJournalier(UPDATED_PLAFOND_JOURNALIER)
            .plafondMensuel(UPDATED_PLAFOND_MENSUEL)
            .consommation(UPDATED_CONSOMMATION)
            .consoJournaliere(UPDATED_CONSO_JOURNALIERE)
            .taux(UPDATED_TAUX)
            .numMaticule(UPDATED_NUM_MATICULE)
            .enbale(UPDATED_ENBALE)
            .categorie(UPDATED_CATEGORIE)
            .status(UPDATED_STATUS)
            .absolute(UPDATED_ABSOLUTE);
        // Add required entity
        Client client;
        if (TestUtil.findAll(em, Client.class).isEmpty()) {
            client = ClientResourceIT.createUpdatedEntity(em);
            em.persist(client);
            em.flush();
        } else {
            client = TestUtil.findAll(em, Client.class).get(0);
        }
        compteClient.setClient(client);
        return compteClient;
    }

    @BeforeEach
    public void initTest() {
        compteClient = createEntity(em);
    }

    @Test
    @Transactional
    public void createCompteClient() throws Exception {
        int databaseSizeBeforeCreate = compteClientRepository.findAll().size();

        // Create the CompteClient
        CompteClientDTO compteClientDTO = compteClientMapper.toDto(compteClient);
        restCompteClientMockMvc.perform(post("/api/compte-clients").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(compteClientDTO)))
            .andExpect(status().isCreated());

        // Validate the CompteClient in the database
        List<CompteClient> compteClientList = compteClientRepository.findAll();
        assertThat(compteClientList).hasSize(databaseSizeBeforeCreate + 1);
        CompteClient testCompteClient = compteClientList.get(compteClientList.size() - 1);
        assertThat(testCompteClient.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testCompteClient.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testCompteClient.getEncours()).isEqualTo(DEFAULT_ENCOURS);
        assertThat(testCompteClient.getVersion()).isEqualTo(DEFAULT_VERSION);
        assertThat(testCompteClient.getPlafondJournalier()).isEqualTo(DEFAULT_PLAFOND_JOURNALIER);
        assertThat(testCompteClient.getPlafondMensuel()).isEqualTo(DEFAULT_PLAFOND_MENSUEL);
        assertThat(testCompteClient.getConsommation()).isEqualTo(DEFAULT_CONSOMMATION);
        assertThat(testCompteClient.getConsoJournaliere()).isEqualTo(DEFAULT_CONSO_JOURNALIERE);
        assertThat(testCompteClient.getTaux()).isEqualTo(DEFAULT_TAUX);
        assertThat(testCompteClient.getNumMaticule()).isEqualTo(DEFAULT_NUM_MATICULE);
        assertThat(testCompteClient.isEnbale()).isEqualTo(DEFAULT_ENBALE);
        assertThat(testCompteClient.getCategorie()).isEqualTo(DEFAULT_CATEGORIE);
        assertThat(testCompteClient.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testCompteClient.isAbsolute()).isEqualTo(DEFAULT_ABSOLUTE);
    }

    @Test
    @Transactional
    public void createCompteClientWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = compteClientRepository.findAll().size();

        // Create the CompteClient with an existing ID
        compteClient.setId(1L);
        CompteClientDTO compteClientDTO = compteClientMapper.toDto(compteClient);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCompteClientMockMvc.perform(post("/api/compte-clients").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(compteClientDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CompteClient in the database
        List<CompteClient> compteClientList = compteClientRepository.findAll();
        assertThat(compteClientList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = compteClientRepository.findAll().size();
        // set the field null
        compteClient.setStatus(null);

        // Create the CompteClient, which fails.
        CompteClientDTO compteClientDTO = compteClientMapper.toDto(compteClient);

        restCompteClientMockMvc.perform(post("/api/compte-clients").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(compteClientDTO)))
            .andExpect(status().isBadRequest());

        List<CompteClient> compteClientList = compteClientRepository.findAll();
        assertThat(compteClientList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAbsoluteIsRequired() throws Exception {
        int databaseSizeBeforeTest = compteClientRepository.findAll().size();
        // set the field null
        compteClient.setAbsolute(null);

        // Create the CompteClient, which fails.
        CompteClientDTO compteClientDTO = compteClientMapper.toDto(compteClient);

        restCompteClientMockMvc.perform(post("/api/compte-clients").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(compteClientDTO)))
            .andExpect(status().isBadRequest());

        List<CompteClient> compteClientList = compteClientRepository.findAll();
        assertThat(compteClientList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCompteClients() throws Exception {
        // Initialize the database
        compteClientRepository.saveAndFlush(compteClient);

        // Get all the compteClientList
        restCompteClientMockMvc.perform(get("/api/compte-clients?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(compteClient.getId().intValue())))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(DEFAULT_UPDATED_AT.toString())))
            .andExpect(jsonPath("$.[*].encours").value(hasItem(DEFAULT_ENCOURS)))
            .andExpect(jsonPath("$.[*].version").value(hasItem(DEFAULT_VERSION)))
            .andExpect(jsonPath("$.[*].plafondJournalier").value(hasItem(DEFAULT_PLAFOND_JOURNALIER)))
            .andExpect(jsonPath("$.[*].plafondMensuel").value(hasItem(DEFAULT_PLAFOND_MENSUEL)))
            .andExpect(jsonPath("$.[*].consommation").value(hasItem(DEFAULT_CONSOMMATION)))
            .andExpect(jsonPath("$.[*].consoJournaliere").value(hasItem(DEFAULT_CONSO_JOURNALIERE)))
            .andExpect(jsonPath("$.[*].taux").value(hasItem(DEFAULT_TAUX)))
            .andExpect(jsonPath("$.[*].numMaticule").value(hasItem(DEFAULT_NUM_MATICULE)))
            .andExpect(jsonPath("$.[*].enbale").value(hasItem(DEFAULT_ENBALE.booleanValue())))
            .andExpect(jsonPath("$.[*].categorie").value(hasItem(DEFAULT_CATEGORIE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].absolute").value(hasItem(DEFAULT_ABSOLUTE.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getCompteClient() throws Exception {
        // Initialize the database
        compteClientRepository.saveAndFlush(compteClient);

        // Get the compteClient
        restCompteClientMockMvc.perform(get("/api/compte-clients/{id}", compteClient.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(compteClient.getId().intValue()))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.updatedAt").value(DEFAULT_UPDATED_AT.toString()))
            .andExpect(jsonPath("$.encours").value(DEFAULT_ENCOURS))
            .andExpect(jsonPath("$.version").value(DEFAULT_VERSION))
            .andExpect(jsonPath("$.plafondJournalier").value(DEFAULT_PLAFOND_JOURNALIER))
            .andExpect(jsonPath("$.plafondMensuel").value(DEFAULT_PLAFOND_MENSUEL))
            .andExpect(jsonPath("$.consommation").value(DEFAULT_CONSOMMATION))
            .andExpect(jsonPath("$.consoJournaliere").value(DEFAULT_CONSO_JOURNALIERE))
            .andExpect(jsonPath("$.taux").value(DEFAULT_TAUX))
            .andExpect(jsonPath("$.numMaticule").value(DEFAULT_NUM_MATICULE))
            .andExpect(jsonPath("$.enbale").value(DEFAULT_ENBALE.booleanValue()))
            .andExpect(jsonPath("$.categorie").value(DEFAULT_CATEGORIE.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.absolute").value(DEFAULT_ABSOLUTE.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingCompteClient() throws Exception {
        // Get the compteClient
        restCompteClientMockMvc.perform(get("/api/compte-clients/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCompteClient() throws Exception {
        // Initialize the database
        compteClientRepository.saveAndFlush(compteClient);

        int databaseSizeBeforeUpdate = compteClientRepository.findAll().size();

        // Update the compteClient
        CompteClient updatedCompteClient = compteClientRepository.findById(compteClient.getId()).get();
        // Disconnect from session so that the updates on updatedCompteClient are not directly saved in db
        em.detach(updatedCompteClient);
        updatedCompteClient
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .encours(UPDATED_ENCOURS)
            .version(UPDATED_VERSION)
            .plafondJournalier(UPDATED_PLAFOND_JOURNALIER)
            .plafondMensuel(UPDATED_PLAFOND_MENSUEL)
            .consommation(UPDATED_CONSOMMATION)
            .consoJournaliere(UPDATED_CONSO_JOURNALIERE)
            .taux(UPDATED_TAUX)
            .numMaticule(UPDATED_NUM_MATICULE)
            .enbale(UPDATED_ENBALE)
            .categorie(UPDATED_CATEGORIE)
            .status(UPDATED_STATUS)
            .absolute(UPDATED_ABSOLUTE);
        CompteClientDTO compteClientDTO = compteClientMapper.toDto(updatedCompteClient);

        restCompteClientMockMvc.perform(put("/api/compte-clients").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(compteClientDTO)))
            .andExpect(status().isOk());

        // Validate the CompteClient in the database
        List<CompteClient> compteClientList = compteClientRepository.findAll();
        assertThat(compteClientList).hasSize(databaseSizeBeforeUpdate);
        CompteClient testCompteClient = compteClientList.get(compteClientList.size() - 1);
        assertThat(testCompteClient.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testCompteClient.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testCompteClient.getEncours()).isEqualTo(UPDATED_ENCOURS);
        assertThat(testCompteClient.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testCompteClient.getPlafondJournalier()).isEqualTo(UPDATED_PLAFOND_JOURNALIER);
        assertThat(testCompteClient.getPlafondMensuel()).isEqualTo(UPDATED_PLAFOND_MENSUEL);
        assertThat(testCompteClient.getConsommation()).isEqualTo(UPDATED_CONSOMMATION);
        assertThat(testCompteClient.getConsoJournaliere()).isEqualTo(UPDATED_CONSO_JOURNALIERE);
        assertThat(testCompteClient.getTaux()).isEqualTo(UPDATED_TAUX);
        assertThat(testCompteClient.getNumMaticule()).isEqualTo(UPDATED_NUM_MATICULE);
        assertThat(testCompteClient.isEnbale()).isEqualTo(UPDATED_ENBALE);
        assertThat(testCompteClient.getCategorie()).isEqualTo(UPDATED_CATEGORIE);
        assertThat(testCompteClient.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testCompteClient.isAbsolute()).isEqualTo(UPDATED_ABSOLUTE);
    }

    @Test
    @Transactional
    public void updateNonExistingCompteClient() throws Exception {
        int databaseSizeBeforeUpdate = compteClientRepository.findAll().size();

        // Create the CompteClient
        CompteClientDTO compteClientDTO = compteClientMapper.toDto(compteClient);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCompteClientMockMvc.perform(put("/api/compte-clients").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(compteClientDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CompteClient in the database
        List<CompteClient> compteClientList = compteClientRepository.findAll();
        assertThat(compteClientList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCompteClient() throws Exception {
        // Initialize the database
        compteClientRepository.saveAndFlush(compteClient);

        int databaseSizeBeforeDelete = compteClientRepository.findAll().size();

        // Delete the compteClient
        restCompteClientMockMvc.perform(delete("/api/compte-clients/{id}", compteClient.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CompteClient> compteClientList = compteClientRepository.findAll();
        assertThat(compteClientList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
