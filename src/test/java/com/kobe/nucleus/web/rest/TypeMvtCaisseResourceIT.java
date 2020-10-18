package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.NucleusApp;
import com.kobe.nucleus.domain.TypeMvtCaisse;
import com.kobe.nucleus.repository.TypeMvtCaisseRepository;
import com.kobe.nucleus.service.TypeMvtCaisseService;
import com.kobe.nucleus.service.dto.TypeMvtCaisseDTO;
import com.kobe.nucleus.service.mapper.TypeMvtCaisseMapper;

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

import com.kobe.nucleus.domain.enumeration.CatMvtCaisse;
import com.kobe.nucleus.domain.enumeration.Status;
/**
 * Integration tests for the {@link TypeMvtCaisseResource} REST controller.
 */
@SpringBootTest(classes = NucleusApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TypeMvtCaisseResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final CatMvtCaisse DEFAULT_CATEGORIE = CatMvtCaisse.ENTREE;
    private static final CatMvtCaisse UPDATED_CATEGORIE = CatMvtCaisse.SORTIE;



    @Autowired
    private TypeMvtCaisseRepository typeMvtCaisseRepository;

    @Autowired
    private TypeMvtCaisseMapper typeMvtCaisseMapper;

    @Autowired
    private TypeMvtCaisseService typeMvtCaisseService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTypeMvtCaisseMockMvc;

    private TypeMvtCaisse typeMvtCaisse;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TypeMvtCaisse createEntity(EntityManager em) {
        TypeMvtCaisse typeMvtCaisse = new TypeMvtCaisse()
            .libelle(DEFAULT_LIBELLE)
            .code(DEFAULT_CODE)
            .categorie(DEFAULT_CATEGORIE)
            ;
        return typeMvtCaisse;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TypeMvtCaisse createUpdatedEntity(EntityManager em) {
        TypeMvtCaisse typeMvtCaisse = new TypeMvtCaisse()
            .libelle(UPDATED_LIBELLE)
            .code(UPDATED_CODE)
            .categorie(UPDATED_CATEGORIE)
            ;
        return typeMvtCaisse;
    }

    @BeforeEach
    public void initTest() {
        typeMvtCaisse = createEntity(em);
    }

    @Test
    @Transactional
    public void createTypeMvtCaisse() throws Exception {
        int databaseSizeBeforeCreate = typeMvtCaisseRepository.findAll().size();
        // Create the TypeMvtCaisse
        TypeMvtCaisseDTO typeMvtCaisseDTO = typeMvtCaisseMapper.toDto(typeMvtCaisse);
        restTypeMvtCaisseMockMvc.perform(post("/api/type-mvt-caisses").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeMvtCaisseDTO)))
            .andExpect(status().isCreated());

        // Validate the TypeMvtCaisse in the database
        List<TypeMvtCaisse> typeMvtCaisseList = typeMvtCaisseRepository.findAll();
        assertThat(typeMvtCaisseList).hasSize(databaseSizeBeforeCreate + 1);
        TypeMvtCaisse testTypeMvtCaisse = typeMvtCaisseList.get(typeMvtCaisseList.size() - 1);
        assertThat(testTypeMvtCaisse.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
        assertThat(testTypeMvtCaisse.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testTypeMvtCaisse.getCategorie()).isEqualTo(DEFAULT_CATEGORIE);

    }

    @Test
    @Transactional
    public void createTypeMvtCaisseWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = typeMvtCaisseRepository.findAll().size();

        // Create the TypeMvtCaisse with an existing ID
        typeMvtCaisse.setId(1L);
        TypeMvtCaisseDTO typeMvtCaisseDTO = typeMvtCaisseMapper.toDto(typeMvtCaisse);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTypeMvtCaisseMockMvc.perform(post("/api/type-mvt-caisses").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeMvtCaisseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TypeMvtCaisse in the database
        List<TypeMvtCaisse> typeMvtCaisseList = typeMvtCaisseRepository.findAll();
        assertThat(typeMvtCaisseList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLibelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = typeMvtCaisseRepository.findAll().size();
        // set the field null
        typeMvtCaisse.setLibelle(null);

        // Create the TypeMvtCaisse, which fails.
        TypeMvtCaisseDTO typeMvtCaisseDTO = typeMvtCaisseMapper.toDto(typeMvtCaisse);


        restTypeMvtCaisseMockMvc.perform(post("/api/type-mvt-caisses").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeMvtCaisseDTO)))
            .andExpect(status().isBadRequest());

        List<TypeMvtCaisse> typeMvtCaisseList = typeMvtCaisseRepository.findAll();
        assertThat(typeMvtCaisseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = typeMvtCaisseRepository.findAll().size();
        // set the field null
        typeMvtCaisse.setCode(null);

        // Create the TypeMvtCaisse, which fails.
        TypeMvtCaisseDTO typeMvtCaisseDTO = typeMvtCaisseMapper.toDto(typeMvtCaisse);


        restTypeMvtCaisseMockMvc.perform(post("/api/type-mvt-caisses").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeMvtCaisseDTO)))
            .andExpect(status().isBadRequest());

        List<TypeMvtCaisse> typeMvtCaisseList = typeMvtCaisseRepository.findAll();
        assertThat(typeMvtCaisseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCategorieIsRequired() throws Exception {
        int databaseSizeBeforeTest = typeMvtCaisseRepository.findAll().size();
        // set the field null
        typeMvtCaisse.setCategorie(null);

        // Create the TypeMvtCaisse, which fails.
        TypeMvtCaisseDTO typeMvtCaisseDTO = typeMvtCaisseMapper.toDto(typeMvtCaisse);


        restTypeMvtCaisseMockMvc.perform(post("/api/type-mvt-caisses").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeMvtCaisseDTO)))
            .andExpect(status().isBadRequest());

        List<TypeMvtCaisse> typeMvtCaisseList = typeMvtCaisseRepository.findAll();
        assertThat(typeMvtCaisseList).hasSize(databaseSizeBeforeTest);
    }


    @Test
    @Transactional
    public void getAllTypeMvtCaisses() throws Exception {
        // Initialize the database
        typeMvtCaisseRepository.saveAndFlush(typeMvtCaisse);

        // Get all the typeMvtCaisseList
        restTypeMvtCaisseMockMvc.perform(get("/api/type-mvt-caisses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(typeMvtCaisse.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].categorie").value(hasItem(DEFAULT_CATEGORIE.toString())))
          ;
    }

    @Test
    @Transactional
    public void getTypeMvtCaisse() throws Exception {
        // Initialize the database
        typeMvtCaisseRepository.saveAndFlush(typeMvtCaisse);

        // Get the typeMvtCaisse
        restTypeMvtCaisseMockMvc.perform(get("/api/type-mvt-caisses/{id}", typeMvtCaisse.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(typeMvtCaisse.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.categorie").value(DEFAULT_CATEGORIE.toString()))
            ;
    }
    @Test
    @Transactional
    public void getNonExistingTypeMvtCaisse() throws Exception {
        // Get the typeMvtCaisse
        restTypeMvtCaisseMockMvc.perform(get("/api/type-mvt-caisses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTypeMvtCaisse() throws Exception {
        // Initialize the database
        typeMvtCaisseRepository.saveAndFlush(typeMvtCaisse);

        int databaseSizeBeforeUpdate = typeMvtCaisseRepository.findAll().size();

        // Update the typeMvtCaisse
        TypeMvtCaisse updatedTypeMvtCaisse = typeMvtCaisseRepository.findById(typeMvtCaisse.getId()).get();
        // Disconnect from session so that the updates on updatedTypeMvtCaisse are not directly saved in db
        em.detach(updatedTypeMvtCaisse);
        updatedTypeMvtCaisse
            .libelle(UPDATED_LIBELLE)
            .code(UPDATED_CODE)
            .categorie(UPDATED_CATEGORIE)
            ;
        TypeMvtCaisseDTO typeMvtCaisseDTO = typeMvtCaisseMapper.toDto(updatedTypeMvtCaisse);

        restTypeMvtCaisseMockMvc.perform(put("/api/type-mvt-caisses").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeMvtCaisseDTO)))
            .andExpect(status().isOk());

        // Validate the TypeMvtCaisse in the database
        List<TypeMvtCaisse> typeMvtCaisseList = typeMvtCaisseRepository.findAll();
        assertThat(typeMvtCaisseList).hasSize(databaseSizeBeforeUpdate);
        TypeMvtCaisse testTypeMvtCaisse = typeMvtCaisseList.get(typeMvtCaisseList.size() - 1);
        assertThat(testTypeMvtCaisse.getLibelle()).isEqualTo(UPDATED_LIBELLE);
        assertThat(testTypeMvtCaisse.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testTypeMvtCaisse.getCategorie()).isEqualTo(UPDATED_CATEGORIE);

    }

    @Test
    @Transactional
    public void updateNonExistingTypeMvtCaisse() throws Exception {
        int databaseSizeBeforeUpdate = typeMvtCaisseRepository.findAll().size();

        // Create the TypeMvtCaisse
        TypeMvtCaisseDTO typeMvtCaisseDTO = typeMvtCaisseMapper.toDto(typeMvtCaisse);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTypeMvtCaisseMockMvc.perform(put("/api/type-mvt-caisses").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeMvtCaisseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TypeMvtCaisse in the database
        List<TypeMvtCaisse> typeMvtCaisseList = typeMvtCaisseRepository.findAll();
        assertThat(typeMvtCaisseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTypeMvtCaisse() throws Exception {
        // Initialize the database
        typeMvtCaisseRepository.saveAndFlush(typeMvtCaisse);

        int databaseSizeBeforeDelete = typeMvtCaisseRepository.findAll().size();

        // Delete the typeMvtCaisse
        restTypeMvtCaisseMockMvc.perform(delete("/api/type-mvt-caisses/{id}", typeMvtCaisse.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TypeMvtCaisse> typeMvtCaisseList = typeMvtCaisseRepository.findAll();
        assertThat(typeMvtCaisseList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
