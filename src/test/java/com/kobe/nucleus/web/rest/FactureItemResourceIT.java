package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.NucleusApp;
import com.kobe.nucleus.domain.FactureItem;
import com.kobe.nucleus.repository.FactureItemRepository;
import com.kobe.nucleus.service.FactureItemService;
import com.kobe.nucleus.service.dto.FactureItemDTO;
import com.kobe.nucleus.service.mapper.FactureItemMapper;

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

import com.kobe.nucleus.domain.enumeration.StatutFacture;
/**
 * Integration tests for the {@link FactureItemResource} REST controller.
 */
@SpringBootTest(classes = NucleusApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class FactureItemResourceIT {

    private static final Instant DEFAULT_CREATED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_UPDATED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_MONTANT_REMISE = 1;
    private static final Integer UPDATED_MONTANT_REMISE = 2;

    private static final Integer DEFAULT_MONTANT_PAYE = 1;
    private static final Integer UPDATED_MONTANT_PAYE = 2;

    private static final StatutFacture DEFAULT_STATUT_FACTURE = StatutFacture.PAID;
    private static final StatutFacture UPDATED_STATUT_FACTURE = StatutFacture.UNPAID;

    private static final Integer DEFAULT_MONTANT_RESTANT = 1;
    private static final Integer UPDATED_MONTANT_RESTANT = 2;

    private static final Integer DEFAULT_MONTANT_NET = 1;
    private static final Integer UPDATED_MONTANT_NET = 2;

    private static final Integer DEFAULT_MONTANT_BRUT = 1;
    private static final Integer UPDATED_MONTANT_BRUT = 2;

    @Autowired
    private FactureItemRepository factureItemRepository;

    @Autowired
    private FactureItemMapper factureItemMapper;

    @Autowired
    private FactureItemService factureItemService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFactureItemMockMvc;

    private FactureItem factureItem;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FactureItem createEntity(EntityManager em) {
        FactureItem factureItem = new FactureItem()
            .createdAt(DEFAULT_CREATED_AT)
            .updatedAt(DEFAULT_UPDATED_AT)
            .montantRemise(DEFAULT_MONTANT_REMISE)
            .montantPaye(DEFAULT_MONTANT_PAYE)
            .statutFacture(DEFAULT_STATUT_FACTURE)
            .montantRestant(DEFAULT_MONTANT_RESTANT)
            .montantNet(DEFAULT_MONTANT_NET)
            .montantBrut(DEFAULT_MONTANT_BRUT);
        return factureItem;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FactureItem createUpdatedEntity(EntityManager em) {
        FactureItem factureItem = new FactureItem()
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .montantRemise(UPDATED_MONTANT_REMISE)
            .montantPaye(UPDATED_MONTANT_PAYE)
            .statutFacture(UPDATED_STATUT_FACTURE)
            .montantRestant(UPDATED_MONTANT_RESTANT)
            .montantNet(UPDATED_MONTANT_NET)
            .montantBrut(UPDATED_MONTANT_BRUT);
        return factureItem;
    }

    @BeforeEach
    public void initTest() {
        factureItem = createEntity(em);
    }

    @Test
    @Transactional
    public void createFactureItem() throws Exception {
        int databaseSizeBeforeCreate = factureItemRepository.findAll().size();
        // Create the FactureItem
        FactureItemDTO factureItemDTO = factureItemMapper.toDto(factureItem);
        restFactureItemMockMvc.perform(post("/api/facture-items").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(factureItemDTO)))
            .andExpect(status().isCreated());

        // Validate the FactureItem in the database
        List<FactureItem> factureItemList = factureItemRepository.findAll();
        assertThat(factureItemList).hasSize(databaseSizeBeforeCreate + 1);
        FactureItem testFactureItem = factureItemList.get(factureItemList.size() - 1);
        assertThat(testFactureItem.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testFactureItem.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testFactureItem.getMontantRemise()).isEqualTo(DEFAULT_MONTANT_REMISE);
        assertThat(testFactureItem.getMontantPaye()).isEqualTo(DEFAULT_MONTANT_PAYE);
        assertThat(testFactureItem.getStatutFacture()).isEqualTo(DEFAULT_STATUT_FACTURE);
        assertThat(testFactureItem.getMontantRestant()).isEqualTo(DEFAULT_MONTANT_RESTANT);
        assertThat(testFactureItem.getMontantNet()).isEqualTo(DEFAULT_MONTANT_NET);
        assertThat(testFactureItem.getMontantBrut()).isEqualTo(DEFAULT_MONTANT_BRUT);
    }

    @Test
    @Transactional
    public void createFactureItemWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = factureItemRepository.findAll().size();

        // Create the FactureItem with an existing ID
        factureItem.setId(1L);
        FactureItemDTO factureItemDTO = factureItemMapper.toDto(factureItem);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFactureItemMockMvc.perform(post("/api/facture-items").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(factureItemDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FactureItem in the database
        List<FactureItem> factureItemList = factureItemRepository.findAll();
        assertThat(factureItemList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCreatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = factureItemRepository.findAll().size();
        // set the field null
        factureItem.setCreatedAt(null);

        // Create the FactureItem, which fails.
        FactureItemDTO factureItemDTO = factureItemMapper.toDto(factureItem);


        restFactureItemMockMvc.perform(post("/api/facture-items").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(factureItemDTO)))
            .andExpect(status().isBadRequest());

        List<FactureItem> factureItemList = factureItemRepository.findAll();
        assertThat(factureItemList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUpdatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = factureItemRepository.findAll().size();
        // set the field null
        factureItem.setUpdatedAt(null);

        // Create the FactureItem, which fails.
        FactureItemDTO factureItemDTO = factureItemMapper.toDto(factureItem);


        restFactureItemMockMvc.perform(post("/api/facture-items").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(factureItemDTO)))
            .andExpect(status().isBadRequest());

        List<FactureItem> factureItemList = factureItemRepository.findAll();
        assertThat(factureItemList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatutFactureIsRequired() throws Exception {
        int databaseSizeBeforeTest = factureItemRepository.findAll().size();
        // set the field null
        factureItem.setStatutFacture(null);

        // Create the FactureItem, which fails.
        FactureItemDTO factureItemDTO = factureItemMapper.toDto(factureItem);


        restFactureItemMockMvc.perform(post("/api/facture-items").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(factureItemDTO)))
            .andExpect(status().isBadRequest());

        List<FactureItem> factureItemList = factureItemRepository.findAll();
        assertThat(factureItemList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFactureItems() throws Exception {
        // Initialize the database
        factureItemRepository.saveAndFlush(factureItem);

        // Get all the factureItemList
        restFactureItemMockMvc.perform(get("/api/facture-items?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(factureItem.getId().intValue())))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(DEFAULT_UPDATED_AT.toString())))
            .andExpect(jsonPath("$.[*].montantRemise").value(hasItem(DEFAULT_MONTANT_REMISE)))
            .andExpect(jsonPath("$.[*].montantPaye").value(hasItem(DEFAULT_MONTANT_PAYE)))
            .andExpect(jsonPath("$.[*].statutFacture").value(hasItem(DEFAULT_STATUT_FACTURE.toString())))
            .andExpect(jsonPath("$.[*].montantRestant").value(hasItem(DEFAULT_MONTANT_RESTANT)))
            .andExpect(jsonPath("$.[*].montantNet").value(hasItem(DEFAULT_MONTANT_NET)))
            .andExpect(jsonPath("$.[*].montantBrut").value(hasItem(DEFAULT_MONTANT_BRUT)));
    }
    
    @Test
    @Transactional
    public void getFactureItem() throws Exception {
        // Initialize the database
        factureItemRepository.saveAndFlush(factureItem);

        // Get the factureItem
        restFactureItemMockMvc.perform(get("/api/facture-items/{id}", factureItem.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(factureItem.getId().intValue()))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.updatedAt").value(DEFAULT_UPDATED_AT.toString()))
            .andExpect(jsonPath("$.montantRemise").value(DEFAULT_MONTANT_REMISE))
            .andExpect(jsonPath("$.montantPaye").value(DEFAULT_MONTANT_PAYE))
            .andExpect(jsonPath("$.statutFacture").value(DEFAULT_STATUT_FACTURE.toString()))
            .andExpect(jsonPath("$.montantRestant").value(DEFAULT_MONTANT_RESTANT))
            .andExpect(jsonPath("$.montantNet").value(DEFAULT_MONTANT_NET))
            .andExpect(jsonPath("$.montantBrut").value(DEFAULT_MONTANT_BRUT));
    }
    @Test
    @Transactional
    public void getNonExistingFactureItem() throws Exception {
        // Get the factureItem
        restFactureItemMockMvc.perform(get("/api/facture-items/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFactureItem() throws Exception {
        // Initialize the database
        factureItemRepository.saveAndFlush(factureItem);

        int databaseSizeBeforeUpdate = factureItemRepository.findAll().size();

        // Update the factureItem
        FactureItem updatedFactureItem = factureItemRepository.findById(factureItem.getId()).get();
        // Disconnect from session so that the updates on updatedFactureItem are not directly saved in db
        em.detach(updatedFactureItem);
        updatedFactureItem
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .montantRemise(UPDATED_MONTANT_REMISE)
            .montantPaye(UPDATED_MONTANT_PAYE)
            .statutFacture(UPDATED_STATUT_FACTURE)
            .montantRestant(UPDATED_MONTANT_RESTANT)
            .montantNet(UPDATED_MONTANT_NET)
            .montantBrut(UPDATED_MONTANT_BRUT);
        FactureItemDTO factureItemDTO = factureItemMapper.toDto(updatedFactureItem);

        restFactureItemMockMvc.perform(put("/api/facture-items").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(factureItemDTO)))
            .andExpect(status().isOk());

        // Validate the FactureItem in the database
        List<FactureItem> factureItemList = factureItemRepository.findAll();
        assertThat(factureItemList).hasSize(databaseSizeBeforeUpdate);
        FactureItem testFactureItem = factureItemList.get(factureItemList.size() - 1);
        assertThat(testFactureItem.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testFactureItem.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testFactureItem.getMontantRemise()).isEqualTo(UPDATED_MONTANT_REMISE);
        assertThat(testFactureItem.getMontantPaye()).isEqualTo(UPDATED_MONTANT_PAYE);
        assertThat(testFactureItem.getStatutFacture()).isEqualTo(UPDATED_STATUT_FACTURE);
        assertThat(testFactureItem.getMontantRestant()).isEqualTo(UPDATED_MONTANT_RESTANT);
        assertThat(testFactureItem.getMontantNet()).isEqualTo(UPDATED_MONTANT_NET);
        assertThat(testFactureItem.getMontantBrut()).isEqualTo(UPDATED_MONTANT_BRUT);
    }

    @Test
    @Transactional
    public void updateNonExistingFactureItem() throws Exception {
        int databaseSizeBeforeUpdate = factureItemRepository.findAll().size();

        // Create the FactureItem
        FactureItemDTO factureItemDTO = factureItemMapper.toDto(factureItem);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFactureItemMockMvc.perform(put("/api/facture-items").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(factureItemDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FactureItem in the database
        List<FactureItem> factureItemList = factureItemRepository.findAll();
        assertThat(factureItemList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFactureItem() throws Exception {
        // Initialize the database
        factureItemRepository.saveAndFlush(factureItem);

        int databaseSizeBeforeDelete = factureItemRepository.findAll().size();

        // Delete the factureItem
        restFactureItemMockMvc.perform(delete("/api/facture-items/{id}", factureItem.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<FactureItem> factureItemList = factureItemRepository.findAll();
        assertThat(factureItemList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
