package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.NucleusApp;
import com.kobe.nucleus.domain.CommandeItem;
import com.kobe.nucleus.domain.Produit;
import com.kobe.nucleus.repository.CommandeItemRepository;
import com.kobe.nucleus.service.CommandeItemService;
import com.kobe.nucleus.service.dto.CommandeItemDTO;
import com.kobe.nucleus.service.mapper.CommandeItemMapper;

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
import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.kobe.nucleus.domain.enumeration.Status;
/**
 * Integration tests for the {@link CommandeItemResource} REST controller.
 */
@SpringBootTest(classes = NucleusApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CommandeItemResourceIT {

    private static final Integer DEFAULT_MONTANT_CMD = 1;
    private static final Integer UPDATED_MONTANT_CMD = 2;

    private static final Integer DEFAULT_PRIX_PAF_CMD = 1;
    private static final Integer UPDATED_PRIX_PAF_CMD = 2;

    private static final Integer DEFAULT_PRIX_UNI_CMD = 1;
    private static final Integer UPDATED_PRIX_UNI_CMD = 2;

    private static final Integer DEFAULT_QTECMDE = 1;
    private static final Integer UPDATED_QTECMDE = 2;

    private static final Integer DEFAULT_QTERECU = 1;
    private static final Integer UPDATED_QTERECU = 2;

    private static final Integer DEFAULT_QTE_INIT = 1;
    private static final Integer UPDATED_QTE_INIT = 2;

    private static final Integer DEFAULT_QTEMAQUANT = 1;
    private static final Integer UPDATED_QTEMAQUANT = 2;

    private static final Integer DEFAULT_QTEGRATUITE = 1;
    private static final Integer UPDATED_QTEGRATUITE = 2;

    private static final Integer DEFAULT_MONTANT_BON = 1;
    private static final Integer UPDATED_MONTANT_BON = 2;

    private static final Integer DEFAULT_PRIX_PAF_BON = 1;
    private static final Integer UPDATED_PRIX_PAF_BON = 2;

    private static final Integer DEFAULT_PRIX_UNI_BON = 1;
    private static final Integer UPDATED_PRIX_UNI_BON = 2;

    private static final LocalDate DEFAULT_PEREMPTION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PEREMPTION = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_FABRICATION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_FABRICATION = LocalDate.now(ZoneId.systemDefault());

    private static final Instant DEFAULT_CREATED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_UPDATED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Status DEFAULT_STATUS = Status.ACTIVE;
    private static final Status UPDATED_STATUS = Status.ENATTENTE;

    @Autowired
    private CommandeItemRepository commandeItemRepository;

    @Autowired
    private CommandeItemMapper commandeItemMapper;

    @Autowired
    private CommandeItemService commandeItemService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCommandeItemMockMvc;

    private CommandeItem commandeItem;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CommandeItem createEntity(EntityManager em) {
        CommandeItem commandeItem = new CommandeItem()
            .montantCmd(DEFAULT_MONTANT_CMD)
            .prixPafCmd(DEFAULT_PRIX_PAF_CMD)
            .prixUniCmd(DEFAULT_PRIX_UNI_CMD)
            .qtecmde(DEFAULT_QTECMDE)
            .qterecu(DEFAULT_QTERECU)
            .qteInit(DEFAULT_QTE_INIT)
            .qtemaquant(DEFAULT_QTEMAQUANT)
            .qtegratuite(DEFAULT_QTEGRATUITE)
            .montantBon(DEFAULT_MONTANT_BON)
            .prixPafBon(DEFAULT_PRIX_PAF_BON)
            .prixUniBon(DEFAULT_PRIX_UNI_BON)
            .peremption(DEFAULT_PEREMPTION)
            .dateFabrication(DEFAULT_DATE_FABRICATION)
            .createdAt(DEFAULT_CREATED_AT)
            .updatedAt(DEFAULT_UPDATED_AT)
            .status(DEFAULT_STATUS);
        // Add required entity
        Produit produit;
        if (TestUtil.findAll(em, Produit.class).isEmpty()) {
            produit = ProduitResourceIT.createEntity(em);
            em.persist(produit);
            em.flush();
        } else {
            produit = TestUtil.findAll(em, Produit.class).get(0);
        }
        commandeItem.setProduit(produit);
        return commandeItem;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CommandeItem createUpdatedEntity(EntityManager em) {
        CommandeItem commandeItem = new CommandeItem()
            .montantCmd(UPDATED_MONTANT_CMD)
            .prixPafCmd(UPDATED_PRIX_PAF_CMD)
            .prixUniCmd(UPDATED_PRIX_UNI_CMD)
            .qtecmde(UPDATED_QTECMDE)
            .qterecu(UPDATED_QTERECU)
            .qteInit(UPDATED_QTE_INIT)
            .qtemaquant(UPDATED_QTEMAQUANT)
            .qtegratuite(UPDATED_QTEGRATUITE)
            .montantBon(UPDATED_MONTANT_BON)
            .prixPafBon(UPDATED_PRIX_PAF_BON)
            .prixUniBon(UPDATED_PRIX_UNI_BON)
            .peremption(UPDATED_PEREMPTION)
            .dateFabrication(UPDATED_DATE_FABRICATION)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .status(UPDATED_STATUS);
        // Add required entity
        Produit produit;
        if (TestUtil.findAll(em, Produit.class).isEmpty()) {
            produit = ProduitResourceIT.createUpdatedEntity(em);
            em.persist(produit);
            em.flush();
        } else {
            produit = TestUtil.findAll(em, Produit.class).get(0);
        }
        commandeItem.setProduit(produit);
        return commandeItem;
    }

    @BeforeEach
    public void initTest() {
        commandeItem = createEntity(em);
    }

    @Test
    @Transactional
    public void createCommandeItem() throws Exception {
        int databaseSizeBeforeCreate = commandeItemRepository.findAll().size();
        // Create the CommandeItem
        CommandeItemDTO commandeItemDTO = commandeItemMapper.toDto(commandeItem);
        restCommandeItemMockMvc.perform(post("/api/commande-items").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(commandeItemDTO)))
            .andExpect(status().isCreated());

        // Validate the CommandeItem in the database
        List<CommandeItem> commandeItemList = commandeItemRepository.findAll();
        assertThat(commandeItemList).hasSize(databaseSizeBeforeCreate + 1);
        CommandeItem testCommandeItem = commandeItemList.get(commandeItemList.size() - 1);
        assertThat(testCommandeItem.getMontantCmd()).isEqualTo(DEFAULT_MONTANT_CMD);
        assertThat(testCommandeItem.getPrixPafCmd()).isEqualTo(DEFAULT_PRIX_PAF_CMD);
        assertThat(testCommandeItem.getPrixUniCmd()).isEqualTo(DEFAULT_PRIX_UNI_CMD);
        assertThat(testCommandeItem.getQtecmde()).isEqualTo(DEFAULT_QTECMDE);
        assertThat(testCommandeItem.getQterecu()).isEqualTo(DEFAULT_QTERECU);
        assertThat(testCommandeItem.getQteInit()).isEqualTo(DEFAULT_QTE_INIT);
        assertThat(testCommandeItem.getQtemaquant()).isEqualTo(DEFAULT_QTEMAQUANT);
        assertThat(testCommandeItem.getQtegratuite()).isEqualTo(DEFAULT_QTEGRATUITE);
        assertThat(testCommandeItem.getMontantBon()).isEqualTo(DEFAULT_MONTANT_BON);
        assertThat(testCommandeItem.getPrixPafBon()).isEqualTo(DEFAULT_PRIX_PAF_BON);
        assertThat(testCommandeItem.getPrixUniBon()).isEqualTo(DEFAULT_PRIX_UNI_BON);
        assertThat(testCommandeItem.getPeremption()).isEqualTo(DEFAULT_PEREMPTION);
        assertThat(testCommandeItem.getDateFabrication()).isEqualTo(DEFAULT_DATE_FABRICATION);
        assertThat(testCommandeItem.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testCommandeItem.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testCommandeItem.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createCommandeItemWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = commandeItemRepository.findAll().size();

        // Create the CommandeItem with an existing ID
        commandeItem.setId(1L);
        CommandeItemDTO commandeItemDTO = commandeItemMapper.toDto(commandeItem);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCommandeItemMockMvc.perform(post("/api/commande-items").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(commandeItemDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CommandeItem in the database
        List<CommandeItem> commandeItemList = commandeItemRepository.findAll();
        assertThat(commandeItemList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = commandeItemRepository.findAll().size();
        // set the field null
        commandeItem.setStatus(null);

        // Create the CommandeItem, which fails.
        CommandeItemDTO commandeItemDTO = commandeItemMapper.toDto(commandeItem);


        restCommandeItemMockMvc.perform(post("/api/commande-items").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(commandeItemDTO)))
            .andExpect(status().isBadRequest());

        List<CommandeItem> commandeItemList = commandeItemRepository.findAll();
        assertThat(commandeItemList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCommandeItems() throws Exception {
        // Initialize the database
        commandeItemRepository.saveAndFlush(commandeItem);

        // Get all the commandeItemList
        restCommandeItemMockMvc.perform(get("/api/commande-items?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(commandeItem.getId().intValue())))
            .andExpect(jsonPath("$.[*].montantCmd").value(hasItem(DEFAULT_MONTANT_CMD)))
            .andExpect(jsonPath("$.[*].prixPafCmd").value(hasItem(DEFAULT_PRIX_PAF_CMD)))
            .andExpect(jsonPath("$.[*].prixUniCmd").value(hasItem(DEFAULT_PRIX_UNI_CMD)))
            .andExpect(jsonPath("$.[*].qtecmde").value(hasItem(DEFAULT_QTECMDE)))
            .andExpect(jsonPath("$.[*].qterecu").value(hasItem(DEFAULT_QTERECU)))
            .andExpect(jsonPath("$.[*].qteInit").value(hasItem(DEFAULT_QTE_INIT)))
            .andExpect(jsonPath("$.[*].qtemaquant").value(hasItem(DEFAULT_QTEMAQUANT)))
            .andExpect(jsonPath("$.[*].qtegratuite").value(hasItem(DEFAULT_QTEGRATUITE)))
            .andExpect(jsonPath("$.[*].montantBon").value(hasItem(DEFAULT_MONTANT_BON)))
            .andExpect(jsonPath("$.[*].prixPafBon").value(hasItem(DEFAULT_PRIX_PAF_BON)))
            .andExpect(jsonPath("$.[*].prixUniBon").value(hasItem(DEFAULT_PRIX_UNI_BON)))
            .andExpect(jsonPath("$.[*].peremption").value(hasItem(DEFAULT_PEREMPTION.toString())))
            .andExpect(jsonPath("$.[*].dateFabrication").value(hasItem(DEFAULT_DATE_FABRICATION.toString())))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(DEFAULT_UPDATED_AT.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }
    
    @Test
    @Transactional
    public void getCommandeItem() throws Exception {
        // Initialize the database
        commandeItemRepository.saveAndFlush(commandeItem);

        // Get the commandeItem
        restCommandeItemMockMvc.perform(get("/api/commande-items/{id}", commandeItem.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(commandeItem.getId().intValue()))
            .andExpect(jsonPath("$.montantCmd").value(DEFAULT_MONTANT_CMD))
            .andExpect(jsonPath("$.prixPafCmd").value(DEFAULT_PRIX_PAF_CMD))
            .andExpect(jsonPath("$.prixUniCmd").value(DEFAULT_PRIX_UNI_CMD))
            .andExpect(jsonPath("$.qtecmde").value(DEFAULT_QTECMDE))
            .andExpect(jsonPath("$.qterecu").value(DEFAULT_QTERECU))
            .andExpect(jsonPath("$.qteInit").value(DEFAULT_QTE_INIT))
            .andExpect(jsonPath("$.qtemaquant").value(DEFAULT_QTEMAQUANT))
            .andExpect(jsonPath("$.qtegratuite").value(DEFAULT_QTEGRATUITE))
            .andExpect(jsonPath("$.montantBon").value(DEFAULT_MONTANT_BON))
            .andExpect(jsonPath("$.prixPafBon").value(DEFAULT_PRIX_PAF_BON))
            .andExpect(jsonPath("$.prixUniBon").value(DEFAULT_PRIX_UNI_BON))
            .andExpect(jsonPath("$.peremption").value(DEFAULT_PEREMPTION.toString()))
            .andExpect(jsonPath("$.dateFabrication").value(DEFAULT_DATE_FABRICATION.toString()))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.updatedAt").value(DEFAULT_UPDATED_AT.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingCommandeItem() throws Exception {
        // Get the commandeItem
        restCommandeItemMockMvc.perform(get("/api/commande-items/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCommandeItem() throws Exception {
        // Initialize the database
        commandeItemRepository.saveAndFlush(commandeItem);

        int databaseSizeBeforeUpdate = commandeItemRepository.findAll().size();

        // Update the commandeItem
        CommandeItem updatedCommandeItem = commandeItemRepository.findById(commandeItem.getId()).get();
        // Disconnect from session so that the updates on updatedCommandeItem are not directly saved in db
        em.detach(updatedCommandeItem);
        updatedCommandeItem
            .montantCmd(UPDATED_MONTANT_CMD)
            .prixPafCmd(UPDATED_PRIX_PAF_CMD)
            .prixUniCmd(UPDATED_PRIX_UNI_CMD)
            .qtecmde(UPDATED_QTECMDE)
            .qterecu(UPDATED_QTERECU)
            .qteInit(UPDATED_QTE_INIT)
            .qtemaquant(UPDATED_QTEMAQUANT)
            .qtegratuite(UPDATED_QTEGRATUITE)
            .montantBon(UPDATED_MONTANT_BON)
            .prixPafBon(UPDATED_PRIX_PAF_BON)
            .prixUniBon(UPDATED_PRIX_UNI_BON)
            .peremption(UPDATED_PEREMPTION)
            .dateFabrication(UPDATED_DATE_FABRICATION)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .status(UPDATED_STATUS);
        CommandeItemDTO commandeItemDTO = commandeItemMapper.toDto(updatedCommandeItem);

        restCommandeItemMockMvc.perform(put("/api/commande-items").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(commandeItemDTO)))
            .andExpect(status().isOk());

        // Validate the CommandeItem in the database
        List<CommandeItem> commandeItemList = commandeItemRepository.findAll();
        assertThat(commandeItemList).hasSize(databaseSizeBeforeUpdate);
        CommandeItem testCommandeItem = commandeItemList.get(commandeItemList.size() - 1);
        assertThat(testCommandeItem.getMontantCmd()).isEqualTo(UPDATED_MONTANT_CMD);
        assertThat(testCommandeItem.getPrixPafCmd()).isEqualTo(UPDATED_PRIX_PAF_CMD);
        assertThat(testCommandeItem.getPrixUniCmd()).isEqualTo(UPDATED_PRIX_UNI_CMD);
        assertThat(testCommandeItem.getQtecmde()).isEqualTo(UPDATED_QTECMDE);
        assertThat(testCommandeItem.getQterecu()).isEqualTo(UPDATED_QTERECU);
        assertThat(testCommandeItem.getQteInit()).isEqualTo(UPDATED_QTE_INIT);
        assertThat(testCommandeItem.getQtemaquant()).isEqualTo(UPDATED_QTEMAQUANT);
        assertThat(testCommandeItem.getQtegratuite()).isEqualTo(UPDATED_QTEGRATUITE);
        assertThat(testCommandeItem.getMontantBon()).isEqualTo(UPDATED_MONTANT_BON);
        assertThat(testCommandeItem.getPrixPafBon()).isEqualTo(UPDATED_PRIX_PAF_BON);
        assertThat(testCommandeItem.getPrixUniBon()).isEqualTo(UPDATED_PRIX_UNI_BON);
        assertThat(testCommandeItem.getPeremption()).isEqualTo(UPDATED_PEREMPTION);
        assertThat(testCommandeItem.getDateFabrication()).isEqualTo(UPDATED_DATE_FABRICATION);
        assertThat(testCommandeItem.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testCommandeItem.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testCommandeItem.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingCommandeItem() throws Exception {
        int databaseSizeBeforeUpdate = commandeItemRepository.findAll().size();

        // Create the CommandeItem
        CommandeItemDTO commandeItemDTO = commandeItemMapper.toDto(commandeItem);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCommandeItemMockMvc.perform(put("/api/commande-items").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(commandeItemDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CommandeItem in the database
        List<CommandeItem> commandeItemList = commandeItemRepository.findAll();
        assertThat(commandeItemList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCommandeItem() throws Exception {
        // Initialize the database
        commandeItemRepository.saveAndFlush(commandeItem);

        int databaseSizeBeforeDelete = commandeItemRepository.findAll().size();

        // Delete the commandeItem
        restCommandeItemMockMvc.perform(delete("/api/commande-items/{id}", commandeItem.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CommandeItem> commandeItemList = commandeItemRepository.findAll();
        assertThat(commandeItemList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
