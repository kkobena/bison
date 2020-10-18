package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.NucleusApp;
import com.kobe.nucleus.domain.DetailsAjustement;
import com.kobe.nucleus.domain.Produit;
import com.kobe.nucleus.repository.DetailsAjustementRepository;
import com.kobe.nucleus.service.DetailsAjustementService;
import com.kobe.nucleus.service.dto.DetailsAjustementDTO;
import com.kobe.nucleus.service.mapper.DetailsAjustementMapper;

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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link DetailsAjustementResource} REST controller.
 */
@SpringBootTest(classes = NucleusApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class DetailsAjustementResourceIT {

    private static final Integer DEFAULT_QTE_INIT = 1;
    private static final Integer UPDATED_QTE_INIT = 2;

    private static final Integer DEFAULT_QTE_FINALE = 1;
    private static final Integer UPDATED_QTE_FINALE = 2;

    private static final Integer DEFAULT_QTE_AJUSTE = 1;
    private static final Integer UPDATED_QTE_AJUSTE = 2;

    private static final Instant DEFAULT_CREATED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_UPDATED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private DetailsAjustementRepository detailsAjustementRepository;

    @Autowired
    private DetailsAjustementMapper detailsAjustementMapper;

    @Autowired
    private DetailsAjustementService detailsAjustementService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDetailsAjustementMockMvc;

    private DetailsAjustement detailsAjustement;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DetailsAjustement createEntity(EntityManager em) {
        DetailsAjustement detailsAjustement = new DetailsAjustement()
            .qteInit(DEFAULT_QTE_INIT)
            .qteFinale(DEFAULT_QTE_FINALE)
            .qteAjuste(DEFAULT_QTE_AJUSTE)
            .createdAt(DEFAULT_CREATED_AT)
            .updatedAt(DEFAULT_UPDATED_AT);
        // Add required entity
        Produit produit;
        if (TestUtil.findAll(em, Produit.class).isEmpty()) {
            produit = ProduitResourceIT.createEntity(em);
            em.persist(produit);
            em.flush();
        } else {
            produit = TestUtil.findAll(em, Produit.class).get(0);
        }
        detailsAjustement.setProduit(produit);
        return detailsAjustement;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DetailsAjustement createUpdatedEntity(EntityManager em) {
        DetailsAjustement detailsAjustement = new DetailsAjustement()
            .qteInit(UPDATED_QTE_INIT)
            .qteFinale(UPDATED_QTE_FINALE)
            .qteAjuste(UPDATED_QTE_AJUSTE)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);
        // Add required entity
        Produit produit;
        if (TestUtil.findAll(em, Produit.class).isEmpty()) {
            produit = ProduitResourceIT.createUpdatedEntity(em);
            em.persist(produit);
            em.flush();
        } else {
            produit = TestUtil.findAll(em, Produit.class).get(0);
        }
        detailsAjustement.setProduit(produit);
        return detailsAjustement;
    }

    @BeforeEach
    public void initTest() {
        detailsAjustement = createEntity(em);
    }

    @Test
    @Transactional
    public void createDetailsAjustement() throws Exception {
        int databaseSizeBeforeCreate = detailsAjustementRepository.findAll().size();
        // Create the DetailsAjustement
        DetailsAjustementDTO detailsAjustementDTO = detailsAjustementMapper.toDto(detailsAjustement);
        restDetailsAjustementMockMvc.perform(post("/api/details-ajustements").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(detailsAjustementDTO)))
            .andExpect(status().isCreated());

        // Validate the DetailsAjustement in the database
        List<DetailsAjustement> detailsAjustementList = detailsAjustementRepository.findAll();
        assertThat(detailsAjustementList).hasSize(databaseSizeBeforeCreate + 1);
        DetailsAjustement testDetailsAjustement = detailsAjustementList.get(detailsAjustementList.size() - 1);
        assertThat(testDetailsAjustement.getQteInit()).isEqualTo(DEFAULT_QTE_INIT);
        assertThat(testDetailsAjustement.getQteFinale()).isEqualTo(DEFAULT_QTE_FINALE);
        assertThat(testDetailsAjustement.getQteAjuste()).isEqualTo(DEFAULT_QTE_AJUSTE);
        assertThat(testDetailsAjustement.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testDetailsAjustement.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
    }

    @Test
    @Transactional
    public void createDetailsAjustementWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = detailsAjustementRepository.findAll().size();

        // Create the DetailsAjustement with an existing ID
        detailsAjustement.setId(1L);
        DetailsAjustementDTO detailsAjustementDTO = detailsAjustementMapper.toDto(detailsAjustement);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDetailsAjustementMockMvc.perform(post("/api/details-ajustements").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(detailsAjustementDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DetailsAjustement in the database
        List<DetailsAjustement> detailsAjustementList = detailsAjustementRepository.findAll();
        assertThat(detailsAjustementList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkQteInitIsRequired() throws Exception {
        int databaseSizeBeforeTest = detailsAjustementRepository.findAll().size();
        // set the field null
        detailsAjustement.setQteInit(null);

        // Create the DetailsAjustement, which fails.
        DetailsAjustementDTO detailsAjustementDTO = detailsAjustementMapper.toDto(detailsAjustement);


        restDetailsAjustementMockMvc.perform(post("/api/details-ajustements").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(detailsAjustementDTO)))
            .andExpect(status().isBadRequest());

        List<DetailsAjustement> detailsAjustementList = detailsAjustementRepository.findAll();
        assertThat(detailsAjustementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQteFinaleIsRequired() throws Exception {
        int databaseSizeBeforeTest = detailsAjustementRepository.findAll().size();
        // set the field null
        detailsAjustement.setQteFinale(null);

        // Create the DetailsAjustement, which fails.
        DetailsAjustementDTO detailsAjustementDTO = detailsAjustementMapper.toDto(detailsAjustement);


        restDetailsAjustementMockMvc.perform(post("/api/details-ajustements").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(detailsAjustementDTO)))
            .andExpect(status().isBadRequest());

        List<DetailsAjustement> detailsAjustementList = detailsAjustementRepository.findAll();
        assertThat(detailsAjustementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQteAjusteIsRequired() throws Exception {
        int databaseSizeBeforeTest = detailsAjustementRepository.findAll().size();
        // set the field null
        detailsAjustement.setQteAjuste(null);

        // Create the DetailsAjustement, which fails.
        DetailsAjustementDTO detailsAjustementDTO = detailsAjustementMapper.toDto(detailsAjustement);


        restDetailsAjustementMockMvc.perform(post("/api/details-ajustements").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(detailsAjustementDTO)))
            .andExpect(status().isBadRequest());

        List<DetailsAjustement> detailsAjustementList = detailsAjustementRepository.findAll();
        assertThat(detailsAjustementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = detailsAjustementRepository.findAll().size();
        // set the field null
        detailsAjustement.setCreatedAt(null);

        // Create the DetailsAjustement, which fails.
        DetailsAjustementDTO detailsAjustementDTO = detailsAjustementMapper.toDto(detailsAjustement);


        restDetailsAjustementMockMvc.perform(post("/api/details-ajustements").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(detailsAjustementDTO)))
            .andExpect(status().isBadRequest());

        List<DetailsAjustement> detailsAjustementList = detailsAjustementRepository.findAll();
        assertThat(detailsAjustementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUpdatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = detailsAjustementRepository.findAll().size();
        // set the field null
        detailsAjustement.setUpdatedAt(null);

        // Create the DetailsAjustement, which fails.
        DetailsAjustementDTO detailsAjustementDTO = detailsAjustementMapper.toDto(detailsAjustement);


        restDetailsAjustementMockMvc.perform(post("/api/details-ajustements").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(detailsAjustementDTO)))
            .andExpect(status().isBadRequest());

        List<DetailsAjustement> detailsAjustementList = detailsAjustementRepository.findAll();
        assertThat(detailsAjustementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDetailsAjustements() throws Exception {
        // Initialize the database
        detailsAjustementRepository.saveAndFlush(detailsAjustement);

        // Get all the detailsAjustementList
        restDetailsAjustementMockMvc.perform(get("/api/details-ajustements?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(detailsAjustement.getId().intValue())))
            .andExpect(jsonPath("$.[*].qteInit").value(hasItem(DEFAULT_QTE_INIT)))
            .andExpect(jsonPath("$.[*].qteFinale").value(hasItem(DEFAULT_QTE_FINALE)))
            .andExpect(jsonPath("$.[*].qteAjuste").value(hasItem(DEFAULT_QTE_AJUSTE)))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(DEFAULT_UPDATED_AT.toString())));
    }
    
    @Test
    @Transactional
    public void getDetailsAjustement() throws Exception {
        // Initialize the database
        detailsAjustementRepository.saveAndFlush(detailsAjustement);

        // Get the detailsAjustement
        restDetailsAjustementMockMvc.perform(get("/api/details-ajustements/{id}", detailsAjustement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(detailsAjustement.getId().intValue()))
            .andExpect(jsonPath("$.qteInit").value(DEFAULT_QTE_INIT))
            .andExpect(jsonPath("$.qteFinale").value(DEFAULT_QTE_FINALE))
            .andExpect(jsonPath("$.qteAjuste").value(DEFAULT_QTE_AJUSTE))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.updatedAt").value(DEFAULT_UPDATED_AT.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingDetailsAjustement() throws Exception {
        // Get the detailsAjustement
        restDetailsAjustementMockMvc.perform(get("/api/details-ajustements/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDetailsAjustement() throws Exception {
        // Initialize the database
        detailsAjustementRepository.saveAndFlush(detailsAjustement);

        int databaseSizeBeforeUpdate = detailsAjustementRepository.findAll().size();

        // Update the detailsAjustement
        DetailsAjustement updatedDetailsAjustement = detailsAjustementRepository.findById(detailsAjustement.getId()).get();
        // Disconnect from session so that the updates on updatedDetailsAjustement are not directly saved in db
        em.detach(updatedDetailsAjustement);
        updatedDetailsAjustement
            .qteInit(UPDATED_QTE_INIT)
            .qteFinale(UPDATED_QTE_FINALE)
            .qteAjuste(UPDATED_QTE_AJUSTE)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);
        DetailsAjustementDTO detailsAjustementDTO = detailsAjustementMapper.toDto(updatedDetailsAjustement);

        restDetailsAjustementMockMvc.perform(put("/api/details-ajustements").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(detailsAjustementDTO)))
            .andExpect(status().isOk());

        // Validate the DetailsAjustement in the database
        List<DetailsAjustement> detailsAjustementList = detailsAjustementRepository.findAll();
        assertThat(detailsAjustementList).hasSize(databaseSizeBeforeUpdate);
        DetailsAjustement testDetailsAjustement = detailsAjustementList.get(detailsAjustementList.size() - 1);
        assertThat(testDetailsAjustement.getQteInit()).isEqualTo(UPDATED_QTE_INIT);
        assertThat(testDetailsAjustement.getQteFinale()).isEqualTo(UPDATED_QTE_FINALE);
        assertThat(testDetailsAjustement.getQteAjuste()).isEqualTo(UPDATED_QTE_AJUSTE);
        assertThat(testDetailsAjustement.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testDetailsAjustement.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
    }

    @Test
    @Transactional
    public void updateNonExistingDetailsAjustement() throws Exception {
        int databaseSizeBeforeUpdate = detailsAjustementRepository.findAll().size();

        // Create the DetailsAjustement
        DetailsAjustementDTO detailsAjustementDTO = detailsAjustementMapper.toDto(detailsAjustement);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDetailsAjustementMockMvc.perform(put("/api/details-ajustements").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(detailsAjustementDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DetailsAjustement in the database
        List<DetailsAjustement> detailsAjustementList = detailsAjustementRepository.findAll();
        assertThat(detailsAjustementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDetailsAjustement() throws Exception {
        // Initialize the database
        detailsAjustementRepository.saveAndFlush(detailsAjustement);

        int databaseSizeBeforeDelete = detailsAjustementRepository.findAll().size();

        // Delete the detailsAjustement
        restDetailsAjustementMockMvc.perform(delete("/api/details-ajustements/{id}", detailsAjustement.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DetailsAjustement> detailsAjustementList = detailsAjustementRepository.findAll();
        assertThat(detailsAjustementList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
