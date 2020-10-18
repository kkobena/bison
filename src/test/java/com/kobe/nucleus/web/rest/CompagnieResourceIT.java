package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.NucleusApp;
import com.kobe.nucleus.domain.Compagnie;
import com.kobe.nucleus.repository.CompagnieRepository;
import com.kobe.nucleus.service.CompagnieService;
import com.kobe.nucleus.service.dto.CompagnieDTO;
import com.kobe.nucleus.service.mapper.CompagnieMapper;

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
 * Integration tests for the {@link CompagnieResource} REST controller.
 */
@SpringBootTest(classes = NucleusApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CompagnieResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    private static final Status DEFAULT_STATUS = Status.ACTIVE;
    private static final Status UPDATED_STATUS = Status.ENATTENTE;

    @Autowired
    private CompagnieRepository compagnieRepository;

    @Autowired
    private CompagnieMapper compagnieMapper;

    @Autowired
    private CompagnieService compagnieService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCompagnieMockMvc;

    private Compagnie compagnie;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Compagnie createEntity(EntityManager em) {
        Compagnie compagnie = new Compagnie()
            .libelle(DEFAULT_LIBELLE)
            .status(DEFAULT_STATUS);
        return compagnie;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Compagnie createUpdatedEntity(EntityManager em) {
        Compagnie compagnie = new Compagnie()
            .libelle(UPDATED_LIBELLE)
            .status(UPDATED_STATUS);
        return compagnie;
    }

    @BeforeEach
    public void initTest() {
        compagnie = createEntity(em);
    }

    @Test
    @Transactional
    public void createCompagnie() throws Exception {
        int databaseSizeBeforeCreate = compagnieRepository.findAll().size();
        // Create the Compagnie
        CompagnieDTO compagnieDTO = compagnieMapper.toDto(compagnie);
        restCompagnieMockMvc.perform(post("/api/compagnies").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(compagnieDTO)))
            .andExpect(status().isCreated());

        // Validate the Compagnie in the database
        List<Compagnie> compagnieList = compagnieRepository.findAll();
        assertThat(compagnieList).hasSize(databaseSizeBeforeCreate + 1);
        Compagnie testCompagnie = compagnieList.get(compagnieList.size() - 1);
        assertThat(testCompagnie.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
        assertThat(testCompagnie.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createCompagnieWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = compagnieRepository.findAll().size();

        // Create the Compagnie with an existing ID
        compagnie.setId(1L);
        CompagnieDTO compagnieDTO = compagnieMapper.toDto(compagnie);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCompagnieMockMvc.perform(post("/api/compagnies").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(compagnieDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Compagnie in the database
        List<Compagnie> compagnieList = compagnieRepository.findAll();
        assertThat(compagnieList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLibelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = compagnieRepository.findAll().size();
        // set the field null
        compagnie.setLibelle(null);

        // Create the Compagnie, which fails.
        CompagnieDTO compagnieDTO = compagnieMapper.toDto(compagnie);


        restCompagnieMockMvc.perform(post("/api/compagnies").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(compagnieDTO)))
            .andExpect(status().isBadRequest());

        List<Compagnie> compagnieList = compagnieRepository.findAll();
        assertThat(compagnieList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = compagnieRepository.findAll().size();
        // set the field null
        compagnie.setStatus(null);

        // Create the Compagnie, which fails.
        CompagnieDTO compagnieDTO = compagnieMapper.toDto(compagnie);


        restCompagnieMockMvc.perform(post("/api/compagnies").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(compagnieDTO)))
            .andExpect(status().isBadRequest());

        List<Compagnie> compagnieList = compagnieRepository.findAll();
        assertThat(compagnieList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCompagnies() throws Exception {
        // Initialize the database
        compagnieRepository.saveAndFlush(compagnie);

        // Get all the compagnieList
        restCompagnieMockMvc.perform(get("/api/compagnies?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(compagnie.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }
    
    @Test
    @Transactional
    public void getCompagnie() throws Exception {
        // Initialize the database
        compagnieRepository.saveAndFlush(compagnie);

        // Get the compagnie
        restCompagnieMockMvc.perform(get("/api/compagnies/{id}", compagnie.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(compagnie.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingCompagnie() throws Exception {
        // Get the compagnie
        restCompagnieMockMvc.perform(get("/api/compagnies/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCompagnie() throws Exception {
        // Initialize the database
        compagnieRepository.saveAndFlush(compagnie);

        int databaseSizeBeforeUpdate = compagnieRepository.findAll().size();

        // Update the compagnie
        Compagnie updatedCompagnie = compagnieRepository.findById(compagnie.getId()).get();
        // Disconnect from session so that the updates on updatedCompagnie are not directly saved in db
        em.detach(updatedCompagnie);
        updatedCompagnie
            .libelle(UPDATED_LIBELLE)
            .status(UPDATED_STATUS);
        CompagnieDTO compagnieDTO = compagnieMapper.toDto(updatedCompagnie);

        restCompagnieMockMvc.perform(put("/api/compagnies").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(compagnieDTO)))
            .andExpect(status().isOk());

        // Validate the Compagnie in the database
        List<Compagnie> compagnieList = compagnieRepository.findAll();
        assertThat(compagnieList).hasSize(databaseSizeBeforeUpdate);
        Compagnie testCompagnie = compagnieList.get(compagnieList.size() - 1);
        assertThat(testCompagnie.getLibelle()).isEqualTo(UPDATED_LIBELLE);
        assertThat(testCompagnie.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingCompagnie() throws Exception {
        int databaseSizeBeforeUpdate = compagnieRepository.findAll().size();

        // Create the Compagnie
        CompagnieDTO compagnieDTO = compagnieMapper.toDto(compagnie);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCompagnieMockMvc.perform(put("/api/compagnies").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(compagnieDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Compagnie in the database
        List<Compagnie> compagnieList = compagnieRepository.findAll();
        assertThat(compagnieList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCompagnie() throws Exception {
        // Initialize the database
        compagnieRepository.saveAndFlush(compagnie);

        int databaseSizeBeforeDelete = compagnieRepository.findAll().size();

        // Delete the compagnie
        restCompagnieMockMvc.perform(delete("/api/compagnies/{id}", compagnie.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Compagnie> compagnieList = compagnieRepository.findAll();
        assertThat(compagnieList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
