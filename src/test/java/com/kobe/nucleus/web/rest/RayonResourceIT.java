package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.NucleusApp;
import com.kobe.nucleus.domain.Rayon;
import com.kobe.nucleus.repository.RayonRepository;
import com.kobe.nucleus.service.RayonService;
import com.kobe.nucleus.service.dto.RayonDTO;
import com.kobe.nucleus.service.mapper.RayonMapper;

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
 * Integration tests for the {@link RayonResource} REST controller.
 */
@SpringBootTest(classes = NucleusApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class RayonResourceIT {

    private static final Instant DEFAULT_CREATED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_UPDATED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Status DEFAULT_STATUS = Status.ACTIVE;
    private static final Status UPDATED_STATUS = Status.ENATTENTE;

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    @Autowired
    private RayonRepository rayonRepository;

    @Autowired
    private RayonMapper rayonMapper;

    @Autowired
    private RayonService rayonService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRayonMockMvc;

    private Rayon rayon;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Rayon createEntity(EntityManager em) {
        Rayon rayon = new Rayon()
            .createdAt(DEFAULT_CREATED_AT)
            .updatedAt(DEFAULT_UPDATED_AT)
            .status(DEFAULT_STATUS)
            .code(DEFAULT_CODE)
            .libelle(DEFAULT_LIBELLE);
        return rayon;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Rayon createUpdatedEntity(EntityManager em) {
        Rayon rayon = new Rayon()
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .status(UPDATED_STATUS)
            .code(UPDATED_CODE)
            .libelle(UPDATED_LIBELLE);
        return rayon;
    }

    @BeforeEach
    public void initTest() {
        rayon = createEntity(em);
    }

    @Test
    @Transactional
    public void createRayon() throws Exception {
        int databaseSizeBeforeCreate = rayonRepository.findAll().size();
        // Create the Rayon
        RayonDTO rayonDTO = rayonMapper.toDto(rayon);
        restRayonMockMvc.perform(post("/api/rayons").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(rayonDTO)))
            .andExpect(status().isCreated());

        // Validate the Rayon in the database
        List<Rayon> rayonList = rayonRepository.findAll();
        assertThat(rayonList).hasSize(databaseSizeBeforeCreate + 1);
        Rayon testRayon = rayonList.get(rayonList.size() - 1);
        assertThat(testRayon.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testRayon.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testRayon.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testRayon.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testRayon.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
    }

    @Test
    @Transactional
    public void createRayonWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = rayonRepository.findAll().size();

        // Create the Rayon with an existing ID
        rayon.setId(1L);
        RayonDTO rayonDTO = rayonMapper.toDto(rayon);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRayonMockMvc.perform(post("/api/rayons").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(rayonDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Rayon in the database
        List<Rayon> rayonList = rayonRepository.findAll();
        assertThat(rayonList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = rayonRepository.findAll().size();
        // set the field null
        rayon.setStatus(null);

        // Create the Rayon, which fails.
        RayonDTO rayonDTO = rayonMapper.toDto(rayon);


        restRayonMockMvc.perform(post("/api/rayons").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(rayonDTO)))
            .andExpect(status().isBadRequest());

        List<Rayon> rayonList = rayonRepository.findAll();
        assertThat(rayonList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = rayonRepository.findAll().size();
        // set the field null
        rayon.setCode(null);

        // Create the Rayon, which fails.
        RayonDTO rayonDTO = rayonMapper.toDto(rayon);


        restRayonMockMvc.perform(post("/api/rayons").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(rayonDTO)))
            .andExpect(status().isBadRequest());

        List<Rayon> rayonList = rayonRepository.findAll();
        assertThat(rayonList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLibelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = rayonRepository.findAll().size();
        // set the field null
        rayon.setLibelle(null);

        // Create the Rayon, which fails.
        RayonDTO rayonDTO = rayonMapper.toDto(rayon);


        restRayonMockMvc.perform(post("/api/rayons").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(rayonDTO)))
            .andExpect(status().isBadRequest());

        List<Rayon> rayonList = rayonRepository.findAll();
        assertThat(rayonList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRayons() throws Exception {
        // Initialize the database
        rayonRepository.saveAndFlush(rayon);

        // Get all the rayonList
        restRayonMockMvc.perform(get("/api/rayons?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(rayon.getId().intValue())))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(DEFAULT_UPDATED_AT.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)));
    }
    
    @Test
    @Transactional
    public void getRayon() throws Exception {
        // Initialize the database
        rayonRepository.saveAndFlush(rayon);

        // Get the rayon
        restRayonMockMvc.perform(get("/api/rayons/{id}", rayon.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(rayon.getId().intValue()))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.updatedAt").value(DEFAULT_UPDATED_AT.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE));
    }
    @Test
    @Transactional
    public void getNonExistingRayon() throws Exception {
        // Get the rayon
        restRayonMockMvc.perform(get("/api/rayons/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRayon() throws Exception {
        // Initialize the database
        rayonRepository.saveAndFlush(rayon);

        int databaseSizeBeforeUpdate = rayonRepository.findAll().size();

        // Update the rayon
        Rayon updatedRayon = rayonRepository.findById(rayon.getId()).get();
        // Disconnect from session so that the updates on updatedRayon are not directly saved in db
        em.detach(updatedRayon);
        updatedRayon
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .status(UPDATED_STATUS)
            .code(UPDATED_CODE)
            .libelle(UPDATED_LIBELLE);
        RayonDTO rayonDTO = rayonMapper.toDto(updatedRayon);

        restRayonMockMvc.perform(put("/api/rayons").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(rayonDTO)))
            .andExpect(status().isOk());

        // Validate the Rayon in the database
        List<Rayon> rayonList = rayonRepository.findAll();
        assertThat(rayonList).hasSize(databaseSizeBeforeUpdate);
        Rayon testRayon = rayonList.get(rayonList.size() - 1);
        assertThat(testRayon.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testRayon.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testRayon.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testRayon.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testRayon.getLibelle()).isEqualTo(UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void updateNonExistingRayon() throws Exception {
        int databaseSizeBeforeUpdate = rayonRepository.findAll().size();

        // Create the Rayon
        RayonDTO rayonDTO = rayonMapper.toDto(rayon);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRayonMockMvc.perform(put("/api/rayons").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(rayonDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Rayon in the database
        List<Rayon> rayonList = rayonRepository.findAll();
        assertThat(rayonList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRayon() throws Exception {
        // Initialize the database
        rayonRepository.saveAndFlush(rayon);

        int databaseSizeBeforeDelete = rayonRepository.findAll().size();

        // Delete the rayon
        restRayonMockMvc.perform(delete("/api/rayons/{id}", rayon.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Rayon> rayonList = rayonRepository.findAll();
        assertThat(rayonList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
