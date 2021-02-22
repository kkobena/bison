package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.NucleusApp;
import com.kobe.nucleus.domain.Stockout;
import com.kobe.nucleus.domain.Produit;
import com.kobe.nucleus.repository.StockoutRepository;
import com.kobe.nucleus.service.StockoutService;
import com.kobe.nucleus.service.dto.StockoutCriteria;


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
 * Integration tests for the {@link StockoutResource} REST controller.
 */
@SpringBootTest(classes = NucleusApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class StockoutResourceIT {

    private static final LocalDate DEFAULT_MVT_DAY = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_MVT_DAY = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_MVT_DAY = LocalDate.ofEpochDay(-1L);

    private static final Integer DEFAULT_QTY = 1;
    private static final Integer UPDATED_QTY = 2;
    private static final Integer SMALLER_QTY = 1 - 1;

    @Autowired
    private StockoutRepository stockoutRepository;

    @Autowired
    private StockoutService stockoutService;



    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restStockoutMockMvc;

    private Stockout stockout;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Stockout createEntity(EntityManager em) {
        Stockout stockout = new Stockout()
            .mvtDay(DEFAULT_MVT_DAY)
            .qty(DEFAULT_QTY);
        // Add required entity
        Produit produit;
        if (TestUtil.findAll(em, Produit.class).isEmpty()) {
            produit = ProduitResourceIT.createEntity(em);
            em.persist(produit);
            em.flush();
        } else {
            produit = TestUtil.findAll(em, Produit.class).get(0);
        }
        stockout.setProduit(produit);
        return stockout;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Stockout createUpdatedEntity(EntityManager em) {
        Stockout stockout = new Stockout()
            .mvtDay(UPDATED_MVT_DAY)
            .qty(UPDATED_QTY);
        // Add required entity
        Produit produit;
        if (TestUtil.findAll(em, Produit.class).isEmpty()) {
            produit = ProduitResourceIT.createUpdatedEntity(em);
            em.persist(produit);
            em.flush();
        } else {
            produit = TestUtil.findAll(em, Produit.class).get(0);
        }
        stockout.setProduit(produit);
        return stockout;
    }

    @BeforeEach
    public void initTest() {
        stockout = createEntity(em);
    }

    @Test
    @Transactional
    public void createStockout() throws Exception {
        int databaseSizeBeforeCreate = stockoutRepository.findAll().size();
        // Create the Stockout
        restStockoutMockMvc.perform(post("/api/stockouts").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(stockout)))
            .andExpect(status().isCreated());

        // Validate the Stockout in the database
        List<Stockout> stockoutList = stockoutRepository.findAll();
        assertThat(stockoutList).hasSize(databaseSizeBeforeCreate + 1);
        Stockout testStockout = stockoutList.get(stockoutList.size() - 1);
        assertThat(testStockout.getMvtDay()).isEqualTo(DEFAULT_MVT_DAY);
        assertThat(testStockout.getQty()).isEqualTo(DEFAULT_QTY);
    }

    @Test
    @Transactional
    public void createStockoutWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = stockoutRepository.findAll().size();

        // Create the Stockout with an existing ID
        stockout.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restStockoutMockMvc.perform(post("/api/stockouts").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(stockout)))
            .andExpect(status().isBadRequest());

        // Validate the Stockout in the database
        List<Stockout> stockoutList = stockoutRepository.findAll();
        assertThat(stockoutList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkMvtDayIsRequired() throws Exception {
        int databaseSizeBeforeTest = stockoutRepository.findAll().size();
        // set the field null
        stockout.setMvtDay(null);

        // Create the Stockout, which fails.


        restStockoutMockMvc.perform(post("/api/stockouts").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(stockout)))
            .andExpect(status().isBadRequest());

        List<Stockout> stockoutList = stockoutRepository.findAll();
        assertThat(stockoutList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQtyIsRequired() throws Exception {
        int databaseSizeBeforeTest = stockoutRepository.findAll().size();
        // set the field null
        stockout.setQty(null);

        // Create the Stockout, which fails.


        restStockoutMockMvc.perform(post("/api/stockouts").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(stockout)))
            .andExpect(status().isBadRequest());

        List<Stockout> stockoutList = stockoutRepository.findAll();
        assertThat(stockoutList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllStockouts() throws Exception {
        // Initialize the database
        stockoutRepository.saveAndFlush(stockout);

        // Get all the stockoutList
        restStockoutMockMvc.perform(get("/api/stockouts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(stockout.getId().intValue())))
            .andExpect(jsonPath("$.[*].mvtDay").value(hasItem(DEFAULT_MVT_DAY.toString())))
            .andExpect(jsonPath("$.[*].qty").value(hasItem(DEFAULT_QTY)));
    }
    
    @Test
    @Transactional
    public void getStockout() throws Exception {
        // Initialize the database
        stockoutRepository.saveAndFlush(stockout);

        // Get the stockout
        restStockoutMockMvc.perform(get("/api/stockouts/{id}", stockout.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(stockout.getId().intValue()))
            .andExpect(jsonPath("$.mvtDay").value(DEFAULT_MVT_DAY.toString()))
            .andExpect(jsonPath("$.qty").value(DEFAULT_QTY));
    }


    @Test
    @Transactional
    public void getStockoutsByIdFiltering() throws Exception {
        // Initialize the database
        stockoutRepository.saveAndFlush(stockout);

        Long id = stockout.getId();

        defaultStockoutShouldBeFound("id.equals=" + id);
        defaultStockoutShouldNotBeFound("id.notEquals=" + id);

        defaultStockoutShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultStockoutShouldNotBeFound("id.greaterThan=" + id);

        defaultStockoutShouldBeFound("id.lessThanOrEqual=" + id);
        defaultStockoutShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllStockoutsByMvtDayIsEqualToSomething() throws Exception {
        // Initialize the database
        stockoutRepository.saveAndFlush(stockout);

        // Get all the stockoutList where mvtDay equals to DEFAULT_MVT_DAY
        defaultStockoutShouldBeFound("mvtDay.equals=" + DEFAULT_MVT_DAY);

        // Get all the stockoutList where mvtDay equals to UPDATED_MVT_DAY
        defaultStockoutShouldNotBeFound("mvtDay.equals=" + UPDATED_MVT_DAY);
    }

    @Test
    @Transactional
    public void getAllStockoutsByMvtDayIsNotEqualToSomething() throws Exception {
        // Initialize the database
        stockoutRepository.saveAndFlush(stockout);

        // Get all the stockoutList where mvtDay not equals to DEFAULT_MVT_DAY
        defaultStockoutShouldNotBeFound("mvtDay.notEquals=" + DEFAULT_MVT_DAY);

        // Get all the stockoutList where mvtDay not equals to UPDATED_MVT_DAY
        defaultStockoutShouldBeFound("mvtDay.notEquals=" + UPDATED_MVT_DAY);
    }

    @Test
    @Transactional
    public void getAllStockoutsByMvtDayIsInShouldWork() throws Exception {
        // Initialize the database
        stockoutRepository.saveAndFlush(stockout);

        // Get all the stockoutList where mvtDay in DEFAULT_MVT_DAY or UPDATED_MVT_DAY
        defaultStockoutShouldBeFound("mvtDay.in=" + DEFAULT_MVT_DAY + "," + UPDATED_MVT_DAY);

        // Get all the stockoutList where mvtDay equals to UPDATED_MVT_DAY
        defaultStockoutShouldNotBeFound("mvtDay.in=" + UPDATED_MVT_DAY);
    }

    @Test
    @Transactional
    public void getAllStockoutsByMvtDayIsNullOrNotNull() throws Exception {
        // Initialize the database
        stockoutRepository.saveAndFlush(stockout);

        // Get all the stockoutList where mvtDay is not null
        defaultStockoutShouldBeFound("mvtDay.specified=true");

        // Get all the stockoutList where mvtDay is null
        defaultStockoutShouldNotBeFound("mvtDay.specified=false");
    }

    @Test
    @Transactional
    public void getAllStockoutsByMvtDayIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        stockoutRepository.saveAndFlush(stockout);

        // Get all the stockoutList where mvtDay is greater than or equal to DEFAULT_MVT_DAY
        defaultStockoutShouldBeFound("mvtDay.greaterThanOrEqual=" + DEFAULT_MVT_DAY);

        // Get all the stockoutList where mvtDay is greater than or equal to UPDATED_MVT_DAY
        defaultStockoutShouldNotBeFound("mvtDay.greaterThanOrEqual=" + UPDATED_MVT_DAY);
    }

    @Test
    @Transactional
    public void getAllStockoutsByMvtDayIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        stockoutRepository.saveAndFlush(stockout);

        // Get all the stockoutList where mvtDay is less than or equal to DEFAULT_MVT_DAY
        defaultStockoutShouldBeFound("mvtDay.lessThanOrEqual=" + DEFAULT_MVT_DAY);

        // Get all the stockoutList where mvtDay is less than or equal to SMALLER_MVT_DAY
        defaultStockoutShouldNotBeFound("mvtDay.lessThanOrEqual=" + SMALLER_MVT_DAY);
    }

    @Test
    @Transactional
    public void getAllStockoutsByMvtDayIsLessThanSomething() throws Exception {
        // Initialize the database
        stockoutRepository.saveAndFlush(stockout);

        // Get all the stockoutList where mvtDay is less than DEFAULT_MVT_DAY
        defaultStockoutShouldNotBeFound("mvtDay.lessThan=" + DEFAULT_MVT_DAY);

        // Get all the stockoutList where mvtDay is less than UPDATED_MVT_DAY
        defaultStockoutShouldBeFound("mvtDay.lessThan=" + UPDATED_MVT_DAY);
    }

    @Test
    @Transactional
    public void getAllStockoutsByMvtDayIsGreaterThanSomething() throws Exception {
        // Initialize the database
        stockoutRepository.saveAndFlush(stockout);

        // Get all the stockoutList where mvtDay is greater than DEFAULT_MVT_DAY
        defaultStockoutShouldNotBeFound("mvtDay.greaterThan=" + DEFAULT_MVT_DAY);

        // Get all the stockoutList where mvtDay is greater than SMALLER_MVT_DAY
        defaultStockoutShouldBeFound("mvtDay.greaterThan=" + SMALLER_MVT_DAY);
    }


    @Test
    @Transactional
    public void getAllStockoutsByQtyIsEqualToSomething() throws Exception {
        // Initialize the database
        stockoutRepository.saveAndFlush(stockout);

        // Get all the stockoutList where qty equals to DEFAULT_QTY
        defaultStockoutShouldBeFound("qty.equals=" + DEFAULT_QTY);

        // Get all the stockoutList where qty equals to UPDATED_QTY
        defaultStockoutShouldNotBeFound("qty.equals=" + UPDATED_QTY);
    }

    @Test
    @Transactional
    public void getAllStockoutsByQtyIsNotEqualToSomething() throws Exception {
        // Initialize the database
        stockoutRepository.saveAndFlush(stockout);

        // Get all the stockoutList where qty not equals to DEFAULT_QTY
        defaultStockoutShouldNotBeFound("qty.notEquals=" + DEFAULT_QTY);

        // Get all the stockoutList where qty not equals to UPDATED_QTY
        defaultStockoutShouldBeFound("qty.notEquals=" + UPDATED_QTY);
    }

    @Test
    @Transactional
    public void getAllStockoutsByQtyIsInShouldWork() throws Exception {
        // Initialize the database
        stockoutRepository.saveAndFlush(stockout);

        // Get all the stockoutList where qty in DEFAULT_QTY or UPDATED_QTY
        defaultStockoutShouldBeFound("qty.in=" + DEFAULT_QTY + "," + UPDATED_QTY);

        // Get all the stockoutList where qty equals to UPDATED_QTY
        defaultStockoutShouldNotBeFound("qty.in=" + UPDATED_QTY);
    }

    @Test
    @Transactional
    public void getAllStockoutsByQtyIsNullOrNotNull() throws Exception {
        // Initialize the database
        stockoutRepository.saveAndFlush(stockout);

        // Get all the stockoutList where qty is not null
        defaultStockoutShouldBeFound("qty.specified=true");

        // Get all the stockoutList where qty is null
        defaultStockoutShouldNotBeFound("qty.specified=false");
    }

    @Test
    @Transactional
    public void getAllStockoutsByQtyIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        stockoutRepository.saveAndFlush(stockout);

        // Get all the stockoutList where qty is greater than or equal to DEFAULT_QTY
        defaultStockoutShouldBeFound("qty.greaterThanOrEqual=" + DEFAULT_QTY);

        // Get all the stockoutList where qty is greater than or equal to UPDATED_QTY
        defaultStockoutShouldNotBeFound("qty.greaterThanOrEqual=" + UPDATED_QTY);
    }

    @Test
    @Transactional
    public void getAllStockoutsByQtyIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        stockoutRepository.saveAndFlush(stockout);

        // Get all the stockoutList where qty is less than or equal to DEFAULT_QTY
        defaultStockoutShouldBeFound("qty.lessThanOrEqual=" + DEFAULT_QTY);

        // Get all the stockoutList where qty is less than or equal to SMALLER_QTY
        defaultStockoutShouldNotBeFound("qty.lessThanOrEqual=" + SMALLER_QTY);
    }

    @Test
    @Transactional
    public void getAllStockoutsByQtyIsLessThanSomething() throws Exception {
        // Initialize the database
        stockoutRepository.saveAndFlush(stockout);

        // Get all the stockoutList where qty is less than DEFAULT_QTY
        defaultStockoutShouldNotBeFound("qty.lessThan=" + DEFAULT_QTY);

        // Get all the stockoutList where qty is less than UPDATED_QTY
        defaultStockoutShouldBeFound("qty.lessThan=" + UPDATED_QTY);
    }

    @Test
    @Transactional
    public void getAllStockoutsByQtyIsGreaterThanSomething() throws Exception {
        // Initialize the database
        stockoutRepository.saveAndFlush(stockout);

        // Get all the stockoutList where qty is greater than DEFAULT_QTY
        defaultStockoutShouldNotBeFound("qty.greaterThan=" + DEFAULT_QTY);

        // Get all the stockoutList where qty is greater than SMALLER_QTY
        defaultStockoutShouldBeFound("qty.greaterThan=" + SMALLER_QTY);
    }


    @Test
    @Transactional
    public void getAllStockoutsByProduitIsEqualToSomething() throws Exception {
        // Get already existing entity
        Produit produit = stockout.getProduit();
        stockoutRepository.saveAndFlush(stockout);
        Long produitId = produit.getId();

        // Get all the stockoutList where produit equals to produitId
        defaultStockoutShouldBeFound("produitId.equals=" + produitId);

        // Get all the stockoutList where produit equals to produitId + 1
        defaultStockoutShouldNotBeFound("produitId.equals=" + (produitId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultStockoutShouldBeFound(String filter) throws Exception {
        restStockoutMockMvc.perform(get("/api/stockouts?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(stockout.getId().intValue())))
            .andExpect(jsonPath("$.[*].mvtDay").value(hasItem(DEFAULT_MVT_DAY.toString())))
            .andExpect(jsonPath("$.[*].qty").value(hasItem(DEFAULT_QTY)));

        // Check, that the count call also returns 1
        restStockoutMockMvc.perform(get("/api/stockouts/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultStockoutShouldNotBeFound(String filter) throws Exception {
        restStockoutMockMvc.perform(get("/api/stockouts?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restStockoutMockMvc.perform(get("/api/stockouts/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingStockout() throws Exception {
        // Get the stockout
        restStockoutMockMvc.perform(get("/api/stockouts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateStockout() throws Exception {
        // Initialize the database
        stockoutService.save(stockout);

        int databaseSizeBeforeUpdate = stockoutRepository.findAll().size();

        // Update the stockout
        Stockout updatedStockout = stockoutRepository.findById(stockout.getId()).get();
        // Disconnect from session so that the updates on updatedStockout are not directly saved in db
        em.detach(updatedStockout);
        updatedStockout
            .mvtDay(UPDATED_MVT_DAY)
            .qty(UPDATED_QTY);

        restStockoutMockMvc.perform(put("/api/stockouts").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedStockout)))
            .andExpect(status().isOk());

        // Validate the Stockout in the database
        List<Stockout> stockoutList = stockoutRepository.findAll();
        assertThat(stockoutList).hasSize(databaseSizeBeforeUpdate);
        Stockout testStockout = stockoutList.get(stockoutList.size() - 1);
        assertThat(testStockout.getMvtDay()).isEqualTo(UPDATED_MVT_DAY);
        assertThat(testStockout.getQty()).isEqualTo(UPDATED_QTY);
    }

    @Test
    @Transactional
    public void updateNonExistingStockout() throws Exception {
        int databaseSizeBeforeUpdate = stockoutRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStockoutMockMvc.perform(put("/api/stockouts").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(stockout)))
            .andExpect(status().isBadRequest());

        // Validate the Stockout in the database
        List<Stockout> stockoutList = stockoutRepository.findAll();
        assertThat(stockoutList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteStockout() throws Exception {
        // Initialize the database
        stockoutService.save(stockout);

        int databaseSizeBeforeDelete = stockoutRepository.findAll().size();

        // Delete the stockout
        restStockoutMockMvc.perform(delete("/api/stockouts/{id}", stockout.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Stockout> stockoutList = stockoutRepository.findAll();
        assertThat(stockoutList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
