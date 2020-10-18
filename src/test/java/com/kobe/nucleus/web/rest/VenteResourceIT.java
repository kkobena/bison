package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.NucleusApp;
import com.kobe.nucleus.domain.Vente;
import com.kobe.nucleus.domain.ModePaiement;
import com.kobe.nucleus.repository.VenteRepository;
import com.kobe.nucleus.service.VenteService;
import com.kobe.nucleus.service.dto.VenteDTO;
import com.kobe.nucleus.service.mapper.VenteMapper;

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
import com.kobe.nucleus.domain.enumeration.NatureVente;
import com.kobe.nucleus.domain.enumeration.TypeVente;
/**
 * Integration tests for the {@link VenteResource} REST controller.
 */
@SpringBootTest(classes = NucleusApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class VenteResourceIT {

    private static final Instant DEFAULT_CREATED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_UPDATED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final LocalDate DEFAULT_DATE_MVT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_MVT = LocalDate.now(ZoneId.systemDefault());

    private static final Status DEFAULT_STATUS = Status.ACTIVE;
    private static final Status UPDATED_STATUS = Status.ENATTENTE;

    private static final NatureVente DEFAULT_NATURE_VENTE = NatureVente.VO;
    private static final NatureVente UPDATED_NATURE_VENTE = NatureVente.VNO;

    private static final TypeVente DEFAULT_TYPE_VENTE = TypeVente.VNO;
    private static final TypeVente UPDATED_TYPE_VENTE = TypeVente.VO;

    private static final String DEFAULT_REF_VENTE = "AAAAAAAAAA";
    private static final String UPDATED_REF_VENTE = "BBBBBBBBBB";

    private static final String DEFAULT_NUM_TICKET = "AAAAAAAAAA";
    private static final String UPDATED_NUM_TICKET = "BBBBBBBBBB";

    private static final Double DEFAULT_TAUXREMISE = 1D;
    private static final Double UPDATED_TAUXREMISE = 2D;

    private static final Integer DEFAULT_MONTANT_VENTE = 1;
    private static final Integer UPDATED_MONTANT_VENTE = 2;

    private static final Integer DEFAULT_MONTANT_TVA = 1;
    private static final Integer UPDATED_MONTANT_TVA = 2;

    private static final Integer DEFAULT_MONTANT_NET = 1;
    private static final Integer UPDATED_MONTANT_NET = 2;

    private static final Integer DEFAULT_MONTAN_REMISE = 1;
    private static final Integer UPDATED_MONTAN_REMISE = 2;

    private static final Integer DEFAULT_VALEUR_MARGE = 1;
    private static final Integer UPDATED_VALEUR_MARGE = 2;

    private static final Boolean DEFAULT_AVOIR = false;
    private static final Boolean UPDATED_AVOIR = true;

    private static final Integer DEFAULT_MONTANT_PAYE = 1;
    private static final Integer UPDATED_MONTANT_PAYE = 2;

    private static final Integer DEFAULT_MONTANT_RESTANT = 1;
    private static final Integer UPDATED_MONTANT_RESTANT = 2;

    private static final Integer DEFAULT_MONTANT_TP = 1;
    private static final Integer UPDATED_MONTANT_TP = 2;

    private static final Integer DEFAULT_MONTANT_CLIENT = 1;
    private static final Integer UPDATED_MONTANT_CLIENT = 2;

    private static final Integer DEFAULT_MONTANT_VERSE = 1;
    private static final Integer UPDATED_MONTANT_VERSE = 2;

    private static final Integer DEFAULT_MONTANT_RENDU = 1;
    private static final Integer UPDATED_MONTANT_RENDU = 2;

    private static final String DEFAULT_REF_BON = "AAAAAAAAAA";
    private static final String UPDATED_REF_BON = "BBBBBBBBBB";

    private static final Integer DEFAULT_AVOID_AMOUNT = 1;
    private static final Integer UPDATED_AVOID_AMOUNT = 2;

    @Autowired
    private VenteRepository venteRepository;

    @Autowired
    private VenteMapper venteMapper;

    @Autowired
    private VenteService venteService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restVenteMockMvc;

    private Vente vente;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Vente createEntity(EntityManager em) {
        Vente vente = new Vente()
            .createdAt(DEFAULT_CREATED_AT)
            .updatedAt(DEFAULT_UPDATED_AT)
            .dateMVT(DEFAULT_DATE_MVT)
            .status(DEFAULT_STATUS)
            .natureVente(DEFAULT_NATURE_VENTE)
            .typeVente(DEFAULT_TYPE_VENTE)
            .refVente(DEFAULT_REF_VENTE)
            .numTicket(DEFAULT_NUM_TICKET)
            .tauxremise(DEFAULT_TAUXREMISE)
            .montantVente(DEFAULT_MONTANT_VENTE)
            .montantTva(DEFAULT_MONTANT_TVA)
            .montantNet(DEFAULT_MONTANT_NET)
            .montanRemise(DEFAULT_MONTAN_REMISE)
            .valeurMarge(DEFAULT_VALEUR_MARGE)
            .avoir(DEFAULT_AVOIR)
            .montantPaye(DEFAULT_MONTANT_PAYE)
            .montantRestant(DEFAULT_MONTANT_RESTANT)
            .montantTp(DEFAULT_MONTANT_TP)
            .montantClient(DEFAULT_MONTANT_CLIENT)
            .montantVerse(DEFAULT_MONTANT_VERSE)
            .montantRendu(DEFAULT_MONTANT_RENDU)
            .refBon(DEFAULT_REF_BON)
            .avoidAmount(DEFAULT_AVOID_AMOUNT);
        // Add required entity
        ModePaiement modePaiement;
        if (TestUtil.findAll(em, ModePaiement.class).isEmpty()) {
            modePaiement = ModePaiementResourceIT.createEntity(em);
            em.persist(modePaiement);
            em.flush();
        } else {
            modePaiement = TestUtil.findAll(em, ModePaiement.class).get(0);
        }
        vente.setModePaiement(modePaiement);
        return vente;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Vente createUpdatedEntity(EntityManager em) {
        Vente vente = new Vente()
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .dateMVT(UPDATED_DATE_MVT)
            .status(UPDATED_STATUS)
            .natureVente(UPDATED_NATURE_VENTE)
            .typeVente(UPDATED_TYPE_VENTE)
            .refVente(UPDATED_REF_VENTE)
            .numTicket(UPDATED_NUM_TICKET)
            .tauxremise(UPDATED_TAUXREMISE)
            .montantVente(UPDATED_MONTANT_VENTE)
            .montantTva(UPDATED_MONTANT_TVA)
            .montantNet(UPDATED_MONTANT_NET)
            .montanRemise(UPDATED_MONTAN_REMISE)
            .valeurMarge(UPDATED_VALEUR_MARGE)
            .avoir(UPDATED_AVOIR)
            .montantPaye(UPDATED_MONTANT_PAYE)
            .montantRestant(UPDATED_MONTANT_RESTANT)
            .montantTp(UPDATED_MONTANT_TP)
            .montantClient(UPDATED_MONTANT_CLIENT)
            .montantVerse(UPDATED_MONTANT_VERSE)
            .montantRendu(UPDATED_MONTANT_RENDU)
            .refBon(UPDATED_REF_BON)
            .avoidAmount(UPDATED_AVOID_AMOUNT);
        // Add required entity
        ModePaiement modePaiement;
        if (TestUtil.findAll(em, ModePaiement.class).isEmpty()) {
            modePaiement = ModePaiementResourceIT.createUpdatedEntity(em);
            em.persist(modePaiement);
            em.flush();
        } else {
            modePaiement = TestUtil.findAll(em, ModePaiement.class).get(0);
        }
        vente.setModePaiement(modePaiement);
        return vente;
    }

    @BeforeEach
    public void initTest() {
        vente = createEntity(em);
    }

    @Test
    @Transactional
    public void createVente() throws Exception {
        int databaseSizeBeforeCreate = venteRepository.findAll().size();
        // Create the Vente
        VenteDTO venteDTO = venteMapper.toDto(vente);
        restVenteMockMvc.perform(post("/api/ventes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(venteDTO)))
            .andExpect(status().isCreated());

        // Validate the Vente in the database
        List<Vente> venteList = venteRepository.findAll();
        assertThat(venteList).hasSize(databaseSizeBeforeCreate + 1);
        Vente testVente = venteList.get(venteList.size() - 1);
        assertThat(testVente.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testVente.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testVente.getDateMVT()).isEqualTo(DEFAULT_DATE_MVT);
        assertThat(testVente.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testVente.getNatureVente()).isEqualTo(DEFAULT_NATURE_VENTE);
        assertThat(testVente.getTypeVente()).isEqualTo(DEFAULT_TYPE_VENTE);
        assertThat(testVente.getRefVente()).isEqualTo(DEFAULT_REF_VENTE);
        assertThat(testVente.getNumTicket()).isEqualTo(DEFAULT_NUM_TICKET);
        assertThat(testVente.getTauxremise()).isEqualTo(DEFAULT_TAUXREMISE);
        assertThat(testVente.getMontantVente()).isEqualTo(DEFAULT_MONTANT_VENTE);
        assertThat(testVente.getMontantTva()).isEqualTo(DEFAULT_MONTANT_TVA);
        assertThat(testVente.getMontantNet()).isEqualTo(DEFAULT_MONTANT_NET);
        assertThat(testVente.getMontanRemise()).isEqualTo(DEFAULT_MONTAN_REMISE);
        assertThat(testVente.getValeurMarge()).isEqualTo(DEFAULT_VALEUR_MARGE);
        assertThat(testVente.isAvoir()).isEqualTo(DEFAULT_AVOIR);
        assertThat(testVente.getMontantPaye()).isEqualTo(DEFAULT_MONTANT_PAYE);
        assertThat(testVente.getMontantRestant()).isEqualTo(DEFAULT_MONTANT_RESTANT);
        assertThat(testVente.getMontantTp()).isEqualTo(DEFAULT_MONTANT_TP);
        assertThat(testVente.getMontantClient()).isEqualTo(DEFAULT_MONTANT_CLIENT);
        assertThat(testVente.getMontantVerse()).isEqualTo(DEFAULT_MONTANT_VERSE);
        assertThat(testVente.getMontantRendu()).isEqualTo(DEFAULT_MONTANT_RENDU);
        assertThat(testVente.getRefBon()).isEqualTo(DEFAULT_REF_BON);
        assertThat(testVente.getAvoidAmount()).isEqualTo(DEFAULT_AVOID_AMOUNT);
    }

    @Test
    @Transactional
    public void createVenteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = venteRepository.findAll().size();

        // Create the Vente with an existing ID
        vente.setId(1L);
        VenteDTO venteDTO = venteMapper.toDto(vente);

        // An entity with an existing ID cannot be created, so this API call must fail
        restVenteMockMvc.perform(post("/api/ventes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(venteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Vente in the database
        List<Vente> venteList = venteRepository.findAll();
        assertThat(venteList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCreatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = venteRepository.findAll().size();
        // set the field null
        vente.setCreatedAt(null);

        // Create the Vente, which fails.
        VenteDTO venteDTO = venteMapper.toDto(vente);


        restVenteMockMvc.perform(post("/api/ventes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(venteDTO)))
            .andExpect(status().isBadRequest());

        List<Vente> venteList = venteRepository.findAll();
        assertThat(venteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUpdatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = venteRepository.findAll().size();
        // set the field null
        vente.setUpdatedAt(null);

        // Create the Vente, which fails.
        VenteDTO venteDTO = venteMapper.toDto(vente);


        restVenteMockMvc.perform(post("/api/ventes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(venteDTO)))
            .andExpect(status().isBadRequest());

        List<Vente> venteList = venteRepository.findAll();
        assertThat(venteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = venteRepository.findAll().size();
        // set the field null
        vente.setStatus(null);

        // Create the Vente, which fails.
        VenteDTO venteDTO = venteMapper.toDto(vente);


        restVenteMockMvc.perform(post("/api/ventes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(venteDTO)))
            .andExpect(status().isBadRequest());

        List<Vente> venteList = venteRepository.findAll();
        assertThat(venteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAvoidAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = venteRepository.findAll().size();
        // set the field null
        vente.setAvoidAmount(null);

        // Create the Vente, which fails.
        VenteDTO venteDTO = venteMapper.toDto(vente);


        restVenteMockMvc.perform(post("/api/ventes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(venteDTO)))
            .andExpect(status().isBadRequest());

        List<Vente> venteList = venteRepository.findAll();
        assertThat(venteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllVentes() throws Exception {
        // Initialize the database
        venteRepository.saveAndFlush(vente);

        // Get all the venteList
        restVenteMockMvc.perform(get("/api/ventes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(vente.getId().intValue())))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(DEFAULT_UPDATED_AT.toString())))
            .andExpect(jsonPath("$.[*].dateMVT").value(hasItem(DEFAULT_DATE_MVT.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].natureVente").value(hasItem(DEFAULT_NATURE_VENTE.toString())))
            .andExpect(jsonPath("$.[*].typeVente").value(hasItem(DEFAULT_TYPE_VENTE.toString())))
            .andExpect(jsonPath("$.[*].refVente").value(hasItem(DEFAULT_REF_VENTE)))
            .andExpect(jsonPath("$.[*].numTicket").value(hasItem(DEFAULT_NUM_TICKET)))
            .andExpect(jsonPath("$.[*].tauxremise").value(hasItem(DEFAULT_TAUXREMISE.doubleValue())))
            .andExpect(jsonPath("$.[*].montantVente").value(hasItem(DEFAULT_MONTANT_VENTE)))
            .andExpect(jsonPath("$.[*].montantTva").value(hasItem(DEFAULT_MONTANT_TVA)))
            .andExpect(jsonPath("$.[*].montantNet").value(hasItem(DEFAULT_MONTANT_NET)))
            .andExpect(jsonPath("$.[*].montanRemise").value(hasItem(DEFAULT_MONTAN_REMISE)))
            .andExpect(jsonPath("$.[*].valeurMarge").value(hasItem(DEFAULT_VALEUR_MARGE)))
            .andExpect(jsonPath("$.[*].avoir").value(hasItem(DEFAULT_AVOIR.booleanValue())))
            .andExpect(jsonPath("$.[*].montantPaye").value(hasItem(DEFAULT_MONTANT_PAYE)))
            .andExpect(jsonPath("$.[*].montantRestant").value(hasItem(DEFAULT_MONTANT_RESTANT)))
            .andExpect(jsonPath("$.[*].montantTp").value(hasItem(DEFAULT_MONTANT_TP)))
            .andExpect(jsonPath("$.[*].montantClient").value(hasItem(DEFAULT_MONTANT_CLIENT)))
            .andExpect(jsonPath("$.[*].montantVerse").value(hasItem(DEFAULT_MONTANT_VERSE)))
            .andExpect(jsonPath("$.[*].montantRendu").value(hasItem(DEFAULT_MONTANT_RENDU)))
            .andExpect(jsonPath("$.[*].refBon").value(hasItem(DEFAULT_REF_BON)))
            .andExpect(jsonPath("$.[*].avoidAmount").value(hasItem(DEFAULT_AVOID_AMOUNT)));
    }
    
    @Test
    @Transactional
    public void getVente() throws Exception {
        // Initialize the database
        venteRepository.saveAndFlush(vente);

        // Get the vente
        restVenteMockMvc.perform(get("/api/ventes/{id}", vente.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(vente.getId().intValue()))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.updatedAt").value(DEFAULT_UPDATED_AT.toString()))
            .andExpect(jsonPath("$.dateMVT").value(DEFAULT_DATE_MVT.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.natureVente").value(DEFAULT_NATURE_VENTE.toString()))
            .andExpect(jsonPath("$.typeVente").value(DEFAULT_TYPE_VENTE.toString()))
            .andExpect(jsonPath("$.refVente").value(DEFAULT_REF_VENTE))
            .andExpect(jsonPath("$.numTicket").value(DEFAULT_NUM_TICKET))
            .andExpect(jsonPath("$.tauxremise").value(DEFAULT_TAUXREMISE.doubleValue()))
            .andExpect(jsonPath("$.montantVente").value(DEFAULT_MONTANT_VENTE))
            .andExpect(jsonPath("$.montantTva").value(DEFAULT_MONTANT_TVA))
            .andExpect(jsonPath("$.montantNet").value(DEFAULT_MONTANT_NET))
            .andExpect(jsonPath("$.montanRemise").value(DEFAULT_MONTAN_REMISE))
            .andExpect(jsonPath("$.valeurMarge").value(DEFAULT_VALEUR_MARGE))
            .andExpect(jsonPath("$.avoir").value(DEFAULT_AVOIR.booleanValue()))
            .andExpect(jsonPath("$.montantPaye").value(DEFAULT_MONTANT_PAYE))
            .andExpect(jsonPath("$.montantRestant").value(DEFAULT_MONTANT_RESTANT))
            .andExpect(jsonPath("$.montantTp").value(DEFAULT_MONTANT_TP))
            .andExpect(jsonPath("$.montantClient").value(DEFAULT_MONTANT_CLIENT))
            .andExpect(jsonPath("$.montantVerse").value(DEFAULT_MONTANT_VERSE))
            .andExpect(jsonPath("$.montantRendu").value(DEFAULT_MONTANT_RENDU))
            .andExpect(jsonPath("$.refBon").value(DEFAULT_REF_BON))
            .andExpect(jsonPath("$.avoidAmount").value(DEFAULT_AVOID_AMOUNT));
    }
    @Test
    @Transactional
    public void getNonExistingVente() throws Exception {
        // Get the vente
        restVenteMockMvc.perform(get("/api/ventes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateVente() throws Exception {
        // Initialize the database
        venteRepository.saveAndFlush(vente);

        int databaseSizeBeforeUpdate = venteRepository.findAll().size();

        // Update the vente
        Vente updatedVente = venteRepository.findById(vente.getId()).get();
        // Disconnect from session so that the updates on updatedVente are not directly saved in db
        em.detach(updatedVente);
        updatedVente
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .dateMVT(UPDATED_DATE_MVT)
            .status(UPDATED_STATUS)
            .natureVente(UPDATED_NATURE_VENTE)
            .typeVente(UPDATED_TYPE_VENTE)
            .refVente(UPDATED_REF_VENTE)
            .numTicket(UPDATED_NUM_TICKET)
            .tauxremise(UPDATED_TAUXREMISE)
            .montantVente(UPDATED_MONTANT_VENTE)
            .montantTva(UPDATED_MONTANT_TVA)
            .montantNet(UPDATED_MONTANT_NET)
            .montanRemise(UPDATED_MONTAN_REMISE)
            .valeurMarge(UPDATED_VALEUR_MARGE)
            .avoir(UPDATED_AVOIR)
            .montantPaye(UPDATED_MONTANT_PAYE)
            .montantRestant(UPDATED_MONTANT_RESTANT)
            .montantTp(UPDATED_MONTANT_TP)
            .montantClient(UPDATED_MONTANT_CLIENT)
            .montantVerse(UPDATED_MONTANT_VERSE)
            .montantRendu(UPDATED_MONTANT_RENDU)
            .refBon(UPDATED_REF_BON)
            .avoidAmount(UPDATED_AVOID_AMOUNT);
        VenteDTO venteDTO = venteMapper.toDto(updatedVente);

        restVenteMockMvc.perform(put("/api/ventes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(venteDTO)))
            .andExpect(status().isOk());

        // Validate the Vente in the database
        List<Vente> venteList = venteRepository.findAll();
        assertThat(venteList).hasSize(databaseSizeBeforeUpdate);
        Vente testVente = venteList.get(venteList.size() - 1);
        assertThat(testVente.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testVente.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testVente.getDateMVT()).isEqualTo(UPDATED_DATE_MVT);
        assertThat(testVente.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testVente.getNatureVente()).isEqualTo(UPDATED_NATURE_VENTE);
        assertThat(testVente.getTypeVente()).isEqualTo(UPDATED_TYPE_VENTE);
        assertThat(testVente.getRefVente()).isEqualTo(UPDATED_REF_VENTE);
        assertThat(testVente.getNumTicket()).isEqualTo(UPDATED_NUM_TICKET);
        assertThat(testVente.getTauxremise()).isEqualTo(UPDATED_TAUXREMISE);
        assertThat(testVente.getMontantVente()).isEqualTo(UPDATED_MONTANT_VENTE);
        assertThat(testVente.getMontantTva()).isEqualTo(UPDATED_MONTANT_TVA);
        assertThat(testVente.getMontantNet()).isEqualTo(UPDATED_MONTANT_NET);
        assertThat(testVente.getMontanRemise()).isEqualTo(UPDATED_MONTAN_REMISE);
        assertThat(testVente.getValeurMarge()).isEqualTo(UPDATED_VALEUR_MARGE);
        assertThat(testVente.isAvoir()).isEqualTo(UPDATED_AVOIR);
        assertThat(testVente.getMontantPaye()).isEqualTo(UPDATED_MONTANT_PAYE);
        assertThat(testVente.getMontantRestant()).isEqualTo(UPDATED_MONTANT_RESTANT);
        assertThat(testVente.getMontantTp()).isEqualTo(UPDATED_MONTANT_TP);
        assertThat(testVente.getMontantClient()).isEqualTo(UPDATED_MONTANT_CLIENT);
        assertThat(testVente.getMontantVerse()).isEqualTo(UPDATED_MONTANT_VERSE);
        assertThat(testVente.getMontantRendu()).isEqualTo(UPDATED_MONTANT_RENDU);
        assertThat(testVente.getRefBon()).isEqualTo(UPDATED_REF_BON);
        assertThat(testVente.getAvoidAmount()).isEqualTo(UPDATED_AVOID_AMOUNT);
    }

    @Test
    @Transactional
    public void updateNonExistingVente() throws Exception {
        int databaseSizeBeforeUpdate = venteRepository.findAll().size();

        // Create the Vente
        VenteDTO venteDTO = venteMapper.toDto(vente);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVenteMockMvc.perform(put("/api/ventes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(venteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Vente in the database
        List<Vente> venteList = venteRepository.findAll();
        assertThat(venteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteVente() throws Exception {
        // Initialize the database
        venteRepository.saveAndFlush(vente);

        int databaseSizeBeforeDelete = venteRepository.findAll().size();

        // Delete the vente
        restVenteMockMvc.perform(delete("/api/ventes/{id}", vente.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Vente> venteList = venteRepository.findAll();
        assertThat(venteList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
