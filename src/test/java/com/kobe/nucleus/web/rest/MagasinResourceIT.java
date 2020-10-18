package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.NucleusApp;
import com.kobe.nucleus.domain.Magasin;
import com.kobe.nucleus.repository.MagasinRepository;
import com.kobe.nucleus.service.MagasinService;
import com.kobe.nucleus.service.dto.MagasinDTO;
import com.kobe.nucleus.service.mapper.MagasinMapper;

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

import com.kobe.nucleus.domain.enumeration.TypeMagasin;
import com.kobe.nucleus.domain.enumeration.Status;
/**
 * Integration tests for the {@link MagasinResource} REST controller.
 */
@SpringBootTest(classes = NucleusApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class MagasinResourceIT {

    private static final TypeMagasin DEFAULT_TYPE_MAGASIN = TypeMagasin.PRINCIPAL;
    private static final TypeMagasin UPDATED_TYPE_MAGASIN = TypeMagasin.RAYON;

    private static final String DEFAULT_NOM_COURT = "AAAAAAAAAA";
    private static final String UPDATED_NOM_COURT = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_LONG = "AAAAAAAAAA";
    private static final String UPDATED_NOM_LONG = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESSE_POSTAL = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESSE_POSTAL = "BBBBBBBBBB";

    private static final Status DEFAULT_STATUS = Status.ACTIVE;
    private static final Status UPDATED_STATUS = Status.ENATTENTE;

    private static final String DEFAULT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_MOBILE = "AAAAAAAAAA";
    private static final String UPDATED_MOBILE = "BBBBBBBBBB";

    private static final String DEFAULT_COMMENTAIRE = "AAAAAAAAAA";
    private static final String UPDATED_COMMENTAIRE = "BBBBBBBBBB";

    private static final String DEFAULT_AUTRE_COMMENTAIRE = "AAAAAAAAAA";
    private static final String UPDATED_AUTRE_COMMENTAIRE = "BBBBBBBBBB";

    private static final String DEFAULT_ENTETE = "AAAAAAAAAA";
    private static final String UPDATED_ENTETE = "BBBBBBBBBB";

    private static final String DEFAULT_COMPTE_CONTRIBUABLE = "AAAAAAAAAA";
    private static final String UPDATED_COMPTE_CONTRIBUABLE = "BBBBBBBBBB";

    private static final String DEFAULT_REGISTRE_COMMERCE = "AAAAAAAAAA";
    private static final String UPDATED_REGISTRE_COMMERCE = "BBBBBBBBBB";

    private static final String DEFAULT_REGISTRE_IMPOSITION = "AAAAAAAAAA";
    private static final String UPDATED_REGISTRE_IMPOSITION = "BBBBBBBBBB";

    private static final String DEFAULT_CENTRE_IMPOSITION = "AAAAAAAAAA";
    private static final String UPDATED_CENTRE_IMPOSITION = "BBBBBBBBBB";

    private static final String DEFAULT_NUM_COMPTABLE = "AAAAAAAAAA";
    private static final String UPDATED_NUM_COMPTABLE = "BBBBBBBBBB";

    @Autowired
    private MagasinRepository magasinRepository;

    @Autowired
    private MagasinMapper magasinMapper;

    @Autowired
    private MagasinService magasinService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMagasinMockMvc;

    private Magasin magasin;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Magasin createEntity(EntityManager em) {
        Magasin magasin = new Magasin()
            .typeMagasin(DEFAULT_TYPE_MAGASIN)
            .nomCourt(DEFAULT_NOM_COURT)
            .nomLong(DEFAULT_NOM_LONG)
            .addressePostal(DEFAULT_ADDRESSE_POSTAL)
            .status(DEFAULT_STATUS)
            .phone(DEFAULT_PHONE)
            .mobile(DEFAULT_MOBILE)
            .commentaire(DEFAULT_COMMENTAIRE)
            .autreCommentaire(DEFAULT_AUTRE_COMMENTAIRE)
            .entete(DEFAULT_ENTETE)
            .compteContribuable(DEFAULT_COMPTE_CONTRIBUABLE)
            .registreCommerce(DEFAULT_REGISTRE_COMMERCE)
            .registreImposition(DEFAULT_REGISTRE_IMPOSITION)
            .centreImposition(DEFAULT_CENTRE_IMPOSITION)
            .numComptable(DEFAULT_NUM_COMPTABLE);
        return magasin;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Magasin createUpdatedEntity(EntityManager em) {
        Magasin magasin = new Magasin()
            .typeMagasin(UPDATED_TYPE_MAGASIN)
            .nomCourt(UPDATED_NOM_COURT)
            .nomLong(UPDATED_NOM_LONG)
            .addressePostal(UPDATED_ADDRESSE_POSTAL)
            .status(UPDATED_STATUS)
            .phone(UPDATED_PHONE)
            .mobile(UPDATED_MOBILE)
            .commentaire(UPDATED_COMMENTAIRE)
            .autreCommentaire(UPDATED_AUTRE_COMMENTAIRE)
            .entete(UPDATED_ENTETE)
            .compteContribuable(UPDATED_COMPTE_CONTRIBUABLE)
            .registreCommerce(UPDATED_REGISTRE_COMMERCE)
            .registreImposition(UPDATED_REGISTRE_IMPOSITION)
            .centreImposition(UPDATED_CENTRE_IMPOSITION)
            .numComptable(UPDATED_NUM_COMPTABLE);
        return magasin;
    }

    @BeforeEach
    public void initTest() {
        magasin = createEntity(em);
    }

    @Test
    @Transactional
    public void createMagasin() throws Exception {
        int databaseSizeBeforeCreate = magasinRepository.findAll().size();
        // Create the Magasin
        MagasinDTO magasinDTO = magasinMapper.toDto(magasin);
        restMagasinMockMvc.perform(post("/api/magasins").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(magasinDTO)))
            .andExpect(status().isCreated());

        // Validate the Magasin in the database
        List<Magasin> magasinList = magasinRepository.findAll();
        assertThat(magasinList).hasSize(databaseSizeBeforeCreate + 1);
        Magasin testMagasin = magasinList.get(magasinList.size() - 1);
        assertThat(testMagasin.getTypeMagasin()).isEqualTo(DEFAULT_TYPE_MAGASIN);
        assertThat(testMagasin.getNomCourt()).isEqualTo(DEFAULT_NOM_COURT);
        assertThat(testMagasin.getNomLong()).isEqualTo(DEFAULT_NOM_LONG);
        assertThat(testMagasin.getAddressePostal()).isEqualTo(DEFAULT_ADDRESSE_POSTAL);
        assertThat(testMagasin.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testMagasin.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testMagasin.getMobile()).isEqualTo(DEFAULT_MOBILE);
        assertThat(testMagasin.getCommentaire()).isEqualTo(DEFAULT_COMMENTAIRE);
        assertThat(testMagasin.getAutreCommentaire()).isEqualTo(DEFAULT_AUTRE_COMMENTAIRE);
        assertThat(testMagasin.getEntete()).isEqualTo(DEFAULT_ENTETE);
        assertThat(testMagasin.getCompteContribuable()).isEqualTo(DEFAULT_COMPTE_CONTRIBUABLE);
        assertThat(testMagasin.getRegistreCommerce()).isEqualTo(DEFAULT_REGISTRE_COMMERCE);
        assertThat(testMagasin.getRegistreImposition()).isEqualTo(DEFAULT_REGISTRE_IMPOSITION);
        assertThat(testMagasin.getCentreImposition()).isEqualTo(DEFAULT_CENTRE_IMPOSITION);
        assertThat(testMagasin.getNumComptable()).isEqualTo(DEFAULT_NUM_COMPTABLE);
    }

    @Test
    @Transactional
    public void createMagasinWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = magasinRepository.findAll().size();

        // Create the Magasin with an existing ID
        magasin.setId(1L);
        MagasinDTO magasinDTO = magasinMapper.toDto(magasin);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMagasinMockMvc.perform(post("/api/magasins").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(magasinDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Magasin in the database
        List<Magasin> magasinList = magasinRepository.findAll();
        assertThat(magasinList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkTypeMagasinIsRequired() throws Exception {
        int databaseSizeBeforeTest = magasinRepository.findAll().size();
        // set the field null
        magasin.setTypeMagasin(null);

        // Create the Magasin, which fails.
        MagasinDTO magasinDTO = magasinMapper.toDto(magasin);


        restMagasinMockMvc.perform(post("/api/magasins").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(magasinDTO)))
            .andExpect(status().isBadRequest());

        List<Magasin> magasinList = magasinRepository.findAll();
        assertThat(magasinList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNomLongIsRequired() throws Exception {
        int databaseSizeBeforeTest = magasinRepository.findAll().size();
        // set the field null
        magasin.setNomLong(null);

        // Create the Magasin, which fails.
        MagasinDTO magasinDTO = magasinMapper.toDto(magasin);


        restMagasinMockMvc.perform(post("/api/magasins").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(magasinDTO)))
            .andExpect(status().isBadRequest());

        List<Magasin> magasinList = magasinRepository.findAll();
        assertThat(magasinList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = magasinRepository.findAll().size();
        // set the field null
        magasin.setStatus(null);

        // Create the Magasin, which fails.
        MagasinDTO magasinDTO = magasinMapper.toDto(magasin);


        restMagasinMockMvc.perform(post("/api/magasins").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(magasinDTO)))
            .andExpect(status().isBadRequest());

        List<Magasin> magasinList = magasinRepository.findAll();
        assertThat(magasinList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMagasins() throws Exception {
        // Initialize the database
        magasinRepository.saveAndFlush(magasin);

        // Get all the magasinList
        restMagasinMockMvc.perform(get("/api/magasins?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(magasin.getId().intValue())))
            .andExpect(jsonPath("$.[*].typeMagasin").value(hasItem(DEFAULT_TYPE_MAGASIN.toString())))
            .andExpect(jsonPath("$.[*].nomCourt").value(hasItem(DEFAULT_NOM_COURT)))
            .andExpect(jsonPath("$.[*].nomLong").value(hasItem(DEFAULT_NOM_LONG)))
            .andExpect(jsonPath("$.[*].addressePostal").value(hasItem(DEFAULT_ADDRESSE_POSTAL)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE)))
            .andExpect(jsonPath("$.[*].mobile").value(hasItem(DEFAULT_MOBILE)))
            .andExpect(jsonPath("$.[*].commentaire").value(hasItem(DEFAULT_COMMENTAIRE)))
            .andExpect(jsonPath("$.[*].autreCommentaire").value(hasItem(DEFAULT_AUTRE_COMMENTAIRE)))
            .andExpect(jsonPath("$.[*].entete").value(hasItem(DEFAULT_ENTETE)))
            .andExpect(jsonPath("$.[*].compteContribuable").value(hasItem(DEFAULT_COMPTE_CONTRIBUABLE)))
            .andExpect(jsonPath("$.[*].registreCommerce").value(hasItem(DEFAULT_REGISTRE_COMMERCE)))
            .andExpect(jsonPath("$.[*].registreImposition").value(hasItem(DEFAULT_REGISTRE_IMPOSITION)))
            .andExpect(jsonPath("$.[*].centreImposition").value(hasItem(DEFAULT_CENTRE_IMPOSITION)))
            .andExpect(jsonPath("$.[*].numComptable").value(hasItem(DEFAULT_NUM_COMPTABLE)));
    }
    
    @Test
    @Transactional
    public void getMagasin() throws Exception {
        // Initialize the database
        magasinRepository.saveAndFlush(magasin);

        // Get the magasin
        restMagasinMockMvc.perform(get("/api/magasins/{id}", magasin.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(magasin.getId().intValue()))
            .andExpect(jsonPath("$.typeMagasin").value(DEFAULT_TYPE_MAGASIN.toString()))
            .andExpect(jsonPath("$.nomCourt").value(DEFAULT_NOM_COURT))
            .andExpect(jsonPath("$.nomLong").value(DEFAULT_NOM_LONG))
            .andExpect(jsonPath("$.addressePostal").value(DEFAULT_ADDRESSE_POSTAL))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE))
            .andExpect(jsonPath("$.mobile").value(DEFAULT_MOBILE))
            .andExpect(jsonPath("$.commentaire").value(DEFAULT_COMMENTAIRE))
            .andExpect(jsonPath("$.autreCommentaire").value(DEFAULT_AUTRE_COMMENTAIRE))
            .andExpect(jsonPath("$.entete").value(DEFAULT_ENTETE))
            .andExpect(jsonPath("$.compteContribuable").value(DEFAULT_COMPTE_CONTRIBUABLE))
            .andExpect(jsonPath("$.registreCommerce").value(DEFAULT_REGISTRE_COMMERCE))
            .andExpect(jsonPath("$.registreImposition").value(DEFAULT_REGISTRE_IMPOSITION))
            .andExpect(jsonPath("$.centreImposition").value(DEFAULT_CENTRE_IMPOSITION))
            .andExpect(jsonPath("$.numComptable").value(DEFAULT_NUM_COMPTABLE));
    }
    @Test
    @Transactional
    public void getNonExistingMagasin() throws Exception {
        // Get the magasin
        restMagasinMockMvc.perform(get("/api/magasins/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMagasin() throws Exception {
        // Initialize the database
        magasinRepository.saveAndFlush(magasin);

        int databaseSizeBeforeUpdate = magasinRepository.findAll().size();

        // Update the magasin
        Magasin updatedMagasin = magasinRepository.findById(magasin.getId()).get();
        // Disconnect from session so that the updates on updatedMagasin are not directly saved in db
        em.detach(updatedMagasin);
        updatedMagasin
            .typeMagasin(UPDATED_TYPE_MAGASIN)
            .nomCourt(UPDATED_NOM_COURT)
            .nomLong(UPDATED_NOM_LONG)
            .addressePostal(UPDATED_ADDRESSE_POSTAL)
            .status(UPDATED_STATUS)
            .phone(UPDATED_PHONE)
            .mobile(UPDATED_MOBILE)
            .commentaire(UPDATED_COMMENTAIRE)
            .autreCommentaire(UPDATED_AUTRE_COMMENTAIRE)
            .entete(UPDATED_ENTETE)
            .compteContribuable(UPDATED_COMPTE_CONTRIBUABLE)
            .registreCommerce(UPDATED_REGISTRE_COMMERCE)
            .registreImposition(UPDATED_REGISTRE_IMPOSITION)
            .centreImposition(UPDATED_CENTRE_IMPOSITION)
            .numComptable(UPDATED_NUM_COMPTABLE);
        MagasinDTO magasinDTO = magasinMapper.toDto(updatedMagasin);

        restMagasinMockMvc.perform(put("/api/magasins").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(magasinDTO)))
            .andExpect(status().isOk());

        // Validate the Magasin in the database
        List<Magasin> magasinList = magasinRepository.findAll();
        assertThat(magasinList).hasSize(databaseSizeBeforeUpdate);
        Magasin testMagasin = magasinList.get(magasinList.size() - 1);
        assertThat(testMagasin.getTypeMagasin()).isEqualTo(UPDATED_TYPE_MAGASIN);
        assertThat(testMagasin.getNomCourt()).isEqualTo(UPDATED_NOM_COURT);
        assertThat(testMagasin.getNomLong()).isEqualTo(UPDATED_NOM_LONG);
        assertThat(testMagasin.getAddressePostal()).isEqualTo(UPDATED_ADDRESSE_POSTAL);
        assertThat(testMagasin.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testMagasin.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testMagasin.getMobile()).isEqualTo(UPDATED_MOBILE);
        assertThat(testMagasin.getCommentaire()).isEqualTo(UPDATED_COMMENTAIRE);
        assertThat(testMagasin.getAutreCommentaire()).isEqualTo(UPDATED_AUTRE_COMMENTAIRE);
        assertThat(testMagasin.getEntete()).isEqualTo(UPDATED_ENTETE);
        assertThat(testMagasin.getCompteContribuable()).isEqualTo(UPDATED_COMPTE_CONTRIBUABLE);
        assertThat(testMagasin.getRegistreCommerce()).isEqualTo(UPDATED_REGISTRE_COMMERCE);
        assertThat(testMagasin.getRegistreImposition()).isEqualTo(UPDATED_REGISTRE_IMPOSITION);
        assertThat(testMagasin.getCentreImposition()).isEqualTo(UPDATED_CENTRE_IMPOSITION);
        assertThat(testMagasin.getNumComptable()).isEqualTo(UPDATED_NUM_COMPTABLE);
    }

    @Test
    @Transactional
    public void updateNonExistingMagasin() throws Exception {
        int databaseSizeBeforeUpdate = magasinRepository.findAll().size();

        // Create the Magasin
        MagasinDTO magasinDTO = magasinMapper.toDto(magasin);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMagasinMockMvc.perform(put("/api/magasins").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(magasinDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Magasin in the database
        List<Magasin> magasinList = magasinRepository.findAll();
        assertThat(magasinList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMagasin() throws Exception {
        // Initialize the database
        magasinRepository.saveAndFlush(magasin);

        int databaseSizeBeforeDelete = magasinRepository.findAll().size();

        // Delete the magasin
        restMagasinMockMvc.perform(delete("/api/magasins/{id}", magasin.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Magasin> magasinList = magasinRepository.findAll();
        assertThat(magasinList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
