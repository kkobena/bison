package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.NucleusApp;
import com.kobe.nucleus.domain.Fabriquant;
import com.kobe.nucleus.repository.FabriquantRepository;
import com.kobe.nucleus.service.FabriquantService;
import com.kobe.nucleus.service.dto.FabriquantDTO;
import com.kobe.nucleus.service.mapper.FabriquantMapper;

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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.kobe.nucleus.domain.enumeration.Status;
/**
 * Integration tests for the {@link FabriquantResource} REST controller.
 */
@SpringBootTest(classes = NucleusApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class FabriquantResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    private static final Status DEFAULT_STATUS = Status.ACTIVE;
    private static final Status UPDATED_STATUS = Status.ENATTENTE;

    @Autowired
    private FabriquantRepository fabriquantRepository;

    @Autowired
    private FabriquantMapper fabriquantMapper;

    @Autowired
    private FabriquantService fabriquantService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFabriquantMockMvc;

    private Fabriquant fabriquant;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Fabriquant createEntity(EntityManager em) {
        Fabriquant fabriquant = new Fabriquant()
            .code(DEFAULT_CODE)
            .libelle(DEFAULT_LIBELLE)
            .status(DEFAULT_STATUS);
        return fabriquant;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Fabriquant createUpdatedEntity(EntityManager em) {
        Fabriquant fabriquant = new Fabriquant()
            .code(UPDATED_CODE)
            .libelle(UPDATED_LIBELLE)
            .status(UPDATED_STATUS);
        return fabriquant;
    }

    @BeforeEach
    public void initTest() {
        fabriquant = createEntity(em);
    }

    @Test
    @Transactional
    public void createFabriquant() throws Exception {
        int databaseSizeBeforeCreate = fabriquantRepository.findAll().size();
        // Create the Fabriquant
        FabriquantDTO fabriquantDTO = fabriquantMapper.toDto(fabriquant);
        restFabriquantMockMvc.perform(post("/api/fabriquants").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fabriquantDTO)))
            .andExpect(status().isCreated());

        // Validate the Fabriquant in the database
        List<Fabriquant> fabriquantList = fabriquantRepository.findAll();
        assertThat(fabriquantList).hasSize(databaseSizeBeforeCreate + 1);
        Fabriquant testFabriquant = fabriquantList.get(fabriquantList.size() - 1);
        assertThat(testFabriquant.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testFabriquant.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
        assertThat(testFabriquant.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createFabriquantWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = fabriquantRepository.findAll().size();

        // Create the Fabriquant with an existing ID
        fabriquant.setId(1L);
        FabriquantDTO fabriquantDTO = fabriquantMapper.toDto(fabriquant);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFabriquantMockMvc.perform(post("/api/fabriquants").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fabriquantDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Fabriquant in the database
        List<Fabriquant> fabriquantList = fabriquantRepository.findAll();
        assertThat(fabriquantList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = fabriquantRepository.findAll().size();
        // set the field null
        fabriquant.setCode(null);

        // Create the Fabriquant, which fails.
        FabriquantDTO fabriquantDTO = fabriquantMapper.toDto(fabriquant);


        restFabriquantMockMvc.perform(post("/api/fabriquants").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fabriquantDTO)))
            .andExpect(status().isBadRequest());

        List<Fabriquant> fabriquantList = fabriquantRepository.findAll();
        assertThat(fabriquantList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLibelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = fabriquantRepository.findAll().size();
        // set the field null
        fabriquant.setLibelle(null);

        // Create the Fabriquant, which fails.
        FabriquantDTO fabriquantDTO = fabriquantMapper.toDto(fabriquant);


        restFabriquantMockMvc.perform(post("/api/fabriquants").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fabriquantDTO)))
            .andExpect(status().isBadRequest());

        List<Fabriquant> fabriquantList = fabriquantRepository.findAll();
        assertThat(fabriquantList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = fabriquantRepository.findAll().size();
        // set the field null
        fabriquant.setStatus(null);

        // Create the Fabriquant, which fails.
        FabriquantDTO fabriquantDTO = fabriquantMapper.toDto(fabriquant);


        restFabriquantMockMvc.perform(post("/api/fabriquants").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fabriquantDTO)))
            .andExpect(status().isBadRequest());

        List<Fabriquant> fabriquantList = fabriquantRepository.findAll();
        assertThat(fabriquantList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFabriquants() throws Exception {
        // Initialize the database
        fabriquantRepository.saveAndFlush(fabriquant);

        // Get all the fabriquantList
        restFabriquantMockMvc.perform(get("/api/fabriquants?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fabriquant.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }
    
    @Test
    @Transactional
    public void getFabriquant() throws Exception {
        // Initialize the database
        fabriquantRepository.saveAndFlush(fabriquant);

        // Get the fabriquant
        restFabriquantMockMvc.perform(get("/api/fabriquants/{id}", fabriquant.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(fabriquant.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingFabriquant() throws Exception {
        // Get the fabriquant
        restFabriquantMockMvc.perform(get("/api/fabriquants/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFabriquant() throws Exception {
        // Initialize the database
        fabriquantRepository.saveAndFlush(fabriquant);

        int databaseSizeBeforeUpdate = fabriquantRepository.findAll().size();

        // Update the fabriquant
        Fabriquant updatedFabriquant = fabriquantRepository.findById(fabriquant.getId()).get();
        // Disconnect from session so that the updates on updatedFabriquant are not directly saved in db
        em.detach(updatedFabriquant);
        updatedFabriquant
            .code(UPDATED_CODE)
            .libelle(UPDATED_LIBELLE)
            .status(UPDATED_STATUS);
        FabriquantDTO fabriquantDTO = fabriquantMapper.toDto(updatedFabriquant);

        restFabriquantMockMvc.perform(put("/api/fabriquants").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fabriquantDTO)))
            .andExpect(status().isOk());

        // Validate the Fabriquant in the database
        List<Fabriquant> fabriquantList = fabriquantRepository.findAll();
        assertThat(fabriquantList).hasSize(databaseSizeBeforeUpdate);
        Fabriquant testFabriquant = fabriquantList.get(fabriquantList.size() - 1);
        assertThat(testFabriquant.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testFabriquant.getLibelle()).isEqualTo(UPDATED_LIBELLE);
        assertThat(testFabriquant.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingFabriquant() throws Exception {
        int databaseSizeBeforeUpdate = fabriquantRepository.findAll().size();

        // Create the Fabriquant
        FabriquantDTO fabriquantDTO = fabriquantMapper.toDto(fabriquant);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFabriquantMockMvc.perform(put("/api/fabriquants").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fabriquantDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Fabriquant in the database
        List<Fabriquant> fabriquantList = fabriquantRepository.findAll();
        assertThat(fabriquantList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFabriquant() throws Exception {
        // Initialize the database
        fabriquantRepository.saveAndFlush(fabriquant);

        int databaseSizeBeforeDelete = fabriquantRepository.findAll().size();

        // Delete the fabriquant
        restFabriquantMockMvc.perform(delete("/api/fabriquants/{id}", fabriquant.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Fabriquant> fabriquantList = fabriquantRepository.findAll();
        assertThat(fabriquantList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
