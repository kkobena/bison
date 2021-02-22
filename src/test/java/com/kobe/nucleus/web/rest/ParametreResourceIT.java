package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.NucleusApp;
import com.kobe.nucleus.domain.Parametre;
import com.kobe.nucleus.repository.ParametreRepository;

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
 * Integration tests for the {@link ParametreResource} REST controller.
 */
@SpringBootTest(classes = NucleusApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ParametreResourceIT {

    private static final String DEFAULT_KEY = "AAAAAAAAAA";
    private static final String UPDATED_KEY = "BBBBBBBBBB";

    private static final String DEFAULT_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_VALUE = "BBBBBBBBBB";

    private static final String DEFAULT_DTYPE = "AAAAAAAAAA";
    private static final String UPDATED_DTYPE = "BBBBBBBBBB";

    @Autowired
    private ParametreRepository parametreRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restParametreMockMvc;

    private Parametre parametre;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Parametre createEntity(EntityManager em) {
        Parametre parametre = new Parametre()
            .key(DEFAULT_KEY)
            .value(DEFAULT_VALUE)
            .dtype(DEFAULT_DTYPE);
        return parametre;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Parametre createUpdatedEntity(EntityManager em) {
        Parametre parametre = new Parametre()
            .key(UPDATED_KEY)
            .value(UPDATED_VALUE)
            .dtype(UPDATED_DTYPE);
        return parametre;
    }

    @BeforeEach
    public void initTest() {
        parametre = createEntity(em);
    }

    @Test
    @Transactional
    public void createParametre() throws Exception {
        int databaseSizeBeforeCreate = parametreRepository.findAll().size();
        // Create the Parametre
        restParametreMockMvc.perform(post("/api/parametres").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(parametre)))
            .andExpect(status().isCreated());

        // Validate the Parametre in the database
        List<Parametre> parametreList = parametreRepository.findAll();
        assertThat(parametreList).hasSize(databaseSizeBeforeCreate + 1);
        Parametre testParametre = parametreList.get(parametreList.size() - 1);
        assertThat(testParametre.getKey()).isEqualTo(DEFAULT_KEY);
        assertThat(testParametre.getValue()).isEqualTo(DEFAULT_VALUE);
        assertThat(testParametre.getDtype()).isEqualTo(DEFAULT_DTYPE);
    }

    @Test
    @Transactional
    public void createParametreWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = parametreRepository.findAll().size();

        // Create the Parametre with an existing ID
        parametre.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restParametreMockMvc.perform(post("/api/parametres").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(parametre)))
            .andExpect(status().isBadRequest());

        // Validate the Parametre in the database
        List<Parametre> parametreList = parametreRepository.findAll();
        assertThat(parametreList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkKeyIsRequired() throws Exception {
        int databaseSizeBeforeTest = parametreRepository.findAll().size();
        // set the field null
        parametre.setKey(null);

        // Create the Parametre, which fails.


        restParametreMockMvc.perform(post("/api/parametres").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(parametre)))
            .andExpect(status().isBadRequest());

        List<Parametre> parametreList = parametreRepository.findAll();
        assertThat(parametreList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkValueIsRequired() throws Exception {
        int databaseSizeBeforeTest = parametreRepository.findAll().size();
        // set the field null
        parametre.setValue(null);

        // Create the Parametre, which fails.


        restParametreMockMvc.perform(post("/api/parametres").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(parametre)))
            .andExpect(status().isBadRequest());

        List<Parametre> parametreList = parametreRepository.findAll();
        assertThat(parametreList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDtypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = parametreRepository.findAll().size();
        // set the field null
        parametre.setDtype(null);

        // Create the Parametre, which fails.


        restParametreMockMvc.perform(post("/api/parametres").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(parametre)))
            .andExpect(status().isBadRequest());

        List<Parametre> parametreList = parametreRepository.findAll();
        assertThat(parametreList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllParametres() throws Exception {
        // Initialize the database
        parametreRepository.saveAndFlush(parametre);

        // Get all the parametreList
        restParametreMockMvc.perform(get("/api/parametres?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(parametre.getId().intValue())))
            .andExpect(jsonPath("$.[*].key").value(hasItem(DEFAULT_KEY)))
            .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE)))
            .andExpect(jsonPath("$.[*].dtype").value(hasItem(DEFAULT_DTYPE)));
    }
    
    @Test
    @Transactional
    public void getParametre() throws Exception {
        // Initialize the database
        parametreRepository.saveAndFlush(parametre);

        // Get the parametre
        restParametreMockMvc.perform(get("/api/parametres/{id}", parametre.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(parametre.getId().intValue()))
            .andExpect(jsonPath("$.key").value(DEFAULT_KEY))
            .andExpect(jsonPath("$.value").value(DEFAULT_VALUE))
            .andExpect(jsonPath("$.dtype").value(DEFAULT_DTYPE));
    }
    @Test
    @Transactional
    public void getNonExistingParametre() throws Exception {
        // Get the parametre
        restParametreMockMvc.perform(get("/api/parametres/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateParametre() throws Exception {
        // Initialize the database
        parametreRepository.saveAndFlush(parametre);

        int databaseSizeBeforeUpdate = parametreRepository.findAll().size();

        // Update the parametre
        Parametre updatedParametre = parametreRepository.findById(parametre.getId()).get();
        // Disconnect from session so that the updates on updatedParametre are not directly saved in db
        em.detach(updatedParametre);
        updatedParametre
            .key(UPDATED_KEY)
            .value(UPDATED_VALUE)
            .dtype(UPDATED_DTYPE);

        restParametreMockMvc.perform(put("/api/parametres").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedParametre)))
            .andExpect(status().isOk());

        // Validate the Parametre in the database
        List<Parametre> parametreList = parametreRepository.findAll();
        assertThat(parametreList).hasSize(databaseSizeBeforeUpdate);
        Parametre testParametre = parametreList.get(parametreList.size() - 1);
        assertThat(testParametre.getKey()).isEqualTo(UPDATED_KEY);
        assertThat(testParametre.getValue()).isEqualTo(UPDATED_VALUE);
        assertThat(testParametre.getDtype()).isEqualTo(UPDATED_DTYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingParametre() throws Exception {
        int databaseSizeBeforeUpdate = parametreRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restParametreMockMvc.perform(put("/api/parametres").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(parametre)))
            .andExpect(status().isBadRequest());

        // Validate the Parametre in the database
        List<Parametre> parametreList = parametreRepository.findAll();
        assertThat(parametreList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteParametre() throws Exception {
        // Initialize the database
        parametreRepository.saveAndFlush(parametre);

        int databaseSizeBeforeDelete = parametreRepository.findAll().size();

        // Delete the parametre
        restParametreMockMvc.perform(delete("/api/parametres/{id}", parametre.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Parametre> parametreList = parametreRepository.findAll();
        assertThat(parametreList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
