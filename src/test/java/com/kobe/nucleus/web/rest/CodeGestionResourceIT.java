package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.NucleusApp;
import com.kobe.nucleus.domain.CodeGestion;
import com.kobe.nucleus.repository.CodeGestionRepository;
import com.kobe.nucleus.service.CodeGestionService;
import com.kobe.nucleus.service.dto.CodeGestionDTO;
import com.kobe.nucleus.service.mapper.CodeGestionMapper;

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

import com.kobe.nucleus.domain.enumeration.Optimisation;
/**
 * Integration tests for the {@link CodeGestionResource} REST controller.
 */
@SpringBootTest(classes = NucleusApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CodeGestionResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final Integer DEFAULT_JOURCOUVERTURE = 1;
    private static final Integer UPDATED_JOURCOUVERTURE = 2;

    private static final Integer DEFAULT_MOISHISTORIQUE = 1;
    private static final Integer UPDATED_MOISHISTORIQUE = 2;

    private static final Integer DEFAULT_DATEBUTOIR = 1;
    private static final Integer UPDATED_DATEBUTOIR = 2;

    private static final Integer DEFAULT_DATEEXTRAPOLATION = 1;
    private static final Integer UPDATED_DATEEXTRAPOLATION = 2;

    private static final Boolean DEFAULT_SEUIL_CMDE = false;
    private static final Boolean UPDATED_SEUIL_CMDE = true;

    private static final Boolean DEFAULT_COFFICIENTPMD = false;
    private static final Boolean UPDATED_COFFICIENTPMD = true;

    private static final Optimisation DEFAULT_TYPE_OPTIMISATION = Optimisation.MENSUEL;
    private static final Optimisation UPDATED_TYPE_OPTIMISATION = Optimisation.PONDERATION;

    @Autowired
    private CodeGestionRepository codeGestionRepository;

    @Autowired
    private CodeGestionMapper codeGestionMapper;

    @Autowired
    private CodeGestionService codeGestionService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCodeGestionMockMvc;

    private CodeGestion codeGestion;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CodeGestion createEntity(EntityManager em) {
        CodeGestion codeGestion = new CodeGestion()
            .code(DEFAULT_CODE)
            .jourcouverture(DEFAULT_JOURCOUVERTURE)
            .moishistorique(DEFAULT_MOISHISTORIQUE)
            .datebutoir(DEFAULT_DATEBUTOIR)
            .dateextrapolation(DEFAULT_DATEEXTRAPOLATION)
            .seuilCmde(DEFAULT_SEUIL_CMDE)
            .cofficientpmd(DEFAULT_COFFICIENTPMD)
            .typeOptimisation(DEFAULT_TYPE_OPTIMISATION);
        return codeGestion;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CodeGestion createUpdatedEntity(EntityManager em) {
        CodeGestion codeGestion = new CodeGestion()
            .code(UPDATED_CODE)
            .jourcouverture(UPDATED_JOURCOUVERTURE)
            .moishistorique(UPDATED_MOISHISTORIQUE)
            .datebutoir(UPDATED_DATEBUTOIR)
            .dateextrapolation(UPDATED_DATEEXTRAPOLATION)
            .seuilCmde(UPDATED_SEUIL_CMDE)
            .cofficientpmd(UPDATED_COFFICIENTPMD)
            .typeOptimisation(UPDATED_TYPE_OPTIMISATION);
        return codeGestion;
    }

    @BeforeEach
    public void initTest() {
        codeGestion = createEntity(em);
    }

    @Test
    @Transactional
    public void createCodeGestion() throws Exception {
        int databaseSizeBeforeCreate = codeGestionRepository.findAll().size();
        // Create the CodeGestion
        CodeGestionDTO codeGestionDTO = codeGestionMapper.toDto(codeGestion);
        restCodeGestionMockMvc.perform(post("/api/code-gestions").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(codeGestionDTO)))
            .andExpect(status().isCreated());

        // Validate the CodeGestion in the database
        List<CodeGestion> codeGestionList = codeGestionRepository.findAll();
        assertThat(codeGestionList).hasSize(databaseSizeBeforeCreate + 1);
        CodeGestion testCodeGestion = codeGestionList.get(codeGestionList.size() - 1);
        assertThat(testCodeGestion.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testCodeGestion.getJourcouverture()).isEqualTo(DEFAULT_JOURCOUVERTURE);
        assertThat(testCodeGestion.getMoishistorique()).isEqualTo(DEFAULT_MOISHISTORIQUE);
        assertThat(testCodeGestion.getDatebutoir()).isEqualTo(DEFAULT_DATEBUTOIR);
        assertThat(testCodeGestion.getDateextrapolation()).isEqualTo(DEFAULT_DATEEXTRAPOLATION);
        assertThat(testCodeGestion.isSeuilCmde()).isEqualTo(DEFAULT_SEUIL_CMDE);
        assertThat(testCodeGestion.isCofficientpmd()).isEqualTo(DEFAULT_COFFICIENTPMD);
        assertThat(testCodeGestion.getTypeOptimisation()).isEqualTo(DEFAULT_TYPE_OPTIMISATION);
    }

    @Test
    @Transactional
    public void createCodeGestionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = codeGestionRepository.findAll().size();

        // Create the CodeGestion with an existing ID
        codeGestion.setId(1L);
        CodeGestionDTO codeGestionDTO = codeGestionMapper.toDto(codeGestion);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCodeGestionMockMvc.perform(post("/api/code-gestions").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(codeGestionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CodeGestion in the database
        List<CodeGestion> codeGestionList = codeGestionRepository.findAll();
        assertThat(codeGestionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = codeGestionRepository.findAll().size();
        // set the field null
        codeGestion.setCode(null);

        // Create the CodeGestion, which fails.
        CodeGestionDTO codeGestionDTO = codeGestionMapper.toDto(codeGestion);


        restCodeGestionMockMvc.perform(post("/api/code-gestions").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(codeGestionDTO)))
            .andExpect(status().isBadRequest());

        List<CodeGestion> codeGestionList = codeGestionRepository.findAll();
        assertThat(codeGestionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkJourcouvertureIsRequired() throws Exception {
        int databaseSizeBeforeTest = codeGestionRepository.findAll().size();
        // set the field null
        codeGestion.setJourcouverture(null);

        // Create the CodeGestion, which fails.
        CodeGestionDTO codeGestionDTO = codeGestionMapper.toDto(codeGestion);


        restCodeGestionMockMvc.perform(post("/api/code-gestions").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(codeGestionDTO)))
            .andExpect(status().isBadRequest());

        List<CodeGestion> codeGestionList = codeGestionRepository.findAll();
        assertThat(codeGestionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMoishistoriqueIsRequired() throws Exception {
        int databaseSizeBeforeTest = codeGestionRepository.findAll().size();
        // set the field null
        codeGestion.setMoishistorique(null);

        // Create the CodeGestion, which fails.
        CodeGestionDTO codeGestionDTO = codeGestionMapper.toDto(codeGestion);


        restCodeGestionMockMvc.perform(post("/api/code-gestions").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(codeGestionDTO)))
            .andExpect(status().isBadRequest());

        List<CodeGestion> codeGestionList = codeGestionRepository.findAll();
        assertThat(codeGestionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDatebutoirIsRequired() throws Exception {
        int databaseSizeBeforeTest = codeGestionRepository.findAll().size();
        // set the field null
        codeGestion.setDatebutoir(null);

        // Create the CodeGestion, which fails.
        CodeGestionDTO codeGestionDTO = codeGestionMapper.toDto(codeGestion);


        restCodeGestionMockMvc.perform(post("/api/code-gestions").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(codeGestionDTO)))
            .andExpect(status().isBadRequest());

        List<CodeGestion> codeGestionList = codeGestionRepository.findAll();
        assertThat(codeGestionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateextrapolationIsRequired() throws Exception {
        int databaseSizeBeforeTest = codeGestionRepository.findAll().size();
        // set the field null
        codeGestion.setDateextrapolation(null);

        // Create the CodeGestion, which fails.
        CodeGestionDTO codeGestionDTO = codeGestionMapper.toDto(codeGestion);


        restCodeGestionMockMvc.perform(post("/api/code-gestions").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(codeGestionDTO)))
            .andExpect(status().isBadRequest());

        List<CodeGestion> codeGestionList = codeGestionRepository.findAll();
        assertThat(codeGestionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSeuilCmdeIsRequired() throws Exception {
        int databaseSizeBeforeTest = codeGestionRepository.findAll().size();
        // set the field null
        codeGestion.setSeuilCmde(null);

        // Create the CodeGestion, which fails.
        CodeGestionDTO codeGestionDTO = codeGestionMapper.toDto(codeGestion);


        restCodeGestionMockMvc.perform(post("/api/code-gestions").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(codeGestionDTO)))
            .andExpect(status().isBadRequest());

        List<CodeGestion> codeGestionList = codeGestionRepository.findAll();
        assertThat(codeGestionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCofficientpmdIsRequired() throws Exception {
        int databaseSizeBeforeTest = codeGestionRepository.findAll().size();
        // set the field null
        codeGestion.setCofficientpmd(null);

        // Create the CodeGestion, which fails.
        CodeGestionDTO codeGestionDTO = codeGestionMapper.toDto(codeGestion);


        restCodeGestionMockMvc.perform(post("/api/code-gestions").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(codeGestionDTO)))
            .andExpect(status().isBadRequest());

        List<CodeGestion> codeGestionList = codeGestionRepository.findAll();
        assertThat(codeGestionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCodeGestions() throws Exception {
        // Initialize the database
        codeGestionRepository.saveAndFlush(codeGestion);

        // Get all the codeGestionList
        restCodeGestionMockMvc.perform(get("/api/code-gestions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(codeGestion.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].jourcouverture").value(hasItem(DEFAULT_JOURCOUVERTURE)))
            .andExpect(jsonPath("$.[*].moishistorique").value(hasItem(DEFAULT_MOISHISTORIQUE)))
            .andExpect(jsonPath("$.[*].datebutoir").value(hasItem(DEFAULT_DATEBUTOIR)))
            .andExpect(jsonPath("$.[*].dateextrapolation").value(hasItem(DEFAULT_DATEEXTRAPOLATION)))
            .andExpect(jsonPath("$.[*].seuilCmde").value(hasItem(DEFAULT_SEUIL_CMDE.booleanValue())))
            .andExpect(jsonPath("$.[*].cofficientpmd").value(hasItem(DEFAULT_COFFICIENTPMD.booleanValue())))
            .andExpect(jsonPath("$.[*].typeOptimisation").value(hasItem(DEFAULT_TYPE_OPTIMISATION.toString())));
    }
    
    @Test
    @Transactional
    public void getCodeGestion() throws Exception {
        // Initialize the database
        codeGestionRepository.saveAndFlush(codeGestion);

        // Get the codeGestion
        restCodeGestionMockMvc.perform(get("/api/code-gestions/{id}", codeGestion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(codeGestion.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.jourcouverture").value(DEFAULT_JOURCOUVERTURE))
            .andExpect(jsonPath("$.moishistorique").value(DEFAULT_MOISHISTORIQUE))
            .andExpect(jsonPath("$.datebutoir").value(DEFAULT_DATEBUTOIR))
            .andExpect(jsonPath("$.dateextrapolation").value(DEFAULT_DATEEXTRAPOLATION))
            .andExpect(jsonPath("$.seuilCmde").value(DEFAULT_SEUIL_CMDE.booleanValue()))
            .andExpect(jsonPath("$.cofficientpmd").value(DEFAULT_COFFICIENTPMD.booleanValue()))
            .andExpect(jsonPath("$.typeOptimisation").value(DEFAULT_TYPE_OPTIMISATION.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingCodeGestion() throws Exception {
        // Get the codeGestion
        restCodeGestionMockMvc.perform(get("/api/code-gestions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCodeGestion() throws Exception {
        // Initialize the database
        codeGestionRepository.saveAndFlush(codeGestion);

        int databaseSizeBeforeUpdate = codeGestionRepository.findAll().size();

        // Update the codeGestion
        CodeGestion updatedCodeGestion = codeGestionRepository.findById(codeGestion.getId()).get();
        // Disconnect from session so that the updates on updatedCodeGestion are not directly saved in db
        em.detach(updatedCodeGestion);
        updatedCodeGestion
            .code(UPDATED_CODE)
            .jourcouverture(UPDATED_JOURCOUVERTURE)
            .moishistorique(UPDATED_MOISHISTORIQUE)
            .datebutoir(UPDATED_DATEBUTOIR)
            .dateextrapolation(UPDATED_DATEEXTRAPOLATION)
            .seuilCmde(UPDATED_SEUIL_CMDE)
            .cofficientpmd(UPDATED_COFFICIENTPMD)
            .typeOptimisation(UPDATED_TYPE_OPTIMISATION);
        CodeGestionDTO codeGestionDTO = codeGestionMapper.toDto(updatedCodeGestion);

        restCodeGestionMockMvc.perform(put("/api/code-gestions").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(codeGestionDTO)))
            .andExpect(status().isOk());

        // Validate the CodeGestion in the database
        List<CodeGestion> codeGestionList = codeGestionRepository.findAll();
        assertThat(codeGestionList).hasSize(databaseSizeBeforeUpdate);
        CodeGestion testCodeGestion = codeGestionList.get(codeGestionList.size() - 1);
        assertThat(testCodeGestion.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testCodeGestion.getJourcouverture()).isEqualTo(UPDATED_JOURCOUVERTURE);
        assertThat(testCodeGestion.getMoishistorique()).isEqualTo(UPDATED_MOISHISTORIQUE);
        assertThat(testCodeGestion.getDatebutoir()).isEqualTo(UPDATED_DATEBUTOIR);
        assertThat(testCodeGestion.getDateextrapolation()).isEqualTo(UPDATED_DATEEXTRAPOLATION);
        assertThat(testCodeGestion.isSeuilCmde()).isEqualTo(UPDATED_SEUIL_CMDE);
        assertThat(testCodeGestion.isCofficientpmd()).isEqualTo(UPDATED_COFFICIENTPMD);
        assertThat(testCodeGestion.getTypeOptimisation()).isEqualTo(UPDATED_TYPE_OPTIMISATION);
    }

    @Test
    @Transactional
    public void updateNonExistingCodeGestion() throws Exception {
        int databaseSizeBeforeUpdate = codeGestionRepository.findAll().size();

        // Create the CodeGestion
        CodeGestionDTO codeGestionDTO = codeGestionMapper.toDto(codeGestion);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCodeGestionMockMvc.perform(put("/api/code-gestions").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(codeGestionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CodeGestion in the database
        List<CodeGestion> codeGestionList = codeGestionRepository.findAll();
        assertThat(codeGestionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCodeGestion() throws Exception {
        // Initialize the database
        codeGestionRepository.saveAndFlush(codeGestion);

        int databaseSizeBeforeDelete = codeGestionRepository.findAll().size();

        // Delete the codeGestion
        restCodeGestionMockMvc.perform(delete("/api/code-gestions/{id}", codeGestion.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CodeGestion> codeGestionList = codeGestionRepository.findAll();
        assertThat(codeGestionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
