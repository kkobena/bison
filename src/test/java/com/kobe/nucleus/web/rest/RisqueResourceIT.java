package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.NucleusApp;
import com.kobe.nucleus.domain.Risque;
import com.kobe.nucleus.domain.TypeRisque;
import com.kobe.nucleus.repository.RisqueRepository;
import com.kobe.nucleus.service.RisqueService;
import com.kobe.nucleus.service.dto.RisqueDTO;
import com.kobe.nucleus.service.mapper.RisqueMapper;

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
 * Integration tests for the {@link RisqueResource} REST controller.
 */
@SpringBootTest(classes = NucleusApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class RisqueResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    private static final Status DEFAULT_STATUS = Status.ACTIVE;
    private static final Status UPDATED_STATUS = Status.ENATTENTE;

    @Autowired
    private RisqueRepository risqueRepository;

    @Autowired
    private RisqueMapper risqueMapper;

    @Autowired
    private RisqueService risqueService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRisqueMockMvc;

    private Risque risque;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Risque createEntity(EntityManager em) {
        Risque risque = new Risque()
            .code(DEFAULT_CODE)
            .libelle(DEFAULT_LIBELLE)
            .status(DEFAULT_STATUS);
        // Add required entity
        TypeRisque typeRisque;
        if (TestUtil.findAll(em, TypeRisque.class).isEmpty()) {
            typeRisque = TypeRisqueResourceIT.createEntity(em);
            em.persist(typeRisque);
            em.flush();
        } else {
            typeRisque = TestUtil.findAll(em, TypeRisque.class).get(0);
        }
        risque.setTyperisque(typeRisque);
        return risque;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Risque createUpdatedEntity(EntityManager em) {
        Risque risque = new Risque()
            .code(UPDATED_CODE)
            .libelle(UPDATED_LIBELLE)
            .status(UPDATED_STATUS);
        // Add required entity
        TypeRisque typeRisque;
        if (TestUtil.findAll(em, TypeRisque.class).isEmpty()) {
            typeRisque = TypeRisqueResourceIT.createUpdatedEntity(em);
            em.persist(typeRisque);
            em.flush();
        } else {
            typeRisque = TestUtil.findAll(em, TypeRisque.class).get(0);
        }
        risque.setTyperisque(typeRisque);
        return risque;
    }

    @BeforeEach
    public void initTest() {
        risque = createEntity(em);
    }

    @Test
    @Transactional
    public void createRisque() throws Exception {
        int databaseSizeBeforeCreate = risqueRepository.findAll().size();
        // Create the Risque
        RisqueDTO risqueDTO = risqueMapper.toDto(risque);
        restRisqueMockMvc.perform(post("/api/risques").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(risqueDTO)))
            .andExpect(status().isCreated());

        // Validate the Risque in the database
        List<Risque> risqueList = risqueRepository.findAll();
        assertThat(risqueList).hasSize(databaseSizeBeforeCreate + 1);
        Risque testRisque = risqueList.get(risqueList.size() - 1);
        assertThat(testRisque.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testRisque.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
        assertThat(testRisque.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createRisqueWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = risqueRepository.findAll().size();

        // Create the Risque with an existing ID
        risque.setId(1L);
        RisqueDTO risqueDTO = risqueMapper.toDto(risque);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRisqueMockMvc.perform(post("/api/risques").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(risqueDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Risque in the database
        List<Risque> risqueList = risqueRepository.findAll();
        assertThat(risqueList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLibelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = risqueRepository.findAll().size();
        // set the field null
        risque.setLibelle(null);

        // Create the Risque, which fails.
        RisqueDTO risqueDTO = risqueMapper.toDto(risque);


        restRisqueMockMvc.perform(post("/api/risques").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(risqueDTO)))
            .andExpect(status().isBadRequest());

        List<Risque> risqueList = risqueRepository.findAll();
        assertThat(risqueList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = risqueRepository.findAll().size();
        // set the field null
        risque.setStatus(null);

        // Create the Risque, which fails.
        RisqueDTO risqueDTO = risqueMapper.toDto(risque);


        restRisqueMockMvc.perform(post("/api/risques").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(risqueDTO)))
            .andExpect(status().isBadRequest());

        List<Risque> risqueList = risqueRepository.findAll();
        assertThat(risqueList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRisques() throws Exception {
        // Initialize the database
        risqueRepository.saveAndFlush(risque);

        // Get all the risqueList
        restRisqueMockMvc.perform(get("/api/risques?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(risque.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }
    
    @Test
    @Transactional
    public void getRisque() throws Exception {
        // Initialize the database
        risqueRepository.saveAndFlush(risque);

        // Get the risque
        restRisqueMockMvc.perform(get("/api/risques/{id}", risque.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(risque.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingRisque() throws Exception {
        // Get the risque
        restRisqueMockMvc.perform(get("/api/risques/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRisque() throws Exception {
        // Initialize the database
        risqueRepository.saveAndFlush(risque);

        int databaseSizeBeforeUpdate = risqueRepository.findAll().size();

        // Update the risque
        Risque updatedRisque = risqueRepository.findById(risque.getId()).get();
        // Disconnect from session so that the updates on updatedRisque are not directly saved in db
        em.detach(updatedRisque);
        updatedRisque
            .code(UPDATED_CODE)
            .libelle(UPDATED_LIBELLE)
            .status(UPDATED_STATUS);
        RisqueDTO risqueDTO = risqueMapper.toDto(updatedRisque);

        restRisqueMockMvc.perform(put("/api/risques").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(risqueDTO)))
            .andExpect(status().isOk());

        // Validate the Risque in the database
        List<Risque> risqueList = risqueRepository.findAll();
        assertThat(risqueList).hasSize(databaseSizeBeforeUpdate);
        Risque testRisque = risqueList.get(risqueList.size() - 1);
        assertThat(testRisque.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testRisque.getLibelle()).isEqualTo(UPDATED_LIBELLE);
        assertThat(testRisque.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingRisque() throws Exception {
        int databaseSizeBeforeUpdate = risqueRepository.findAll().size();

        // Create the Risque
        RisqueDTO risqueDTO = risqueMapper.toDto(risque);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRisqueMockMvc.perform(put("/api/risques").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(risqueDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Risque in the database
        List<Risque> risqueList = risqueRepository.findAll();
        assertThat(risqueList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRisque() throws Exception {
        // Initialize the database
        risqueRepository.saveAndFlush(risque);

        int databaseSizeBeforeDelete = risqueRepository.findAll().size();

        // Delete the risque
        restRisqueMockMvc.perform(delete("/api/risques/{id}", risque.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Risque> risqueList = risqueRepository.findAll();
        assertThat(risqueList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
