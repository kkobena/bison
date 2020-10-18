package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.NucleusApp;
import com.kobe.nucleus.domain.DetailsInventaire;
import com.kobe.nucleus.repository.DetailsInventaireRepository;
import com.kobe.nucleus.service.DetailsInventaireService;
import com.kobe.nucleus.service.dto.DetailsInventaireDTO;
import com.kobe.nucleus.service.mapper.DetailsInventaireMapper;

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
 * Integration tests for the {@link DetailsInventaireResource} REST controller.
 */
@SpringBootTest(classes = NucleusApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class DetailsInventaireResourceIT {

    private static final Integer DEFAULT_QTY = 1;
    private static final Integer UPDATED_QTY = 2;

    private static final Integer DEFAULT_QTY_INIT = 1;
    private static final Integer UPDATED_QTY_INIT = 2;

    private static final Instant DEFAULT_CREATED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_UPDATED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Boolean DEFAULT_IS_UPDATED = false;
    private static final Boolean UPDATED_IS_UPDATED = true;

    @Autowired
    private DetailsInventaireRepository detailsInventaireRepository;

    @Autowired
    private DetailsInventaireMapper detailsInventaireMapper;

    @Autowired
    private DetailsInventaireService detailsInventaireService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDetailsInventaireMockMvc;

    private DetailsInventaire detailsInventaire;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DetailsInventaire createEntity(EntityManager em) {
        DetailsInventaire detailsInventaire = new DetailsInventaire()
            .qty(DEFAULT_QTY)
            .qtyInit(DEFAULT_QTY_INIT)
            .createdAt(DEFAULT_CREATED_AT)
            .updatedAt(DEFAULT_UPDATED_AT)
            .isUpdated(DEFAULT_IS_UPDATED);
        return detailsInventaire;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DetailsInventaire createUpdatedEntity(EntityManager em) {
        DetailsInventaire detailsInventaire = new DetailsInventaire()
            .qty(UPDATED_QTY)
            .qtyInit(UPDATED_QTY_INIT)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .isUpdated(UPDATED_IS_UPDATED);
        return detailsInventaire;
    }

    @BeforeEach
    public void initTest() {
        detailsInventaire = createEntity(em);
    }

    @Test
    @Transactional
    public void createDetailsInventaire() throws Exception {
        int databaseSizeBeforeCreate = detailsInventaireRepository.findAll().size();
        // Create the DetailsInventaire
        DetailsInventaireDTO detailsInventaireDTO = detailsInventaireMapper.toDto(detailsInventaire);
        restDetailsInventaireMockMvc.perform(post("/api/details-inventaires").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(detailsInventaireDTO)))
            .andExpect(status().isCreated());

        // Validate the DetailsInventaire in the database
        List<DetailsInventaire> detailsInventaireList = detailsInventaireRepository.findAll();
        assertThat(detailsInventaireList).hasSize(databaseSizeBeforeCreate + 1);
        DetailsInventaire testDetailsInventaire = detailsInventaireList.get(detailsInventaireList.size() - 1);
        assertThat(testDetailsInventaire.getQty()).isEqualTo(DEFAULT_QTY);
        assertThat(testDetailsInventaire.getQtyInit()).isEqualTo(DEFAULT_QTY_INIT);
        assertThat(testDetailsInventaire.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testDetailsInventaire.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testDetailsInventaire.isIsUpdated()).isEqualTo(DEFAULT_IS_UPDATED);
    }

    @Test
    @Transactional
    public void createDetailsInventaireWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = detailsInventaireRepository.findAll().size();

        // Create the DetailsInventaire with an existing ID
        detailsInventaire.setId(1L);
        DetailsInventaireDTO detailsInventaireDTO = detailsInventaireMapper.toDto(detailsInventaire);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDetailsInventaireMockMvc.perform(post("/api/details-inventaires").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(detailsInventaireDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DetailsInventaire in the database
        List<DetailsInventaire> detailsInventaireList = detailsInventaireRepository.findAll();
        assertThat(detailsInventaireList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkQtyIsRequired() throws Exception {
        int databaseSizeBeforeTest = detailsInventaireRepository.findAll().size();
        // set the field null
        detailsInventaire.setQty(null);

        // Create the DetailsInventaire, which fails.
        DetailsInventaireDTO detailsInventaireDTO = detailsInventaireMapper.toDto(detailsInventaire);


        restDetailsInventaireMockMvc.perform(post("/api/details-inventaires").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(detailsInventaireDTO)))
            .andExpect(status().isBadRequest());

        List<DetailsInventaire> detailsInventaireList = detailsInventaireRepository.findAll();
        assertThat(detailsInventaireList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQtyInitIsRequired() throws Exception {
        int databaseSizeBeforeTest = detailsInventaireRepository.findAll().size();
        // set the field null
        detailsInventaire.setQtyInit(null);

        // Create the DetailsInventaire, which fails.
        DetailsInventaireDTO detailsInventaireDTO = detailsInventaireMapper.toDto(detailsInventaire);


        restDetailsInventaireMockMvc.perform(post("/api/details-inventaires").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(detailsInventaireDTO)))
            .andExpect(status().isBadRequest());

        List<DetailsInventaire> detailsInventaireList = detailsInventaireRepository.findAll();
        assertThat(detailsInventaireList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = detailsInventaireRepository.findAll().size();
        // set the field null
        detailsInventaire.setCreatedAt(null);

        // Create the DetailsInventaire, which fails.
        DetailsInventaireDTO detailsInventaireDTO = detailsInventaireMapper.toDto(detailsInventaire);


        restDetailsInventaireMockMvc.perform(post("/api/details-inventaires").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(detailsInventaireDTO)))
            .andExpect(status().isBadRequest());

        List<DetailsInventaire> detailsInventaireList = detailsInventaireRepository.findAll();
        assertThat(detailsInventaireList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUpdatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = detailsInventaireRepository.findAll().size();
        // set the field null
        detailsInventaire.setUpdatedAt(null);

        // Create the DetailsInventaire, which fails.
        DetailsInventaireDTO detailsInventaireDTO = detailsInventaireMapper.toDto(detailsInventaire);


        restDetailsInventaireMockMvc.perform(post("/api/details-inventaires").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(detailsInventaireDTO)))
            .andExpect(status().isBadRequest());

        List<DetailsInventaire> detailsInventaireList = detailsInventaireRepository.findAll();
        assertThat(detailsInventaireList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsUpdatedIsRequired() throws Exception {
        int databaseSizeBeforeTest = detailsInventaireRepository.findAll().size();
        // set the field null
        detailsInventaire.setIsUpdated(null);

        // Create the DetailsInventaire, which fails.
        DetailsInventaireDTO detailsInventaireDTO = detailsInventaireMapper.toDto(detailsInventaire);


        restDetailsInventaireMockMvc.perform(post("/api/details-inventaires").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(detailsInventaireDTO)))
            .andExpect(status().isBadRequest());

        List<DetailsInventaire> detailsInventaireList = detailsInventaireRepository.findAll();
        assertThat(detailsInventaireList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDetailsInventaires() throws Exception {
        // Initialize the database
        detailsInventaireRepository.saveAndFlush(detailsInventaire);

        // Get all the detailsInventaireList
        restDetailsInventaireMockMvc.perform(get("/api/details-inventaires?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(detailsInventaire.getId().intValue())))
            .andExpect(jsonPath("$.[*].qty").value(hasItem(DEFAULT_QTY)))
            .andExpect(jsonPath("$.[*].qtyInit").value(hasItem(DEFAULT_QTY_INIT)))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(DEFAULT_UPDATED_AT.toString())))
            .andExpect(jsonPath("$.[*].isUpdated").value(hasItem(DEFAULT_IS_UPDATED.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getDetailsInventaire() throws Exception {
        // Initialize the database
        detailsInventaireRepository.saveAndFlush(detailsInventaire);

        // Get the detailsInventaire
        restDetailsInventaireMockMvc.perform(get("/api/details-inventaires/{id}", detailsInventaire.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(detailsInventaire.getId().intValue()))
            .andExpect(jsonPath("$.qty").value(DEFAULT_QTY))
            .andExpect(jsonPath("$.qtyInit").value(DEFAULT_QTY_INIT))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.updatedAt").value(DEFAULT_UPDATED_AT.toString()))
            .andExpect(jsonPath("$.isUpdated").value(DEFAULT_IS_UPDATED.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingDetailsInventaire() throws Exception {
        // Get the detailsInventaire
        restDetailsInventaireMockMvc.perform(get("/api/details-inventaires/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDetailsInventaire() throws Exception {
        // Initialize the database
        detailsInventaireRepository.saveAndFlush(detailsInventaire);

        int databaseSizeBeforeUpdate = detailsInventaireRepository.findAll().size();

        // Update the detailsInventaire
        DetailsInventaire updatedDetailsInventaire = detailsInventaireRepository.findById(detailsInventaire.getId()).get();
        // Disconnect from session so that the updates on updatedDetailsInventaire are not directly saved in db
        em.detach(updatedDetailsInventaire);
        updatedDetailsInventaire
            .qty(UPDATED_QTY)
            .qtyInit(UPDATED_QTY_INIT)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .isUpdated(UPDATED_IS_UPDATED);
        DetailsInventaireDTO detailsInventaireDTO = detailsInventaireMapper.toDto(updatedDetailsInventaire);

        restDetailsInventaireMockMvc.perform(put("/api/details-inventaires").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(detailsInventaireDTO)))
            .andExpect(status().isOk());

        // Validate the DetailsInventaire in the database
        List<DetailsInventaire> detailsInventaireList = detailsInventaireRepository.findAll();
        assertThat(detailsInventaireList).hasSize(databaseSizeBeforeUpdate);
        DetailsInventaire testDetailsInventaire = detailsInventaireList.get(detailsInventaireList.size() - 1);
        assertThat(testDetailsInventaire.getQty()).isEqualTo(UPDATED_QTY);
        assertThat(testDetailsInventaire.getQtyInit()).isEqualTo(UPDATED_QTY_INIT);
        assertThat(testDetailsInventaire.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testDetailsInventaire.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testDetailsInventaire.isIsUpdated()).isEqualTo(UPDATED_IS_UPDATED);
    }

    @Test
    @Transactional
    public void updateNonExistingDetailsInventaire() throws Exception {
        int databaseSizeBeforeUpdate = detailsInventaireRepository.findAll().size();

        // Create the DetailsInventaire
        DetailsInventaireDTO detailsInventaireDTO = detailsInventaireMapper.toDto(detailsInventaire);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDetailsInventaireMockMvc.perform(put("/api/details-inventaires").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(detailsInventaireDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DetailsInventaire in the database
        List<DetailsInventaire> detailsInventaireList = detailsInventaireRepository.findAll();
        assertThat(detailsInventaireList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDetailsInventaire() throws Exception {
        // Initialize the database
        detailsInventaireRepository.saveAndFlush(detailsInventaire);

        int databaseSizeBeforeDelete = detailsInventaireRepository.findAll().size();

        // Delete the detailsInventaire
        restDetailsInventaireMockMvc.perform(delete("/api/details-inventaires/{id}", detailsInventaire.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DetailsInventaire> detailsInventaireList = detailsInventaireRepository.findAll();
        assertThat(detailsInventaireList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
