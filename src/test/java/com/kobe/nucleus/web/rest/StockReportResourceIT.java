package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.NucleusApp;
import com.kobe.nucleus.domain.StockReport;
import com.kobe.nucleus.repository.StockReportRepository;
import com.kobe.nucleus.service.StockReportService;
import com.kobe.nucleus.service.dto.StockReportDTO;
import com.kobe.nucleus.service.mapper.StockReportMapper;

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
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link StockReportResource} REST controller.
 */
@SpringBootTest(classes = NucleusApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class StockReportResourceIT {

    private static final Integer DEFAULT_QTY = 1;
    private static final Integer UPDATED_QTY = 2;

    private static final LocalDate DEFAULT_MVT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_MVT_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_PRIX_PAF = 1;
    private static final Integer UPDATED_PRIX_PAF = 2;

    private static final Integer DEFAULT_PRIX_UNI = 1;
    private static final Integer UPDATED_PRIX_UNI = 2;

    private static final Integer DEFAULT_QTY_UG = 1;
    private static final Integer UPDATED_QTY_UG = 2;

    private static final Integer DEFAULT_TVA = 1;
    private static final Integer UPDATED_TVA = 2;

    @Autowired
    private StockReportRepository stockReportRepository;

    @Autowired
    private StockReportMapper stockReportMapper;

    @Autowired
    private StockReportService stockReportService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restStockReportMockMvc;

    private StockReport stockReport;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static StockReport createEntity(EntityManager em) {
        StockReport stockReport = new StockReport()
            .qty(DEFAULT_QTY)
            .mvtDate(DEFAULT_MVT_DATE)
            .prixPaf(DEFAULT_PRIX_PAF)
            .prixUni(DEFAULT_PRIX_UNI)
            .qtyUg(DEFAULT_QTY_UG)
            .tva(DEFAULT_TVA);
        return stockReport;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static StockReport createUpdatedEntity(EntityManager em) {
        StockReport stockReport = new StockReport()
            .qty(UPDATED_QTY)
            .mvtDate(UPDATED_MVT_DATE)
            .prixPaf(UPDATED_PRIX_PAF)
            .prixUni(UPDATED_PRIX_UNI)
            .qtyUg(UPDATED_QTY_UG)
            .tva(UPDATED_TVA);
        return stockReport;
    }

    @BeforeEach
    public void initTest() {
        stockReport = createEntity(em);
    }

    @Test
    @Transactional
    public void createStockReport() throws Exception {
        int databaseSizeBeforeCreate = stockReportRepository.findAll().size();
        // Create the StockReport
        StockReportDTO stockReportDTO = stockReportMapper.toDto(stockReport);
        restStockReportMockMvc.perform(post("/api/stock-reports").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(stockReportDTO)))
            .andExpect(status().isCreated());

        // Validate the StockReport in the database
        List<StockReport> stockReportList = stockReportRepository.findAll();
        assertThat(stockReportList).hasSize(databaseSizeBeforeCreate + 1);
        StockReport testStockReport = stockReportList.get(stockReportList.size() - 1);
        assertThat(testStockReport.getQty()).isEqualTo(DEFAULT_QTY);
        assertThat(testStockReport.getMvtDate()).isEqualTo(DEFAULT_MVT_DATE);
        assertThat(testStockReport.getPrixPaf()).isEqualTo(DEFAULT_PRIX_PAF);
        assertThat(testStockReport.getPrixUni()).isEqualTo(DEFAULT_PRIX_UNI);
        assertThat(testStockReport.getQtyUg()).isEqualTo(DEFAULT_QTY_UG);
        assertThat(testStockReport.getTva()).isEqualTo(DEFAULT_TVA);
    }

    @Test
    @Transactional
    public void createStockReportWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = stockReportRepository.findAll().size();

        // Create the StockReport with an existing ID
        stockReport.setId(1L);
        StockReportDTO stockReportDTO = stockReportMapper.toDto(stockReport);

        // An entity with an existing ID cannot be created, so this API call must fail
        restStockReportMockMvc.perform(post("/api/stock-reports").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(stockReportDTO)))
            .andExpect(status().isBadRequest());

        // Validate the StockReport in the database
        List<StockReport> stockReportList = stockReportRepository.findAll();
        assertThat(stockReportList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkQtyIsRequired() throws Exception {
        int databaseSizeBeforeTest = stockReportRepository.findAll().size();
        // set the field null
        stockReport.setQty(null);

        // Create the StockReport, which fails.
        StockReportDTO stockReportDTO = stockReportMapper.toDto(stockReport);


        restStockReportMockMvc.perform(post("/api/stock-reports").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(stockReportDTO)))
            .andExpect(status().isBadRequest());

        List<StockReport> stockReportList = stockReportRepository.findAll();
        assertThat(stockReportList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMvtDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = stockReportRepository.findAll().size();
        // set the field null
        stockReport.setMvtDate(null);

        // Create the StockReport, which fails.
        StockReportDTO stockReportDTO = stockReportMapper.toDto(stockReport);


        restStockReportMockMvc.perform(post("/api/stock-reports").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(stockReportDTO)))
            .andExpect(status().isBadRequest());

        List<StockReport> stockReportList = stockReportRepository.findAll();
        assertThat(stockReportList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPrixPafIsRequired() throws Exception {
        int databaseSizeBeforeTest = stockReportRepository.findAll().size();
        // set the field null
        stockReport.setPrixPaf(null);

        // Create the StockReport, which fails.
        StockReportDTO stockReportDTO = stockReportMapper.toDto(stockReport);


        restStockReportMockMvc.perform(post("/api/stock-reports").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(stockReportDTO)))
            .andExpect(status().isBadRequest());

        List<StockReport> stockReportList = stockReportRepository.findAll();
        assertThat(stockReportList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPrixUniIsRequired() throws Exception {
        int databaseSizeBeforeTest = stockReportRepository.findAll().size();
        // set the field null
        stockReport.setPrixUni(null);

        // Create the StockReport, which fails.
        StockReportDTO stockReportDTO = stockReportMapper.toDto(stockReport);


        restStockReportMockMvc.perform(post("/api/stock-reports").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(stockReportDTO)))
            .andExpect(status().isBadRequest());

        List<StockReport> stockReportList = stockReportRepository.findAll();
        assertThat(stockReportList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQtyUgIsRequired() throws Exception {
        int databaseSizeBeforeTest = stockReportRepository.findAll().size();
        // set the field null
        stockReport.setQtyUg(null);

        // Create the StockReport, which fails.
        StockReportDTO stockReportDTO = stockReportMapper.toDto(stockReport);


        restStockReportMockMvc.perform(post("/api/stock-reports").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(stockReportDTO)))
            .andExpect(status().isBadRequest());

        List<StockReport> stockReportList = stockReportRepository.findAll();
        assertThat(stockReportList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTvaIsRequired() throws Exception {
        int databaseSizeBeforeTest = stockReportRepository.findAll().size();
        // set the field null
        stockReport.setTva(null);

        // Create the StockReport, which fails.
        StockReportDTO stockReportDTO = stockReportMapper.toDto(stockReport);


        restStockReportMockMvc.perform(post("/api/stock-reports").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(stockReportDTO)))
            .andExpect(status().isBadRequest());

        List<StockReport> stockReportList = stockReportRepository.findAll();
        assertThat(stockReportList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllStockReports() throws Exception {
        // Initialize the database
        stockReportRepository.saveAndFlush(stockReport);

        // Get all the stockReportList
        restStockReportMockMvc.perform(get("/api/stock-reports?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(stockReport.getId().intValue())))
            .andExpect(jsonPath("$.[*].qty").value(hasItem(DEFAULT_QTY)))
            .andExpect(jsonPath("$.[*].mvtDate").value(hasItem(DEFAULT_MVT_DATE.toString())))
            .andExpect(jsonPath("$.[*].prixPaf").value(hasItem(DEFAULT_PRIX_PAF)))
            .andExpect(jsonPath("$.[*].prixUni").value(hasItem(DEFAULT_PRIX_UNI)))
            .andExpect(jsonPath("$.[*].qtyUg").value(hasItem(DEFAULT_QTY_UG)))
            .andExpect(jsonPath("$.[*].tva").value(hasItem(DEFAULT_TVA)));
    }
    
    @Test
    @Transactional
    public void getStockReport() throws Exception {
        // Initialize the database
        stockReportRepository.saveAndFlush(stockReport);

        // Get the stockReport
        restStockReportMockMvc.perform(get("/api/stock-reports/{id}", stockReport.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(stockReport.getId().intValue()))
            .andExpect(jsonPath("$.qty").value(DEFAULT_QTY))
            .andExpect(jsonPath("$.mvtDate").value(DEFAULT_MVT_DATE.toString()))
            .andExpect(jsonPath("$.prixPaf").value(DEFAULT_PRIX_PAF))
            .andExpect(jsonPath("$.prixUni").value(DEFAULT_PRIX_UNI))
            .andExpect(jsonPath("$.qtyUg").value(DEFAULT_QTY_UG))
            .andExpect(jsonPath("$.tva").value(DEFAULT_TVA));
    }
    @Test
    @Transactional
    public void getNonExistingStockReport() throws Exception {
        // Get the stockReport
        restStockReportMockMvc.perform(get("/api/stock-reports/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateStockReport() throws Exception {
        // Initialize the database
        stockReportRepository.saveAndFlush(stockReport);

        int databaseSizeBeforeUpdate = stockReportRepository.findAll().size();

        // Update the stockReport
        StockReport updatedStockReport = stockReportRepository.findById(stockReport.getId()).get();
        // Disconnect from session so that the updates on updatedStockReport are not directly saved in db
        em.detach(updatedStockReport);
        updatedStockReport
            .qty(UPDATED_QTY)
            .mvtDate(UPDATED_MVT_DATE)
            .prixPaf(UPDATED_PRIX_PAF)
            .prixUni(UPDATED_PRIX_UNI)
            .qtyUg(UPDATED_QTY_UG)
            .tva(UPDATED_TVA);
        StockReportDTO stockReportDTO = stockReportMapper.toDto(updatedStockReport);

        restStockReportMockMvc.perform(put("/api/stock-reports").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(stockReportDTO)))
            .andExpect(status().isOk());

        // Validate the StockReport in the database
        List<StockReport> stockReportList = stockReportRepository.findAll();
        assertThat(stockReportList).hasSize(databaseSizeBeforeUpdate);
        StockReport testStockReport = stockReportList.get(stockReportList.size() - 1);
        assertThat(testStockReport.getQty()).isEqualTo(UPDATED_QTY);
        assertThat(testStockReport.getMvtDate()).isEqualTo(UPDATED_MVT_DATE);
        assertThat(testStockReport.getPrixPaf()).isEqualTo(UPDATED_PRIX_PAF);
        assertThat(testStockReport.getPrixUni()).isEqualTo(UPDATED_PRIX_UNI);
        assertThat(testStockReport.getQtyUg()).isEqualTo(UPDATED_QTY_UG);
        assertThat(testStockReport.getTva()).isEqualTo(UPDATED_TVA);
    }

    @Test
    @Transactional
    public void updateNonExistingStockReport() throws Exception {
        int databaseSizeBeforeUpdate = stockReportRepository.findAll().size();

        // Create the StockReport
        StockReportDTO stockReportDTO = stockReportMapper.toDto(stockReport);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStockReportMockMvc.perform(put("/api/stock-reports").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(stockReportDTO)))
            .andExpect(status().isBadRequest());

        // Validate the StockReport in the database
        List<StockReport> stockReportList = stockReportRepository.findAll();
        assertThat(stockReportList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteStockReport() throws Exception {
        // Initialize the database
        stockReportRepository.saveAndFlush(stockReport);

        int databaseSizeBeforeDelete = stockReportRepository.findAll().size();

        // Delete the stockReport
        restStockReportMockMvc.perform(delete("/api/stock-reports/{id}", stockReport.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<StockReport> stockReportList = stockReportRepository.findAll();
        assertThat(stockReportList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
