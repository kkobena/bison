package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.NucleusApp;
import com.kobe.nucleus.domain.ModelFacture;
import com.kobe.nucleus.repository.ModelFactureRepository;
import com.kobe.nucleus.service.ModelFactureService;
import com.kobe.nucleus.service.dto.ModelFactureDTO;
import com.kobe.nucleus.service.mapper.ModelFactureMapper;

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
 * Integration tests for the {@link ModelFactureResource} REST controller.
 */
@SpringBootTest(classes = NucleusApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class ModelFactureResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_FICHIER = "AAAAAAAAAA";
    private static final String UPDATED_FICHIER = "BBBBBBBBBB";

    @Autowired
    private ModelFactureRepository modelFactureRepository;

    @Autowired
    private ModelFactureMapper modelFactureMapper;

    @Autowired
    private ModelFactureService modelFactureService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restModelFactureMockMvc;

    private ModelFacture modelFacture;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ModelFacture createEntity(EntityManager em) {
        ModelFacture modelFacture = new ModelFacture()
            .libelle(DEFAULT_LIBELLE)
            .code(DEFAULT_CODE)
            .fichier(DEFAULT_FICHIER);
        return modelFacture;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ModelFacture createUpdatedEntity(EntityManager em) {
        ModelFacture modelFacture = new ModelFacture()
            .libelle(UPDATED_LIBELLE)
            .code(UPDATED_CODE)
            .fichier(UPDATED_FICHIER);
        return modelFacture;
    }

    @BeforeEach
    public void initTest() {
        modelFacture = createEntity(em);
    }

    @Test
    @Transactional
    public void createModelFacture() throws Exception {
        int databaseSizeBeforeCreate = modelFactureRepository.findAll().size();

        // Create the ModelFacture
        ModelFactureDTO modelFactureDTO = modelFactureMapper.toDto(modelFacture);
        restModelFactureMockMvc.perform(post("/api/model-factures").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(modelFactureDTO)))
            .andExpect(status().isCreated());

        // Validate the ModelFacture in the database
        List<ModelFacture> modelFactureList = modelFactureRepository.findAll();
        assertThat(modelFactureList).hasSize(databaseSizeBeforeCreate + 1);
        ModelFacture testModelFacture = modelFactureList.get(modelFactureList.size() - 1);
        assertThat(testModelFacture.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
        assertThat(testModelFacture.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testModelFacture.getFichier()).isEqualTo(DEFAULT_FICHIER);
    }

    @Test
    @Transactional
    public void createModelFactureWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = modelFactureRepository.findAll().size();

        // Create the ModelFacture with an existing ID
        modelFacture.setId(1L);
        ModelFactureDTO modelFactureDTO = modelFactureMapper.toDto(modelFacture);

        // An entity with an existing ID cannot be created, so this API call must fail
        restModelFactureMockMvc.perform(post("/api/model-factures").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(modelFactureDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ModelFacture in the database
        List<ModelFacture> modelFactureList = modelFactureRepository.findAll();
        assertThat(modelFactureList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLibelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = modelFactureRepository.findAll().size();
        // set the field null
        modelFacture.setLibelle(null);

        // Create the ModelFacture, which fails.
        ModelFactureDTO modelFactureDTO = modelFactureMapper.toDto(modelFacture);

        restModelFactureMockMvc.perform(post("/api/model-factures").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(modelFactureDTO)))
            .andExpect(status().isBadRequest());

        List<ModelFacture> modelFactureList = modelFactureRepository.findAll();
        assertThat(modelFactureList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = modelFactureRepository.findAll().size();
        // set the field null
        modelFacture.setCode(null);

        // Create the ModelFacture, which fails.
        ModelFactureDTO modelFactureDTO = modelFactureMapper.toDto(modelFacture);

        restModelFactureMockMvc.perform(post("/api/model-factures").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(modelFactureDTO)))
            .andExpect(status().isBadRequest());

        List<ModelFacture> modelFactureList = modelFactureRepository.findAll();
        assertThat(modelFactureList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFichierIsRequired() throws Exception {
        int databaseSizeBeforeTest = modelFactureRepository.findAll().size();
        // set the field null
        modelFacture.setFichier(null);

        // Create the ModelFacture, which fails.
        ModelFactureDTO modelFactureDTO = modelFactureMapper.toDto(modelFacture);

        restModelFactureMockMvc.perform(post("/api/model-factures").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(modelFactureDTO)))
            .andExpect(status().isBadRequest());

        List<ModelFacture> modelFactureList = modelFactureRepository.findAll();
        assertThat(modelFactureList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllModelFactures() throws Exception {
        // Initialize the database
        modelFactureRepository.saveAndFlush(modelFacture);

        // Get all the modelFactureList
        restModelFactureMockMvc.perform(get("/api/model-factures?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(modelFacture.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].fichier").value(hasItem(DEFAULT_FICHIER)));
    }
    
    @Test
    @Transactional
    public void getModelFacture() throws Exception {
        // Initialize the database
        modelFactureRepository.saveAndFlush(modelFacture);

        // Get the modelFacture
        restModelFactureMockMvc.perform(get("/api/model-factures/{id}", modelFacture.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(modelFacture.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.fichier").value(DEFAULT_FICHIER));
    }

    @Test
    @Transactional
    public void getNonExistingModelFacture() throws Exception {
        // Get the modelFacture
        restModelFactureMockMvc.perform(get("/api/model-factures/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateModelFacture() throws Exception {
        // Initialize the database
        modelFactureRepository.saveAndFlush(modelFacture);

        int databaseSizeBeforeUpdate = modelFactureRepository.findAll().size();

        // Update the modelFacture
        ModelFacture updatedModelFacture = modelFactureRepository.findById(modelFacture.getId()).get();
        // Disconnect from session so that the updates on updatedModelFacture are not directly saved in db
        em.detach(updatedModelFacture);
        updatedModelFacture
            .libelle(UPDATED_LIBELLE)
            .code(UPDATED_CODE)
            .fichier(UPDATED_FICHIER);
        ModelFactureDTO modelFactureDTO = modelFactureMapper.toDto(updatedModelFacture);

        restModelFactureMockMvc.perform(put("/api/model-factures").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(modelFactureDTO)))
            .andExpect(status().isOk());

        // Validate the ModelFacture in the database
        List<ModelFacture> modelFactureList = modelFactureRepository.findAll();
        assertThat(modelFactureList).hasSize(databaseSizeBeforeUpdate);
        ModelFacture testModelFacture = modelFactureList.get(modelFactureList.size() - 1);
        assertThat(testModelFacture.getLibelle()).isEqualTo(UPDATED_LIBELLE);
        assertThat(testModelFacture.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testModelFacture.getFichier()).isEqualTo(UPDATED_FICHIER);
    }

    @Test
    @Transactional
    public void updateNonExistingModelFacture() throws Exception {
        int databaseSizeBeforeUpdate = modelFactureRepository.findAll().size();

        // Create the ModelFacture
        ModelFactureDTO modelFactureDTO = modelFactureMapper.toDto(modelFacture);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restModelFactureMockMvc.perform(put("/api/model-factures").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(modelFactureDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ModelFacture in the database
        List<ModelFacture> modelFactureList = modelFactureRepository.findAll();
        assertThat(modelFactureList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteModelFacture() throws Exception {
        // Initialize the database
        modelFactureRepository.saveAndFlush(modelFacture);

        int databaseSizeBeforeDelete = modelFactureRepository.findAll().size();

        // Delete the modelFacture
        restModelFactureMockMvc.perform(delete("/api/model-factures/{id}", modelFacture.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ModelFacture> modelFactureList = modelFactureRepository.findAll();
        assertThat(modelFactureList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
