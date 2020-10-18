package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.NucleusApp;
import com.kobe.nucleus.domain.Etiquette;
import com.kobe.nucleus.repository.EtiquetteRepository;
import com.kobe.nucleus.service.EtiquetteService;
import com.kobe.nucleus.service.dto.EtiquetteDTO;
import com.kobe.nucleus.service.mapper.EtiquetteMapper;

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
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link EtiquetteResource} REST controller.
 */
@SpringBootTest(classes = NucleusApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class EtiquetteResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final Integer DEFAULT_QTY = 1;
    private static final Integer UPDATED_QTY = 2;

    private static final LocalDate DEFAULT_END_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_END_DATE = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private EtiquetteRepository etiquetteRepository;

    @Autowired
    private EtiquetteMapper etiquetteMapper;

    @Autowired
    private EtiquetteService etiquetteService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEtiquetteMockMvc;

    private Etiquette etiquette;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Etiquette createEntity(EntityManager em) {
        Etiquette etiquette = new Etiquette()
            .libelle(DEFAULT_LIBELLE)
            .code(DEFAULT_CODE)
            .qty(DEFAULT_QTY)
            .endDate(DEFAULT_END_DATE);
        return etiquette;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Etiquette createUpdatedEntity(EntityManager em) {
        Etiquette etiquette = new Etiquette()
            .libelle(UPDATED_LIBELLE)
            .code(UPDATED_CODE)
            .qty(UPDATED_QTY)
            .endDate(UPDATED_END_DATE);
        return etiquette;
    }

    @BeforeEach
    public void initTest() {
        etiquette = createEntity(em);
    }

    @Test
    @Transactional
    public void createEtiquette() throws Exception {
        int databaseSizeBeforeCreate = etiquetteRepository.findAll().size();
        // Create the Etiquette
        EtiquetteDTO etiquetteDTO = etiquetteMapper.toDto(etiquette);
        restEtiquetteMockMvc.perform(post("/api/etiquettes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(etiquetteDTO)))
            .andExpect(status().isCreated());

        // Validate the Etiquette in the database
        List<Etiquette> etiquetteList = etiquetteRepository.findAll();
        assertThat(etiquetteList).hasSize(databaseSizeBeforeCreate + 1);
        Etiquette testEtiquette = etiquetteList.get(etiquetteList.size() - 1);
        assertThat(testEtiquette.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
        assertThat(testEtiquette.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testEtiquette.getQty()).isEqualTo(DEFAULT_QTY);
        assertThat(testEtiquette.getEndDate()).isEqualTo(DEFAULT_END_DATE);
    }

    @Test
    @Transactional
    public void createEtiquetteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = etiquetteRepository.findAll().size();

        // Create the Etiquette with an existing ID
        etiquette.setId(1L);
        EtiquetteDTO etiquetteDTO = etiquetteMapper.toDto(etiquette);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEtiquetteMockMvc.perform(post("/api/etiquettes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(etiquetteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Etiquette in the database
        List<Etiquette> etiquetteList = etiquetteRepository.findAll();
        assertThat(etiquetteList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEtiquettes() throws Exception {
        // Initialize the database
        etiquetteRepository.saveAndFlush(etiquette);

        // Get all the etiquetteList
        restEtiquetteMockMvc.perform(get("/api/etiquettes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(etiquette.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].qty").value(hasItem(DEFAULT_QTY)))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())));
    }
    
    @Test
    @Transactional
    public void getEtiquette() throws Exception {
        // Initialize the database
        etiquetteRepository.saveAndFlush(etiquette);

        // Get the etiquette
        restEtiquetteMockMvc.perform(get("/api/etiquettes/{id}", etiquette.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(etiquette.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.qty").value(DEFAULT_QTY))
            .andExpect(jsonPath("$.endDate").value(DEFAULT_END_DATE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingEtiquette() throws Exception {
        // Get the etiquette
        restEtiquetteMockMvc.perform(get("/api/etiquettes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEtiquette() throws Exception {
        // Initialize the database
        etiquetteRepository.saveAndFlush(etiquette);

        int databaseSizeBeforeUpdate = etiquetteRepository.findAll().size();

        // Update the etiquette
        Etiquette updatedEtiquette = etiquetteRepository.findById(etiquette.getId()).get();
        // Disconnect from session so that the updates on updatedEtiquette are not directly saved in db
        em.detach(updatedEtiquette);
        updatedEtiquette
            .libelle(UPDATED_LIBELLE)
            .code(UPDATED_CODE)
            .qty(UPDATED_QTY)
            .endDate(UPDATED_END_DATE);
        EtiquetteDTO etiquetteDTO = etiquetteMapper.toDto(updatedEtiquette);

        restEtiquetteMockMvc.perform(put("/api/etiquettes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(etiquetteDTO)))
            .andExpect(status().isOk());

        // Validate the Etiquette in the database
        List<Etiquette> etiquetteList = etiquetteRepository.findAll();
        assertThat(etiquetteList).hasSize(databaseSizeBeforeUpdate);
        Etiquette testEtiquette = etiquetteList.get(etiquetteList.size() - 1);
        assertThat(testEtiquette.getLibelle()).isEqualTo(UPDATED_LIBELLE);
        assertThat(testEtiquette.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testEtiquette.getQty()).isEqualTo(UPDATED_QTY);
        assertThat(testEtiquette.getEndDate()).isEqualTo(UPDATED_END_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingEtiquette() throws Exception {
        int databaseSizeBeforeUpdate = etiquetteRepository.findAll().size();

        // Create the Etiquette
        EtiquetteDTO etiquetteDTO = etiquetteMapper.toDto(etiquette);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEtiquetteMockMvc.perform(put("/api/etiquettes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(etiquetteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Etiquette in the database
        List<Etiquette> etiquetteList = etiquetteRepository.findAll();
        assertThat(etiquetteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEtiquette() throws Exception {
        // Initialize the database
        etiquetteRepository.saveAndFlush(etiquette);

        int databaseSizeBeforeDelete = etiquetteRepository.findAll().size();

        // Delete the etiquette
        restEtiquetteMockMvc.perform(delete("/api/etiquettes/{id}", etiquette.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Etiquette> etiquetteList = etiquetteRepository.findAll();
        assertThat(etiquetteList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
