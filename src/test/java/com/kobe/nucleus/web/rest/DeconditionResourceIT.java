package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.NucleusApp;
import com.kobe.nucleus.domain.Decondition;
import com.kobe.nucleus.repository.DeconditionRepository;
import com.kobe.nucleus.service.DeconditionService;
import com.kobe.nucleus.service.dto.DeconditionDTO;
import com.kobe.nucleus.service.mapper.DeconditionMapper;

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
 * Integration tests for the {@link DeconditionResource} REST controller.
 */
@SpringBootTest(classes = NucleusApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class DeconditionResourceIT {

    private static final Integer DEFAULT_QTY_STOCK = 1;
    private static final Integer UPDATED_QTY_STOCK = 2;

    private static final Integer DEFAULT_QTY_INIT = 1;
    private static final Integer UPDATED_QTY_INIT = 2;

    @Autowired
    private DeconditionRepository deconditionRepository;

    @Autowired
    private DeconditionMapper deconditionMapper;

    @Autowired
    private DeconditionService deconditionService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDeconditionMockMvc;

    private Decondition decondition;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Decondition createEntity(EntityManager em) {
        Decondition decondition = new Decondition()
            .qtyStock(DEFAULT_QTY_STOCK)
            .qtyInit(DEFAULT_QTY_INIT);
        return decondition;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Decondition createUpdatedEntity(EntityManager em) {
        Decondition decondition = new Decondition()
            .qtyStock(UPDATED_QTY_STOCK)
            .qtyInit(UPDATED_QTY_INIT);
        return decondition;
    }

    @BeforeEach
    public void initTest() {
        decondition = createEntity(em);
    }

    @Test
    @Transactional
    public void createDecondition() throws Exception {
        int databaseSizeBeforeCreate = deconditionRepository.findAll().size();
        // Create the Decondition
        DeconditionDTO deconditionDTO = deconditionMapper.toDto(decondition);
        restDeconditionMockMvc.perform(post("/api/deconditions").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(deconditionDTO)))
            .andExpect(status().isCreated());

        // Validate the Decondition in the database
        List<Decondition> deconditionList = deconditionRepository.findAll();
        assertThat(deconditionList).hasSize(databaseSizeBeforeCreate + 1);
        Decondition testDecondition = deconditionList.get(deconditionList.size() - 1);
        assertThat(testDecondition.getQtyStock()).isEqualTo(DEFAULT_QTY_STOCK);
        assertThat(testDecondition.getQtyInit()).isEqualTo(DEFAULT_QTY_INIT);
    }

    @Test
    @Transactional
    public void createDeconditionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = deconditionRepository.findAll().size();

        // Create the Decondition with an existing ID
        decondition.setId(1L);
        DeconditionDTO deconditionDTO = deconditionMapper.toDto(decondition);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDeconditionMockMvc.perform(post("/api/deconditions").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(deconditionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Decondition in the database
        List<Decondition> deconditionList = deconditionRepository.findAll();
        assertThat(deconditionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkQtyStockIsRequired() throws Exception {
        int databaseSizeBeforeTest = deconditionRepository.findAll().size();
        // set the field null
        decondition.setQtyStock(null);

        // Create the Decondition, which fails.
        DeconditionDTO deconditionDTO = deconditionMapper.toDto(decondition);


        restDeconditionMockMvc.perform(post("/api/deconditions").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(deconditionDTO)))
            .andExpect(status().isBadRequest());

        List<Decondition> deconditionList = deconditionRepository.findAll();
        assertThat(deconditionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQtyInitIsRequired() throws Exception {
        int databaseSizeBeforeTest = deconditionRepository.findAll().size();
        // set the field null
        decondition.setQtyInit(null);

        // Create the Decondition, which fails.
        DeconditionDTO deconditionDTO = deconditionMapper.toDto(decondition);


        restDeconditionMockMvc.perform(post("/api/deconditions").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(deconditionDTO)))
            .andExpect(status().isBadRequest());

        List<Decondition> deconditionList = deconditionRepository.findAll();
        assertThat(deconditionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDeconditions() throws Exception {
        // Initialize the database
        deconditionRepository.saveAndFlush(decondition);

        // Get all the deconditionList
        restDeconditionMockMvc.perform(get("/api/deconditions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(decondition.getId().intValue())))
            .andExpect(jsonPath("$.[*].qtyStock").value(hasItem(DEFAULT_QTY_STOCK)))
            .andExpect(jsonPath("$.[*].qtyInit").value(hasItem(DEFAULT_QTY_INIT)));
    }
    
    @Test
    @Transactional
    public void getDecondition() throws Exception {
        // Initialize the database
        deconditionRepository.saveAndFlush(decondition);

        // Get the decondition
        restDeconditionMockMvc.perform(get("/api/deconditions/{id}", decondition.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(decondition.getId().intValue()))
            .andExpect(jsonPath("$.qtyStock").value(DEFAULT_QTY_STOCK))
            .andExpect(jsonPath("$.qtyInit").value(DEFAULT_QTY_INIT));
    }
    @Test
    @Transactional
    public void getNonExistingDecondition() throws Exception {
        // Get the decondition
        restDeconditionMockMvc.perform(get("/api/deconditions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDecondition() throws Exception {
        // Initialize the database
        deconditionRepository.saveAndFlush(decondition);

        int databaseSizeBeforeUpdate = deconditionRepository.findAll().size();

        // Update the decondition
        Decondition updatedDecondition = deconditionRepository.findById(decondition.getId()).get();
        // Disconnect from session so that the updates on updatedDecondition are not directly saved in db
        em.detach(updatedDecondition);
        updatedDecondition
            .qtyStock(UPDATED_QTY_STOCK)
            .qtyInit(UPDATED_QTY_INIT);
        DeconditionDTO deconditionDTO = deconditionMapper.toDto(updatedDecondition);

        restDeconditionMockMvc.perform(put("/api/deconditions").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(deconditionDTO)))
            .andExpect(status().isOk());

        // Validate the Decondition in the database
        List<Decondition> deconditionList = deconditionRepository.findAll();
        assertThat(deconditionList).hasSize(databaseSizeBeforeUpdate);
        Decondition testDecondition = deconditionList.get(deconditionList.size() - 1);
        assertThat(testDecondition.getQtyStock()).isEqualTo(UPDATED_QTY_STOCK);
        assertThat(testDecondition.getQtyInit()).isEqualTo(UPDATED_QTY_INIT);
    }

    @Test
    @Transactional
    public void updateNonExistingDecondition() throws Exception {
        int databaseSizeBeforeUpdate = deconditionRepository.findAll().size();

        // Create the Decondition
        DeconditionDTO deconditionDTO = deconditionMapper.toDto(decondition);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDeconditionMockMvc.perform(put("/api/deconditions").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(deconditionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Decondition in the database
        List<Decondition> deconditionList = deconditionRepository.findAll();
        assertThat(deconditionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDecondition() throws Exception {
        // Initialize the database
        deconditionRepository.saveAndFlush(decondition);

        int databaseSizeBeforeDelete = deconditionRepository.findAll().size();

        // Delete the decondition
        restDeconditionMockMvc.perform(delete("/api/deconditions/{id}", decondition.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Decondition> deconditionList = deconditionRepository.findAll();
        assertThat(deconditionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
