package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.NucleusApp;
import com.kobe.nucleus.domain.Utilisateur;
import com.kobe.nucleus.repository.UtilisateurRepository;
import com.kobe.nucleus.service.UtilisateurService;
import com.kobe.nucleus.service.dto.UtilisateurDTO;
import com.kobe.nucleus.service.mapper.UtilisateurMapper;

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

import com.kobe.nucleus.domain.enumeration.Status;
/**
 * Integration tests for the {@link UtilisateurResource} REST controller.
 */
@SpringBootTest(classes = NucleusApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class UtilisateurResourceIT {

    private static final LocalDate DEFAULT_CREATED_AT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_AT = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_UPDATED_AT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_AT = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_USER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_USER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PWORD = "AAAAAAAAAA";
    private static final String UPDATED_PWORD = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ENABLE = false;
    private static final Boolean UPDATED_ENABLE = true;

    private static final String DEFAULT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE = "BBBBBBBBBB";

    private static final Status DEFAULT_STATUS = Status.ACTIVE;
    private static final Status UPDATED_STATUS = Status.ENATTENTE;

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private UtilisateurMapper utilisateurMapper;

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUtilisateurMockMvc;

    private Utilisateur utilisateur;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Utilisateur createEntity(EntityManager em) {
        Utilisateur utilisateur = new Utilisateur()
            .createdAt(DEFAULT_CREATED_AT)
            .updatedAt(DEFAULT_UPDATED_AT)
            .lastName(DEFAULT_LAST_NAME)
            .firstName(DEFAULT_FIRST_NAME)
            .userName(DEFAULT_USER_NAME)
            .pword(DEFAULT_PWORD)
            .enable(DEFAULT_ENABLE)
            .phone(DEFAULT_PHONE)
            .status(DEFAULT_STATUS)
            .email(DEFAULT_EMAIL);
        return utilisateur;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Utilisateur createUpdatedEntity(EntityManager em) {
        Utilisateur utilisateur = new Utilisateur()
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .lastName(UPDATED_LAST_NAME)
            .firstName(UPDATED_FIRST_NAME)
            .userName(UPDATED_USER_NAME)
            .pword(UPDATED_PWORD)
            .enable(UPDATED_ENABLE)
            .phone(UPDATED_PHONE)
            .status(UPDATED_STATUS)
            .email(UPDATED_EMAIL);
        return utilisateur;
    }

    @BeforeEach
    public void initTest() {
        utilisateur = createEntity(em);
    }

    @Test
    @Transactional
    public void createUtilisateur() throws Exception {
        int databaseSizeBeforeCreate = utilisateurRepository.findAll().size();
        // Create the Utilisateur
        UtilisateurDTO utilisateurDTO = utilisateurMapper.toDto(utilisateur);
        restUtilisateurMockMvc.perform(post("/api/utilisateurs").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(utilisateurDTO)))
            .andExpect(status().isCreated());

        // Validate the Utilisateur in the database
        List<Utilisateur> utilisateurList = utilisateurRepository.findAll();
        assertThat(utilisateurList).hasSize(databaseSizeBeforeCreate + 1);
        Utilisateur testUtilisateur = utilisateurList.get(utilisateurList.size() - 1);
        assertThat(testUtilisateur.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testUtilisateur.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testUtilisateur.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testUtilisateur.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testUtilisateur.getUserName()).isEqualTo(DEFAULT_USER_NAME);
        assertThat(testUtilisateur.getPword()).isEqualTo(DEFAULT_PWORD);
        assertThat(testUtilisateur.isEnable()).isEqualTo(DEFAULT_ENABLE);
        assertThat(testUtilisateur.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testUtilisateur.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testUtilisateur.getEmail()).isEqualTo(DEFAULT_EMAIL);
    }

    @Test
    @Transactional
    public void createUtilisateurWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = utilisateurRepository.findAll().size();

        // Create the Utilisateur with an existing ID
        utilisateur.setId(1L);
        UtilisateurDTO utilisateurDTO = utilisateurMapper.toDto(utilisateur);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUtilisateurMockMvc.perform(post("/api/utilisateurs").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(utilisateurDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Utilisateur in the database
        List<Utilisateur> utilisateurList = utilisateurRepository.findAll();
        assertThat(utilisateurList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCreatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = utilisateurRepository.findAll().size();
        // set the field null
        utilisateur.setCreatedAt(null);

        // Create the Utilisateur, which fails.
        UtilisateurDTO utilisateurDTO = utilisateurMapper.toDto(utilisateur);


        restUtilisateurMockMvc.perform(post("/api/utilisateurs").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(utilisateurDTO)))
            .andExpect(status().isBadRequest());

        List<Utilisateur> utilisateurList = utilisateurRepository.findAll();
        assertThat(utilisateurList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUpdatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = utilisateurRepository.findAll().size();
        // set the field null
        utilisateur.setUpdatedAt(null);

        // Create the Utilisateur, which fails.
        UtilisateurDTO utilisateurDTO = utilisateurMapper.toDto(utilisateur);


        restUtilisateurMockMvc.perform(post("/api/utilisateurs").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(utilisateurDTO)))
            .andExpect(status().isBadRequest());

        List<Utilisateur> utilisateurList = utilisateurRepository.findAll();
        assertThat(utilisateurList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFirstNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = utilisateurRepository.findAll().size();
        // set the field null
        utilisateur.setFirstName(null);

        // Create the Utilisateur, which fails.
        UtilisateurDTO utilisateurDTO = utilisateurMapper.toDto(utilisateur);


        restUtilisateurMockMvc.perform(post("/api/utilisateurs").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(utilisateurDTO)))
            .andExpect(status().isBadRequest());

        List<Utilisateur> utilisateurList = utilisateurRepository.findAll();
        assertThat(utilisateurList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUserNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = utilisateurRepository.findAll().size();
        // set the field null
        utilisateur.setUserName(null);

        // Create the Utilisateur, which fails.
        UtilisateurDTO utilisateurDTO = utilisateurMapper.toDto(utilisateur);


        restUtilisateurMockMvc.perform(post("/api/utilisateurs").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(utilisateurDTO)))
            .andExpect(status().isBadRequest());

        List<Utilisateur> utilisateurList = utilisateurRepository.findAll();
        assertThat(utilisateurList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPwordIsRequired() throws Exception {
        int databaseSizeBeforeTest = utilisateurRepository.findAll().size();
        // set the field null
        utilisateur.setPword(null);

        // Create the Utilisateur, which fails.
        UtilisateurDTO utilisateurDTO = utilisateurMapper.toDto(utilisateur);


        restUtilisateurMockMvc.perform(post("/api/utilisateurs").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(utilisateurDTO)))
            .andExpect(status().isBadRequest());

        List<Utilisateur> utilisateurList = utilisateurRepository.findAll();
        assertThat(utilisateurList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = utilisateurRepository.findAll().size();
        // set the field null
        utilisateur.setStatus(null);

        // Create the Utilisateur, which fails.
        UtilisateurDTO utilisateurDTO = utilisateurMapper.toDto(utilisateur);


        restUtilisateurMockMvc.perform(post("/api/utilisateurs").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(utilisateurDTO)))
            .andExpect(status().isBadRequest());

        List<Utilisateur> utilisateurList = utilisateurRepository.findAll();
        assertThat(utilisateurList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllUtilisateurs() throws Exception {
        // Initialize the database
        utilisateurRepository.saveAndFlush(utilisateur);

        // Get all the utilisateurList
        restUtilisateurMockMvc.perform(get("/api/utilisateurs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(utilisateur.getId().intValue())))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(DEFAULT_UPDATED_AT.toString())))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].userName").value(hasItem(DEFAULT_USER_NAME)))
            .andExpect(jsonPath("$.[*].pword").value(hasItem(DEFAULT_PWORD)))
            .andExpect(jsonPath("$.[*].enable").value(hasItem(DEFAULT_ENABLE.booleanValue())))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)));
    }
    
    @Test
    @Transactional
    public void getUtilisateur() throws Exception {
        // Initialize the database
        utilisateurRepository.saveAndFlush(utilisateur);

        // Get the utilisateur
        restUtilisateurMockMvc.perform(get("/api/utilisateurs/{id}", utilisateur.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(utilisateur.getId().intValue()))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.updatedAt").value(DEFAULT_UPDATED_AT.toString()))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME))
            .andExpect(jsonPath("$.userName").value(DEFAULT_USER_NAME))
            .andExpect(jsonPath("$.pword").value(DEFAULT_PWORD))
            .andExpect(jsonPath("$.enable").value(DEFAULT_ENABLE.booleanValue()))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL));
    }
    @Test
    @Transactional
    public void getNonExistingUtilisateur() throws Exception {
        // Get the utilisateur
        restUtilisateurMockMvc.perform(get("/api/utilisateurs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUtilisateur() throws Exception {
        // Initialize the database
        utilisateurRepository.saveAndFlush(utilisateur);

        int databaseSizeBeforeUpdate = utilisateurRepository.findAll().size();

        // Update the utilisateur
        Utilisateur updatedUtilisateur = utilisateurRepository.findById(utilisateur.getId()).get();
        // Disconnect from session so that the updates on updatedUtilisateur are not directly saved in db
        em.detach(updatedUtilisateur);
        updatedUtilisateur
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .lastName(UPDATED_LAST_NAME)
            .firstName(UPDATED_FIRST_NAME)
            .userName(UPDATED_USER_NAME)
            .pword(UPDATED_PWORD)
            .enable(UPDATED_ENABLE)
            .phone(UPDATED_PHONE)
            .status(UPDATED_STATUS)
            .email(UPDATED_EMAIL);
        UtilisateurDTO utilisateurDTO = utilisateurMapper.toDto(updatedUtilisateur);

        restUtilisateurMockMvc.perform(put("/api/utilisateurs").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(utilisateurDTO)))
            .andExpect(status().isOk());

        // Validate the Utilisateur in the database
        List<Utilisateur> utilisateurList = utilisateurRepository.findAll();
        assertThat(utilisateurList).hasSize(databaseSizeBeforeUpdate);
        Utilisateur testUtilisateur = utilisateurList.get(utilisateurList.size() - 1);
        assertThat(testUtilisateur.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testUtilisateur.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testUtilisateur.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testUtilisateur.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testUtilisateur.getUserName()).isEqualTo(UPDATED_USER_NAME);
        assertThat(testUtilisateur.getPword()).isEqualTo(UPDATED_PWORD);
        assertThat(testUtilisateur.isEnable()).isEqualTo(UPDATED_ENABLE);
        assertThat(testUtilisateur.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testUtilisateur.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testUtilisateur.getEmail()).isEqualTo(UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void updateNonExistingUtilisateur() throws Exception {
        int databaseSizeBeforeUpdate = utilisateurRepository.findAll().size();

        // Create the Utilisateur
        UtilisateurDTO utilisateurDTO = utilisateurMapper.toDto(utilisateur);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUtilisateurMockMvc.perform(put("/api/utilisateurs").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(utilisateurDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Utilisateur in the database
        List<Utilisateur> utilisateurList = utilisateurRepository.findAll();
        assertThat(utilisateurList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteUtilisateur() throws Exception {
        // Initialize the database
        utilisateurRepository.saveAndFlush(utilisateur);

        int databaseSizeBeforeDelete = utilisateurRepository.findAll().size();

        // Delete the utilisateur
        restUtilisateurMockMvc.perform(delete("/api/utilisateurs/{id}", utilisateur.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Utilisateur> utilisateurList = utilisateurRepository.findAll();
        assertThat(utilisateurList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
