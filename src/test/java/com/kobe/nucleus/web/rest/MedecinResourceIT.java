package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.NucleusApp;
import com.kobe.nucleus.domain.Medecin;
import com.kobe.nucleus.domain.Vente;
import com.kobe.nucleus.repository.MedecinRepository;
import com.kobe.nucleus.service.MedecinService;
import com.kobe.nucleus.service.dto.MedecinDTO;
import com.kobe.nucleus.service.mapper.MedecinMapper;

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
 * Integration tests for the {@link MedecinResource} REST controller.
 */
@SpringBootTest(classes = NucleusApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class MedecinResourceIT {

    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_MOBILE = "AAAAAAAAAA";
    private static final String UPDATED_MOBILE = "BBBBBBBBBB";

    private static final String DEFAULT_NUM = "AAAAAAAAAA";
    private static final String UPDATED_NUM = "BBBBBBBBBB";

    @Autowired
    private MedecinRepository medecinRepository;

    @Autowired
    private MedecinMapper medecinMapper;

    @Autowired
    private MedecinService medecinService;


    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMedecinMockMvc;

    private Medecin medecin;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Medecin createEntity(EntityManager em) {
        Medecin medecin = new Medecin()
            .firstName(DEFAULT_FIRST_NAME)
            .lastName(DEFAULT_LAST_NAME)
            .mobile(DEFAULT_MOBILE)
            .num(DEFAULT_NUM);
        return medecin;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Medecin createUpdatedEntity(EntityManager em) {
        Medecin medecin = new Medecin()
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .mobile(UPDATED_MOBILE)
            .num(UPDATED_NUM);
        return medecin;
    }

    @BeforeEach
    public void initTest() {
        medecin = createEntity(em);
    }

    @Test
    @Transactional
    public void createMedecin() throws Exception {
        int databaseSizeBeforeCreate = medecinRepository.findAll().size();
        // Create the Medecin
        MedecinDTO medecinDTO = medecinMapper.toDto(medecin);
        restMedecinMockMvc.perform(post("/api/medecins").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medecinDTO)))
            .andExpect(status().isCreated());

        // Validate the Medecin in the database
        List<Medecin> medecinList = medecinRepository.findAll();
        assertThat(medecinList).hasSize(databaseSizeBeforeCreate + 1);
        Medecin testMedecin = medecinList.get(medecinList.size() - 1);
        assertThat(testMedecin.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testMedecin.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testMedecin.getMobile()).isEqualTo(DEFAULT_MOBILE);
        assertThat(testMedecin.getNum()).isEqualTo(DEFAULT_NUM);
    }

    @Test
    @Transactional
    public void createMedecinWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = medecinRepository.findAll().size();

        // Create the Medecin with an existing ID
        medecin.setId(1L);
        MedecinDTO medecinDTO = medecinMapper.toDto(medecin);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMedecinMockMvc.perform(post("/api/medecins").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medecinDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Medecin in the database
        List<Medecin> medecinList = medecinRepository.findAll();
        assertThat(medecinList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNumIsRequired() throws Exception {
        int databaseSizeBeforeTest = medecinRepository.findAll().size();
        // set the field null
        medecin.setNum(null);

        // Create the Medecin, which fails.
        MedecinDTO medecinDTO = medecinMapper.toDto(medecin);


        restMedecinMockMvc.perform(post("/api/medecins").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medecinDTO)))
            .andExpect(status().isBadRequest());

        List<Medecin> medecinList = medecinRepository.findAll();
        assertThat(medecinList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMedecins() throws Exception {
        // Initialize the database
        medecinRepository.saveAndFlush(medecin);

        // Get all the medecinList
        restMedecinMockMvc.perform(get("/api/medecins?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(medecin.getId().intValue())))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].mobile").value(hasItem(DEFAULT_MOBILE)))
            .andExpect(jsonPath("$.[*].num").value(hasItem(DEFAULT_NUM)));
    }
    
    @Test
    @Transactional
    public void getMedecin() throws Exception {
        // Initialize the database
        medecinRepository.saveAndFlush(medecin);

        // Get the medecin
        restMedecinMockMvc.perform(get("/api/medecins/{id}", medecin.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(medecin.getId().intValue()))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME))
            .andExpect(jsonPath("$.mobile").value(DEFAULT_MOBILE))
            .andExpect(jsonPath("$.num").value(DEFAULT_NUM));
    }


    @Test
    @Transactional
    public void getMedecinsByIdFiltering() throws Exception {
        // Initialize the database
        medecinRepository.saveAndFlush(medecin);

        Long id = medecin.getId();

        defaultMedecinShouldBeFound("id.equals=" + id);
        defaultMedecinShouldNotBeFound("id.notEquals=" + id);

        defaultMedecinShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultMedecinShouldNotBeFound("id.greaterThan=" + id);

        defaultMedecinShouldBeFound("id.lessThanOrEqual=" + id);
        defaultMedecinShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllMedecinsByFirstNameIsEqualToSomething() throws Exception {
        // Initialize the database
        medecinRepository.saveAndFlush(medecin);

        // Get all the medecinList where firstName equals to DEFAULT_FIRST_NAME
        defaultMedecinShouldBeFound("firstName.equals=" + DEFAULT_FIRST_NAME);

        // Get all the medecinList where firstName equals to UPDATED_FIRST_NAME
        defaultMedecinShouldNotBeFound("firstName.equals=" + UPDATED_FIRST_NAME);
    }

    @Test
    @Transactional
    public void getAllMedecinsByFirstNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        medecinRepository.saveAndFlush(medecin);

        // Get all the medecinList where firstName not equals to DEFAULT_FIRST_NAME
        defaultMedecinShouldNotBeFound("firstName.notEquals=" + DEFAULT_FIRST_NAME);

        // Get all the medecinList where firstName not equals to UPDATED_FIRST_NAME
        defaultMedecinShouldBeFound("firstName.notEquals=" + UPDATED_FIRST_NAME);
    }

    @Test
    @Transactional
    public void getAllMedecinsByFirstNameIsInShouldWork() throws Exception {
        // Initialize the database
        medecinRepository.saveAndFlush(medecin);

        // Get all the medecinList where firstName in DEFAULT_FIRST_NAME or UPDATED_FIRST_NAME
        defaultMedecinShouldBeFound("firstName.in=" + DEFAULT_FIRST_NAME + "," + UPDATED_FIRST_NAME);

        // Get all the medecinList where firstName equals to UPDATED_FIRST_NAME
        defaultMedecinShouldNotBeFound("firstName.in=" + UPDATED_FIRST_NAME);
    }

    @Test
    @Transactional
    public void getAllMedecinsByFirstNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        medecinRepository.saveAndFlush(medecin);

        // Get all the medecinList where firstName is not null
        defaultMedecinShouldBeFound("firstName.specified=true");

        // Get all the medecinList where firstName is null
        defaultMedecinShouldNotBeFound("firstName.specified=false");
    }
                @Test
    @Transactional
    public void getAllMedecinsByFirstNameContainsSomething() throws Exception {
        // Initialize the database
        medecinRepository.saveAndFlush(medecin);

        // Get all the medecinList where firstName contains DEFAULT_FIRST_NAME
        defaultMedecinShouldBeFound("firstName.contains=" + DEFAULT_FIRST_NAME);

        // Get all the medecinList where firstName contains UPDATED_FIRST_NAME
        defaultMedecinShouldNotBeFound("firstName.contains=" + UPDATED_FIRST_NAME);
    }

    @Test
    @Transactional
    public void getAllMedecinsByFirstNameNotContainsSomething() throws Exception {
        // Initialize the database
        medecinRepository.saveAndFlush(medecin);

        // Get all the medecinList where firstName does not contain DEFAULT_FIRST_NAME
        defaultMedecinShouldNotBeFound("firstName.doesNotContain=" + DEFAULT_FIRST_NAME);

        // Get all the medecinList where firstName does not contain UPDATED_FIRST_NAME
        defaultMedecinShouldBeFound("firstName.doesNotContain=" + UPDATED_FIRST_NAME);
    }


    @Test
    @Transactional
    public void getAllMedecinsByLastNameIsEqualToSomething() throws Exception {
        // Initialize the database
        medecinRepository.saveAndFlush(medecin);

        // Get all the medecinList where lastName equals to DEFAULT_LAST_NAME
        defaultMedecinShouldBeFound("lastName.equals=" + DEFAULT_LAST_NAME);

        // Get all the medecinList where lastName equals to UPDATED_LAST_NAME
        defaultMedecinShouldNotBeFound("lastName.equals=" + UPDATED_LAST_NAME);
    }

    @Test
    @Transactional
    public void getAllMedecinsByLastNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        medecinRepository.saveAndFlush(medecin);

        // Get all the medecinList where lastName not equals to DEFAULT_LAST_NAME
        defaultMedecinShouldNotBeFound("lastName.notEquals=" + DEFAULT_LAST_NAME);

        // Get all the medecinList where lastName not equals to UPDATED_LAST_NAME
        defaultMedecinShouldBeFound("lastName.notEquals=" + UPDATED_LAST_NAME);
    }

    @Test
    @Transactional
    public void getAllMedecinsByLastNameIsInShouldWork() throws Exception {
        // Initialize the database
        medecinRepository.saveAndFlush(medecin);

        // Get all the medecinList where lastName in DEFAULT_LAST_NAME or UPDATED_LAST_NAME
        defaultMedecinShouldBeFound("lastName.in=" + DEFAULT_LAST_NAME + "," + UPDATED_LAST_NAME);

        // Get all the medecinList where lastName equals to UPDATED_LAST_NAME
        defaultMedecinShouldNotBeFound("lastName.in=" + UPDATED_LAST_NAME);
    }

    @Test
    @Transactional
    public void getAllMedecinsByLastNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        medecinRepository.saveAndFlush(medecin);

        // Get all the medecinList where lastName is not null
        defaultMedecinShouldBeFound("lastName.specified=true");

        // Get all the medecinList where lastName is null
        defaultMedecinShouldNotBeFound("lastName.specified=false");
    }
                @Test
    @Transactional
    public void getAllMedecinsByLastNameContainsSomething() throws Exception {
        // Initialize the database
        medecinRepository.saveAndFlush(medecin);

        // Get all the medecinList where lastName contains DEFAULT_LAST_NAME
        defaultMedecinShouldBeFound("lastName.contains=" + DEFAULT_LAST_NAME);

        // Get all the medecinList where lastName contains UPDATED_LAST_NAME
        defaultMedecinShouldNotBeFound("lastName.contains=" + UPDATED_LAST_NAME);
    }

    @Test
    @Transactional
    public void getAllMedecinsByLastNameNotContainsSomething() throws Exception {
        // Initialize the database
        medecinRepository.saveAndFlush(medecin);

        // Get all the medecinList where lastName does not contain DEFAULT_LAST_NAME
        defaultMedecinShouldNotBeFound("lastName.doesNotContain=" + DEFAULT_LAST_NAME);

        // Get all the medecinList where lastName does not contain UPDATED_LAST_NAME
        defaultMedecinShouldBeFound("lastName.doesNotContain=" + UPDATED_LAST_NAME);
    }


    @Test
    @Transactional
    public void getAllMedecinsByMobileIsEqualToSomething() throws Exception {
        // Initialize the database
        medecinRepository.saveAndFlush(medecin);

        // Get all the medecinList where mobile equals to DEFAULT_MOBILE
        defaultMedecinShouldBeFound("mobile.equals=" + DEFAULT_MOBILE);

        // Get all the medecinList where mobile equals to UPDATED_MOBILE
        defaultMedecinShouldNotBeFound("mobile.equals=" + UPDATED_MOBILE);
    }

    @Test
    @Transactional
    public void getAllMedecinsByMobileIsNotEqualToSomething() throws Exception {
        // Initialize the database
        medecinRepository.saveAndFlush(medecin);

        // Get all the medecinList where mobile not equals to DEFAULT_MOBILE
        defaultMedecinShouldNotBeFound("mobile.notEquals=" + DEFAULT_MOBILE);

        // Get all the medecinList where mobile not equals to UPDATED_MOBILE
        defaultMedecinShouldBeFound("mobile.notEquals=" + UPDATED_MOBILE);
    }

    @Test
    @Transactional
    public void getAllMedecinsByMobileIsInShouldWork() throws Exception {
        // Initialize the database
        medecinRepository.saveAndFlush(medecin);

        // Get all the medecinList where mobile in DEFAULT_MOBILE or UPDATED_MOBILE
        defaultMedecinShouldBeFound("mobile.in=" + DEFAULT_MOBILE + "," + UPDATED_MOBILE);

        // Get all the medecinList where mobile equals to UPDATED_MOBILE
        defaultMedecinShouldNotBeFound("mobile.in=" + UPDATED_MOBILE);
    }

    @Test
    @Transactional
    public void getAllMedecinsByMobileIsNullOrNotNull() throws Exception {
        // Initialize the database
        medecinRepository.saveAndFlush(medecin);

        // Get all the medecinList where mobile is not null
        defaultMedecinShouldBeFound("mobile.specified=true");

        // Get all the medecinList where mobile is null
        defaultMedecinShouldNotBeFound("mobile.specified=false");
    }
                @Test
    @Transactional
    public void getAllMedecinsByMobileContainsSomething() throws Exception {
        // Initialize the database
        medecinRepository.saveAndFlush(medecin);

        // Get all the medecinList where mobile contains DEFAULT_MOBILE
        defaultMedecinShouldBeFound("mobile.contains=" + DEFAULT_MOBILE);

        // Get all the medecinList where mobile contains UPDATED_MOBILE
        defaultMedecinShouldNotBeFound("mobile.contains=" + UPDATED_MOBILE);
    }

    @Test
    @Transactional
    public void getAllMedecinsByMobileNotContainsSomething() throws Exception {
        // Initialize the database
        medecinRepository.saveAndFlush(medecin);

        // Get all the medecinList where mobile does not contain DEFAULT_MOBILE
        defaultMedecinShouldNotBeFound("mobile.doesNotContain=" + DEFAULT_MOBILE);

        // Get all the medecinList where mobile does not contain UPDATED_MOBILE
        defaultMedecinShouldBeFound("mobile.doesNotContain=" + UPDATED_MOBILE);
    }


    @Test
    @Transactional
    public void getAllMedecinsByNumIsEqualToSomething() throws Exception {
        // Initialize the database
        medecinRepository.saveAndFlush(medecin);

        // Get all the medecinList where num equals to DEFAULT_NUM
        defaultMedecinShouldBeFound("num.equals=" + DEFAULT_NUM);

        // Get all the medecinList where num equals to UPDATED_NUM
        defaultMedecinShouldNotBeFound("num.equals=" + UPDATED_NUM);
    }

    @Test
    @Transactional
    public void getAllMedecinsByNumIsNotEqualToSomething() throws Exception {
        // Initialize the database
        medecinRepository.saveAndFlush(medecin);

        // Get all the medecinList where num not equals to DEFAULT_NUM
        defaultMedecinShouldNotBeFound("num.notEquals=" + DEFAULT_NUM);

        // Get all the medecinList where num not equals to UPDATED_NUM
        defaultMedecinShouldBeFound("num.notEquals=" + UPDATED_NUM);
    }

    @Test
    @Transactional
    public void getAllMedecinsByNumIsInShouldWork() throws Exception {
        // Initialize the database
        medecinRepository.saveAndFlush(medecin);

        // Get all the medecinList where num in DEFAULT_NUM or UPDATED_NUM
        defaultMedecinShouldBeFound("num.in=" + DEFAULT_NUM + "," + UPDATED_NUM);

        // Get all the medecinList where num equals to UPDATED_NUM
        defaultMedecinShouldNotBeFound("num.in=" + UPDATED_NUM);
    }

    @Test
    @Transactional
    public void getAllMedecinsByNumIsNullOrNotNull() throws Exception {
        // Initialize the database
        medecinRepository.saveAndFlush(medecin);

        // Get all the medecinList where num is not null
        defaultMedecinShouldBeFound("num.specified=true");

        // Get all the medecinList where num is null
        defaultMedecinShouldNotBeFound("num.specified=false");
    }
                @Test
    @Transactional
    public void getAllMedecinsByNumContainsSomething() throws Exception {
        // Initialize the database
        medecinRepository.saveAndFlush(medecin);

        // Get all the medecinList where num contains DEFAULT_NUM
        defaultMedecinShouldBeFound("num.contains=" + DEFAULT_NUM);

        // Get all the medecinList where num contains UPDATED_NUM
        defaultMedecinShouldNotBeFound("num.contains=" + UPDATED_NUM);
    }

    @Test
    @Transactional
    public void getAllMedecinsByNumNotContainsSomething() throws Exception {
        // Initialize the database
        medecinRepository.saveAndFlush(medecin);

        // Get all the medecinList where num does not contain DEFAULT_NUM
        defaultMedecinShouldNotBeFound("num.doesNotContain=" + DEFAULT_NUM);

        // Get all the medecinList where num does not contain UPDATED_NUM
        defaultMedecinShouldBeFound("num.doesNotContain=" + UPDATED_NUM);
    }


    @Test
    @Transactional
    public void getAllMedecinsByVentesIsEqualToSomething() throws Exception {
        // Initialize the database
        medecinRepository.saveAndFlush(medecin);
        Vente ventes = VenteResourceIT.createEntity(em);
        em.persist(ventes);
        em.flush();
      
        medecinRepository.saveAndFlush(medecin);
        Long ventesId = ventes.getId();

        // Get all the medecinList where ventes equals to ventesId
        defaultMedecinShouldBeFound("ventesId.equals=" + ventesId);

        // Get all the medecinList where ventes equals to ventesId + 1
        defaultMedecinShouldNotBeFound("ventesId.equals=" + (ventesId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMedecinShouldBeFound(String filter) throws Exception {
        restMedecinMockMvc.perform(get("/api/medecins?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(medecin.getId().intValue())))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].mobile").value(hasItem(DEFAULT_MOBILE)))
            .andExpect(jsonPath("$.[*].num").value(hasItem(DEFAULT_NUM)));

        // Check, that the count call also returns 1
        restMedecinMockMvc.perform(get("/api/medecins/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMedecinShouldNotBeFound(String filter) throws Exception {
        restMedecinMockMvc.perform(get("/api/medecins?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMedecinMockMvc.perform(get("/api/medecins/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingMedecin() throws Exception {
        // Get the medecin
        restMedecinMockMvc.perform(get("/api/medecins/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMedecin() throws Exception {
        // Initialize the database
        medecinRepository.saveAndFlush(medecin);

        int databaseSizeBeforeUpdate = medecinRepository.findAll().size();

        // Update the medecin
        Medecin updatedMedecin = medecinRepository.findById(medecin.getId()).get();
        // Disconnect from session so that the updates on updatedMedecin are not directly saved in db
        em.detach(updatedMedecin);
        updatedMedecin
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .mobile(UPDATED_MOBILE)
            .num(UPDATED_NUM);
        MedecinDTO medecinDTO = medecinMapper.toDto(updatedMedecin);

        restMedecinMockMvc.perform(put("/api/medecins").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medecinDTO)))
            .andExpect(status().isOk());

        // Validate the Medecin in the database
        List<Medecin> medecinList = medecinRepository.findAll();
        assertThat(medecinList).hasSize(databaseSizeBeforeUpdate);
        Medecin testMedecin = medecinList.get(medecinList.size() - 1);
        assertThat(testMedecin.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testMedecin.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testMedecin.getMobile()).isEqualTo(UPDATED_MOBILE);
        assertThat(testMedecin.getNum()).isEqualTo(UPDATED_NUM);
    }

    @Test
    @Transactional
    public void updateNonExistingMedecin() throws Exception {
        int databaseSizeBeforeUpdate = medecinRepository.findAll().size();

        // Create the Medecin
        MedecinDTO medecinDTO = medecinMapper.toDto(medecin);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMedecinMockMvc.perform(put("/api/medecins").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medecinDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Medecin in the database
        List<Medecin> medecinList = medecinRepository.findAll();
        assertThat(medecinList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMedecin() throws Exception {
        // Initialize the database
        medecinRepository.saveAndFlush(medecin);

        int databaseSizeBeforeDelete = medecinRepository.findAll().size();

        // Delete the medecin
        restMedecinMockMvc.perform(delete("/api/medecins/{id}", medecin.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Medecin> medecinList = medecinRepository.findAll();
        assertThat(medecinList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
