package ro.hmihai.fms.web.rest;

import ro.hmihai.fms.service.ProductionAreaService;
import ro.hmihai.fms.web.rest.errors.BadRequestAlertException;
import ro.hmihai.fms.service.dto.ProductionAreaDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link ro.hmihai.fms.domain.ProductionArea}.
 */
@RestController
@RequestMapping("/api")
public class ProductionAreaResource {

    private final Logger log = LoggerFactory.getLogger(ProductionAreaResource.class);

    private static final String ENTITY_NAME = "productionArea";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProductionAreaService productionAreaService;

    public ProductionAreaResource(ProductionAreaService productionAreaService) {
        this.productionAreaService = productionAreaService;
    }

    /**
     * {@code POST  /production-areas} : Create a new productionArea.
     *
     * @param productionAreaDTO the productionAreaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new productionAreaDTO, or with status {@code 400 (Bad Request)} if the productionArea has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/production-areas")
    public ResponseEntity<ProductionAreaDTO> createProductionArea(@RequestBody ProductionAreaDTO productionAreaDTO) throws URISyntaxException {
        log.debug("REST request to save ProductionArea : {}", productionAreaDTO);
        if (productionAreaDTO.getId() != null) {
            throw new BadRequestAlertException("A new productionArea cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProductionAreaDTO result = productionAreaService.save(productionAreaDTO);
        return ResponseEntity.created(new URI("/api/production-areas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /production-areas} : Updates an existing productionArea.
     *
     * @param productionAreaDTO the productionAreaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated productionAreaDTO,
     * or with status {@code 400 (Bad Request)} if the productionAreaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the productionAreaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/production-areas")
    public ResponseEntity<ProductionAreaDTO> updateProductionArea(@RequestBody ProductionAreaDTO productionAreaDTO) throws URISyntaxException {
        log.debug("REST request to update ProductionArea : {}", productionAreaDTO);
        if (productionAreaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProductionAreaDTO result = productionAreaService.save(productionAreaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, productionAreaDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /production-areas} : get all the productionAreas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of productionAreas in body.
     */
    @GetMapping("/production-areas")
    public List<ProductionAreaDTO> getAllProductionAreas() {
        log.debug("REST request to get all ProductionAreas");
        return productionAreaService.findAll();
    }

    /**
     * {@code GET  /production-areas/:id} : get the "id" productionArea.
     *
     * @param id the id of the productionAreaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the productionAreaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/production-areas/{id}")
    public ResponseEntity<ProductionAreaDTO> getProductionArea(@PathVariable Long id) {
        log.debug("REST request to get ProductionArea : {}", id);
        Optional<ProductionAreaDTO> productionAreaDTO = productionAreaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(productionAreaDTO);
    }

    /**
     * {@code DELETE  /production-areas/:id} : delete the "id" productionArea.
     *
     * @param id the id of the productionAreaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/production-areas/{id}")
    public ResponseEntity<Void> deleteProductionArea(@PathVariable Long id) {
        log.debug("REST request to delete ProductionArea : {}", id);
        productionAreaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
