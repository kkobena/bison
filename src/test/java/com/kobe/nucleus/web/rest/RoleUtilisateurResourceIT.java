package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.NucleusApp;
import com.kobe.nucleus.domain.RoleUtilisateur;
import com.kobe.nucleus.repository.RoleUtilisateurRepository;
import com.kobe.nucleus.service.RoleUtilisateurService;
import com.kobe.nucleus.service.dto.RoleUtilisateurDTO;
import com.kobe.nucleus.service.mapper.RoleUtilisateurMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link RoleUtilisateurResource} REST controller.
 */
@SpringBootTest(classes = NucleusApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class RoleUtilisateurResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESC = "AAAAAAAAAA";
    private static final String UPDATED_DESC = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ENABLE = false;
    private static final Boolean UPDATED_ENABLE = true;

    @Autowired
    private RoleUtilisateurRepository roleUtilisateurRepository;

    @Mock
    private RoleUtilisateurRepository roleUtilisateurRepositoryMock;

    @Autowired
    private RoleUtilisateurMapper roleUtilisateurMapper;

    @Mock
    private RoleUtilisateurService roleUtilisateurServiceMock;

    @Autowired
    private RoleUtilisateurService roleUtilisateurService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRoleUtilisateurMockMvc;

    private RoleUtilisateur roleUtilisateur;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RoleUtilisateur createEntity(EntityManager em) {
        RoleUtilisateur roleUtilisateur = new RoleUtilisateur()
            .name(DEFAULT_NAME)
            .desc(DEFAULT_DESC)
            .enable(DEFAULT_ENABLE);
        return roleUtilisateur;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RoleUtilisateur createUpdatedEntity(EntityManager em) {
        RoleUtilisateur roleUtilisateur = new RoleUtilisateur()
            .name(UPDATED_NAME)
            .desc(UPDATED_DESC)
            .enable(UPDATED_ENABLE);
        return roleUtilisateur;
    }

    @BeforeEach
    public void initTest() {
        roleUtilisateur = createEntity(em);
    }

    @Test
    @Transactional
    public void createRoleUtilisateur() throws Exception {
        int databaseSizeBeforeCreate = roleUtilisateurRepository.findAll().size();
        // Create the RoleUtilisateur
        RoleUtilisateurDTO roleUtilisateurDTO = roleUtilisateurMapper.toDto(roleUtilisateur);
        restRoleUtilisateurMockMvc.perform(post("/api/role-utilisateurs").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(roleUtilisateurDTO)))
            .andExpect(status().isCreated());

        // Validate the RoleUtilisateur in the database
        List<RoleUtilisateur> roleUtilisateurList = roleUtilisateurRepository.findAll();
        assertThat(roleUtilisateurList).hasSize(databaseSizeBeforeCreate + 1);
        RoleUtilisateur testRoleUtilisateur = roleUtilisateurList.get(roleUtilisateurList.size() - 1);
        assertThat(testRoleUtilisateur.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testRoleUtilisateur.getDesc()).isEqualTo(DEFAULT_DESC);
        assertThat(testRoleUtilisateur.isEnable()).isEqualTo(DEFAULT_ENABLE);
    }

    @Test
    @Transactional
    public void createRoleUtilisateurWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = roleUtilisateurRepository.findAll().size();

        // Create the RoleUtilisateur with an existing ID
        roleUtilisateur.setId(1L);
        RoleUtilisateurDTO roleUtilisateurDTO = roleUtilisateurMapper.toDto(roleUtilisateur);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRoleUtilisateurMockMvc.perform(post("/api/role-utilisateurs").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(roleUtilisateurDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RoleUtilisateur in the database
        List<RoleUtilisateur> roleUtilisateurList = roleUtilisateurRepository.findAll();
        assertThat(roleUtilisateurList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = roleUtilisateurRepository.findAll().size();
        // set the field null
        roleUtilisateur.setName(null);

        // Create the RoleUtilisateur, which fails.
        RoleUtilisateurDTO roleUtilisateurDTO = roleUtilisateurMapper.toDto(roleUtilisateur);


        restRoleUtilisateurMockMvc.perform(post("/api/role-utilisateurs").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(roleUtilisateurDTO)))
            .andExpect(status().isBadRequest());

        List<RoleUtilisateur> roleUtilisateurList = roleUtilisateurRepository.findAll();
        assertThat(roleUtilisateurList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRoleUtilisateurs() throws Exception {
        // Initialize the database
        roleUtilisateurRepository.saveAndFlush(roleUtilisateur);

        // Get all the roleUtilisateurList
        restRoleUtilisateurMockMvc.perform(get("/api/role-utilisateurs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(roleUtilisateur.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].desc").value(hasItem(DEFAULT_DESC)))
            .andExpect(jsonPath("$.[*].enable").value(hasItem(DEFAULT_ENABLE.booleanValue())));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllRoleUtilisateursWithEagerRelationshipsIsEnabled() throws Exception {
        when(roleUtilisateurServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restRoleUtilisateurMockMvc.perform(get("/api/role-utilisateurs?eagerload=true"))
            .andExpect(status().isOk());

        verify(roleUtilisateurServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllRoleUtilisateursWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(roleUtilisateurServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restRoleUtilisateurMockMvc.perform(get("/api/role-utilisateurs?eagerload=true"))
            .andExpect(status().isOk());

        verify(roleUtilisateurServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getRoleUtilisateur() throws Exception {
        // Initialize the database
        roleUtilisateurRepository.saveAndFlush(roleUtilisateur);

        // Get the roleUtilisateur
        restRoleUtilisateurMockMvc.perform(get("/api/role-utilisateurs/{id}", roleUtilisateur.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(roleUtilisateur.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.desc").value(DEFAULT_DESC))
            .andExpect(jsonPath("$.enable").value(DEFAULT_ENABLE.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingRoleUtilisateur() throws Exception {
        // Get the roleUtilisateur
        restRoleUtilisateurMockMvc.perform(get("/api/role-utilisateurs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRoleUtilisateur() throws Exception {
        // Initialize the database
        roleUtilisateurRepository.saveAndFlush(roleUtilisateur);

        int databaseSizeBeforeUpdate = roleUtilisateurRepository.findAll().size();

        // Update the roleUtilisateur
        RoleUtilisateur updatedRoleUtilisateur = roleUtilisateurRepository.findById(roleUtilisateur.getId()).get();
        // Disconnect from session so that the updates on updatedRoleUtilisateur are not directly saved in db
        em.detach(updatedRoleUtilisateur);
        updatedRoleUtilisateur
            .name(UPDATED_NAME)
            .desc(UPDATED_DESC)
            .enable(UPDATED_ENABLE);
        RoleUtilisateurDTO roleUtilisateurDTO = roleUtilisateurMapper.toDto(updatedRoleUtilisateur);

        restRoleUtilisateurMockMvc.perform(put("/api/role-utilisateurs").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(roleUtilisateurDTO)))
            .andExpect(status().isOk());

        // Validate the RoleUtilisateur in the database
        List<RoleUtilisateur> roleUtilisateurList = roleUtilisateurRepository.findAll();
        assertThat(roleUtilisateurList).hasSize(databaseSizeBeforeUpdate);
        RoleUtilisateur testRoleUtilisateur = roleUtilisateurList.get(roleUtilisateurList.size() - 1);
        assertThat(testRoleUtilisateur.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testRoleUtilisateur.getDesc()).isEqualTo(UPDATED_DESC);
        assertThat(testRoleUtilisateur.isEnable()).isEqualTo(UPDATED_ENABLE);
    }

    @Test
    @Transactional
    public void updateNonExistingRoleUtilisateur() throws Exception {
        int databaseSizeBeforeUpdate = roleUtilisateurRepository.findAll().size();

        // Create the RoleUtilisateur
        RoleUtilisateurDTO roleUtilisateurDTO = roleUtilisateurMapper.toDto(roleUtilisateur);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRoleUtilisateurMockMvc.perform(put("/api/role-utilisateurs").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(roleUtilisateurDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RoleUtilisateur in the database
        List<RoleUtilisateur> roleUtilisateurList = roleUtilisateurRepository.findAll();
        assertThat(roleUtilisateurList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRoleUtilisateur() throws Exception {
        // Initialize the database
        roleUtilisateurRepository.saveAndFlush(roleUtilisateur);

        int databaseSizeBeforeDelete = roleUtilisateurRepository.findAll().size();

        // Delete the roleUtilisateur
        restRoleUtilisateurMockMvc.perform(delete("/api/role-utilisateurs/{id}", roleUtilisateur.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RoleUtilisateur> roleUtilisateurList = roleUtilisateurRepository.findAll();
        assertThat(roleUtilisateurList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
