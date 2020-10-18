package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.NucleusApp;
import com.kobe.nucleus.domain.RetourFournisseur;
import com.kobe.nucleus.repository.RetourFournisseurRepository;
import com.kobe.nucleus.service.RetourFournisseurService;
import com.kobe.nucleus.service.dto.RetourFournisseurDTO;
import com.kobe.nucleus.service.mapper.RetourFournisseurMapper;

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
import java.time.LocalDate;
import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.kobe.nucleus.domain.enumeration.RetourEnum;
/**
 * Integration tests for the {@link RetourFournisseurResource} REST controller.
 */
@SpringBootTest(classes = NucleusApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class RetourFournisseurResourceIT {

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ANSWERED = false;
    private static final Boolean UPDATED_ANSWERED = true;

    private static final RetourEnum DEFAULT_STATUS = RetourEnum.NONCOMFIRME;
    private static final RetourEnum UPDATED_STATUS = RetourEnum.CONFIRME;

    private static final Instant DEFAULT_CREATED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_UPDATED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final LocalDate DEFAULT_MVT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_MVT_DATE = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private RetourFournisseurRepository retourFournisseurRepository;

    @Autowired
    private RetourFournisseurMapper retourFournisseurMapper;

    @Autowired
    private RetourFournisseurService retourFournisseurService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRetourFournisseurMockMvc;

    private RetourFournisseur retourFournisseur;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RetourFournisseur createEntity(EntityManager em) {
        RetourFournisseur retourFournisseur = new RetourFournisseur()
            .description(DEFAULT_DESCRIPTION)
            .answered(DEFAULT_ANSWERED)
            .status(DEFAULT_STATUS)
            .createdAt(DEFAULT_CREATED_AT)
            .updatedAt(DEFAULT_UPDATED_AT)
            .mvtDate(DEFAULT_MVT_DATE);
        return retourFournisseur;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RetourFournisseur createUpdatedEntity(EntityManager em) {
        RetourFournisseur retourFournisseur = new RetourFournisseur()
            .description(UPDATED_DESCRIPTION)
            .answered(UPDATED_ANSWERED)
            .status(UPDATED_STATUS)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .mvtDate(UPDATED_MVT_DATE);
        return retourFournisseur;
    }

    @BeforeEach
    public void initTest() {
        retourFournisseur = createEntity(em);
    }

    @Test
    @Transactional
    public void createRetourFournisseur() throws Exception {
        int databaseSizeBeforeCreate = retourFournisseurRepository.findAll().size();
        // Create the RetourFournisseur
        RetourFournisseurDTO retourFournisseurDTO = retourFournisseurMapper.toDto(retourFournisseur);
        restRetourFournisseurMockMvc.perform(post("/api/retour-fournisseurs").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(retourFournisseurDTO)))
            .andExpect(status().isCreated());

        // Validate the RetourFournisseur in the database
        List<RetourFournisseur> retourFournisseurList = retourFournisseurRepository.findAll();
        assertThat(retourFournisseurList).hasSize(databaseSizeBeforeCreate + 1);
        RetourFournisseur testRetourFournisseur = retourFournisseurList.get(retourFournisseurList.size() - 1);
        assertThat(testRetourFournisseur.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testRetourFournisseur.isAnswered()).isEqualTo(DEFAULT_ANSWERED);
        assertThat(testRetourFournisseur.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testRetourFournisseur.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testRetourFournisseur.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testRetourFournisseur.getMvtDate()).isEqualTo(DEFAULT_MVT_DATE);
    }

    @Test
    @Transactional
    public void createRetourFournisseurWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = retourFournisseurRepository.findAll().size();

        // Create the RetourFournisseur with an existing ID
        retourFournisseur.setId(1L);
        RetourFournisseurDTO retourFournisseurDTO = retourFournisseurMapper.toDto(retourFournisseur);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRetourFournisseurMockMvc.perform(post("/api/retour-fournisseurs").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(retourFournisseurDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RetourFournisseur in the database
        List<RetourFournisseur> retourFournisseurList = retourFournisseurRepository.findAll();
        assertThat(retourFournisseurList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkAnsweredIsRequired() throws Exception {
        int databaseSizeBeforeTest = retourFournisseurRepository.findAll().size();
        // set the field null
        retourFournisseur.setAnswered(null);

        // Create the RetourFournisseur, which fails.
        RetourFournisseurDTO retourFournisseurDTO = retourFournisseurMapper.toDto(retourFournisseur);


        restRetourFournisseurMockMvc.perform(post("/api/retour-fournisseurs").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(retourFournisseurDTO)))
            .andExpect(status().isBadRequest());

        List<RetourFournisseur> retourFournisseurList = retourFournisseurRepository.findAll();
        assertThat(retourFournisseurList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = retourFournisseurRepository.findAll().size();
        // set the field null
        retourFournisseur.setCreatedAt(null);

        // Create the RetourFournisseur, which fails.
        RetourFournisseurDTO retourFournisseurDTO = retourFournisseurMapper.toDto(retourFournisseur);


        restRetourFournisseurMockMvc.perform(post("/api/retour-fournisseurs").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(retourFournisseurDTO)))
            .andExpect(status().isBadRequest());

        List<RetourFournisseur> retourFournisseurList = retourFournisseurRepository.findAll();
        assertThat(retourFournisseurList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUpdatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = retourFournisseurRepository.findAll().size();
        // set the field null
        retourFournisseur.setUpdatedAt(null);

        // Create the RetourFournisseur, which fails.
        RetourFournisseurDTO retourFournisseurDTO = retourFournisseurMapper.toDto(retourFournisseur);


        restRetourFournisseurMockMvc.perform(post("/api/retour-fournisseurs").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(retourFournisseurDTO)))
            .andExpect(status().isBadRequest());

        List<RetourFournisseur> retourFournisseurList = retourFournisseurRepository.findAll();
        assertThat(retourFournisseurList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMvtDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = retourFournisseurRepository.findAll().size();
        // set the field null
        retourFournisseur.setMvtDate(null);

        // Create the RetourFournisseur, which fails.
        RetourFournisseurDTO retourFournisseurDTO = retourFournisseurMapper.toDto(retourFournisseur);


        restRetourFournisseurMockMvc.perform(post("/api/retour-fournisseurs").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(retourFournisseurDTO)))
            .andExpect(status().isBadRequest());

        List<RetourFournisseur> retourFournisseurList = retourFournisseurRepository.findAll();
        assertThat(retourFournisseurList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRetourFournisseurs() throws Exception {
        // Initialize the database
        retourFournisseurRepository.saveAndFlush(retourFournisseur);

        // Get all the retourFournisseurList
        restRetourFournisseurMockMvc.perform(get("/api/retour-fournisseurs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(retourFournisseur.getId().intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].answered").value(hasItem(DEFAULT_ANSWERED.booleanValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(DEFAULT_UPDATED_AT.toString())))
            .andExpect(jsonPath("$.[*].mvtDate").value(hasItem(DEFAULT_MVT_DATE.toString())));
    }
    
    @Test
    @Transactional
    public void getRetourFournisseur() throws Exception {
        // Initialize the database
        retourFournisseurRepository.saveAndFlush(retourFournisseur);

        // Get the retourFournisseur
        restRetourFournisseurMockMvc.perform(get("/api/retour-fournisseurs/{id}", retourFournisseur.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(retourFournisseur.getId().intValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.answered").value(DEFAULT_ANSWERED.booleanValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.updatedAt").value(DEFAULT_UPDATED_AT.toString()))
            .andExpect(jsonPath("$.mvtDate").value(DEFAULT_MVT_DATE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingRetourFournisseur() throws Exception {
        // Get the retourFournisseur
        restRetourFournisseurMockMvc.perform(get("/api/retour-fournisseurs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRetourFournisseur() throws Exception {
        // Initialize the database
        retourFournisseurRepository.saveAndFlush(retourFournisseur);

        int databaseSizeBeforeUpdate = retourFournisseurRepository.findAll().size();

        // Update the retourFournisseur
        RetourFournisseur updatedRetourFournisseur = retourFournisseurRepository.findById(retourFournisseur.getId()).get();
        // Disconnect from session so that the updates on updatedRetourFournisseur are not directly saved in db
        em.detach(updatedRetourFournisseur);
        updatedRetourFournisseur
            .description(UPDATED_DESCRIPTION)
            .answered(UPDATED_ANSWERED)
            .status(UPDATED_STATUS)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .mvtDate(UPDATED_MVT_DATE);
        RetourFournisseurDTO retourFournisseurDTO = retourFournisseurMapper.toDto(updatedRetourFournisseur);

        restRetourFournisseurMockMvc.perform(put("/api/retour-fournisseurs").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(retourFournisseurDTO)))
            .andExpect(status().isOk());

        // Validate the RetourFournisseur in the database
        List<RetourFournisseur> retourFournisseurList = retourFournisseurRepository.findAll();
        assertThat(retourFournisseurList).hasSize(databaseSizeBeforeUpdate);
        RetourFournisseur testRetourFournisseur = retourFournisseurList.get(retourFournisseurList.size() - 1);
        assertThat(testRetourFournisseur.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testRetourFournisseur.isAnswered()).isEqualTo(UPDATED_ANSWERED);
        assertThat(testRetourFournisseur.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testRetourFournisseur.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testRetourFournisseur.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testRetourFournisseur.getMvtDate()).isEqualTo(UPDATED_MVT_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingRetourFournisseur() throws Exception {
        int databaseSizeBeforeUpdate = retourFournisseurRepository.findAll().size();

        // Create the RetourFournisseur
        RetourFournisseurDTO retourFournisseurDTO = retourFournisseurMapper.toDto(retourFournisseur);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRetourFournisseurMockMvc.perform(put("/api/retour-fournisseurs").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(retourFournisseurDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RetourFournisseur in the database
        List<RetourFournisseur> retourFournisseurList = retourFournisseurRepository.findAll();
        assertThat(retourFournisseurList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRetourFournisseur() throws Exception {
        // Initialize the database
        retourFournisseurRepository.saveAndFlush(retourFournisseur);

        int databaseSizeBeforeDelete = retourFournisseurRepository.findAll().size();

        // Delete the retourFournisseur
        restRetourFournisseurMockMvc.perform(delete("/api/retour-fournisseurs/{id}", retourFournisseur.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RetourFournisseur> retourFournisseurList = retourFournisseurRepository.findAll();
        assertThat(retourFournisseurList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
