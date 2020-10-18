package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.NucleusApp;
import com.kobe.nucleus.domain.LignesVenteAssurence;
import com.kobe.nucleus.repository.LignesVenteAssurenceRepository;
import com.kobe.nucleus.service.LignesVenteAssurenceService;
import com.kobe.nucleus.service.dto.LignesVenteAssurenceDTO;
import com.kobe.nucleus.service.mapper.LignesVenteAssurenceMapper;

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

import com.kobe.nucleus.domain.enumeration.StatutFacture;
/**
 * Integration tests for the {@link LignesVenteAssurenceResource} REST controller.
 */
@SpringBootTest(classes = NucleusApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class LignesVenteAssurenceResourceIT {

    private static final Integer DEFAULT_MONTANT = 1;
    private static final Integer UPDATED_MONTANT = 2;

    private static final String DEFAULT_REF_BON = "AAAAAAAAAA";
    private static final String UPDATED_REF_BON = "BBBBBBBBBB";

    private static final Integer DEFAULT_TAUX = 1;
    private static final Integer UPDATED_TAUX = 2;

    private static final Integer DEFAULT_MONTANT_RESTE = 1;
    private static final Integer UPDATED_MONTANT_RESTE = 2;

    private static final StatutFacture DEFAULT_STATUT_FACTURE = StatutFacture.PAID;
    private static final StatutFacture UPDATED_STATUT_FACTURE = StatutFacture.UNPAID;

    private static final Instant DEFAULT_CREATED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_UPDATED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private LignesVenteAssurenceRepository lignesVenteAssurenceRepository;

    @Autowired
    private LignesVenteAssurenceMapper lignesVenteAssurenceMapper;

    @Autowired
    private LignesVenteAssurenceService lignesVenteAssurenceService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLignesVenteAssurenceMockMvc;

    private LignesVenteAssurence lignesVenteAssurence;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LignesVenteAssurence createEntity(EntityManager em) {
        LignesVenteAssurence lignesVenteAssurence = new LignesVenteAssurence()
            .montant(DEFAULT_MONTANT)
            .refBon(DEFAULT_REF_BON)
            .taux(DEFAULT_TAUX)
            .montantReste(DEFAULT_MONTANT_RESTE)
            .statutFacture(DEFAULT_STATUT_FACTURE)
            .createdAt(DEFAULT_CREATED_AT)
            .updatedAt(DEFAULT_UPDATED_AT);
        return lignesVenteAssurence;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LignesVenteAssurence createUpdatedEntity(EntityManager em) {
        LignesVenteAssurence lignesVenteAssurence = new LignesVenteAssurence()
            .montant(UPDATED_MONTANT)
            .refBon(UPDATED_REF_BON)
            .taux(UPDATED_TAUX)
            .montantReste(UPDATED_MONTANT_RESTE)
            .statutFacture(UPDATED_STATUT_FACTURE)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);
        return lignesVenteAssurence;
    }

    @BeforeEach
    public void initTest() {
        lignesVenteAssurence = createEntity(em);
    }

    @Test
    @Transactional
    public void createLignesVenteAssurence() throws Exception {
        int databaseSizeBeforeCreate = lignesVenteAssurenceRepository.findAll().size();
        // Create the LignesVenteAssurence
        LignesVenteAssurenceDTO lignesVenteAssurenceDTO = lignesVenteAssurenceMapper.toDto(lignesVenteAssurence);
        restLignesVenteAssurenceMockMvc.perform(post("/api/lignes-vente-assurences").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(lignesVenteAssurenceDTO)))
            .andExpect(status().isCreated());

        // Validate the LignesVenteAssurence in the database
        List<LignesVenteAssurence> lignesVenteAssurenceList = lignesVenteAssurenceRepository.findAll();
        assertThat(lignesVenteAssurenceList).hasSize(databaseSizeBeforeCreate + 1);
        LignesVenteAssurence testLignesVenteAssurence = lignesVenteAssurenceList.get(lignesVenteAssurenceList.size() - 1);
        assertThat(testLignesVenteAssurence.getMontant()).isEqualTo(DEFAULT_MONTANT);
        assertThat(testLignesVenteAssurence.getRefBon()).isEqualTo(DEFAULT_REF_BON);
        assertThat(testLignesVenteAssurence.getTaux()).isEqualTo(DEFAULT_TAUX);
        assertThat(testLignesVenteAssurence.getMontantReste()).isEqualTo(DEFAULT_MONTANT_RESTE);
        assertThat(testLignesVenteAssurence.getStatutFacture()).isEqualTo(DEFAULT_STATUT_FACTURE);
        assertThat(testLignesVenteAssurence.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testLignesVenteAssurence.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
    }

    @Test
    @Transactional
    public void createLignesVenteAssurenceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = lignesVenteAssurenceRepository.findAll().size();

        // Create the LignesVenteAssurence with an existing ID
        lignesVenteAssurence.setId(1L);
        LignesVenteAssurenceDTO lignesVenteAssurenceDTO = lignesVenteAssurenceMapper.toDto(lignesVenteAssurence);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLignesVenteAssurenceMockMvc.perform(post("/api/lignes-vente-assurences").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(lignesVenteAssurenceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the LignesVenteAssurence in the database
        List<LignesVenteAssurence> lignesVenteAssurenceList = lignesVenteAssurenceRepository.findAll();
        assertThat(lignesVenteAssurenceList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkStatutFactureIsRequired() throws Exception {
        int databaseSizeBeforeTest = lignesVenteAssurenceRepository.findAll().size();
        // set the field null
        lignesVenteAssurence.setStatutFacture(null);

        // Create the LignesVenteAssurence, which fails.
        LignesVenteAssurenceDTO lignesVenteAssurenceDTO = lignesVenteAssurenceMapper.toDto(lignesVenteAssurence);


        restLignesVenteAssurenceMockMvc.perform(post("/api/lignes-vente-assurences").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(lignesVenteAssurenceDTO)))
            .andExpect(status().isBadRequest());

        List<LignesVenteAssurence> lignesVenteAssurenceList = lignesVenteAssurenceRepository.findAll();
        assertThat(lignesVenteAssurenceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = lignesVenteAssurenceRepository.findAll().size();
        // set the field null
        lignesVenteAssurence.setCreatedAt(null);

        // Create the LignesVenteAssurence, which fails.
        LignesVenteAssurenceDTO lignesVenteAssurenceDTO = lignesVenteAssurenceMapper.toDto(lignesVenteAssurence);


        restLignesVenteAssurenceMockMvc.perform(post("/api/lignes-vente-assurences").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(lignesVenteAssurenceDTO)))
            .andExpect(status().isBadRequest());

        List<LignesVenteAssurence> lignesVenteAssurenceList = lignesVenteAssurenceRepository.findAll();
        assertThat(lignesVenteAssurenceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUpdatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = lignesVenteAssurenceRepository.findAll().size();
        // set the field null
        lignesVenteAssurence.setUpdatedAt(null);

        // Create the LignesVenteAssurence, which fails.
        LignesVenteAssurenceDTO lignesVenteAssurenceDTO = lignesVenteAssurenceMapper.toDto(lignesVenteAssurence);


        restLignesVenteAssurenceMockMvc.perform(post("/api/lignes-vente-assurences").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(lignesVenteAssurenceDTO)))
            .andExpect(status().isBadRequest());

        List<LignesVenteAssurence> lignesVenteAssurenceList = lignesVenteAssurenceRepository.findAll();
        assertThat(lignesVenteAssurenceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllLignesVenteAssurences() throws Exception {
        // Initialize the database
        lignesVenteAssurenceRepository.saveAndFlush(lignesVenteAssurence);

        // Get all the lignesVenteAssurenceList
        restLignesVenteAssurenceMockMvc.perform(get("/api/lignes-vente-assurences?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(lignesVenteAssurence.getId().intValue())))
            .andExpect(jsonPath("$.[*].montant").value(hasItem(DEFAULT_MONTANT)))
            .andExpect(jsonPath("$.[*].refBon").value(hasItem(DEFAULT_REF_BON)))
            .andExpect(jsonPath("$.[*].taux").value(hasItem(DEFAULT_TAUX)))
            .andExpect(jsonPath("$.[*].montantReste").value(hasItem(DEFAULT_MONTANT_RESTE)))
            .andExpect(jsonPath("$.[*].statutFacture").value(hasItem(DEFAULT_STATUT_FACTURE.toString())))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(DEFAULT_UPDATED_AT.toString())));
    }
    
    @Test
    @Transactional
    public void getLignesVenteAssurence() throws Exception {
        // Initialize the database
        lignesVenteAssurenceRepository.saveAndFlush(lignesVenteAssurence);

        // Get the lignesVenteAssurence
        restLignesVenteAssurenceMockMvc.perform(get("/api/lignes-vente-assurences/{id}", lignesVenteAssurence.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(lignesVenteAssurence.getId().intValue()))
            .andExpect(jsonPath("$.montant").value(DEFAULT_MONTANT))
            .andExpect(jsonPath("$.refBon").value(DEFAULT_REF_BON))
            .andExpect(jsonPath("$.taux").value(DEFAULT_TAUX))
            .andExpect(jsonPath("$.montantReste").value(DEFAULT_MONTANT_RESTE))
            .andExpect(jsonPath("$.statutFacture").value(DEFAULT_STATUT_FACTURE.toString()))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.updatedAt").value(DEFAULT_UPDATED_AT.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingLignesVenteAssurence() throws Exception {
        // Get the lignesVenteAssurence
        restLignesVenteAssurenceMockMvc.perform(get("/api/lignes-vente-assurences/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLignesVenteAssurence() throws Exception {
        // Initialize the database
        lignesVenteAssurenceRepository.saveAndFlush(lignesVenteAssurence);

        int databaseSizeBeforeUpdate = lignesVenteAssurenceRepository.findAll().size();

        // Update the lignesVenteAssurence
        LignesVenteAssurence updatedLignesVenteAssurence = lignesVenteAssurenceRepository.findById(lignesVenteAssurence.getId()).get();
        // Disconnect from session so that the updates on updatedLignesVenteAssurence are not directly saved in db
        em.detach(updatedLignesVenteAssurence);
        updatedLignesVenteAssurence
            .montant(UPDATED_MONTANT)
            .refBon(UPDATED_REF_BON)
            .taux(UPDATED_TAUX)
            .montantReste(UPDATED_MONTANT_RESTE)
            .statutFacture(UPDATED_STATUT_FACTURE)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);
        LignesVenteAssurenceDTO lignesVenteAssurenceDTO = lignesVenteAssurenceMapper.toDto(updatedLignesVenteAssurence);

        restLignesVenteAssurenceMockMvc.perform(put("/api/lignes-vente-assurences").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(lignesVenteAssurenceDTO)))
            .andExpect(status().isOk());

        // Validate the LignesVenteAssurence in the database
        List<LignesVenteAssurence> lignesVenteAssurenceList = lignesVenteAssurenceRepository.findAll();
        assertThat(lignesVenteAssurenceList).hasSize(databaseSizeBeforeUpdate);
        LignesVenteAssurence testLignesVenteAssurence = lignesVenteAssurenceList.get(lignesVenteAssurenceList.size() - 1);
        assertThat(testLignesVenteAssurence.getMontant()).isEqualTo(UPDATED_MONTANT);
        assertThat(testLignesVenteAssurence.getRefBon()).isEqualTo(UPDATED_REF_BON);
        assertThat(testLignesVenteAssurence.getTaux()).isEqualTo(UPDATED_TAUX);
        assertThat(testLignesVenteAssurence.getMontantReste()).isEqualTo(UPDATED_MONTANT_RESTE);
        assertThat(testLignesVenteAssurence.getStatutFacture()).isEqualTo(UPDATED_STATUT_FACTURE);
        assertThat(testLignesVenteAssurence.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testLignesVenteAssurence.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
    }

    @Test
    @Transactional
    public void updateNonExistingLignesVenteAssurence() throws Exception {
        int databaseSizeBeforeUpdate = lignesVenteAssurenceRepository.findAll().size();

        // Create the LignesVenteAssurence
        LignesVenteAssurenceDTO lignesVenteAssurenceDTO = lignesVenteAssurenceMapper.toDto(lignesVenteAssurence);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLignesVenteAssurenceMockMvc.perform(put("/api/lignes-vente-assurences").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(lignesVenteAssurenceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the LignesVenteAssurence in the database
        List<LignesVenteAssurence> lignesVenteAssurenceList = lignesVenteAssurenceRepository.findAll();
        assertThat(lignesVenteAssurenceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLignesVenteAssurence() throws Exception {
        // Initialize the database
        lignesVenteAssurenceRepository.saveAndFlush(lignesVenteAssurence);

        int databaseSizeBeforeDelete = lignesVenteAssurenceRepository.findAll().size();

        // Delete the lignesVenteAssurence
        restLignesVenteAssurenceMockMvc.perform(delete("/api/lignes-vente-assurences/{id}", lignesVenteAssurence.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<LignesVenteAssurence> lignesVenteAssurenceList = lignesVenteAssurenceRepository.findAll();
        assertThat(lignesVenteAssurenceList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
