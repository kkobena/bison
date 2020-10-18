package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.NucleusApp;
import com.kobe.nucleus.domain.TypeRisque;
import com.kobe.nucleus.repository.TypeRisqueRepository;
import com.kobe.nucleus.service.TypeRisqueService;
import com.kobe.nucleus.service.dto.TypeRisqueDTO;
import com.kobe.nucleus.service.mapper.TypeRisqueMapper;

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
 * Integration tests for the {@link TypeRisqueResource} REST controller.
 */
@SpringBootTest(classes = NucleusApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TypeRisqueResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";
    @Autowired
    private TypeRisqueRepository typeRisqueRepository;

    @Autowired
    private TypeRisqueMapper typeRisqueMapper;

    @Autowired
    private TypeRisqueService typeRisqueService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTypeRisqueMockMvc;

    private TypeRisque typeRisque;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TypeRisque createEntity(EntityManager em) {
        TypeRisque typeRisque = new TypeRisque()
            .code(DEFAULT_CODE)
            .libelle(DEFAULT_LIBELLE)
            ;
        return typeRisque;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TypeRisque createUpdatedEntity(EntityManager em) {
        TypeRisque typeRisque = new TypeRisque()
            .code(UPDATED_CODE)
            .libelle(UPDATED_LIBELLE)
            ;
        return typeRisque;
    }

    @BeforeEach
    public void initTest() {
        typeRisque = createEntity(em);
    }

    @Test
    @Transactional
    public void createTypeRisque() throws Exception {
        int databaseSizeBeforeCreate = typeRisqueRepository.findAll().size();
        // Create the TypeRisque
        TypeRisqueDTO typeRisqueDTO = typeRisqueMapper.toDto(typeRisque);
        restTypeRisqueMockMvc.perform(post("/api/type-risques").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeRisqueDTO)))
            .andExpect(status().isCreated());

        // Validate the TypeRisque in the database
        List<TypeRisque> typeRisqueList = typeRisqueRepository.findAll();
        assertThat(typeRisqueList).hasSize(databaseSizeBeforeCreate + 1);
        TypeRisque testTypeRisque = typeRisqueList.get(typeRisqueList.size() - 1);
        assertThat(testTypeRisque.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testTypeRisque.getLibelle()).isEqualTo(DEFAULT_LIBELLE);

    }

    @Test
    @Transactional
    public void createTypeRisqueWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = typeRisqueRepository.findAll().size();

        // Create the TypeRisque with an existing ID
        typeRisque.setId(1L);
        TypeRisqueDTO typeRisqueDTO = typeRisqueMapper.toDto(typeRisque);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTypeRisqueMockMvc.perform(post("/api/type-risques").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeRisqueDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TypeRisque in the database
        List<TypeRisque> typeRisqueList = typeRisqueRepository.findAll();
        assertThat(typeRisqueList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = typeRisqueRepository.findAll().size();
        // set the field null
        typeRisque.setCode(null);

        // Create the TypeRisque, which fails.
        TypeRisqueDTO typeRisqueDTO = typeRisqueMapper.toDto(typeRisque);


        restTypeRisqueMockMvc.perform(post("/api/type-risques").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeRisqueDTO)))
            .andExpect(status().isBadRequest());

        List<TypeRisque> typeRisqueList = typeRisqueRepository.findAll();
        assertThat(typeRisqueList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLibelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = typeRisqueRepository.findAll().size();
        // set the field null
        typeRisque.setLibelle(null);

        // Create the TypeRisque, which fails.
        TypeRisqueDTO typeRisqueDTO = typeRisqueMapper.toDto(typeRisque);


        restTypeRisqueMockMvc.perform(post("/api/type-risques").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeRisqueDTO)))
            .andExpect(status().isBadRequest());

        List<TypeRisque> typeRisqueList = typeRisqueRepository.findAll();
        assertThat(typeRisqueList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTypeRisques() throws Exception {
        // Initialize the database
        typeRisqueRepository.saveAndFlush(typeRisque);

        // Get all the typeRisqueList
        restTypeRisqueMockMvc.perform(get("/api/type-risques?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(typeRisque.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)))
            ;
    }

    @Test
    @Transactional
    public void getTypeRisque() throws Exception {
        // Initialize the database
        typeRisqueRepository.saveAndFlush(typeRisque);

        // Get the typeRisque
        restTypeRisqueMockMvc.perform(get("/api/type-risques/{id}", typeRisque.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(typeRisque.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE))
            ;
    }
    @Test
    @Transactional
    public void getNonExistingTypeRisque() throws Exception {
        // Get the typeRisque
        restTypeRisqueMockMvc.perform(get("/api/type-risques/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTypeRisque() throws Exception {
        // Initialize the database
        typeRisqueRepository.saveAndFlush(typeRisque);

        int databaseSizeBeforeUpdate = typeRisqueRepository.findAll().size();

        // Update the typeRisque
        TypeRisque updatedTypeRisque = typeRisqueRepository.findById(typeRisque.getId()).get();
        // Disconnect from session so that the updates on updatedTypeRisque are not directly saved in db
        em.detach(updatedTypeRisque);
        updatedTypeRisque
            .code(UPDATED_CODE)
            .libelle(UPDATED_LIBELLE)
          ;
        TypeRisqueDTO typeRisqueDTO = typeRisqueMapper.toDto(updatedTypeRisque);

        restTypeRisqueMockMvc.perform(put("/api/type-risques").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeRisqueDTO)))
            .andExpect(status().isOk());

        // Validate the TypeRisque in the database
        List<TypeRisque> typeRisqueList = typeRisqueRepository.findAll();
        assertThat(typeRisqueList).hasSize(databaseSizeBeforeUpdate);
        TypeRisque testTypeRisque = typeRisqueList.get(typeRisqueList.size() - 1);
        assertThat(testTypeRisque.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testTypeRisque.getLibelle()).isEqualTo(UPDATED_LIBELLE);

    }

    @Test
    @Transactional
    public void updateNonExistingTypeRisque() throws Exception {
        int databaseSizeBeforeUpdate = typeRisqueRepository.findAll().size();

        // Create the TypeRisque
        TypeRisqueDTO typeRisqueDTO = typeRisqueMapper.toDto(typeRisque);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTypeRisqueMockMvc.perform(put("/api/type-risques").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeRisqueDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TypeRisque in the database
        List<TypeRisque> typeRisqueList = typeRisqueRepository.findAll();
        assertThat(typeRisqueList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTypeRisque() throws Exception {
        // Initialize the database
        typeRisqueRepository.saveAndFlush(typeRisque);

        int databaseSizeBeforeDelete = typeRisqueRepository.findAll().size();

        // Delete the typeRisque
        restTypeRisqueMockMvc.perform(delete("/api/type-risques/{id}", typeRisque.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TypeRisque> typeRisqueList = typeRisqueRepository.findAll();
        assertThat(typeRisqueList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
