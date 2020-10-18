package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.NucleusApp;
import com.kobe.nucleus.domain.FormProduit;
import com.kobe.nucleus.repository.FormProduitRepository;
import com.kobe.nucleus.service.FormProduitService;
import com.kobe.nucleus.service.dto.FormProduitDTO;
import com.kobe.nucleus.service.mapper.FormProduitMapper;

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
 * Integration tests for the {@link FormProduitResource} REST controller.
 */
@SpringBootTest(classes = NucleusApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class FormProduitResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    private static final Status DEFAULT_STATUS = Status.ACTIVE;
    private static final Status UPDATED_STATUS = Status.ENATTENTE;

    @Autowired
    private FormProduitRepository formProduitRepository;

    @Autowired
    private FormProduitMapper formProduitMapper;

    @Autowired
    private FormProduitService formProduitService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFormProduitMockMvc;

    private FormProduit formProduit;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FormProduit createEntity(EntityManager em) {
        FormProduit formProduit = new FormProduit()
            .code(DEFAULT_CODE)
            .libelle(DEFAULT_LIBELLE)
            ;
        return formProduit;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FormProduit createUpdatedEntity(EntityManager em) {
        FormProduit formProduit = new FormProduit()
            .code(UPDATED_CODE)
            .libelle(UPDATED_LIBELLE)
           ;
        return formProduit;
    }

    @BeforeEach
    public void initTest() {
        formProduit = createEntity(em);
    }

    @Test
    @Transactional
    public void createFormProduit() throws Exception {
        int databaseSizeBeforeCreate = formProduitRepository.findAll().size();
        // Create the FormProduit
        FormProduitDTO formProduitDTO = formProduitMapper.toDto(formProduit);
        restFormProduitMockMvc.perform(post("/api/form-produits").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(formProduitDTO)))
            .andExpect(status().isCreated());

        // Validate the FormProduit in the database
        List<FormProduit> formProduitList = formProduitRepository.findAll();
        assertThat(formProduitList).hasSize(databaseSizeBeforeCreate + 1);
        FormProduit testFormProduit = formProduitList.get(formProduitList.size() - 1);
        assertThat(testFormProduit.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testFormProduit.getLibelle()).isEqualTo(DEFAULT_LIBELLE);

    }

    @Test
    @Transactional
    public void createFormProduitWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = formProduitRepository.findAll().size();

        // Create the FormProduit with an existing ID
        formProduit.setId(1L);
        FormProduitDTO formProduitDTO = formProduitMapper.toDto(formProduit);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFormProduitMockMvc.perform(post("/api/form-produits").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(formProduitDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FormProduit in the database
        List<FormProduit> formProduitList = formProduitRepository.findAll();
        assertThat(formProduitList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = formProduitRepository.findAll().size();
        // set the field null
        formProduit.setCode(null);

        // Create the FormProduit, which fails.
        FormProduitDTO formProduitDTO = formProduitMapper.toDto(formProduit);


        restFormProduitMockMvc.perform(post("/api/form-produits").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(formProduitDTO)))
            .andExpect(status().isBadRequest());

        List<FormProduit> formProduitList = formProduitRepository.findAll();
        assertThat(formProduitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLibelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = formProduitRepository.findAll().size();
        // set the field null
        formProduit.setLibelle(null);

        // Create the FormProduit, which fails.
        FormProduitDTO formProduitDTO = formProduitMapper.toDto(formProduit);


        restFormProduitMockMvc.perform(post("/api/form-produits").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(formProduitDTO)))
            .andExpect(status().isBadRequest());

        List<FormProduit> formProduitList = formProduitRepository.findAll();
        assertThat(formProduitList).hasSize(databaseSizeBeforeTest);
    }


    @Test
    @Transactional
    public void getAllFormProduits() throws Exception {
        // Initialize the database
        formProduitRepository.saveAndFlush(formProduit);

        // Get all the formProduitList
        restFormProduitMockMvc.perform(get("/api/form-produits?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(formProduit.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }

    @Test
    @Transactional
    public void getFormProduit() throws Exception {
        // Initialize the database
        formProduitRepository.saveAndFlush(formProduit);

        // Get the formProduit
        restFormProduitMockMvc.perform(get("/api/form-produits/{id}", formProduit.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(formProduit.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingFormProduit() throws Exception {
        // Get the formProduit
        restFormProduitMockMvc.perform(get("/api/form-produits/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFormProduit() throws Exception {
        // Initialize the database
        formProduitRepository.saveAndFlush(formProduit);

        int databaseSizeBeforeUpdate = formProduitRepository.findAll().size();

        // Update the formProduit
        FormProduit updatedFormProduit = formProduitRepository.findById(formProduit.getId()).get();
        // Disconnect from session so that the updates on updatedFormProduit are not directly saved in db
        em.detach(updatedFormProduit);
        updatedFormProduit
            .code(UPDATED_CODE)
            .libelle(UPDATED_LIBELLE)
            ;
        FormProduitDTO formProduitDTO = formProduitMapper.toDto(updatedFormProduit);

        restFormProduitMockMvc.perform(put("/api/form-produits").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(formProduitDTO)))
            .andExpect(status().isOk());

        // Validate the FormProduit in the database
        List<FormProduit> formProduitList = formProduitRepository.findAll();
        assertThat(formProduitList).hasSize(databaseSizeBeforeUpdate);
        FormProduit testFormProduit = formProduitList.get(formProduitList.size() - 1);
        assertThat(testFormProduit.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testFormProduit.getLibelle()).isEqualTo(UPDATED_LIBELLE);

    }

    @Test
    @Transactional
    public void updateNonExistingFormProduit() throws Exception {
        int databaseSizeBeforeUpdate = formProduitRepository.findAll().size();

        // Create the FormProduit
        FormProduitDTO formProduitDTO = formProduitMapper.toDto(formProduit);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFormProduitMockMvc.perform(put("/api/form-produits").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(formProduitDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FormProduit in the database
        List<FormProduit> formProduitList = formProduitRepository.findAll();
        assertThat(formProduitList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFormProduit() throws Exception {
        // Initialize the database
        formProduitRepository.saveAndFlush(formProduit);

        int databaseSizeBeforeDelete = formProduitRepository.findAll().size();

        // Delete the formProduit
        restFormProduitMockMvc.perform(delete("/api/form-produits/{id}", formProduit.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<FormProduit> formProduitList = formProduitRepository.findAll();
        assertThat(formProduitList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
