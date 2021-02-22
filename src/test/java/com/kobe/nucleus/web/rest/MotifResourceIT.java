package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.NucleusApp;
import com.kobe.nucleus.domain.Motif;
import com.kobe.nucleus.repository.MotifRepository;
import com.kobe.nucleus.service.MotifService;
import com.kobe.nucleus.service.dto.MotifDTO;
import com.kobe.nucleus.service.mapper.MotifMapper;

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
 * Integration tests for the {@link MotifResource} REST controller.
 */
@SpringBootTest(classes = NucleusApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class MotifResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    private static final Status DEFAULT_STATUS = Status.ACTIVE;
    private static final Status UPDATED_STATUS = Status.ENATTENTE;

    @Autowired
    private MotifRepository motifRepository;

    @Autowired
    private MotifMapper motifMapper;

    @Autowired
    private MotifService motifService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMotifMockMvc;

    private Motif motif;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Motif createEntity(EntityManager em) {
        Motif motif = new Motif()
            .libelle(DEFAULT_LIBELLE)
            ;
        return motif;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Motif createUpdatedEntity(EntityManager em) {
        Motif motif = new Motif()
            .libelle(UPDATED_LIBELLE);
           
        return motif;
    }

    @BeforeEach
    public void initTest() {
        motif = createEntity(em);
    }

    @Test
    @Transactional
    public void createMotif() throws Exception {
        int databaseSizeBeforeCreate = motifRepository.findAll().size();
        // Create the Motif
        MotifDTO motifDTO = motifMapper.toDto(motif);
        restMotifMockMvc.perform(post("/api/motifs").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(motifDTO)))
            .andExpect(status().isCreated());

        // Validate the Motif in the database
        List<Motif> motifList = motifRepository.findAll();
        assertThat(motifList).hasSize(databaseSizeBeforeCreate + 1);
        Motif testMotif = motifList.get(motifList.size() - 1);
        assertThat(testMotif.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
      
    }

    @Test
    @Transactional
    public void createMotifWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = motifRepository.findAll().size();

        // Create the Motif with an existing ID
        motif.setId(1L);
        MotifDTO motifDTO = motifMapper.toDto(motif);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMotifMockMvc.perform(post("/api/motifs").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(motifDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Motif in the database
        List<Motif> motifList = motifRepository.findAll();
        assertThat(motifList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLibelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = motifRepository.findAll().size();
        // set the field null
        motif.setLibelle(null);

        // Create the Motif, which fails.
        MotifDTO motifDTO = motifMapper.toDto(motif);


        restMotifMockMvc.perform(post("/api/motifs").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(motifDTO)))
            .andExpect(status().isBadRequest());

        List<Motif> motifList = motifRepository.findAll();
        assertThat(motifList).hasSize(databaseSizeBeforeTest);
    }

  

    @Test
    @Transactional
    public void getAllMotifs() throws Exception {
        // Initialize the database
        motifRepository.saveAndFlush(motif);

        // Get all the motifList
        restMotifMockMvc.perform(get("/api/motifs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(motif.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)));
        
    }
    
    @Test
    @Transactional
    public void getMotif() throws Exception {
        // Initialize the database
        motifRepository.saveAndFlush(motif);

        // Get the motif
        restMotifMockMvc.perform(get("/api/motifs/{id}", motif.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(motif.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE));
          
    }
    @Test
    @Transactional
    public void getNonExistingMotif() throws Exception {
        // Get the motif
        restMotifMockMvc.perform(get("/api/motifs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMotif() throws Exception {
        // Initialize the database
        motifRepository.saveAndFlush(motif);

        int databaseSizeBeforeUpdate = motifRepository.findAll().size();

        // Update the motif
        Motif updatedMotif = motifRepository.findById(motif.getId()).get();
        // Disconnect from session so that the updates on updatedMotif are not directly saved in db
        em.detach(updatedMotif);
        updatedMotif
            .libelle(UPDATED_LIBELLE)
            ;
        MotifDTO motifDTO = motifMapper.toDto(updatedMotif);

        restMotifMockMvc.perform(put("/api/motifs").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(motifDTO)))
            .andExpect(status().isOk());

        // Validate the Motif in the database
        List<Motif> motifList = motifRepository.findAll();
        assertThat(motifList).hasSize(databaseSizeBeforeUpdate);
        Motif testMotif = motifList.get(motifList.size() - 1);
        assertThat(testMotif.getLibelle()).isEqualTo(UPDATED_LIBELLE);
      
    }

    @Test
    @Transactional
    public void updateNonExistingMotif() throws Exception {
        int databaseSizeBeforeUpdate = motifRepository.findAll().size();

        // Create the Motif
        MotifDTO motifDTO = motifMapper.toDto(motif);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMotifMockMvc.perform(put("/api/motifs").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(motifDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Motif in the database
        List<Motif> motifList = motifRepository.findAll();
        assertThat(motifList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMotif() throws Exception {
        // Initialize the database
        motifRepository.saveAndFlush(motif);

        int databaseSizeBeforeDelete = motifRepository.findAll().size();

        // Delete the motif
        restMotifMockMvc.perform(delete("/api/motifs/{id}", motif.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Motif> motifList = motifRepository.findAll();
        assertThat(motifList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
