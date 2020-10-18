package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.NucleusApp;
import com.kobe.nucleus.domain.MvtsProduit;
import com.kobe.nucleus.repository.MvtsProduitRepository;
import com.kobe.nucleus.service.MvtsProduitService;
import com.kobe.nucleus.service.dto.MvtsProduitDTO;
import com.kobe.nucleus.service.mapper.MvtsProduitMapper;

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
 * Integration tests for the {@link MvtsProduitResource} REST controller.
 */
@SpringBootTest(classes = NucleusApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class MvtsProduitResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    @Autowired
    private MvtsProduitRepository mvtsProduitRepository;

    @Autowired
    private MvtsProduitMapper mvtsProduitMapper;

    @Autowired
    private MvtsProduitService mvtsProduitService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMvtsProduitMockMvc;

    private MvtsProduit mvtsProduit;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MvtsProduit createEntity(EntityManager em) {
        MvtsProduit mvtsProduit = new MvtsProduit()
            .libelle(DEFAULT_LIBELLE);
        return mvtsProduit;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MvtsProduit createUpdatedEntity(EntityManager em) {
        MvtsProduit mvtsProduit = new MvtsProduit()
            .libelle(UPDATED_LIBELLE);
        return mvtsProduit;
    }

    @BeforeEach
    public void initTest() {
        mvtsProduit = createEntity(em);
    }

    @Test
    @Transactional
    public void createMvtsProduit() throws Exception {
        int databaseSizeBeforeCreate = mvtsProduitRepository.findAll().size();
        // Create the MvtsProduit
        MvtsProduitDTO mvtsProduitDTO = mvtsProduitMapper.toDto(mvtsProduit);
        restMvtsProduitMockMvc.perform(post("/api/mvts-produits").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mvtsProduitDTO)))
            .andExpect(status().isCreated());

        // Validate the MvtsProduit in the database
        List<MvtsProduit> mvtsProduitList = mvtsProduitRepository.findAll();
        assertThat(mvtsProduitList).hasSize(databaseSizeBeforeCreate + 1);
        MvtsProduit testMvtsProduit = mvtsProduitList.get(mvtsProduitList.size() - 1);
        assertThat(testMvtsProduit.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
    }

    @Test
    @Transactional
    public void createMvtsProduitWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mvtsProduitRepository.findAll().size();

        // Create the MvtsProduit with an existing ID
        mvtsProduit.setId(1L);
        MvtsProduitDTO mvtsProduitDTO = mvtsProduitMapper.toDto(mvtsProduit);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMvtsProduitMockMvc.perform(post("/api/mvts-produits").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mvtsProduitDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MvtsProduit in the database
        List<MvtsProduit> mvtsProduitList = mvtsProduitRepository.findAll();
        assertThat(mvtsProduitList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLibelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = mvtsProduitRepository.findAll().size();
        // set the field null
        mvtsProduit.setLibelle(null);

        // Create the MvtsProduit, which fails.
        MvtsProduitDTO mvtsProduitDTO = mvtsProduitMapper.toDto(mvtsProduit);


        restMvtsProduitMockMvc.perform(post("/api/mvts-produits").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mvtsProduitDTO)))
            .andExpect(status().isBadRequest());

        List<MvtsProduit> mvtsProduitList = mvtsProduitRepository.findAll();
        assertThat(mvtsProduitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMvtsProduits() throws Exception {
        // Initialize the database
        mvtsProduitRepository.saveAndFlush(mvtsProduit);

        // Get all the mvtsProduitList
        restMvtsProduitMockMvc.perform(get("/api/mvts-produits?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mvtsProduit.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)));
    }
    
    @Test
    @Transactional
    public void getMvtsProduit() throws Exception {
        // Initialize the database
        mvtsProduitRepository.saveAndFlush(mvtsProduit);

        // Get the mvtsProduit
        restMvtsProduitMockMvc.perform(get("/api/mvts-produits/{id}", mvtsProduit.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(mvtsProduit.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE));
    }
    @Test
    @Transactional
    public void getNonExistingMvtsProduit() throws Exception {
        // Get the mvtsProduit
        restMvtsProduitMockMvc.perform(get("/api/mvts-produits/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMvtsProduit() throws Exception {
        // Initialize the database
        mvtsProduitRepository.saveAndFlush(mvtsProduit);

        int databaseSizeBeforeUpdate = mvtsProduitRepository.findAll().size();

        // Update the mvtsProduit
        MvtsProduit updatedMvtsProduit = mvtsProduitRepository.findById(mvtsProduit.getId()).get();
        // Disconnect from session so that the updates on updatedMvtsProduit are not directly saved in db
        em.detach(updatedMvtsProduit);
        updatedMvtsProduit
            .libelle(UPDATED_LIBELLE);
        MvtsProduitDTO mvtsProduitDTO = mvtsProduitMapper.toDto(updatedMvtsProduit);

        restMvtsProduitMockMvc.perform(put("/api/mvts-produits").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mvtsProduitDTO)))
            .andExpect(status().isOk());

        // Validate the MvtsProduit in the database
        List<MvtsProduit> mvtsProduitList = mvtsProduitRepository.findAll();
        assertThat(mvtsProduitList).hasSize(databaseSizeBeforeUpdate);
        MvtsProduit testMvtsProduit = mvtsProduitList.get(mvtsProduitList.size() - 1);
        assertThat(testMvtsProduit.getLibelle()).isEqualTo(UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void updateNonExistingMvtsProduit() throws Exception {
        int databaseSizeBeforeUpdate = mvtsProduitRepository.findAll().size();

        // Create the MvtsProduit
        MvtsProduitDTO mvtsProduitDTO = mvtsProduitMapper.toDto(mvtsProduit);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMvtsProduitMockMvc.perform(put("/api/mvts-produits").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mvtsProduitDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MvtsProduit in the database
        List<MvtsProduit> mvtsProduitList = mvtsProduitRepository.findAll();
        assertThat(mvtsProduitList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMvtsProduit() throws Exception {
        // Initialize the database
        mvtsProduitRepository.saveAndFlush(mvtsProduit);

        int databaseSizeBeforeDelete = mvtsProduitRepository.findAll().size();

        // Delete the mvtsProduit
        restMvtsProduitMockMvc.perform(delete("/api/mvts-produits/{id}", mvtsProduit.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MvtsProduit> mvtsProduitList = mvtsProduitRepository.findAll();
        assertThat(mvtsProduitList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
