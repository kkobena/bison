package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.NucleusApp;
import com.kobe.nucleus.domain.Commande;
import com.kobe.nucleus.repository.CommandeRepository;
import com.kobe.nucleus.service.CommandeService;
import com.kobe.nucleus.service.dto.CommandeDTO;
import com.kobe.nucleus.service.mapper.CommandeMapper;

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

import com.kobe.nucleus.domain.enumeration.StatutFacture;
import com.kobe.nucleus.domain.enumeration.OrderStatus;
import com.kobe.nucleus.domain.enumeration.TypeOrder;
/**
 * Integration tests for the {@link CommandeResource} REST controller.
 */
@SpringBootTest(classes = NucleusApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CommandeResourceIT {

    private static final Instant DEFAULT_CREATED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_UPDATED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_NUM = "AAAAAAAAAA";
    private static final String UPDATED_NUM = "BBBBBBBBBB";

    private static final String DEFAULT_NUM_REF = "AAAAAAAAAA";
    private static final String UPDATED_NUM_REF = "BBBBBBBBBB";

    private static final Integer DEFAULT_MONTANT_CMD = 1;
    private static final Integer UPDATED_MONTANT_CMD = 2;

    private static final Integer DEFAULT_MONTANT_TVA = 1;
    private static final Integer UPDATED_MONTANT_TVA = 2;

    private static final Integer DEFAULT_MONTANT_HT = 1;
    private static final Integer UPDATED_MONTANT_HT = 2;

    private static final Integer DEFAULT_MONTANT_TTC = 1;
    private static final Integer UPDATED_MONTANT_TTC = 2;

    private static final Integer DEFAULT_MONTANT_REGL = 1;
    private static final Integer UPDATED_MONTANT_REGL = 2;

    private static final Integer DEFAULT_MONTANT_RESTANT = 1;
    private static final Integer UPDATED_MONTANT_RESTANT = 2;

    private static final LocalDate DEFAULT_DATE_REGL = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_REGL = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_END_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_END_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_LIVRAISON = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_LIVRAISON = LocalDate.now(ZoneId.systemDefault());

    private static final StatutFacture DEFAULT_STATUT_FACTURE = StatutFacture.PAID;
    private static final StatutFacture UPDATED_STATUT_FACTURE = StatutFacture.UNPAID;

    private static final OrderStatus DEFAULT_STATUS = OrderStatus.SUGGESSIONMANNUEL;
    private static final OrderStatus UPDATED_STATUS = OrderStatus.SUGGESSIONAUTOMATIK;

    private static final TypeOrder DEFAULT_TYPE = TypeOrder.SUGGESSION;
    private static final TypeOrder UPDATED_TYPE = TypeOrder.COMMANDE;

    @Autowired
    private CommandeRepository commandeRepository;

    @Autowired
    private CommandeMapper commandeMapper;

    @Autowired
    private CommandeService commandeService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCommandeMockMvc;

    private Commande commande;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Commande createEntity(EntityManager em) {
        Commande commande = new Commande()
            .createdAt(DEFAULT_CREATED_AT)
            .updatedAt(DEFAULT_UPDATED_AT)
            .num(DEFAULT_NUM)
            .numRef(DEFAULT_NUM_REF)
            .montantCmd(DEFAULT_MONTANT_CMD)
            .montantTva(DEFAULT_MONTANT_TVA)
            .montantHT(DEFAULT_MONTANT_HT)
            .montantTTC(DEFAULT_MONTANT_TTC)
            .montantRegl(DEFAULT_MONTANT_REGL)
            .montantRestant(DEFAULT_MONTANT_RESTANT)
            .dateRegl(DEFAULT_DATE_REGL)
            .endDate(DEFAULT_END_DATE)
            .dateLivraison(DEFAULT_DATE_LIVRAISON)
            .statutFacture(DEFAULT_STATUT_FACTURE)
            .status(DEFAULT_STATUS)
            .type(DEFAULT_TYPE);
        return commande;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Commande createUpdatedEntity(EntityManager em) {
        Commande commande = new Commande()
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .num(UPDATED_NUM)
            .numRef(UPDATED_NUM_REF)
            .montantCmd(UPDATED_MONTANT_CMD)
            .montantTva(UPDATED_MONTANT_TVA)
            .montantHT(UPDATED_MONTANT_HT)
            .montantTTC(UPDATED_MONTANT_TTC)
            .montantRegl(UPDATED_MONTANT_REGL)
            .montantRestant(UPDATED_MONTANT_RESTANT)
            .dateRegl(UPDATED_DATE_REGL)
            .endDate(UPDATED_END_DATE)
            .dateLivraison(UPDATED_DATE_LIVRAISON)
            .statutFacture(UPDATED_STATUT_FACTURE)
            .status(UPDATED_STATUS)
            .type(UPDATED_TYPE);
        return commande;
    }

    @BeforeEach
    public void initTest() {
        commande = createEntity(em);
    }

    @Test
    @Transactional
    public void createCommande() throws Exception {
        int databaseSizeBeforeCreate = commandeRepository.findAll().size();
        // Create the Commande
        CommandeDTO commandeDTO = commandeMapper.toDto(commande);
        restCommandeMockMvc.perform(post("/api/commandes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(commandeDTO)))
            .andExpect(status().isCreated());

        // Validate the Commande in the database
        List<Commande> commandeList = commandeRepository.findAll();
        assertThat(commandeList).hasSize(databaseSizeBeforeCreate + 1);
        Commande testCommande = commandeList.get(commandeList.size() - 1);
        assertThat(testCommande.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testCommande.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testCommande.getNum()).isEqualTo(DEFAULT_NUM);
        assertThat(testCommande.getNumRef()).isEqualTo(DEFAULT_NUM_REF);
        assertThat(testCommande.getMontantCmd()).isEqualTo(DEFAULT_MONTANT_CMD);
        assertThat(testCommande.getMontantTva()).isEqualTo(DEFAULT_MONTANT_TVA);
        assertThat(testCommande.getMontantHT()).isEqualTo(DEFAULT_MONTANT_HT);
        assertThat(testCommande.getMontantTTC()).isEqualTo(DEFAULT_MONTANT_TTC);
        assertThat(testCommande.getMontantRegl()).isEqualTo(DEFAULT_MONTANT_REGL);
        assertThat(testCommande.getMontantRestant()).isEqualTo(DEFAULT_MONTANT_RESTANT);
        assertThat(testCommande.getDateRegl()).isEqualTo(DEFAULT_DATE_REGL);
        assertThat(testCommande.getEndDate()).isEqualTo(DEFAULT_END_DATE);
        assertThat(testCommande.getDateLivraison()).isEqualTo(DEFAULT_DATE_LIVRAISON);
        assertThat(testCommande.getStatutFacture()).isEqualTo(DEFAULT_STATUT_FACTURE);
        assertThat(testCommande.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testCommande.getType()).isEqualTo(DEFAULT_TYPE);
    }

    @Test
    @Transactional
    public void createCommandeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = commandeRepository.findAll().size();

        // Create the Commande with an existing ID
        commande.setId(1L);
        CommandeDTO commandeDTO = commandeMapper.toDto(commande);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCommandeMockMvc.perform(post("/api/commandes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(commandeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Commande in the database
        List<Commande> commandeList = commandeRepository.findAll();
        assertThat(commandeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCreatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = commandeRepository.findAll().size();
        // set the field null
        commande.setCreatedAt(null);

        // Create the Commande, which fails.
        CommandeDTO commandeDTO = commandeMapper.toDto(commande);


        restCommandeMockMvc.perform(post("/api/commandes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(commandeDTO)))
            .andExpect(status().isBadRequest());

        List<Commande> commandeList = commandeRepository.findAll();
        assertThat(commandeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUpdatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = commandeRepository.findAll().size();
        // set the field null
        commande.setUpdatedAt(null);

        // Create the Commande, which fails.
        CommandeDTO commandeDTO = commandeMapper.toDto(commande);


        restCommandeMockMvc.perform(post("/api/commandes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(commandeDTO)))
            .andExpect(status().isBadRequest());

        List<Commande> commandeList = commandeRepository.findAll();
        assertThat(commandeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatutFactureIsRequired() throws Exception {
        int databaseSizeBeforeTest = commandeRepository.findAll().size();
        // set the field null
        commande.setStatutFacture(null);

        // Create the Commande, which fails.
        CommandeDTO commandeDTO = commandeMapper.toDto(commande);


        restCommandeMockMvc.perform(post("/api/commandes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(commandeDTO)))
            .andExpect(status().isBadRequest());

        List<Commande> commandeList = commandeRepository.findAll();
        assertThat(commandeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCommandes() throws Exception {
        // Initialize the database
        commandeRepository.saveAndFlush(commande);

        // Get all the commandeList
        restCommandeMockMvc.perform(get("/api/commandes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(commande.getId().intValue())))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(DEFAULT_UPDATED_AT.toString())))
            .andExpect(jsonPath("$.[*].num").value(hasItem(DEFAULT_NUM)))
            .andExpect(jsonPath("$.[*].numRef").value(hasItem(DEFAULT_NUM_REF)))
            .andExpect(jsonPath("$.[*].montantCmd").value(hasItem(DEFAULT_MONTANT_CMD)))
            .andExpect(jsonPath("$.[*].montantTva").value(hasItem(DEFAULT_MONTANT_TVA)))
            .andExpect(jsonPath("$.[*].montantHT").value(hasItem(DEFAULT_MONTANT_HT)))
            .andExpect(jsonPath("$.[*].montantTTC").value(hasItem(DEFAULT_MONTANT_TTC)))
            .andExpect(jsonPath("$.[*].montantRegl").value(hasItem(DEFAULT_MONTANT_REGL)))
            .andExpect(jsonPath("$.[*].montantRestant").value(hasItem(DEFAULT_MONTANT_RESTANT)))
            .andExpect(jsonPath("$.[*].dateRegl").value(hasItem(DEFAULT_DATE_REGL.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].dateLivraison").value(hasItem(DEFAULT_DATE_LIVRAISON.toString())))
            .andExpect(jsonPath("$.[*].statutFacture").value(hasItem(DEFAULT_STATUT_FACTURE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())));
    }
    
    @Test
    @Transactional
    public void getCommande() throws Exception {
        // Initialize the database
        commandeRepository.saveAndFlush(commande);

        // Get the commande
        restCommandeMockMvc.perform(get("/api/commandes/{id}", commande.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(commande.getId().intValue()))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.updatedAt").value(DEFAULT_UPDATED_AT.toString()))
            .andExpect(jsonPath("$.num").value(DEFAULT_NUM))
            .andExpect(jsonPath("$.numRef").value(DEFAULT_NUM_REF))
            .andExpect(jsonPath("$.montantCmd").value(DEFAULT_MONTANT_CMD))
            .andExpect(jsonPath("$.montantTva").value(DEFAULT_MONTANT_TVA))
            .andExpect(jsonPath("$.montantHT").value(DEFAULT_MONTANT_HT))
            .andExpect(jsonPath("$.montantTTC").value(DEFAULT_MONTANT_TTC))
            .andExpect(jsonPath("$.montantRegl").value(DEFAULT_MONTANT_REGL))
            .andExpect(jsonPath("$.montantRestant").value(DEFAULT_MONTANT_RESTANT))
            .andExpect(jsonPath("$.dateRegl").value(DEFAULT_DATE_REGL.toString()))
            .andExpect(jsonPath("$.endDate").value(DEFAULT_END_DATE.toString()))
            .andExpect(jsonPath("$.dateLivraison").value(DEFAULT_DATE_LIVRAISON.toString()))
            .andExpect(jsonPath("$.statutFacture").value(DEFAULT_STATUT_FACTURE.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingCommande() throws Exception {
        // Get the commande
        restCommandeMockMvc.perform(get("/api/commandes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCommande() throws Exception {
        // Initialize the database
        commandeRepository.saveAndFlush(commande);

        int databaseSizeBeforeUpdate = commandeRepository.findAll().size();

        // Update the commande
        Commande updatedCommande = commandeRepository.findById(commande.getId()).get();
        // Disconnect from session so that the updates on updatedCommande are not directly saved in db
        em.detach(updatedCommande);
        updatedCommande
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .num(UPDATED_NUM)
            .numRef(UPDATED_NUM_REF)
            .montantCmd(UPDATED_MONTANT_CMD)
            .montantTva(UPDATED_MONTANT_TVA)
            .montantHT(UPDATED_MONTANT_HT)
            .montantTTC(UPDATED_MONTANT_TTC)
            .montantRegl(UPDATED_MONTANT_REGL)
            .montantRestant(UPDATED_MONTANT_RESTANT)
            .dateRegl(UPDATED_DATE_REGL)
            .endDate(UPDATED_END_DATE)
            .dateLivraison(UPDATED_DATE_LIVRAISON)
            .statutFacture(UPDATED_STATUT_FACTURE)
            .status(UPDATED_STATUS)
            .type(UPDATED_TYPE);
        CommandeDTO commandeDTO = commandeMapper.toDto(updatedCommande);

        restCommandeMockMvc.perform(put("/api/commandes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(commandeDTO)))
            .andExpect(status().isOk());

        // Validate the Commande in the database
        List<Commande> commandeList = commandeRepository.findAll();
        assertThat(commandeList).hasSize(databaseSizeBeforeUpdate);
        Commande testCommande = commandeList.get(commandeList.size() - 1);
        assertThat(testCommande.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testCommande.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testCommande.getNum()).isEqualTo(UPDATED_NUM);
        assertThat(testCommande.getNumRef()).isEqualTo(UPDATED_NUM_REF);
        assertThat(testCommande.getMontantCmd()).isEqualTo(UPDATED_MONTANT_CMD);
        assertThat(testCommande.getMontantTva()).isEqualTo(UPDATED_MONTANT_TVA);
        assertThat(testCommande.getMontantHT()).isEqualTo(UPDATED_MONTANT_HT);
        assertThat(testCommande.getMontantTTC()).isEqualTo(UPDATED_MONTANT_TTC);
        assertThat(testCommande.getMontantRegl()).isEqualTo(UPDATED_MONTANT_REGL);
        assertThat(testCommande.getMontantRestant()).isEqualTo(UPDATED_MONTANT_RESTANT);
        assertThat(testCommande.getDateRegl()).isEqualTo(UPDATED_DATE_REGL);
        assertThat(testCommande.getEndDate()).isEqualTo(UPDATED_END_DATE);
        assertThat(testCommande.getDateLivraison()).isEqualTo(UPDATED_DATE_LIVRAISON);
        assertThat(testCommande.getStatutFacture()).isEqualTo(UPDATED_STATUT_FACTURE);
        assertThat(testCommande.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testCommande.getType()).isEqualTo(UPDATED_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingCommande() throws Exception {
        int databaseSizeBeforeUpdate = commandeRepository.findAll().size();

        // Create the Commande
        CommandeDTO commandeDTO = commandeMapper.toDto(commande);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCommandeMockMvc.perform(put("/api/commandes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(commandeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Commande in the database
        List<Commande> commandeList = commandeRepository.findAll();
        assertThat(commandeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCommande() throws Exception {
        // Initialize the database
        commandeRepository.saveAndFlush(commande);

        int databaseSizeBeforeDelete = commandeRepository.findAll().size();

        // Delete the commande
        restCommandeMockMvc.perform(delete("/api/commandes/{id}", commande.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Commande> commandeList = commandeRepository.findAll();
        assertThat(commandeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
