package com.kobe.nucleus.web.rest;

import com.kobe.nucleus.service.StockReportService;
import com.kobe.nucleus.web.rest.errors.BadRequestAlertException;
import com.kobe.nucleus.service.dto.StockReportDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.kobe.nucleus.domain.StockReport}.
 */
@RestController
@RequestMapping("/api")
public class StockReportResource {

    private final Logger log = LoggerFactory.getLogger(StockReportResource.class);

    private static final String ENTITY_NAME = "stockReport";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final StockReportService stockReportService;

    public StockReportResource(StockReportService stockReportService) {
        this.stockReportService = stockReportService;
    }

    /**
     * {@code POST  /stock-reports} : Create a new stockReport.
     *
     * @param stockReportDTO the stockReportDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new stockReportDTO, or with status {@code 400 (Bad Request)} if the stockReport has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/stock-reports")
    public ResponseEntity<StockReportDTO> createStockReport(@Valid @RequestBody StockReportDTO stockReportDTO) throws URISyntaxException {
        log.debug("REST request to save StockReport : {}", stockReportDTO);
        if (stockReportDTO.getId() != null) {
            throw new BadRequestAlertException("A new stockReport cannot already have an ID", ENTITY_NAME, "idexists");
        }
        StockReportDTO result = stockReportService.save(stockReportDTO);
        return ResponseEntity.created(new URI("/api/stock-reports/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /stock-reports} : Updates an existing stockReport.
     *
     * @param stockReportDTO the stockReportDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated stockReportDTO,
     * or with status {@code 400 (Bad Request)} if the stockReportDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the stockReportDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/stock-reports")
    public ResponseEntity<StockReportDTO> updateStockReport(@Valid @RequestBody StockReportDTO stockReportDTO) throws URISyntaxException {
        log.debug("REST request to update StockReport : {}", stockReportDTO);
        if (stockReportDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        StockReportDTO result = stockReportService.save(stockReportDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, stockReportDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /stock-reports} : get all the stockReports.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of stockReports in body.
     */
    @GetMapping("/stock-reports")
    public ResponseEntity<List<StockReportDTO>> getAllStockReports(Pageable pageable) {
        log.debug("REST request to get a page of StockReports");
        Page<StockReportDTO> page = stockReportService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /stock-reports/:id} : get the "id" stockReport.
     *
     * @param id the id of the stockReportDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the stockReportDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/stock-reports/{id}")
    public ResponseEntity<StockReportDTO> getStockReport(@PathVariable Long id) {
        log.debug("REST request to get StockReport : {}", id);
        Optional<StockReportDTO> stockReportDTO = stockReportService.findOne(id);
        return ResponseUtil.wrapOrNotFound(stockReportDTO);
    }

    /**
     * {@code DELETE  /stock-reports/:id} : delete the "id" stockReport.
     *
     * @param id the id of the stockReportDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/stock-reports/{id}")
    public ResponseEntity<Void> deleteStockReport(@PathVariable Long id) {
        log.debug("REST request to delete StockReport : {}", id);

        stockReportService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
