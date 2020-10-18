package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.NucleusApp;
import com.kobe.nucleus.domain.TypeInventaire;
import com.kobe.nucleus.repository.TypeInventaireRepository;
import com.kobe.nucleus.service.TypeInventaireService;
import com.kobe.nucleus.service.dto.TypeInventaireDTO;
import com.kobe.nucleus.service.mapper.TypeInventaireMapper;

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
 * Integration tests for the {@link TypeInventaireResource} REST controller.
 */
@SpringBootTest(classes = NucleusApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TypeInventaireResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    private static final Status DEFAULT_STATUS = Status.ACTIVE;
    private static final Status UPDATED_STATUS = Status.ENATTENTE;

    @Autowired
    private TypeInventaireRepository typeInventaireRepository;

    @Autowired
    private TypeInventaireMapper typeInventaireMapper;

    @Autowired
    private TypeInventaireService typeInventaireService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTypeInventaireMockMvc;

    private TypeInventaire typeInventaire;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TypeInventaire createEntity(EntityManager em) {
        TypeInventaire typeInventaire = new TypeInventaire()
            .libelle(DEFAULT_LIBELLE)
            ;
        return typeInventaire;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TypeInventaire createUpdatedEntity(EntityManager em) {
        TypeInventaire typeInventaire = new TypeInventaire()
            .libelle(UPDATED_LIBELLE)
           ;
        return typeInventaire;
    }

    @BeforeEach
    public void initTest() {
        typeInventaire = createEntity(em);
    }

    @Test
    @Transactional
    public void createTypeInventaire() throws Exception {
        int databaseSizeBeforeCreate = typeInventaireRepository.findAll().size();
        // Create the TypeInventaire
        TypeInventaireDTO typeInventaireDTO = typeInventaireMapper.toDto(typeInventaire);
        restTypeInventaireMockMvc.perform(post("/api/type-inventaires").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeInventaireDTO)))
            .andExpect(status().isCreated());

        // Validate the TypeInventaire in the database
        List<TypeInventaire> typeInventaireList = typeInventaireRepository.findAll();
        assertThat(typeInventaireList).hasSize(databaseSizeBeforeCreate + 1);
        TypeInventaire testTypeInventaire = typeInventaireList.get(typeInventaireList.size() - 1);
        assertThat(testTypeInventaire.getLibelle()).isEqualTo(DEFAULT_LIBELLE);

    }

    @Test
    @Transactional
    public void createTypeInventaireWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = typeInventaireRepository.findAll().size();

        // Create the TypeInventaire with an existing ID
        typeInventaire.setId(1L);
        TypeInventaireDTO typeInventaireDTO = typeInventaireMapper.toDto(typeInventaire);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTypeInventaireMockMvc.perform(post("/api/type-inventaires").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeInventaireDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TypeInventaire in the database
        List<TypeInventaire> typeInventaireList = typeInventaireRepository.findAll();
        assertThat(typeInventaireList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLibelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = typeInventaireRepository.findAll().size();
        // set the field null
        typeInventaire.setLibelle(null);

        // Create the TypeInventaire, which fails.
        TypeInventaireDTO typeInventaireDTO = typeInventaireMapper.toDto(typeInventaire);


        restTypeInventaireMockMvc.perform(post("/api/type-inventaires").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeInventaireDTO)))
            .andExpect(status().isBadRequest());

        List<TypeInventaire> typeInventaireList = typeInventaireRepository.findAll();
        assertThat(typeInventaireList).hasSize(databaseSizeBeforeTest);
    }



    @Test
    @Transactional
    public void getAllTypeInventaires() throws Exception {
        // Initialize the database
        typeInventaireRepository.saveAndFlush(typeInventaire);

        // Get all the typeInventaireList
        restTypeInventaireMockMvc.perform(get("/api/type-inventaires?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(typeInventaire.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)))
            ;
    }

    @Test
    @Transactional
    public void getTypeInventaire() throws Exception {
        // Initialize the database
        typeInventaireRepository.saveAndFlush(typeInventaire);

        // Get the typeInventaire
        restTypeInventaireMockMvc.perform(get("/api/type-inventaires/{id}", typeInventaire.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(typeInventaire.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE))
            ;
    }
    @Test
    @Transactional
    public void getNonExistingTypeInventaire() throws Exception {
        // Get the typeInventaire
        restTypeInventaireMockMvc.perform(get("/api/type-inventaires/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTypeInventaire() throws Exception {
        // Initialize the database
        typeInventaireRepository.saveAndFlush(typeInventaire);

        int databaseSizeBeforeUpdate = typeInventaireRepository.findAll().size();

        // Update the typeInventaire
        TypeInventaire updatedTypeInventaire = typeInventaireRepository.findById(typeInventaire.getId()).get();
        // Disconnect from session so that the updates on updatedTypeInventaire are not directly saved in db
        em.detach(updatedTypeInventaire);
        updatedTypeInventaire
            .libelle(UPDATED_LIBELLE)
           ;
        TypeInventaireDTO typeInventaireDTO = typeInventaireMapper.toDto(updatedTypeInventaire);

        restTypeInventaireMockMvc.perform(put("/api/type-inventaires").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeInventaireDTO)))
            .andExpect(status().isOk());

        // Validate the TypeInventaire in the database
        List<TypeInventaire> typeInventaireList = typeInventaireRepository.findAll();
        assertThat(typeInventaireList).hasSize(databaseSizeBeforeUpdate);
        TypeInventaire testTypeInventaire = typeInventaireList.get(typeInventaireList.size() - 1);
        assertThat(testTypeInventaire.getLibelle()).isEqualTo(UPDATED_LIBELLE);

    }

    @Test
    @Transactional
    public void updateNonExistingTypeInventaire() throws Exception {
        int databaseSizeBeforeUpdate = typeInventaireRepository.findAll().size();

        // Create the TypeInventaire
        TypeInventaireDTO typeInventaireDTO = typeInventaireMapper.toDto(typeInventaire);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTypeInventaireMockMvc.perform(put("/api/type-inventaires").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeInventaireDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TypeInventaire in the database
        List<TypeInventaire> typeInventaireList = typeInventaireRepository.findAll();
        assertThat(typeInventaireList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTypeInventaire() throws Exception {
        // Initialize the database
        typeInventaireRepository.saveAndFlush(typeInventaire);

        int databaseSizeBeforeDelete = typeInventaireRepository.findAll().size();

        // Delete the typeInventaire
        restTypeInventaireMockMvc.perform(delete("/api/type-inventaires/{id}", typeInventaire.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TypeInventaire> typeInventaireList = typeInventaireRepository.findAll();
        assertThat(typeInventaireList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
