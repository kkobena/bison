package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.NucleusApp;
import com.kobe.nucleus.domain.FamilleProduit;
import com.kobe.nucleus.repository.FamilleProduitRepository;
import com.kobe.nucleus.service.FamilleProduitService;
import com.kobe.nucleus.service.dto.FamilleProduitDTO;
import com.kobe.nucleus.service.mapper.FamilleProduitMapper;

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
 * Integration tests for the {@link FamilleProduitResource} REST controller.
 */
@SpringBootTest(classes = NucleusApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class FamilleProduitResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    private static final Status DEFAULT_STATUS = Status.ACTIVE;
    private static final Status UPDATED_STATUS = Status.ENATTENTE;

    @Autowired
    private FamilleProduitRepository familleProduitRepository;

    @Autowired
    private FamilleProduitMapper familleProduitMapper;

    @Autowired
    private FamilleProduitService familleProduitService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFamilleProduitMockMvc;

    private FamilleProduit familleProduit;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FamilleProduit createEntity(EntityManager em) {
        FamilleProduit familleProduit = new FamilleProduit()
            .code(DEFAULT_CODE)
            .libelle(DEFAULT_LIBELLE)
            .status(DEFAULT_STATUS);
        return familleProduit;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FamilleProduit createUpdatedEntity(EntityManager em) {
        FamilleProduit familleProduit = new FamilleProduit()
            .code(UPDATED_CODE)
            .libelle(UPDATED_LIBELLE)
            .status(UPDATED_STATUS);
        return familleProduit;
    }

    @BeforeEach
    public void initTest() {
        familleProduit = createEntity(em);
    }

    @Test
    @Transactional
    public void createFamilleProduit() throws Exception {
        int databaseSizeBeforeCreate = familleProduitRepository.findAll().size();
        // Create the FamilleProduit
        FamilleProduitDTO familleProduitDTO = familleProduitMapper.toDto(familleProduit);
        restFamilleProduitMockMvc.perform(post("/api/famille-produits").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(familleProduitDTO)))
            .andExpect(status().isCreated());

        // Validate the FamilleProduit in the database
        List<FamilleProduit> familleProduitList = familleProduitRepository.findAll();
        assertThat(familleProduitList).hasSize(databaseSizeBeforeCreate + 1);
        FamilleProduit testFamilleProduit = familleProduitList.get(familleProduitList.size() - 1);
        assertThat(testFamilleProduit.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testFamilleProduit.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
        assertThat(testFamilleProduit.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createFamilleProduitWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = familleProduitRepository.findAll().size();

        // Create the FamilleProduit with an existing ID
        familleProduit.setId(1L);
        FamilleProduitDTO familleProduitDTO = familleProduitMapper.toDto(familleProduit);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFamilleProduitMockMvc.perform(post("/api/famille-produits").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(familleProduitDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FamilleProduit in the database
        List<FamilleProduit> familleProduitList = familleProduitRepository.findAll();
        assertThat(familleProduitList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = familleProduitRepository.findAll().size();
        // set the field null
        familleProduit.setCode(null);

        // Create the FamilleProduit, which fails.
        FamilleProduitDTO familleProduitDTO = familleProduitMapper.toDto(familleProduit);


        restFamilleProduitMockMvc.perform(post("/api/famille-produits").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(familleProduitDTO)))
            .andExpect(status().isBadRequest());

        List<FamilleProduit> familleProduitList = familleProduitRepository.findAll();
        assertThat(familleProduitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLibelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = familleProduitRepository.findAll().size();
        // set the field null
        familleProduit.setLibelle(null);

        // Create the FamilleProduit, which fails.
        FamilleProduitDTO familleProduitDTO = familleProduitMapper.toDto(familleProduit);


        restFamilleProduitMockMvc.perform(post("/api/famille-produits").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(familleProduitDTO)))
            .andExpect(status().isBadRequest());

        List<FamilleProduit> familleProduitList = familleProduitRepository.findAll();
        assertThat(familleProduitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = familleProduitRepository.findAll().size();
        // set the field null
        familleProduit.setStatus(null);

        // Create the FamilleProduit, which fails.
        FamilleProduitDTO familleProduitDTO = familleProduitMapper.toDto(familleProduit);


        restFamilleProduitMockMvc.perform(post("/api/famille-produits").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(familleProduitDTO)))
            .andExpect(status().isBadRequest());

        List<FamilleProduit> familleProduitList = familleProduitRepository.findAll();
        assertThat(familleProduitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFamilleProduits() throws Exception {
        // Initialize the database
        familleProduitRepository.saveAndFlush(familleProduit);

        // Get all the familleProduitList
        restFamilleProduitMockMvc.perform(get("/api/famille-produits?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(familleProduit.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }
    
    @Test
    @Transactional
    public void getFamilleProduit() throws Exception {
        // Initialize the database
        familleProduitRepository.saveAndFlush(familleProduit);

        // Get the familleProduit
        restFamilleProduitMockMvc.perform(get("/api/famille-produits/{id}", familleProduit.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(familleProduit.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingFamilleProduit() throws Exception {
        // Get the familleProduit
        restFamilleProduitMockMvc.perform(get("/api/famille-produits/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFamilleProduit() throws Exception {
        // Initialize the database
        familleProduitRepository.saveAndFlush(familleProduit);

        int databaseSizeBeforeUpdate = familleProduitRepository.findAll().size();

        // Update the familleProduit
        FamilleProduit updatedFamilleProduit = familleProduitRepository.findById(familleProduit.getId()).get();
        // Disconnect from session so that the updates on updatedFamilleProduit are not directly saved in db
        em.detach(updatedFamilleProduit);
        updatedFamilleProduit
            .code(UPDATED_CODE)
            .libelle(UPDATED_LIBELLE)
            .status(UPDATED_STATUS);
        FamilleProduitDTO familleProduitDTO = familleProduitMapper.toDto(updatedFamilleProduit);

        restFamilleProduitMockMvc.perform(put("/api/famille-produits").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(familleProduitDTO)))
            .andExpect(status().isOk());

        // Validate the FamilleProduit in the database
        List<FamilleProduit> familleProduitList = familleProduitRepository.findAll();
        assertThat(familleProduitList).hasSize(databaseSizeBeforeUpdate);
        FamilleProduit testFamilleProduit = familleProduitList.get(familleProduitList.size() - 1);
        assertThat(testFamilleProduit.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testFamilleProduit.getLibelle()).isEqualTo(UPDATED_LIBELLE);
        assertThat(testFamilleProduit.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingFamilleProduit() throws Exception {
        int databaseSizeBeforeUpdate = familleProduitRepository.findAll().size();

        // Create the FamilleProduit
        FamilleProduitDTO familleProduitDTO = familleProduitMapper.toDto(familleProduit);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFamilleProduitMockMvc.perform(put("/api/famille-produits").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(familleProduitDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FamilleProduit in the database
        List<FamilleProduit> familleProduitList = familleProduitRepository.findAll();
        assertThat(familleProduitList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFamilleProduit() throws Exception {
        // Initialize the database
        familleProduitRepository.saveAndFlush(familleProduit);

        int databaseSizeBeforeDelete = familleProduitRepository.findAll().size();

        // Delete the familleProduit
        restFamilleProduitMockMvc.perform(delete("/api/famille-produits/{id}", familleProduit.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<FamilleProduit> familleProduitList = familleProduitRepository.findAll();
        assertThat(familleProduitList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
