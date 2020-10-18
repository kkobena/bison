package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.NucleusApp;
import com.kobe.nucleus.domain.GroupeFournisseur;
import com.kobe.nucleus.repository.GroupeFournisseurRepository;
import com.kobe.nucleus.service.GroupeFournisseurService;
import com.kobe.nucleus.service.dto.GroupeFournisseurDTO;
import com.kobe.nucleus.service.mapper.GroupeFournisseurMapper;

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
 * Integration tests for the {@link GroupeFournisseurResource} REST controller.
 */
@SpringBootTest(classes = NucleusApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class GroupeFournisseurResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESSPOSTALE = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESSPOSTALE = "BBBBBBBBBB";

    private static final String DEFAULT_NUM_FAXE = "AAAAAAAAAA";
    private static final String UPDATED_NUM_FAXE = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_TEL = "AAAAAAAAAA";
    private static final String UPDATED_TEL = "BBBBBBBBBB";

    private static final Status DEFAULT_STATUS = Status.ACTIVE;
    private static final Status UPDATED_STATUS = Status.ENATTENTE;

    private static final Integer DEFAULT_ODRE = 1;
    private static final Integer UPDATED_ODRE = 2;

    @Autowired
    private GroupeFournisseurRepository groupeFournisseurRepository;

    @Autowired
    private GroupeFournisseurMapper groupeFournisseurMapper;

    @Autowired
    private GroupeFournisseurService groupeFournisseurService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restGroupeFournisseurMockMvc;

    private GroupeFournisseur groupeFournisseur;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GroupeFournisseur createEntity(EntityManager em) {
        GroupeFournisseur groupeFournisseur = new GroupeFournisseur()
            .libelle(DEFAULT_LIBELLE)
            .addresspostale(DEFAULT_ADDRESSPOSTALE)
            .numFaxe(DEFAULT_NUM_FAXE)
            .email(DEFAULT_EMAIL)
            .tel(DEFAULT_TEL)
            .status(DEFAULT_STATUS)
            .odre(DEFAULT_ODRE);
        return groupeFournisseur;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GroupeFournisseur createUpdatedEntity(EntityManager em) {
        GroupeFournisseur groupeFournisseur = new GroupeFournisseur()
            .libelle(UPDATED_LIBELLE)
            .addresspostale(UPDATED_ADDRESSPOSTALE)
            .numFaxe(UPDATED_NUM_FAXE)
            .email(UPDATED_EMAIL)
            .tel(UPDATED_TEL)
            .status(UPDATED_STATUS)
            .odre(UPDATED_ODRE);
        return groupeFournisseur;
    }

    @BeforeEach
    public void initTest() {
        groupeFournisseur = createEntity(em);
    }

    @Test
    @Transactional
    public void createGroupeFournisseur() throws Exception {
        int databaseSizeBeforeCreate = groupeFournisseurRepository.findAll().size();
        // Create the GroupeFournisseur
        GroupeFournisseurDTO groupeFournisseurDTO = groupeFournisseurMapper.toDto(groupeFournisseur);
        restGroupeFournisseurMockMvc.perform(post("/api/groupe-fournisseurs").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(groupeFournisseurDTO)))
            .andExpect(status().isCreated());

        // Validate the GroupeFournisseur in the database
        List<GroupeFournisseur> groupeFournisseurList = groupeFournisseurRepository.findAll();
        assertThat(groupeFournisseurList).hasSize(databaseSizeBeforeCreate + 1);
        GroupeFournisseur testGroupeFournisseur = groupeFournisseurList.get(groupeFournisseurList.size() - 1);
        assertThat(testGroupeFournisseur.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
        assertThat(testGroupeFournisseur.getAddresspostale()).isEqualTo(DEFAULT_ADDRESSPOSTALE);
        assertThat(testGroupeFournisseur.getNumFaxe()).isEqualTo(DEFAULT_NUM_FAXE);
        assertThat(testGroupeFournisseur.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testGroupeFournisseur.getTel()).isEqualTo(DEFAULT_TEL);
        assertThat(testGroupeFournisseur.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testGroupeFournisseur.getOdre()).isEqualTo(DEFAULT_ODRE);
    }

    @Test
    @Transactional
    public void createGroupeFournisseurWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = groupeFournisseurRepository.findAll().size();

        // Create the GroupeFournisseur with an existing ID
        groupeFournisseur.setId(1L);
        GroupeFournisseurDTO groupeFournisseurDTO = groupeFournisseurMapper.toDto(groupeFournisseur);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGroupeFournisseurMockMvc.perform(post("/api/groupe-fournisseurs").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(groupeFournisseurDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GroupeFournisseur in the database
        List<GroupeFournisseur> groupeFournisseurList = groupeFournisseurRepository.findAll();
        assertThat(groupeFournisseurList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLibelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = groupeFournisseurRepository.findAll().size();
        // set the field null
        groupeFournisseur.setLibelle(null);

        // Create the GroupeFournisseur, which fails.
        GroupeFournisseurDTO groupeFournisseurDTO = groupeFournisseurMapper.toDto(groupeFournisseur);


        restGroupeFournisseurMockMvc.perform(post("/api/groupe-fournisseurs").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(groupeFournisseurDTO)))
            .andExpect(status().isBadRequest());

        List<GroupeFournisseur> groupeFournisseurList = groupeFournisseurRepository.findAll();
        assertThat(groupeFournisseurList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = groupeFournisseurRepository.findAll().size();
        // set the field null
        groupeFournisseur.setStatus(null);

        // Create the GroupeFournisseur, which fails.
        GroupeFournisseurDTO groupeFournisseurDTO = groupeFournisseurMapper.toDto(groupeFournisseur);


        restGroupeFournisseurMockMvc.perform(post("/api/groupe-fournisseurs").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(groupeFournisseurDTO)))
            .andExpect(status().isBadRequest());

        List<GroupeFournisseur> groupeFournisseurList = groupeFournisseurRepository.findAll();
        assertThat(groupeFournisseurList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOdreIsRequired() throws Exception {
        int databaseSizeBeforeTest = groupeFournisseurRepository.findAll().size();
        // set the field null
        groupeFournisseur.setOdre(null);

        // Create the GroupeFournisseur, which fails.
        GroupeFournisseurDTO groupeFournisseurDTO = groupeFournisseurMapper.toDto(groupeFournisseur);


        restGroupeFournisseurMockMvc.perform(post("/api/groupe-fournisseurs").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(groupeFournisseurDTO)))
            .andExpect(status().isBadRequest());

        List<GroupeFournisseur> groupeFournisseurList = groupeFournisseurRepository.findAll();
        assertThat(groupeFournisseurList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllGroupeFournisseurs() throws Exception {
        // Initialize the database
        groupeFournisseurRepository.saveAndFlush(groupeFournisseur);

        // Get all the groupeFournisseurList
        restGroupeFournisseurMockMvc.perform(get("/api/groupe-fournisseurs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(groupeFournisseur.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)))
            .andExpect(jsonPath("$.[*].addresspostale").value(hasItem(DEFAULT_ADDRESSPOSTALE)))
            .andExpect(jsonPath("$.[*].numFaxe").value(hasItem(DEFAULT_NUM_FAXE)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].tel").value(hasItem(DEFAULT_TEL)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].odre").value(hasItem(DEFAULT_ODRE)));
    }
    
    @Test
    @Transactional
    public void getGroupeFournisseur() throws Exception {
        // Initialize the database
        groupeFournisseurRepository.saveAndFlush(groupeFournisseur);

        // Get the groupeFournisseur
        restGroupeFournisseurMockMvc.perform(get("/api/groupe-fournisseurs/{id}", groupeFournisseur.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(groupeFournisseur.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE))
            .andExpect(jsonPath("$.addresspostale").value(DEFAULT_ADDRESSPOSTALE))
            .andExpect(jsonPath("$.numFaxe").value(DEFAULT_NUM_FAXE))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.tel").value(DEFAULT_TEL))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.odre").value(DEFAULT_ODRE));
    }
    @Test
    @Transactional
    public void getNonExistingGroupeFournisseur() throws Exception {
        // Get the groupeFournisseur
        restGroupeFournisseurMockMvc.perform(get("/api/groupe-fournisseurs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGroupeFournisseur() throws Exception {
        // Initialize the database
        groupeFournisseurRepository.saveAndFlush(groupeFournisseur);

        int databaseSizeBeforeUpdate = groupeFournisseurRepository.findAll().size();

        // Update the groupeFournisseur
        GroupeFournisseur updatedGroupeFournisseur = groupeFournisseurRepository.findById(groupeFournisseur.getId()).get();
        // Disconnect from session so that the updates on updatedGroupeFournisseur are not directly saved in db
        em.detach(updatedGroupeFournisseur);
        updatedGroupeFournisseur
            .libelle(UPDATED_LIBELLE)
            .addresspostale(UPDATED_ADDRESSPOSTALE)
            .numFaxe(UPDATED_NUM_FAXE)
            .email(UPDATED_EMAIL)
            .tel(UPDATED_TEL)
            .status(UPDATED_STATUS)
            .odre(UPDATED_ODRE);
        GroupeFournisseurDTO groupeFournisseurDTO = groupeFournisseurMapper.toDto(updatedGroupeFournisseur);

        restGroupeFournisseurMockMvc.perform(put("/api/groupe-fournisseurs").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(groupeFournisseurDTO)))
            .andExpect(status().isOk());

        // Validate the GroupeFournisseur in the database
        List<GroupeFournisseur> groupeFournisseurList = groupeFournisseurRepository.findAll();
        assertThat(groupeFournisseurList).hasSize(databaseSizeBeforeUpdate);
        GroupeFournisseur testGroupeFournisseur = groupeFournisseurList.get(groupeFournisseurList.size() - 1);
        assertThat(testGroupeFournisseur.getLibelle()).isEqualTo(UPDATED_LIBELLE);
        assertThat(testGroupeFournisseur.getAddresspostale()).isEqualTo(UPDATED_ADDRESSPOSTALE);
        assertThat(testGroupeFournisseur.getNumFaxe()).isEqualTo(UPDATED_NUM_FAXE);
        assertThat(testGroupeFournisseur.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testGroupeFournisseur.getTel()).isEqualTo(UPDATED_TEL);
        assertThat(testGroupeFournisseur.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testGroupeFournisseur.getOdre()).isEqualTo(UPDATED_ODRE);
    }

    @Test
    @Transactional
    public void updateNonExistingGroupeFournisseur() throws Exception {
        int databaseSizeBeforeUpdate = groupeFournisseurRepository.findAll().size();

        // Create the GroupeFournisseur
        GroupeFournisseurDTO groupeFournisseurDTO = groupeFournisseurMapper.toDto(groupeFournisseur);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGroupeFournisseurMockMvc.perform(put("/api/groupe-fournisseurs").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(groupeFournisseurDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GroupeFournisseur in the database
        List<GroupeFournisseur> groupeFournisseurList = groupeFournisseurRepository.findAll();
        assertThat(groupeFournisseurList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGroupeFournisseur() throws Exception {
        // Initialize the database
        groupeFournisseurRepository.saveAndFlush(groupeFournisseur);

        int databaseSizeBeforeDelete = groupeFournisseurRepository.findAll().size();

        // Delete the groupeFournisseur
        restGroupeFournisseurMockMvc.perform(delete("/api/groupe-fournisseurs/{id}", groupeFournisseur.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<GroupeFournisseur> groupeFournisseurList = groupeFournisseurRepository.findAll();
        assertThat(groupeFournisseurList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
