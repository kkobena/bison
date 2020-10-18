package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.NucleusApp;
import com.kobe.nucleus.domain.GroupeTierspayant;
import com.kobe.nucleus.repository.GroupeTierspayantRepository;
import com.kobe.nucleus.service.GroupeTierspayantService;
import com.kobe.nucleus.service.dto.GroupeTierspayantDTO;
import com.kobe.nucleus.service.mapper.GroupeTierspayantMapper;

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

/**
 * Integration tests for the {@link GroupeTierspayantResource} REST controller.
 */
@SpringBootTest(classes = NucleusApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class GroupeTierspayantResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    @Autowired
    private GroupeTierspayantRepository groupeTierspayantRepository;

    @Autowired
    private GroupeTierspayantMapper groupeTierspayantMapper;

    @Autowired
    private GroupeTierspayantService groupeTierspayantService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restGroupeTierspayantMockMvc;

    private GroupeTierspayant groupeTierspayant;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GroupeTierspayant createEntity(EntityManager em) {
        GroupeTierspayant groupeTierspayant = new GroupeTierspayant()
            .code(DEFAULT_CODE)
            .libelle(DEFAULT_LIBELLE)
            .phone(DEFAULT_PHONE)
            .address(DEFAULT_ADDRESS);
        return groupeTierspayant;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GroupeTierspayant createUpdatedEntity(EntityManager em) {
        GroupeTierspayant groupeTierspayant = new GroupeTierspayant()
            .code(UPDATED_CODE)
            .libelle(UPDATED_LIBELLE)
            .phone(UPDATED_PHONE)
            .address(UPDATED_ADDRESS);
        return groupeTierspayant;
    }

    @BeforeEach
    public void initTest() {
        groupeTierspayant = createEntity(em);
    }

    @Test
    @Transactional
    public void createGroupeTierspayant() throws Exception {
        int databaseSizeBeforeCreate = groupeTierspayantRepository.findAll().size();
        // Create the GroupeTierspayant
        GroupeTierspayantDTO groupeTierspayantDTO = groupeTierspayantMapper.toDto(groupeTierspayant);
        restGroupeTierspayantMockMvc.perform(post("/api/groupe-tierspayants").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(groupeTierspayantDTO)))
            .andExpect(status().isCreated());

        // Validate the GroupeTierspayant in the database
        List<GroupeTierspayant> groupeTierspayantList = groupeTierspayantRepository.findAll();
        assertThat(groupeTierspayantList).hasSize(databaseSizeBeforeCreate + 1);
        GroupeTierspayant testGroupeTierspayant = groupeTierspayantList.get(groupeTierspayantList.size() - 1);
        assertThat(testGroupeTierspayant.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testGroupeTierspayant.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
        assertThat(testGroupeTierspayant.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testGroupeTierspayant.getAddress()).isEqualTo(DEFAULT_ADDRESS);
    }

    @Test
    @Transactional
    public void createGroupeTierspayantWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = groupeTierspayantRepository.findAll().size();

        // Create the GroupeTierspayant with an existing ID
        groupeTierspayant.setId(1L);
        GroupeTierspayantDTO groupeTierspayantDTO = groupeTierspayantMapper.toDto(groupeTierspayant);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGroupeTierspayantMockMvc.perform(post("/api/groupe-tierspayants").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(groupeTierspayantDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GroupeTierspayant in the database
        List<GroupeTierspayant> groupeTierspayantList = groupeTierspayantRepository.findAll();
        assertThat(groupeTierspayantList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLibelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = groupeTierspayantRepository.findAll().size();
        // set the field null
        groupeTierspayant.setLibelle(null);

        // Create the GroupeTierspayant, which fails.
        GroupeTierspayantDTO groupeTierspayantDTO = groupeTierspayantMapper.toDto(groupeTierspayant);


        restGroupeTierspayantMockMvc.perform(post("/api/groupe-tierspayants").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(groupeTierspayantDTO)))
            .andExpect(status().isBadRequest());

        List<GroupeTierspayant> groupeTierspayantList = groupeTierspayantRepository.findAll();
        assertThat(groupeTierspayantList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllGroupeTierspayants() throws Exception {
        // Initialize the database
        groupeTierspayantRepository.saveAndFlush(groupeTierspayant);

        // Get all the groupeTierspayantList
        restGroupeTierspayantMockMvc.perform(get("/api/groupe-tierspayants?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(groupeTierspayant.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE)))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)));
    }
    
    @Test
    @Transactional
    public void getGroupeTierspayant() throws Exception {
        // Initialize the database
        groupeTierspayantRepository.saveAndFlush(groupeTierspayant);

        // Get the groupeTierspayant
        restGroupeTierspayantMockMvc.perform(get("/api/groupe-tierspayants/{id}", groupeTierspayant.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(groupeTierspayant.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS));
    }
    @Test
    @Transactional
    public void getNonExistingGroupeTierspayant() throws Exception {
        // Get the groupeTierspayant
        restGroupeTierspayantMockMvc.perform(get("/api/groupe-tierspayants/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGroupeTierspayant() throws Exception {
        // Initialize the database
        groupeTierspayantRepository.saveAndFlush(groupeTierspayant);

        int databaseSizeBeforeUpdate = groupeTierspayantRepository.findAll().size();

        // Update the groupeTierspayant
        GroupeTierspayant updatedGroupeTierspayant = groupeTierspayantRepository.findById(groupeTierspayant.getId()).get();
        // Disconnect from session so that the updates on updatedGroupeTierspayant are not directly saved in db
        em.detach(updatedGroupeTierspayant);
        updatedGroupeTierspayant
            .code(UPDATED_CODE)
            .libelle(UPDATED_LIBELLE)
            .phone(UPDATED_PHONE)
            .address(UPDATED_ADDRESS);
        GroupeTierspayantDTO groupeTierspayantDTO = groupeTierspayantMapper.toDto(updatedGroupeTierspayant);

        restGroupeTierspayantMockMvc.perform(put("/api/groupe-tierspayants").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(groupeTierspayantDTO)))
            .andExpect(status().isOk());

        // Validate the GroupeTierspayant in the database
        List<GroupeTierspayant> groupeTierspayantList = groupeTierspayantRepository.findAll();
        assertThat(groupeTierspayantList).hasSize(databaseSizeBeforeUpdate);
        GroupeTierspayant testGroupeTierspayant = groupeTierspayantList.get(groupeTierspayantList.size() - 1);
        assertThat(testGroupeTierspayant.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testGroupeTierspayant.getLibelle()).isEqualTo(UPDATED_LIBELLE);
        assertThat(testGroupeTierspayant.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testGroupeTierspayant.getAddress()).isEqualTo(UPDATED_ADDRESS);
    }

    @Test
    @Transactional
    public void updateNonExistingGroupeTierspayant() throws Exception {
        int databaseSizeBeforeUpdate = groupeTierspayantRepository.findAll().size();

        // Create the GroupeTierspayant
        GroupeTierspayantDTO groupeTierspayantDTO = groupeTierspayantMapper.toDto(groupeTierspayant);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGroupeTierspayantMockMvc.perform(put("/api/groupe-tierspayants").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(groupeTierspayantDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GroupeTierspayant in the database
        List<GroupeTierspayant> groupeTierspayantList = groupeTierspayantRepository.findAll();
        assertThat(groupeTierspayantList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGroupeTierspayant() throws Exception {
        // Initialize the database
        groupeTierspayantRepository.saveAndFlush(groupeTierspayant);

        int databaseSizeBeforeDelete = groupeTierspayantRepository.findAll().size();

        // Delete the groupeTierspayant
        restGroupeTierspayantMockMvc.perform(delete("/api/groupe-tierspayants/{id}", groupeTierspayant.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<GroupeTierspayant> groupeTierspayantList = groupeTierspayantRepository.findAll();
        assertThat(groupeTierspayantList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
