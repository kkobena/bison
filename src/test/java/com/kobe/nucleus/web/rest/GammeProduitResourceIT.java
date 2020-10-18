package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.NucleusApp;
import com.kobe.nucleus.domain.GammeProduit;
import com.kobe.nucleus.repository.GammeProduitRepository;
import com.kobe.nucleus.service.GammeProduitService;
import com.kobe.nucleus.service.dto.GammeProduitDTO;
import com.kobe.nucleus.service.mapper.GammeProduitMapper;

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

/**
 * Integration tests for the {@link GammeProduitResource} REST controller.
 */
@SpringBootTest(classes = NucleusApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class GammeProduitResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    @Autowired
    private GammeProduitRepository gammeProduitRepository;

    @Autowired
    private GammeProduitMapper gammeProduitMapper;

    @Autowired
    private GammeProduitService gammeProduitService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restGammeProduitMockMvc;

    private GammeProduit gammeProduit;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GammeProduit createEntity(EntityManager em) {
        GammeProduit gammeProduit = new GammeProduit()
            .code(DEFAULT_CODE)
            .libelle(DEFAULT_LIBELLE);
        return gammeProduit;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GammeProduit createUpdatedEntity(EntityManager em) {
        GammeProduit gammeProduit = new GammeProduit()
            .code(UPDATED_CODE)
            .libelle(UPDATED_LIBELLE);
        return gammeProduit;
    }

    @BeforeEach
    public void initTest() {
        gammeProduit = createEntity(em);
    }

    @Test
    @Transactional
    public void createGammeProduit() throws Exception {
        int databaseSizeBeforeCreate = gammeProduitRepository.findAll().size();
        // Create the GammeProduit
        GammeProduitDTO gammeProduitDTO = gammeProduitMapper.toDto(gammeProduit);
        restGammeProduitMockMvc.perform(post("/api/gamme-produits").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(gammeProduitDTO)))
            .andExpect(status().isCreated());

        // Validate the GammeProduit in the database
        List<GammeProduit> gammeProduitList = gammeProduitRepository.findAll();
        assertThat(gammeProduitList).hasSize(databaseSizeBeforeCreate + 1);
        GammeProduit testGammeProduit = gammeProduitList.get(gammeProduitList.size() - 1);
        assertThat(testGammeProduit.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testGammeProduit.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
    }

    @Test
    @Transactional
    public void createGammeProduitWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = gammeProduitRepository.findAll().size();

        // Create the GammeProduit with an existing ID
        gammeProduit.setId(1L);
        GammeProduitDTO gammeProduitDTO = gammeProduitMapper.toDto(gammeProduit);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGammeProduitMockMvc.perform(post("/api/gamme-produits").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(gammeProduitDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GammeProduit in the database
        List<GammeProduit> gammeProduitList = gammeProduitRepository.findAll();
        assertThat(gammeProduitList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = gammeProduitRepository.findAll().size();
        // set the field null
        gammeProduit.setCode(null);

        // Create the GammeProduit, which fails.
        GammeProduitDTO gammeProduitDTO = gammeProduitMapper.toDto(gammeProduit);


        restGammeProduitMockMvc.perform(post("/api/gamme-produits").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(gammeProduitDTO)))
            .andExpect(status().isBadRequest());

        List<GammeProduit> gammeProduitList = gammeProduitRepository.findAll();
        assertThat(gammeProduitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLibelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = gammeProduitRepository.findAll().size();
        // set the field null
        gammeProduit.setLibelle(null);

        // Create the GammeProduit, which fails.
        GammeProduitDTO gammeProduitDTO = gammeProduitMapper.toDto(gammeProduit);


        restGammeProduitMockMvc.perform(post("/api/gamme-produits").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(gammeProduitDTO)))
            .andExpect(status().isBadRequest());

        List<GammeProduit> gammeProduitList = gammeProduitRepository.findAll();
        assertThat(gammeProduitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllGammeProduits() throws Exception {
        // Initialize the database
        gammeProduitRepository.saveAndFlush(gammeProduit);

        // Get all the gammeProduitList
        restGammeProduitMockMvc.perform(get("/api/gamme-produits?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(gammeProduit.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)));
    }
    
    @Test
    @Transactional
    public void getGammeProduit() throws Exception {
        // Initialize the database
        gammeProduitRepository.saveAndFlush(gammeProduit);

        // Get the gammeProduit
        restGammeProduitMockMvc.perform(get("/api/gamme-produits/{id}", gammeProduit.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(gammeProduit.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE));
    }
    @Test
    @Transactional
    public void getNonExistingGammeProduit() throws Exception {
        // Get the gammeProduit
        restGammeProduitMockMvc.perform(get("/api/gamme-produits/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGammeProduit() throws Exception {
        // Initialize the database
        gammeProduitRepository.saveAndFlush(gammeProduit);

        int databaseSizeBeforeUpdate = gammeProduitRepository.findAll().size();

        // Update the gammeProduit
        GammeProduit updatedGammeProduit = gammeProduitRepository.findById(gammeProduit.getId()).get();
        // Disconnect from session so that the updates on updatedGammeProduit are not directly saved in db
        em.detach(updatedGammeProduit);
        updatedGammeProduit
            .code(UPDATED_CODE)
            .libelle(UPDATED_LIBELLE);
        GammeProduitDTO gammeProduitDTO = gammeProduitMapper.toDto(updatedGammeProduit);

        restGammeProduitMockMvc.perform(put("/api/gamme-produits").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(gammeProduitDTO)))
            .andExpect(status().isOk());

        // Validate the GammeProduit in the database
        List<GammeProduit> gammeProduitList = gammeProduitRepository.findAll();
        assertThat(gammeProduitList).hasSize(databaseSizeBeforeUpdate);
        GammeProduit testGammeProduit = gammeProduitList.get(gammeProduitList.size() - 1);
        assertThat(testGammeProduit.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testGammeProduit.getLibelle()).isEqualTo(UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void updateNonExistingGammeProduit() throws Exception {
        int databaseSizeBeforeUpdate = gammeProduitRepository.findAll().size();

        // Create the GammeProduit
        GammeProduitDTO gammeProduitDTO = gammeProduitMapper.toDto(gammeProduit);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGammeProduitMockMvc.perform(put("/api/gamme-produits").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(gammeProduitDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GammeProduit in the database
        List<GammeProduit> gammeProduitList = gammeProduitRepository.findAll();
        assertThat(gammeProduitList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGammeProduit() throws Exception {
        // Initialize the database
        gammeProduitRepository.saveAndFlush(gammeProduit);

        int databaseSizeBeforeDelete = gammeProduitRepository.findAll().size();

        // Delete the gammeProduit
        restGammeProduitMockMvc.perform(delete("/api/gamme-produits/{id}", gammeProduit.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<GammeProduit> gammeProduitList = gammeProduitRepository.findAll();
        assertThat(gammeProduitList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
