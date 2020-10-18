package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.NucleusApp;
import com.kobe.nucleus.domain.Remise;
import com.kobe.nucleus.repository.RemiseRepository;
import com.kobe.nucleus.service.RemiseService;
import com.kobe.nucleus.service.dto.RemiseDTO;
import com.kobe.nucleus.service.mapper.RemiseMapper;

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
 * Integration tests for the {@link RemiseResource} REST controller.
 */
@SpringBootTest(classes = NucleusApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class RemiseResourceIT {

    private static final String DEFAULT_VALEUR = "AAAAAAAAAA";
    private static final String UPDATED_VALEUR = "BBBBBBBBBB";

    private static final Float DEFAULT_REMISE_VALUE = 1F;
    private static final Float UPDATED_REMISE_VALUE = 2F;

    private static final Status DEFAULT_STATUS = Status.ACTIVE;
    private static final Status UPDATED_STATUS = Status.ENATTENTE;

    @Autowired
    private RemiseRepository remiseRepository;

    @Autowired
    private RemiseMapper remiseMapper;

    @Autowired
    private RemiseService remiseService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRemiseMockMvc;

    private Remise remise;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Remise createEntity(EntityManager em) {
        Remise remise = new Remise()
            .valeur(DEFAULT_VALEUR)
            .remiseValue(DEFAULT_REMISE_VALUE)
            .status(DEFAULT_STATUS);
        return remise;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Remise createUpdatedEntity(EntityManager em) {
        Remise remise = new Remise()
            .valeur(UPDATED_VALEUR)
            .remiseValue(UPDATED_REMISE_VALUE)
            .status(UPDATED_STATUS);
        return remise;
    }

    @BeforeEach
    public void initTest() {
        remise = createEntity(em);
    }

    @Test
    @Transactional
    public void createRemise() throws Exception {
        int databaseSizeBeforeCreate = remiseRepository.findAll().size();
        // Create the Remise
        RemiseDTO remiseDTO = remiseMapper.toDto(remise);
        restRemiseMockMvc.perform(post("/api/remises").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(remiseDTO)))
            .andExpect(status().isCreated());

        // Validate the Remise in the database
        List<Remise> remiseList = remiseRepository.findAll();
        assertThat(remiseList).hasSize(databaseSizeBeforeCreate + 1);
        Remise testRemise = remiseList.get(remiseList.size() - 1);
        assertThat(testRemise.getValeur()).isEqualTo(DEFAULT_VALEUR);
        assertThat(testRemise.getRemiseValue()).isEqualTo(DEFAULT_REMISE_VALUE);
        assertThat(testRemise.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createRemiseWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = remiseRepository.findAll().size();

        // Create the Remise with an existing ID
        remise.setId(1L);
        RemiseDTO remiseDTO = remiseMapper.toDto(remise);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRemiseMockMvc.perform(post("/api/remises").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(remiseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Remise in the database
        List<Remise> remiseList = remiseRepository.findAll();
        assertThat(remiseList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkRemiseValueIsRequired() throws Exception {
        int databaseSizeBeforeTest = remiseRepository.findAll().size();
        // set the field null
        remise.setRemiseValue(null);

        // Create the Remise, which fails.
        RemiseDTO remiseDTO = remiseMapper.toDto(remise);


        restRemiseMockMvc.perform(post("/api/remises").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(remiseDTO)))
            .andExpect(status().isBadRequest());

        List<Remise> remiseList = remiseRepository.findAll();
        assertThat(remiseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = remiseRepository.findAll().size();
        // set the field null
        remise.setStatus(null);

        // Create the Remise, which fails.
        RemiseDTO remiseDTO = remiseMapper.toDto(remise);


        restRemiseMockMvc.perform(post("/api/remises").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(remiseDTO)))
            .andExpect(status().isBadRequest());

        List<Remise> remiseList = remiseRepository.findAll();
        assertThat(remiseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRemises() throws Exception {
        // Initialize the database
        remiseRepository.saveAndFlush(remise);

        // Get all the remiseList
        restRemiseMockMvc.perform(get("/api/remises?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(remise.getId().intValue())))
            .andExpect(jsonPath("$.[*].valeur").value(hasItem(DEFAULT_VALEUR)))
            .andExpect(jsonPath("$.[*].remiseValue").value(hasItem(DEFAULT_REMISE_VALUE.doubleValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }
    
    @Test
    @Transactional
    public void getRemise() throws Exception {
        // Initialize the database
        remiseRepository.saveAndFlush(remise);

        // Get the remise
        restRemiseMockMvc.perform(get("/api/remises/{id}", remise.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(remise.getId().intValue()))
            .andExpect(jsonPath("$.valeur").value(DEFAULT_VALEUR))
            .andExpect(jsonPath("$.remiseValue").value(DEFAULT_REMISE_VALUE.doubleValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingRemise() throws Exception {
        // Get the remise
        restRemiseMockMvc.perform(get("/api/remises/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRemise() throws Exception {
        // Initialize the database
        remiseRepository.saveAndFlush(remise);

        int databaseSizeBeforeUpdate = remiseRepository.findAll().size();

        // Update the remise
        Remise updatedRemise = remiseRepository.findById(remise.getId()).get();
        // Disconnect from session so that the updates on updatedRemise are not directly saved in db
        em.detach(updatedRemise);
        updatedRemise
            .valeur(UPDATED_VALEUR)
            .remiseValue(UPDATED_REMISE_VALUE)
            .status(UPDATED_STATUS);
        RemiseDTO remiseDTO = remiseMapper.toDto(updatedRemise);

        restRemiseMockMvc.perform(put("/api/remises").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(remiseDTO)))
            .andExpect(status().isOk());

        // Validate the Remise in the database
        List<Remise> remiseList = remiseRepository.findAll();
        assertThat(remiseList).hasSize(databaseSizeBeforeUpdate);
        Remise testRemise = remiseList.get(remiseList.size() - 1);
        assertThat(testRemise.getValeur()).isEqualTo(UPDATED_VALEUR);
        assertThat(testRemise.getRemiseValue()).isEqualTo(UPDATED_REMISE_VALUE);
        assertThat(testRemise.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingRemise() throws Exception {
        int databaseSizeBeforeUpdate = remiseRepository.findAll().size();

        // Create the Remise
        RemiseDTO remiseDTO = remiseMapper.toDto(remise);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRemiseMockMvc.perform(put("/api/remises").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(remiseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Remise in the database
        List<Remise> remiseList = remiseRepository.findAll();
        assertThat(remiseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRemise() throws Exception {
        // Initialize the database
        remiseRepository.saveAndFlush(remise);

        int databaseSizeBeforeDelete = remiseRepository.findAll().size();

        // Delete the remise
        restRemiseMockMvc.perform(delete("/api/remises/{id}", remise.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Remise> remiseList = remiseRepository.findAll();
        assertThat(remiseList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
