package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.domain.Stockout;
import com.kobe.nucleus.service.StockoutService;
import com.kobe.nucleus.web.rest.errors.BadRequestAlertException;
import com.kobe.nucleus.service.dto.StockoutCriteria;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.kobe.nucleus.domain.Stockout}.
 */
@RestController
@RequestMapping("/api")
public class StockoutResource {

    private final Logger log = LoggerFactory.getLogger(StockoutResource.class);

    private static final String ENTITY_NAME = "stockout";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final StockoutService stockoutService;



    public StockoutResource(StockoutService stockoutService) {
        this.stockoutService = stockoutService;
     
    }

    /**
     * {@code POST  /stockouts} : Create a new stockout.
     *
     * @param stockout the stockout to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new stockout, or with status {@code 400 (Bad Request)} if the stockout has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/stockouts")
    public ResponseEntity<Stockout> createStockout(@Valid @RequestBody Stockout stockout) throws URISyntaxException {
        log.debug("REST request to save Stockout : {}", stockout);
        if (stockout.getId() != null) {
            throw new BadRequestAlertException("A new stockout cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Stockout result = stockoutService.save(stockout);
        return ResponseEntity.created(new URI("/api/stockouts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /stockouts} : Updates an existing stockout.
     *
     * @param stockout the stockout to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated stockout,
     * or with status {@code 400 (Bad Request)} if the stockout is not valid,
     * or with status {@code 500 (Internal Server Error)} if the stockout couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/stockouts")
    public ResponseEntity<Stockout> updateStockout(@Valid @RequestBody Stockout stockout) throws URISyntaxException {
        log.debug("REST request to update Stockout : {}", stockout);
        if (stockout.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Stockout result = stockoutService.save(stockout);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, stockout.getId().toString()))
            .body(result);
    }


    

    /**
     * {@code GET  /stockouts/:id} : get the "id" stockout.
     *
     * @param id the id of the stockout to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the stockout, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/stockouts/{id}")
    public ResponseEntity<Stockout> getStockout(@PathVariable Long id) {
        log.debug("REST request to get Stockout : {}", id);
        Optional<Stockout> stockout = stockoutService.findOne(id);
        return ResponseUtil.wrapOrNotFound(stockout);
    }

    /**
     * {@code DELETE  /stockouts/:id} : delete the "id" stockout.
     *
     * @param id the id of the stockout to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/stockouts/{id}")
    public ResponseEntity<Void> deleteStockout(@PathVariable Long id) {
        log.debug("REST request to delete Stockout : {}", id);

        stockoutService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
