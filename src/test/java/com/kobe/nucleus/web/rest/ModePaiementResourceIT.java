package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.NucleusApp;
import com.kobe.nucleus.domain.ModePaiement;
import com.kobe.nucleus.repository.ModePaiementRepository;
import com.kobe.nucleus.service.ModePaiementService;
import com.kobe.nucleus.service.dto.ModePaiementDTO;
import com.kobe.nucleus.service.mapper.ModePaiementMapper;

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
 * Integration tests for the {@link ModePaiementResource} REST controller.
 */
@SpringBootTest(classes = NucleusApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ModePaiementResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    private static final Status DEFAULT_STATUS = Status.ACTIVE;
    private static final Status UPDATED_STATUS = Status.ENATTENTE;

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    @Autowired
    private ModePaiementRepository modePaiementRepository;

    @Autowired
    private ModePaiementMapper modePaiementMapper;

    @Autowired
    private ModePaiementService modePaiementService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restModePaiementMockMvc;

    private ModePaiement modePaiement;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ModePaiement createEntity(EntityManager em) {
        ModePaiement modePaiement = new ModePaiement()
            .libelle(DEFAULT_LIBELLE)
            .status(DEFAULT_STATUS)
            .code(DEFAULT_CODE);
        return modePaiement;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ModePaiement createUpdatedEntity(EntityManager em) {
        ModePaiement modePaiement = new ModePaiement()
            .libelle(UPDATED_LIBELLE)
            .status(UPDATED_STATUS)
            .code(UPDATED_CODE);
        return modePaiement;
    }

    @BeforeEach
    public void initTest() {
        modePaiement = createEntity(em);
    }

    @Test
    @Transactional
    public void createModePaiement() throws Exception {
        int databaseSizeBeforeCreate = modePaiementRepository.findAll().size();
        // Create the ModePaiement
        ModePaiementDTO modePaiementDTO = modePaiementMapper.toDto(modePaiement);
        restModePaiementMockMvc.perform(post("/api/mode-paiements").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(modePaiementDTO)))
            .andExpect(status().isCreated());

        // Validate the ModePaiement in the database
        List<ModePaiement> modePaiementList = modePaiementRepository.findAll();
        assertThat(modePaiementList).hasSize(databaseSizeBeforeCreate + 1);
        ModePaiement testModePaiement = modePaiementList.get(modePaiementList.size() - 1);
        assertThat(testModePaiement.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
        assertThat(testModePaiement.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testModePaiement.getCode()).isEqualTo(DEFAULT_CODE);
    }

    @Test
    @Transactional
    public void createModePaiementWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = modePaiementRepository.findAll().size();

        // Create the ModePaiement with an existing ID
        modePaiement.setId(1L);
        ModePaiementDTO modePaiementDTO = modePaiementMapper.toDto(modePaiement);

        // An entity with an existing ID cannot be created, so this API call must fail
        restModePaiementMockMvc.perform(post("/api/mode-paiements").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(modePaiementDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ModePaiement in the database
        List<ModePaiement> modePaiementList = modePaiementRepository.findAll();
        assertThat(modePaiementList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLibelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = modePaiementRepository.findAll().size();
        // set the field null
        modePaiement.setLibelle(null);

        // Create the ModePaiement, which fails.
        ModePaiementDTO modePaiementDTO = modePaiementMapper.toDto(modePaiement);


        restModePaiementMockMvc.perform(post("/api/mode-paiements").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(modePaiementDTO)))
            .andExpect(status().isBadRequest());

        List<ModePaiement> modePaiementList = modePaiementRepository.findAll();
        assertThat(modePaiementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = modePaiementRepository.findAll().size();
        // set the field null
        modePaiement.setStatus(null);

        // Create the ModePaiement, which fails.
        ModePaiementDTO modePaiementDTO = modePaiementMapper.toDto(modePaiement);


        restModePaiementMockMvc.perform(post("/api/mode-paiements").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(modePaiementDTO)))
            .andExpect(status().isBadRequest());

        List<ModePaiement> modePaiementList = modePaiementRepository.findAll();
        assertThat(modePaiementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = modePaiementRepository.findAll().size();
        // set the field null
        modePaiement.setCode(null);

        // Create the ModePaiement, which fails.
        ModePaiementDTO modePaiementDTO = modePaiementMapper.toDto(modePaiement);


        restModePaiementMockMvc.perform(post("/api/mode-paiements").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(modePaiementDTO)))
            .andExpect(status().isBadRequest());

        List<ModePaiement> modePaiementList = modePaiementRepository.findAll();
        assertThat(modePaiementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllModePaiements() throws Exception {
        // Initialize the database
        modePaiementRepository.saveAndFlush(modePaiement);

        // Get all the modePaiementList
        restModePaiementMockMvc.perform(get("/api/mode-paiements?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(modePaiement.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)));
    }
    
    @Test
    @Transactional
    public void getModePaiement() throws Exception {
        // Initialize the database
        modePaiementRepository.saveAndFlush(modePaiement);

        // Get the modePaiement
        restModePaiementMockMvc.perform(get("/api/mode-paiements/{id}", modePaiement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(modePaiement.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE));
    }
    @Test
    @Transactional
    public void getNonExistingModePaiement() throws Exception {
        // Get the modePaiement
        restModePaiementMockMvc.perform(get("/api/mode-paiements/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateModePaiement() throws Exception {
        // Initialize the database
        modePaiementRepository.saveAndFlush(modePaiement);

        int databaseSizeBeforeUpdate = modePaiementRepository.findAll().size();

        // Update the modePaiement
        ModePaiement updatedModePaiement = modePaiementRepository.findById(modePaiement.getId()).get();
        // Disconnect from session so that the updates on updatedModePaiement are not directly saved in db
        em.detach(updatedModePaiement);
        updatedModePaiement
            .libelle(UPDATED_LIBELLE)
            .status(UPDATED_STATUS)
            .code(UPDATED_CODE);
        ModePaiementDTO modePaiementDTO = modePaiementMapper.toDto(updatedModePaiement);

        restModePaiementMockMvc.perform(put("/api/mode-paiements").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(modePaiementDTO)))
            .andExpect(status().isOk());

        // Validate the ModePaiement in the database
        List<ModePaiement> modePaiementList = modePaiementRepository.findAll();
        assertThat(modePaiementList).hasSize(databaseSizeBeforeUpdate);
        ModePaiement testModePaiement = modePaiementList.get(modePaiementList.size() - 1);
        assertThat(testModePaiement.getLibelle()).isEqualTo(UPDATED_LIBELLE);
        assertThat(testModePaiement.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testModePaiement.getCode()).isEqualTo(UPDATED_CODE);
    }

    @Test
    @Transactional
    public void updateNonExistingModePaiement() throws Exception {
        int databaseSizeBeforeUpdate = modePaiementRepository.findAll().size();

        // Create the ModePaiement
        ModePaiementDTO modePaiementDTO = modePaiementMapper.toDto(modePaiement);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restModePaiementMockMvc.perform(put("/api/mode-paiements").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(modePaiementDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ModePaiement in the database
        List<ModePaiement> modePaiementList = modePaiementRepository.findAll();
        assertThat(modePaiementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteModePaiement() throws Exception {
        // Initialize the database
        modePaiementRepository.saveAndFlush(modePaiement);

        int databaseSizeBeforeDelete = modePaiementRepository.findAll().size();

        // Delete the modePaiement
        restModePaiementMockMvc.perform(delete("/api/mode-paiements/{id}", modePaiement.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ModePaiement> modePaiementList = modePaiementRepository.findAll();
        assertThat(modePaiementList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
