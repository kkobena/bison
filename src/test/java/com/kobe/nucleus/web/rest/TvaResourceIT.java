package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.NucleusApp;
import com.kobe.nucleus.domain.Tva;
import com.kobe.nucleus.repository.TvaRepository;
import com.kobe.nucleus.service.TvaService;
import com.kobe.nucleus.service.dto.TvaDTO;
import com.kobe.nucleus.service.mapper.TvaMapper;

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
 * Integration tests for the {@link TvaResource} REST controller.
 */
@SpringBootTest(classes = NucleusApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TvaResourceIT {

    private static final Integer DEFAULT_TAUX = 1;
    private static final Integer UPDATED_TAUX = 2;

    @Autowired
    private TvaRepository tvaRepository;

    @Autowired
    private TvaMapper tvaMapper;

    @Autowired
    private TvaService tvaService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTvaMockMvc;

    private Tva tva;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Tva createEntity(EntityManager em) {
        Tva tva = new Tva()
            .taux(DEFAULT_TAUX);
        return tva;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Tva createUpdatedEntity(EntityManager em) {
        Tva tva = new Tva()
            .taux(UPDATED_TAUX);
        return tva;
    }

    @BeforeEach
    public void initTest() {
        tva = createEntity(em);
    }

    @Test
    @Transactional
    public void createTva() throws Exception {
        int databaseSizeBeforeCreate = tvaRepository.findAll().size();
        // Create the Tva
        TvaDTO tvaDTO = tvaMapper.toDto(tva);
        restTvaMockMvc.perform(post("/api/tvas").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tvaDTO)))
            .andExpect(status().isCreated());

        // Validate the Tva in the database
        List<Tva> tvaList = tvaRepository.findAll();
        assertThat(tvaList).hasSize(databaseSizeBeforeCreate + 1);
        Tva testTva = tvaList.get(tvaList.size() - 1);
        assertThat(testTva.getTaux()).isEqualTo(DEFAULT_TAUX);
    }

    @Test
    @Transactional
    public void createTvaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tvaRepository.findAll().size();

        // Create the Tva with an existing ID
        tva.setId(1L);
        TvaDTO tvaDTO = tvaMapper.toDto(tva);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTvaMockMvc.perform(post("/api/tvas").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tvaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Tva in the database
        List<Tva> tvaList = tvaRepository.findAll();
        assertThat(tvaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkTauxIsRequired() throws Exception {
        int databaseSizeBeforeTest = tvaRepository.findAll().size();
        // set the field null
        tva.setTaux(null);

        // Create the Tva, which fails.
        TvaDTO tvaDTO = tvaMapper.toDto(tva);


        restTvaMockMvc.perform(post("/api/tvas").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tvaDTO)))
            .andExpect(status().isBadRequest());

        List<Tva> tvaList = tvaRepository.findAll();
        assertThat(tvaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTvas() throws Exception {
        // Initialize the database
        tvaRepository.saveAndFlush(tva);

        // Get all the tvaList
        restTvaMockMvc.perform(get("/api/tvas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tva.getId().intValue())))
            .andExpect(jsonPath("$.[*].taux").value(hasItem(DEFAULT_TAUX)));
    }
    
    @Test
    @Transactional
    public void getTva() throws Exception {
        // Initialize the database
        tvaRepository.saveAndFlush(tva);

        // Get the tva
        restTvaMockMvc.perform(get("/api/tvas/{id}", tva.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tva.getId().intValue()))
            .andExpect(jsonPath("$.taux").value(DEFAULT_TAUX));
    }
    @Test
    @Transactional
    public void getNonExistingTva() throws Exception {
        // Get the tva
        restTvaMockMvc.perform(get("/api/tvas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTva() throws Exception {
        // Initialize the database
        tvaRepository.saveAndFlush(tva);

        int databaseSizeBeforeUpdate = tvaRepository.findAll().size();

        // Update the tva
        Tva updatedTva = tvaRepository.findById(tva.getId()).get();
        // Disconnect from session so that the updates on updatedTva are not directly saved in db
        em.detach(updatedTva);
        updatedTva
            .taux(UPDATED_TAUX);
        TvaDTO tvaDTO = tvaMapper.toDto(updatedTva);

        restTvaMockMvc.perform(put("/api/tvas").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tvaDTO)))
            .andExpect(status().isOk());

        // Validate the Tva in the database
        List<Tva> tvaList = tvaRepository.findAll();
        assertThat(tvaList).hasSize(databaseSizeBeforeUpdate);
        Tva testTva = tvaList.get(tvaList.size() - 1);
        assertThat(testTva.getTaux()).isEqualTo(UPDATED_TAUX);
    }

    @Test
    @Transactional
    public void updateNonExistingTva() throws Exception {
        int databaseSizeBeforeUpdate = tvaRepository.findAll().size();

        // Create the Tva
        TvaDTO tvaDTO = tvaMapper.toDto(tva);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTvaMockMvc.perform(put("/api/tvas").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tvaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Tva in the database
        List<Tva> tvaList = tvaRepository.findAll();
        assertThat(tvaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTva() throws Exception {
        // Initialize the database
        tvaRepository.saveAndFlush(tva);

        int databaseSizeBeforeDelete = tvaRepository.findAll().size();

        // Delete the tva
        restTvaMockMvc.perform(delete("/api/tvas/{id}", tva.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Tva> tvaList = tvaRepository.findAll();
        assertThat(tvaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
