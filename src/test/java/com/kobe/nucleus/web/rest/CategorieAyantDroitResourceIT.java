package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.NucleusApp;
import com.kobe.nucleus.domain.CategorieAyantDroit;
import com.kobe.nucleus.repository.CategorieAyantDroitRepository;
import com.kobe.nucleus.service.CategorieAyantDroitService;
import com.kobe.nucleus.service.dto.CategorieAyantDroitDTO;
import com.kobe.nucleus.service.mapper.CategorieAyantDroitMapper;

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
 * Integration tests for the {@link CategorieAyantDroitResource} REST controller.
 */
@SpringBootTest(classes = NucleusApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class CategorieAyantDroitResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    private static final Status DEFAULT_STATUS = Status.ACTIVE;
    private static final Status UPDATED_STATUS = Status.ENATTENTE;

    @Autowired
    private CategorieAyantDroitRepository categorieAyantDroitRepository;

    @Autowired
    private CategorieAyantDroitMapper categorieAyantDroitMapper;

    @Autowired
    private CategorieAyantDroitService categorieAyantDroitService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCategorieAyantDroitMockMvc;

    private CategorieAyantDroit categorieAyantDroit;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CategorieAyantDroit createEntity(EntityManager em) {
        CategorieAyantDroit categorieAyantDroit = new CategorieAyantDroit()
            .code(DEFAULT_CODE)
            .libelle(DEFAULT_LIBELLE)
            .status(DEFAULT_STATUS);
        return categorieAyantDroit;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CategorieAyantDroit createUpdatedEntity(EntityManager em) {
        CategorieAyantDroit categorieAyantDroit = new CategorieAyantDroit()
            .code(UPDATED_CODE)
            .libelle(UPDATED_LIBELLE)
            .status(UPDATED_STATUS);
        return categorieAyantDroit;
    }

    @BeforeEach
    public void initTest() {
        categorieAyantDroit = createEntity(em);
    }

    @Test
    @Transactional
    public void createCategorieAyantDroit() throws Exception {
        int databaseSizeBeforeCreate = categorieAyantDroitRepository.findAll().size();

        // Create the CategorieAyantDroit
        CategorieAyantDroitDTO categorieAyantDroitDTO = categorieAyantDroitMapper.toDto(categorieAyantDroit);
        restCategorieAyantDroitMockMvc.perform(post("/api/categorie-ayant-droits").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(categorieAyantDroitDTO)))
            .andExpect(status().isCreated());

        // Validate the CategorieAyantDroit in the database
        List<CategorieAyantDroit> categorieAyantDroitList = categorieAyantDroitRepository.findAll();
        assertThat(categorieAyantDroitList).hasSize(databaseSizeBeforeCreate + 1);
        CategorieAyantDroit testCategorieAyantDroit = categorieAyantDroitList.get(categorieAyantDroitList.size() - 1);
        assertThat(testCategorieAyantDroit.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testCategorieAyantDroit.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
        assertThat(testCategorieAyantDroit.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createCategorieAyantDroitWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = categorieAyantDroitRepository.findAll().size();

        // Create the CategorieAyantDroit with an existing ID
        categorieAyantDroit.setId(1L);
        CategorieAyantDroitDTO categorieAyantDroitDTO = categorieAyantDroitMapper.toDto(categorieAyantDroit);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCategorieAyantDroitMockMvc.perform(post("/api/categorie-ayant-droits").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(categorieAyantDroitDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CategorieAyantDroit in the database
        List<CategorieAyantDroit> categorieAyantDroitList = categorieAyantDroitRepository.findAll();
        assertThat(categorieAyantDroitList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = categorieAyantDroitRepository.findAll().size();
        // set the field null
        categorieAyantDroit.setCode(null);

        // Create the CategorieAyantDroit, which fails.
        CategorieAyantDroitDTO categorieAyantDroitDTO = categorieAyantDroitMapper.toDto(categorieAyantDroit);

        restCategorieAyantDroitMockMvc.perform(post("/api/categorie-ayant-droits").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(categorieAyantDroitDTO)))
            .andExpect(status().isBadRequest());

        List<CategorieAyantDroit> categorieAyantDroitList = categorieAyantDroitRepository.findAll();
        assertThat(categorieAyantDroitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLibelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = categorieAyantDroitRepository.findAll().size();
        // set the field null
        categorieAyantDroit.setLibelle(null);

        // Create the CategorieAyantDroit, which fails.
        CategorieAyantDroitDTO categorieAyantDroitDTO = categorieAyantDroitMapper.toDto(categorieAyantDroit);

        restCategorieAyantDroitMockMvc.perform(post("/api/categorie-ayant-droits").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(categorieAyantDroitDTO)))
            .andExpect(status().isBadRequest());

        List<CategorieAyantDroit> categorieAyantDroitList = categorieAyantDroitRepository.findAll();
        assertThat(categorieAyantDroitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = categorieAyantDroitRepository.findAll().size();
        // set the field null
        categorieAyantDroit.setStatus(null);

        // Create the CategorieAyantDroit, which fails.
        CategorieAyantDroitDTO categorieAyantDroitDTO = categorieAyantDroitMapper.toDto(categorieAyantDroit);

        restCategorieAyantDroitMockMvc.perform(post("/api/categorie-ayant-droits").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(categorieAyantDroitDTO)))
            .andExpect(status().isBadRequest());

        List<CategorieAyantDroit> categorieAyantDroitList = categorieAyantDroitRepository.findAll();
        assertThat(categorieAyantDroitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCategorieAyantDroits() throws Exception {
        // Initialize the database
        categorieAyantDroitRepository.saveAndFlush(categorieAyantDroit);

        // Get all the categorieAyantDroitList
        restCategorieAyantDroitMockMvc.perform(get("/api/categorie-ayant-droits?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(categorieAyantDroit.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }
    
    @Test
    @Transactional
    public void getCategorieAyantDroit() throws Exception {
        // Initialize the database
        categorieAyantDroitRepository.saveAndFlush(categorieAyantDroit);

        // Get the categorieAyantDroit
        restCategorieAyantDroitMockMvc.perform(get("/api/categorie-ayant-droits/{id}", categorieAyantDroit.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(categorieAyantDroit.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCategorieAyantDroit() throws Exception {
        // Get the categorieAyantDroit
        restCategorieAyantDroitMockMvc.perform(get("/api/categorie-ayant-droits/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCategorieAyantDroit() throws Exception {
        // Initialize the database
        categorieAyantDroitRepository.saveAndFlush(categorieAyantDroit);

        int databaseSizeBeforeUpdate = categorieAyantDroitRepository.findAll().size();

        // Update the categorieAyantDroit
        CategorieAyantDroit updatedCategorieAyantDroit = categorieAyantDroitRepository.findById(categorieAyantDroit.getId()).get();
        // Disconnect from session so that the updates on updatedCategorieAyantDroit are not directly saved in db
        em.detach(updatedCategorieAyantDroit);
        updatedCategorieAyantDroit
            .code(UPDATED_CODE)
            .libelle(UPDATED_LIBELLE)
            .status(UPDATED_STATUS);
        CategorieAyantDroitDTO categorieAyantDroitDTO = categorieAyantDroitMapper.toDto(updatedCategorieAyantDroit);

        restCategorieAyantDroitMockMvc.perform(put("/api/categorie-ayant-droits").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(categorieAyantDroitDTO)))
            .andExpect(status().isOk());

        // Validate the CategorieAyantDroit in the database
        List<CategorieAyantDroit> categorieAyantDroitList = categorieAyantDroitRepository.findAll();
        assertThat(categorieAyantDroitList).hasSize(databaseSizeBeforeUpdate);
        CategorieAyantDroit testCategorieAyantDroit = categorieAyantDroitList.get(categorieAyantDroitList.size() - 1);
        assertThat(testCategorieAyantDroit.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testCategorieAyantDroit.getLibelle()).isEqualTo(UPDATED_LIBELLE);
        assertThat(testCategorieAyantDroit.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingCategorieAyantDroit() throws Exception {
        int databaseSizeBeforeUpdate = categorieAyantDroitRepository.findAll().size();

        // Create the CategorieAyantDroit
        CategorieAyantDroitDTO categorieAyantDroitDTO = categorieAyantDroitMapper.toDto(categorieAyantDroit);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCategorieAyantDroitMockMvc.perform(put("/api/categorie-ayant-droits").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(categorieAyantDroitDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CategorieAyantDroit in the database
        List<CategorieAyantDroit> categorieAyantDroitList = categorieAyantDroitRepository.findAll();
        assertThat(categorieAyantDroitList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCategorieAyantDroit() throws Exception {
        // Initialize the database
        categorieAyantDroitRepository.saveAndFlush(categorieAyantDroit);

        int databaseSizeBeforeDelete = categorieAyantDroitRepository.findAll().size();

        // Delete the categorieAyantDroit
        restCategorieAyantDroitMockMvc.perform(delete("/api/categorie-ayant-droits/{id}", categorieAyantDroit.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CategorieAyantDroit> categorieAyantDroitList = categorieAyantDroitRepository.findAll();
        assertThat(categorieAyantDroitList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
