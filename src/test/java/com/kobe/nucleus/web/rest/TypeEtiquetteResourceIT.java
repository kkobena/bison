package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.NucleusApp;
import com.kobe.nucleus.domain.TypeEtiquette;
import com.kobe.nucleus.repository.TypeEtiquetteRepository;
import com.kobe.nucleus.service.TypeEtiquetteService;
import com.kobe.nucleus.service.dto.TypeEtiquetteDTO;
import com.kobe.nucleus.service.mapper.TypeEtiquetteMapper;

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
 * Integration tests for the {@link TypeEtiquetteResource} REST controller.
 */
@SpringBootTest(classes = NucleusApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TypeEtiquetteResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    private static final Status DEFAULT_STATUS = Status.ACTIVE;
    private static final Status UPDATED_STATUS = Status.ENATTENTE;

    @Autowired
    private TypeEtiquetteRepository typeEtiquetteRepository;

    @Autowired
    private TypeEtiquetteMapper typeEtiquetteMapper;

    @Autowired
    private TypeEtiquetteService typeEtiquetteService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTypeEtiquetteMockMvc;

    private TypeEtiquette typeEtiquette;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TypeEtiquette createEntity(EntityManager em) {
        TypeEtiquette typeEtiquette = new TypeEtiquette()
            .libelle(DEFAULT_LIBELLE)
            .status(DEFAULT_STATUS);
        return typeEtiquette;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TypeEtiquette createUpdatedEntity(EntityManager em) {
        TypeEtiquette typeEtiquette = new TypeEtiquette()
            .libelle(UPDATED_LIBELLE)
            .status(UPDATED_STATUS);
        return typeEtiquette;
    }

    @BeforeEach
    public void initTest() {
        typeEtiquette = createEntity(em);
    }

    @Test
    @Transactional
    public void createTypeEtiquette() throws Exception {
        int databaseSizeBeforeCreate = typeEtiquetteRepository.findAll().size();
        // Create the TypeEtiquette
        TypeEtiquetteDTO typeEtiquetteDTO = typeEtiquetteMapper.toDto(typeEtiquette);
        restTypeEtiquetteMockMvc.perform(post("/api/type-etiquettes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeEtiquetteDTO)))
            .andExpect(status().isCreated());

        // Validate the TypeEtiquette in the database
        List<TypeEtiquette> typeEtiquetteList = typeEtiquetteRepository.findAll();
        assertThat(typeEtiquetteList).hasSize(databaseSizeBeforeCreate + 1);
        TypeEtiquette testTypeEtiquette = typeEtiquetteList.get(typeEtiquetteList.size() - 1);
        assertThat(testTypeEtiquette.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
        assertThat(testTypeEtiquette.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createTypeEtiquetteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = typeEtiquetteRepository.findAll().size();

        // Create the TypeEtiquette with an existing ID
        typeEtiquette.setId(1L);
        TypeEtiquetteDTO typeEtiquetteDTO = typeEtiquetteMapper.toDto(typeEtiquette);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTypeEtiquetteMockMvc.perform(post("/api/type-etiquettes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeEtiquetteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TypeEtiquette in the database
        List<TypeEtiquette> typeEtiquetteList = typeEtiquetteRepository.findAll();
        assertThat(typeEtiquetteList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLibelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = typeEtiquetteRepository.findAll().size();
        // set the field null
        typeEtiquette.setLibelle(null);

        // Create the TypeEtiquette, which fails.
        TypeEtiquetteDTO typeEtiquetteDTO = typeEtiquetteMapper.toDto(typeEtiquette);


        restTypeEtiquetteMockMvc.perform(post("/api/type-etiquettes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeEtiquetteDTO)))
            .andExpect(status().isBadRequest());

        List<TypeEtiquette> typeEtiquetteList = typeEtiquetteRepository.findAll();
        assertThat(typeEtiquetteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = typeEtiquetteRepository.findAll().size();
        // set the field null
        typeEtiquette.setStatus(null);

        // Create the TypeEtiquette, which fails.
        TypeEtiquetteDTO typeEtiquetteDTO = typeEtiquetteMapper.toDto(typeEtiquette);


        restTypeEtiquetteMockMvc.perform(post("/api/type-etiquettes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeEtiquetteDTO)))
            .andExpect(status().isBadRequest());

        List<TypeEtiquette> typeEtiquetteList = typeEtiquetteRepository.findAll();
        assertThat(typeEtiquetteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTypeEtiquettes() throws Exception {
        // Initialize the database
        typeEtiquetteRepository.saveAndFlush(typeEtiquette);

        // Get all the typeEtiquetteList
        restTypeEtiquetteMockMvc.perform(get("/api/type-etiquettes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(typeEtiquette.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }
    
    @Test
    @Transactional
    public void getTypeEtiquette() throws Exception {
        // Initialize the database
        typeEtiquetteRepository.saveAndFlush(typeEtiquette);

        // Get the typeEtiquette
        restTypeEtiquetteMockMvc.perform(get("/api/type-etiquettes/{id}", typeEtiquette.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(typeEtiquette.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingTypeEtiquette() throws Exception {
        // Get the typeEtiquette
        restTypeEtiquetteMockMvc.perform(get("/api/type-etiquettes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTypeEtiquette() throws Exception {
        // Initialize the database
        typeEtiquetteRepository.saveAndFlush(typeEtiquette);

        int databaseSizeBeforeUpdate = typeEtiquetteRepository.findAll().size();

        // Update the typeEtiquette
        TypeEtiquette updatedTypeEtiquette = typeEtiquetteRepository.findById(typeEtiquette.getId()).get();
        // Disconnect from session so that the updates on updatedTypeEtiquette are not directly saved in db
        em.detach(updatedTypeEtiquette);
        updatedTypeEtiquette
            .libelle(UPDATED_LIBELLE)
            .status(UPDATED_STATUS);
        TypeEtiquetteDTO typeEtiquetteDTO = typeEtiquetteMapper.toDto(updatedTypeEtiquette);

        restTypeEtiquetteMockMvc.perform(put("/api/type-etiquettes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeEtiquetteDTO)))
            .andExpect(status().isOk());

        // Validate the TypeEtiquette in the database
        List<TypeEtiquette> typeEtiquetteList = typeEtiquetteRepository.findAll();
        assertThat(typeEtiquetteList).hasSize(databaseSizeBeforeUpdate);
        TypeEtiquette testTypeEtiquette = typeEtiquetteList.get(typeEtiquetteList.size() - 1);
        assertThat(testTypeEtiquette.getLibelle()).isEqualTo(UPDATED_LIBELLE);
        assertThat(testTypeEtiquette.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingTypeEtiquette() throws Exception {
        int databaseSizeBeforeUpdate = typeEtiquetteRepository.findAll().size();

        // Create the TypeEtiquette
        TypeEtiquetteDTO typeEtiquetteDTO = typeEtiquetteMapper.toDto(typeEtiquette);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTypeEtiquetteMockMvc.perform(put("/api/type-etiquettes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeEtiquetteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TypeEtiquette in the database
        List<TypeEtiquette> typeEtiquetteList = typeEtiquetteRepository.findAll();
        assertThat(typeEtiquetteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTypeEtiquette() throws Exception {
        // Initialize the database
        typeEtiquetteRepository.saveAndFlush(typeEtiquette);

        int databaseSizeBeforeDelete = typeEtiquetteRepository.findAll().size();

        // Delete the typeEtiquette
        restTypeEtiquetteMockMvc.perform(delete("/api/type-etiquettes/{id}", typeEtiquette.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TypeEtiquette> typeEtiquetteList = typeEtiquetteRepository.findAll();
        assertThat(typeEtiquetteList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
