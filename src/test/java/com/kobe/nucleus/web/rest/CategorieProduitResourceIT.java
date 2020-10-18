package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.NucleusApp;
import com.kobe.nucleus.domain.CategorieProduit;
import com.kobe.nucleus.repository.CategorieProduitRepository;
import com.kobe.nucleus.service.CategorieProduitService;
import com.kobe.nucleus.service.dto.CategorieProduitDTO;
import com.kobe.nucleus.service.mapper.CategorieProduitMapper;

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
 * Integration tests for the {@link CategorieProduitResource} REST controller.
 */
@SpringBootTest(classes = NucleusApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CategorieProduitResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    private static final Status DEFAULT_STATUS = Status.ACTIVE;
    private static final Status UPDATED_STATUS = Status.ENATTENTE;

    @Autowired
    private CategorieProduitRepository categorieProduitRepository;

    @Autowired
    private CategorieProduitMapper categorieProduitMapper;

    @Autowired
    private CategorieProduitService categorieProduitService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCategorieProduitMockMvc;

    private CategorieProduit categorieProduit;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CategorieProduit createEntity(EntityManager em) {
        CategorieProduit categorieProduit = new CategorieProduit()
            .code(DEFAULT_CODE)
            .libelle(DEFAULT_LIBELLE)
            .status(DEFAULT_STATUS);
        return categorieProduit;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CategorieProduit createUpdatedEntity(EntityManager em) {
        CategorieProduit categorieProduit = new CategorieProduit()
            .code(UPDATED_CODE)
            .libelle(UPDATED_LIBELLE)
            .status(UPDATED_STATUS);
        return categorieProduit;
    }

    @BeforeEach
    public void initTest() {
        categorieProduit = createEntity(em);
    }

    @Test
    @Transactional
    public void createCategorieProduit() throws Exception {
        int databaseSizeBeforeCreate = categorieProduitRepository.findAll().size();
        // Create the CategorieProduit
        CategorieProduitDTO categorieProduitDTO = categorieProduitMapper.toDto(categorieProduit);
        restCategorieProduitMockMvc.perform(post("/api/categorie-produits").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(categorieProduitDTO)))
            .andExpect(status().isCreated());

        // Validate the CategorieProduit in the database
        List<CategorieProduit> categorieProduitList = categorieProduitRepository.findAll();
        assertThat(categorieProduitList).hasSize(databaseSizeBeforeCreate + 1);
        CategorieProduit testCategorieProduit = categorieProduitList.get(categorieProduitList.size() - 1);
        assertThat(testCategorieProduit.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testCategorieProduit.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
        assertThat(testCategorieProduit.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createCategorieProduitWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = categorieProduitRepository.findAll().size();

        // Create the CategorieProduit with an existing ID
        categorieProduit.setId(1L);
        CategorieProduitDTO categorieProduitDTO = categorieProduitMapper.toDto(categorieProduit);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCategorieProduitMockMvc.perform(post("/api/categorie-produits").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(categorieProduitDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CategorieProduit in the database
        List<CategorieProduit> categorieProduitList = categorieProduitRepository.findAll();
        assertThat(categorieProduitList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = categorieProduitRepository.findAll().size();
        // set the field null
        categorieProduit.setCode(null);

        // Create the CategorieProduit, which fails.
        CategorieProduitDTO categorieProduitDTO = categorieProduitMapper.toDto(categorieProduit);


        restCategorieProduitMockMvc.perform(post("/api/categorie-produits").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(categorieProduitDTO)))
            .andExpect(status().isBadRequest());

        List<CategorieProduit> categorieProduitList = categorieProduitRepository.findAll();
        assertThat(categorieProduitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLibelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = categorieProduitRepository.findAll().size();
        // set the field null
        categorieProduit.setLibelle(null);

        // Create the CategorieProduit, which fails.
        CategorieProduitDTO categorieProduitDTO = categorieProduitMapper.toDto(categorieProduit);


        restCategorieProduitMockMvc.perform(post("/api/categorie-produits").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(categorieProduitDTO)))
            .andExpect(status().isBadRequest());

        List<CategorieProduit> categorieProduitList = categorieProduitRepository.findAll();
        assertThat(categorieProduitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = categorieProduitRepository.findAll().size();
        // set the field null
        categorieProduit.setStatus(null);

        // Create the CategorieProduit, which fails.
        CategorieProduitDTO categorieProduitDTO = categorieProduitMapper.toDto(categorieProduit);


        restCategorieProduitMockMvc.perform(post("/api/categorie-produits").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(categorieProduitDTO)))
            .andExpect(status().isBadRequest());

        List<CategorieProduit> categorieProduitList = categorieProduitRepository.findAll();
        assertThat(categorieProduitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCategorieProduits() throws Exception {
        // Initialize the database
        categorieProduitRepository.saveAndFlush(categorieProduit);

        // Get all the categorieProduitList
        restCategorieProduitMockMvc.perform(get("/api/categorie-produits?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(categorieProduit.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }
    
    @Test
    @Transactional
    public void getCategorieProduit() throws Exception {
        // Initialize the database
        categorieProduitRepository.saveAndFlush(categorieProduit);

        // Get the categorieProduit
        restCategorieProduitMockMvc.perform(get("/api/categorie-produits/{id}", categorieProduit.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(categorieProduit.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingCategorieProduit() throws Exception {
        // Get the categorieProduit
        restCategorieProduitMockMvc.perform(get("/api/categorie-produits/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCategorieProduit() throws Exception {
        // Initialize the database
        categorieProduitRepository.saveAndFlush(categorieProduit);

        int databaseSizeBeforeUpdate = categorieProduitRepository.findAll().size();

        // Update the categorieProduit
        CategorieProduit updatedCategorieProduit = categorieProduitRepository.findById(categorieProduit.getId()).get();
        // Disconnect from session so that the updates on updatedCategorieProduit are not directly saved in db
        em.detach(updatedCategorieProduit);
        updatedCategorieProduit
            .code(UPDATED_CODE)
            .libelle(UPDATED_LIBELLE)
            .status(UPDATED_STATUS);
        CategorieProduitDTO categorieProduitDTO = categorieProduitMapper.toDto(updatedCategorieProduit);

        restCategorieProduitMockMvc.perform(put("/api/categorie-produits").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(categorieProduitDTO)))
            .andExpect(status().isOk());

        // Validate the CategorieProduit in the database
        List<CategorieProduit> categorieProduitList = categorieProduitRepository.findAll();
        assertThat(categorieProduitList).hasSize(databaseSizeBeforeUpdate);
        CategorieProduit testCategorieProduit = categorieProduitList.get(categorieProduitList.size() - 1);
        assertThat(testCategorieProduit.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testCategorieProduit.getLibelle()).isEqualTo(UPDATED_LIBELLE);
        assertThat(testCategorieProduit.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingCategorieProduit() throws Exception {
        int databaseSizeBeforeUpdate = categorieProduitRepository.findAll().size();

        // Create the CategorieProduit
        CategorieProduitDTO categorieProduitDTO = categorieProduitMapper.toDto(categorieProduit);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCategorieProduitMockMvc.perform(put("/api/categorie-produits").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(categorieProduitDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CategorieProduit in the database
        List<CategorieProduit> categorieProduitList = categorieProduitRepository.findAll();
        assertThat(categorieProduitList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCategorieProduit() throws Exception {
        // Initialize the database
        categorieProduitRepository.saveAndFlush(categorieProduit);

        int databaseSizeBeforeDelete = categorieProduitRepository.findAll().size();

        // Delete the categorieProduit
        restCategorieProduitMockMvc.perform(delete("/api/categorie-produits/{id}", categorieProduit.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CategorieProduit> categorieProduitList = categorieProduitRepository.findAll();
        assertThat(categorieProduitList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
