package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.NucleusApp;
import com.kobe.nucleus.domain.StockProduit;
import com.kobe.nucleus.domain.Rayon;
import com.kobe.nucleus.domain.Produit;
import com.kobe.nucleus.repository.StockProduitRepository;
import com.kobe.nucleus.service.StockProduitService;
import com.kobe.nucleus.service.dto.StockProduitDTO;
import com.kobe.nucleus.service.mapper.StockProduitMapper;

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
 * Integration tests for the {@link StockProduitResource} REST controller.
 */
@SpringBootTest(classes = NucleusApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class StockProduitResourceIT {

    private static final Integer DEFAULT_QTY_STOCK = 1;
    private static final Integer UPDATED_QTY_STOCK = 2;

    private static final Integer DEFAULT_QTY_VIRTUAL = 1;
    private static final Integer UPDATED_QTY_VIRTUAL = 2;

    private static final Integer DEFAULT_QTY_UG = 1;
    private static final Integer UPDATED_QTY_UG = 2;

    @Autowired
    private StockProduitRepository stockProduitRepository;

    @Autowired
    private StockProduitMapper stockProduitMapper;

    @Autowired
    private StockProduitService stockProduitService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restStockProduitMockMvc;

    private StockProduit stockProduit;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static StockProduit createEntity(EntityManager em) {
        StockProduit stockProduit = new StockProduit()
            .qtyStock(DEFAULT_QTY_STOCK)
            .qtyVirtual(DEFAULT_QTY_VIRTUAL)
            .qtyUG(DEFAULT_QTY_UG);
        // Add required entity
        Rayon rayon;
        if (TestUtil.findAll(em, Rayon.class).isEmpty()) {
            rayon = RayonResourceIT.createEntity(em);
            em.persist(rayon);
            em.flush();
        } else {
            rayon = TestUtil.findAll(em, Rayon.class).get(0);
        }
        stockProduit.setRayon(rayon);
        // Add required entity
        Produit produit;
        if (TestUtil.findAll(em, Produit.class).isEmpty()) {
            produit = ProduitResourceIT.createEntity(em);
            em.persist(produit);
            em.flush();
        } else {
            produit = TestUtil.findAll(em, Produit.class).get(0);
        }
        stockProduit.setProduit(produit);
        return stockProduit;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static StockProduit createUpdatedEntity(EntityManager em) {
        StockProduit stockProduit = new StockProduit()
            .qtyStock(UPDATED_QTY_STOCK)
            .qtyVirtual(UPDATED_QTY_VIRTUAL)
            .qtyUG(UPDATED_QTY_UG);
        // Add required entity
        Rayon rayon;
        if (TestUtil.findAll(em, Rayon.class).isEmpty()) {
            rayon = RayonResourceIT.createUpdatedEntity(em);
            em.persist(rayon);
            em.flush();
        } else {
            rayon = TestUtil.findAll(em, Rayon.class).get(0);
        }
        stockProduit.setRayon(rayon);
        // Add required entity
        Produit produit;
        if (TestUtil.findAll(em, Produit.class).isEmpty()) {
            produit = ProduitResourceIT.createUpdatedEntity(em);
            em.persist(produit);
            em.flush();
        } else {
            produit = TestUtil.findAll(em, Produit.class).get(0);
        }
        stockProduit.setProduit(produit);
        return stockProduit;
    }

    @BeforeEach
    public void initTest() {
        stockProduit = createEntity(em);
    }

    @Test
    @Transactional
    public void createStockProduit() throws Exception {
        int databaseSizeBeforeCreate = stockProduitRepository.findAll().size();
        // Create the StockProduit
        StockProduitDTO stockProduitDTO = stockProduitMapper.toDto(stockProduit);
        restStockProduitMockMvc.perform(post("/api/stock-produits").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(stockProduitDTO)))
            .andExpect(status().isCreated());

        // Validate the StockProduit in the database
        List<StockProduit> stockProduitList = stockProduitRepository.findAll();
        assertThat(stockProduitList).hasSize(databaseSizeBeforeCreate + 1);
        StockProduit testStockProduit = stockProduitList.get(stockProduitList.size() - 1);
        assertThat(testStockProduit.getQtyStock()).isEqualTo(DEFAULT_QTY_STOCK);
        assertThat(testStockProduit.getQtyVirtual()).isEqualTo(DEFAULT_QTY_VIRTUAL);
        assertThat(testStockProduit.getQtyUG()).isEqualTo(DEFAULT_QTY_UG);
    }

    @Test
    @Transactional
    public void createStockProduitWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = stockProduitRepository.findAll().size();

        // Create the StockProduit with an existing ID
        stockProduit.setId(1L);
        StockProduitDTO stockProduitDTO = stockProduitMapper.toDto(stockProduit);

        // An entity with an existing ID cannot be created, so this API call must fail
        restStockProduitMockMvc.perform(post("/api/stock-produits").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(stockProduitDTO)))
            .andExpect(status().isBadRequest());

        // Validate the StockProduit in the database
        List<StockProduit> stockProduitList = stockProduitRepository.findAll();
        assertThat(stockProduitList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkQtyStockIsRequired() throws Exception {
        int databaseSizeBeforeTest = stockProduitRepository.findAll().size();
        // set the field null
        stockProduit.setQtyStock(null);

        // Create the StockProduit, which fails.
        StockProduitDTO stockProduitDTO = stockProduitMapper.toDto(stockProduit);


        restStockProduitMockMvc.perform(post("/api/stock-produits").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(stockProduitDTO)))
            .andExpect(status().isBadRequest());

        List<StockProduit> stockProduitList = stockProduitRepository.findAll();
        assertThat(stockProduitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQtyVirtualIsRequired() throws Exception {
        int databaseSizeBeforeTest = stockProduitRepository.findAll().size();
        // set the field null
        stockProduit.setQtyVirtual(null);

        // Create the StockProduit, which fails.
        StockProduitDTO stockProduitDTO = stockProduitMapper.toDto(stockProduit);


        restStockProduitMockMvc.perform(post("/api/stock-produits").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(stockProduitDTO)))
            .andExpect(status().isBadRequest());

        List<StockProduit> stockProduitList = stockProduitRepository.findAll();
        assertThat(stockProduitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQtyUGIsRequired() throws Exception {
        int databaseSizeBeforeTest = stockProduitRepository.findAll().size();
        // set the field null
        stockProduit.setQtyUG(null);

        // Create the StockProduit, which fails.
        StockProduitDTO stockProduitDTO = stockProduitMapper.toDto(stockProduit);


        restStockProduitMockMvc.perform(post("/api/stock-produits").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(stockProduitDTO)))
            .andExpect(status().isBadRequest());

        List<StockProduit> stockProduitList = stockProduitRepository.findAll();
        assertThat(stockProduitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllStockProduits() throws Exception {
        // Initialize the database
        stockProduitRepository.saveAndFlush(stockProduit);

        // Get all the stockProduitList
        restStockProduitMockMvc.perform(get("/api/stock-produits?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(stockProduit.getId().intValue())))
            .andExpect(jsonPath("$.[*].qtyStock").value(hasItem(DEFAULT_QTY_STOCK)))
            .andExpect(jsonPath("$.[*].qtyVirtual").value(hasItem(DEFAULT_QTY_VIRTUAL)))
            .andExpect(jsonPath("$.[*].qtyUG").value(hasItem(DEFAULT_QTY_UG)));
    }
    
    @Test
    @Transactional
    public void getStockProduit() throws Exception {
        // Initialize the database
        stockProduitRepository.saveAndFlush(stockProduit);

        // Get the stockProduit
        restStockProduitMockMvc.perform(get("/api/stock-produits/{id}", stockProduit.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(stockProduit.getId().intValue()))
            .andExpect(jsonPath("$.qtyStock").value(DEFAULT_QTY_STOCK))
            .andExpect(jsonPath("$.qtyVirtual").value(DEFAULT_QTY_VIRTUAL))
            .andExpect(jsonPath("$.qtyUG").value(DEFAULT_QTY_UG));
    }
    @Test
    @Transactional
    public void getNonExistingStockProduit() throws Exception {
        // Get the stockProduit
        restStockProduitMockMvc.perform(get("/api/stock-produits/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateStockProduit() throws Exception {
        // Initialize the database
        stockProduitRepository.saveAndFlush(stockProduit);

        int databaseSizeBeforeUpdate = stockProduitRepository.findAll().size();

        // Update the stockProduit
        StockProduit updatedStockProduit = stockProduitRepository.findById(stockProduit.getId()).get();
        // Disconnect from session so that the updates on updatedStockProduit are not directly saved in db
        em.detach(updatedStockProduit);
        updatedStockProduit
            .qtyStock(UPDATED_QTY_STOCK)
            .qtyVirtual(UPDATED_QTY_VIRTUAL)
            .qtyUG(UPDATED_QTY_UG);
        StockProduitDTO stockProduitDTO = stockProduitMapper.toDto(updatedStockProduit);

        restStockProduitMockMvc.perform(put("/api/stock-produits").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(stockProduitDTO)))
            .andExpect(status().isOk());

        // Validate the StockProduit in the database
        List<StockProduit> stockProduitList = stockProduitRepository.findAll();
        assertThat(stockProduitList).hasSize(databaseSizeBeforeUpdate);
        StockProduit testStockProduit = stockProduitList.get(stockProduitList.size() - 1);
        assertThat(testStockProduit.getQtyStock()).isEqualTo(UPDATED_QTY_STOCK);
        assertThat(testStockProduit.getQtyVirtual()).isEqualTo(UPDATED_QTY_VIRTUAL);
        assertThat(testStockProduit.getQtyUG()).isEqualTo(UPDATED_QTY_UG);
    }

    @Test
    @Transactional
    public void updateNonExistingStockProduit() throws Exception {
        int databaseSizeBeforeUpdate = stockProduitRepository.findAll().size();

        // Create the StockProduit
        StockProduitDTO stockProduitDTO = stockProduitMapper.toDto(stockProduit);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStockProduitMockMvc.perform(put("/api/stock-produits").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(stockProduitDTO)))
            .andExpect(status().isBadRequest());

        // Validate the StockProduit in the database
        List<StockProduit> stockProduitList = stockProduitRepository.findAll();
        assertThat(stockProduitList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteStockProduit() throws Exception {
        // Initialize the database
        stockProduitRepository.saveAndFlush(stockProduit);

        int databaseSizeBeforeDelete = stockProduitRepository.findAll().size();

        // Delete the stockProduit
        restStockProduitMockMvc.perform(delete("/api/stock-produits/{id}", stockProduit.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<StockProduit> stockProduitList = stockProduitRepository.findAll();
        assertThat(stockProduitList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
