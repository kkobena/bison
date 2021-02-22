package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.service.StockProduitService;
import com.kobe.nucleus.web.rest.errors.BadRequestAlertException;
import com.kobe.nucleus.service.dto.StockProduitDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.kobe.nucleus.domain.StockProduit}.
 */
@RestController
@RequestMapping("/api")
public class StockProduitResource {

    private final Logger log = LoggerFactory.getLogger(StockProduitResource.class);

    private static final String ENTITY_NAME = "stockProduit";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final StockProduitService stockProduitService;

    public StockProduitResource(StockProduitService stockProduitService) {
        this.stockProduitService = stockProduitService;
    }

    /**
     * {@code POST  /stock-produits} : Create a new stockProduit.
     *
     * @param stockProduitDTO the stockProduitDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new stockProduitDTO, or with status {@code 400 (Bad Request)} if the stockProduit has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/stock-produits")
    public ResponseEntity<StockProduitDTO> createStockProduit(@Valid @RequestBody StockProduitDTO stockProduitDTO) throws URISyntaxException {
        log.debug("REST request to save StockProduit : {}", stockProduitDTO);
        if (stockProduitDTO.getId() != null) {
            throw new BadRequestAlertException("A new stockProduit cannot already have an ID", ENTITY_NAME, "idexists");
        }
        StockProduitDTO result = stockProduitService.save(stockProduitDTO);
        return ResponseEntity.created(new URI("/api/stock-produits/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /stock-produits} : Updates an existing stockProduit.
     *
     * @param stockProduitDTO the stockProduitDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated stockProduitDTO,
     * or with status {@code 400 (Bad Request)} if the stockProduitDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the stockProduitDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/stock-produits")
    public ResponseEntity<StockProduitDTO> updateStockProduit(@Valid @RequestBody StockProduitDTO stockProduitDTO) throws URISyntaxException {
        log.debug("REST request to update StockProduit : {}", stockProduitDTO);
        if (stockProduitDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        StockProduitDTO result = stockProduitService.save(stockProduitDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, stockProduitDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /stock-produits} : get all the stockProduits.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of stockProduits in body.
     */
    @GetMapping("/stock-produits")
    public ResponseEntity<List<StockProduitDTO>> getAllStockProduits(Pageable pageable) {
        log.debug("REST request to get a page of StockProduits");
        Page<StockProduitDTO> page = stockProduitService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /stock-produits/:id} : get the "id" stockProduit.
     *
     * @param id the id of the stockProduitDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the stockProduitDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/stock-produits/{id}")
    public ResponseEntity<StockProduitDTO> getStockProduit(@PathVariable Long id) {
        log.debug("REST request to get StockProduit : {}", id);
        Optional<StockProduitDTO> stockProduitDTO = stockProduitService.findOne(id);
        return ResponseUtil.wrapOrNotFound(stockProduitDTO);
    }

    /**
     * {@code DELETE  /stock-produits/:id} : delete the "id" stockProduit.
     *
     * @param id the id of the stockProduitDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/stock-produits/{id}")
    public ResponseEntity<Void> deleteStockProduit(@PathVariable Long id) {
        log.debug("REST request to delete StockProduit : {}", id);

        stockProduitService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
