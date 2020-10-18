package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.NucleusApp;
import com.kobe.nucleus.domain.AyantDroit;
import com.kobe.nucleus.domain.Client;
import com.kobe.nucleus.repository.AyantDroitRepository;
import com.kobe.nucleus.service.AyantDroitService;
import com.kobe.nucleus.service.dto.AyantDroitDTO;
import com.kobe.nucleus.service.mapper.AyantDroitMapper;

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
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.kobe.nucleus.domain.enumeration.Status;
/**
 * Integration tests for the {@link AyantDroitResource} REST controller.
 */
@SpringBootTest(classes = NucleusApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class AyantDroitResourceIT {

    private static final LocalDate DEFAULT_CREATED_AT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_AT = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_UPDATED_AT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_AT = LocalDate.now(ZoneId.systemDefault());

    private static final Status DEFAULT_STATUS = Status.ACTIVE;
    private static final Status UPDATED_STATUS = Status.ENATTENTE;

    private static final String DEFAULT_NUM = "AAAAAAAAAA";
    private static final String UPDATED_NUM = "BBBBBBBBBB";

    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SEXE = "AAAAAAAAAA";
    private static final String UPDATED_SEXE = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_INTERNE = "AAAAAAAAAA";
    private static final String UPDATED_CODE_INTERNE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DAT_NAISS = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DAT_NAISS = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private AyantDroitRepository ayantDroitRepository;

    @Autowired
    private AyantDroitMapper ayantDroitMapper;

    @Autowired
    private AyantDroitService ayantDroitService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAyantDroitMockMvc;

    private AyantDroit ayantDroit;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AyantDroit createEntity(EntityManager em) {
        AyantDroit ayantDroit = new AyantDroit()
            .createdAt(DEFAULT_CREATED_AT)
            .updatedAt(DEFAULT_UPDATED_AT)
            .status(DEFAULT_STATUS)
            .num(DEFAULT_NUM)
            .firstName(DEFAULT_FIRST_NAME)
            .lastName(DEFAULT_LAST_NAME)
            .sexe(DEFAULT_SEXE)
            .codeInterne(DEFAULT_CODE_INTERNE)
            .datNaiss(DEFAULT_DAT_NAISS);
        // Add required entity
        Client client;
        if (TestUtil.findAll(em, Client.class).isEmpty()) {
            client = ClientResourceIT.createEntity(em);
            em.persist(client);
            em.flush();
        } else {
            client = TestUtil.findAll(em, Client.class).get(0);
        }
        ayantDroit.setAssure(client);
        return ayantDroit;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AyantDroit createUpdatedEntity(EntityManager em) {
        AyantDroit ayantDroit = new AyantDroit()
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .status(UPDATED_STATUS)
            .num(UPDATED_NUM)
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .sexe(UPDATED_SEXE)
            .codeInterne(UPDATED_CODE_INTERNE)
            .datNaiss(UPDATED_DAT_NAISS);
        // Add required entity
        Client client;
        if (TestUtil.findAll(em, Client.class).isEmpty()) {
            client = ClientResourceIT.createUpdatedEntity(em);
            em.persist(client);
            em.flush();
        } else {
            client = TestUtil.findAll(em, Client.class).get(0);
        }
        ayantDroit.setAssure(client);
        return ayantDroit;
    }

    @BeforeEach
    public void initTest() {
        ayantDroit = createEntity(em);
    }

    @Test
    @Transactional
    public void createAyantDroit() throws Exception {
        int databaseSizeBeforeCreate = ayantDroitRepository.findAll().size();
        // Create the AyantDroit
        AyantDroitDTO ayantDroitDTO = ayantDroitMapper.toDto(ayantDroit);
        restAyantDroitMockMvc.perform(post("/api/ayant-droits").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ayantDroitDTO)))
            .andExpect(status().isCreated());

        // Validate the AyantDroit in the database
        List<AyantDroit> ayantDroitList = ayantDroitRepository.findAll();
        assertThat(ayantDroitList).hasSize(databaseSizeBeforeCreate + 1);
        AyantDroit testAyantDroit = ayantDroitList.get(ayantDroitList.size() - 1);
        assertThat(testAyantDroit.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testAyantDroit.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testAyantDroit.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testAyantDroit.getNum()).isEqualTo(DEFAULT_NUM);
        assertThat(testAyantDroit.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testAyantDroit.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testAyantDroit.getSexe()).isEqualTo(DEFAULT_SEXE);
        assertThat(testAyantDroit.getCodeInterne()).isEqualTo(DEFAULT_CODE_INTERNE);
        assertThat(testAyantDroit.getDatNaiss()).isEqualTo(DEFAULT_DAT_NAISS);
    }

    @Test
    @Transactional
    public void createAyantDroitWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ayantDroitRepository.findAll().size();

        // Create the AyantDroit with an existing ID
        ayantDroit.setId(1L);
        AyantDroitDTO ayantDroitDTO = ayantDroitMapper.toDto(ayantDroit);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAyantDroitMockMvc.perform(post("/api/ayant-droits").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ayantDroitDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AyantDroit in the database
        List<AyantDroit> ayantDroitList = ayantDroitRepository.findAll();
        assertThat(ayantDroitList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCreatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = ayantDroitRepository.findAll().size();
        // set the field null
        ayantDroit.setCreatedAt(null);

        // Create the AyantDroit, which fails.
        AyantDroitDTO ayantDroitDTO = ayantDroitMapper.toDto(ayantDroit);


        restAyantDroitMockMvc.perform(post("/api/ayant-droits").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ayantDroitDTO)))
            .andExpect(status().isBadRequest());

        List<AyantDroit> ayantDroitList = ayantDroitRepository.findAll();
        assertThat(ayantDroitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUpdatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = ayantDroitRepository.findAll().size();
        // set the field null
        ayantDroit.setUpdatedAt(null);

        // Create the AyantDroit, which fails.
        AyantDroitDTO ayantDroitDTO = ayantDroitMapper.toDto(ayantDroit);


        restAyantDroitMockMvc.perform(post("/api/ayant-droits").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ayantDroitDTO)))
            .andExpect(status().isBadRequest());

        List<AyantDroit> ayantDroitList = ayantDroitRepository.findAll();
        assertThat(ayantDroitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = ayantDroitRepository.findAll().size();
        // set the field null
        ayantDroit.setStatus(null);

        // Create the AyantDroit, which fails.
        AyantDroitDTO ayantDroitDTO = ayantDroitMapper.toDto(ayantDroit);


        restAyantDroitMockMvc.perform(post("/api/ayant-droits").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ayantDroitDTO)))
            .andExpect(status().isBadRequest());

        List<AyantDroit> ayantDroitList = ayantDroitRepository.findAll();
        assertThat(ayantDroitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFirstNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = ayantDroitRepository.findAll().size();
        // set the field null
        ayantDroit.setFirstName(null);

        // Create the AyantDroit, which fails.
        AyantDroitDTO ayantDroitDTO = ayantDroitMapper.toDto(ayantDroit);


        restAyantDroitMockMvc.perform(post("/api/ayant-droits").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ayantDroitDTO)))
            .andExpect(status().isBadRequest());

        List<AyantDroit> ayantDroitList = ayantDroitRepository.findAll();
        assertThat(ayantDroitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCodeInterneIsRequired() throws Exception {
        int databaseSizeBeforeTest = ayantDroitRepository.findAll().size();
        // set the field null
        ayantDroit.setCodeInterne(null);

        // Create the AyantDroit, which fails.
        AyantDroitDTO ayantDroitDTO = ayantDroitMapper.toDto(ayantDroit);


        restAyantDroitMockMvc.perform(post("/api/ayant-droits").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ayantDroitDTO)))
            .andExpect(status().isBadRequest());

        List<AyantDroit> ayantDroitList = ayantDroitRepository.findAll();
        assertThat(ayantDroitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAyantDroits() throws Exception {
        // Initialize the database
        ayantDroitRepository.saveAndFlush(ayantDroit);

        // Get all the ayantDroitList
        restAyantDroitMockMvc.perform(get("/api/ayant-droits?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ayantDroit.getId().intValue())))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(DEFAULT_UPDATED_AT.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].num").value(hasItem(DEFAULT_NUM)))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].sexe").value(hasItem(DEFAULT_SEXE)))
            .andExpect(jsonPath("$.[*].codeInterne").value(hasItem(DEFAULT_CODE_INTERNE)))
            .andExpect(jsonPath("$.[*].datNaiss").value(hasItem(DEFAULT_DAT_NAISS.toString())));
    }
    
    @Test
    @Transactional
    public void getAyantDroit() throws Exception {
        // Initialize the database
        ayantDroitRepository.saveAndFlush(ayantDroit);

        // Get the ayantDroit
        restAyantDroitMockMvc.perform(get("/api/ayant-droits/{id}", ayantDroit.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(ayantDroit.getId().intValue()))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.updatedAt").value(DEFAULT_UPDATED_AT.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.num").value(DEFAULT_NUM))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME))
            .andExpect(jsonPath("$.sexe").value(DEFAULT_SEXE))
            .andExpect(jsonPath("$.codeInterne").value(DEFAULT_CODE_INTERNE))
            .andExpect(jsonPath("$.datNaiss").value(DEFAULT_DAT_NAISS.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingAyantDroit() throws Exception {
        // Get the ayantDroit
        restAyantDroitMockMvc.perform(get("/api/ayant-droits/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAyantDroit() throws Exception {
        // Initialize the database
        ayantDroitRepository.saveAndFlush(ayantDroit);

        int databaseSizeBeforeUpdate = ayantDroitRepository.findAll().size();

        // Update the ayantDroit
        AyantDroit updatedAyantDroit = ayantDroitRepository.findById(ayantDroit.getId()).get();
        // Disconnect from session so that the updates on updatedAyantDroit are not directly saved in db
        em.detach(updatedAyantDroit);
        updatedAyantDroit
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .status(UPDATED_STATUS)
            .num(UPDATED_NUM)
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .sexe(UPDATED_SEXE)
            .codeInterne(UPDATED_CODE_INTERNE)
            .datNaiss(UPDATED_DAT_NAISS);
        AyantDroitDTO ayantDroitDTO = ayantDroitMapper.toDto(updatedAyantDroit);

        restAyantDroitMockMvc.perform(put("/api/ayant-droits").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ayantDroitDTO)))
            .andExpect(status().isOk());

        // Validate the AyantDroit in the database
        List<AyantDroit> ayantDroitList = ayantDroitRepository.findAll();
        assertThat(ayantDroitList).hasSize(databaseSizeBeforeUpdate);
        AyantDroit testAyantDroit = ayantDroitList.get(ayantDroitList.size() - 1);
        assertThat(testAyantDroit.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testAyantDroit.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testAyantDroit.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testAyantDroit.getNum()).isEqualTo(UPDATED_NUM);
        assertThat(testAyantDroit.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testAyantDroit.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testAyantDroit.getSexe()).isEqualTo(UPDATED_SEXE);
        assertThat(testAyantDroit.getCodeInterne()).isEqualTo(UPDATED_CODE_INTERNE);
        assertThat(testAyantDroit.getDatNaiss()).isEqualTo(UPDATED_DAT_NAISS);
    }

    @Test
    @Transactional
    public void updateNonExistingAyantDroit() throws Exception {
        int databaseSizeBeforeUpdate = ayantDroitRepository.findAll().size();

        // Create the AyantDroit
        AyantDroitDTO ayantDroitDTO = ayantDroitMapper.toDto(ayantDroit);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAyantDroitMockMvc.perform(put("/api/ayant-droits").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ayantDroitDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AyantDroit in the database
        List<AyantDroit> ayantDroitList = ayantDroitRepository.findAll();
        assertThat(ayantDroitList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAyantDroit() throws Exception {
        // Initialize the database
        ayantDroitRepository.saveAndFlush(ayantDroit);

        int databaseSizeBeforeDelete = ayantDroitRepository.findAll().size();

        // Delete the ayantDroit
        restAyantDroitMockMvc.perform(delete("/api/ayant-droits/{id}", ayantDroit.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AyantDroit> ayantDroitList = ayantDroitRepository.findAll();
        assertThat(ayantDroitList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
