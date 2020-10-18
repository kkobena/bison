package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.NucleusApp;
import com.kobe.nucleus.domain.RetourItem;
import com.kobe.nucleus.repository.RetourItemRepository;
import com.kobe.nucleus.service.RetourItemService;
import com.kobe.nucleus.service.dto.RetourItemDTO;
import com.kobe.nucleus.service.mapper.RetourItemMapper;

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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link RetourItemResource} REST controller.
 */
@SpringBootTest(classes = NucleusApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class RetourItemResourceIT {

    private static final Integer DEFAULT_QTE_CONFIRME = 1;
    private static final Integer UPDATED_QTE_CONFIRME = 2;

    private static final Instant DEFAULT_UPDATED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_CREATED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_QTE_RETOURNE = 1;
    private static final Integer UPDATED_QTE_RETOURNE = 2;

    @Autowired
    private RetourItemRepository retourItemRepository;

    @Autowired
    private RetourItemMapper retourItemMapper;

    @Autowired
    private RetourItemService retourItemService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRetourItemMockMvc;

    private RetourItem retourItem;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RetourItem createEntity(EntityManager em) {
        RetourItem retourItem = new RetourItem()
            .qteConfirme(DEFAULT_QTE_CONFIRME)
            .updatedAt(DEFAULT_UPDATED_AT)
            .createdAt(DEFAULT_CREATED_AT)
            .qteRetourne(DEFAULT_QTE_RETOURNE);
        return retourItem;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RetourItem createUpdatedEntity(EntityManager em) {
        RetourItem retourItem = new RetourItem()
            .qteConfirme(UPDATED_QTE_CONFIRME)
            .updatedAt(UPDATED_UPDATED_AT)
            .createdAt(UPDATED_CREATED_AT)
            .qteRetourne(UPDATED_QTE_RETOURNE);
        return retourItem;
    }

    @BeforeEach
    public void initTest() {
        retourItem = createEntity(em);
    }

    @Test
    @Transactional
    public void createRetourItem() throws Exception {
        int databaseSizeBeforeCreate = retourItemRepository.findAll().size();
        // Create the RetourItem
        RetourItemDTO retourItemDTO = retourItemMapper.toDto(retourItem);
        restRetourItemMockMvc.perform(post("/api/retour-items").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(retourItemDTO)))
            .andExpect(status().isCreated());

        // Validate the RetourItem in the database
        List<RetourItem> retourItemList = retourItemRepository.findAll();
        assertThat(retourItemList).hasSize(databaseSizeBeforeCreate + 1);
        RetourItem testRetourItem = retourItemList.get(retourItemList.size() - 1);
        assertThat(testRetourItem.getQteConfirme()).isEqualTo(DEFAULT_QTE_CONFIRME);
        assertThat(testRetourItem.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testRetourItem.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testRetourItem.getQteRetourne()).isEqualTo(DEFAULT_QTE_RETOURNE);
    }

    @Test
    @Transactional
    public void createRetourItemWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = retourItemRepository.findAll().size();

        // Create the RetourItem with an existing ID
        retourItem.setId(1L);
        RetourItemDTO retourItemDTO = retourItemMapper.toDto(retourItem);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRetourItemMockMvc.perform(post("/api/retour-items").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(retourItemDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RetourItem in the database
        List<RetourItem> retourItemList = retourItemRepository.findAll();
        assertThat(retourItemList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCreatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = retourItemRepository.findAll().size();
        // set the field null
        retourItem.setCreatedAt(null);

        // Create the RetourItem, which fails.
        RetourItemDTO retourItemDTO = retourItemMapper.toDto(retourItem);


        restRetourItemMockMvc.perform(post("/api/retour-items").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(retourItemDTO)))
            .andExpect(status().isBadRequest());

        List<RetourItem> retourItemList = retourItemRepository.findAll();
        assertThat(retourItemList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRetourItems() throws Exception {
        // Initialize the database
        retourItemRepository.saveAndFlush(retourItem);

        // Get all the retourItemList
        restRetourItemMockMvc.perform(get("/api/retour-items?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(retourItem.getId().intValue())))
            .andExpect(jsonPath("$.[*].qteConfirme").value(hasItem(DEFAULT_QTE_CONFIRME)))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(DEFAULT_UPDATED_AT.toString())))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].qteRetourne").value(hasItem(DEFAULT_QTE_RETOURNE)));
    }
    
    @Test
    @Transactional
    public void getRetourItem() throws Exception {
        // Initialize the database
        retourItemRepository.saveAndFlush(retourItem);

        // Get the retourItem
        restRetourItemMockMvc.perform(get("/api/retour-items/{id}", retourItem.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(retourItem.getId().intValue()))
            .andExpect(jsonPath("$.qteConfirme").value(DEFAULT_QTE_CONFIRME))
            .andExpect(jsonPath("$.updatedAt").value(DEFAULT_UPDATED_AT.toString()))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.qteRetourne").value(DEFAULT_QTE_RETOURNE));
    }
    @Test
    @Transactional
    public void getNonExistingRetourItem() throws Exception {
        // Get the retourItem
        restRetourItemMockMvc.perform(get("/api/retour-items/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRetourItem() throws Exception {
        // Initialize the database
        retourItemRepository.saveAndFlush(retourItem);

        int databaseSizeBeforeUpdate = retourItemRepository.findAll().size();

        // Update the retourItem
        RetourItem updatedRetourItem = retourItemRepository.findById(retourItem.getId()).get();
        // Disconnect from session so that the updates on updatedRetourItem are not directly saved in db
        em.detach(updatedRetourItem);
        updatedRetourItem
            .qteConfirme(UPDATED_QTE_CONFIRME)
            .updatedAt(UPDATED_UPDATED_AT)
            .createdAt(UPDATED_CREATED_AT)
            .qteRetourne(UPDATED_QTE_RETOURNE);
        RetourItemDTO retourItemDTO = retourItemMapper.toDto(updatedRetourItem);

        restRetourItemMockMvc.perform(put("/api/retour-items").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(retourItemDTO)))
            .andExpect(status().isOk());

        // Validate the RetourItem in the database
        List<RetourItem> retourItemList = retourItemRepository.findAll();
        assertThat(retourItemList).hasSize(databaseSizeBeforeUpdate);
        RetourItem testRetourItem = retourItemList.get(retourItemList.size() - 1);
        assertThat(testRetourItem.getQteConfirme()).isEqualTo(UPDATED_QTE_CONFIRME);
        assertThat(testRetourItem.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testRetourItem.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testRetourItem.getQteRetourne()).isEqualTo(UPDATED_QTE_RETOURNE);
    }

    @Test
    @Transactional
    public void updateNonExistingRetourItem() throws Exception {
        int databaseSizeBeforeUpdate = retourItemRepository.findAll().size();

        // Create the RetourItem
        RetourItemDTO retourItemDTO = retourItemMapper.toDto(retourItem);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRetourItemMockMvc.perform(put("/api/retour-items").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(retourItemDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RetourItem in the database
        List<RetourItem> retourItemList = retourItemRepository.findAll();
        assertThat(retourItemList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRetourItem() throws Exception {
        // Initialize the database
        retourItemRepository.saveAndFlush(retourItem);

        int databaseSizeBeforeDelete = retourItemRepository.findAll().size();

        // Delete the retourItem
        restRetourItemMockMvc.perform(delete("/api/retour-items/{id}", retourItem.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RetourItem> retourItemList = retourItemRepository.findAll();
        assertThat(retourItemList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
