package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.NucleusApp;
import com.kobe.nucleus.domain.Ajustement;
import com.kobe.nucleus.repository.AjustementRepository;
import com.kobe.nucleus.service.AjustementService;
import com.kobe.nucleus.service.dto.AjustementDTO;
import com.kobe.nucleus.service.mapper.AjustementMapper;

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
 * Integration tests for the {@link AjustementResource} REST controller.
 */
@SpringBootTest(classes = NucleusApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class AjustementResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_UPDATED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final LocalDate DEFAULT_END_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_END_DATE = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private AjustementRepository ajustementRepository;

    @Autowired
    private AjustementMapper ajustementMapper;

    @Autowired
    private AjustementService ajustementService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAjustementMockMvc;

    private Ajustement ajustement;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Ajustement createEntity(EntityManager em) {
        Ajustement ajustement = new Ajustement()
            .libelle(DEFAULT_LIBELLE)
            .createdAt(DEFAULT_CREATED_AT)
            .updatedAt(DEFAULT_UPDATED_AT)
            .endDate(DEFAULT_END_DATE);
        return ajustement;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Ajustement createUpdatedEntity(EntityManager em) {
        Ajustement ajustement = new Ajustement()
            .libelle(UPDATED_LIBELLE)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .endDate(UPDATED_END_DATE);
        return ajustement;
    }

    @BeforeEach
    public void initTest() {
        ajustement = createEntity(em);
    }

    @Test
    @Transactional
    public void createAjustement() throws Exception {
        int databaseSizeBeforeCreate = ajustementRepository.findAll().size();
        // Create the Ajustement
        AjustementDTO ajustementDTO = ajustementMapper.toDto(ajustement);
        restAjustementMockMvc.perform(post("/api/ajustements").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ajustementDTO)))
            .andExpect(status().isCreated());

        // Validate the Ajustement in the database
        List<Ajustement> ajustementList = ajustementRepository.findAll();
        assertThat(ajustementList).hasSize(databaseSizeBeforeCreate + 1);
        Ajustement testAjustement = ajustementList.get(ajustementList.size() - 1);
        assertThat(testAjustement.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
        assertThat(testAjustement.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testAjustement.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testAjustement.getEndDate()).isEqualTo(DEFAULT_END_DATE);
    }

    @Test
    @Transactional
    public void createAjustementWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ajustementRepository.findAll().size();

        // Create the Ajustement with an existing ID
        ajustement.setId(1L);
        AjustementDTO ajustementDTO = ajustementMapper.toDto(ajustement);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAjustementMockMvc.perform(post("/api/ajustements").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ajustementDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Ajustement in the database
        List<Ajustement> ajustementList = ajustementRepository.findAll();
        assertThat(ajustementList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLibelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = ajustementRepository.findAll().size();
        // set the field null
        ajustement.setLibelle(null);

        // Create the Ajustement, which fails.
        AjustementDTO ajustementDTO = ajustementMapper.toDto(ajustement);


        restAjustementMockMvc.perform(post("/api/ajustements").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ajustementDTO)))
            .andExpect(status().isBadRequest());

        List<Ajustement> ajustementList = ajustementRepository.findAll();
        assertThat(ajustementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = ajustementRepository.findAll().size();
        // set the field null
        ajustement.setCreatedAt(null);

        // Create the Ajustement, which fails.
        AjustementDTO ajustementDTO = ajustementMapper.toDto(ajustement);


        restAjustementMockMvc.perform(post("/api/ajustements").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ajustementDTO)))
            .andExpect(status().isBadRequest());

        List<Ajustement> ajustementList = ajustementRepository.findAll();
        assertThat(ajustementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUpdatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = ajustementRepository.findAll().size();
        // set the field null
        ajustement.setUpdatedAt(null);

        // Create the Ajustement, which fails.
        AjustementDTO ajustementDTO = ajustementMapper.toDto(ajustement);


        restAjustementMockMvc.perform(post("/api/ajustements").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ajustementDTO)))
            .andExpect(status().isBadRequest());

        List<Ajustement> ajustementList = ajustementRepository.findAll();
        assertThat(ajustementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEndDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = ajustementRepository.findAll().size();
        // set the field null
        ajustement.setEndDate(null);

        // Create the Ajustement, which fails.
        AjustementDTO ajustementDTO = ajustementMapper.toDto(ajustement);


        restAjustementMockMvc.perform(post("/api/ajustements").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ajustementDTO)))
            .andExpect(status().isBadRequest());

        List<Ajustement> ajustementList = ajustementRepository.findAll();
        assertThat(ajustementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAjustements() throws Exception {
        // Initialize the database
        ajustementRepository.saveAndFlush(ajustement);

        // Get all the ajustementList
        restAjustementMockMvc.perform(get("/api/ajustements?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ajustement.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(DEFAULT_UPDATED_AT.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())));
    }
    
    @Test
    @Transactional
    public void getAjustement() throws Exception {
        // Initialize the database
        ajustementRepository.saveAndFlush(ajustement);

        // Get the ajustement
        restAjustementMockMvc.perform(get("/api/ajustements/{id}", ajustement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(ajustement.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.updatedAt").value(DEFAULT_UPDATED_AT.toString()))
            .andExpect(jsonPath("$.endDate").value(DEFAULT_END_DATE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingAjustement() throws Exception {
        // Get the ajustement
        restAjustementMockMvc.perform(get("/api/ajustements/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAjustement() throws Exception {
        // Initialize the database
        ajustementRepository.saveAndFlush(ajustement);

        int databaseSizeBeforeUpdate = ajustementRepository.findAll().size();

        // Update the ajustement
        Ajustement updatedAjustement = ajustementRepository.findById(ajustement.getId()).get();
        // Disconnect from session so that the updates on updatedAjustement are not directly saved in db
        em.detach(updatedAjustement);
        updatedAjustement
            .libelle(UPDATED_LIBELLE)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .endDate(UPDATED_END_DATE);
        AjustementDTO ajustementDTO = ajustementMapper.toDto(updatedAjustement);

        restAjustementMockMvc.perform(put("/api/ajustements").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ajustementDTO)))
            .andExpect(status().isOk());

        // Validate the Ajustement in the database
        List<Ajustement> ajustementList = ajustementRepository.findAll();
        assertThat(ajustementList).hasSize(databaseSizeBeforeUpdate);
        Ajustement testAjustement = ajustementList.get(ajustementList.size() - 1);
        assertThat(testAjustement.getLibelle()).isEqualTo(UPDATED_LIBELLE);
        assertThat(testAjustement.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testAjustement.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testAjustement.getEndDate()).isEqualTo(UPDATED_END_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingAjustement() throws Exception {
        int databaseSizeBeforeUpdate = ajustementRepository.findAll().size();

        // Create the Ajustement
        AjustementDTO ajustementDTO = ajustementMapper.toDto(ajustement);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAjustementMockMvc.perform(put("/api/ajustements").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ajustementDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Ajustement in the database
        List<Ajustement> ajustementList = ajustementRepository.findAll();
        assertThat(ajustementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAjustement() throws Exception {
        // Initialize the database
        ajustementRepository.saveAndFlush(ajustement);

        int databaseSizeBeforeDelete = ajustementRepository.findAll().size();

        // Delete the ajustement
        restAjustementMockMvc.perform(delete("/api/ajustements/{id}", ajustement.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Ajustement> ajustementList = ajustementRepository.findAll();
        assertThat(ajustementList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
