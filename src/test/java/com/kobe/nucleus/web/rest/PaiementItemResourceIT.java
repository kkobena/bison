package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.NucleusApp;
import com.kobe.nucleus.domain.PaiementItem;
import com.kobe.nucleus.repository.PaiementItemRepository;
import com.kobe.nucleus.service.PaiementItemService;
import com.kobe.nucleus.service.dto.PaiementItemDTO;
import com.kobe.nucleus.service.mapper.PaiementItemMapper;

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
 * Integration tests for the {@link PaiementItemResource} REST controller.
 */
@SpringBootTest(classes = NucleusApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PaiementItemResourceIT {

    private static final String DEFAULT_CODE_REF = "AAAAAAAAAA";
    private static final String UPDATED_CODE_REF = "BBBBBBBBBB";

    private static final String DEFAULT_PKEY = "AAAAAAAAAA";
    private static final String UPDATED_PKEY = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_UPDATED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_MONTANT_ATTENDU = 1;
    private static final Integer UPDATED_MONTANT_ATTENDU = 2;

    private static final Integer DEFAULT_MONTANT_REGL = 1;
    private static final Integer UPDATED_MONTANT_REGL = 2;

    private static final Integer DEFAULT_MONTANT_RESTANT = 1;
    private static final Integer UPDATED_MONTANT_RESTANT = 2;

    @Autowired
    private PaiementItemRepository paiementItemRepository;

    @Autowired
    private PaiementItemMapper paiementItemMapper;

    @Autowired
    private PaiementItemService paiementItemService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPaiementItemMockMvc;

    private PaiementItem paiementItem;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PaiementItem createEntity(EntityManager em) {
        PaiementItem paiementItem = new PaiementItem()
            .codeRef(DEFAULT_CODE_REF)
            .pkey(DEFAULT_PKEY)
            .createdAt(DEFAULT_CREATED_AT)
            .updatedAt(DEFAULT_UPDATED_AT)
            .montantAttendu(DEFAULT_MONTANT_ATTENDU)
            .montantRegl(DEFAULT_MONTANT_REGL)
            .montantRestant(DEFAULT_MONTANT_RESTANT);
        return paiementItem;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PaiementItem createUpdatedEntity(EntityManager em) {
        PaiementItem paiementItem = new PaiementItem()
            .codeRef(UPDATED_CODE_REF)
            .pkey(UPDATED_PKEY)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .montantAttendu(UPDATED_MONTANT_ATTENDU)
            .montantRegl(UPDATED_MONTANT_REGL)
            .montantRestant(UPDATED_MONTANT_RESTANT);
        return paiementItem;
    }

    @BeforeEach
    public void initTest() {
        paiementItem = createEntity(em);
    }

    @Test
    @Transactional
    public void createPaiementItem() throws Exception {
        int databaseSizeBeforeCreate = paiementItemRepository.findAll().size();
        // Create the PaiementItem
        PaiementItemDTO paiementItemDTO = paiementItemMapper.toDto(paiementItem);
        restPaiementItemMockMvc.perform(post("/api/paiement-items").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paiementItemDTO)))
            .andExpect(status().isCreated());

        // Validate the PaiementItem in the database
        List<PaiementItem> paiementItemList = paiementItemRepository.findAll();
        assertThat(paiementItemList).hasSize(databaseSizeBeforeCreate + 1);
        PaiementItem testPaiementItem = paiementItemList.get(paiementItemList.size() - 1);
        assertThat(testPaiementItem.getCodeRef()).isEqualTo(DEFAULT_CODE_REF);
        assertThat(testPaiementItem.getPkey()).isEqualTo(DEFAULT_PKEY);
        assertThat(testPaiementItem.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testPaiementItem.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testPaiementItem.getMontantAttendu()).isEqualTo(DEFAULT_MONTANT_ATTENDU);
        assertThat(testPaiementItem.getMontantRegl()).isEqualTo(DEFAULT_MONTANT_REGL);
        assertThat(testPaiementItem.getMontantRestant()).isEqualTo(DEFAULT_MONTANT_RESTANT);
    }

    @Test
    @Transactional
    public void createPaiementItemWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = paiementItemRepository.findAll().size();

        // Create the PaiementItem with an existing ID
        paiementItem.setId(1L);
        PaiementItemDTO paiementItemDTO = paiementItemMapper.toDto(paiementItem);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPaiementItemMockMvc.perform(post("/api/paiement-items").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paiementItemDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PaiementItem in the database
        List<PaiementItem> paiementItemList = paiementItemRepository.findAll();
        assertThat(paiementItemList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCreatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = paiementItemRepository.findAll().size();
        // set the field null
        paiementItem.setCreatedAt(null);

        // Create the PaiementItem, which fails.
        PaiementItemDTO paiementItemDTO = paiementItemMapper.toDto(paiementItem);


        restPaiementItemMockMvc.perform(post("/api/paiement-items").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paiementItemDTO)))
            .andExpect(status().isBadRequest());

        List<PaiementItem> paiementItemList = paiementItemRepository.findAll();
        assertThat(paiementItemList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUpdatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = paiementItemRepository.findAll().size();
        // set the field null
        paiementItem.setUpdatedAt(null);

        // Create the PaiementItem, which fails.
        PaiementItemDTO paiementItemDTO = paiementItemMapper.toDto(paiementItem);


        restPaiementItemMockMvc.perform(post("/api/paiement-items").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paiementItemDTO)))
            .andExpect(status().isBadRequest());

        List<PaiementItem> paiementItemList = paiementItemRepository.findAll();
        assertThat(paiementItemList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPaiementItems() throws Exception {
        // Initialize the database
        paiementItemRepository.saveAndFlush(paiementItem);

        // Get all the paiementItemList
        restPaiementItemMockMvc.perform(get("/api/paiement-items?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(paiementItem.getId().intValue())))
            .andExpect(jsonPath("$.[*].codeRef").value(hasItem(DEFAULT_CODE_REF)))
            .andExpect(jsonPath("$.[*].pkey").value(hasItem(DEFAULT_PKEY)))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(DEFAULT_UPDATED_AT.toString())))
            .andExpect(jsonPath("$.[*].montantAttendu").value(hasItem(DEFAULT_MONTANT_ATTENDU)))
            .andExpect(jsonPath("$.[*].montantRegl").value(hasItem(DEFAULT_MONTANT_REGL)))
            .andExpect(jsonPath("$.[*].montantRestant").value(hasItem(DEFAULT_MONTANT_RESTANT)));
    }
    
    @Test
    @Transactional
    public void getPaiementItem() throws Exception {
        // Initialize the database
        paiementItemRepository.saveAndFlush(paiementItem);

        // Get the paiementItem
        restPaiementItemMockMvc.perform(get("/api/paiement-items/{id}", paiementItem.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(paiementItem.getId().intValue()))
            .andExpect(jsonPath("$.codeRef").value(DEFAULT_CODE_REF))
            .andExpect(jsonPath("$.pkey").value(DEFAULT_PKEY))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.updatedAt").value(DEFAULT_UPDATED_AT.toString()))
            .andExpect(jsonPath("$.montantAttendu").value(DEFAULT_MONTANT_ATTENDU))
            .andExpect(jsonPath("$.montantRegl").value(DEFAULT_MONTANT_REGL))
            .andExpect(jsonPath("$.montantRestant").value(DEFAULT_MONTANT_RESTANT));
    }
    @Test
    @Transactional
    public void getNonExistingPaiementItem() throws Exception {
        // Get the paiementItem
        restPaiementItemMockMvc.perform(get("/api/paiement-items/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePaiementItem() throws Exception {
        // Initialize the database
        paiementItemRepository.saveAndFlush(paiementItem);

        int databaseSizeBeforeUpdate = paiementItemRepository.findAll().size();

        // Update the paiementItem
        PaiementItem updatedPaiementItem = paiementItemRepository.findById(paiementItem.getId()).get();
        // Disconnect from session so that the updates on updatedPaiementItem are not directly saved in db
        em.detach(updatedPaiementItem);
        updatedPaiementItem
            .codeRef(UPDATED_CODE_REF)
            .pkey(UPDATED_PKEY)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .montantAttendu(UPDATED_MONTANT_ATTENDU)
            .montantRegl(UPDATED_MONTANT_REGL)
            .montantRestant(UPDATED_MONTANT_RESTANT);
        PaiementItemDTO paiementItemDTO = paiementItemMapper.toDto(updatedPaiementItem);

        restPaiementItemMockMvc.perform(put("/api/paiement-items").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paiementItemDTO)))
            .andExpect(status().isOk());

        // Validate the PaiementItem in the database
        List<PaiementItem> paiementItemList = paiementItemRepository.findAll();
        assertThat(paiementItemList).hasSize(databaseSizeBeforeUpdate);
        PaiementItem testPaiementItem = paiementItemList.get(paiementItemList.size() - 1);
        assertThat(testPaiementItem.getCodeRef()).isEqualTo(UPDATED_CODE_REF);
        assertThat(testPaiementItem.getPkey()).isEqualTo(UPDATED_PKEY);
        assertThat(testPaiementItem.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testPaiementItem.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testPaiementItem.getMontantAttendu()).isEqualTo(UPDATED_MONTANT_ATTENDU);
        assertThat(testPaiementItem.getMontantRegl()).isEqualTo(UPDATED_MONTANT_REGL);
        assertThat(testPaiementItem.getMontantRestant()).isEqualTo(UPDATED_MONTANT_RESTANT);
    }

    @Test
    @Transactional
    public void updateNonExistingPaiementItem() throws Exception {
        int databaseSizeBeforeUpdate = paiementItemRepository.findAll().size();

        // Create the PaiementItem
        PaiementItemDTO paiementItemDTO = paiementItemMapper.toDto(paiementItem);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPaiementItemMockMvc.perform(put("/api/paiement-items").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paiementItemDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PaiementItem in the database
        List<PaiementItem> paiementItemList = paiementItemRepository.findAll();
        assertThat(paiementItemList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePaiementItem() throws Exception {
        // Initialize the database
        paiementItemRepository.saveAndFlush(paiementItem);

        int databaseSizeBeforeDelete = paiementItemRepository.findAll().size();

        // Delete the paiementItem
        restPaiementItemMockMvc.perform(delete("/api/paiement-items/{id}", paiementItem.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PaiementItem> paiementItemList = paiementItemRepository.findAll();
        assertThat(paiementItemList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
