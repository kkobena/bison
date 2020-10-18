package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.NucleusApp;
import com.kobe.nucleus.domain.Lot;
import com.kobe.nucleus.repository.LotRepository;
import com.kobe.nucleus.service.LotService;
import com.kobe.nucleus.service.dto.LotDTO;
import com.kobe.nucleus.service.mapper.LotMapper;

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

import com.kobe.nucleus.domain.enumeration.Peremption;
/**
 * Integration tests for the {@link LotResource} REST controller.
 */
@SpringBootTest(classes = NucleusApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class LotResourceIT {

    private static final Instant DEFAULT_CREATED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_UPDATED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_QTE = 1;
    private static final Integer UPDATED_QTE = 2;

    private static final Integer DEFAULT_QT_UG = 1;
    private static final Integer UPDATED_QT_UG = 2;

    private static final String DEFAULT_NUM = "AAAAAAAAAA";
    private static final String UPDATED_NUM = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_FABRICATION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_FABRICATION = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_PEREMPTION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PEREMPTION = LocalDate.now(ZoneId.systemDefault());

    private static final Peremption DEFAULT_PEREMPTIONSTATUS = Peremption.PERIME;
    private static final Peremption UPDATED_PEREMPTIONSTATUS = Peremption.ENCOURS;

    @Autowired
    private LotRepository lotRepository;

    @Autowired
    private LotMapper lotMapper;

    @Autowired
    private LotService lotService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLotMockMvc;

    private Lot lot;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Lot createEntity(EntityManager em) {
        Lot lot = new Lot()
            .createdAt(DEFAULT_CREATED_AT)
            .updatedAt(DEFAULT_UPDATED_AT)
            .qte(DEFAULT_QTE)
            .qtUg(DEFAULT_QT_UG)
            .num(DEFAULT_NUM)
            .dateFabrication(DEFAULT_DATE_FABRICATION)
            .peremption(DEFAULT_PEREMPTION)
            .peremptionstatus(DEFAULT_PEREMPTIONSTATUS);
        return lot;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Lot createUpdatedEntity(EntityManager em) {
        Lot lot = new Lot()
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .qte(UPDATED_QTE)
            .qtUg(UPDATED_QT_UG)
            .num(UPDATED_NUM)
            .dateFabrication(UPDATED_DATE_FABRICATION)
            .peremption(UPDATED_PEREMPTION)
            .peremptionstatus(UPDATED_PEREMPTIONSTATUS);
        return lot;
    }

    @BeforeEach
    public void initTest() {
        lot = createEntity(em);
    }

    @Test
    @Transactional
    public void createLot() throws Exception {
        int databaseSizeBeforeCreate = lotRepository.findAll().size();
        // Create the Lot
        LotDTO lotDTO = lotMapper.toDto(lot);
        restLotMockMvc.perform(post("/api/lots").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(lotDTO)))
            .andExpect(status().isCreated());

        // Validate the Lot in the database
        List<Lot> lotList = lotRepository.findAll();
        assertThat(lotList).hasSize(databaseSizeBeforeCreate + 1);
        Lot testLot = lotList.get(lotList.size() - 1);
        assertThat(testLot.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testLot.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testLot.getQte()).isEqualTo(DEFAULT_QTE);
        assertThat(testLot.getQtUg()).isEqualTo(DEFAULT_QT_UG);
        assertThat(testLot.getNum()).isEqualTo(DEFAULT_NUM);
        assertThat(testLot.getDateFabrication()).isEqualTo(DEFAULT_DATE_FABRICATION);
        assertThat(testLot.getPeremption()).isEqualTo(DEFAULT_PEREMPTION);
        assertThat(testLot.getPeremptionstatus()).isEqualTo(DEFAULT_PEREMPTIONSTATUS);
    }

    @Test
    @Transactional
    public void createLotWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = lotRepository.findAll().size();

        // Create the Lot with an existing ID
        lot.setId(1L);
        LotDTO lotDTO = lotMapper.toDto(lot);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLotMockMvc.perform(post("/api/lots").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(lotDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Lot in the database
        List<Lot> lotList = lotRepository.findAll();
        assertThat(lotList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCreatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = lotRepository.findAll().size();
        // set the field null
        lot.setCreatedAt(null);

        // Create the Lot, which fails.
        LotDTO lotDTO = lotMapper.toDto(lot);


        restLotMockMvc.perform(post("/api/lots").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(lotDTO)))
            .andExpect(status().isBadRequest());

        List<Lot> lotList = lotRepository.findAll();
        assertThat(lotList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUpdatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = lotRepository.findAll().size();
        // set the field null
        lot.setUpdatedAt(null);

        // Create the Lot, which fails.
        LotDTO lotDTO = lotMapper.toDto(lot);


        restLotMockMvc.perform(post("/api/lots").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(lotDTO)))
            .andExpect(status().isBadRequest());

        List<Lot> lotList = lotRepository.findAll();
        assertThat(lotList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQteIsRequired() throws Exception {
        int databaseSizeBeforeTest = lotRepository.findAll().size();
        // set the field null
        lot.setQte(null);

        // Create the Lot, which fails.
        LotDTO lotDTO = lotMapper.toDto(lot);


        restLotMockMvc.perform(post("/api/lots").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(lotDTO)))
            .andExpect(status().isBadRequest());

        List<Lot> lotList = lotRepository.findAll();
        assertThat(lotList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQtUgIsRequired() throws Exception {
        int databaseSizeBeforeTest = lotRepository.findAll().size();
        // set the field null
        lot.setQtUg(null);

        // Create the Lot, which fails.
        LotDTO lotDTO = lotMapper.toDto(lot);


        restLotMockMvc.perform(post("/api/lots").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(lotDTO)))
            .andExpect(status().isBadRequest());

        List<Lot> lotList = lotRepository.findAll();
        assertThat(lotList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllLots() throws Exception {
        // Initialize the database
        lotRepository.saveAndFlush(lot);

        // Get all the lotList
        restLotMockMvc.perform(get("/api/lots?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(lot.getId().intValue())))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(DEFAULT_UPDATED_AT.toString())))
            .andExpect(jsonPath("$.[*].qte").value(hasItem(DEFAULT_QTE)))
            .andExpect(jsonPath("$.[*].qtUg").value(hasItem(DEFAULT_QT_UG)))
            .andExpect(jsonPath("$.[*].num").value(hasItem(DEFAULT_NUM)))
            .andExpect(jsonPath("$.[*].dateFabrication").value(hasItem(DEFAULT_DATE_FABRICATION.toString())))
            .andExpect(jsonPath("$.[*].peremption").value(hasItem(DEFAULT_PEREMPTION.toString())))
            .andExpect(jsonPath("$.[*].peremptionstatus").value(hasItem(DEFAULT_PEREMPTIONSTATUS.toString())));
    }
    
    @Test
    @Transactional
    public void getLot() throws Exception {
        // Initialize the database
        lotRepository.saveAndFlush(lot);

        // Get the lot
        restLotMockMvc.perform(get("/api/lots/{id}", lot.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(lot.getId().intValue()))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.updatedAt").value(DEFAULT_UPDATED_AT.toString()))
            .andExpect(jsonPath("$.qte").value(DEFAULT_QTE))
            .andExpect(jsonPath("$.qtUg").value(DEFAULT_QT_UG))
            .andExpect(jsonPath("$.num").value(DEFAULT_NUM))
            .andExpect(jsonPath("$.dateFabrication").value(DEFAULT_DATE_FABRICATION.toString()))
            .andExpect(jsonPath("$.peremption").value(DEFAULT_PEREMPTION.toString()))
            .andExpect(jsonPath("$.peremptionstatus").value(DEFAULT_PEREMPTIONSTATUS.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingLot() throws Exception {
        // Get the lot
        restLotMockMvc.perform(get("/api/lots/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLot() throws Exception {
        // Initialize the database
        lotRepository.saveAndFlush(lot);

        int databaseSizeBeforeUpdate = lotRepository.findAll().size();

        // Update the lot
        Lot updatedLot = lotRepository.findById(lot.getId()).get();
        // Disconnect from session so that the updates on updatedLot are not directly saved in db
        em.detach(updatedLot);
        updatedLot
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .qte(UPDATED_QTE)
            .qtUg(UPDATED_QT_UG)
            .num(UPDATED_NUM)
            .dateFabrication(UPDATED_DATE_FABRICATION)
            .peremption(UPDATED_PEREMPTION)
            .peremptionstatus(UPDATED_PEREMPTIONSTATUS);
        LotDTO lotDTO = lotMapper.toDto(updatedLot);

        restLotMockMvc.perform(put("/api/lots").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(lotDTO)))
            .andExpect(status().isOk());

        // Validate the Lot in the database
        List<Lot> lotList = lotRepository.findAll();
        assertThat(lotList).hasSize(databaseSizeBeforeUpdate);
        Lot testLot = lotList.get(lotList.size() - 1);
        assertThat(testLot.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testLot.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testLot.getQte()).isEqualTo(UPDATED_QTE);
        assertThat(testLot.getQtUg()).isEqualTo(UPDATED_QT_UG);
        assertThat(testLot.getNum()).isEqualTo(UPDATED_NUM);
        assertThat(testLot.getDateFabrication()).isEqualTo(UPDATED_DATE_FABRICATION);
        assertThat(testLot.getPeremption()).isEqualTo(UPDATED_PEREMPTION);
        assertThat(testLot.getPeremptionstatus()).isEqualTo(UPDATED_PEREMPTIONSTATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingLot() throws Exception {
        int databaseSizeBeforeUpdate = lotRepository.findAll().size();

        // Create the Lot
        LotDTO lotDTO = lotMapper.toDto(lot);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLotMockMvc.perform(put("/api/lots").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(lotDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Lot in the database
        List<Lot> lotList = lotRepository.findAll();
        assertThat(lotList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLot() throws Exception {
        // Initialize the database
        lotRepository.saveAndFlush(lot);

        int databaseSizeBeforeDelete = lotRepository.findAll().size();

        // Delete the lot
        restLotMockMvc.perform(delete("/api/lots/{id}", lot.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Lot> lotList = lotRepository.findAll();
        assertThat(lotList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
